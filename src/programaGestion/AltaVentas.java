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
			GPI.choAVLocalVenta.removeAll();
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM locales";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);

			while(GPBD.rs.next())
			{
				String poblarChoice = Integer.toString(GPBD.rs.getInt("idLocal"));
				poblarChoice = poblarChoice + "-"+ GPBD.rs.getString("direccionLocal");
				GPI.choAVLocalVenta.add(poblarChoice);
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
		//Sentencia para recopilar los datos e introducirlos en el choice
				try
				{	
					GPI.choAVProductoVenta.removeAll();
					GPBD.ConexionBD();
					GPBD.sentencia = "SELECT * FROM productos";
					GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);

					while(GPBD.rs.next())
					{
						String poblarChoice = Integer.toString(GPBD.rs.getInt("idProducto"));
						poblarChoice = poblarChoice + "-"+ GPBD.rs.getString("nombreProducto");
						GPI.choAVProductoVenta.add(poblarChoice);
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
		GPI.altaVenta.setLayout(new FlowLayout());
		GPI.altaVenta.setSize(350, 160);
		GPI.altaVenta.setResizable(false);
		GPI.altaVenta.addWindowListener(this);
		GPI.btnAVAceptar.addActionListener(this);
		GPI.btnAVLimpiar.addActionListener(this);
		GPI.altaVenta.add(GPI.lblAVLocalVenta);
		GPI.altaVenta.add(GPI.choAVLocalVenta);
		GPI.altaVenta.add(GPI.lblAVProductoVenta);
		GPI.altaVenta.add(GPI.choAVProductoVenta);
		GPI.altaVenta.add(GPI.lblAVFechaVenta);
		GPI.altaVenta.add(GPI.txtAVFechaVenta);
		GPI.altaVenta.add(GPI.btnAVAceptar);
		GPI.altaVenta.add(GPI.btnAVLimpiar);
		GPI.altaVenta.setLocationRelativeTo(null);
		GPI.altaVenta.setVisible(true);
		GPI.txtAVFechaVenta.setText(fechaAmericana);
	}

	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(GPI.btnAVAceptar)) 
		{
			try
			{

				String[] LSeleccionado=GPI.choAVLocalVenta.getSelectedItem().split("-");
				String localVenta = LSeleccionado[0];
				String[] PSeleccionado=GPI.choAVProductoVenta.getSelectedItem().split("-");
				String productoVenta = PSeleccionado[0];
				String[] fechaFormateada = fechaAmericana.split("-");
				GPBD.ConexionBD();
				GPBD.sentencia = "INSERT INTO venden (idLocalFK,idProductoFK,fechaVenta) VALUES (" + localVenta+ "," + productoVenta+ ",'"+fechaFormateada[0]+"-"+fechaFormateada[1]+"-"+fechaFormateada[2]+"')";
				GPBD.statement.executeUpdate(GPBD.sentencia);
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
		if(evento.getSource().equals(GPI.btnAProdLimpiar)) 
		{
			GPI.txtAVFechaVenta.selectAll();
			GPI.txtAVFechaVenta.setText("");
			GPI.txtAVFechaVenta.requestFocus();
		}

	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.altaVenta.isActive())
		{
			GPI.altaVenta.setVisible(false);
		}
	}
}
