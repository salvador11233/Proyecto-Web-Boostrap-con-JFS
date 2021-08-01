package mx.edu.itspa.dto;

public class Movimiento {
	private Integer id_movimiento = null;
	private String fecha_actual;
	private String movimiento;
	
	public Integer getId_movimiento() {
		return id_movimiento;
	}
	
	public void setId_movimiento(Integer id_movimiento) {
		this.id_movimiento = id_movimiento;
	}
	
	public String getFecha_actual() {
		return fecha_actual;
	}
	
	public void setFecha_actual(String fecha_actual) {
		this.fecha_actual = fecha_actual;
	}
	
	public String getMovimiento() {
		return movimiento;
	}
	
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
}
