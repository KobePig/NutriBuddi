using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using UnityEngine.UI;
using System;

public class ShowPhoto : MonoBehaviour {

	public string fileName;
	string url;
	public RawImage image;


	void Start () {
		fileName = UserSession.session.fileName;
		Debug.Log ("start called");
		Invoke("GetPhoto",2f);


	}
	
	// Update is called once per frame
	void Update () {

	}



	//THIS WORKS ONLY ON PHONE NOT ON PC
	// shows photo on the raw image
	public void GetPhoto()
	{    
		
		//#if !UNITY_EDITOR
		//string url = Application.persistentDataPath +"/"+fileName;
		string url = Application.persistentDataPath +"/"+fileName;

		string path = System.IO.Path.Combine (Application.persistentDataPath, fileName);

		var bytes = File.ReadAllBytes( url );

		//StartCoroutine (loadImageCoroutine(bytes));
		Texture2D texture = new Texture2D( 73, 73 );
		texture.LoadImage( bytes );
		image.texture = texture;
		Debug.Log("image shown?");
		//#endif
	}


//	IEnumerator loadImageCoroutine(byte[] bytesToTexturize) {
//		yield return new WaitForSeconds(2);
//
//		Texture2D texture = new Texture2D( 73, 73 );
//		texture.LoadImage( bytesToTexturize );
//		image.texture = texture;
//	}
}

