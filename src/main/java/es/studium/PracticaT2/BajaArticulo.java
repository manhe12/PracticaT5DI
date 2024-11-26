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

public class BajaArticulo extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BajaArticulo frame = new BajaArticulo();
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
	public BajaArticulo() {
		addWindowListener(this);
		setTitle("Baja Artículo");
		setBounds(100, 100, 320, 202);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(82, 66, 148, 22);
		contentPane.add(comboBox);
		
		// Cargar artículos en el comboBox
		BaseDatos.cargarArticulos(comboBox);
		
		JLabel lblChoice = new JLabel("Elige un Artículo");
		lblChoice.setBounds(116, 41, 76, 14);
		contentPane.add(lblChoice);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(45, 116, 89, 23);
		// Acción para el botón Eliminar
		btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarArticuloSeleccionado(); // Llamar al método de eliminación
            }
        });
		contentPane.add(btnEliminar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}});
		btnVolver.setBounds(181, 116, 89, 23);
		contentPane.add(btnVolver);
		
		
	}
	//Método para eliminar un artículo seleccionado del comboBox.
	private void eliminarArticuloSeleccionado() {
        String itemSeleccionado = (String) comboBox.getSelectedItem();
        if (itemSeleccionado == null || itemSeleccionado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un artículo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idArticulo = Integer.parseInt(itemSeleccionado.split(" - ")[0]);

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este artículo?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean resultado = BaseDatos.eliminarArticulo(idArticulo);
            if (resultado) {
                JOptionPane.showMessageDialog(this, "Artículo eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                BaseDatos.cargarArticulos(comboBox); // Actualizar el comboBox tras eliminar
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el artículo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
