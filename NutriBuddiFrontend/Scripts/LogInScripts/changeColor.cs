using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class changeColor : MonoBehaviour {

	// Use this for initialization
	void Start ()
    {
        
    }
	
	// Update is called once per frame
	void Update ()
    {
		
	}

    void ChangeWhite(InputField inputField)
    {
        InputField input;
        ColorBlock cb;

        input = inputField.GetComponent<InputField>();
        cb = input.colors;
        cb.normalColor = Color.white;
        input.colors = cb;

        Debug.Log("Value has changed");
    }
}
