package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Departamento;

public class DepartamentoDAO {

	private ConexionBD conexion;
	
    public DepartamentoDAO() {
        this.conexion = new ConexionBD();
    }

    public ArrayList<Departamento> obtenerTodosDepartamentos() {
		Connection con = conexion.getConexion();
		Statement consulta = null; 
		ResultSet resultado = null; 
		ArrayList<Departamento> lista = new ArrayList<Departamento>();
		
		try {
			consulta = con.createStatement();
			resultado = consulta.executeQuery("select * from departamentos");
			
			while(resultado.next()) {
				int codDepartamento = resultado.getInt("cod_departamento");
				int codCentro = resultado.getInt("cod_centro");
				String tipoDir = resultado.getString("tipo_dir");
				int presupuesto = resultado.getInt("presupuesto");
				String nombre = resultado.getString("nombre");
				
				Departamento dpto = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);
				
				lista.add(dpto);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close(); 
				conexion.desconectar(); 
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return lista;
    }


    public Departamento obtenerUnDepartamento(int codDepartamento) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		ResultSet resultado = null;
		Departamento dpto = null;
		
		try {
			consulta = con.prepareStatement("select * from departamentos "
					+ "where cod_departamento = ?");
			consulta.setInt(1, codDepartamento);
			resultado = consulta.executeQuery();
			
			if (resultado.next()) {	
				int codCentro = resultado.getInt("cod_centro");
				String tipoDir = resultado.getString("tipo_dir");
				int presupuesto = resultado.getInt("presupuesto");
				String nombre = resultado.getString("nombre");
				
				dpto = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, 				nombre);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				resultado.close();
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return dpto;
    }

    public int insertarDepartamento(Departamento dpto) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("INSERT INTO departamentos (cod_departamento, cod_centro, tipo_dir, presupuesto, nombre)"
					+ " VALUES (?,?,?,?,?) ");
			
			consulta.setInt(1, dpto.getCodDepartamento());
			consulta.setInt(2, dpto.getCodCentro());
			consulta.setString(3, dpto.getTipoDir());
			consulta.setInt(4, dpto.getPresupuesto());
			consulta.setString(5, dpto.getNombre());
			
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la consulta: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }
    
    public int actualizarDepartamento(Departamento dpto) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("UPDATE departamentos\n"
					+ "SET cod_centro = ?\n, tipo_dir = ?\n, presupuesto = ?\n, "
					+ "nombre = ?\n"
					+ "WHERE cod_departamento = ?;");
			
			consulta.setInt(1, dpto.getCodCentro());
			consulta.setString(2, dpto.getTipoDir());
			consulta.setInt(3, dpto.getPresupuesto());
			consulta.setString(4, dpto.getNombre());
			consulta.setInt(5, dpto.getCodDepartamento());
			resultado=consulta.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al realizar la actualizacion: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }


    public int eliminarDepartamento(int codDepartamento) {
		Connection con = conexion.getConexion();
		PreparedStatement consulta = null;
		int resultado=0;
		
		try {
			consulta = con.prepareStatement("DELETE FROM departamentos \n"
					+ "WHERE cod_departamento = ?");
			
			consulta.setInt(1, codDepartamento);
			resultado=consulta.executeUpdate(); 
			
		} catch (SQLException e) {
			System.out.println("Error al realizar el borrado: "+e.getMessage());
		} finally {
			try {
				consulta.close();
				conexion.desconectar();
			} catch (SQLException e) {
				System.out.println("Error al liberar recursos: "+e.getMessage());
			} catch (Exception e) {
				
			}
		}
		return resultado;
    }

}
