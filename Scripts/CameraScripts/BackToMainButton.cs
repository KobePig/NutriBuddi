using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;


public class BackToMainButton : MonoBehaviour 
{


	public Button backButton;
	//public WebCamTexture webcamTexture;

	// Use this for initialization
	void Start () 
	{

		backButton.onClick.AddListener (call: BackToMain);
	}
	
	// Update is called once per frame
	void Update () 
	{
		
	}

	void BackToMain() 
	{
		SceneManager.LoadScene ("MainLanding");
	}
}
