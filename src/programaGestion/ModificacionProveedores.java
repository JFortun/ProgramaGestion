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
		Vista.modificacionProveedor.setLayout(new FlowLayout());
		Vista.modificacionProveedor.setSize(600, 170);
		Vista.modificacionProveedor.setResizable(false);
		Vista.modificacionProveedor.addWindowListener(this);
		Vista.btnMProvAceptar.addActionListener(this);
		Vista.modificacionProveedor.add(Vista.lblModificacionProveedor);
		Vista.modificacionProveedor.add(Vista.choModificacionProveedor);
		Vista.modificacionProveedor.add(Vista.btnMProvAceptar);
		Vista.modificacionProveedor.setLocationRelativeTo(null);
		Vista.modificacionProveedor.setVisible(true);

		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{
			Vista.choModificacionProveedor.removeAll();
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM proveedores";
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
			while(Modelo.rs.next())
			{
				String poblarChoice = Integer.toString(Modelo.rs.getInt("idProveedor"));
				poblarChoice = poblarChoice + "-"+ Modelo.rs.getString("nombreProveedor");
				Vista.choModificacionProveedor.add(poblarChoice);
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

	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource().equals(Vista.btnMProvAceptar))
		{	
			new ObtenerDatosModProv();
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.modificacionProveedor.isActive())
		{
			Vista.modificacionProveedor.setVisible(false);
		}
	}
}
