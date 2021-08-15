using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Sensors : MonoBehaviour {

    // Variables auxiliares
    public bool isTouchingTrash = false;
    public bool isTouchingWall = false;
    public bool isTouchingStation = false;
    public bool inFrontTrash = false;
    public bool inFrontWall = false;
    public bool inFrontStation = false;

    private GameObject trash; // Referencia a basura que se esta tocando
    private VisionController radar; // Componente externo
    public float rayDistance; // Longitud de la linea que mira al frente

	// Metodo que se ejecuta al inicio del script.
	void Start () {
		radar = GameObject.Find("Vision").gameObject.GetComponent<VisionController>();
	}
	
    // Metodo que se llama en la ejecución de cada frame.
	// En cada frame lanza un rayo para verificar si existe un objeto frente al agente
    void FixedUpdate(){
        RaycastHit raycastHit; // Almacena la informacion de contacto con el rayo
        if(Physics.Raycast(transform.position, transform.forward, out raycastHit, rayDistance)){
            if(raycastHit.collider.gameObject.CompareTag("Wall"))
                inFrontWall = true;
            if(raycastHit.collider.gameObject.CompareTag("Trash"))
                inFrontTrash = true;
            if(raycastHit.collider.gameObject.CompareTag("Station"))
                inFrontStation = true;
        }else{
            inFrontTrash = false;
            inFrontWall = false;
            inFrontStation = false;
        }
    }

    // En cada frame dibuja una linea de color para representar lo que el agente esta mirando al frente
    void Update(){
        Debug.DrawLine(transform.position, transform.position + transform.forward * rayDistance, Color.green);
    }

    // Revisa cuando sucede una colision con algun pared
    void OnCollissionEnter(Collision other){
        if(other.gameObject.CompareTag("Wall"))
            isTouchingWall = true;
    }

    // Revisa cuando e mantiene una colision con alguna pared
    void OnCollisionStay(Collision other){
        if(other.gameObject.CompareTag("Wall"))
            isTouchingWall = true;
    }

    // Revisa cuando termina la colision con alguna pared
    void OnCollisionExit(Collision other){
        if(other.gameObject.CompareTag("Wall"))
            isTouchingWall = false;
    }

    void OnTriggerEnter(Collider other){
        if(other.gameObject.CompareTag("Trash")){
            isTouchingTrash = true;
            trash = other.gameObject;
        }
        if(other.gameObject.CompareTag("Station"))
            isTouchingStation = true;
    }

    void OnTriggerStay(Collider other){
        if (other.gameObject.CompareTag ("Trash")) {
            isTouchingTrash = true;
            trash = other.gameObject;
        }
        if (other.gameObject.CompareTag ("Station"))
            isTouchingStation = true;
    }

    void OnTriggerExit(Collider other){
        if (other.gameObject.CompareTag ("Trash")) {
            isTouchingTrash = false;
            trash = null;
        }
        if (other.gameObject.CompareTag ("Station"))
            isTouchingStation = false;
    }

    // Metodo para saber si esta tocando basura
    public bool TouchingTrash(){
        return isTouchingTrash;
    }

    // Metodo para saber si esta tocando pared
    public bool TouchingWall(){
        return isTouchingWall;
    }

    // Metodo para saber si esta tocando una estacion
    public bool TouchingStation(){
        return isTouchingStation;
    }

    // Metodo pra saber si esta en frente de una basura
    public bool FrontTrash(){
        return inFrontTrash;
    }

    // Metodo para saber si esta en frente de una pared
    public bool FrontWall(){
        return inFrontWall;
    }

    // Metodo para saber si esta en frente de una estacion
    public bool FrontStation(){
        return inFrontStation;
    }

    // Metodo para saber si esta cerca de una basura
    public bool NearTrash(){
        return radar.NearTrash();
    }

    // Metodo para saber si esta cerca de una pared
    public bool NearWall(){
        return radar.NearWall();
    }

    // Metodo para saber si esta cerca de una estacion
    public bool NearStation(){
        return radar.NearStation();
    }

    // Metodo para modificar la bandera isThouchingTrash
    public void SetTouchTrash(bool value){
        isTouchingTrash = value;
    }

    // Metodo para modificar la bandera inFrontTrash
    public void SetFrontTrash(bool value){
        inFrontTrash = value;
    }

    // Metodo para modificar la bandera NearTrash
    public void SetNearTrash(bool value){
        radar.setNearTrash(value);
    }

    // Metodo para obtener la basura detectada
    public GameObject GetTrash(){
        return trash;
    }
}
