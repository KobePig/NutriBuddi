# -*- coding: utf-8 -*-
"""
Created on Sun Oct 08 11:45:37 2017

@author: Peter
"""
import urllib
import cv2
import numpy as np
import os
import subprocess
import math
#import mergevec


def main():
    link = "http://image-net.org/api/text/imagenet.synset.geturls?wnid=n07739125"
    direc = "apple"
    numNeg = 900
    store_raw_images(link,direc)
    found = find_uglies(direc,"ugly.jpg")
    print "Ugly images removed:",found
    create_bg('neg')
    info = "info"
    bg = "bg.txt"
    num_samples = 2000
    print "Creating Vector File..."
    vecpath = create_positives_new(direc,info, bg, num_samples)
    print "Vector File Created."
    vecpath = "info/positives.vec"
    print "Training..."
    train( vecpath, bg, num_samples*.8, numNeg, 10,"data")
    print "Training Complete."
    
    
def store_raw_images(images_link, store_path, pic_num=1, img_dim=None, quiet=False):
    #Retrieves all images from the provided link, storing them as a jpg with an
    #incremental number in the specified folder.
    #Arguments: 
        #images_link: The url to the image-net page containing all image links
        #store_path: the directory to store all images.
        #pic_num: Counter for image names. Starts from 0 by default
        #img_dim: dimension to store the image as. All images will be greyscale.
        #quiet: whether or not to hide output.
    #Returns: returns the counter for pictures
    
    #open the web page
    import socket
    socket.setdefaulttimeout(10)
    neg_image_urls = urllib.urlopen(images_link).read().decode('utf-8')
    
	 #create the directory if it doesnt exist
    if not os.path.exists(store_path):
        os.makedirs(store_path)
    
    for i in neg_image_urls.split('\n'):
        try:
            
            #print img name if requested
            if(quiet==False):
                print(i)
			  
            #image reference
            img_path = store_path + '/' + str(pic_num) + ".jpg"
            
            #get each image
            urllib.urlretrieve(i, img_path)
            
            #read each image in as a greyscale
            img = cv2.imread(img_path, cv2.IMREAD_COLOR)
            
            #resize the images
            if(img_dim is not None):
                img = cv2.resize(img, img_dim)

            #save to directory
            cv2.imwrite(img_path, img)
            pic_num += 1
            
        except Exception as e:
            print(str(e))  
            
    #return number of pictures up to this point
    return pic_num
                    
def find_uglies(folder, ugly):
    #Removes images similar to the specified ugly image
    #Arguments:
        #folder: The folder to remove images from
        #ugly: The image to remove duplicates of
    #Returns: The number of ugly images removed
    
    #get the ugly images
    uglies = []
    try:
        for ug in os.listdir(ugly):
            uglyImage = cv2.imread(ugly+'/'+ug,cv2.IMREAD_GRAYSCALE)
            uglyImage = cv2.resize(uglyImage,(128,128))
            uglies.append(uglyImage)
    except Exception as e:
        print(str(e))
        return -1
    
    #move through the folder
    num_uglies = 0
    for img in os.listdir(folder):
        try:
            #load image into memory
            current_img_path = str(folder) + '/' + str(img)
            question = cv2.imread(current_img_path,cv2.IMREAD_GRAYSCALE)
            #if its empty
            if(question==None):
                try:
                    os.remove(current_img_path)
                except Exception as e:
                    print(str(e))
                continue
                
            question = cv2.resize(question,(128,128))
            #if match
            for ug in uglies:
                if not(np.bitwise_xor(ug,question).any()):
                    num_uglies +=1
                    os.remove(current_img_path)
        except Exception as e:
            print(str(e))
            
    return num_uglies
+ 

def splitImageSet(rootFolder, outFolder, p):
    import os
    from os import listdir
    import numpy as np
    from PIL import Image
    cats = listdir(rootFolder) # a list of subfolder names    
    for cat in cats:
        print('Image Category...{}'.format(cat))
        
        folderPath = (os.path.join(rootFolder,cat))
        imgNames = listdir(folderPath)
        imgPaths = [os.path.join(folderPath,imgName) for imgName in imgNames]
        idx = np.random.permutation(len(imgPaths))
        trainIdx = idx[:int(p*len(idx))]
        testIdx = [ind for ind in idx if not ind in trainIdx]

        if not os.path.exists(os.path.join(outFolder,'Train',cat)):
            os.makedirs(os.path.join(outFolder,'Train',cat))
        for k in range(len(trainIdx)):
            img = Image.open(os.path.join(imgPaths[trainIdx[k]]))
            #temp = os.path.join(outFolder,'train',cat,imgNames[trainIdx[k]])
            img.save(os.path.join(outFolder,'Train',cat,imgNames[trainIdx[k]]))            
        if not os.path.exists(os.path.join(outFolder,'Test',cat)):
            os.makedirs(os.path.join(outFolder,'Test',cat))
        for k in range(len(testIdx)):
            img = Image.open(os.path.join(imgPaths[testIdx[k]]))
            img.save(os.path.join(outFolder,'Test',cat,imgNames[testIdx[k]])) 
            
    print('Split Done!')
    return




