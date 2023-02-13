package modelo;

import java.util.Objects;

/**
 * Clase que modela un departamento. Servirá para representar un departamento
 * de la tabla departamentos.
 * 
 * @author Barbara
 *
 */

public class Departamento {
	
	private int codDepartamento;
	private int codCentro;
	private String tipoDir;
	private int presupuesto;
	private String nombre;
	
	/**
	 * Constructor de la clase que crea un Departamento inicializando sus
	 * valores a cero y las cadenas vacías.
	 */
	
	public Departamento() {
		this.codDepartamento = 0;
		this.codCentro = 0;
		this.tipoDir = "P";
		this.presupuesto = 0;
		this.nombre = "";
	}

	/**
	 * Constructor de la clase que crea un Departamento con parámetros.
	 * @param codDepartamento int con el código del departamento.
	 * @param codCentro int con el código del centro.
	 * @param tipoDir Tipo de dirección. String que solo tiene dos valores: P o 
	 * 		F, indicando P para	Propiedad y F para 'en Funciones'.
	 * @param presupuesto int con el presupuesto del departamento en miles de euros.
	 * @param nombre String con el nombre del departamento. Tamaño máximo 50 caracteres.
	 */
	public Departamento(int codDepartamento, int codCentro, String tipoDir, int presupuesto, String nombre) {
		super();
		this.codDepartamento = codDepartamento;
		this.codCentro = codCentro;
		this.tipoDir = tipoDir;
		this.presupuesto = presupuesto;
		this.nombre = nombre.substring(0,50);
	}
	/**
	 * Getter del código del departamento
	 * @return int
	 */
	public int getCodDepartamento() {
		return codDepartamento;
	}
	/**
	 * Establece el código de departamento
	 * @param codDepartamento int con el código del departamento
	 */
	public void setCodDepartamento(int codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	
	public int getCodCentro() {
		return codCentro;
	}

	public void setCodCentro(int codCentro) {
		this.codCentro = codCentro;
	}

	public String getTipoDir() {
		return tipoDir;
	}
	/**
	 * Método que devuelve la representación en String del tipo de dirección.
	 * La almacenada en la base de datos solo puede ser char(1)- P o F.
	 * @return String con los valores "En propiedad" o "En funciones".
	 */
	public String getTipoDirTexto() {
		if (this.tipoDir.equals("p")) {
			return "En propiedad";
		} else {
			return "En funciones";
		}
	}

	public void setTipoDir(String tipoDir) {
		this.tipoDir = tipoDir;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codDepartamento);
	}

	//la clave primaria es codDepartamento por lo que dos departamentos solo serán iguales si tienen el mismo codDepartamento.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return codDepartamento == other.codDepartamento;
	}

	@Override
	public String toString() {
		return "codDepartamento: " + codDepartamento + ", codCentro: " + codCentro + 
			", tipoDir: " + tipoDir	+ ", presupuesto: " + presupuesto + ", nombre: " + nombre ;
	}
}
