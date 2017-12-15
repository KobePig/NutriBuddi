using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

using UnityEngine.TestTools;
using NUnit.Framework;

public class LogInBehaviour : MonoBehaviour {

    //Basic buttons for the Login Screen 
    //They are attached through Unity, 
    //Need to figure out where programmatically that happens 
    public InputField loginError;
    private static string denyAccess = "User email and password combination does not exist";

    public Button signIn;
    public Button register;
    public Button forgotPass;

    public InputField email;
    public InputField password;

    public string userInfoURL= "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/user/login?";

	// Use this for initialization
	void Start ()
    {
        signIn.onClick.AddListener(call: MyClick);
	}
	
	// Update is called once per frame
	void Update ()
    {
       // loginError.text = "";
	}
    
    void MyClick()
    {

        //need to check for empty stuff
        if(email.text == "" || password.text == "")
        {
            loginError.text = "";
            Debug.Log("N empty ");
            loginError.text = ("Email & Password cannot be empty\n");
        }
        else
        {
            Debug.Log("About to GET request");
            StartCoroutine(StartRequest());
        }
        
    }

     IEnumerator StartRequest()
    {
        //www CLASS used for both POST and GET
        //by default its GET
        //Need to get these two for validating user entry

        //Dont need this for login 
        WWWForm form = new WWWForm();
        form.AddField("email",  email.ToString());
        form.AddField("password", password.ToString());

        //Hashtable headers = form.headers;
        Dictionary<string, string> headers = form.headers;
        byte[] rawData = form.data;

        //url ill be using to see if user exist in database
        string url = userInfoURL+"email="+email.text+"&password="+password.text;

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

        if(www.text == denyAccess)
        {
            loginError.text = "Incorrect Email or Password\n";
            loginError.text += "Or user doesn't exist";
            //Tell user either pass or username was incorrect
        }
        else
        {
			UserSession.session.userEmailAddress = email.text;
            loginError.text = "Welcome to the nutribuddi USER:: " + email.text;

            //Going to do the API call to pull all of their goals and eats
            //APICall userAPICall = new APICall();
            //APICall userAPICall = gameObject.AddComponent<APICall>();
            // userAPICall.startRetrieveData();
            // Debug.Log("Here are the goals:" + UserSession.session.sessionUserGoal.calories);
            // Debug.Log("Here are the eats:" + UserSession.session.sessionAllUserEats.Length);

            // while(true){
            //     // if(UserSession.session.callDone == true){ //Holds the load scene until after the APIcall is done
            //     //     UserSession.session.callDone = false;
            //     //     SceneManager.LoadScene("MainLanding");
            //     // }
            // }
            APICall userAPICall = gameObject.AddComponent<APICall>();
            userAPICall.startRetrieveData();
            StartCoroutine(loginCoroutine());
            //Accept user to home screen and pass json object with all of their info
        }
     }
        // IEnumerator loginCoroutine() {

        //     yield return APICall jfinished

            
        //     SceneManager.LoadScene("MainLanding");
        // }

        IEnumerator loginCoroutine() {
            if(UserSession.session.callDone == true){ //Holds the load scene until after the APIcall is done
                UserSession.session.callDone = false;
                SceneManager.LoadScene("MainLanding");
            }
            else{
                yield return new WaitForSeconds((1.0f));
                StartCoroutine(loginCoroutine());
            }
        }
}

