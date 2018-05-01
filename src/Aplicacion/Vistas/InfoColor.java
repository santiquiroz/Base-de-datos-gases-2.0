
package Aplicacion.Vistas;

import Aplicacion.Controller.IndexController;
import System.DataBase.Core.DataBase;
import System.Helper.IO;
import System.MVC.Core.IView;
import System.MVC.Core.View;
import java.util.ArrayList;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


/**
 * Frame de creacion cliente nuevo
 * @author santiquiroz 
 * @version 0.8.1
 * @since JConexionDB 1.0
 */
public class InfoColor extends View implements IView{
    
    DataBase db = new DataBase();
    ArrayList datoscolor;
    private final String tipoColor;
    public InfoColor(ArrayList datos, String tipo) {  
        datoscolor=datos;
        tipoColor=tipo;
        initComponents();
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
        if(tipoColor.equals("Empresarial")){
            jLabel4.setText("Telefono cliente");
        }
        jTextField1.setText((String)datoscolor.get(0));
        jTextField3.setText((String)datoscolor.get(3));
        jTextField4.setText((String)datoscolor.get(4));
        jTextField2.setText((String)datoscolor.get(1));
        if(((String)datoscolor.get(2)).equals("1")){
            jComboBox2.setSelectedItem("Si");
        }
        else{
            jComboBox2.setSelectedItem("No");
        }
        setVisible(true);
        setTitle("Informacion del color");
        setLocationRelativeTo(null);
        this.setResizable(false);
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("INFORMACION DEL COLOR");

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Color");

        jLabel3.setText("Codigo de producto");

        jLabel4.setText("Municipio");

        jLabel5.setText("Precio");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);

        jTextField4.setEditable(false);

        jLabel7.setText("Disponible");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No" }));
        jComboBox2.setToolTipText("");

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(403, 414, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel7))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1))
                                .addGap(262, 262, 262))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        db=new DataBase();
        String dis="0";
        if(((String)jComboBox2.getSelectedItem()).equals("Si")){
            dis = "1";
        }
        System.out.println(dis);
        if(tipoColor.equals("Empresarial")){
            System.out.println("consulta:"+"UPDATE colore SET precio = '"+jTextField2.getText()+"',disponible = '"+dis+"' WHERE color = '"+jTextField1.getText()+"' AND id_productoe_codigo = '"+jTextField3.getText()+"' AND id_productoe_id_cliente = '"+jTextField4.getText()+"'");
            db=new DataBase();
            db.actualizar("UPDATE colore SET precio = '"+jTextField2.getText()+"',disponible = '"+dis+"' WHERE color = '"+jTextField1.getText()+"' AND id_productoe_codigo = '"+jTextField3.getText()+"' AND id_productoe_id_cliente = '"+jTextField4.getText()+"'");
        }
        else{
            System.out.println("consulta:"+"UPDATE colorm SET precio = '"+jTextField2.getText()+"',disponible = '"+dis+"' WHERE color = '"+jTextField1.getText()+"' AND id_productom_codigo = '"+jTextField3.getText()+"' AND id_productom_id_municipio ='"+jTextField4.getText()+"'");
            db=new DataBase();
            db.actualizar("UPDATE colorm SET precio = '"+jTextField2.getText()+"',disponible = '"+dis+"' WHERE color = '"+jTextField1.getText()+"' AND id_productom_codigo = '"+jTextField3.getText()+"' AND id_productom_id_municipio ='"+jTextField4.getText()+"'");
        }
        this.dispose();    
             
    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        db= new DataBase();
        if(tipoColor.equals("Empresarial")){
            db.delete2("DELETE FROM colore WHERE color ='"+jTextField1.getText()+"' AND id_productoe_codigo = '"+jTextField3.getText()+"' AND id_productoe_id_cliente = '"+jTextField4.getText()+"'");
        }
        else{
            db.delete2("DELETE FROM colorm WHERE color ='"+jTextField1.getText()+"' AND id_productom_codigo = '"+jTextField3.getText()+"' AND id_productom_id_municipio = '"+jTextField4.getText()+"'");
        }
         this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
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


