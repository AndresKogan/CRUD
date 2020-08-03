/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloMateria;
import Vista.VistaMateria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ControladorMateria implements ActionListener, MouseListener {

    VistaMateria vistaMateria;
    ModeloMateria modeloMateria;
    DefaultTableModel modeloTabla;

    public ControladorMateria(VistaMateria vistaMateria, ModeloMateria modeloMateria) {
        this.vistaMateria = vistaMateria;
        this.modeloMateria = modeloMateria;
        vistaMateria.setVisible(true);
        vistaMateria.setTitle("Materia");
        vistaMateria.setLocationRelativeTo(null);
        botones();
        dniProfesores();
        this.llenarTabla(this.vistaMateria.getTablaMaterias());
        this.vistaMateria.getTextoCodigo().setEditable(false);
        this.vistaMateria.getTextoCodigo().addMouseListener(this);
    }

    public void botones() {

        vistaMateria.getBotonEliminar().addActionListener(this);
        vistaMateria.getBotonNuevo().addActionListener(this);
        vistaMateria.getBotonVolver().addActionListener(this);
        vistaMateria.getBotonModificar().addActionListener(this);
        vistaMateria.getTablaMaterias().addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.vistaMateria.getBotonNuevo())) {
            if (modeloMateria.validaCarga(this.vistaMateria.getTextoNombre().getText())
                    || modeloMateria.validaCarga(String.valueOf(this.vistaMateria.getDniProfesor().getSelectedItem()))) {
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre y un profesor para la Materia");
            } else {
                this.modeloMateria.setNombreMateria(this.vistaMateria.getTextoNombre().getText());
                this.modeloMateria.setProfMateriaDNI(Long.parseLong(this.vistaMateria.getDniProfesor().getSelectedItem().toString()));

                if (modeloMateria.nombreRepetido(modeloMateria)) {
                    
                    if (this.modeloMateria.cargaDatos(modeloMateria)) {
                        JOptionPane.showMessageDialog(null, "La materia se ha cargado con éxito");
                    }
                    
                    this.limpiarTabla(this.vistaMateria.getTablaMaterias());
                    llenarTabla(this.vistaMateria.getTablaMaterias());
                    limpiaCuadros();
                    

                } else {
                    JOptionPane.showMessageDialog(null, "Ya hay una materia con ese nombre");

                }

            }
        } else if (e.getSource().equals(this.vistaMateria.getBotonEliminar())) {
            if (modeloMateria.validaCarga(this.vistaMateria.getTextoNombre().getText())
                    || modeloMateria.validaCarga(String.valueOf(this.vistaMateria.getDniProfesor().getSelectedItem()))){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una materia para eliminar");}
            else{
            if (this.modeloMateria.baja(this.vistaMateria.getTablaMaterias())) {
                limpiarTabla(this.vistaMateria.getTablaMaterias());
                llenarTabla(this.vistaMateria.getTablaMaterias());
                limpiaCuadros();
                JOptionPane.showMessageDialog(null, "Materia eliminada con éxito");

            }}

        } else if (e.getSource().equals(this.vistaMateria.getBotonModificar())) {
            if (modeloMateria.validaCarga(this.vistaMateria.getTextoNombre().getText())
                    || modeloMateria.validaCarga(String.valueOf(this.vistaMateria.getDniProfesor().getSelectedItem()))) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una materia para modificar");
            } else {
                this.modeloMateria.setCodigoMateria(Integer.parseInt(this.vistaMateria.getTextoCodigo().getText()));
                this.modeloMateria.setNombreMateria(this.vistaMateria.getTextoNombre().getText());
                this.modeloMateria.setProfMateriaDNI(Long.parseLong(this.vistaMateria.getDniProfesor().getSelectedItem().toString()));

                if (this.modeloMateria.modifica(modeloMateria)) {
                    JOptionPane.showMessageDialog(null, "Materia modificada con éxito");
                }
                this.limpiarTabla(this.vistaMateria.getTablaMaterias());
                llenarTabla(this.vistaMateria.getTablaMaterias());
                limpiaCuadros();

            }
        } else if (e.getSource().equals(this.vistaMateria.getBotonVolver())) {
            Vista.VistaMenu vis = new Vista.VistaMenu();
            Modelo.ModeloMenu mod = new Modelo.ModeloMenu();
            ControladorMenu controladorMenu = new ControladorMenu(vis, mod);
            this.vistaMateria.dispose();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getSource().equals(this.vistaMateria.getTextoCodigo())){
           if (modeloMateria.validaCarga((this.vistaMateria.getTextoCodigo().getText()))){
           JOptionPane.showMessageDialog(null, "El código se asignará automaticamente cuando cargue una nueva materia");}
           else {
                   JOptionPane.showMessageDialog(null, "el código no puede ser modificado");}
       }
       
           else if (e.getSource().equals(this.vistaMateria.getTablaMaterias())){  
        if (e.getButton() == 1) {
            int fila = this.vistaMateria.getTablaMaterias().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistaMateria.getTextoCodigo().setText(String.valueOf(this.vistaMateria.getTablaMaterias().getValueAt(fila, 0)));
                this.vistaMateria.getTextoNombre().setText(String.valueOf(this.vistaMateria.getTablaMaterias().getValueAt(fila, 1)));
                this.vistaMateria.getDniProfesor().setSelectedItem(String.valueOf(this.vistaMateria.getTablaMaterias().getValueAt(fila, 2)));
            }
        }
    }
    }
    @Override
    public void mousePressed(MouseEvent e
    ) {
    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
    }

    @Override
    public void mouseExited(MouseEvent e
    ) {
    }

    public String[] nombreColumnas() {
        String[] Columna = {"Código", "Nombre", "Dni Profesor"};
        return Columna;
    }

    public void limpiarTabla(JTable tablaCarrera) {
        DefaultTableModel tb = (DefaultTableModel) tablaCarrera.getModel();
        int a = tablaCarrera.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    public void llenarTabla(JTable tablaCarrera) {
        modeloTabla = new DefaultTableModel(null, nombreColumnas());
        ArrayList<ModeloMateria> materias;
        materias = this.modeloMateria.traeMaterias();
        this.limpiarTabla(this.vistaMateria.getTablaMaterias());
        Object datos[] = new Object[3];
        if (materias.size() > 0) {
            for (int i = 0; i < materias.size(); i++) {
                datos[0] = materias.get(i).getCodigoMateria();
                datos[1] = materias.get(i).getNombreMateria();
                datos[2] = materias.get(i).getProfMateriaDNI();

                modeloTabla.addRow(datos);
            }
        }
        tablaCarrera.setModel(modeloTabla);
        materias.clear();
    }

    public void limpiaCuadros() {

        this.vistaMateria.getTextoNombre().setText("");
        this.vistaMateria.getTextoCodigo().setText("");
        this.vistaMateria.getDniProfesor().setSelectedIndex(0);

    }

    public void dniProfesores() {
        Set<String> dniProfesores = this.modeloMateria.traeDNIProfesor();
        Iterator<String> dniIterator = dniProfesores.iterator();
        while (dniIterator.hasNext()) {
            this.vistaMateria.getDniProfesor().addItem(dniIterator.next());
        }
    }

}
