using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class AutomaticBehavior : MonoBehaviour {

    private Sensors sensor;
    private Actuators actuator;
    public float battery;
    public float maxBattery;

    // Metodo que se ejecuta al inicio del script.
	void Start () {
		sensor = GetComponent<Sensors>();
        actuator = GetComponent<Actuators>();
	}
	
    // Metodo que se llama en la ejecución de cada frame.
	void Update () {

        // Si la bateria se acaba. No puede realizar ninguna accion
        if(battery <= 0)
            return;
        else
            // Reduce bateria
            battery -= Time.deltaTime;
        // Recargar bateria al contacto con la estacion
        if(sensor.TouchingStation() && battery < maxBattery)
            battery += Time.deltaTime *2;

        GoCharge();
        TrashSearch();
        Patrol();  
	}

    // Metodo para que cuando este cerca de la estacion de carga cargue su bateria
    void GoCharge(){
        // Si la bateria esta cargada y esta tocando la estacion 
        // Se aleja de la estacion de carga para seguir buscando
        // El movimiento es brusco para que los sensores no sigan detectanto que esta cerca
        // De la base y lo manden de vuelta al objeto
        if(battery >= maxBattery && sensor.TouchingStation()){
            actuator.MoveAwayStation();
        }
    
        // Si el sensor detecta que esta cerca de la estacion y no esta
        // enfrente del objeto empieza a rotar para encontarla
        if(sensor.NearStation() && !sensor.FrontStation()){
            actuator.RotateLeft();
        }
        // Una vez que tiene enfrente la estacion se mueve hacia en frente
        if(sensor.FrontStation()){
            actuator.MoveForward();
        }
        // Esta cargando su bateria
        if(sensor.TouchingStation() && sensor.NearStation()){
            // No hace nada
        }
    }


    // Metodo que se ejecuta para buscar una basura y aspirarla
    void TrashSearch(){
        //  Si el sensor detecta una basura cerca y no esta enfrente de el
        // Empieza a rotar para encontrarla
        // Puede encontrar su posicion relativa para girar en la direccion correcta
        if(sensor.NearTrash() && !sensor.FrontTrash()){
            actuator.RotateLeft();
        }
        // Una vez que la tiene enfrente avanza hacia la basura
        // TODO VER CASO DONDE TENGA PARED EN MEDIO
        if(sensor.FrontTrash()){
            actuator.MoveForward();
        }
        // Si el sensor esta tocando basura la aspira
        if(sensor.TouchingTrash()){
            actuator.Aspire(sensor.GetTrash());
        }
    }

    // Metodo para crear una busqueda aleatoria en el entorno.
    void Patrol(){
        /// Si el sensor no esta tocando una pared avanza hacia adelante
        // Se detiene cuando encuentra una basura cerca
        if (!sensor.TouchingWall() && !sensor.NearTrash() && !sensor.NearStation()){
            actuator.MoveForward();
        }
        // Si toca una pared da una vuelta aleatoria para seguir buscando
        if(sensor.TouchingWall()){
            actuator.MoveBackward();
            actuator.RotateRandom();
        }
    }
}
