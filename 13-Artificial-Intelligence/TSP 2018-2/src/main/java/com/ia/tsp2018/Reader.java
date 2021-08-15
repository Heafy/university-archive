package com.ia.tsp2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class to represent File Reader for the project.
 */
public class Reader {
    
    String csvFile;
    String line;
    String csvSplit;
    BufferedReader br;
    /** Length of the city arrays. */
    static final int LENGTH = TSP.NUMBERCITIES;
    
    /**
     * Constructor of the class for reading files.
     */
    public Reader() {
        this.line = "";
        this.csvSplit = ",";
    }
    
    /**
     * Read indexCiudades.csv and store it in a HashMap.
     * @return HashMap with the city index and her names
     */
    public HashMap<Integer, String> readIndexCSV() {
        HashMap<Integer, String> map = new HashMap<>();
        this.csvFile = "indexCities.csv";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while((line = br.readLine()) != null) {
                String[] city = line.split(csvSplit);
                map.put(Integer.parseInt(city[0]), city[1]);
            }
        } catch(IOException ioe) {
            System.out.println("No se pudo leer el archivo.");
        }
        return map;
    }
    
    /**
     * Get travel costs storing it in a bidimensional array.
     * @return Bidimensional array with the travel cost.
     */
    public int [][] getCosts() {
        this.csvFile = "citiesMex.csv";
        // [i][] Array position
        // [][i] Element position
        int [][] array = new int[LENGTH+1][LENGTH+1];
        try {
            br = new BufferedReader(new FileReader(csvFile));
            int counter = 0;
            while((line = br.readLine()) != null) {
                String[] distances = line.split(csvSplit);
                for(int i = 0; i < distances.length; i++) {
                    array[counter][i] = Integer.parseInt(distances[i]);
                }
                counter++;
            }
        } catch(IOException ioe) {
            System.out.println("No se pudo leer el archivo.");
        } 
        return array;
    }
    
}
