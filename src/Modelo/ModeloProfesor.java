/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Datos.ProfesorDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class ModeloProfesor {
    private long dni;
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private String domicilio;
    private String telefono;
  
    private ProfesorDAO dato = new ProfesorDAO();

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ProfesorDAO getDato() {
        return dato;
    }

    public void setDato(ProfesorDAO dato) {
        this.dato = dato;
    }
    
     public ArrayList<ModeloProfesor>traeProfesores(){
        return dato.traerDatosDAO();
    }
    
    public boolean cargaDatos (ModeloProfesor modeloProfesor){
        
        return dato.cargaDatosDAO(modeloProfesor);
    }
    public boolean baja(JTable tablaProfesor){
    return dato.bajaDAO(tablaProfesor);
        
    }
    public boolean validaCarga (String a){
    if (a.length()==0){
        return true;
    }
    else {return false;
    
    }}
    
    public boolean validaDNI (String datos){
        try {dni = Long.parseLong(datos);
        return false;
        }
        catch (NumberFormatException nfe){
                return true;
    }
    }
    public boolean dniRepetido(ModeloProfesor profesor){
    return dato.dniRepetidoDAO(profesor);
      
    
    }
    
    public boolean modifica(ModeloProfesor profesor){
        return dato.modificaDAO(profesor);
    }
    
    
    
}
