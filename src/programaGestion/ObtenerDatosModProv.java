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
			int idProElegido = Integer.parseInt(Vista.choModificacionProveedor.getSelectedItem().split("-")[0]);
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM proveedores WHERE idProveedor="+idProElegido;
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
			Modelo.rs.next();
			//Le doy a los text field los valores extraidos del elemento seleccionado
			nombreProveedor = Modelo.rs.getString("nombreProveedor");
			Vista.txtMPNombreProveedor.setText(nombreProveedor);
			telefonoProveedor = Modelo.rs.getString("telefonoProveedor");
			Vista.txtMPTelefonoProveedor.setText(telefonoProveedor);
			nifProveedor = Modelo.rs.getString("nifProveedor");
			Vista.txtMPNIFProveedor.setText(nifProveedor);

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
			try
			{
				if(Modelo.connection!=null)
				{
					Modelo.connection.close();
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error 3-"+e.getMessage());
			}
		}
		Vista.modificacionProveedorConfirmacion.setLayout(new FlowLayout());
		Vista.modificacionProveedorConfirmacion.setSize(350, 170);
		Vista.modificacionProveedorConfirmacion.setResizable(false);
		Vista.modificacionProveedorConfirmacion.addWindowListener(this);
		Vista.btnMProvConfirmar.addActionListener(this);
		Vista.btnMProvVolver.addActionListener(this);
		Vista.modificacionProveedorConfirmacion.add(Vista.lblMPNombreProveedor);
		Vista.modificacionProveedorConfirmacion.add(Vista.txtMPNombreProveedor);
		Vista.modificacionProveedorConfirmacion.add(Vista.lblMPTelefonoProveedor);
		Vista.modificacionProveedorConfirmacion.add(Vista.txtMPTelefonoProveedor);
		Vista.modificacionProveedorConfirmacion.add(Vista.lblMPNIFProveedor);
		Vista.modificacionProveedorConfirmacion.add(Vista.txtMPNIFProveedor);
		Vista.modificacionProveedorConfirmacion.add(Vista.btnMProvConfirmar);
		Vista.modificacionProveedorConfirmacion.add(Vista.btnMProvVolver);
		Vista.modificacionProveedorConfirmacion.setLocationRelativeTo(null);
		Vista.modificacionProveedorConfirmacion.setVisible(true);
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.modificacionProveedorConfirmacion.isActive())
		{
			Vista.modificacionProveedorConfirmacion.setVisible(false);
		}
	}

	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(Vista.btnMProvConfirmar))
		{
			try
			{
				int idProvElegido = Integer.parseInt(Vista.choModificacionProveedor.getSelectedItem().split("-")[0]);
				String nombre = Vista.txtMPNombreProveedor.getText();
				String telefono = Vista.txtMPTelefonoProveedor.getText();
				String nif = Vista.txtMPNIFProveedor.getText();

				Modelo.ConexionBD();
				Modelo.sentencia = "UPDATE proveedores SET nombreProveedor = '"+nombre+"', telefonoProveedor = "+telefono+", nifProveedor = '"+nif+"' WHERE idProveedor="+idProvElegido;
				Modelo.statement.executeUpdate(Modelo.sentencia);
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
					if(Modelo.connection!=null)
					{
						Modelo.connection.close();
					}
				}
				catch (SQLException e)
				{
					System.out.println("Error 3-"+e.getMessage());
				}
			}		
		}
		else if(evento.getSource().equals(Vista.btnMProvVolver))
		{
			Vista.modificacionProveedorConfirmacion.setVisible(false);
		}

	}
}
