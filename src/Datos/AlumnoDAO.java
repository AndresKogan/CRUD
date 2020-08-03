/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.ModeloAlumno;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class AlumnoDAO extends SQLQuery {

    private ArrayList<ModeloAlumno> alumnos;
    private ModeloAlumno alumno;

    public ArrayList<ModeloAlumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<ModeloAlumno> alumnos) {
        this.alumnos = alumnos;
    }

    public boolean cargaDatosDAO(ModeloAlumno alumno) {
        try {
            
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO alumno (alu_dni, alu_nombre, alu_apellido, alu_fec_nac, alu_domicilio, alu_telefono, alu_insc_cod) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setLong(1, alumno.getDni());
            preparedStmt.setString(2, alumno.getNombre());
            preparedStmt.setString(3, alumno.getApellido());
            preparedStmt.setDate(4, alumno.getFechaNac());
            preparedStmt.setString(6, alumno.getTelefono());
            preparedStmt.setString(5, alumno.getDomicilio());
            preparedStmt.setLong(7, alumno.getCodInsc());

            preparedStmt.execute();
            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<ModeloAlumno> traerDatosDAO() {
        alumnos = new ArrayList();
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from alumno");
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                alumno = new ModeloAlumno();
                alumno.setDni(resultados.getLong(1));
                alumno.setNombre(resultados.getString(2));
                alumno.setApellido(resultados.getString(3));
                alumno.setFechaNac(resultados.getDate(4));
                alumno.setDomicilio(resultados.getString(5));
                alumno.setTelefono(resultados.getString(6));
                alumno.setCodInsc(resultados.getLong(7));
                alumnos.add(alumno);

            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return alumnos;

    }

    public boolean bajaDAO(JTable tablaAlumnos) {
        int seleccion;
        alumnos = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM Alumno WHERE alu_dni = ?");
            seleccion = tablaAlumnos.getSelectedRow();
            this.consulta.setLong(1, alumnos.get(seleccion).getDni());
            consulta.executeUpdate();

            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(ModeloAlumno alumno) {    
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            java.sql.PreparedStatement preparedStmt = (java.sql.PreparedStatement) this.conn.prepareStatement("UPDATE Alumno SET alu_nombre=?, alu_apellido=?, alu_fec_nac=?, alu_domicilio=?, alu_telefono=?,alu_insc_cod=? WHERE alu_dni=?");
            preparedStmt.setString(1, alumno.getNombre());
            preparedStmt.setString(2, alumno.getApellido());
            preparedStmt.setDate(3, alumno.getFechaNac());
            preparedStmt.setString(4, alumno.getDomicilio());
            preparedStmt.setString(5, alumno.getTelefono());
            preparedStmt.setLong(6, alumno.getCodInsc());
            preparedStmt.setLong(7, alumno.getDni());

            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Set<String> traeCodInsc() {

        Set<String> codInsc = new HashSet<>();

        try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select insc_cod from inscripcion");
            ResultSet resultados = consulta.executeQuery();
            codInsc.add("");
            while (resultados.next()) {
                codInsc.add(Long.toString(resultados.getLong(1)));

            }

            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codInsc;

    }
    public boolean dniRepetidoDAO(ModeloAlumno alumno){
        
        try{
           this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from alumno where alu_dni=?");
            this.consulta.setLong(1, alumno.getDni());
            ResultSet hojadeResultados = consulta.executeQuery();
            if(hojadeResultados.next()){
                return true;              
            }
            this.desconectar();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
   }
}
