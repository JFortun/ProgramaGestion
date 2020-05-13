package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ObtenerDatosModProv extends WindowAdapter implements ActionListener
{

	ObtenerDatosModProv()
	{

		String nombreProveedor = "";
		String telefonoProveedor = "";
		String nifProveedor = "";

		try
		{
			int idProElegido = Integer.parseInt(GPI.choModificacionProveedor.getSelectedItem().split("-")[0]);
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM proveedores WHERE idProveedor="+idProElegido;
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			GPBD.rs.next();
			//Le doy a los text field los valores extraidos del elemento seleccionado
			nombreProveedor = GPBD.rs.getString("nombreProveedor");
			GPI.txtMPNombreProveedor.setText(nombreProveedor);
			telefonoProveedor = GPBD.rs.getString("telefonoProveedor");
			GPI.txtMPTelefonoProveedor.setText(telefonoProveedor);
			nifProveedor = GPBD.rs.getString("nifProveedor");
			GPI.txtMPNIFProveedor.setText(nifProveedor);

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
			try
			{
				if(GPBD.connection!=null)
				{
					GPBD.connection.close();
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error 3-"+e.getMessage());
			}
		}
		GPI.modificacionProveedorConfirmacion.setLayout(new FlowLayout());
		GPI.modificacionProveedorConfirmacion.setSize(350, 170);
		GPI.modificacionProveedorConfirmacion.setResizable(false);
		GPI.modificacionProveedorConfirmacion.addWindowListener(this);
		GPI.btnMProvConfirmar.addActionListener(this);
		GPI.btnMProvVolver.addActionListener(this);
		GPI.modificacionProveedorConfirmacion.add(GPI.lblMPNombreProveedor);
		GPI.modificacionProveedorConfirmacion.add(GPI.txtMPNombreProveedor);
		GPI.modificacionProveedorConfirmacion.add(GPI.lblMPTelefonoProveedor);
		GPI.modificacionProveedorConfirmacion.add(GPI.txtMPTelefonoProveedor);
		GPI.modificacionProveedorConfirmacion.add(GPI.lblMPNIFProveedor);
		GPI.modificacionProveedorConfirmacion.add(GPI.txtMPNIFProveedor);
		GPI.modificacionProveedorConfirmacion.add(GPI.btnMProvConfirmar);
		GPI.modificacionProveedorConfirmacion.add(GPI.btnMProvVolver);
		GPI.modificacionProveedorConfirmacion.setLocationRelativeTo(null);
		GPI.modificacionProveedorConfirmacion.setVisible(true);
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.modificacionProveedorConfirmacion.isActive())
		{
			GPI.modificacionProveedorConfirmacion.setVisible(false);
		}
	}

	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(GPI.btnMProvConfirmar))
		{
			try
			{
				int idProvElegido = Integer.parseInt(GPI.choModificacionProveedor.getSelectedItem().split("-")[0]);
				String nombre = GPI.txtMPNombreProveedor.getText();
				String telefono = GPI.txtMPTelefonoProveedor.getText();
				String nif = GPI.txtMPNIFProveedor.getText();

				GPBD.ConexionBD();
				GPBD.sentencia = "UPDATE proveedores SET nombreProveedor = '"+nombre+"', telefonoProveedor = "+telefono+", nifProveedor = '"+nif+"' WHERE idProveedor="+idProvElegido;
				GPBD.statement.executeUpdate(GPBD.sentencia);
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
			finally
			{
				Log.registrarLog("Modificacion de proveedor realizada");
				try
				{
					if(GPBD.connection!=null)
					{
						GPBD.connection.close();
					}
				}
				catch (SQLException e)
				{
					System.out.println("Error 3-"+e.getMessage());
				}
			}		
		}
		else if(evento.getSource().equals(GPI.btnMProvVolver))
		{
			GPI.modificacionProveedorConfirmacion.setVisible(false);
		}

	}
}
