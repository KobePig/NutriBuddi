using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class PencilButton : MonoBehaviour {

	public GameObject addUserPanel;
	public Button addUserBtn;
	// Use this for initialization
	void Start () 
	{
		addUserBtn.onClick.AddListener(call: makeAddGoal);
	}
	
	// Update is called once per frame
	void Update () 
	{
		
	}

	void makeAddGoal()
	{
		addUserPanel.SetActive(true);
	}
}
