package programaGestion;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ConsultaProductos extends WindowAdapter implements ActionListener
{

	ConsultaProductos()
	{
		GPI.consultaProducto.setLayout(new FlowLayout());
		GPI.consultaProducto.setSize(550, 300);
		GPI.consultaProducto.setResizable(false);
		GPI.consultaProducto.addWindowListener(this);
		GPI.btnCProdExportarPDF.addActionListener(this);
		GPI.consultaProducto.add(GPI.lblConsultaProducto);
		GPI.consultaProducto.add(GPI.taConsultaProducto);
		GPI.consultaProducto.add(GPI.btnCProdExportarPDF);
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

	@Override
	public void actionPerformed(ActionEvent evento) 
	{
		if(evento.getSource().equals(GPI.btnCProdExportarPDF)) 
		{
			// Se crea el documento 
			Document documento = new Document();
			String resultado = "";
			try 
			{
				try	//Sentencia para recopilar los datos e introducirlos en la variable

				{
					GPBD.ConexionBD();
					GPBD.sentencia = "SELECT * FROM productos";
					GPBD.rs = GPBD.statement.executeQuery(GPBD.sentencia);
					GPI.taConsultaProducto.setText("");
					while(GPBD.rs.next())
					{
						resultado = resultado + GPBD.rs.getInt("idProducto")+
								"-"+GPBD.rs.getString("nombreProducto")+
								"-"+GPBD.rs.getString("precioProducto")+
								"-"+GPBD.rs.getString("stockProducto")+
								"-"+GPBD.rs.getString("idProveedorFK")+"\n";
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
				// Se crea el OutputStream para el fichero donde queremos dejar el pdf. 
				FileOutputStream ficheroPdf = new FileOutputStream("Productos.pdf");
				PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(22);
				// Se abre el documento. 
				documento.open();
				Paragraph titulo = new Paragraph("INFORME DE PRODUCTOS", 
						FontFactory.getFont("arial", // fuente 
								22, // tamaño 
								Font.BOLD, // estilo 
								BaseColor.RED)); // color
				titulo.setAlignment(Element.ALIGN_CENTER);
				documento.add(titulo);
				// Sacar los datos
				String[] cadena = resultado.split("\n");
				PdfPTable tabla = new PdfPTable(5); // Se indica el número de columnas
				tabla.setSpacingBefore(5); // Espaciado ANTES de la tabla
				tabla.addCell("ID");
				tabla.addCell("NOMBRE");
				tabla.addCell("PRECIO");
				tabla.addCell("STOCK");
				tabla.addCell("PROVEEDOR");
				String[] subCadena;
				for (int i = 0; i < cadena.length; i++) 
				{
					subCadena = cadena[i].split("-");
					for(int j = 0; j < 5;j++)
					{
						tabla.addCell(subCadena[j]);
					}
				}
				documento.add(tabla); 
				documento.close(); 
				//Abrimos el archivo PDF recién creado 
				try 
				{
					File path = new File ("Productos.pdf"); 
					Desktop.getDesktop().open(path); 
				}
				catch (IOException ex) 
				{
					System.out.println("Se ha producido un error al abrir el archivo PDF"); 
				}
			}
			catch ( Exception e ) 
			{ 
				System.out.println("Se ha producido un error al generar el archivo PDF");  
			}
		}
	}
}