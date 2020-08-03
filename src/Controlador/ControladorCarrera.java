/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloCarrera;
import Vista.VistaCarrera;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ControladorCarrera implements ActionListener, MouseListener {

    ModeloCarrera modeloCarrera;
    VistaCarrera vistaCarrera;
    DefaultTableModel modeloTabla;

    public ControladorCarrera(ModeloCarrera modeloCarrera, VistaCarrera vistaCarrera) {
        this.modeloCarrera = modeloCarrera;
        this.vistaCarrera = vistaCarrera;
        this.vistaCarrera.setVisible(true);
        this.vistaCarrera.setTitle("Carreras");
        this.vistaCarrera.setLocationRelativeTo(null);
        botones();
        this.llenarTabla(this.vistaCarrera.getTablaCarreras());
        this.vistaCarrera.getTextoCodigo().setEditable(false);
        this.vistaCarrera.getTextoCodigo().addMouseListener(this);

    }

    public void botones() {

        vistaCarrera.getBotonEliminar().addActionListener(this);
        vistaCarrera.getBotonNuevo().addActionListener(this);
        vistaCarrera.getBotonVolver().addActionListener(this);
        vistaCarrera.getBotonModificar().addActionListener(this);
        vistaCarrera.getTablaCarreras().addMouseListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.vistaCarrera.getBotonNuevo())) {
            if (modeloCarrera.validaCarga(this.vistaCarrera.getTextoNombreCarrera().getText())
                    || modeloCarrera.validaCarga(this.vistaCarrera.getTextoDuracion().getText())) {
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre y la duración de la carrera");
            } else {
                this.modeloCarrera.setNombreCarrera(this.vistaCarrera.getTextoNombreCarrera().getText());
                this.modeloCarrera.setDuracionCarrera(Integer.parseInt(this.vistaCarrera.getTextoDuracion().getText()));

                if (modeloCarrera.nombreRepetido(modeloCarrera)) {
                    if (this.modeloCarrera.cargaDatos(modeloCarrera)) {
                        JOptionPane.showMessageDialog(null, "La carrera se ha cargado con éxito");
                    }
                    this.limpiarTabla(this.vistaCarrera.getTablaCarreras());
                    llenarTabla(this.vistaCarrera.getTablaCarreras());
                    limpiaCuadros();

                } else {
                    JOptionPane.showMessageDialog(null, "Ya hay una carrera con ese nombre");

                }

            }
        } else if (e.getSource()
                .equals(this.vistaCarrera.getBotonEliminar())) {
            if (modeloCarrera.validaCarga(this.vistaCarrera.getTextoNombreCarrera().getText())
                    || modeloCarrera.validaCarga(this.vistaCarrera.getTextoDuracion().getText())) {
                JOptionPane.showMessageDialog(null, "debe seleccionar una carrera para eliminar");}
            else{
            
            if (this.modeloCarrera.baja(this.vistaCarrera.getTablaCarreras())) {
                limpiarTabla(this.vistaCarrera.getTablaCarreras());
                llenarTabla(this.vistaCarrera.getTablaCarreras());
                limpiaCuadros();
                JOptionPane.showMessageDialog(null, "Carrera eliminada con éxito");

            }}

        } else if (e.getSource().equals(this.vistaCarrera.getBotonModificar())) {
            if (modeloCarrera.validaCarga(this.vistaCarrera.getTextoNombreCarrera().getText())
                    || modeloCarrera.validaCarga(this.vistaCarrera.getTextoDuracion().getText())) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera para modificar");
            } else {
                this.modeloCarrera.setNombreCarrera(this.vistaCarrera.getTextoNombreCarrera().getText());
                this.modeloCarrera.setCodigoCarrera(Integer.parseInt(this.vistaCarrera.getTextoCodigo().getText()));
                this.modeloCarrera.setDuracionCarrera(Integer.parseInt(this.vistaCarrera.getTextoDuracion().getText()));

                if (this.modeloCarrera.modifica(modeloCarrera)) {
                    JOptionPane.showMessageDialog(null, "Carrera modificada con éxito");
                }
                this.limpiarTabla(this.vistaCarrera.getTablaCarreras());
                llenarTabla(this.vistaCarrera.getTablaCarreras());
                limpiaCuadros();

            }
        } else if (e.getSource().equals(this.vistaCarrera.getBotonVolver())) {
            Vista.VistaMenu vis = new Vista.VistaMenu();
            Modelo.ModeloMenu mod = new Modelo.ModeloMenu();
            ControladorMenu controladorMenu = new ControladorMenu(vis, mod);
            this.vistaCarrera.dispose();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
        if (e.getSource().equals(this.vistaCarrera.getTextoCodigo())){
           if (modeloCarrera.validaCarga((this.vistaCarrera.getTextoCodigo().getText()))){
           JOptionPane.showMessageDialog(null, "El código se asignará automaticamente cuando cargue una nueva carrera");}
           else {
                   JOptionPane.showMessageDialog(null, "el código no puede ser modificado");}
       }
       
           else if (e.getSource().equals(this.vistaCarrera.getTablaCarreras())){  
        if (e.getButton() == 1) {
            int fila = this.vistaCarrera.getTablaCarreras().rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistaCarrera.getTextoCodigo().setText(String.valueOf(this.vistaCarrera.getTablaCarreras().getValueAt(fila,0)));
                this.vistaCarrera.getTextoNombreCarrera().setText(String.valueOf(this.vistaCarrera.getTablaCarreras().getValueAt(fila, 1)));
                this.vistaCarrera.getTextoDuracion().setText(String.valueOf(this.vistaCarrera.getTablaCarreras().getValueAt(fila, 2)));
            }
        }
    }}

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
        String[] Columna = {"Código", "Nombre", "Duración"};
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
        ArrayList<ModeloCarrera> carreras;
        carreras = this.modeloCarrera.traeCarreras();
        this.limpiarTabla(this.vistaCarrera.getTablaCarreras());
        Object datos[] = new Object[3];
        if (carreras.size() > 0) {
            for (int i = 0; i < carreras.size(); i++) {
                datos[0] = carreras.get(i).getCodigoCarrera();
                datos[1] = carreras.get(i).getNombreCarrera();
                datos[2] = carreras.get(i).getDuracionCarrera();

                modeloTabla.addRow(datos);
            }
        }
        tablaCarrera.setModel(modeloTabla);
        carreras.clear();
    }

    public void limpiaCuadros() {

        this.vistaCarrera.getTextoNombreCarrera().setText("");
        this.vistaCarrera.getTextoDuracion().setText("");
        this.vistaCarrera.getTextoCodigo().setText("");

    }

}
