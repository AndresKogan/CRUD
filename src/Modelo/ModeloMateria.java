/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.VistaMateria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Usuario
 */
public class ModeloMateria implements ActionListener {

    Vista.VistaMateria vista;
    Modelo.ModeloMateria modelo;

    public ModeloMateria(VistaMateria vista, ModeloMateria modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
        vista.setTitle("Materia");
        vista.setLocation(null);

        botones();
    }

    
    public void botones(){
        
    
    vista.DniProfesor.addActionListener(this);
    vista.TextoCargaHoraria.addActionListener(this);
    vista.TextoCodigo.addActionListener(this);
    vista.TextoNombre.addActionListener(this);
    vista.TextoNombreProfesor.addActionListener(this);
   vista.botonEliminar.addActionListener(this);
    vista.botonModificar.addActionListener(this);
    vista.botonNuevo.addActionListener(this);
   vista.botonVolver.addActionListener(this);;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      }
}
    
