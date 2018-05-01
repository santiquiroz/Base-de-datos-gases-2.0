package Aplicacion.Vistas;
import Aplicacion.Controller.IndexController;
import System.DataBase.Core.ConvertidorAMatriz;
import System.DataBase.Core.DataBase;
import System.Helper.IO;
import System.MVC.Core.IView;
import System.MVC.Core.View;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JOptionPane;


/**
 * Frame de la eliminacion del empleado
 * @author santiquiroz 
 * @version 0.8.1(actualmente desechado)
 * @since JConexionDB 1.0
 */
public class carrito extends View implements IView{
    DataBase db = new DataBase();
    String codigo, nombre, peso,ti,descripcion,preciobase, telefono,ultimafecha;
    Double aumentoMunicipio,aumentoCliente,aumentoProducto,aumentoTotal,precioFinal,ultimoprecio,aumentoClientein;
    Pedido vPedido;
    ArrayList infocliente;
    ArrayList colores;
    final String c,mun,tel;
    public carrito(String idprod,Pedido ventanaPadre) {
        c=ventanaPadre.comun;
        tel=ventanaPadre.telefono;
        mun="";
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        codigo=idprod;
        vPedido=ventanaPadre;
        telefono=ventanaPadre.telefono;
        
        DataBase db = new DataBase();
        
        

        setVisible(true);
        setTitle("Agregar producto");
        setLocationRelativeTo(null);
    }
    public carrito(ArrayList datos,Pedido ventanaPadre,String tipo) {
        initComponents();
        vPedido=ventanaPadre;
        c=this.vPedido.comun;
        mun=this.vPedido.idMunicipio;
        tel=this.vPedido.telefono;
        this.telefono=ventanaPadre.telefono;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        codigo=(String)datos.get(0);
        nombre=(String)datos.get(1);
        peso=(String)datos.get(2);
        ti=(String)datos.get(3);
        jTextField1.setText(codigo);
        jTextField2.setText(nombre);
        jTextField3.setText(peso);
        
        DataBase db = new DataBase();
        db= new DataBase();
            
            colores = new ArrayList();
            if(this.vPedido.comun.equals("1")){
                colores = db.excecuteQuery("SELECT color FROM colorm WHERE disponible ='1' AND id_productom_codigo = '"+codigo+"' AND id_productom_id_municipio = '"+this.vPedido.idMunicipio+"'");
            }
            else{
                colores = db.excecuteQuery("SELECT color FROM colore WHERE disponible ='1' AND id_productoe_codigo = '"+codigo+"' AND id_productoe_id_cliente = '"+this.vPedido.telefono+"'");
            }
            
        
            jComboBox1.removeAllItems();
            DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
            ArrayList emp;
            for (int i = 0; i < colores.size(); i++) {
                emp=(ArrayList)colores.get(i);
                modeloCombo2.addElement(emp.get(0));
            }
        jComboBox1.setModel(modeloCombo2);
        db= new DataBase();
        if(this.vPedido.comun.equals("1")){
            System.out.println("Entre a comun");
           db.excecuteQuery("SELECT precio FROM colorm WHERE color ='"+jComboBox1.getSelectedItem()+"' AND id_productom_codigo = '"+codigo+"' AND id_productom_id_municipio = '"+this.vPedido.idMunicipio+"'");
 
        }
        else{
            System.out.println("Entre a empresarial");
            db.excecuteQuery("SELECT precio FROM colore WHERE color ='"+jComboBox1.getSelectedItem()+"' AND id_productoe_codigo = '"+codigo+"' AND id_productoe_id_cliente = '"+this.vPedido.telefono+"'");

        }
        String precioBase = db.getDato(0,0);
        jTextField6.setText(precioBase);
        jTextField4.setText(precioBase);
        
        jComboBox1.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                DataBase db1= new DataBase();
                if(c.equals("1")){
                   db1.excecuteQuery("SELECT precio FROM colorm WHERE color = '"+jComboBox1.getSelectedItem()+"' AND id_productom_codigo = '"+jTextField1.getText()+"' AND id_productom_id_municipio = '"+mun+"'"); 
                }
                else{
                   db1.excecuteQuery("SELECT precio FROM colore WHERE color = '"+jComboBox1.getSelectedItem()+"' AND id_productoe_codigo = '"+jTextField1.getText()+"' AND id_productoe_id_cliente = '"+tel+"'"); 
                }
                
                String precioUnidad = db1.getDato(0,0);
                jTextField6.setText(precioUnidad);
                jTextField4.setText(precioUnidad);
            }
        });
        setVisible(true);
        setTitle("Agregar producto");
        setLocationRelativeTo(null);
    }
   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton6 = new javax.swing.JToggleButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel35 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton6.setText("Salir");
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("AGREGAR PRODUCTO A LA COMPRA");

        jTextField1.setEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel32.setText("Codigo");

        jLabel39.setText("Nombre");

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton14.setText("Agregar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel18.setText(" Precio por unidad final");

        jLabel33.setText("Peso");

        jTextField3.setEnabled(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel34.setText("Cantidad");

        jSpinner1.setToolTipText("");
        jSpinner1.setFocusable(false);
        jSpinner1.setValue(1);

        jLabel35.setText("Color");

        jTextField6.setEnabled(false);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel36.setText("Precio base por unidad");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jToggleButton6)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel32)
                                        .addComponent(jLabel18))
                                    .addGap(41, 41, 41)))
                            .addComponent(jLabel35))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(452, 452, 452)
                                .addComponent(jButton14))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField4)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel39))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel36)
                                                .addComponent(jLabel34))))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField6)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                        .addComponent(jSpinner1)))))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel11)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34))
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel36)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jToggleButton6))
                .addGap(52, 52, 52))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        int numerodeproductos = (int)jSpinner1.getValue();
        String color = (String) jComboBox1.getSelectedItem();
        String precio = (String) jTextField4.getText();
        db= new DataBase();
        db.excecuteQuery("SELECT puntosxkilo FROM static");
        String puntosxkilo=db.getDato(0, 0);
        String puntos = String.valueOf((Double.parseDouble(peso))*(Double.parseDouble(puntosxkilo)));
        for (int i = 0; i < numerodeproductos; i++) {
            ArrayList producto = new ArrayList();
            ArrayList productoInsercion = new ArrayList();
            producto.add(codigo);
            int u=this.vPedido.productos.size();
            if(u>0){
                producto.add((Integer.parseInt((((String)((ArrayList) this.vPedido.productos.get(u-1)).get(1))))+1)+"");
            }
            else{
                producto.add("1");
            }
            producto.add(nombre);
            producto.add(color);
            producto.add(peso);
            producto.add(precio);
            this.vPedido.productos.add(producto);
            
            productoInsercion.add(Integer.toString(i+1));
            productoInsercion.add(nombre);
            productoInsercion.add(color);
            productoInsercion.add(peso);
            productoInsercion.add(precio);
            productoInsercion.add(puntos);
            productoInsercion.add(codigo);
            productoInsercion.add(this.vPedido.fechaActual);
            productoInsercion.add(this.vPedido.jTextField8.getText());
            this.vPedido.productosInsercion.add(productoInsercion);
            
            //actualizando puntos
            Double currentpuntos= Double.parseDouble(vPedido.jTextField17.getText()) + Double.parseDouble(puntos);
            DataBase da = new DataBase();
            da.excecuteQuery("SELECT limPuntosAcomulables FROM static");
            Double limiteAcomulables =Double.parseDouble(da.getDato(0,0));
            if((Double.parseDouble(vPedido.jTextField17.getText())+currentpuntos)>limiteAcomulables){
                currentpuntos=limiteAcomulables;
            }
            this.vPedido.jTextField17.setText((currentpuntos).toString());
            //actualizando precio bruto
            Double currentpreciobruto= Double.parseDouble(vPedido.jTextField20.getText()) + Double.parseDouble(precio);
            this.vPedido.jTextField20.setText((currentpreciobruto).toString());
            //actualizando precio neto
            Double currentprecioneto= Double.parseDouble(vPedido.jTextField22.getText()) + Double.parseDouble(precio);
            this.vPedido.jTextField22.setText((currentprecioneto).toString());
            
        }
       
        if(numerodeproductos > 0){
            this.vPedido.nuevo.removeAll();
            String[] cabecera= new String[6];
                    cabecera[0]="codigo";
                    cabecera[1]="consecutivo";
                    cabecera[2]="nombre";
                    cabecera[3]="color";
                    cabecera[4]="peso";     
                    cabecera[5]="precio";   
            SimpleTableDemo productoSQL1= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(this.vPedido.productos,6).result(),"productos","Carrito",this,"todaLaFila");
            this.vPedido.nuevo.add( this.vPedido.frame2 );
            SimpleTableDemo newContentPaneproducto = new SimpleTableDemo();
            newContentPaneproducto.enable(false);
            newContentPaneproducto.setOpaque(true); //content panes must be opaque
            this.vPedido.frame2.setContentPane(productoSQL1);
            this.vPedido.frame2.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)this.vPedido.frame2.getUI()).setNorthPane(null);
            this.vPedido.frame2.pack();
            this.vPedido.frame2.setVisible(true);
            try {
               this.vPedido.frame2.setMaximum(true);
           } catch (PropertyVetoException ex) {
               Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
           }
            this.vPedido.nuevo.repaint();         
        }
        this.dispose();
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton14;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    public javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JToggleButton jToggleButton6;
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


