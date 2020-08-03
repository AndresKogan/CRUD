/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloCursado;
import Vista.VistaCursado;
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
public class ControladorCursado implements ActionListener, MouseListener {
    
    private VistaCursado vistaCursado;
    private ModeloCursado modeloCursado;
    private DefaultTableModel modeloTabla;

    public ControladorCursado(VistaCursado vistaCursado, ModeloCursado modeloCursado) {
        this.vistaCursado = vistaCursado;
        this.modeloCursado = modeloCursado;
        vistaCursado.setVisible(true);
        vistaCursado.setTitle("Cursado");
        vistaCursado.setLocationRelativeTo(null);
        this.llenarTabla(this.vistaCursado.getTablaCursado());
        botones();
        dniAlumnos();
        codigosDeMaterias();
                
    }
    
     public void botones() {
        this.vistaCursado.getBotonNuevo().addActionListener(this);
        this.vistaCursado.getBotonEliminar().addActionListener(this);
        this.vistaCursado.getBotonModificar().addActionListener(this);
        this.vistaCursado.getBotonVolver().addActionListener(this);
        this.vistaCursado.getTablaCursado().addMouseListener(this);
        
    
    
     }
     
     @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.vistaCursado.getBotonNuevo())) {
            if (modeloCursado.validaCarga(this.vistaCursado.getComboDNIALumno().getSelectedItem().toString())
                    || modeloCursado.validaCarga(this.vistaCursado.getComboCodMateria().getSelectedItem().toString())
                           || modeloCursado.validaCarga(this.vistaCursado.getTextoNota().getText())) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar Dni del alumno, código de materia e ingresar una  Nota");

            } else {
                this.modeloCursado.setCodigoMateria(Integer.parseInt(this.vistaCursado.getComboCodMateria().getSelectedItem().toString()));
                this.modeloCursado.setDniAlumno(Long.parseLong(this.vistaCursado.getComboDNIALumno().getSelectedItem().toString()));
                this.modeloCursado.setNota(Integer.parseInt(this.vistaCursado.getTextoNota().getText()));

            
            if (this.modeloCursado.cargaDatos(modeloCursado)) {
                JOptionPane.showMessageDialog(null, "Nota cargada con éxito");
            }

            limpiarTabla(this.vistaCursado.getTablaCursado());
            llenarTabla(this.vistaCursado.getTablaCursado());
            limpiaCuadros();}

        } else if (e.getSource().equals(this.vistaCursado.getBotonEliminar())) {
             if (modeloCursado.validaCarga(this.vistaCursado.getComboDNIALumno().getSelectedItem().toString())
                    || modeloCursado.validaCarga(this.vistaCursado.getComboCodMateria().getSelectedItem().toString())
                           || modeloCursado.validaCarga(this.vistaCursado.getTextoNota().getText())) {
                 JOptionPane.showMessageDialog(null, "Debe seleccionar una nota para eliminar");}
             else{
            if (this.modeloCursado.baja(this.vistaCursado.getTablaCursado())) {
                limpiarTabla(this.vistaCursado.getTablaCursado());
                llenarTabla(this.vistaCursado.getTablaCursado());
                JOptionPane.showMessageDialog(null, "Nota eliminada con éxito");
                limpiaCuadros();
            }

        }}
        else if (e.getSource().equals(this.vistaCursado.getBotonModificar())){
             if (modeloCursado.validaCarga(this.vistaCursado.getComboDNIALumno().getSelectedItem().toString())
                    || modeloCursado.validaCarga(this.vistaCursado.getComboCodMateria().getSelectedItem().toString())
                           || modeloCursado.validaCarga(this.vistaCursado.getTextoNota().getText())) {
                 JOptionPane.showMessageDialog(null, "Debe seleccionar una nota para modificar");}
             else{
              this.modeloCursado.setCodigoMateria(Integer.parseInt(this.vistaCursado.getComboCodMateria().getSelectedItem().toString()));
                this.modeloCursado.setDniAlumno(Long.parseLong(this.vistaCursado.getComboDNIALumno().getSelectedItem().toString()));
                this.modeloCursado.setNota(Integer.parseInt(this.vistaCursado.getTextoNota().getText()));
             
             if (this.modeloCursado.modifica(modeloCursado)){
                JOptionPane.showMessageDialog(null, "Nota modificada con éxito");
                 limpiarTabla(this.vistaCursado.getTablaCursado());
                llenarTabla(this.vistaCursado.getTablaCursado());
                limpiaCuadros();     
            }
            
            
        }}
        else if (e.getSource().equals(this.vistaCursado.getBotonVolver())){
        
        Vista.VistaMenu vis = new Vista.VistaMenu();
            Modelo.ModeloMenu modeloMenu = new Modelo.ModeloMenu();
            ControladorMenu controladorMenu = new ControladorMenu(vis, modeloMenu);
            this.vistaCursado.dispose();}

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getButton() == 1) {
            
            int fila = this.vistaCursado.getTablaCursado().rowAtPoint(e.getPoint());
            
            if (fila > -1) {
                  this.vistaCursado.getComboDNIALumno().setSelectedItem(String.valueOf(this.vistaCursado.getTablaCursado().getValueAt(fila, 0)));
                this.vistaCursado.getComboCodMateria().setSelectedItem(String.valueOf(this.vistaCursado.getTablaCursado().getValueAt(fila, 1)));
              this.vistaCursado.getTextoNota().setText(String.valueOf(this.vistaCursado.getTablaCursado().getValueAt(fila, 2)));

                }
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
        String[] columnas = {"DNI Alumno", "Código Materia", "Nota"};
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
         ArrayList<ModeloCursado> cursados;
        cursados = this.modeloCursado.traeCursado();
        this.limpiarTabla(this.vistaCursado.getTablaCursado());
        Object datos[] = new Object[4];
        if (cursados.size() > 0) {
            for (int i = 0; i < cursados.size(); i++) {
                datos[0] = cursados.get(i).getDniAlumno();
                datos[1] = cursados.get(i).getCodigoMateria();
                datos[2] = cursados.get(i).getNota();
                
                modeloTabla.addRow(datos);
            }
        }
        tabla.setModel(modeloTabla);
        cursados.clear();
    }
     
      public void dniAlumnos() {
        Set<String> dniAlu = this.modeloCursado.traeDniAlumno();
        Iterator<String> codIterator = dniAlu.iterator();
        while (codIterator.hasNext()) {
            this.vistaCursado.getComboDNIALumno().addItem(codIterator.next());
        }
    }
      
       public void codigosDeMaterias() {
        Set<String> codigoMat = this.modeloCursado.traeCodMaterias();
        Iterator<String> codIterator = codigoMat.iterator();
        while (codIterator.hasNext()) {
            this.vistaCursado.getComboCodMateria().addItem(codIterator.next());
        }
    }
       public void limpiaCuadros() {
        this.vistaCursado.getComboCodMateria().setSelectedIndex(0);
        this.vistaCursado.getComboDNIALumno().setSelectedIndex(0);
        this.vistaCursado.getTextoNota().setText("");
      
    }
    
}
