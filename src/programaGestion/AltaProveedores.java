package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AltaProveedores extends WindowAdapter implements ActionListener
{

	AltaProveedores()
	{
		Vista.altaProveedor.setLayout(new FlowLayout());
		Vista.altaProveedor.setSize(350, 170);
		Vista.altaProveedor.setResizable(false);
		Vista.altaProveedor.addWindowListener(this);
		Vista.btnAProvAceptar.addActionListener(this);
		Vista.btnAProvLimpiar.addActionListener(this);
		Vista.altaProveedor.add(Vista.lblAPNombreProveedor);
		Vista.altaProveedor.add(Vista.txtAPNombreProveedor);
		Vista.altaProveedor.add(Vista.lblAPTelefonoProveedor);
		Vista.altaProveedor.add(Vista.txtAPTelefonoProveedor);
		Vista.altaProveedor.add(Vista.lblAPNIFProveedor);
		Vista.altaProveedor.add(Vista.txtAPNIFProveedor);
		Vista.altaProveedor.add(Vista.btnAProvAceptar);
		Vista.altaProveedor.add(Vista.btnAProvLimpiar);
		Vista.altaProveedor.setLocationRelativeTo(null);
		Vista.altaProveedor.setVisible(true);
		Vista.txtAPNombreProveedor.selectAll();
		Vista.txtAPNombreProveedor.setText("");
		Vista.txtAPTelefonoProveedor.selectAll();
		Vista.txtAPTelefonoProveedor.setText("");
		Vista.txtAPNIFProveedor.selectAll();
		Vista.txtAPNIFProveedor.setText("");
	}

	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(Vista.btnAProvAceptar)) 
		{
			try
			{
				Modelo.ConexionBD();
				Modelo.sentencia = "INSERT INTO proveedores (nombreProveedor,telefonoProveedor,nifProveedor) VALUES ('" + Vista.txtAPNombreProveedor.getText()+ "',"+ Vista.txtAPTelefonoProveedor.getText()+",'"+Vista.txtAPNIFProveedor.getText()+"')";
				Modelo.statement.executeUpdate(Modelo.sentencia);
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}

			finally
			{
				Log.registrarLog("Alta de proveedor realizada");
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
		if(evento.getSource().equals(Vista.btnAProvLimpiar)) 
		{
			Vista.txtAPNombreProveedor.selectAll();
			Vista.txtAPNombreProveedor.setText("");
			Vista.txtAPTelefonoProveedor.selectAll();
			Vista.txtAPTelefonoProveedor.setText("");
			Vista.txtAPNIFProveedor.selectAll();
			Vista.txtAPNIFProveedor.setText("");
			Vista.txtAPNombreProveedor.requestFocus();
		}

	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.altaProveedor.isActive())
		{
			Vista.altaProveedor.setVisible(false);
		}
	}
}
