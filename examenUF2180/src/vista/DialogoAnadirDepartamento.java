package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import modelo.Centro;
import modelo.Departamento;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DialogoAnadirDepartamento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodDepartamento;
	private JTextField txtNombre;
	private Controlador controlador;
	private JTextField txtCodCentro;
	private final ButtonGroup buttonGroupTipoDir = new ButtonGroup();
	private JSpinner spinner;

	public DialogoAnadirDepartamento() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Detalles del departamento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			((TitledBorder)  panel.getBorder()).setTitleFont(new Font("Tahoma", Font.PLAIN, 14));
			contentPanel.add(panel, "cell 0 0,grow");
			panel.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][]"));
			{
				JLabel lblNewLabel_3 = new JLabel("");
				lblNewLabel_3.setIcon(new ImageIcon(DialogoAnadirDepartamento.class.getResource("/images/editar32.png")));
				panel.add(lblNewLabel_3, "cell 0 0 1 5");
			}
			{
				JLabel lblCodDpto = new JLabel("Cod. Dpto:");
				panel.add(lblCodDpto, "cell 1 0,alignx trailing");
				lblCodDpto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtCodDepartamento = new JTextField();
				panel.add(txtCodDepartamento, "cell 2 0 2 1,growx");
				txtCodDepartamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtCodDepartamento.setColumns(10);
			}
			{
				JLabel lblCodCentro = new JLabel("Cod Centro:");
				lblCodCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel.add(lblCodCentro, "cell 1 1,alignx trailing");
			}
			{
				txtCodCentro = new JTextField();
				txtCodCentro.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtCodCentro.setColumns(10);
				panel.add(txtCodCentro, "cell 2 1 2 1,growx");
			}
			{
				JLabel lblTipoDir = new JLabel("Tipo Dirección:");
				panel.add(lblTipoDir, "cell 1 2,alignx trailing");
				lblTipoDir.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				JRadioButton rdbtnPropiedad = new JRadioButton("Propiedad");
				rdbtnPropiedad.setSelected(true);
				buttonGroupTipoDir.add(rdbtnPropiedad);
				rdbtnPropiedad.setActionCommand("p");
				panel.add(rdbtnPropiedad, "cell 2 2");
			}
			{
				JRadioButton rdbtnFunciones = new JRadioButton("En funciones");
				buttonGroupTipoDir.add(rdbtnFunciones);
				rdbtnFunciones.setActionCommand("f");
				panel.add(rdbtnFunciones, "cell 3 2");
			}
			{
				JLabel lblPresupuesto = new JLabel("Presupuesto:");
				lblPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panel.add(lblPresupuesto, "cell 1 3,alignx trailing");
			}
			{
				spinner = new JSpinner();
				spinner.setModel(new SpinnerNumberModel(5, 1, 100, 1));
				panel.add(spinner, "cell 2 3");
			}
			{
				JLabel lblSpinnerPresupuesto = new JLabel("(en miles de €)");
				panel.add(lblSpinnerPresupuesto, "cell 3 3");
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				panel.add(lblNombre, "cell 1 4,alignx trailing");
				lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			}
			{
				txtNombre = new JTextField();
				panel.add(txtNombre, "cell 2 4 2 1,growx");
				txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtNombre.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						recogerDatos();
					}
				});
				btnOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}


	protected void recogerDatos() {
	try {
		int codDepartamento = Integer.parseInt(txtCodDepartamento.getText());
		int codCentro = Integer.parseInt(txtCodCentro.getText());
		String tipoDir = buttonGroupTipoDir.getSelection().getActionCommand();
		int presupuesto = (int) spinner.getValue();
		String nombre = txtNombre.getText();
			
		Departamento dpto = new Departamento(codDepartamento, codCentro, tipoDir, presupuesto, nombre);
		controlador.insertarDepartamento(dpto);
		
		if (
				//tipoDir == null || tipoDir.isBlank() 
			 nombre==null || nombre.isBlank()){
			JOptionPane.showMessageDialog(this, "Hay datos sin introducir. Por favor, introduzca todos los datos requeridos.", "Faltan datos", JOptionPane.ERROR_MESSAGE);
			return;}
			
		} 
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Hay datos sin introducir. Por favor, introduzca los datos numéricos.", "Faltan datos", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void setControlador(Controlador controlador) {
		this.controlador=controlador;
	}

}
