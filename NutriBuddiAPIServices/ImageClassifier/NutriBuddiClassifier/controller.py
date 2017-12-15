from __future__ import print_function
import boto3
import botocore
import os
import sys
import uuid
from PIL import Image
import PIL.Image
import tensorflow as tf
import keras
from keras.models import load_model
from keras.preprocessing.image import ImageDataGenerator, img_to_array, load_img
import numpy as np
from Classifier import FoodClassifier

from flask import Flask, request, json
application = Flask(__name__)
application.debug = True

#transfer = S3Transfer(boto3.client('s3', cfg.AWS_REGION, aws_access_key_id=cfg.AWS_APP_ID, aws_secret_access_key=cfg.AWS_APP_SECRET))
s3_client = boto3.client('s3', aws_access_key_id='AKIAIFVDJNCPZE3LX6MA', aws_secret_access_key='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')
modelpath = './static/extModel1512927878.h5'
labels = ['apple', 'banana', 'caesar salad', 'carrot', 'chow mein', 'french fries', 'hamburger', 'orange', 'pizza',  'spaghetti', 'taco', 'white rice']
classifier = FoodClassifier(modelpath, labels)

@application.route('/', methods=['GET', 'POST'])
@application.route('/classify', methods=['GET', 'POST'])
def index():
	_imageString = request.args.get("img")
	if _imageString:
		download_path = os.path.join('tmp', _imageString)
		try:
			s3_client.download_file('nutribuddi', _imageString, download_path)
		except botocore.exceptions.ClientError as e:
			if e.response['Error']['Code'] == "404":
				print("The object does not exist.")
			else:
				raise
		if os.path.exists(download_path):
			_classification = classifier.predict(download_path)
			os.remove(download_path)
			return json.dumps({'classification': _classification})
		else:
			return json.dumps({'classification': 'Invalid Image Key'})
	else:
		return json.dumps({'message':'img parameter required'})

if __name__ == "__main__":
	application.run()
