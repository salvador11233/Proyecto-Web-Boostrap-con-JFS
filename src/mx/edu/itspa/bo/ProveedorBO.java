package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.ProvedorDAO;
import mx.edu.itspa.dto.Proveedor;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="proveedorBO")
@SessionScoped
public class ProveedorBO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Proveedor proveedor;
	private ProvedorDAO proveedorDAO;
	
	public ProveedorBO() {
		proveedor = new Proveedor();
		proveedorDAO = new ProvedorDAO();
	}
	
	public List<Proveedor> getProveedores(){
		try {
			return proveedorDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String codigo) {
		try {
			proveedor = proveedorDAO.obtener(codigo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entro a edit");
		
		return "anadir_proveedores";
	}
	
	public String saves() {
		try {
		
			if(proveedor.getId_proveedor() == null || proveedor.getId_proveedor()==0) {
				System.out.println("Entro a insertar");
				proveedorDAO.insertar(proveedor);
			}else {
				System.out.println("Entro a modi");
				proveedorDAO.modificar(proveedor);
			}
			proveedor = new Proveedor();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "proveedores";
	}
	
	public String eliminar(String codigo) {
		try {
			proveedor = proveedorDAO.obtener(codigo);
			proveedorDAO.eliminar(proveedor);
			proveedor = new Proveedor();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "proveedores";
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}
