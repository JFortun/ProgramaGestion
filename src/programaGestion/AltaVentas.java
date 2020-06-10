package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

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
			Vista.choAVLocalVenta.add("Elige un local");
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
					Vista.choAVProductoVenta.add("Elige un producto");
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
		
	    String[] arrayFA = fechaAmericana.split("-");
	    String fechaEuropea = arrayFA[2].toString() + "-" + arrayFA[1] + "-" + arrayFA[0];
		
		Vista.txtAVFechaVenta.setText(fechaEuropea);
		
	}

	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(Vista.btnAVAceptar)) 
		{
			if (Vista.choAVLocalVenta.getSelectedItem().equals("Elige un local") || Vista.choAVProductoVenta.getSelectedItem().equals("Elige un producto")) 
			{
				JOptionPane.showMessageDialog(Vista.altaVenta, "Tienes que elegir un local y un producto");
			}
			else
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
					JOptionPane.showMessageDialog(Vista.altaVenta, "Alta de ventas realizada");
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
