/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

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
import Modelo.ModeloInscripcion;
import Vista.VistaInscripcion;

/**
 *
 * @author Usuario
 */
public class ControladorInscripcion implements ActionListener, MouseListener {

    private VistaInscripcion vistaInscripcion;
    private ModeloInscripcion modeloInscripcion;
    private DefaultTableModel modeloTabla;

    public ControladorInscripcion(VistaInscripcion vistaInscripcion, ModeloInscripcion modeloInscripcion) {
        this.vistaInscripcion = vistaInscripcion;
        this.modeloInscripcion = modeloInscripcion;
        vistaInscripcion.setVisible(true);
        vistaInscripcion.setTitle("Inscripciones");
        vistaInscripcion.setLocationRelativeTo(null);
        this.llenarTabla(this.vistaInscripcion.getTablaInscripcion());
        this.vistaInscripcion.getTextoCodigo().setEditable(false);
        botones();
        codigosDeCarrera();
    }

    public void botones() {
        this.vistaInscripcion.getBotonNuevo().addActionListener(this);
        this.vistaInscripcion.getBotonEliminar().addActionListener(this);
        this.vistaInscripcion.getBotonModificar().addActionListener(this);
        this.vistaInscripcion.getBotonVolver().addActionListener(this);
        this.vistaInscripcion.getTablaInscripcion().addMouseListener(this);
        this.vistaInscripcion.getTextoCodigo().addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaInscripcion.getBotonNuevo())) {
            if (modeloInscripcion.validaCarga(this.vistaInscripcion.getTextoNombre().getText())
                    || modeloInscripcion.validaCarga(this.vistaInscripcion.getFechaInsc().getDateFormatString())||
                            modeloInscripcion.validaCarga(this.vistaInscripcion.getComboCodigoCarrera().getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Debe ingresar fecha, nombre y código de carrera de la inscripción");

            } else {
                this.modeloInscripcion.setNombreInscripcion(this.vistaInscripcion.getTextoNombre().getText());
                java.util.Date date = this.vistaInscripcion.getFechaInsc().getDate();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.modeloInscripcion.setFechaInsc(sqlDate);
                this.modeloInscripcion.setCodigoCarrera(Integer.parseInt(this.vistaInscripcion.getComboCodigoCarrera().getSelectedItem().toString()));

            
            if (this.modeloInscripcion.cargaDatos(modeloInscripcion)) {
                JOptionPane.showMessageDialog(null, "Inscripción cargada con éxito");
            }

            limpiarTabla(this.vistaInscripcion.getTablaInscripcion());
            llenarTabla(this.vistaInscripcion.getTablaInscripcion());
            limpiaCuadros();}

        } else if (e.getSource().equals(this.vistaInscripcion.getBotonEliminar())) {
             if (modeloInscripcion.validaCarga(this.vistaInscripcion.getTextoNombre().getText())
                    || modeloInscripcion.validaCarga(this.vistaInscripcion.getFechaInsc().getDateFormatString())||
                            modeloInscripcion.validaCarga(this.vistaInscripcion.getComboCodigoCarrera().getSelectedItem().toString())) {
                 JOptionPane.showMessageDialog(null, "Debe seleccionar una inscripción para eliminar");}
             else {
            if (this.modeloInscripcion.baja(this.vistaInscripcion.getTablaInscripcion())) {
                limpiarTabla(this.vistaInscripcion.getTablaInscripcion());
                llenarTabla(this.vistaInscripcion.getTablaInscripcion());
                JOptionPane.showMessageDialog(null, "Inscripción eliminada con éxito");
                limpiaCuadros();
            }

        }}
        else if (e.getSource().equals(this.vistaInscripcion.getBotonModificar())){
            if (modeloInscripcion.validaCarga(this.vistaInscripcion.getTextoNombre().getText())
                    || modeloInscripcion.validaCarga(this.vistaInscripcion.getFechaInsc().getDateFormatString())||
                            modeloInscripcion.validaCarga(this.vistaInscripcion.getComboCodigoCarrera().getSelectedItem().toString())) {
                 JOptionPane.showMessageDialog(null, "Debe seleccionar una inscripción para modificar");}
             else {
            
            this.modeloInscripcion.setCodigoInscripcion(Integer.parseInt(this.vistaInscripcion.getTextoCodigo().getText()));
            this.modeloInscripcion.setNombreInscripcion(this.vistaInscripcion.getTextoNombre().getText());
             java.util.Date date = this.vistaInscripcion.getFechaInsc().getDate();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.modeloInscripcion.setFechaInsc(sqlDate);
             this.modeloInscripcion.setCodigoCarrera(Integer.parseInt(this.vistaInscripcion.getComboCodigoCarrera().getSelectedItem().toString()));
            if (this.modeloInscripcion.modifica(modeloInscripcion)){
                JOptionPane.showMessageDialog(null, "Inscripción modificada con éxito");
                 limpiarTabla(this.vistaInscripcion.getTablaInscripcion());
                llenarTabla(this.vistaInscripcion.getTablaInscripcion());
                limpiaCuadros();     
            }
            
            
        }}
        else if (e.getSource().equals(this.vistaInscripcion.getBotonVolver())){
        
        Vista.VistaMenu vis = new Vista.VistaMenu();
            Modelo.ModeloMenu modeloMenu = new Modelo.ModeloMenu();
            ControladorMenu controladorMenu = new ControladorMenu(vis, modeloMenu);
            this.vistaInscripcion.dispose();}

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
       if (e.getSource().equals(this.vistaInscripcion.getTextoCodigo())){
           if (modeloInscripcion.validaCarga((this.vistaInscripcion.getTextoCodigo().getText()))){
           JOptionPane.showMessageDialog(null, "El código se asignará automaticamente cuando cargue una nueva inscripción");}
           else {
                   JOptionPane.showMessageDialog(null, "el código no puede ser modificado");}
       }
       
           else if (e.getSource().equals(this.vistaInscripcion.getTablaInscripcion())){  
        if (e.getButton() == 1) {
            
            int fila = this.vistaInscripcion.getTablaInscripcion().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistaInscripcion.getTextoCodigo().setText(String.valueOf(this.vistaInscripcion.getTablaInscripcion().getValueAt(fila, 0)));
                this.vistaInscripcion.getTextoNombre().setText(String.valueOf(this.vistaInscripcion.getTablaInscripcion().getValueAt(fila, 1)));
                String date = String.valueOf(this.vistaInscripcion.getTablaInscripcion().getValueAt(fila, 2));
                try {
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    this.vistaInscripcion.getFechaInsc().setDate(date2);
                } catch (ParseException ex) {
                    Logger.getLogger(ControladorInscripcion.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.vistaInscripcion.getComboCodigoCarrera().setSelectedItem(String.valueOf(this.vistaInscripcion.getTablaInscripcion().getValueAt(fila, 3)));
            }}
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public String[] nombreColumnas() {
        String[] columnas = {"Código Inscripción", "Nombre", "Fecha", "Código de Carrera"};
        return columnas;
    }

    public void limpiarTabla(JTable tablaInsc) {
        DefaultTableModel tb = (DefaultTableModel) tablaInsc.getModel();
        int a = tablaInsc.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }

    }

    public void llenarTabla(JTable tabla) {
        modeloTabla = new DefaultTableModel(null, nombreColumnas());
        ArrayList<ModeloInscripcion> inscripciones;
        inscripciones = this.modeloInscripcion.traeInsc();
        this.limpiarTabla(this.vistaInscripcion.getTablaInscripcion());
        Object datos[] = new Object[4];
        if (inscripciones.size() > 0) {
            for (int i = 0; i < inscripciones.size(); i++) {
                datos[0] = inscripciones.get(i).getCodigoInscripcion();
                datos[1] = inscripciones.get(i).getNombreInscripcion();
                datos[2] = inscripciones.get(i).getFechaInsc();
                datos[3] = inscripciones.get(i).getCodigoCarrera();
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        inscripciones.clear();
    }

    public void codigosDeCarrera() {
        Set<String> codigoCar = this.modeloInscripcion.traeCodCarrera();
        Iterator<String> codIterator = codigoCar.iterator();
        while (codIterator.hasNext()) {
            this.vistaInscripcion.getComboCodigoCarrera().addItem(codIterator.next());
        }
    }

    public void limpiaCuadros() {
        this.vistaInscripcion.getTextoCodigo().setText("");
        this.vistaInscripcion.getTextoNombre().setText("");
        this.vistaInscripcion.getFechaInsc().setDate(null);
        this.vistaInscripcion.getComboCodigoCarrera().setSelectedIndex(0);
    }
}
