/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.VistaNotas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Usuario
 */
public class ModeloNotas implements ActionListener {
    
    Vista.VistaNotas vista;
    Modelo.ModeloNotas modelo;

    public ModeloNotas(VistaNotas vista, ModeloNotas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
        vista.setTitle("Notas");
        vista.setLocation(null);
        botones();
    }
    
    
    public void botones(){
    
    vista.AlumnoDni.addActionListener(this);
    vista.CodigoMateria.addActionListener(this);
    vista.TextoAlumno.addActionListener(this);
    vista.TextoNombreMateria.addActionListener(this);
    vista.TextoNota.addActionListener(this);
    vista.botonEliminar.addActionListener(this);
    vista.botonModificar.addActionListener(this);
    vista.botonNuevo.addActionListener(this);
    vista.botonVolver.addActionListener(this);
    
    
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
      }
    
}