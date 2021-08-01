package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.MovimientoDAO;
import mx.edu.itspa.dto.Movimiento;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="movimientoBO")
@SessionScoped
public class MovimientoBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Movimiento movimiento;
	private MovimientoDAO movimientoDAO;
	
	public MovimientoBO() {
		movimiento = new Movimiento();
		movimientoDAO = new MovimientoDAO();
	}
	
	public List<Movimiento> getMovimientos(){
		try {
			return movimientoDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
}
