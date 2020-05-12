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
		GPI.modificacionProducto.setLayout(new FlowLayout());
		GPI.modificacionProducto.setSize(600, 170);
		GPI.modificacionProducto.setResizable(false);
		GPI.modificacionProducto.addWindowListener(this);
		GPI.btnMProdAceptar.addActionListener(this);
		GPI.modificacionProducto.add(GPI.lblModificacionProducto);
		GPI.modificacionProducto.add(GPI.choModificacionProducto);
		GPI.modificacionProducto.add(GPI.btnMProdAceptar);
		GPI.modificacionProducto.setLocationRelativeTo(null);
		GPI.modificacionProducto.setVisible(true);

		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{
			GPI.choModificacionProducto.removeAll();
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM productos";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			while(GPBD.rs.next())
			{
				String poblarChoice = Integer.toString(GPBD.rs.getInt("idProducto"));
				poblarChoice = poblarChoice + "-"+ GPBD.rs.getString("nombreProducto");
				GPI.choModificacionProducto.add(poblarChoice);
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
		if(evento.getSource().equals(GPI.btnMProdAceptar))
		{	
			new ObtenerDatosModProd();
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.modificacionProducto.isActive())
		{
			GPI.modificacionProducto.setVisible(false);
		}
	}
}
