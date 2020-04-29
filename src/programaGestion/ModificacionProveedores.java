package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ModificacionProveedores extends WindowAdapter implements ActionListener
{

	ModificacionProveedores()
	{
		GPI.modificacionProveedor.setLayout(new FlowLayout());
		GPI.modificacionProveedor.setSize(600, 170);
		GPI.modificacionProveedor.setResizable(false);
		GPI.modificacionProveedor.addWindowListener(this);
		GPI.btnMProvAceptar.addActionListener(this);
		GPI.modificacionProveedor.add(GPI.lblModificacionProveedor);
		GPI.modificacionProveedor.add(GPI.choModificacionProveedor);
		GPI.modificacionProveedor.add(GPI.btnMProvAceptar);
		GPI.modificacionProveedor.setLocationRelativeTo(null);
		GPI.modificacionProveedor.setVisible(true);

		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{
			GPI.choModificacionProveedor.removeAll();
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM proveedores";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			while(GPBD.rs.next())
			{
				String poblarChoice = Integer.toString(GPBD.rs.getInt("idProveedor"));
				poblarChoice = poblarChoice + "-"+ GPBD.rs.getString("nombreProveedor");
				GPI.choModificacionProveedor.add(poblarChoice);
			}
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
	}

	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource().equals(GPI.btnMProvAceptar))
		{	
			GPI.txtMPNombreProveedor.selectAll();
			GPI.txtMPNombreProveedor.setText("");
			GPI.txtMPTelefonoProveedor.selectAll();
			GPI.txtMPTelefonoProveedor.setText("");
			GPI.txtMPNIFProveedor.selectAll();
			GPI.txtMPNIFProveedor.setText("");
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
			try
			{
				int idProElegido = Integer.parseInt(GPI.choModificacionProveedor.getSelectedItem().split("-")[0]);
				GPBD.ConexionBD();
				GPBD.sentencia = "SELECT * FROM proveedores WHERE idProveedor="+idProElegido;
				GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
				GPBD.rs.next();
				//Le doy a los text field los valores extraidos del elemento seleccionado
				GPI.txtMPNombreProveedor.setText(GPBD.rs.getString("nombreProveedor"));
				GPI.txtMPTelefonoProveedor.setText(GPBD.rs.getString("telefonoProveedor"));
				GPI.txtMPNIFProveedor.setText(GPBD.rs.getString("nifProveedor"));
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

		}	
		else if(evento.getSource().equals(GPI.btnMProvConfirmar))
		{
			try
			{
				int idProElegido = Integer.parseInt(GPI.choModificacionProveedor.getSelectedItem().split("-")[0]);
				String nombre = GPI.txtMPNombreProveedor.getText();
				String telefono = GPI.txtMPTelefonoProveedor.getText();
				String nif = GPI.txtMPNIFProveedor.getText();

				GPBD.ConexionBD();
				GPBD.sentencia = "UPDATE proveedores SET nombreProveedor = '"+nombre+"', telefonoProveedor = "+telefono+", nifProveedor = '"+nif+"' WHERE idProveedor="+idProElegido;
				GPBD.statement.executeUpdate(GPBD.sentencia);
				System.out.println("Modificación realizada con éxito");
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
		}
		else if(evento.getSource().equals(GPI.btnMProvVolver))
		{
			GPI.modificacionProveedorConfirmacion.setVisible(false);
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.modificacionProveedor.isActive())
		{
			GPI.modificacionProveedor.setVisible(false);
		}
		else if(GPI.modificacionProveedorConfirmacion.isActive())
		{
			GPI.modificacionProveedorConfirmacion.setVisible(false);
		}
	}
}
