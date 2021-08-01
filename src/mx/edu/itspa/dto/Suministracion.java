package mx.edu.itspa.dto;

public class Suministracion {
	private Integer id_sum = null;
	private Integer num_alta_funkos;
	private String fecha_alta;
	private Double precio_unidad;
	
	public Integer getId_sum() {
		return id_sum;
	}
	
	public void setId_sum(Integer id_sum) {
		this.id_sum = id_sum;
	}
	
	public Integer getNum_alta_funkos() {
		return num_alta_funkos;
	}
	
	public void setNum_alta_funkos(Integer num_alta_funkos) {
		this.num_alta_funkos = num_alta_funkos;
	}
	
	public String getFecha_alta() {
		return fecha_alta;
	}
	
	public void setFecha_alta(String fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	
	public Double getPrecio_unidad() {
		return precio_unidad;
	}
	
	public void setPrecio_unidad(Double precio_unidad) {
		this.precio_unidad = precio_unidad;
	}
	
}
