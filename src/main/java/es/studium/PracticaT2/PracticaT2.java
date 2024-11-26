package es.studium.PracticaT2;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class PracticaT2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PracticaT2 frame = new PracticaT2();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PracticaT2() {
		setTitle("Menú Principal");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 108, 22);
		contentPane.add(menuBar);
		
		JMenu mnArticulo = new JMenu("Artículos");
		menuBar.add(mnArticulo);
		mnArticulo.setForeground(Color.WHITE); // Cambiar color del texto
		mnArticulo.setBackground(Color.BLACK); // Cambiar fondo
		mnArticulo.setOpaque(true);
		
		JMenuItem miAltaArticulo = new JMenuItem("Alta");
		mnArticulo.add(miAltaArticulo);
		miAltaArticulo.setForeground(Color.WHITE); // Cambiar color del texto
		miAltaArticulo.setBackground(Color.BLACK); // Cambiar fondo
		miAltaArticulo.setOpaque(true);
		
		miAltaArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la nueva ventana
				AltaArticulo altaArticulo = new AltaArticulo();
				altaArticulo.setVisible(true); // Mostrar la nueva ventana
				altaArticulo.setLocationRelativeTo(null);
			}
		});
		
		JMenuItem miBajaArticulo = new JMenuItem("Baja");
		mnArticulo.add(miBajaArticulo);
		miBajaArticulo.setForeground(Color.WHITE); // Cambiar color del texto
		miBajaArticulo.setBackground(Color.BLACK); // Cambiar fondo
		miBajaArticulo.setOpaque(true);
		
		miBajaArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la nueva ventana
				BajaArticulo bajaArticulo = new BajaArticulo();
				bajaArticulo.setVisible(true); // Mostrar la nueva ventana
				bajaArticulo.setLocationRelativeTo(null);
			}
		});
		
		JMenuItem miModificacionArticulo = new JMenuItem("Modificacion");
		mnArticulo.add(miModificacionArticulo);
		miModificacionArticulo.setForeground(Color.WHITE); // Cambiar color del texto
		miModificacionArticulo.setBackground(Color.BLACK); // Cambiar fondo
		miModificacionArticulo.setOpaque(true);
		
		miModificacionArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la nueva ventana
				ModificacionArticulo modificacionArticulo = new ModificacionArticulo();
				modificacionArticulo.setVisible(true); // Mostrar la nueva ventana
				modificacionArticulo.setLocationRelativeTo(null);
			}
		});
		
		JMenuItem miConsultaArticulo = new JMenuItem("Consulta");
		mnArticulo.add(miConsultaArticulo);
		miConsultaArticulo.setForeground(Color.WHITE); // Cambiar color del texto
		miConsultaArticulo.setBackground(Color.BLACK); // Cambiar fondo
		miConsultaArticulo.setOpaque(true);
		
		miConsultaArticulo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la nueva ventana
				ConsultaArticulo consultaArticulo = new ConsultaArticulo();
				consultaArticulo.setVisible(true); // Mostrar la nueva ventana
				consultaArticulo.setLocationRelativeTo(null);
			}
		});
		
		JMenu mnTicket = new JMenu("Ticket");
		menuBar.add(mnTicket);
		mnTicket.setForeground(Color.BLACK); // Cambiar color del texto
		mnTicket.setBackground(Color.WHITE); // Cambiar fondo
		mnTicket.setOpaque(true);
		
		JMenuItem miAltaTicket = new JMenuItem("Alta");
		mnTicket.add(miAltaTicket);
		miAltaTicket.setForeground(Color.BLACK); // Cambiar color del texto
		miAltaTicket.setBackground(Color.WHITE); // Cambiar fondo
		miAltaTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la nueva ventana
				AltaTicket altaTicket = new AltaTicket();
				altaTicket.setVisible(true); // Mostrar la nueva ventana
				altaTicket.setLocationRelativeTo(null);
			}
		});
		
		JMenuItem miConsultaTicket = new JMenuItem("Consulta");
		mnTicket.add(miConsultaTicket);
		miConsultaTicket.setForeground(Color.BLACK); // Cambiar color del texto
		miConsultaTicket.setBackground(Color.WHITE); // Cambiar fondo
		miConsultaTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Crear una instancia de la nueva ventana
				ConsultaTicket consultaTicket = new ConsultaTicket();
				consultaTicket.setVisible(true); // Mostrar la nueva ventana
				consultaTicket.setLocationRelativeTo(null);
			}
		});
		
	}
}
