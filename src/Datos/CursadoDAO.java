/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.ModeloCursado;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class CursadoDAO extends SQLQuery {
    private ArrayList<ModeloCursado> cursados;
    private ModeloCursado cursado;

    public ArrayList<ModeloCursado> traerDatosDAO() {
        cursados = new ArrayList();
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from cursado");
            ResultSet resultados = consulta.executeQuery();

            while (resultados.next()) {
                cursado = new ModeloCursado();
                cursado.setDniAlumno(resultados.getLong(1));
                cursado.setCodigoMateria(resultados.getInt(2));
                cursado.setNota(resultados.getInt(3));
                cursados.add(cursado);

            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursados;
    }
    
    public Set<String> traeCodMateriaDAO(){
        Set<String> codCarrera = new HashSet<>();
         try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select mat_cod from materia");
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
    public Set<String> traeDniAlumnoDAO(){
        Set<String> codCarrera = new HashSet<>();
         try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select alu_dni from alumno");
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
public boolean cargaDatosDAO(ModeloCursado cursado) {  
        try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO cursado (cur_alu_dni, cur_mat_cod, cur_nota) VALUES (?,?,?)";
            java.sql.PreparedStatement preparedStmt = (java.sql.PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setLong(1, cursado.getDniAlumno());
            preparedStmt.setInt(2, cursado.getCodigoMateria());
            preparedStmt.setInt(3, cursado.getNota());
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
        cursados = new ArrayList(traerDatosDAO());
        try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("DELETE FROM cursado WHERE cur_alu_dni=? AND cur_mat_cod=?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, cursados.get(seleccion).getDniAlumno());
            this.consulta.setLong(2, cursados.get(seleccion).getCodigoMateria());
            consulta.executeUpdate();

            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CursadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  public boolean modificaDAO(ModeloCursado cursado) {    
      try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
             this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE cursado SET cur_nota=? WHERE cur_alu_dni=? AND cur_mat_cod=?");
            preparedStmt.setInt(1, cursado.getNota());
            preparedStmt.setLong(2, cursado.getDniAlumno());
            preparedStmt.setInt(3, cursado.getCodigoMateria());
            
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

}

