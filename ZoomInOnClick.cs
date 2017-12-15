using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ZoomInOnClick : MonoBehaviour {

	//Could be done better, but works 
	//Dont need a bunch of this Shit but whatevs
	public Canvas mainCanvas;
	//mainCanvas will be setActive() == false onClick of zoomButtom
	public GameObject homePanel;
	public GameObject journalPanel;
	public GameObject cameraPanel;
	public GameObject gearPanel;

	public Camera mainCamera;
	//Main camera in Main Screen is at location (x: 11, y: 13, z: 24.7)
	//Will zoom to (x: 4.85, y: 7.2, z: 13.01)
	//Rotation of z axis 90 degrees for landscape mode
	public Button zoomInButtom;
	//zoomInButton will be the Button that will hear for the onClick()

	private float currentFOV;
	private float initialFOV;
	//These are the degrees of where i want the camera finishing at 
	private float rotateZAxis = 90.0f;
	private float zoomInXAxis = 4.85f;
	private float zoomInYAxis = 7.2f;
	private float zoomInZAxis = 13.01f;

	private float dummy2 = 90.0f;
	//Hard coded for now 
	private float initialX = 11f;
	private float initialY = 13f;
	private float initialZ = 24.7f;
	//speed of camera change 
	private float dummy = 24.7f;
	public float fovSpeed = 1.0f;

	private Boolean enableZoom =  false;
	private Boolean notZoomedIN = true;
	private float initialRotateZ = .222f;
	//public float initialFOV;
	//public float zoomInFOV;
	//public float smooth;
	// Use this for initialization
	void Start () 
	{
		//mainCamera.fieldOfView = initialFOV;
		initialFOV = mainCamera.fieldOfView; 
		zoomInButtom.onClick.AddListener(call: HideCanvas);

	}
	
	// Update is called once per frame
	// void Update () 
	// {
	// 	//Where the smooth transactions happend per frame
	// 	currentFOV = mainCamera.fieldOfView;
	// 	// Debug.Log("enableZoom " + enableZoom.ToString());
	// 	if(enableZoom)
	// 	{
	// 		ZoomIN();
	// 	}
	// 	else if (notZoomedIN)
	// 	{
	// 		zoomOut();
	// 	}

	// }

	//If want to go back to where we started
	void ResetFOV()
	{
		mainCamera.fieldOfView = initialFOV;
	}

	void HideCanvas()
	{
		if(notZoomedIN)
		{
			homePanel.gameObject.SetActive(false);	
			journalPanel.gameObject.SetActive(false);	
			cameraPanel.gameObject.SetActive(false);	
			gearPanel.gameObject.SetActive(false);	

			enableZoom = true;
			//Debug.Log("enableZoom " + enableZoom.ToString());
			notZoomedIN = false;
		}
		else if(enableZoom)
		{
			homePanel.gameObject.SetActive(true);	
			journalPanel.gameObject.SetActive(true);	
			cameraPanel.gameObject.SetActive(true);	
			gearPanel.gameObject.SetActive(true);

			enableZoom = false;
			notZoomedIN = true;	
		}
		//ZoomIN();
	}
	//Should be called in Every frame until reached destination 
	void ZoomIN()
	{
		if (initialZ >= zoomInZAxis)
		{
			mainCamera.transform.Translate(0f,0f,.12f);
			rotateZ();
			initialZ-=.12f;
			//Debug.Log("doing trandform");
		}
		else if (initialZ == zoomInZAxis)
		{
			enableZoom = false;
		}
	}

	void zoomOut()
	{
		if (initialZ <= dummy)
		{
			mainCamera.transform.Translate(0f,0f,-.12f);
			ReRotateZ();
			initialZ+=.12f;
			//Debug.Log("doing trandform");
		}
		else if (initialZ == zoomInZAxis)
		{
			enableZoom = false;
		}
	}
	void rotateZ()
	{	
		if(rotateZAxis >= initialRotateZ)
		{
			mainCamera.transform.Rotate(0,0,.8f);
			rotateZAxis-=.8f;
		}
	}

	void ReRotateZ()
	{	
		if(rotateZAxis <= dummy2)
		{
			mainCamera.transform.Rotate(0,0, -.8f);
			rotateZAxis+=.8f;
		}
	}

}
