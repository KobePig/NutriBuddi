using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class BackButton : MonoBehaviour {

    public Button backButton;
    public GameObject registerPanel;

	// Use this for initialization
	void Start ()
    {

        backButton.onClick.AddListener(call: OnClick);
	}
	
	// Update is called once per frame
	void Update ()
    {
		
	}

    void OnClick()
    {
        registerPanel.SetActive(false);
    }
}
