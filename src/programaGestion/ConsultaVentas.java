package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ConsultaVentas extends WindowAdapter
{

	ConsultaVentas()
	{
		GPI.consultaVenta.setLayout(new FlowLayout());
		GPI.consultaVenta.setSize(550, 300);
		GPI.consultaVenta.setResizable(false);
		GPI.consultaVenta.addWindowListener(this);
		GPI.consultaVenta.add(GPI.lblConsultaVenta);
		GPI.consultaVenta.add(GPI.taConsultaVenta);
		GPI.taConsultaVenta.setEditable(false);
		GPI.consultaVenta.setLocationRelativeTo(null);
		GPI.consultaVenta.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area

		{
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM venden";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			System.out.println("Consulta realizada con éxito");
			GPI.taConsultaVenta.setText("");
			while(GPBD.rs.next())
			{
				if(GPI.taConsultaVenta.getText().length()==0)
				{
					GPI.taConsultaVenta.setText(GPBD.rs.getInt("idLocalFK")+
							"-"+GPBD.rs.getString("idProductoFK")+
							", "+GPBD.rs.getString("fechaVenta"));
				}
				else
				{
					GPI.taConsultaVenta.setText(GPI.taConsultaVenta.getText() + "\n" +
							GPBD.rs.getInt("idLocalFK")+
							"-"+GPBD.rs.getString("idProductoFK")+
							", "+GPBD.rs.getString("fechaVenta"));
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
		if(GPI.consultaVenta.isActive())
		{
			GPI.consultaVenta.setVisible(false);
		}
	}
}
