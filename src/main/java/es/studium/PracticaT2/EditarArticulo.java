package es.studium.PracticaT2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EditarArticulo extends JFrame implements WindowListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIdArticulo;   // Campo para el ID
    private JTextField txtDescipcion; // Campo para la descripción
    private JTextField txtPrecio; // Campo para el precio
    private JTextField txtCantidad; // Campo para la cantidad


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditarArticulo frame = new EditarArticulo(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditarArticulo(int idArticulo) {
		addWindowListener(this);
		setTitle("Editar Artículo");
		setBounds(100, 100, 247, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblidArticulo = new JLabel("idArticulo");
		lblidArticulo.setBounds(79, 11, 65, 14);
		contentPane.add(lblidArticulo);
		
		txtIdArticulo = new JTextField();
		txtIdArticulo.setEditable(false);
		txtIdArticulo.setBounds(40, 36, 145, 20);
		contentPane.add(txtIdArticulo);
		txtIdArticulo.setColumns(10);
		
		txtDescipcion = new JTextField();
		txtDescipcion.setBounds(40, 92, 145, 20);
		txtDescipcion.setColumns(10);
		contentPane.add(txtDescipcion);
		
		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(68, 67, 78, 14);
		contentPane.add(lblDescripcion);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(40, 147, 145, 20);
		txtPrecio.setColumns(10);
		contentPane.add(txtPrecio);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(87, 122, 53, 14);
		contentPane.add(lblPrecio);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(40, 203, 145, 20);
		txtCantidad.setColumns(10);
		contentPane.add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(79, 178, 59, 14);
		contentPane.add(lblCantidad);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 251, 89, 23);
		// Acción para aceptar los cambios
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados por el usuario
                String descripcion = txtDescipcion.getText();
                float precio = Float.parseFloat(txtPrecio.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());

                // Llamar al método para actualizar el artículo
                boolean actualizado = BaseDatos.actualizarArticulo(idArticulo, descripcion, precio, cantidad);

                if (actualizado) {
                    // Mostrar un mensaje si la actualización fue exitosa
		            JOptionPane.showMessageDialog(null, "Artículo modificado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Mostrar un mensaje si la actualización falló
                    System.out.println("Error al actualizar el artículo.");
                }
            }
        });
		contentPane.add(btnAceptar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(132, 251, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificacionArticulo mod = new ModificacionArticulo();
				setVisible(false);
				mod.setVisible(true);
				mod.setLocationRelativeTo(null);
			}});
		contentPane.add(btnVolver);
		
		// Llenar los campos con los datos del artículo
        cargarArticulo(idArticulo);
	}

	 // Método para cargar los datos del artículo en los campos de texto
    private void cargarArticulo(int idArticulo) {
        // Obtener los datos del artículo desde la base de datos
        Object[] articulo = BaseDatos.obtenerArticuloPorId(idArticulo);
        if (articulo != null) {
            // Los datos vienen en el array: [idArticulo, descripcion, precio, cantidad]
            txtIdArticulo.setText(String.valueOf(articulo[0])); // idArticulo
            txtDescipcion.setText((String) articulo[1]); // descripcionArticulo
            txtPrecio.setText(String.valueOf(articulo[2])); // precioArticulo
            txtCantidad.setText(String.valueOf(articulo[3])); // cantidadArticulo
        }
    }
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}