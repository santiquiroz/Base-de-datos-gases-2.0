
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


/**
 * Frame del pedido
 * @author santiquiroz
 * @version 0.5.1
 * @since JConexionDB 1.0
 */
public class InfoProductoNoEdit extends View implements IView{
   DataBase db = new DataBase();
   String[] cabecera;
   SimpleTableDemo productoSQL;
   public Boolean editable;
   public String today,ultimoCondonado,ultimoPuntos;
   public ArrayList productoconsulta;
   public ArrayList colores;
   public LinkedList productosInsercion;
   public ArrayList ultimosPedidos,ultimoPedido,datosCliente; 
        JInternalFrame frame = new JInternalFrame("pedido viejo");
    private String ultimoObsequio;
    public String tipoproducto;
    public ArrayList datosPedido;
    private final String codigo;
    private final String id_poseedor;
    private SimpleTableDemo viejoSQL;

   
    public InfoProductoNoEdit(String cod,String id) {
        productoconsulta = new ArrayList();
        id_poseedor = id;
        colores = new ArrayList();
        ultimosPedidos=new ArrayList();
        db = new DataBase();
        codigo=cod;
        initComponents();
        
        setTitle("Informacion del producto");
        setLocationRelativeTo(null);
        String[] cabecera= new String[5];
        
            db= new DataBase();
            
                productoconsulta=db.excecuteQuery("SELECT * FROM productoe WHERE (codigo = '"+codigo+"' AND id_cliente = '"+id_poseedor+"')");
                jTextField1.setText(db.getDato(0,1));
                jTextField2.setText(db.getDato(0,2));
                jComboBox1.setSelectedItem(db.getDato(0,3));
                db= new DataBase();
                colores=db.excecuteQuery("SELECT * FROM colore WHERE (id_productoe_codigo = '"+codigo+"' AND id_productoe_id_cliente = '"+id_poseedor+"')");
                cabecera[0]="color";
                cabecera[1]="precio";
                cabecera[2]="disponible";
                cabecera[3]="codigo de producto";
                cabecera[4]="cliente";
                viejoSQL= new SimpleTableDemo(cabecera,new Object [0][5],"colores","InfoColorNoEdit",this,"todaLaFila");
                if(!db.isEmpty()){
                     viejoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(colores,5).result(),"colores","InfoColorNoEdit",this,"todaLaFila");
                }
            
            //llenando datos del producto
            jTextField13.setText(codigo);
            
            //creando tabla de productos del pedido
            
            
            
            
            viejo.add( frame );
            SimpleTableDemo newContentPane = new SimpleTableDemo();
            newContentPane.setOpaque(true); 
            frame.setContentPane(viejoSQL);
            frame.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
            frame.setVisible(true);
            viejo.repaint();
            viejo.setVisible(true);
        
       try {
           frame.setMaximum(true);
       } catch (PropertyVetoException ex) {
           Logger.getLogger(InfoProductoNoEdit.class.getName()).log(Level.SEVERE, null, ex);
       }
       setVisible(true);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        viejo = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INFORMACION DEL PRODUCTO");

        javax.swing.GroupLayout viejoLayout = new javax.swing.GroupLayout(viejo);
        viejo.setLayout(viejoLayout);
        viejoLayout.setHorizontalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );
        viejoLayout.setVerticalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        jLabel15.setText("Codigo");

        jLabel16.setText("Nombre");

        jTextField13.setEditable(false);
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel17.setText("Peso");

        jLabel28.setText("Tipo");

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Liquido", "Envase", "Extintor", "Soldadura", "Gas industrial", "Otro" }));

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(viejo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel28))
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField13)
                            .addComponent(jComboBox1, 0, 269, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(viejo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
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


