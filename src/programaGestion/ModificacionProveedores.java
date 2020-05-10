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
			new ObtenerDatosModProv();
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.modificacionProveedor.isActive())
		{
			GPI.modificacionProveedor.setVisible(false);
		}
	}
}
