package mx.edu.itspa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itspa.dto.Proveedor;
import mx.edu.itspa.general.ConexionBD;
import mx.edu.itspa.general.DAO;
import mx.edu.itspa.general.DAOException;

public class ProvedorDAO implements DAO<Proveedor, String> {
	
	private final String INSERT = "INSERT INTO proveedor(nombre_empresa, rfc, cp, calle, colonia) VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE proveedor SET nombre_empresa = ?, rfc = ?, cp = ?, calle = ?, colonia = ? WHERE id_proveedor = ?";
    private final String DELETE = "DELETE FROM proveedor WHERE id_proveedor = ?";
    private final String GETALL = "SELECT * FROM proveedor";
    private final String GETONE = "SELECT * FROM proveedor WHERE id_proveedor = ?";
    


    public Integer insertar(Proveedor obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNombre_empresa());
            stmt.setString(2, obj.getRfc());
            stmt.setInt(3, obj.getCp());
            stmt.setString(4, obj.getCalle());
            stmt.setString(5, obj.getColonia());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setId_proveedor(clave);                
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

    public boolean modificar(Proveedor obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, obj.getNombre_empresa());
            stmt.setString(2, obj.getRfc());
            stmt.setInt(3, obj.getCp());
            stmt.setString(4, obj.getCalle());
            stmt.setString(5, obj.getColonia());
            stmt.setInt(6, obj.getId_proveedor());
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

    public boolean eliminar(Proveedor obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, obj.getId_proveedor());
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
    
    public List<Proveedor> obtenerTodos() throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Proveedor> proveedor = new ArrayList<Proveedor>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	proveedor.add((Proveedor) convertir(rs, new Proveedor()));
            }
            return proveedor;
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

    public List<Proveedor> obtenerTodos(String campos[]) throws DAOException {
        int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Proveedor> proveedor = new ArrayList<Proveedor>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	proveedor.add((Proveedor) convertir(rs, new Proveedor(), campos));
            }
            return proveedor;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Proveedor obtener(String id) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Proveedor proveedor = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	proveedor = (Proveedor) convertir(rs, new Proveedor());
            } else {
                System.out.println("No se encontro el libro en la base de datos");
            }
            return proveedor;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Proveedor obtenerModelo() {
        return new Proveedor();
    }
    
    private String prepararSelect(String campos[], int numero_campos){
        String select = "SELECT ";
        for (int i = 0; i < numero_campos - 1; i++) {
            select = select.concat(campos[i]) + ", ";
        }
        select = select.concat(campos[numero_campos - 1]);
        select = select.concat(" FROM proveedor;");
        return select;
    }
}
