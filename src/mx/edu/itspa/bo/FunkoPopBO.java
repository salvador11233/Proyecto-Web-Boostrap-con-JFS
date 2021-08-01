package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.FunkoPopDAO;
import mx.edu.itspa.dto.FunkoPop;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="funkopopBO")
@SessionScoped
public class FunkoPopBO implements Serializable  {

	private static final long serialVersionUID = 1L;
	private FunkoPop funkopop;
	private FunkoPopDAO funkopopDAO;
	
	public FunkoPopBO() {
		funkopop = new FunkoPop();
		funkopopDAO = new FunkoPopDAO();
	}
	
	public List<FunkoPop> getFunkopops(){
		try {
			return funkopopDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String codigo) {
		try {
			funkopop = funkopopDAO.obtener(codigo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entro a edit");
		
		return "anadir_contenido";
	}
	
	public String save() {
		try {
			if(funkopop.getFm() == null || funkopop.getFm()==0) {
				System.out.println("Entro a insertar");
				funkopopDAO.insertar(funkopop);
			}else {
				System.out.println("Entro a modi");
				funkopopDAO.modificar(funkopop);
			}
			funkopop = new FunkoPop();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "alta_contenido";
	}
	
	public String eliminar(String codigo) {
		try {
			funkopop = funkopopDAO.obtener(codigo);
			funkopopDAO.eliminar(funkopop);
			funkopop = new FunkoPop();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "alta_contenido";
	}

	public FunkoPop getFunkoPop() {
		return funkopop;
	}

	public void setFunkoPop(FunkoPop funkopop) {
		this.funkopop = funkopop;
	}	
	
}
