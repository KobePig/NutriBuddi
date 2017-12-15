using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class StartCam : MonoBehaviour {

	public WebCamTexture webcamTexture;
	//public Button backButton;

	// Use this for initialization
	void Start () {

		webcamTexture = new WebCamTexture(WebCamTexture.devices [0].name, 1280, 720, 30);
		webcamTexture.Play();

		Renderer renderer = GetComponent<Renderer>();

		if (webcamTexture.isPlaying)
			renderer.material.mainTexture = webcamTexture;

		//backButton.onClick.AddListener (BackToMain);
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void BackToMain() {

		while (webcamTexture!=null && webcamTexture.isPlaying)
		{
			Debug.Log("is still playing");
			webcamTexture.Stop();
			webcamTexture=null;
			break;
		}

		Debug.Log("camera stopped playing");


		SceneManager.LoadScene ("MainPagesScene");
	}

}
