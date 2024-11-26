package es.studium.PracticaT2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class AltaArticulo extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDescripcion;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JButton btnAceptar;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaArticulo frame = new AltaArticulo();
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
	public AltaArticulo() {
		addWindowListener(this);
		setTitle("Alta Artículo");
		setBounds(100, 100, 250, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDescripcion = new JLabel("Descripción");
		lblDescripcion.setBounds(84, 26, 73, 14);
		contentPane.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(32, 51, 164, 20);
		contentPane.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(96, 84, 51, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(32, 109, 164, 20);
		contentPane.add(txtPrecio);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(86, 140, 61, 14);
		contentPane.add(lblCantidad);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(32, 164, 164, 20);
		contentPane.add(txtCantidad);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String descripcion = txtDescripcion.getText();
				String precio = txtPrecio.getText();
				String cantidad = txtCantidad.getText();

				if (descripcion.isEmpty() || precio.isEmpty() || cantidad.isEmpty()) {
					JOptionPane.showMessageDialog(AltaArticulo.this, "Por favor, rellena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
					setVisible(true);
					return;
				}

				try {
					int idGenerado = BaseDatos.crearArticulo(descripcion, precio, cantidad);
					if (idGenerado != -1) {
						JOptionPane.showMessageDialog(AltaArticulo.this, "Artículo creado con éxito. ID generado: " + idGenerado, "Éxito", JOptionPane.INFORMATION_MESSAGE);
						txtDescripcion.setText("");
						txtPrecio.setText("");
						txtCantidad.setText("");
					} else {
						JOptionPane.showMessageDialog(AltaArticulo.this, "Error al crear el artículo.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(AltaArticulo.this, "Ocurrió un error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();

				}
			}
		});
		btnAceptar.setBounds(10, 212, 89, 23);
		contentPane.add(btnAceptar);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}});
		btnVolver.setBounds(135, 212, 89, 23);
		contentPane.add(btnVolver);
	}

	@Override
	public void windowOpened(WindowEvent e) {		
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
