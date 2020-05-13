package programaGestion;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ConsultaLocales extends WindowAdapter
{

	ConsultaLocales()
	{
		GPI.consultaLocal.setLayout(new FlowLayout());
		GPI.consultaLocal.setSize(550, 300);
		GPI.consultaLocal.setResizable(false);
		GPI.consultaLocal.addWindowListener(this);
		GPI.consultaLocal.add(GPI.lblConsultaLocal);
		GPI.consultaLocal.add(GPI.taConsultaLocal);
		GPI.taConsultaLocal.setEditable(false);
		GPI.consultaLocal.setLocationRelativeTo(null);
		GPI.consultaLocal.setVisible(true);

		try	//Sentencia para recopilar los datos e introducirlos en el text area
		{
			GPBD.ConexionBD();
			GPBD.sentencia = "SELECT * FROM locales";
			GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
			GPI.taConsultaLocal.setText("");
			while(GPBD.rs.next())
			{
				if(GPI.taConsultaLocal.getText().length()==0)
				{
					GPI.taConsultaLocal.setText(GPBD.rs.getInt("idLocal")+
							", "+GPBD.rs.getString("direccionLocal"));
				}
				else
				{
					GPI.taConsultaProveedor.setText(GPI.taConsultaLocal.getText() + "\n" +
							GPBD.rs.getInt("idLocal")+
							", "+GPBD.rs.getString("direccionLocal"));
				}
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		finally
		{
			Log.registrarLog("Consulta de local realizada");
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
		if(GPI.consultaLocal.isActive())
		{
			GPI.consultaLocal.setVisible(false);
		}
	}
}
