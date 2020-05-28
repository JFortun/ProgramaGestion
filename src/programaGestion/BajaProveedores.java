package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class BajaProveedores extends WindowAdapter implements ActionListener
{

	BajaProveedores()
	{
		Vista.bajaProveedor.setLayout(new FlowLayout());
		Vista.bajaProveedor.setSize(400, 120);
		Vista.bajaProveedor.setResizable(false);
		Vista.bajaProveedor.addWindowListener(this);
		Vista.bajaProveedor.add(Vista.lblBajaProveedor);
		Vista.bajaProveedor.add(Vista.choBajaProveedor);
		Vista.btnBProvAceptar.addActionListener(this);
		Vista.bajaProveedor.add(Vista.btnBProvAceptar);
		Vista.btnBProvAceptar.addActionListener(this);
		Vista.bajaProveedor.setLocationRelativeTo(null);
		Vista.bajaProveedor.setVisible(true);

		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{	
			Vista.choBajaProveedor.removeAll();
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM proveedores";
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);

			while(Modelo.rs.next())
			{
				String poblarChoice = Integer.toString(Modelo.rs.getInt("idProveedor"));
				poblarChoice = poblarChoice + "-"+ Modelo.rs.getString("nombreProveedor");
				Vista.choBajaProveedor.add(poblarChoice);
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

		if(evento.getSource().equals(Vista.btnBProvAceptar))
		{
			Vista.bajaProveedorConfirmacion.setLayout(new FlowLayout());
			Vista.bajaProveedorConfirmacion.setSize(280, 125);
			Vista.bajaProveedorConfirmacion.setResizable(false);
			Vista.bajaProveedorConfirmacion.addWindowListener(this);
			Vista.btnBProvConfirmar.addActionListener(this);
			Vista.btnBProvVolver.addActionListener(this);
			Vista.bajaProveedorConfirmacion.add(Vista.lblBajaProveedorConfirmacion);
			Vista.bajaProveedorConfirmacion.add(Vista.btnBProvConfirmar);
			Vista.bajaProveedorConfirmacion.add(Vista.btnBProvVolver);
			Vista.bajaProveedorConfirmacion.setLocationRelativeTo(null);
			Vista.bajaProveedorConfirmacion.setVisible(true);
		}
		else if(evento.getSource().equals(Vista.btnBProvConfirmar))
		{
			try
			{
				String[] PSeleccionado=Vista.choBajaProveedor.getSelectedItem().split("-");

				Modelo.ConexionBD();
				Modelo.sentencia = "DELETE FROM proveedores WHERE idProveedor = " + Integer.parseInt(PSeleccionado[0]);
				Modelo.statement.executeUpdate(Modelo.sentencia);
			}
			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}
			finally
			{
				Log.registrarLog("Baja de proveedor realizada");
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
		else if(evento.getSource().equals(Vista.btnBProvVolver))//Investigar porqué hay que darle al botón 2 veces para que funcione
		{
			Vista.bajaProveedorConfirmacion.setVisible(false);
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.bajaProveedor.isActive())
		{
			Vista.bajaProveedor.setVisible(false);
		}
		else if(Vista.bajaProveedorConfirmacion.isActive())
		{
			Vista.bajaProveedorConfirmacion.setVisible(false);
		}
	}
}