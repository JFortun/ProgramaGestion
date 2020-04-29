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
		GPI.bajaProveedor.setLayout(new FlowLayout());
		GPI.bajaProveedor.setSize(400, 120);
		GPI.bajaProveedor.setResizable(false);
		GPI.bajaProveedor.addWindowListener(this);
		GPI.bajaProveedor.add(GPI.lblBajaProveedor);
		GPI.bajaProveedor.add(GPI.choBajaProveedor);
		GPI.btnBProvAceptar.addActionListener(this);
		GPI.bajaProveedor.add(GPI.btnBProvAceptar);
		GPI.btnBProvAceptar.addActionListener(this);
		GPI.bajaProveedor.setLocationRelativeTo(null);
		GPI.bajaProveedor.setVisible(true);

		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{	
			GPI.choBajaProveedor.removeAll();
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM proveedores";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);

			while(GPBD.rs.next())
			{
				String poblarChoice = Integer.toString(GPBD.rs.getInt("idProveedor"));
				poblarChoice = poblarChoice + "-"+ GPBD.rs.getString("nombreProveedor");
				GPI.choBajaProveedor.add(poblarChoice);
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

		if(evento.getSource().equals(GPI.btnBProvAceptar))
		{
			GPI.bajaProveedorConfirmacion.setLayout(new FlowLayout());
			GPI.bajaProveedorConfirmacion.setSize(280, 125);
			GPI.bajaProveedorConfirmacion.setResizable(false);
			GPI.bajaProveedorConfirmacion.addWindowListener(this);
			GPI.btnBProvConfirmar.addActionListener(this);
			GPI.btnBProvVolver.addActionListener(this);
			GPI.bajaProveedorConfirmacion.add(GPI.lblBajaProveedorConfirmacion);
			GPI.bajaProveedorConfirmacion.add(GPI.btnBProvConfirmar);
			GPI.bajaProveedorConfirmacion.add(GPI.btnBProvVolver);
			GPI.bajaProveedorConfirmacion.setLocationRelativeTo(null);
			GPI.bajaProveedorConfirmacion.setVisible(true);
		}
		else if(evento.getSource().equals(GPI.btnBProvConfirmar))
		{
			try
			{
				String[] PSeleccionado=GPI.choBajaProveedor.getSelectedItem().split("-");

				GPBD.ConexionBD();
				GPBD.sentencia = "DELETE FROM proveedores WHERE idProveedor = " + Integer.parseInt(PSeleccionado[0]);
				GPBD.statement.executeUpdate(GPBD.sentencia);
				System.out.println("Baja realizada con éxito");
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
		else if(evento.getSource().equals(GPI.btnBProvVolver))//Investigar porqué hay que darle al botón 2 veces para que funcione
		{
			GPI.bajaProveedorConfirmacion.setVisible(false);
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.bajaProveedor.isActive())
		{
			GPI.bajaProveedor.setVisible(false);
		}
		else if(GPI.bajaProveedorConfirmacion.isActive())
		{
			GPI.bajaProveedorConfirmacion.setVisible(false);
		}
	}
}