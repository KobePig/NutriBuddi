using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using System.IO;
using UnityEngine.SceneManagement;
using System;

public class TakePicture : MonoBehaviour {

	string fileName;
	public Canvas mainCanvas;
	public Button cameraButton;

	// Use this for initialization
	void Start () {

		cameraButton.onClick.AddListener (cameraCLick);
		//userSession = GameObject.Find ("PersistentGameObject");
	}
	
	// Update is called once per frame
	void Update () {

//		string path = System.IO.Path.Combine (Application.persistentDataPath, fileName);
//
//		if (File.Exists (path)) {
//			Debug.Log ("File Exists");
//		}
		
	}

	// when camera button pressed
	public void cameraCLick() {

		mainCanvas.enabled = false;
		StartCoroutine("TakeScreenShot");

		//canvas.enabled = true;
		Debug.Log ("Screenshot Taken");
		Debug.Log (Application.persistentDataPath);

		//**************************************
		//string filePath = System.IO.Path.Combine(Application.persistentDataPath, UserSession.session.fileName);
		//string filePath = System.IO.Path.Combine(Application.persistentDataPath, fileName);

//		while (File.Exists (filePath)) {
//			SceneManager.LoadScene ("ShowPictureScene");
//		}
		//******************************************

		SceneManager.LoadScene ("ShowPictureScene");
	}


	// take screenshot
	private IEnumerator TakeScreenShot()
	{
		yield return new WaitForEndOfFrame();

		//		var directory = new DirectoryInfo(Application.dataPath);
		//		var path = Path.Combine(directory.Parent.FullName, string.Format("Screenshot_{0}.png", "10"));
		//		Debug.Log("Taking screenshot to " + path);

		long time = DateTime.Now.Ticks / TimeSpan.TicksPerMillisecond;
		string fileName = UserSession.session.userEmailAddress+"_"+ time +".png";

		//save it to UserSession
		UserSession.session.fileName = UserSession.session.userEmailAddress + "_" + time + ".png";
		UserSession.session.timeStamp = time;


		//ScreenCapture.CaptureScreenshot(fileName, 3);
		ScreenCapture.CaptureScreenshot(fileName);
		//m_screenShotLock = false;

		//Wait for 4 frames
		for (int i = 0; i < 5; i++)
		{
			yield return null;
		}

	}
}
