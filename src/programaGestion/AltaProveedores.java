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
		GPI.altaProveedor.setLayout(new FlowLayout());
		GPI.altaProveedor.setSize(350, 170);
		GPI.altaProveedor.setResizable(false);
		GPI.altaProveedor.addWindowListener(this);
		GPI.btnAProvAceptar.addActionListener(this);
		GPI.btnAProvLimpiar.addActionListener(this);
		GPI.altaProveedor.add(GPI.lblAPNombreProveedor);
		GPI.altaProveedor.add(GPI.txtAPNombreProveedor);
		GPI.altaProveedor.add(GPI.lblAPTelefonoProveedor);
		GPI.altaProveedor.add(GPI.txtAPTelefonoProveedor);
		GPI.altaProveedor.add(GPI.lblAPNIFProveedor);
		GPI.altaProveedor.add(GPI.txtAPNIFProveedor);
		GPI.altaProveedor.add(GPI.btnAProvAceptar);
		GPI.altaProveedor.add(GPI.btnAProvLimpiar);
		GPI.altaProveedor.setLocationRelativeTo(null);
		GPI.altaProveedor.setVisible(true);
		GPI.txtAPNombreProveedor.selectAll();
		GPI.txtAPNombreProveedor.setText("");
		GPI.txtAPTelefonoProveedor.selectAll();
		GPI.txtAPTelefonoProveedor.setText("");
		GPI.txtAPNIFProveedor.selectAll();
		GPI.txtAPNIFProveedor.setText("");
	}

	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(GPI.btnAProvAceptar)) 
		{
			try
			{
				GPBD.ConexionBD();
				GPBD.sentencia = "INSERT INTO proveedores (nombreProveedor,telefonoProveedor,nifProveedor) VALUES ('" + GPI.txtAPNombreProveedor.getText()+ "',"+ GPI.txtAPTelefonoProveedor.getText()+",'"+GPI.txtAPNIFProveedor.getText()+"')";
				GPBD.statement.executeUpdate(GPBD.sentencia);
				System.out.println("Alta realizada con éxito");
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
		if(evento.getSource().equals(GPI.btnAProvLimpiar)) 
		{
			GPI.txtAPNombreProveedor.selectAll();
			GPI.txtAPNombreProveedor.setText("");
			GPI.txtAPTelefonoProveedor.selectAll();
			GPI.txtAPTelefonoProveedor.setText("");
			GPI.txtAPNIFProveedor.selectAll();
			GPI.txtAPNIFProveedor.setText("");
			GPI.txtAPNombreProveedor.requestFocus();
		}

	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.altaProveedor.isActive())
		{
			GPI.altaProveedor.setVisible(false);
		}
	}
}
