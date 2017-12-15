using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ChangeFoodTextScript : MonoBehaviour {

	public Text foodName;

	// Use this for initialization
	void Start () {
		foodName.text = UserSession.session.currentClassifiedFood;
	
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
