using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Behavior : MonoBehaviour {

    
    //private Sensors sensor;
    private Actuators actuator;
    

	// Metodo que se ejecuta al inicio del script.
	void Start () {
		//sensor = GetComponent<Sensors>();
        actuator = GetComponent<Actuators>();
	}
	
	// Metodo que se llama en la ejecución de cada frame.
    // En cada frame decidimos el comportamiento del agente
    // Control manual del agente
	void Update () {
        
        // Mueve hacia adelante
		if(Input.GetKeyDown(KeyCode.UpArrow) || Input.GetAxis("Vertical") == 1)
            actuator.MoveForward();
        // Mueve hacia atras
        if(Input.GetKeyDown(KeyCode.DownArrow) || Input.GetAxis("Vertical") == -1)
            actuator.MoveBackward();
        // Rota hacia la izquierda
        if(Input.GetKeyDown(KeyCode.LeftArrow) || Input.GetAxis("Horizontal") == -1)
            //actuator.MoveLeft();
            actuator.RotateLeft();
        // Rota hacia la derecha
        if(Input.GetKeyDown(KeyCode.RightArrow) || Input.GetAxis("Horizontal") == 1)
            //actuator.MoveRight();
            actuator.RotateRight();

        // Registros para debug

        
        /*
        if(sensor.TouchingTrash()){
            Debug.Log("Touching trash");
            actuator.Aspire(sensor.GetTrash());
        }

        if(sensor.TouchingWall())
            Debug.Log("Touching wall");

        if(sensor.FrontWall())
            Debug.Log("Front wall");

        if(sensor.FrontTrash())
            Debug.Log("Front trash");

        if(sensor.TouchingStation())
            Debug.Log("Touching station");

        if(sensor.NearWall())
            Debug.Log("Near wall");

        if(sensor.NearTrash())
            Debug.Log("Near trash");
        */

        

       
	}
}
