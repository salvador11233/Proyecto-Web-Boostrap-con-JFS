package mx.edu.itspa.bo;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mx.edu.itspa.dao.UsuarioDAO;
import mx.edu.itspa.dto.Usuario;
import mx.edu.itspa.general.DAOException;

@ManagedBean(name="usuarioBO")
@SessionScoped
public class UsuarioBo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO;
	
	public UsuarioBo() {
		usuario = new Usuario();
		usuarioDAO = new UsuarioDAO();
	}
	
	public List<Usuario> getUsuarios(){
		try {
			return usuarioDAO.obtenerTodos();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String edit(String codigo) {
		try {
			usuario = usuarioDAO.obtener(codigo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entro a edit");
		
		return "anadir_usuarios";
	}
	
	public String save() {
		try {
			if(usuario.getId_usuario() == null || usuario.getId_usuario()==0) {
				System.out.println("Entro a insertar");
				usuarioDAO.insertar(usuario);
			}else {
				System.out.println("Entro a modi");
				usuarioDAO.modificar(usuario);
			}
			usuario = new Usuario();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "usuarios";
	}
	
	public String eliminar(String codigo) {
		try {
			usuario = usuarioDAO.obtener(codigo);
			usuarioDAO.eliminar(usuario);
			usuario = new Usuario();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return "usuarios";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
