package mx.edu.itspa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itspa.dto.Usuario;
import mx.edu.itspa.general.ConexionBD;
import mx.edu.itspa.general.DAO;
import mx.edu.itspa.general.DAOException;

public class UsuarioDAO implements DAO<Usuario, String> {

	private final String INSERT = "INSERT INTO usuario(email, contrasena, cp, calle, colonia, ap, am, nombre) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE usuario SET email = ?, contrasena = ?, cp = ?, calle = ?, colonia = ?, ap = ?, am = ?, nombre = ? WHERE id_usuario = ?";
    private final String DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    private final String GETALL = "SELECT * FROM usuario";
    private final String GETONE = "SELECT * FROM usuario WHERE id_usuario = ?";
    


    public Integer insertar(Usuario obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getEmail());
            stmt.setString(2, obj.getContrasena());
            stmt.setInt(3, obj.getCp());
            stmt.setString(4, obj.getCalle());
            stmt.setString(5, obj.getColonia());
            stmt.setString(6, obj.getAp());
            stmt.setString(7, obj.getAm());
            stmt.setString(8, obj.getNombre());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setId_usuario(clave);                
            }
            return clave;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }    

    public boolean modificar(Usuario obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, obj.getEmail());
            stmt.setString(2, obj.getContrasena());
            stmt.setInt(3, obj.getCp());
            stmt.setString(4, obj.getCalle());
            stmt.setString(5, obj.getColonia());
            stmt.setString(6, obj.getAp());
            stmt.setString(7, obj.getAm());
            stmt.setString(8, obj.getNombre());
            stmt.setInt(9, obj.getId_usuario());
            return stmt.executeUpdate() == 0;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return false;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public boolean eliminar(Usuario obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, obj.getId_usuario());
            return stmt.executeUpdate() == 0;
        } catch (SQLException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return false;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }
    
    public List<Usuario> obtenerTodos() throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuario = new ArrayList<Usuario>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	usuario.add((Usuario) convertir(rs, new Usuario()));
            }
            return usuario;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        } finally {
        	//alumnos.stream().forEach((e)->{System.out.println(e.getId());});
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public List<Usuario> obtenerTodos(String campos[]) throws DAOException {
        int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuario = new ArrayList<Usuario>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	usuario.add((Usuario) convertir(rs, new Usuario(), campos));
            }
            return usuario;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Usuario obtener(String id) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	usuario = (Usuario) convertir(rs, new Usuario());
            } else {
                System.out.println("No se encontro el libro en la base de datos");
            }
            return usuario;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Usuario obtenerModelo() {
        return new Usuario();
    }
    
    private String prepararSelect(String campos[], int numero_campos){
        String select = "SELECT ";
        for (int i = 0; i < numero_campos - 1; i++) {
            select = select.concat(campos[i]) + ", ";
        }
        select = select.concat(campos[numero_campos - 1]);
        select = select.concat(" FROM usuario;");
        return select;
    }
}
