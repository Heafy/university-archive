using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour {

	// Player del juego
	public GameObject player;

	// Distancia entre el player y la camara
	private Vector3 offset; 

	// Use this for initialization
	void Start () {
		// Coloca la camara en posicion
		offset = transform.position - player.transform.position;
	}
	
	// Update is called once per frame
	void LateUpdate () {
		// Actualiza la posicion de la camara
		transform.position = player.transform.position + offset;
	}
}
