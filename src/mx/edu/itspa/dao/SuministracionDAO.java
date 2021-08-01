package mx.edu.itspa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.edu.itspa.dto.Suministracion;
import mx.edu.itspa.general.ConexionBD;
import mx.edu.itspa.general.DAO;
import mx.edu.itspa.general.DAOException;

public class SuministracionDAO implements DAO<Suministracion, String>{
	
	private final String INSERT = "INSERT INTO sumistracion(num_alta_funkos, fecha_alta, precio_unidad) VALUES (?,?,?)";
    private final String UPDATE = "UPDATE sumistracion SET num_alta_funkos = ?, fecha_alta = ?, precio_unidad = ? WHERE id_sum = ?";
    private final String DELETE = "DELETE FROM sumistracion WHERE id_sum = ?";
    private final String GETALL = "SELECT * FROM sumistracion";
    private final String GETONE = "SELECT * FROM sumistracion WHERE id_sum = ?";
    


    public Integer insertar(Suministracion obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer clave = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, obj.getNum_alta_funkos());
            stmt.setString(2, obj.getFecha_alta());
            stmt.setDouble(3, obj.getPrecio_unidad());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();            
            if (rs.next()) {
                clave = rs.getInt(1);
                obj.setId_sum(clave);                
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

    public boolean modificar(Suministracion obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setInt(1, obj.getNum_alta_funkos());
            stmt.setString(2, obj.getFecha_alta());
            stmt.setDouble(3, obj.getPrecio_unidad());
            stmt.setInt(4, obj.getId_sum());
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

    public boolean eliminar(Suministracion obj) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, obj.getId_sum());
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
    
    public List<Suministracion> obtenerTodos() throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Suministracion> sumistracion = new ArrayList<Suministracion>();
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	sumistracion.add((Suministracion) convertir(rs, new Suministracion()));
            }
            return sumistracion;
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

    public List<Suministracion> obtenerTodos(String campos[]) throws DAOException {
        int numero_campos;
        String select;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Suministracion> sumistracion = new ArrayList<Suministracion>();
        try {
            numero_campos = campos.length;
            select = prepararSelect(campos, numero_campos);
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(select);
            rs = stmt.executeQuery();
            while (rs.next()) {
            	sumistracion.add((Suministracion) convertir(rs, new Suministracion(), campos));
            }
            return sumistracion;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Suministracion obtener(String id) throws DAOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Suministracion sumistracion = null;
        try {
            conn = ConexionBD.obtenerConexion();
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
            	sumistracion = (Suministracion) convertir(rs, new Suministracion());
            } else {
                System.out.println("No se encontro el libro en la base de datos");
            }
            return sumistracion;
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | DAOException ex) {
            System.out.println("Error causado por: " + ex.getMessage());
            return null;
        } finally {
            cerrarConnection(conn);
            cerrarResultSet(rs);
            cerrarStatement(stmt);
        }
    }

    public Suministracion obtenerModelo() {
        return new Suministracion();
    }
    
    private String prepararSelect(String campos[], int numero_campos){
        String select = "SELECT ";
        for (int i = 0; i < numero_campos - 1; i++) {
            select = select.concat(campos[i]) + ", ";
        }
        select = select.concat(campos[numero_campos - 1]);
        select = select.concat(" FROM sumistracion;");
        return select;
    }
}
