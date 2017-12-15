using System.Collections;
using System.Collections.Generic;
using System.Linq;
using UnityEngine;

public class EpochConverter : MonoBehaviour {

	// Use this for initialization
	public GameObject btn;
	
	//this is where the time gets store, [0] is where the date is at where the comparison happens
	string[] epochTimeString;
	string[] currentTimeString;
	void Start () 
	{
	}
	
	// Update is called once per frame
	void Update () 
	{
		
	}

	public bool isToday(long timestamp){
		//this is where you feed the epoch time from json
		//then convert to string to parse later
		System.DateTime time = EpochTimeConverter(timestamp);
		string stringTime = time.ToString();
		epochTimeString = stringTime.Split(' ');


		//Gets the date for today, which youll use later for comparison 
		string time2 = System.DateTime.Now.ToString("");
		currentTimeString =  time2.Split(' ');

		//Call the compares function 
		//This does the comparison only for the date, 
		//no time b/c it would always be false due to millisecond differences
		bool isToday = IsEqualDate(epochTimeString[0], currentTimeString[0]);
		return isToday;
	}
	System.DateTime EpochTimeConverter(long unixTimeStamp)
	{
		//Time converter is for the value input in milliseconds 

		//Test example
		//1510547547720

		//System.DateTimeKind.Utc == Coordinated Universal Time
		//year, month, day, hour, minute, second, milli, calnedar, kind
		System.DateTime dateTime = new System.DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Utc);
		dateTime = dateTime.AddMilliseconds(unixTimeStamp);
		return dateTime;
	}

	bool IsEqualDate(string timestamp, string current)
	{
		if(timestamp == current)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

}
