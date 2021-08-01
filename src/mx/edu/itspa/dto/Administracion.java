package mx.edu.itspa.dto;

public class Administracion {
	private Integer id_admin = null;
	private String email;
	private String contrasena;
	private String ap;
	private String am;
	private String nombre;
	private String rol;
	
	public Integer getId_admin() {
		return id_admin;
	}
	
	public void setId_admin(Integer id_admin) {
		this.id_admin = id_admin;
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
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
