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
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author Usuario
 */
public class ControladorAlumno implements ActionListener, MouseListener {

    Vista.VistaAlumno vistaAlumno;
    Modelo.ModeloAlumno modeloAlumno;
    DefaultTableModel modeloTabla;

    public ControladorAlumno(Vista.VistaAlumno vistaAlumno, Modelo.ModeloAlumno modeloAlumno) {
        this.vistaAlumno = vistaAlumno;
        this.modeloAlumno = modeloAlumno;
        vistaAlumno.setVisible(true);
        vistaAlumno.setTitle("Alumno");
        vistaAlumno.setLocationRelativeTo(null);
        this.llenarTabla(this.vistaAlumno.getTablaAlumnos());
        botones();
        codigosDeInscripcion();

    }

    public void botones() {

        vistaAlumno.getBotonEliminar().addActionListener(this);
        vistaAlumno.getBotonModificar().addActionListener(this);
        vistaAlumno.getBotonNuevo().addActionListener(this);
        vistaAlumno.getBotonVolver().addActionListener(this);
        vistaAlumno.getTablaAlumnos().addMouseListener(this);
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.vistaAlumno.getBotonNuevo())) {

            if (modeloAlumno.validaCarga(this.vistaAlumno.getTextoNombre().getText())
                    || modeloAlumno.validaCarga(this.vistaAlumno.getTextoApellido().getText())
                    || modeloAlumno.validaCarga(this.vistaAlumno.getTextoDNI().getText())||
                            modeloAlumno.validaCarga(this.vistaAlumno.getFechaNac().getDateFormatString())||
                    modeloAlumno.validaCarga(this.vistaAlumno.getCodInsc().getSelectedItem().toString())){
                JOptionPane.showMessageDialog(null, "Debe cargar los campos de nombre, apellido, Dni, fecha de nacimiento e Inscripción");
            } else if (modeloAlumno.validaDNI(this.vistaAlumno.getTextoDNI().getText())) {
                JOptionPane.showMessageDialog(null, "El DNI no es válido");
            } else if (modeloAlumno.dniRepetido(modeloAlumno)) {
                JOptionPane.showMessageDialog(null, "Ya hay un alumno con ese DNI cargado ");
                limpiaCuadros();
                 this.vistaAlumno.getTextoDNI().setEditable(true);
            } else {

                this.modeloAlumno.setNombre(this.vistaAlumno.getTextoNombre().getText());
                this.modeloAlumno.setApellido(this.vistaAlumno.getTextoApellido().getText());
                this.modeloAlumno.setDni(Long.valueOf(this.vistaAlumno.getTextoDNI().getText()));
                this.modeloAlumno.setDomicilio(this.vistaAlumno.getTextoDomicilio1().getText());
                this.modeloAlumno.setTelefono(this.vistaAlumno.getTextoTelefono().getText());
                java.util.Date date = this.vistaAlumno.getFechaNac().getDate();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.modeloAlumno.setFechaNac(sqlDate);
                this.modeloAlumno.setCodInsc(Long.parseLong(this.vistaAlumno.getCodInsc().getSelectedItem().toString()));

                this.modeloAlumno.cargaDatos(modeloAlumno);
                this.limpiarTabla(this.vistaAlumno.getTablaAlumnos());
                limpiaCuadros();
                llenarTabla(this.vistaAlumno.getTablaAlumnos());
                JOptionPane.showMessageDialog(null, "Alumno cargado con éxito");

            }
        } else if (e.getSource().equals(this.vistaAlumno.getBotonEliminar())) {
            if (modeloAlumno.validaCarga(this.vistaAlumno.getTextoNombre().getText())
                    || modeloAlumno.validaCarga(this.vistaAlumno.getTextoApellido().getText())
                    || modeloAlumno.validaCarga(this.vistaAlumno.getTextoDNI().getText())||
                            modeloAlumno.validaCarga(this.vistaAlumno.getFechaNac().getDateFormatString())||
                    modeloAlumno.validaCarga(this.vistaAlumno.getCodInsc().getSelectedItem().toString())){
            JOptionPane.showMessageDialog(null, "Debe seleccionar un alumno para eliminar");
        }
            else{

            if (this.modeloAlumno.baja(this.vistaAlumno.getTablaAlumnos())) {
                limpiarTabla(this.vistaAlumno.getTablaAlumnos());
                llenarTabla(this.vistaAlumno.getTablaAlumnos());
                JOptionPane.showMessageDialog(null, "Se eliminó al Alumno");
                limpiaCuadros();
                this.vistaAlumno.getTextoDNI().setEditable(true);
            }
            }
        } else if (e.getSource().equals(this.vistaAlumno.getBotonModificar())) {
            
             if (modeloAlumno.validaCarga(this.vistaAlumno.getTextoNombre().getText())
                    || modeloAlumno.validaCarga(this.vistaAlumno.getTextoApellido().getText())
                    || modeloAlumno.validaCarga(this.vistaAlumno.getTextoDNI().getText())
                     ||modeloAlumno.validaCarga(this.vistaAlumno.getFechaNac().getDateFormatString())
                     ||
                    modeloAlumno.validaCarga(this.vistaAlumno.getCodInsc().getSelectedItem().toString())) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Alumno para modificar");}
             else{
            this.modeloAlumno.setNombre(this.vistaAlumno.getTextoNombre().getText());
            this.modeloAlumno.setApellido(this.vistaAlumno.getTextoApellido().getText());
            this.modeloAlumno.setDni(Long.parseLong(this.vistaAlumno.getTextoDNI().getText()));
            this.modeloAlumno.setDomicilio(this.vistaAlumno.getTextoDomicilio1().getText());
            this.modeloAlumno.setTelefono(this.vistaAlumno.getTextoTelefono().getText());
            java.util.Date date = this.vistaAlumno.getFechaNac().getDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            this.modeloAlumno.setFechaNac(sqlDate);
            this.modeloAlumno.setCodInsc(Long.parseLong(this.vistaAlumno.getCodInsc().getSelectedItem().toString()));
            if (this.modeloAlumno.modifica(modeloAlumno)) {
                JOptionPane.showMessageDialog(null, "Alumno modificado con éxito");
            }
            
            this.limpiarTabla(this.vistaAlumno.getTablaAlumnos());
            limpiaCuadros();
            llenarTabla(this.vistaAlumno.getTablaAlumnos());
            this.vistaAlumno.getTextoDNI().setEditable(true);

        }}
        else if (e.getSource().equals(this.vistaAlumno.getBotonVolver())){
            Vista.VistaMenu vis = new Vista.VistaMenu();
            Modelo.ModeloMenu modeloMenu = new Modelo.ModeloMenu();
            ControladorMenu controladorMenu = new ControladorMenu(vis, modeloMenu);
            this.vistaAlumno.dispose();
            
            
            
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getButton() == 1) {

            int fila = this.vistaAlumno.getTablaAlumnos().rowAtPoint(me.getPoint());
            if (fila > -1) {
                vistaAlumno.getTextoDNI().setText((String.valueOf(this.vistaAlumno.getTablaAlumnos().getValueAt(fila, 0))));
                vistaAlumno.getTextoDNI().setEditable(false);
                vistaAlumno.getTextoNombre().setText(String.valueOf(this.vistaAlumno.getTablaAlumnos().getValueAt(fila, 1)));
                this.vistaAlumno.getTextoApellido().setText(String.valueOf(this.vistaAlumno.getTablaAlumnos().getValueAt(fila, 2)));
                String date = String.valueOf(this.vistaAlumno.getTablaAlumnos().getValueAt(fila, 3));
                try {
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    this.vistaAlumno.getFechaNac().setDate(date2);

                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAlumno.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.vistaAlumno.getTextoDomicilio1().setText(String.valueOf(this.vistaAlumno.getTablaAlumnos().getValueAt(fila, 4)));
                this.vistaAlumno.getTextoTelefono().setText(String.valueOf(this.vistaAlumno.getTablaAlumnos().getValueAt(fila, 5)));
                this.vistaAlumno.getCodInsc().setSelectedItem(String.valueOf(this.vistaAlumno.getTablaAlumnos().getValueAt(fila, 6)));

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

    public void codigosDeInscripcion() {
        Set<String> codigoIns = this.modeloAlumno.CodInsc();
        Iterator<String> codIterator = codigoIns.iterator();
        while (codIterator.hasNext()) {
            this.vistaAlumno.getCodInsc().addItem(codIterator.next());

        }
    }

    public String[] nombreColumnas() {  //nombre de las columnas del jtable
        String[] Columna = {"DNI", "Nombre", "Apellido", "Fecha de Nacimiento", "Domicilio", "Telefono", "Codigo de Inscripcion"};
        return Columna;
    }

    public void limpiarTabla(JTable tablaAlumno) {
        DefaultTableModel tb = (DefaultTableModel) tablaAlumno.getModel();
        int a = tablaAlumno.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }

    public void llenarTabla(JTable tabla) {

        modeloTabla = new DefaultTableModel(null, nombreColumnas());
        ArrayList<Modelo.ModeloAlumno> alumnos;
        alumnos = this.modeloAlumno.traeAlumnos();
        this.limpiarTabla(this.vistaAlumno.getTablaAlumnos());
        Object datos[] = new Object[7];
        if (alumnos.size() > 0) {
            for (int i = 0; i < alumnos.size(); i++) {
                datos[0] = alumnos.get(i).getDni();
                datos[1] = alumnos.get(i).getNombre();
                datos[2] = alumnos.get(i).getApellido();
                datos[3] = alumnos.get(i).getFechaNac();
                datos[4] = alumnos.get(i).getDomicilio();
                datos[5] = alumnos.get(i).getTelefono();
                datos[6] = alumnos.get(i).getCodInsc();
                modeloTabla.addRow(datos);
            }

        }
        tabla.setModel(modeloTabla);
        alumnos.clear();
    }

    public void limpiaCuadros() {
        this.vistaAlumno.getTextoDNI().setText("");
        this.vistaAlumno.getTextoApellido().setText("");
        this.vistaAlumno.getTextoDomicilio1().setText("");
        this.vistaAlumno.getTextoNombre().setText("");
        this.vistaAlumno.getTextoTelefono().setText("");
        this.vistaAlumno.getCodInsc().setSelectedIndex(0);
        this.vistaAlumno.getFechaNac().setDate(null);
    }

}
