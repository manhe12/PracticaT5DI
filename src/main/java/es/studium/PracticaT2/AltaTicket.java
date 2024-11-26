package es.studium.PracticaT2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AltaTicket extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JComboBox<String> comboBoxProductos;
    private JList<String> listProductos;
    private DefaultListModel<String> listModel;
    private ArrayList<String> productosSeleccionados;
    private ArrayList<Double> preciosProductos;
    private JTextField textFieldTotal;
    private JTextField textFieldFecha; // Campo de texto para la fecha

    public AltaTicket() {
        setTitle("Alta Ticket");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        productosSeleccionados = new ArrayList<>();
        preciosProductos = new ArrayList<>();

        // ComboBox para seleccionar productos
        comboBoxProductos = new JComboBox<>();
        comboBoxProductos.setBounds(40, 30, 150, 20);
        contentPane.add(comboBoxProductos);

        // Cargar los productos en el ComboBox desde la base de datos
        cargarProductos();

        // JList para mostrar productos seleccionados
        listModel = new DefaultListModel<>();
        listProductos = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listProductos);
        scrollPane.setBounds(40, 70, 200, 100);
        contentPane.add(scrollPane);

        // JTextField para mostrar el total
        textFieldTotal = new JTextField();
        textFieldTotal.setEditable(false);
        textFieldTotal.setBounds(250, 70, 100, 20);
        contentPane.add(textFieldTotal);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(250, 50, 40, 14);
        contentPane.add(lblTotal);

        // Campo de texto para ingresar la fecha
        textFieldFecha = new JTextField();
        textFieldFecha.setBounds(250, 120, 100, 20);
        contentPane.add(textFieldFecha);

        JLabel lblFecha = new JLabel("Fecha (yyyy-MM-dd):");
        lblFecha.setBounds(250, 100, 120, 14);
        contentPane.add(lblFecha);

        // Botón para agregar el producto seleccionado
        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(40, 180, 150, 23);
        contentPane.add(btnAgregar);

        // Botón para guardar el ticket
        JButton btnGuardarTicket = new JButton("Guardar Ticket");
        btnGuardarTicket.setBounds(200, 180, 150, 23);
        contentPane.add(btnGuardarTicket);

        // Acción para agregar el producto seleccionado
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productoSeleccionado = (String) comboBoxProductos.getSelectedItem();
                if (productoSeleccionado != null) {
                    double precio = BaseDatos.obtenerPrecioProducto(productoSeleccionado);
                    listModel.addElement(productoSeleccionado);
                    productosSeleccionados.add(productoSeleccionado);
                    preciosProductos.add(precio);
                    actualizarTotal();
                }
            }
        });

        // Acción para guardar el ticket
        btnGuardarTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener la fecha del campo de texto
                String fechaTexto = textFieldFecha.getText();
                java.sql.Date fechaTicket = null;

                try {
                    // Convertir la fecha de texto en formato Date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(fechaTexto);
                    fechaTicket = new java.sql.Date(date.getTime());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AltaTicket.this, "Fecha inválida. Use el formato yyyy-MM-dd.", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double totalTicket = calcularTotal();
                boolean resultado = BaseDatos.guardarTicket(fechaTicket, totalTicket, productosSeleccionados, preciosProductos);
                if (resultado) {
                    JOptionPane.showMessageDialog(AltaTicket.this, "Ticket Guardado con Éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(AltaTicket.this, "Error al guardar el ticket", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Método para cargar los productos en el ComboBox
    private void cargarProductos() {
        ArrayList<String> productos = BaseDatos.getProductos();
        for (String producto : productos) {
            comboBoxProductos.addItem(producto);
        }
    }

    // Método para actualizar el total
    private void actualizarTotal() {
        double total = calcularTotal();
        textFieldTotal.setText(String.format("%.2f", total));
    }

    // Método para calcular el total
    private double calcularTotal() {
        double total = 0;
        for (double precio : preciosProductos) {
            total += precio;
        }
        return total;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AltaTicket frame = new AltaTicket();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

