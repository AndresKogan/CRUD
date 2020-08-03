/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VistaCursado;
import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Usuario
 */
public class ControladorMenu implements ActionListener {
    
      Vista.VistaMenu vistaMenu;
    Modelo.ModeloMenu modeloMenu;

    public ControladorMenu() {
    }
    
    
    

    public ControladorMenu(VistaMenu vistaMenu, ModeloMenu modeloMenu) {
        this.vistaMenu = vistaMenu;
        this.modeloMenu = modeloMenu;
        vistaMenu.setVisible(true);
        vistaMenu.setTitle("Menu");
        vistaMenu.setLocationRelativeTo(null);
        botones();
    }

    public void botones() {

        vistaMenu.getBotonAlumno().addActionListener(this);
        vistaMenu.getBotonMateria().addActionListener(this);
        vistaMenu.getBotonCarrera().addActionListener(this);
        vistaMenu.getBotonProfesor().addActionListener(this);
        vistaMenu.getBotonNotas().addActionListener(this);
        vistaMenu.getBotonInscripcion().addActionListener(this);
               

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vistaMenu.getBotonAlumno())){
            VistaAlumno vistaAlumno = new VistaAlumno();
            ModeloAlumno modeloAlumno = new ModeloAlumno();
            ControladorAlumno controladorAlumno = new ControladorAlumno(vistaAlumno, modeloAlumno);
            this.vistaMenu.dispose();
        
        
        }
        else if (e.getSource().equals(vistaMenu.getBotonCarrera())){
            VistaCarrera vistaCarrera = new VistaCarrera();
            ModeloCarrera modeloCarrera = new ModeloCarrera();
            ControladorCarrera controladorCarrera = new ControladorCarrera(modeloCarrera, vistaCarrera);
            this.vistaMenu.dispose();
        
        
        }
        else if (e.getSource().equals(vistaMenu.getBotonInscripcion())){
            VistaInscripcion vistaInscripcion = new VistaInscripcion();
            ModeloInscripcion modeloInscripcion = new ModeloInscripcion();
            ControladorInscripcion controladorInscripcion = new ControladorInscripcion(vistaInscripcion, modeloInscripcion);
            this.vistaMenu.dispose();
        
        
        }
         else if (e.getSource().equals(vistaMenu.getBotonNotas())){
            VistaCursado vistaCursado = new VistaCursado();
            ModeloCursado modeloCursado = new ModeloCursado();
            ControladorCursado controladorCursado = new ControladorCursado(vistaCursado, modeloCursado);
            this.vistaMenu.dispose();
        
        
        }
        else if (e.getSource().equals(vistaMenu.getBotonMateria())){
            VistaMateria vistaMateria = new VistaMateria();
            ModeloMateria modeloMateria = new ModeloMateria();
            ControladorMateria controladoMateria = new ControladorMateria(vistaMateria, modeloMateria);
            this.vistaMenu.dispose();
        
        
    }   else if (e.getSource().equals(vistaMenu.getBotonProfesor())){
            VistaProfesor vistaProfesor = new VistaProfesor();
            ModeloProfesor modeloProfesor = new ModeloProfesor();
            ControladorProfesor controladoProfesor = new ControladorProfesor(vistaProfesor, modeloProfesor);
            this.vistaMenu.dispose();
        
        
    }}}
        

   

   
