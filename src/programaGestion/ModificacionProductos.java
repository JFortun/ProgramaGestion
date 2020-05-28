package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ModificacionProductos extends WindowAdapter implements ActionListener
{

	ModificacionProductos()
	{
		Vista.modificacionProducto.setLayout(new FlowLayout());
		Vista.modificacionProducto.setSize(600, 170);
		Vista.modificacionProducto.setResizable(false);
		Vista.modificacionProducto.addWindowListener(this);
		Vista.btnMProdAceptar.addActionListener(this);
		Vista.modificacionProducto.add(Vista.lblModificacionProducto);
		Vista.modificacionProducto.add(Vista.choModificacionProducto);
		Vista.modificacionProducto.add(Vista.btnMProdAceptar);
		Vista.modificacionProducto.setLocationRelativeTo(null);
		Vista.modificacionProducto.setVisible(true);

		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{
			Vista.choModificacionProducto.removeAll();
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM productos";
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);
			while(Modelo.rs.next())
			{
				String poblarChoice = Integer.toString(Modelo.rs.getInt("idProducto"));
				poblarChoice = poblarChoice + "-"+ Modelo.rs.getString("nombreProducto");
				Vista.choModificacionProducto.add(poblarChoice);
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
		if(evento.getSource().equals(Vista.btnMProdAceptar))
		{	
			new ObtenerDatosModProd();
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.modificacionProducto.isActive())
		{
			Vista.modificacionProducto.setVisible(false);
		}
	}
}
