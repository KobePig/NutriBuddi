using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;
using UnityEngine.UI;

public class MessageScript : MonoBehaviour {

    DateTime currentTime;
    Text message;

	// Use this for initialization
    void Start () {
        message = GetComponent<Text>();

        string time = GetCurrentTime();
        if(String.Compare(time, "AM") == 0){
            message.text = "Good morning!";
		}else if(String.Compare(time, "AFTERNOON") == 0){
            message.text = "Good afternoon!";
		}else{
			message.text = "Good evening!";
		}
    }
	
	// Update is called once per frame
	void Update () {
		
	}

    //Determines whether its the morning or evening based on system time
    string GetCurrentTime() {
		currentTime = DateTime.Now;
		if (currentTime.Hour <= 12) {
			return "AM";
		}else if(currentTime.Hour <= 17){
			return "AFTERNOON";
		}else{
            return "PM";
        }
    }
}
