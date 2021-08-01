package mx.edu.itspa.dto;

public class Usuario {
	private Integer id_usuario = null;
	private String email;
	private String contrasena;
	private Integer cp;
	private String calle;
	private String colonia;
	private String ap;
	private String am;
	private String nombre;
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContrasena() {
		return contrasena;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
	
	public String getAp() {
		return ap;
	}
	
	public void setAp(String ap) {
		this.ap = ap;
	}
	
	public String getAm() {
		return am;
	}
	
	public void setAm(String am) {
		this.am = am;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
