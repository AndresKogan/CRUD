/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.MateriaDAO;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class ModeloMateria  {
    
    private int codigoMateria;
    private String nombreMateria;
    private Long profMateriaDNI;
    private MateriaDAO dato = new MateriaDAO();

    public int getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Long getProfMateriaDNI() {
        return profMateriaDNI;
    }

    public void setProfMateriaDNI(Long profMateriaDNI) {
        this.profMateriaDNI = profMateriaDNI;
    }

    public MateriaDAO getDato() {
        return dato;
    }

    public void setDato(MateriaDAO dato) {
        this.dato = dato;
    }
    
   public boolean validaCarga(String a){
    
        if (a.length()==0){
            return true;
        }
        else{return false;}
    
    
    }
    
    public boolean nombreRepetido (ModeloMateria materia){
        return dato.nombreRepetidoDAO(materia);
        
    }
    
    public ArrayList <ModeloMateria> traeMaterias(){
        return dato.traerDatosDAO();
    }
    
    public boolean cargaDatos (ModeloMateria modeloMateria){
    
        return dato.cargaDatosDAO(modeloMateria);
    }
    public boolean baja (JTable tablaMateria){
    return dato.bajaDAO(tablaMateria);
    }
    
    public boolean modifica(ModeloMateria materia){
    return dato.modificaDAO(materia);}
    


 public Set<String> traeDNIProfesor(){
   
        return dato.traeDNIProfesorDAO();
    
    }}

    
