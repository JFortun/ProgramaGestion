package programaGestion;

import java.io.IOException;

public class Ayuda 
{
	{
		try 
		{
			Runtime.getRuntime().exec("hh.exe ayuda.chm");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			Log.registrarLog("Consulta de ayuda realizada");
		}

	}
}
