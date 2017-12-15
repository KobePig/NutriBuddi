using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class CancelButton : MonoBehaviour {

	public Button cancelButton;

	// Use this for initialization
	void Start () {
		cancelButton.onClick.AddListener (RestartCamera);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void RestartCamera() {
		SceneManager.LoadScene ("CameraScene");
	}
}
