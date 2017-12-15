using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class CameraButton : MonoBehaviour {

	// Use this for initialization
	public Button camButton;
	void Start () {
		// camButton = GetComponent<Button>();
		camButton.onClick.AddListener(activateCamera);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void activateCamera () {
		Debug.Log("hello");
		SceneManager.LoadScene("CameraScene");
	}
}
