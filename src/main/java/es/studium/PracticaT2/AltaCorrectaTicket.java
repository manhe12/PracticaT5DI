package es.studium.PracticaT2;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class AltaCorrectaTicket extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaCorrectaTicket dialog = new AltaCorrectaTicket();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaCorrectaTicket() {
		setTitle("Alta Correcta");
		setBounds(100, 100, 322, 132);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAltaCorrecta = new JLabel("Alta realizada correctamente");
			lblAltaCorrecta.setBounds(68, 39, 169, 14);
			contentPanel.add(lblAltaCorrecta);
		}
	}

}
