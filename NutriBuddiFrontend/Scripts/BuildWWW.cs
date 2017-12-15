using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BuildWWW : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	public WWW BuildRequest(string uri){

		WWWForm form = new WWWForm();


        //Hashtable headers = form.headers;
        Dictionary<string, string> headers = form.headers;
        byte[] rawData = form.data;
        
		headers["Authorization"] = "Basic " + System.Convert.ToBase64String(
            System.Text.Encoding.ASCII.GetBytes("user:default")); //put user and password here(get it from those boxes)

        //this makes it a GET
        WWW www = new WWW(uri, null, headers);

		return www;

	}


}
