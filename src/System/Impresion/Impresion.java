/*package System.Impresion;
//import br.com.adilson.util.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Impresion implements Printable, ActionListener{
     public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException {

        if (page > 0) { /* We have only one page, and 'page' is zero-based *//*
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         *//*
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Now we perform our rendering *//*
        g.drawString("Hello world!", 100, 100);

        /* tell the caller that this page is part of the printed document *//*
        return PAGE_EXISTS;
    }

    public void actionPerformed(ActionEvent e) {
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete *//*
             }
         }
    }
    public static void main(String[] args) throws PrintException, IOException {
        
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        JFrame f = new JFrame("Hello World Printer");
        f.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        JButton printButton = new JButton("Print Hello World");
        printButton.addActionListener(new Impresion());
        f.add("Center", printButton);
        f.pack();
        f.setVisible(true);
        
        
        
        
        
        //new Impresora().imprimirFactura();

        String defaultPrinter = PrintServiceLookup.lookupDefaultPrintService().getName();
        System.out.println("Default printer: " + defaultPrinter);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        // prints the famous hello world! plus a form feed
        InputStream is = new ByteArrayInputStream("hello world!\f".getBytes("UTF8"));

        PrintRequestAttributeSet  pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(is, flavor, null);
        DocPrintJob job = service.createPrintJob();

        PrintJobWatcher pjw = new PrintJobWatcher(job);
        job.print(doc, pras);
        pjw.waitForDone();
        is.close();
        
  }

    
 
}
//class Impresora{
    //void imprimirFactura(){
       /* PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(101.85);


        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        printer.setOutSize(60, 80);
        //Imprimir * de la 2da linea a 25 en la columna 1;
       // printer.printCharAtLin(2, 25, 1, "*");
        //Imprimir * 1ra linea de la columa de 1 a 80
       printer.printCharAtCol(1, 1, 80, "=");
        //Imprimir Encabezado nombre del La EMpresa
       printer.printTextWrap(1, 2, 30, 80, "FACTURA DE VENTA");
       //printer.printTextWrap(linI, linE, colI, colE, null);
       printer.printTextWrap(2, 3, 1, 22, "Num. Boleta : " + "0001" /*txtVentaNumeroFactura.getText()*//*);
       printer.printTextWrap(2, 3, 25, 55, "Fecha de Emision: " + "11/01/2017"/*dateFechaVenta.getDate()*//*);
       printer.printTextWrap(2, 3, 60, 80, "Hora: 12:22:51");
       printer.printTextWrap(3, 3, 1, 80, "Vendedor.  : "+ "santiago"/*txtVentaIdVendedor.getText() +" - " + txtVentaNombreVendedor.getText()*//*);
       printer.printTextWrap(4, 4, 1, 80, "CLIENTE: " + "3122161625"/*txtVentaNombreCliente.getText()*//*);
       printer.printTextWrap(5, 5, 1, 80, "RUC/CI.: " + "000000"/*txtVentaRucCliente.getText()*//*);
       printer.printTextWrap(6, 6, 1, 80, "DIRECCION: " + "");
       printer.printCharAtCol(7, 1, 80, "=");
       printer.printTextWrap(7, 8, 1, 80, "Codigo          Descripcion                Cant.      P  P.Unit.      P.Total");
       printer.printCharAtCol(9, 1, 80, "-");
       DefaultTableModel modelo = new DefaultTableModel();
        JTable tblVentas = new JTable(modelo);
        modelo.addColumn("Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cant.");
        modelo.addColumn("P P.Unit");
        modelo.addColumn("Total");
        Object [] fila = new Object[5];
        fila[0] = "dato columna 1";
        fila[1] = "dato columna 2";
        fila[2] = "dato columna 3";
        fila[3] = "dato columna 4";   
        fila[4] = "dato columna 5";
         modelo.addRow(fila);
        
       int filas = tblVentas.getRowCount();

        for (int i = 0; i < filas; i++) {
         printer.printTextWrap(9 + i, 10, 1, 80, tblVentas.getValueAt(i,0).toString()+"|"+tblVentas.getValueAt(i,1).toString()+"| "+tblVentas.getValueAt(i,2).toString()+"| "+tblVentas.getValueAt(i,3).toString()+"|"+ tblVentas.getValueAt(i,4).toString());
         }

        if(filas > 15){
        printer.printCharAtCol(filas + 1, 1, 80, "=");
        printer.printTextWrap(filas + 1, filas + 2, 1, 80, "TOTAL A PAGAR " + "200000"/*txtVentaTotal.getText()*//*);
        printer.printCharAtCol(filas + 2, 1, 80, "=");
        printer.printTextWrap(filas + 2, filas + 3, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");
        }else{
        printer.printCharAtCol(25, 1, 80, "=");
        printer.printTextWrap(26, 26, 1, 80, "TOTAL A PAGAR " + "30000"/*txtVentaTotal.getText()*//*);
        printer.printCharAtCol(27, 1, 80, "=");
        printer.printTextWrap(27, 28, 1, 80, "Esta boleta no tiene valor fiscal, solo para uso interno.: + Descripciones........");

        }
        printer.toFile("impresion.txt");

      FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();


        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //inputStream.close();

    }
}

class PrintJobWatcher {
  boolean done = false;

  PrintJobWatcher(DocPrintJob job) {
    job.addPrintJobListener(new PrintJobAdapter() {
      public void printJobCanceled(PrintJobEvent pje) {
        allDone();
      }
      public void printJobCompleted(PrintJobEvent pje) {
        allDone();
      }
      public void printJobFailed(PrintJobEvent pje) {
        allDone();
      }
      public void printJobNoMoreEvents(PrintJobEvent pje) {
        allDone();
      }
      void allDone() {
        synchronized (PrintJobWatcher.this) {
          done = true;
          System.out.println("Printing done ...");
          PrintJobWatcher.this.notify();
        }
      }
    });
  }
  public synchronized void waitForDone() {
    try {
      while (!done) {
        wait();
      }
    } catch (InterruptedException e) {
    }
  }  
}
*/