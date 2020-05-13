package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AltaProductos extends WindowAdapter implements ActionListener
{

	AltaProductos()
	{
		//Sentencia para recopilar los datos e introducirlos en el choice
		try
		{	
			GPI.choAPProveedorProducto.removeAll();
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM proveedores";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);

			while(GPBD.rs.next())
			{
				String poblarChoice = Integer.toString(GPBD.rs.getInt("idProveedor"));
				poblarChoice = poblarChoice + "-"+ GPBD.rs.getString("nombreProveedor");
				GPI.choAPProveedorProducto.add(poblarChoice);
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
		GPI.altaProducto.setLayout(new FlowLayout());
		GPI.altaProducto.setSize(325, 300);
		GPI.altaProducto.setResizable(false);
		GPI.altaProducto.addWindowListener(this);
		GPI.btnAProdAceptar.addActionListener(this);
		GPI.btnAProdLimpiar.addActionListener(this);
		GPI.altaProducto.add(GPI.lblAPNombreProducto);
		GPI.altaProducto.add(GPI.txtAPNombreProducto);
		GPI.altaProducto.add(GPI.lblAPPrecioProducto);
		GPI.altaProducto.add(GPI.txtAPPrecioProducto);
		GPI.altaProducto.add(GPI.lblAPStockProducto);
		GPI.altaProducto.add(GPI.txtAPStockProducto);
		GPI.altaProducto.add(GPI.lblAPDescripcionProducto);
		GPI.altaProducto.add(GPI.taAPDescripcionProducto);
		GPI.altaProducto.add(GPI.lblAPProveedorProducto);
		GPI.altaProducto.add(GPI.choAPProveedorProducto);
		GPI.altaProducto.add(GPI.btnAProdAceptar);
		GPI.altaProducto.add(GPI.btnAProdLimpiar);
		GPI.altaProducto.setLocationRelativeTo(null);
		GPI.altaProducto.setVisible(true);
		GPI.txtAPNombreProducto.selectAll();
		GPI.txtAPNombreProducto.setText("");
		GPI.txtAPPrecioProducto.selectAll();
		GPI.txtAPPrecioProducto.setText("");
		GPI.txtAPStockProducto.selectAll();
		GPI.txtAPStockProducto.setText("");		
	}

	public void actionPerformed(ActionEvent evento)
	{

		// Alta de proveedores

		if(evento.getSource().equals(GPI.btnAProdAceptar)) 
		{
			try
			{
				String[] PSeleccionado=GPI.choAPProveedorProducto.getSelectedItem().split("-");
				String ProveedorProducto = PSeleccionado[0];
				GPBD.ConexionBD();
				GPBD.sentencia = "INSERT INTO productos (nombreProducto,descripcionProducto,stockProducto,precioProducto,idProveedorFK) VALUES ('" + GPI.txtAPNombreProducto.getText()+ "','" + GPI.taAPDescripcionProducto.getText()+ "',"+ GPI.txtAPStockProducto.getText()+","+ GPI.txtAPPrecioProducto.getText()+",'"+ProveedorProducto+"')";
				GPBD.statement.executeUpdate(GPBD.sentencia);
			}

			catch (SQLException sqle)
			{
				System.out.println("Error 2-"+sqle.getMessage());
			}

			finally
			{
				Log.registrarLog("Alta de producto realizada");
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
			GPI.txtAPNombreProducto.selectAll();
			GPI.txtAPNombreProducto.setText("");
			GPI.txtAPPrecioProducto.selectAll();
			GPI.txtAPPrecioProducto.setText("");
			GPI.txtAPStockProducto.selectAll();
			GPI.txtAPStockProducto.setText("");
			GPI.taAPDescripcionProducto.selectAll();
			GPI.taAPDescripcionProducto.setText("");
			GPI.txtAPNombreProveedor.requestFocus();
		}

	}

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.altaProducto.isActive())
		{
			GPI.altaProducto.setVisible(false);
		}
	}
}
