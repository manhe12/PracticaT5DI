package es.studium.PracticaT2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class ModificacionArticulo extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JButton btnEditar;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificacionArticulo frame = new ModificacionArticulo();
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
	public ModificacionArticulo() {
		addWindowListener(this);
		setTitle("Modificación Artículo");
		setBounds(100, 100, 320, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(82, 66, 148, 22);
		contentPane.add(comboBox);
		
		// Cargar los artículos
        BaseDatos.cargarArticulos(comboBox);
		
		JLabel lblChoice = new JLabel("Elige un Artículo");
		lblChoice.setBounds(108, 42, 97, 14);
		contentPane.add(lblChoice);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(37, 115, 89, 23);
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener el idArticulo seleccionado del JComboBox
		        String itemSeleccionado = (String) comboBox.getSelectedItem();
		        if (itemSeleccionado != null && !itemSeleccionado.isEmpty()) {
		            // Extraer el idArticulo del texto del JComboBox (asumimos que el formato es "id - descripcion")
		            int idArticulo = Integer.parseInt(itemSeleccionado.split(" - ")[0]);

		            // Cerrar la ventana actual
		            setVisible(false);

		            // Crear la instancia de EditarArticulo y pasarle el idArticulo
		            EditarArticulo editar = new EditarArticulo(idArticulo);
		            editar.setVisible(true);
		            editar.setLocationRelativeTo(null);
		        } else {
		            // Mostrar mensaje si no se selecciona un artículo
		            JOptionPane.showMessageDialog(null, "Por favor, selecciona un artículo para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        }
		    }
		});
		contentPane.add(btnEditar);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(180, 115, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}});
		contentPane.add(btnVolver);
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
