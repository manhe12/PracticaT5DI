package es.studium.PracticaT2;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ConsultaArticulo extends JFrame implements WindowListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	 private JList<String> list;
	 private JButton btnGenerarInforme;

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
		btnVolver.setBounds(191, 144, 89, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}});
		contentPane.add(btnVolver);
		
		btnGenerarInforme = new JButton("Generar Informe");
		btnGenerarInforme.setBounds(22, 144, 113, 23);
		contentPane.add(btnGenerarInforme);
		btnGenerarInforme.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				generarInforme();
			}
		});
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
	
    private void generarInforme() {
        try {
        	// Parámetros del informe (puede estar vacío si no necesitas filtros)
            HashMap<String, Object> parametros = new HashMap<>();
            
         // Cargar el informe compilado
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(
                    "./src/main/resources/es/studium/jasper/articulos.jasper");

            // Conectar a la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost:3306/tiendecitamabc";
            String usuarioDB = "root";
            String passwordDB = "Studium2023;";
            Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

            // Generar el informe con las fechas ingresadas
            JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

            // Mostrar el informe en JasperViewer
            JasperViewer.viewReport(print, false);

            // Exportar el informe a PDF
            String pdfPath = "./src/main/resources/es/studium/jasper/articulos.pdf";
            JasperExportManager.exportReportToPdfFile(print, pdfPath);

            // Abrir el archivo PDF generado
            File pdfFile = new File(pdfPath);
            Desktop.getDesktop().open(pdfFile);

            JOptionPane.showMessageDialog(this, "Informe generado correctamente.");

        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el informe: " + e.getMessage());
            e.printStackTrace();
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
