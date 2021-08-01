package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.CompraDAO;
import mx.edu.itspa.dto.Compra;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="compraBO")
@SessionScoped
public class CompraBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Compra compra;
	private CompraDAO compraDAO;
	
	public CompraBO() {
		compra = new Compra();
		compraDAO = new  CompraDAO();
	}
	
	public List<Compra> getCompras(){
		try {
			return compraDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}
