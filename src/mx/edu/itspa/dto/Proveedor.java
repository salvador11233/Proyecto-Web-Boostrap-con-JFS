package mx.edu.itspa.dto;

public class Proveedor {
	private Integer id_proveedor = null;
	private String nombre_empresa;
	private String rfc;
	private Integer cp;
	private String calle;
	private String colonia;
	
	public Integer getId_proveedor() {
		return id_proveedor;
	}
	
	public void setId_proveedor(Integer id_proveedor) {
		this.id_proveedor = id_proveedor;
	}
	
	public String getNombre_empresa() {
		return nombre_empresa;
	}
	
	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}
	
	public String getRfc() {
		return rfc;
	}
	
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	public Integer getCp() {
		return cp;
	}
	
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	
	public String getCalle() {
		return calle;
	}
	
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	public String getColonia() {
		return colonia;
	}
	
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}	
}
