package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;

public class AltaVentas extends WindowAdapter implements ActionListener
{
    LocalDate fecha = LocalDate.now(); // Create a date object
    String fechaAmericana = (fecha.toString());
    
	AltaVentas()
	{
		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{	
			Vista.choAVLocalVenta.removeAll();
			Modelo.ConexionBD();
			Modelo.sentencia = "SELECT * FROM locales";
			Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);

			while(Modelo.rs.next())
			{
				String poblarChoice = Integer.toString(Modelo.rs.getInt("idLocal"));
				poblarChoice = poblarChoice + "-"+ Modelo.rs.getString("direccionLocal");
				Vista.choAVLocalVenta.add(poblarChoice);
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
		//Sentencia para recopilar los datos e introducirlos en el choice
				try
				{	
					Vista.choAVProductoVenta.removeAll();
					Modelo.ConexionBD();
					Modelo.sentencia = "SELECT * FROM productos";
					Modelo.rs = Modelo.statement.executeQuery(Modelo.sentencia);

					while(Modelo.rs.next())
					{
						String poblarChoice = Integer.toString(Modelo.rs.getInt("idProducto"));
						poblarChoice = poblarChoice + "-"+ Modelo.rs.getString("nombreProducto");
						Vista.choAVProductoVenta.add(poblarChoice);
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
		Vista.altaVenta.setLayout(new FlowLayout());
		Vista.altaVenta.setSize(350, 160);
		Vista.altaVenta.setResizable(false);
		Vista.altaVenta.addWindowListener(this);
		Vista.btnAVAceptar.addActionListener(this);
		Vista.btnAVLimpiar.addActionListener(this);
		Vista.altaVenta.add(Vista.lblAVLocalVenta);
		Vista.altaVenta.add(Vista.choAVLocalVenta);
		Vista.altaVenta.add(Vista.lblAVProductoVenta);
		Vista.altaVenta.add(Vista.choAVProductoVenta);
		Vista.altaVenta.add(Vista.lblAVFechaVenta);
		Vista.altaVenta.add(Vista.txtAVFechaVenta);
		Vista.altaVenta.add(Vista.btnAVAceptar);
		Vista.altaVenta.add(Vista.btnAVLimpiar);
		Vista.altaVenta.setLocationRelativeTo(null);
		Vista.altaVenta.setVisible(true);
		Vista.txtAVFechaVenta.setText(fechaAmericana);
	}

	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(Vista.btnAVAceptar)) 
		{
			try
			{

				String[] LSeleccionado=Vista.choAVLocalVenta.getSelectedItem().split("-");
				String localVenta = LSeleccionado[0];
				String[] PSeleccionado=Vista.choAVProductoVenta.getSelectedItem().split("-");
				String productoVenta = PSeleccionado[0];
				String[] fechaFormateada = fechaAmericana.split("-");
				Modelo.ConexionBD();
				Modelo.sentencia = "INSERT INTO venden (idLocalFK,idProductoFK,fechaVenta) VALUES (" + localVenta+ "," + productoVenta+ ",'"+fechaFormateada[0]+"-"+fechaFormateada[1]+"-"+fechaFormateada[2]+"')";
				Modelo.statement.executeUpdate(Modelo.sentencia);
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}

			finally
			{
				Log.registrarLog("Alta de venta realizada");
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
		if(evento.getSource().equals(Vista.btnAProdLimpiar)) 
		{
			Vista.txtAVFechaVenta.selectAll();
			Vista.txtAVFechaVenta.setText("");
			Vista.txtAVFechaVenta.requestFocus();
		}

	}

	public void windowClosing(WindowEvent arg0)
	{
		if(Vista.altaVenta.isActive())
		{
			Vista.altaVenta.setVisible(false);
		}
	}
}
