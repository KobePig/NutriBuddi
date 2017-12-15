using System.Collections;
using System.Collections.Generic;
using UnityEngine;


// we will keep user information here
public class UserSession : MonoBehaviour {

	public static UserSession session; //reference var

	// properties that we need to be passed
	public string userEmailAddress = "frank@frank.com"; // testing
	public long timeStamp;
	public string fileName;
	public string currentClassifiedFood;

	public bool callDone;

	public APICall.eatTransaction[] sessionAllUserEats;

	public APICall.userGoal sessionUserGoal;


	// if there is no current usersession, create it
	// never destroy this script
	void Awake() {
		if (session == null) {
			DontDestroyOnLoad (gameObject);
			session = this;
		} else if (session != this) { 
			// if already exists, and this one is not it, destroy
			//there will be only one of this
			Destroy (gameObject);
		}

	}
	
	// Update is called once per frame
	void Update () {
		
	}

//	void OnGUI() {
//		GUI.Label (new Rect (10, 10, 100, 30), "Email: " + userEmailAddress);
//		GUI.Label (new Rect (10, 40, 150, 30), "timeStamp: " + timeStamp);
//
//	}
		
}
