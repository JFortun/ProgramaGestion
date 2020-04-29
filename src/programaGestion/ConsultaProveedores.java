package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ConsultaProveedores extends WindowAdapter
{

	ConsultaProveedores()
	{
		GPI.consultaProveedor.setLayout(new FlowLayout());
		GPI.consultaProveedor.setSize(550, 300);
		GPI.consultaProveedor.setResizable(false);
		GPI.consultaProveedor.addWindowListener(this);
		GPI.consultaProveedor.add(GPI.lblConsultaProveedor);
		GPI.consultaProveedor.add(GPI.taConsultaProveedor);
		GPI.taConsultaProveedor.setEditable(false);
		GPI.consultaProveedor.setLocationRelativeTo(null);
		GPI.consultaProveedor.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area

		{
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM proveedores";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			System.out.println("Consulta realizada con éxito");
			GPI.taConsultaProveedor.setText("");
			while(GPBD.rs.next())
			{
				if(GPI.taConsultaProveedor.getText().length()==0)
				{
					GPI.taConsultaProveedor.setText(GPBD.rs.getInt("idProveedor")+
							"-"+GPBD.rs.getString("nombreProveedor")+
							", "+GPBD.rs.getString("telefonoProveedor")+
							", "+GPBD.rs.getString("nifProveedor"));
				}
				else
				{
					GPI.taConsultaProveedor.setText(GPI.taConsultaProveedor.getText() + "\n" +
							GPBD.rs.getInt("idProveedor")+
							"-"+GPBD.rs.getString("nombreProveedor")+
							", "+GPBD.rs.getString("telefonoProveedor")+
							", "+GPBD.rs.getString("nifProveedor"));
				}
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

	public void windowClosing(WindowEvent arg0)
	{
		if(GPI.consultaProveedor.isActive())
		{
			GPI.consultaProveedor.setVisible(false);
		}
	}
}
