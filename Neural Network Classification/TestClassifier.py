# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""


class FoodClassifier:
    #Class Attributes:
    #model - the underlying keras model
    #labels - the labels to be associated with the activation of each output neuron. 
    #Labels must be the same size as the output layer of the neural network.    
    
    
    def __init__(self, modelpath, labels, min_confidence = 0.6):
        from keras.models import load_model
        from keras.applications.resnet50 import ResNet50
        self.resnet = ResNet50(include_top=False,weights='imagenet',pooling='max',input_shape=(224,224,3))
        self.extModel = load_model(modelpath)
        
        if(isinstance(labels,str)):
            #its a file path
            from os.path import exists
            if(exists(labels)):
                f = open(labels,'r')
                x = f.readlines()
                y = []
                for i in x:
                    y.append(i.split('\n')[0])
                self.labels = y
        else:
            self.labels = labels
        
        self.num_classes = len(labels)
        self.min_confidence=min_confidence
    
    
    def predict(self,img):
        import os
        from PIL import Image
        from keras.preprocessing.image import img_to_array
        import numpy as np 
        #check if image is a filepath
        if(isinstance(img,str)):
            if(not os.path.exists(img)):
                print("Error: Invalid File Path")
                return ""
            else:
                #if its a filepath, convert to PIL image
                img = Image.open(img)
        
        #resize image
        #shape from model input
        shape = self.resnet.input_shape
        imgr = img.resize(shape[1:3])
        
        x = img_to_array(imgr).reshape((1,shape[1],shape[2],shape[3]))
        
        
        #predict
        features = self.resnet.predict(x)
        prediction = self.extModel.predict(features)
        
        #get max of predictions and return label(s)
        predIdx = np.argmax(prediction)
        if(prediction[0,predIdx]<self.min_confidence):
            return ""
        else:
            return self.labels[predIdx]
    
    
    
    def set_extModel(self,model):
        self.extModel = model
    
    def get_extModel(self):
        return self.extModel
    
    
    def set_labels(self,labels):
        self.labels = labels
        
    def get_labels(self):
        return self.labels
    
    
    def set_min_confidence(self,conf):
        self.min_confidence=conf
    
    def get_min_confidence(self):
        return self.min_confidence
    
        
def generate_features_from_directory(location,target_image_count,model=None):
    #generates feature maps from the convolutional layers of ResNet50 using all
    #images from the directory
    #INPUT:
        #directory containing NESTED DIRECTORIES  of images. (Very Important)
        #the number of feature maps to generate for each image class
    #OUTPUT:
        #a npy file containing the 2048-dimensional feature vector 
        #produced by ResNet50's convolutional layers
    
    #data is generated in batches of 32
    
    import numpy as np
    
    from keras.preprocessing.image import ImageDataGenerator
    from keras.applications.resnet50 import ResNet50
    from os import listdir
    from os.path import isdir 
    
    #create the model, if not defined
    if model==None:
        model = ResNet50(weights='imagenet',include_top=False,pooling='max')
    
    #create the data generation
    
    datagen = ImageDataGenerator()
    
    #for each directory in 
    if(not isdir(location)):
        print("could not find location: " + location)
        return
    for label in listdir(location):
        #first check that its a directory
        label_path = location+'/'+label
        if(not isdir(label_path)):
            continue
        
        #create the data generator
        #Output size is 256x256 to fit the ResNet50
        print("Generating feature maps for " + label + "...")
        generator = datagen.flow_from_directory(
                 label_path,
                 target_size = (224,224),
                 batch_size = 32, 
                 class_mode=None)
        
        #use ResNet50 to create the features
        features = model.predict_generator(generator,target_image_count/32)
        #features = np.reshape(features,(features.shape[0],features.shape[3]))
        
        #save the features in a numpy binary
        np.save(location+'/'+label+'.npy', features)
        
    
        
        
def create_data_set(data_path,output_folder,save_to_file=True):
    #combines all npy files into one large file with their respective labels
    #INPUTS:
        #a directory containing npy fils of all different classes
    #Outputs:
        #training array and training labels
        #label array is returned as a one hot encoding
        #label names
        
    from os.path import isdir
    from os import listdir
    import numpy as np
    
    #find out how many classes
    num_classes = 0
    label_names = []
    
    if(not isdir(data_path)):
        print("Could not find directory: "+ data_path)
        return
    
    data_contents = listdir(data_path)
    for f in data_contents:
        if(f.endswith('.npy')):
            num_classes +=1
            label_names.append(f.split('.')[0])
    
    if(num_classes==0):
        print("Could not find any data files in directory: "+data_path)
        return
    
    #generate one-hot label vectors
    labels = np.zeros([num_classes,num_classes])
    for i in range(0,num_classes):
        labels[i][i]=1
    
    #load all arrays into memory. 
    #In the future, might need to do this on either a high ram machine
    #or find another way to concatenate data
    arrays = []
    sizes = []
    for f in data_contents:
        if(f.endswith('.npy')):
            arr = np.load(data_path+'/'+f)
            sizes.append(arr.shape[0])
            arrays.append(arr)
    
    X = np.vstack([arr for arr in arrays])
    
    #load the labels into memory
    labelcodes = []
    for i in range(0,num_classes):
        labelcodes.append(np.vstack([labels[i]]*sizes[i]))
    y = np.vstack([l for l in labelcodes])
    
    if(save_to_file):
        np.save(output_folder+'/data_set.npy',X)
        np.save(output_folder+'/label_codes.npy',y)
        with open(output_folder+"/labels.txt","w") as output:
            output.write("".join([label + '\n' for label in label_names]))
    
    return X,y,label_names
        


def train_classifier_from_images(train_dir,train_size,val_dir,val_size,output_dir):
    #INPUTS:
        #train_dir is the directory containig the training images
        #test_dir is the directory containing the validation images
        #output_dir is the directory to save the trained model
        #train_size is the number of images to generate for each training class
        #val_size is the number of images to generate for each validation class
    #OUTPUTS
    #A model that takes as input a 2048-vector of feature maps and outputs
    #a prediction of what an image with those features might be.
    #The labels file is also placed in this directory
    
    #The model created is an SVM with softmax activation.
    from time import time
    from keras.applications.resnet50 import ResNet50
    from keras.models import Sequential
    from keras.optimizers import SGD
    from keras.regularizers import l2
    from keras.layers import Dense
    from sklearn.utils import shuffle
    from keras.callbacks import EarlyStopping, ModelCheckpoint
    
    #import ResNet50 without top layer
    print("Loading the ResNet50 Network...")
    resnet = ResNet50(weights='imagenet',include_top=False,pooling='max')
    
    #create the training and validation datasets for each class
    print("Generating Training Set...")
    generate_features_from_directory(train_dir,train_size,model=resnet)
    print("Generating Testing Set...")
    generate_features_from_directory(val_dir,val_size,model=resnet)
    
    #create the combined dataset
    print("Combining datasets...")
    from os.path import isdir
    from os import makedirs
    
    if(not isdir(train_dir)):
        print("No directory named " +train_dir)
        return
    if(not isdir(val_dir)):
        print("No directory named " + val_dir)
        return
    
    if(not isdir(output_dir)):
        makedirs(output_dir)
        
    if(not isdir(output_dir + "/train")):
        makedirs(output_dir + "/train")
    if(not isdir(output_dir + "/validation")):
        makedirs(output_dir + "/validation")
    
    X_train,y_train,labels = create_data_set(train_dir,output_dir+"/train",save_to_file=True)
    X_val,y_val,labels = create_data_set(val_dir,output_dir+"/validation",save_to_file=True)
    
    #shuffle the train data
    X_train,y_train = shuffle(X_train,y_train)
    
    num_classes = len(labels)
    
    #create the extension model
    print("Creating extension model...")
    extModel = Sequential()
    extModel.add(Dense(256,input_shape=(2048,), activation='relu'))
    extModel.add(Dense(256,activation='relu'))
    extModel.add(Dense(num_classes,activation='softmax'))
    extModel.compile(loss='categorical_crossentropy',optimizer=SGD(lr=0.01,momentum=0.9),metrics=["accuracy"])
    
    #callbacks
    checkpoint = ModelCheckpoint(output_dir + "/extModel"+str(int(time()))+".h5", monitor='val_acc', verbose=1, save_best_only=True, save_weights_only=False, mode='auto', period=1)
    early = EarlyStopping(monitor='val_acc', min_delta=0, patience=10, verbose=1, mode='auto')
    
    
    with open(output_dir+"/labels.txt","w") as output:
        output.write("".join([label + '\n' for label in labels]))
    
    #train model
    print("Training...")
    extModel.fit(X_train,y_train,
                 batch_size=32,
                 epochs=50,
                 validation_data=(X_val,y_val),
                 callbacks = [checkpoint,early])
    
    return extModel


def train_classifier_from_data(train_dir,val_dir,labels,output_dir):
    from time import time
    from keras.models import Sequential
    from keras.optimizers import SGD
    from keras.layers import Dense, BatchNormalization
    from sklearn.utils import shuffle
    from keras.callbacks import EarlyStopping, ModelCheckpoint
    import numpy as np
    from os.path import isdir, exists
    
    if(not isdir(train_dir)):
        print("No directory found: " + train_dir)
        return
    
    if(not isdir(val_dir)):
        print("No directory found: " + val_dir)
        return
    if(not exists(labels)):
        print("no labels file found: " + labels)
    
    X_train = np.load(train_dir+'/data_set.npy')
    y_train = np.load(train_dir+'/label_codes.npy')
    X_val = np.load(val_dir+'/data_set.npy')
    y_val = np.load(val_dir+'/label_codes.npy')
    f = open(labels,'r')
    x = f.readlines()
    y = []
    for i in x:
        y.append(i.split('\n')[0])
    class_labels = y
    
    #shuffle the train data
    X_train,y_train = shuffle(X_train,y_train)
    X_val,y_val = shuffle(X_val,y_val)
    num_classes = len(class_labels)
    
    #create the extension model
    print("Creating extension model...")
    extModel = Sequential()
    extModel.add(Dense(256,input_shape=(2048,), activation='relu'))
    extModel.add(BatchNormalization())
    extModel.add(Dense(256,activation='relu'))
    extModel.add(BatchNormalization())
    extModel.add(Dense(num_classes,activation='softmax'))
    extModel.compile(loss='categorical_crossentropy',optimizer=SGD(lr=0.01,momentum=0.9),metrics=["accuracy"])
    
    #callbacks
    checkpoint = ModelCheckpoint(output_dir + "/extModel"+str(int(time()))+".h5", monitor='val_acc', verbose=1, save_best_only=True, save_weights_only=False, mode='auto', period=1)
    early = EarlyStopping(monitor='val_acc', min_delta=0, patience=10, verbose=1, mode='auto')
    
    
    with open(output_dir+"/labels.txt","w") as output:
        output.write("".join([label + '\n' for label in class_labels]))
    
    #train model
    print("Training...")
    extModel.fit(X_train,y_train,
                 batch_size=32,
                 epochs=50,
                 validation_data=(X_val,y_val),
                 callbacks = [checkpoint,early])
    
    return extModel

def add_to_train(train_dir,image,label, resnet):
    #INPUTS
        #Train_dir - the directory that all npy files are contained
        #image - the path to the image being added
        #resnet - the resnet model to be used for feature determination
        #label - the name of the item
    #Appends the features of the new item to the training set data for that label
    from PIL import Image
    from os.path import exists
    from keras.preprocessing.image import img_to_array 
    
    if(isinstance(image,str)):
        if(not exists(image)):
            print("Error: Invalid File Path")
            return ""
        else:
            #if its a filepath, convert to PIL image
            img = Image.open(image)
    
    shape = resnet.input_shape
    imgr = img.resize(shape[1:3])
    
    x = img_to_array(imgr).reshape((1,shape[1],shape[2],shape[3]))
    
    #predict
    features = resnet.predict(x)
    
    
    

    import numpy as np
    
    npyname = train_dir+'/'+label+'.npy'
    if(not exists(npyname)):
        np.save(npyname,features)    
    else:
        fullset = np.load(npyname)
        newset = np.append(fullset,features,axis=0)
        np.save(npyname,newset)
    
if __name__ == "__main__":
    train_dir = 'data/train'
    val_dir = 'data/val'
    train_size = 3000
    val_size = 300
    output_dir = 'demoOutput'
    train_classifier_from_images(train_dir,train_size,val_dir,val_size,output_dir)
        
        