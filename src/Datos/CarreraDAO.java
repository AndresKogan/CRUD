/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.ModeloCarrera;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;


/**
 *
 * @author Usuario
 */
public class CarreraDAO extends SQLQuery {

    private ArrayList<ModeloCarrera> carreras;
    private ModeloCarrera carrera;

    public ArrayList<ModeloCarrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<ModeloCarrera> carreras) {
        this.carreras = carreras;
    }

    public boolean cargaDatosDAO(ModeloCarrera carrera) {
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO carrera (car_nombre, car_duracion) VALUES (?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setString(1, carrera.getNombreCarrera());
            preparedStmt.setInt(2, carrera.getDuracionCarrera());
            preparedStmt.execute();
            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean nombreRepetidoDAO(ModeloCarrera carrera) {
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from carrera where car_nombre=?");
            this.consulta.setString(1, carrera.getNombreCarrera());
            ResultSet hojadeResultados = consulta.executeQuery();
            if (hojadeResultados.next()) {
                return false;
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<ModeloCarrera> traerDatosDAO() {
        carreras = new ArrayList();
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from carrera");
            ResultSet resultados = consulta.executeQuery();
            while (resultados.next()) {
                carrera = new Modelo.ModeloCarrera();
                carrera.setCodigoCarrera(resultados.getInt(1));
                carrera.setNombreCarrera(resultados.getString(2));
                carrera.setDuracionCarrera(resultados.getInt(3));
                carreras.add(carrera);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return carreras;

    }

    public boolean bajaDAO(JTable tablaCarrera) {

        int seleccion;
        carreras = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
           this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM carrera WHERE car_cod = ?");
            seleccion = tablaCarrera.getSelectedRow();
            this.consulta.setLong(1, carreras.get(seleccion).getCodigoCarrera());
            consulta.executeUpdate();

            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(ModeloCarrera carrera) {
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE carrera SET car_nombre=?, car_duracion=? WHERE car_cod=?");
            
            preparedStmt.setString(1, carrera.getNombreCarrera());
            preparedStmt.setInt(2, carrera.getDuracionCarrera());
            preparedStmt.setInt(3, carrera.getCodigoCarrera());
             
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }}
