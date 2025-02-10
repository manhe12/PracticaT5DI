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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class InformeTickets extends JFrame implements WindowListener{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFechaDesde;
    private JTextField textFechaHasta;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InformeTickets frame = new InformeTickets();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InformeTickets() {
    	addWindowListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 265, 160);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textFechaDesde = new JTextField();
        textFechaDesde.setBounds(133, 27, 86, 20);
        contentPane.add(textFechaDesde);
        textFechaDesde.setColumns(10);

        JLabel lblFechaDesde = new JLabel("Fecha desde");
        lblFechaDesde.setBounds(36, 30, 87, 14);
        contentPane.add(lblFechaDesde);

        textFechaHasta = new JTextField();
        textFechaHasta.setBounds(133, 58, 86, 20);
        contentPane.add(textFechaHasta);
        textFechaHasta.setColumns(10);

        JLabel lblFechaHasta = new JLabel("Fecha hasta");
        lblFechaHasta.setBounds(36, 61, 87, 14);
        contentPane.add(lblFechaHasta);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(36, 87, 90, 23);
        contentPane.add(btnAceptar);

        // Acción del botón Aceptar
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		generarInforme();
        	}
        });

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(138, 87, 90, 23);
        contentPane.add(btnVolver);

        //Acción del botón Volver
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            }
        });
        
    }

    

    private void generarInforme() {
        try {
            // Formato esperado de la fecha (ajústalo si es diferente en tu aplicación)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Obtener valores de los textfields y parsear a Date
            String fechaDesdeStr = textFechaDesde.getText();
            String fechaHastaStr = textFechaHasta.getText();

            // Verificar que los campos no estén vacíos
            if (fechaDesdeStr.isEmpty() || fechaHastaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese ambas fechas.");
                return;
            }

            // Convertir las fechas de String a Date
            Date fechaDesde = dateFormat.parse(fechaDesdeStr);
            Date fechaHasta = dateFormat.parse(fechaHastaStr);

            // Parámetros del informe
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("FechaDesde", fechaDesde);
            parametros.put("FechaHasta", fechaHasta);
            
         // Cargar el informe compilado
            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(
                    "./src/main/resources/es/studium/jasper/tickets.jasper");

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
            String pdfPath = "./src/main/resources/es/studium/jasper/tickets.pdf";
            JasperExportManager.exportReportToPdfFile(print, pdfPath);

            // Abrir el archivo PDF generado
            File pdfFile = new File(pdfPath);
            Desktop.getDesktop().open(pdfFile);

            JOptionPane.showMessageDialog(this, "Informe generado correctamente.");

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use 'yyyy-MM-dd'.");
            e.printStackTrace();
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
