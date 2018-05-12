package System.Impresion;

import java.awt.*;
import java.awt.print.*;
import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.ServiceUI;
import javax.print.attribute.*;

public class ImpresionTermica {
  
  //Ticket attribute content
    private String contenidoFactura = 
    "FECHA: {{date}}\n"+
    "NUMERO DE PEDIDO: {{numped}}\n"+
    "MENSAJERO: {{mensa}}\n"+
    "================================================\n"+
    "CLIENTE: {{client}}\n"+
    "TELEFONO: {{tel}}\n"+
    "DIRECCION:{{dir}}\n"+
    "MUNICIPIO:{{mun}}\n"+
    "NOTA:{{note}}\n"+
    "================================================\n"+
    "{{items}}\n"+
    "================================================\n"+
    "TOTAL A PAGAR: {{total}}\n"+
    "PUNTOS DESPUES DE COMPRA:{{puntos}}\n"+
    "\n \n";
    
  //El constructor que setea los valores a la instancia
  public ImpresionTermica(String fecha,String numero,String mensajero,String cliente,String telefono, String direccion, String municipio,String nota,String productos,String total,String puntos) {
    this.contenidoFactura = this.contenidoFactura.replace("{{date}}", fecha);
    this.contenidoFactura = this.contenidoFactura.replace("{{numped}}", numero);
    this.contenidoFactura = this.contenidoFactura.replace("{{mensa}}", mensajero);
    this.contenidoFactura = this.contenidoFactura.replace("{{client}}", cliente);
    this.contenidoFactura = this.contenidoFactura.replace("{{tel}}", telefono);
    this.contenidoFactura = this.contenidoFactura.replace("{{dir}}", direccion);
    this.contenidoFactura = this.contenidoFactura.replace("{{mun}}", municipio);
    this.contenidoFactura = this.contenidoFactura.replace("{{note}}", nota);
    this.contenidoFactura = this.contenidoFactura.replace("{{items}}", productos);
    this.contenidoFactura = this.contenidoFactura.replace("{{puntos}}", puntos);
    
  }
  public ImpresionTermica(){
      
  }
    
  public void print() {
    //Especificamos el tipo de dato a imprimir
    //Tipo: bytes; Subtipo: autodetectado
    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
    
    //Aca obtenemos el servicio de impresion por default
    //Si no quieres ver el dialogo de seleccionar impresora usa esto
    //PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    
    
    //Con esto mostramos el dialogo para seleccionar impresora
    //Si quieres ver el dialogo de seleccionar impresora usalo
    //Solo mostrara las impresoras que soporte arreglo de bits
    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
    PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
    PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);
      
    //Creamos un arreglo de tipo byte
    byte[] bytes;

    //Aca convertimos el string(cuerpo del ticket) a bytes tal como
    //lo maneja la impresora(mas bien ticketera :p)
    bytes = this.contenidoFactura.getBytes();
    //Creamos un documento a imprimir, a el se le appendeara
    //el arreglo de bytes
    Doc doc = new SimpleDoc(bytes,flavor,null);
      
    //Creamos un trabajo de impresión
    DocPrintJob job = service.createPrintJob();

    //Imprimimos dentro de un try de a huevo
    try {
      //El metodo print imprime
      job.print(doc, null);
      
    } catch (Exception er) {
      JOptionPane.showMessageDialog(null,"Error al imprimir: " + er.getMessage());
    }
    DocPrintJob cutjob = service.createPrintJob();
    DocPrintJob spacejob = service.createPrintJob();
    //dejando espacios al final
    byte[] espacio = ("\n \n \n \n").getBytes();
    try{
        spacejob.print(new SimpleDoc(espacio,flavor,null),null);
    }
    catch (Exception er) {
      JOptionPane.showMessageDialog(null,"Error al imprimir: " + er.getMessage());
    }
    //cortando el papel
    try{
        byte[] cutP = new byte[] { 0x1d, 'V', 1 };
        cutjob.print(new SimpleDoc(cutP,flavor,null),null); 
    }catch (Exception er) {
      JOptionPane.showMessageDialog(null,"Error al imprimir: " + er.getMessage());
    }
    
  }

}