using System.Collections;
using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class HideLoginScreen : MonoBehaviour {


    public Button registerBtn;
    public GameObject registerPanel;

	// Use this for initialization
	void Start ()
    {
         registerPanel.SetActive(false);
         registerBtn.onClick.AddListener(call: OnClick);
	}
	
	// Update is called once per frame
	void Update ()
    {
		
	}

    void OnClick ()
    {
         registerPanel.SetActive(true);
         Debug.Log("You just clicked the register in button");
    }

}
