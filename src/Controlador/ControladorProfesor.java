/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModeloProfesor;
import Vista.VistaProfesor;
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
public class ControladorProfesor implements ActionListener, MouseListener {
    
    Vista.VistaProfesor vistaProfesor;
    Modelo.ModeloProfesor modeloProfesor;
    DefaultTableModel modeloTabla;

    public ControladorProfesor(VistaProfesor vistaProfesor, ModeloProfesor modeloProfesor) {
        this.vistaProfesor = vistaProfesor;
        this.modeloProfesor = modeloProfesor;
        vistaProfesor.setVisible(true);
        vistaProfesor.setTitle("Profesores");
        vistaProfesor.setLocationRelativeTo(null);
        this.llenarTabla(this.vistaProfesor.getTablaProfesores());
        botones();
        
    }
    
    public void botones(){
        this.vistaProfesor.getBotonNuevo().addActionListener(this);
        this.vistaProfesor.getBotonEliminar().addActionListener(this);
        this.vistaProfesor.getBotonModificar().addActionListener(this);
        this.vistaProfesor.getBotonVolver().addActionListener(this);
        this.vistaProfesor.getTablaProfesores().addMouseListener(this);
    
    
    }
 @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(this.vistaProfesor.getBotonNuevo())) {

            if (modeloProfesor.validaCarga(this.vistaProfesor.getTextoNombre().getText())
                    || modeloProfesor.validaCarga(this.vistaProfesor.getTextoApellido().getText())
                    || modeloProfesor.validaCarga(this.vistaProfesor.getTextoDNI().getText())||
                            modeloProfesor.validaCarga(this.vistaProfesor.getFechaNac().getDateFormatString()))
                    {
                JOptionPane.showMessageDialog(null, "Debe cargar los campos de nombre, apellido, Dni y fecha de nacimiento");
            } else if (modeloProfesor.validaDNI(this.vistaProfesor.getTextoDNI().getText())) {
                JOptionPane.showMessageDialog(null, "El DNI no es válido");
            } else if (modeloProfesor.dniRepetido(modeloProfesor)) {
                JOptionPane.showMessageDialog(null, "Ya hay un profesor con ese DNI cargado ");
                limpiaCuadros();
                 this.vistaProfesor.getTextoDNI().setEditable(true);
            } else {

                this.modeloProfesor.setNombre(this.vistaProfesor.getTextoNombre().getText());
                this.modeloProfesor.setApellido(this.vistaProfesor.getTextoApellido().getText());
                this.modeloProfesor.setDni(Long.valueOf(this.vistaProfesor.getTextoDNI().getText()));
                this.modeloProfesor.setDomicilio(this.vistaProfesor.getTextoDomicilio().getText());
                this.modeloProfesor.setTelefono(this.vistaProfesor.getTextoTelefono().getText());
                java.util.Date date = this.vistaProfesor.getFechaNac().getDate();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                this.modeloProfesor.setFechaNac(sqlDate);
                

                this.modeloProfesor.cargaDatos(modeloProfesor);
                this.limpiarTabla(this.vistaProfesor.getTablaProfesores());
                limpiaCuadros();
                llenarTabla(this.vistaProfesor.getTablaProfesores());
                JOptionPane.showMessageDialog(null, "profesor cargado con éxito");

            }
        } else if (e.getSource().equals(this.vistaProfesor.getBotonEliminar())) {
            if (modeloProfesor.validaCarga(this.vistaProfesor.getTextoNombre().getText())
                    || modeloProfesor.validaCarga(this.vistaProfesor.getTextoApellido().getText())
                    || modeloProfesor.validaCarga(this.vistaProfesor.getTextoDNI().getText())
                    || modeloProfesor.validaCarga(this.vistaProfesor.getFechaNac().getDateFormatString())) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar a un profesor para eliminar");
            }
            else{
            if (this.modeloProfesor.baja(this.vistaProfesor.getTablaProfesores())) {
                
                
                limpiarTabla(this.vistaProfesor.getTablaProfesores());
                llenarTabla(this.vistaProfesor.getTablaProfesores());
                JOptionPane.showMessageDialog(null, "Se eliminó al profesor");
                limpiaCuadros();
                this.vistaProfesor.getTextoDNI().setEditable(true);
            }}

        } else if (e.getSource().equals(this.vistaProfesor.getBotonModificar())) {
            
             if (modeloProfesor.validaCarga(this.vistaProfesor.getTextoNombre().getText())
                    || modeloProfesor.validaCarga(this.vistaProfesor.getTextoApellido().getText())
                    || modeloProfesor.validaCarga(this.vistaProfesor.getTextoDNI().getText())
                     ||modeloProfesor.validaCarga(this.vistaProfesor.getFechaNac().getDateFormatString())
                     
                   ) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un Profesor para modificar");}
             else{
                 
            this.modeloProfesor.setDni(Long.parseLong(this.vistaProfesor.getTextoDNI().getText()));
            this.modeloProfesor.setNombre(this.vistaProfesor.getTextoNombre().getText());
            this.modeloProfesor.setApellido(this.vistaProfesor.getTextoApellido().getText());
            this.modeloProfesor.setDomicilio(this.vistaProfesor.getTextoDomicilio().getText());
            this.modeloProfesor.setTelefono(this.vistaProfesor.getTextoTelefono().getText());
            java.util.Date date = this.vistaProfesor.getFechaNac().getDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            this.modeloProfesor.setFechaNac(sqlDate);
            
            if (this.modeloProfesor.modifica(modeloProfesor)) {
                JOptionPane.showMessageDialog(null, "Profesor modificado con éxito");
            }
          
            this.limpiarTabla(this.vistaProfesor.getTablaProfesores());
            limpiaCuadros();
            llenarTabla(this.vistaProfesor.getTablaProfesores());
             this.vistaProfesor.getTextoDNI().setEditable(true);

        }}
        else if (e.getSource().equals(this.vistaProfesor.getBotonVolver())){
            Vista.VistaMenu vis = new Vista.VistaMenu();
            Modelo.ModeloMenu modeloMenu = new Modelo.ModeloMenu();
            ControladorMenu controladorMenu = new ControladorMenu(vis, modeloMenu);
            this.vistaProfesor.dispose();
            
            
            
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {

        if (me.getButton() == 1) {

            int fila = this.vistaProfesor.getTablaProfesores().rowAtPoint(me.getPoint());
            if (fila > -1) {
                vistaProfesor.getTextoDNI().setText((String.valueOf(this.vistaProfesor.getTablaProfesores().getValueAt(fila, 0))));
                vistaProfesor.getTextoDNI().setEditable(false);
                vistaProfesor.getTextoNombre().setText(String.valueOf(this.vistaProfesor.getTablaProfesores().getValueAt(fila, 1)));
                this.vistaProfesor.getTextoApellido().setText(String.valueOf(this.vistaProfesor.getTablaProfesores().getValueAt(fila, 2)));
                String date = String.valueOf(this.vistaProfesor.getTablaProfesores().getValueAt(fila, 3));
                try {
                    java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                    this.vistaProfesor.getFechaNac().setDate(date2);

                } catch (ParseException ex) {
                    Logger.getLogger(ControladorAlumno.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.vistaProfesor.getTextoDomicilio().setText(String.valueOf(this.vistaProfesor.getTablaProfesores().getValueAt(fila, 4)));
                this.vistaProfesor.getTextoTelefono().setText(String.valueOf(this.vistaProfesor.getTablaProfesores().getValueAt(fila, 5)));
               
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
    
    
    
    public String[] nombreColumnas() {  //nombre de las columnas del jtable
        String[] Columna = {"DNI", "Nombre", "Apellido", "Fecha de Nacimiento", "Domicilio", "Telefono"};
        return Columna;
    }
    
     public void limpiarTabla(JTable tabla) {    //limpia la tabla
        DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
        int a = tabla.getRowCount() - 1;
        for (int i = a; i >= 0; i--) {
            tb.removeRow(tb.getRowCount() - 1);
        }
    }
    
    
    public void llenarTabla(JTable tabla){
        
        modeloTabla = new DefaultTableModel(null, nombreColumnas());
        ArrayList <Modelo.ModeloProfesor> profesores;
        profesores = this.modeloProfesor.traeProfesores();
        this.limpiarTabla(this.vistaProfesor.getTablaProfesores());
        Object datos [] = new Object [6];
        if(profesores.size()>0){
             for (int i = 0; i < profesores.size(); i++) {
                datos[0] = profesores.get(i).getDni();
                datos[1] = profesores.get(i).getNombre();
                datos[2] = profesores.get(i).getApellido();
                datos[3] = profesores.get(i).getFechaNac();
                datos[4] = profesores.get(i).getDomicilio();
                datos[5] = profesores.get(i).getTelefono();
               
                modeloTabla.addRow(datos);
            }
            tabla.setModel(modeloTabla);
        profesores.clear();
        
        }}
    
     public void limpiaCuadros() {
        this.vistaProfesor.getTextoDNI().setText("");
        this.vistaProfesor.getTextoApellido().setText("");
        this.vistaProfesor.getTextoDomicilio().setText("");
        this.vistaProfesor.getTextoNombre().setText("");
        this.vistaProfesor.getTextoTelefono().setText("");
      
        this.vistaProfesor.getFechaNac().setDate(null);
    }
    
    
        
    
    
    
    
}
