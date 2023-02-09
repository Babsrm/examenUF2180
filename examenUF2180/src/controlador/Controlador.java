package controlador;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import dao.CentroDAO;
import dao.DepartamentoDAO;
import modelo.Centro;
import modelo.Departamento;
import vista.DialogoAnadirCentro;
import vista.DialogoAnadirDepartamento;
import vista.VentanaMostrarCentros;
import vista.VentanaMostrarDepartamentos;
import vista.VentanaPpal;

public class Controlador {

	private VentanaPpal ventanaPpal;
	private VentanaMostrarCentros ventanaMostrarCentros;
	private DialogoAnadirCentro dialogoAnadirCentro;
	private VentanaMostrarDepartamentos ventanaMostrarDepartamentos;
	private DialogoAnadirDepartamento dialogoAnadirDepartamento;
	
	private CentroDAO centroDAO;
	private DepartamentoDAO departamentoDAO;

	
	
	public Controlador() {
		// Creamos las ventanas de la aplicaci�n
		ventanaPpal = new VentanaPpal();
		ventanaMostrarCentros = new VentanaMostrarCentros();
		ventanaMostrarDepartamentos = new VentanaMostrarDepartamentos();
		dialogoAnadirCentro = new DialogoAnadirCentro();
		dialogoAnadirDepartamento = new DialogoAnadirDepartamento();
		
		// Dando acceso al controlador desde las vistas
		ventanaPpal.setControlador(this);
		ventanaMostrarCentros.setControlador(this);
		dialogoAnadirCentro.setControlador(this);
		ventanaMostrarDepartamentos.setControlador(this);
		dialogoAnadirDepartamento.setControlador(this);

		
		// Creamos los objetos DAO
		centroDAO = new CentroDAO();
		departamentoDAO = new DepartamentoDAO();
	}
	
	public void inciarPrograma() {
		ventanaPpal.setVisible(true);
	}
	
	public void mostrarListaCentros() {
		ArrayList<Centro> lista = centroDAO.obtenerCentros();
		ventanaMostrarCentros.setListaCentros(lista);
		ventanaMostrarCentros.setVisible(true);
	}
	
	public void mostrarInsertarCentros() {
		dialogoAnadirCentro.setVisible(true);
	}

	public void insertarCentro(Centro centro) {
		int resultado = centroDAO.insertarCentro(centro);
		if (resultado ==0) {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Error. no se ha podido insertar.");
		} else {
			JOptionPane.showMessageDialog(dialogoAnadirCentro, "Insercion del centro correcta");
			dialogoAnadirCentro.setVisible(false);
		}
	}
	
	public void mostrarListaDepartamentos() {
		ArrayList<Departamento> lista = departamentoDAO.obtenerTodosDepartamentos();
		ventanaMostrarDepartamentos.setListaDepartamentos(lista);
		ventanaMostrarDepartamentos.setVisible(true);
	}
	
	public void mostrarInsertarDepartamentos() {
		dialogoAnadirDepartamento.setVisible(true);
		
	}

	public void insertarDepartamento(Departamento dpto) throws SQLException {
		
		int resultado = departamentoDAO.insertarDepartamento(dpto);
		//no me hace falta hacer el if controlador de que ha fallado la inserción porque la he lanzado a que lo controle al ventana de dialogo
			dialogoAnadirDepartamento.setVisible(false);
		}
	}
