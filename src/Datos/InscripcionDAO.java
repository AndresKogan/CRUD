/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.ModeloInscripcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;

public class InscripcionDAO extends SQLQuery {

    private ArrayList<ModeloInscripcion> inscripciones;
    private ModeloInscripcion inscripcion;

    public ArrayList<ModeloInscripcion> traerDatosDAO() {
        inscripciones = new ArrayList();
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from inscripcion");
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                inscripcion = new ModeloInscripcion();
                inscripcion.setCodigoInscripcion(resultados.getInt(1));
                inscripcion.setNombreInscripcion(resultados.getString(2));
                inscripcion.setFechaInsc(resultados.getDate(3));
                inscripcion.setCodigoCarrera(resultados.getInt(4));
                inscripciones.add(inscripcion);

            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscripciones;
    }
    
    public Set<String> traeCodCarreraDAO(){
        Set<String> codCarrera = new HashSet<>();
         try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select car_cod from carrera");
            ResultSet resultados = consulta.executeQuery();
            codCarrera.add("");
            while (resultados.next()) {
                codCarrera.add(Long.toString(resultados.getLong(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codCarrera;
    }
public boolean cargaDatosDAO(ModeloInscripcion inscripcion) {  
        try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO inscripcion (insc_nombre, insc_fecha, insc_car_cod) VALUES (?,?,?)";
            java.sql.PreparedStatement preparedStmt = (java.sql.PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setString(1, inscripcion.getNombreInscripcion());
            preparedStmt.setDate(2, inscripcion.getFechaInsc());
            preparedStmt.setLong(3, inscripcion.getCodigoCarrera());
            preparedStmt.execute();
            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
 public boolean bajaDAO(JTable tabla) {  //da de baja en la db
        int seleccion;
        inscripciones = new ArrayList(traerDatosDAO());
        try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
             this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM inscripcion WHERE insc_cod = ?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, inscripciones.get(seleccion).getCodigoInscripcion());
            consulta.executeUpdate();

            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  public boolean modificaDAO(ModeloInscripcion inscripcion) {    
      try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
             this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE inscripcion SET insc_nombre=?, insc_fecha=?, insc_car_cod=? WHERE insc_cod=?");
            preparedStmt.setString(1, inscripcion.getNombreInscripcion());
            preparedStmt.setDate(2, inscripcion.getFechaInsc());
            preparedStmt.setLong(3, inscripcion.getCodigoCarrera());
            preparedStmt.setLong(4, inscripcion.getCodigoInscripcion());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}

        
    
    