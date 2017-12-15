using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class testScript : MonoBehaviour {

	public string myURL = "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/userGoal/getUserGoals?email=test@test.com";
	// Use this for initialization
	void Start () {
		BuildWWW myRequest = new BuildWWW();
		StartCoroutine(startMyComplicatedTask(myRequest.BuildRequest(myURL)));
	}

	IEnumerator startMyComplicatedTask(WWW builtRequest){
		yield return builtRequest;

		//this is where you do all the complicated stuff like normal(if you actually need to do anything after the call(ex: check for 404 error))
		Debug.Log(builtRequest.text);
	}
}
