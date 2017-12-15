using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class addGoalBackButton : MonoBehaviour {

	public GameObject addUserPanel;
	public Button backButton;
	// Use this for initialization
	void Start () 
	{
		backButton.onClick.AddListener(call: goBack);
	}
	
	// Update is called once per frame
	void Update () 
	{
		
	}

	void goBack()
	{
		addUserPanel.SetActive(false);
	}
}
