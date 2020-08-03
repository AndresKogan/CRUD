/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.CarreraDAO;

import java.util.ArrayList;

import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class ModeloCarrera {
   private int codigoCarrera;
   private String nombreCarrera;
   private int duracionCarrera;
   private CarreraDAO dato = new CarreraDAO();

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getDuracionCarrera() {
        return duracionCarrera;
    }

    public void setDuracionCarrera(int duracionCarrera) {
        this.duracionCarrera = duracionCarrera;
    }

    public CarreraDAO getDato() {
        return dato;
    }

    public void setDato(CarreraDAO dato) {
        this.dato = dato;
    }
    
    
    public boolean validaCarga(String a){
    
        if (a.length()==0){
            return true;
        }
        else{return false;}
    
    
    }
    
    public boolean nombreRepetido (ModeloCarrera carrera){
        return dato.nombreRepetidoDAO(carrera);
        
    }
    
    public ArrayList <ModeloCarrera> traeCarreras(){
        return dato.traerDatosDAO();
    }
    
    public boolean cargaDatos (ModeloCarrera modeloCarrera){
    
        return dato.cargaDatosDAO(modeloCarrera);
    }
    public boolean baja (JTable tablaCarrera){
    return dato.bajaDAO(tablaCarrera);
    }
    
    public boolean modifica(ModeloCarrera carrera){
    return dato.modificaDAO(carrera);}
    
}

