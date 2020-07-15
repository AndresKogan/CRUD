/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Usuario
 */
public class ControladorAlumno implements ActionListener {
    
    Vista.VistaAlumno vista;
    Modelo.ModeloAlumno modelo;
    public ControladorAlumno(Vista.VistaAlumno vista, Modelo.ModeloAlumno modelo){
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
        vista.setTitle("Alumno");
        vista.setLocationRelativeTo(null);
        botones();
        
    }
    
    public void botones (){
        
    vista.botonEliminar.addActionListener(this);
    vista.TextoApellido.addActionListener(this);
    vista.TextoDomicilio1.addActionListener(this);
    vista.TextoDNI.addActionListener(this);
   vista.TextoNombre.addActionListener(this);
   vista.TextoTelefono.addActionListener(this);
    vista.botonEliminar.addActionListener(this);
    vista.botonModificar.addActionListener(this);
    vista.botonNuevo.addActionListener(this);
    vista.botonVolver.addActionListener(this);
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource().equals(this.vista.botonNuevo)){
            
            this.modelo.setNombre(this.vista.TextoNombre.getText());
            this.modelo.setApellido(this.vista.TextoApellido.getText());
            this.modelo.setDni(Long.parseLong(this.vista.TextoDNI.getText()));
            this.modelo.setDomicilio(this.vista.TextoDomicilio1.getText());
            this.modelo.setTelefono(this.vista.TextoTelefono.getText());
            java.util.Date date = this.vista..getDate();  
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            
        }
       
    }

    
    }
    
    
        
        
    
    
    
    
    
    

