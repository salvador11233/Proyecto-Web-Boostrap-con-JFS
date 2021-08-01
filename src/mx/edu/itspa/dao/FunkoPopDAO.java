package mx.edu.itspa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import mx.edu.itspa.dto.FunkoPop;
import mx.edu.itspa.general.ConexionBD;
import mx.edu.itspa.general.DAO;
import mx.edu.itspa.general.DAOException;

public class FunkoPopDAO implements DAO<FunkoPop, String>{

	private final String INSERT = "INSERT INTO funko_pop(nombre, descripcion, img, edicion, precio, fecha_elaboracion, categoria, tipo) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE funko_pop SET nombre = ?, descripcion = ?, img = ?, edicion = ?, precio = ?, fecha_elaboracion = ?, categoria = ?, tipo = ? WHERE fm = ?";
    private final String DELETE = "DELETE FROM funko_pop WHERE fm = ?";
    private final String GETALL = "SELECT * FROM funko_pop";
    private final String GETONE = "SELECT * FROM funko_pop WHERE fm = ?";
    


    public Integer insertar(FunkoPop obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getDescripcion());
            stmt.setString(3, obj.getImg());
            stmt.setString(4, obj.getEdicion());
            stmt.setDouble(5, obj.getPrecio());
            stmt.setString(6, obj.getFecha_elaboracion());
            stmt.setString(7, obj.getCategoria());
            stmt.setString(8, obj.getTipo());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setFm(clave);                
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

    public boolean modificar(FunkoPop obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getDescripcion());
            stmt.setString(3, obj.getImg());
            stmt.setString(4, obj.getEdicion());
            stmt.setDouble(5, obj.getPrecio());
            stmt.setString(6, obj.getFecha_elaboracion());
            stmt.setString(7, obj.getCategoria());
            stmt.setString(8, obj.getTipo());
            stmt.setInt(9, obj.getFm());
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

    public boolean eliminar(FunkoPop obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, obj.getFm());
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
    
    public List<FunkoPop> obtenerTodos() throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FunkoPop> funko = new ArrayList<FunkoPop>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	funko.add((FunkoPop) convertir(rs, new FunkoPop()));
            }
            return funko;
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

    public List<FunkoPop> obtenerTodos(String campos[]) throws DAOException {
        int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<FunkoPop> funko = new ArrayList<FunkoPop>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	funko.add((FunkoPop) convertir(rs, new FunkoPop(), campos));
            }
            return funko;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public FunkoPop obtener(String id) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        FunkoPop funko = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	funko = (FunkoPop) convertir(rs, new FunkoPop());
            } else {
                System.out.println("No se encontro el libro en la base de datos");
            }
            return funko;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public FunkoPop obtenerModelo() {
        return new FunkoPop();
    }
    
    private String prepararSelect(String campos[], int numero_campos){
        String select = "SELECT ";
        for (int i = 0; i < numero_campos - 1; i++) {
            select = select.concat(campos[i]) + ", ";
        }
        select = select.concat(campos[numero_campos - 1]);
        select = select.concat(" FROM funko_pop;");
        return select;
    }
}
