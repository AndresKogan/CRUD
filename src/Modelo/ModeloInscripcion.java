/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import Datos.InscripcionDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class ModeloInscripcion {
    
    private int codigoInscripcion;
    private String nombreInscripcion;
    private Date fechaInsc;
    private int codigoCarrera;
    private Datos.InscripcionDAO dato = new Datos.InscripcionDAO();

    
    
    public int getCodigoInscripcion() {
        return codigoInscripcion;
    }

    public void setCodigoInscripcion(int codigoInscripcion) {
        this.codigoInscripcion = codigoInscripcion;
    }

    public String getNombreInscripcion() {
        return nombreInscripcion;
    }

    public void setNombreInscripcion(String nombreInscripcion) {
        this.nombreInscripcion = nombreInscripcion;
    }

    public Date getFechaInsc() {
        return fechaInsc;
    }

    public void setFechaInsc(Date fechaInsc) {
        this.fechaInsc = fechaInsc;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public InscripcionDAO getDato() {
        return dato;
    }

    public void setDato(InscripcionDAO dato) {
        this.dato = dato;
    }

    
    public Set<String> traeCodCarrera(){
   
        return dato.traeCodCarreraDAO();
    
    }
    
    public ArrayList<ModeloInscripcion> traeInsc(){
        return dato.traerDatosDAO();
        
    }
    
    public boolean baja(JTable tabla){
    
    return dato.bajaDAO(tabla);
    }
    
    public boolean modifica(ModeloInscripcion modelo){
    
    return dato.modificaDAO(modelo);
    }
    
    
    
    public boolean validaCarga(String a){
    if (a.length()==0){
    return true;
    }
    else{return false;}
    
    }
    public boolean cargaDatos(ModeloInscripcion inscripcion){
    
    return dato.cargaDatosDAO(inscripcion);}
    
}

