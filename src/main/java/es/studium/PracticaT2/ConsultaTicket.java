package es.studium.PracticaT2;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultaTicket extends JFrame implements WindowListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConsultaTicket frame = new ConsultaTicket();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ConsultaTicket() {
    	addWindowListener(this);
        setTitle("Consulta de Tickets");
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Configuración de la tabla
        String[] columnNames = {"ID Ticket", "Fecha", "Productos", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 560, 300);
        contentPane.add(scrollPane);

        // Cargar los tickets y llenar la tabla
        cargarTickets();
    }

    private void cargarTickets() {
        // Obtener todos los tickets desde la base de datos
        ArrayList<Object[]> tickets = BaseDatos.obtenerTickets();

        // Agregar los tickets a la tabla
        for (Object[] ticket : tickets) {
            // ticket[0] = idTicket, ticket[1] = fecha, ticket[2] = productos, ticket[3] = total
            String fecha = (String) ticket[1];
            String productos = (String) ticket[2]; // Los productos están concatenados en una sola cadena
            String total = String.format("%.2f", ticket[3]);

            // Agregar los datos a la tabla
            tableModel.addRow(new Object[] { ticket[0], fecha, productos, total });
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