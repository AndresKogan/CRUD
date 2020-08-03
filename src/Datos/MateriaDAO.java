/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.ModeloMateria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class MateriaDAO extends SQLQuery {

    private ArrayList<ModeloMateria> materias;
    private ModeloMateria materia;

    public ArrayList<ModeloMateria> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<ModeloMateria> materias) {
        this.materias = materias;
    }

    public boolean cargaDatosDAO(ModeloMateria materia) {
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String sql = "INSERT INTO materia (mat_cod, mat_nombre, mat_prof_dni) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement(sql);
            preparedStmt.setInt(1, materia.getCodigoMateria());
            preparedStmt.setString(2, materia.getNombreMateria());
            preparedStmt.setLong(3, materia.getProfMateriaDNI());
            preparedStmt.execute();
            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean nombreRepetidoDAO(ModeloMateria materia) {
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from materia where mat_nombre=?");
            this.consulta.setString(1, materia.getNombreMateria());
            ResultSet resultados = consulta.executeQuery();
            if (resultados.next()) {

                return false;
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public ArrayList<ModeloMateria> traerDatosDAO() {
        materias = new ArrayList();
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select * from materia");
            ResultSet resultados = consulta.executeQuery();
            while (resultados.next()) {
                materia = new Modelo.ModeloMateria();
                materia.setCodigoMateria(resultados.getInt(1));
                materia.setNombreMateria(resultados.getString(2));
                materia.setProfMateriaDNI(resultados.getLong(3));

                materias.add(materia);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return materias;

    }

    public boolean bajaDAO(JTable tablaCarrera) {

        int seleccion;
        materias = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            this.consulta = this.conn.prepareStatement("DELETE FROM materia WHERE mat_cod = ?");
            seleccion = tablaCarrera.getSelectedRow();
            this.consulta.setLong(1, materias.get(seleccion).getCodigoMateria());
            consulta.executeUpdate();

            this.desconectar();
            return true;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificaDAO(ModeloMateria materia) {
        try {
            this.conectar("127.0.0.1", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            PreparedStatement preparedStmt = (PreparedStatement) this.conn.prepareStatement("UPDATE materia SET mat_nombre=?, mat_prof_dni=? WHERE mat_cod=?");

            preparedStmt.setString(1, materia.getNombreMateria());
            preparedStmt.setLong(2, materia.getProfMateriaDNI());
            preparedStmt.setInt(3, materia.getCodigoMateria());

            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Set<String> traeDNIProfesorDAO() {
        Set<String> codCarrera = new HashSet<>();
        try {
            this.conectar("localhost", "UTN-2020", "root", "mysql");
            this.consulta = this.conn.prepareStatement("select prof_dni from profesor");
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
}
