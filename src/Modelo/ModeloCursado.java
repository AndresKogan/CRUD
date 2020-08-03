/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.CursadoDAO;
import Datos.InscripcionDAO;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class ModeloCursado {

    private int nota;
    private long dniAlumno;
    private int codigoMateria;
    private Datos.CursadoDAO dato = new Datos.CursadoDAO();

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public long getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(long dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public int getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(int codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public CursadoDAO getDato() {
        return dato;
    }

    public void setDato(CursadoDAO dato) {
        this.dato = dato;
    }

    public boolean baja(JTable tabla) {

        return dato.bajaDAO(tabla);
    }

    public boolean modifica(ModeloCursado cursado) {

        return dato.modificaDAO(cursado);
    }

    public Set<String> traeCodMaterias() {

        return dato.traeCodMateriaDAO();

    }

    public Set<String> traeDniAlumno() {

        return dato.traeDniAlumnoDAO();

    }

    public ArrayList<ModeloCursado> traeCursado() {
        return dato.traerDatosDAO();

    }

    public boolean validaCarga(String a) {
        if (a.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cargaDatos(ModeloCursado cursado) {

        return dato.cargaDatosDAO(cursado);
    }

}
