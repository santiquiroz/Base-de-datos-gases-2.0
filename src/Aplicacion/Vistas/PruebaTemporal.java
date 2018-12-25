
package Aplicacion.Vistas;

import Aplicacion.Controller.IndexController;
import System.DataBase.Core.ConvertidorAMatriz;
import System.DataBase.Core.DataBase;
import System.Helper.IO;
import System.Impresion.ImpresionTermica;
import System.MVC.Core.IView;
import System.MVC.Core.View;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import System.Helper.ManejoDeStrings;

/**
 * Frame del pedido
 * @author santiquiroz e IsmaelTheMemeMaster
 * @version 0.5.1
 * @since JConexionDB 1.0
 */
public class PruebaTemporal extends View implements IView{
   DataBase db = new DataBase();
   String fechaActual;
   String fecha, numero,usuario,telefono,nombre,fechaRegistro,puntos,empresarial,comun,idMunicipio;
   String ultimoFecha,ultimoNumero,ultimoDireccion,ultimoNota,ultimoPrecioTotal,ultimoEstado,ultimoIdUsuario,ultimoIdEmpleado,ultimoIdCliente,ultimoTipo,ultimoIdMunicipio,ultimoBodega;
   String[] cabecera;
   SimpleTableDemo productoSQL;
   public String today,ultimoCondonado,ultimoPuntos;
   public ArrayList productos;
   public ArrayList ultimoProductos;
   public LinkedList productosInsercion;
   public ArrayList ultimosPedidos,ultimoPedido,datosCliente; 
        JInternalFrame frame = new JInternalFrame("Matenme, esto es un frame");
    private String ultimoObsequio;
    private String ultimoPuntosDescontadosPorObsequio;
    public ArrayList datosPedido;
    private String nombreCliente;

   
    public PruebaTemporal() {
        productos = new ArrayList();
        ultimoProductos = new ArrayList();
        ultimosPedidos=new ArrayList();
        db = new DataBase();
        initComponents();
       
        setVisible(true);
        setTitle("Informacion del pedido");
        setLocationRelativeTo(null);
        //datosPedido=db.excecuteQuery("SELECT * FROM cliente WHERE telefono = '"+"telefono"+"' AND nombre ='"+nombre+"'");
        datosPedido=db.excecuteQuery("SELECT id_cliente, COUNT(*) FROM pedido GROUP BY id_cliente HAVING (COUNT(*) > 1)");
                           //Para ver cuantas veces repite     SELECT COUNT(pedido.id_cliente) FROM pedido WHERE (pedido.id_cliente = '3116157700' )
                           //SELECT id_cliente, COUNT(*) FROM pedido GROUP BY id_cliente;
        ultimoFecha=fecha;
        ultimoNumero=numero;

        if(!db.isEmpty()){  //si la consulta no es nula

            //creando tabla de productos del pedido
            String[] cabecera= new String[2];
            cabecera[0]="numero";
            cabecera[1]="repite";
            
            SimpleTableDemo viejoSQL= new SimpleTableDemo(cabecera,new Object [0][2],"","",this,"todaLaFila");
            if(!db.isEmpty()){
                 viejoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(datosPedido,2).result(),"","",this,"todaLaFila");
            
            }
            
            viejo.add( frame );
            SimpleTableDemo newContentPane = new SimpleTableDemo();
            newContentPane.setOpaque(true); 
            frame.setContentPane(viejoSQL);
            frame.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
            frame.setVisible(true);
            viejo.repaint();
            viejo.setVisible(true);
        }
        
        else{
            JOptionPane.showMessageDialog(null, "La consulta retorno cero filas");
            
        }
       try {
           frame.setMaximum(true);
       } catch (PropertyVetoException ex) {
           Logger.getLogger(PruebaTemporal.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        viejo = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Prueba normie de resultados");

        javax.swing.GroupLayout viejoLayout = new javax.swing.GroupLayout(viejo);
        viejo.setLayout(viejoLayout);
        viejoLayout.setHorizontalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        viejoLayout.setVerticalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(372, 372, 372)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(415, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(viejo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(72, 72, 72))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(41, 41, 41)
                .addComponent(viejo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel viejo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onDataReceiver(int action, Object object) {
        switch (action) {
            // recibimos la informacion desde el controllador
            case IndexController.btnExitApp:
                String data = (String) object; // parceamos al objeto original
                JOptionPane.showMessageDialog(this, data); // imprimimos la informacion 
                break;
            default:
                System.out.println("No hay un caso para actionid");
        }
    }
    
}


