using System.Collections;
using System.Collections.Generic;
using UnityEngine.Networking;
using UnityEngine;
using UnityEngine.UI;

public class RegistrationBehavior : MonoBehaviour {

    public Button backButton;
    public Button registerButton;
    public InputField error;
    public GameObject registerPanel;
    private static string addedUser = "User added";

    //Should i makes user into a class?
    //content type: Name
    public InputField firstName;
    public InputField lastName;
    public InputField userName;

    //content type email
    public InputField email;

    //content type: password
    public InputField password;
    public InputField checkPass;

    //content type: integer 
    public InputField age;
    public InputField gender;
    public InputField height;

    //content type: decimal 
    public InputField weight;

    //URL to make POST of new users 
    public string userInfoURL = "http://nutribuddiusermanagementapi-env.evsatgc2zc.us-east-1.elasticbeanstalk.com/user/addNewUser?";

    // Use this for initialization
    void Start ()
    {
        registerButton.onClick.AddListener(call: OnClick);

        firstName.onValueChanged.AddListener(delegate { ChangeColors(firstName, Color.white); });
        lastName.onValueChanged.AddListener(delegate { ChangeColors(lastName, Color.white); });
        userName.onValueChanged.AddListener(delegate { ChangeColors(userName, Color.white); });
        email.onValueChanged.AddListener(delegate { ChangeColors(email, Color.white); });
        password.onValueChanged.AddListener(delegate { ChangeColors(password, Color.white); });
        checkPass.onValueChanged.AddListener(delegate { ChangeColors(checkPass, Color.white); });
        age.onValueChanged.AddListener(delegate { ChangeColors(age, Color.white); });
        gender.onValueChanged.AddListener(delegate { ChangeColors(gender, Color.white); });
        height.onValueChanged.AddListener(delegate { ChangeColors(height, Color.white); });
        weight.onValueChanged.AddListener(delegate { ChangeColors(weight, Color.white); });

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
            StartCoroutine(StartUserPostRequest());
        }
        else
        {        

            Debug.Log("Did not let it POST");
        }
        

    }

	// Update is called once per frame
	void Update ()
    {
	}

    //For POST
    IEnumerator StartUserPostRequest()
    {
        //Need to create a form object to send NEW USER information 
        Debug.Log("ALL fields are good");

        //First need to convert all the inputs to strings
        //Some already in string format i believe 
        //but going to store in new variable 
        string Firstname = firstName.text;
        string Lastname = lastName.text;
        string Username = userName.text;
        string Email = email.text;
        string PassWord = password.text;
        string PassWord2 = checkPass.text;
        string Age = age.text;
        string Gender = gender.text;
        string Height = height.text;
        string Weight = weight.text;

        //List<IMultipartFormSection> userForm = new List<IMultipartFormSection>();

        
        string userInfo = "email="+Email+"&password="+PassWord+"&password2="+PassWord2+"&userName="+Username+
            "&first="+Firstname+"&last="+Lastname+"&height="+Height+"&weight="+Weight+"&age="+Age+"&gender="+Gender;

        userInfo = userInfoURL + userInfo;
        Debug.Log(userInfo);
        

        WWWForm userForm = new WWWForm();

        userForm.AddField("email", Email);
        userForm.AddField("password", PassWord);
        userForm.AddField("password2", PassWord2);
        userForm.AddField("userName", Username);
        userForm.AddField("first", Firstname);
        userForm.AddField("last", Lastname);
        userForm.AddField("height", Height);
        userForm.AddField("weight", Weight);
        userForm.AddField("age", Age);
        userForm.AddField("gender", Gender);

 
        Dictionary<string, string> headers = userForm.headers;


        //permissions
        headers["Authorization"] = "Basic " + System.Convert.ToBase64String(
           System.Text.Encoding.ASCII.GetBytes("user:default"));

        //For the POST
        WWW www = new WWW(userInfo, null, headers);

        yield return www;

        if (www.isDone)
        {
            if (www.text == addedUser)
            {
                Debug.Log("Aye we about to leave to home screen");
                registerPanel.SetActive(false);
            }
            else
            {
                Debug.Log("Done www stuff");
                Debug.Log(www.text);
                Debug.Log(www.text.Equals("User added"));
            }
           
        }
       
    
      
    }

    //method to validate data 
    public bool Validate()
    {
        bool check = true;
        //set the error blank first
        error.text = "";

        check = IsEmpty(firstName);
        check = IsEmpty(lastName);
        check = IsEmpty(userName);
        check = IsEmpty(email);
        check = IsEmpty(password);
        check = IsEmpty(checkPass);
        check = IsEmpty(age);
        check = IsEmpty(gender);
        check = IsEmpty(weight);
        check = IsEmpty(height);
   
        if(!check)
        {
            error.text += "NEED TO FILL OUT ALL FIELDS\n";
        }

        if (password.text != checkPass.text)
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
