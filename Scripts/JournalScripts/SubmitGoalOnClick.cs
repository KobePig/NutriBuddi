using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class SubmitGoalOnClick : MonoBehaviour {

	public Button submitGoalButton;

    public InputField caloriesInput;
    public InputField carbsInput;
    public InputField totalFatInput;
    public InputField sodiumInput;
    public InputField proteinInput;

    public Dropdown weightDropdown;

    private int weightGoal = 0;

	public string userInfoURL= "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/userGoal/updateUserGoal?";

	// Use this for initialization
	void Start () {
		submitGoalButton.onClick.AddListener(startAddGoal);
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	void startAddGoal(){
		StartCoroutine(StartRequest());
	}

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
        // updateUserGoal?email=test@test.com&protein=25&carbs=234&calories=150&sodium=800&totalFat=12&weightGoal=2
        weightGoal = weightDropdown.value;
        //string url = userInfoURL+"email="+UserSession.session.userEmailAddress+"&protein="+proteinInput.text+"&carbs="+carbsInput.text+"&calories="+caloriesInput.text+"&sodium="+sodiumInput.text+"&totalFat="+totalFatInput.text+"&weightGoal="+weightGoal;
        //this is the hardcoded test
        string url = userInfoURL+"email="+"test@test.com"+"&protein="+proteinInput.text+"&carbs="+carbsInput.text+"&calories="+caloriesInput.text+"&sodium="+sodiumInput.text+"&totalFat="+totalFatInput.text+"&weightGoal="+weightGoal;

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

        // if(www.text == denyAccess)
        // {
        //     loginError.text = "Incorrect Email or Password\n";
        //     loginError.text += "Or user doesn't exist";
        //     //Tell user either pass or username was incorrect
        // }
        // else
        // {
        //     UserSession.session.userEmailAddress = email.text;
        //     loginError.text = "Welcome to the nutribuddi USER:: " + email.text;
        //     //UserSession.session.userEmailAddress = email.text;
        //     SceneManager.LoadScene("MainPagesScene");
        //     //Accept user to home screen and pass json object with all of their info
        // }


     }
}
