/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Usuario
 */
public class ModeloMenu implements ActionListener {

    Vista.VistaMenu vista;
    Modelo.ModeloMenu modelo;

    public ModeloMenu(VistaMenu vista, ModeloMenu modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setVisible(true);
        vista.setTitle("Menu");
        vista.setLocation(null);
        botones();
    }

    public void botones() {

        vista.botonAlumno.addActionListener(this);
        vista.botonMateria.addActionListener(this);
        vista.botonNotas.addActionListener(this);
        vista.botonProfesor.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
      }
}
    

