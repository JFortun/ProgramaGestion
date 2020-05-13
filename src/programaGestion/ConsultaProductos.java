package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ConsultaProductos extends WindowAdapter
{

	ConsultaProductos()
	{
		GPI.consultaProducto.setLayout(new FlowLayout());
		GPI.consultaProducto.setSize(550, 300);
		GPI.consultaProducto.setResizable(false);
		GPI.consultaProducto.addWindowListener(this);
		GPI.consultaProducto.add(GPI.lblConsultaProducto);
		GPI.consultaProducto.add(GPI.taConsultaProducto);
		GPI.taConsultaProducto.setEditable(false);
		GPI.consultaProducto.setLocationRelativeTo(null);
		GPI.consultaProducto.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area

		{
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM productos";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			GPI.taConsultaProducto.setText("");
			while(GPBD.rs.next())
			{
				if(GPI.taConsultaProducto.getText().length()==0)
				{
					GPI.taConsultaProducto.setText(GPBD.rs.getInt("idProducto")+
							"-"+GPBD.rs.getString("nombreProducto")+
							", "+GPBD.rs.getString("precioProducto")+
							", "+GPBD.rs.getString("stockProducto")+
							", "+GPBD.rs.getString("idProveedorFK"));
				}
				else
				{
					GPI.taConsultaProducto.setText(GPI.taConsultaProducto.getText() + "\n" +
							GPBD.rs.getInt("idProducto")+
							"-"+GPBD.rs.getString("nombreProducto")+
							", "+GPBD.rs.getString("precioProducto")+
							", "+GPBD.rs.getString("stockProducto")+
							", "+GPBD.rs.getString("idProveedorFK"));
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
			Log.registrarLog("Consulta de producto realizada");
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

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.consultaProducto.isActive())
		{
			GPI.consultaProducto.setVisible(false);
		}
	}
}
