package es.studium.PracticaT2;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ConsultaArticulo extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	 private JList<String> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaArticulo frame = new ConsultaArticulo();
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
	public ConsultaArticulo() {
		addWindowListener(this);
		setTitle("Consulta Artículo");
		setBounds(100, 100, 318, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList<>();
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(22, 11, 258, 122);
		contentPane.add(scrollPane);
		// Llamar al método para cargar los artículos
        cargarArticulos();
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(103, 144, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}});
		contentPane.add(btnVolver);
	}

	// Método para cargar los artículos en el JList
    private void cargarArticulos() {
        // Obtener los artículos desde la base de datos
        java.util.List<String> articulos = BaseDatos.getArticulos();

        // Verificar que la lista no está vacía
        if (articulos.isEmpty()) {
            list.setListData(new String[] { "No hay artículos disponibles" });
        } else {
            // Convertir la lista de artículos en un array y asignarlo al JList
            list.setListData(articulos.toArray(new String[0]));
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
