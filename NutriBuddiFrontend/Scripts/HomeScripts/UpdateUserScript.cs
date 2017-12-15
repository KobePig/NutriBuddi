using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UpdateUserScript : MonoBehaviour {

	public Text email;
	public InputField password;
	public InputField password2;
	public InputField height;
	public InputField weight;
	public InputField age;
	public InputField gender;
	public Button updateButton;
	public Text error;

	public string userInfoURL= "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/user/updateUser?";

	// Use this for initialization
	void Start () {

		email.text = "Email: " + UserSession.session.userEmailAddress;
		updateButton.onClick.AddListener (call: OnClick);
	}
		
	
	// Update is called once per frame
	void Update () {
		
	}

	void OnClick ()
	{
		//will be used to either send post or make user re enter  data
		bool canProceed;

		Debug.Log("About to GET request");

		//Need to make user all fields are not empty
		//Data validation before POST request
		//Validate that all fields are not empty
		//Validate that passwords match 

		canProceed = Validate();

		if(canProceed)
		{
			//CALL to POST
			StartCoroutine(StartRequest());
		}
		else
		{        
			error.text = "Invalid Input";
			Debug.Log("Did not let it POST");
		}


	}



	//email=<email>&password=<password>&password2=<password2>&height=<height>&weight=<weight>&age=<age>&gender=<gender>

	IEnumerator StartRequest()
	{
		//www CLASS used for both POST and GET
		//by default its GET
		//Need to get these two for validating user entry

		//Dont need this for login 
		WWWForm form = new WWWForm();

		//Hashtable headers = form.headers;
		Dictionary<string, string> headers = form.headers;
		byte[] rawData = form.data;

		//url ill be using to see if user exist in database
		string url = userInfoURL+"email="+"frank@frank.com"+"&password="+password.text+"&password2="+password2.text+"&height="+height.text+"&weight="+weight.text+"&age="+age.text+"&gender="+gender.text;

		Debug.Log (url);
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
			error.text = www.text;
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

	//method to validate data 
	public bool Validate()
	{
		bool check = true;
		//set the error blank first
		error.text = "";

		check = IsEmpty(password);
		check = IsEmpty(password2);
		check = IsEmpty(height);
		check = IsEmpty(weight);
		check = IsEmpty(age);
		check = IsEmpty(gender);

		if(!check)
		{
			error.text += "NEED TO FILL OUT ALL FIELDS\n";
		}

		if (password.text != password2.text)
		{
			//Need to show user passwords do not match
			error.text += "PASSWORDS DO NOT MATCH";

		}

		return check;
	}

	bool IsEmpty(InputField input)
	{
		if (input.text == "")
		{
			ChangeColors(input, Color.red);
			return false;
		}
		else
		{
			return true;
		}
	}

	void ChangeColors(InputField inputField, Color color)
	{
		//used to get the colors and change to red
		InputField input;
		ColorBlock cb;

		input = inputField.GetComponent<InputField>();
		cb = input.colors;
		cb.normalColor = color;
		input.colors = cb;
	}

}
