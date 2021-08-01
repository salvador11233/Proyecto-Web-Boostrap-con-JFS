package mx.edu.itspa.dto;

public class Compra {
	private Integer id_compra = null;
	private Integer cantidad;
	private Double total;
	private String fecha_compra;
	private Integer fm;
	
	public Integer getId_compra() {
		return id_compra;
	}
	
	public void setId_compra(Integer id_compra) {
		this.id_compra = id_compra;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public String getFecha_compra() {
		return fecha_compra;
	}
	
	public void setFecha_compra(String fecha_compra) {
		this.fecha_compra = fecha_compra;
	}
	
	public Integer getFm() {
		return fm;
	}
	
	public void setFm(Integer fm) {
		this.fm = fm;
	}
	
}
