package es.studium.PracticaT2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BaseDatos {
	
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	// Metodo para la conexion con la BD
	static Connection getConexion() {
        try {
            String url = "jdbc:mysql://localhost/tiendecitamabc";
            String usuario = "root";
            String clave = "Studium2023;";
            return DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	// Metodo para dar de alta articulos
	public static int crearArticulo(String descripcionArticulo, String precioArticulo, String cantidadArticulo) {
	    String sentencia = "INSERT INTO articulos (idArticulo, descripcionArticulo, precioArticulo, cantidadArticulo) " +
	                       "VALUES (null, '" + descripcionArticulo + "', '" + precioArticulo + "', '" + cantidadArticulo + "');";
	    try (Connection conexion = getConexion();
	         Statement statement = conexion.createStatement()) {

	        System.out.println("Intentando ejecutar: " + sentencia); // Depuración
	        statement.executeUpdate(sentencia, Statement.RETURN_GENERATED_KEYS);

	        ResultSet rs = statement.getGeneratedKeys();
	        if (rs.next()) {
	            int id = rs.getInt(1);
	            System.out.println("ID generado: " + id); // Depuración
	            return id; // Devuelve el ID generado
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al insertar en la base de datos: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return -1; // Si algo falla
	}
	
	// Metodo para eliminar articulos
	public static boolean eliminarArticulo(int idArticulo) {
        String sql = "DELETE FROM articulos WHERE idArticulo = " + idArticulo;
        try (Connection conexion = getConexion();
             Statement statement = conexion.createStatement()) {

            return statement.executeUpdate(sql) > 0; // Devuelve true si se eliminó
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si algo falla
    }
	
	// Metodo para rellenar el combobox de baja articulos
	public static void cargarArticulos(JComboBox<String> comboBox) {
	    comboBox.removeAllItems(); // Limpiar el comboBox antes de cargar nuevos elementos
	    try (Connection conexion = BaseDatos.getConexion();
	         Statement statement = conexion.createStatement();
	         ResultSet rs = statement.executeQuery("SELECT idArticulo, descripcionArticulo FROM articulos")) {

	        while (rs.next()) {
	            int idArticulo = rs.getInt("idArticulo");
	            String descripcion = rs.getString("descripcionArticulo");
	            // Mostrar los artículos en la consola o en un mensaje para depurar
	            System.out.println("Artículo: " + idArticulo + " - " + descripcion); // Depuración
	            comboBox.addItem(idArticulo + " - " + descripcion); // Agregar al comboBox
	        }

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(null, "Error al cargar los artículos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        ex.printStackTrace();
	    }
	}
	
	// Método para obtener los artículos de la base de datos
    public static ArrayList<String> getArticulos() {
        ArrayList<String> articulos = new ArrayList<>();
        String sql = "SELECT descripcionArticulo, precioArticulo, cantidadArticulo FROM articulos"; // Query para obtener todos los artículos

        try (Connection conexion = getConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String descripcion = rs.getString("descripcionArticulo");
                float precio = rs.getFloat("precioArticulo");
                int cantidad = rs.getInt("cantidadArticulo");
                
                // Crear una cadena que combine la descripción, precio y cantidad
                String articuloInfo = descripcion + " - Precio: " + precio + " - Cantidad: " + cantidad; 
                
                articulos.add(articuloInfo); // Añadir la descripción a la lista
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los artículos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return articulos;
    }
    
 // Método para obtener los datos de un artículo por su idArticulo
    public static Object[] obtenerArticuloPorId(int idArticulo) {
        String sql = "SELECT idArticulo, descripcionArticulo, precioArticulo, cantidadArticulo FROM articulos WHERE idArticulo = ?";
        try (Connection conexion = getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idArticulo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("idArticulo");
                String descripcion = rs.getString("descripcionArticulo");
                float precio = rs.getFloat("precioArticulo");
                int cantidad = rs.getInt("cantidadArticulo");

                // Retorna los datos en un array de objetos
                return new Object[]{id, descripcion, precio, cantidad};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 // Método para actualizar los datos de un artículo
    public static boolean actualizarArticulo(int idArticulo, String descripcion, float precio, int cantidad) {
        String sql = "UPDATE articulos SET descripcionArticulo = ?, precioArticulo = ?, cantidadArticulo = ? WHERE idArticulo = ?";
        try (Connection conexion = getConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, descripcion);
            stmt.setFloat(2, precio);
            stmt.setInt(3, cantidad);
            stmt.setInt(4, idArticulo);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Si se actualizó al menos un registro
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Método para obtener los productos desde la base de datos
    public static ArrayList<String> getProductos() {
        ArrayList<String> productos = new ArrayList<>();
        String sql = "SELECT descripcionArticulo FROM articulos"; // Query para obtener los nombres de los productos

        try (Connection conexion = getConexion();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String descripcion = rs.getString("descripcionArticulo");
                productos.add(descripcion); // Añadir la descripción a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método para obtener el precio de un producto por su nombre
    public static double obtenerPrecioProducto(String descripcion) {
        double precio = 0;
        String sql = "SELECT precioArticulo FROM articulos WHERE descripcionArticulo = ?";

        try (Connection conexion = getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, descripcion);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                precio = rs.getDouble("precioArticulo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return precio;
    }

    // Método para guardar un ticket en la base de datos
    public static boolean guardarTicket(Date fechaTicket, double totalTicket, ArrayList<String> productos, ArrayList<Double> precios) {
        String sqlTicket = "INSERT INTO tickets (fechaTicket, totalTicket) VALUES (?, ?)";
        String sqlPertenece = "INSERT INTO pertenece (idArticuloFK, idTicketFK) VALUES (?, ?)";

        try (Connection conexion = getConexion();
             PreparedStatement stmtTicket = conexion.prepareStatement(sqlTicket, Statement.RETURN_GENERATED_KEYS)) {

            // Insertar el ticket
            stmtTicket.setDate(1, fechaTicket);
            stmtTicket.setDouble(2, totalTicket);
            int affectedRows = stmtTicket.executeUpdate();

            if (affectedRows == 0) {
                return false;  // Si no se inserta el ticket
            }

            // Obtener el ID del ticket insertado
            ResultSet generatedKeys = stmtTicket.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idTicket = generatedKeys.getInt(1);

                // Insertar las relaciones de productos con el ticket
                try (PreparedStatement stmtPertenece = conexion.prepareStatement(sqlPertenece)) {
                    for (int i = 0; i < productos.size(); i++) {
                        String descripcionProducto = productos.get(i);
                        int idArticulo = obtenerIdArticulo(descripcionProducto);
                        stmtPertenece.setInt(1, idArticulo);
                        stmtPertenece.setInt(2, idTicket);
                        stmtPertenece.addBatch();  // Agregar cada inserción a un lote
                    }
                    stmtPertenece.executeBatch();  // Ejecutar el lote
                }
                return true;  // Ticket guardado correctamente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Si algo falla
    }

    // Método auxiliar para obtener el ID del artículo por su descripción
    public static int obtenerIdArticulo(String descripcion) {
        int idArticulo = -1;
        String sql = "SELECT idArticulo FROM articulos WHERE descripcionArticulo = ?";

        try (Connection conexion = getConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, descripcion);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                idArticulo = rs.getInt("idArticulo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idArticulo;
    }
    
 // Método para obtener todos los tickets con productos
    public static ArrayList<Object[]> obtenerTickets() {
        ArrayList<Object[]> tickets = new ArrayList<>();
        String sql = "SELECT t.idTicket, t.fechaTicket, t.totalTicket, " +
                     "GROUP_CONCAT(a.descripcionArticulo) AS productos " +  // Usamos GROUP_CONCAT para obtener los productos en una sola cadena
                     "FROM tickets t " +
                     "JOIN pertenece p ON t.idTicket = p.idTicketFK " +
                     "JOIN articulos a ON p.idArticuloFK = a.idArticulo " +
                     "GROUP BY t.idTicket";

        try (Connection conexion = getConexion(); 
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int idTicket = rs.getInt("idTicket");
                String fecha = rs.getString("fechaTicket");
                double total = rs.getDouble("totalTicket");
                String productos = rs.getString("productos"); // Los productos ya están concatenados

                // Guardamos los datos en un array de objetos (en vez de crear una clase)
                tickets.add(new Object[] { idTicket, fecha, productos, total });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los tickets", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return tickets;
    }
}
