using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PostEatScript : MonoBehaviour {

	public Button postEatButton;
	public InputField foodName;
	public InputField numOfServing;

	public string userInfoURL= "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/eats/addNewEats?";

	//email=<email>&numServings=<numServings>&foodName=<foodName>
	// Use this for initialization
	void Start () {
		postEatButton.onClick.AddListener (call: postFood);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void postFood() {

		StartCoroutine(StartRequest());
	}




	IEnumerator StartRequest()
	{
		//www CLASS used for both POST and GET
		//by default its GET
		//Need to get these two for validating user entry

		//Dont need this for login 
		WWWForm form = new WWWForm();
//		form.AddField("email",  email.ToString());
//		form.AddField("password", password.ToString());

		//Hashtable headers = form.headers;
		Dictionary<string, string> headers = form.headers;
		byte[] rawData = form.data;

		//url ill be using to see if user exist in database
		string url = userInfoURL+"email="+UserSession.session.userEmailAddress+"&numServings="+numOfServing.text+"&foodName="+foodName.text;

		// Add a custom header to the request.
		// In this case a basic authentication to access a password protected resource.
		headers["Authorization"] = "Basic " + System.Convert.ToBase64String(
			System.Text.Encoding.ASCII.GetBytes("user:default")); //put user and password here(get it from those boxes)

		//this makes it a GET
		WWW www = new WWW(url, null, headers);

		yield return www;
		if (www.isDone)
		{
			Debug.Log(www.text);
		}
		//.. process results from WWW request here... 

//		if(www.text == denyAccess)
//		{
//			loginError.text = "Incorrect Email or Password\n";
//			loginError.text += "Or user doesn't exist";
//			//Tell user either pass or username was incorrect
//		}
//		else
//		{
//			UserSession.session.userEmailAddress = email.text;
//			loginError.text = "Welcome to the nutribuddi USER:: " + email.text;
//			//UserSession.session.userEmailAddress = email.text;
//			SceneManager.LoadScene("MainPagesScene");
//			//Accept user to home screen and pass json object with all of their info
//		}


	}
}
