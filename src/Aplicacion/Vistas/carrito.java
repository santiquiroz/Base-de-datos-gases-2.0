package Aplicacion.Vistas;
import Aplicacion.Controller.IndexController;
import System.DataBase.Core.ConvertidorAMatriz;
import System.DataBase.Core.DataBase;
import System.Helper.IO;
import System.MVC.Core.IView;
import System.MVC.Core.View;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Frame de la eliminacion del empleado
 * @author santiquiroz e isamelTheMemeMaster
 * @version 0.8.1(actualmente desechado)
 * @since JConexionDB 1.0
 */
public class carrito extends View implements IView{
    DataBase db = new DataBase();
    String codigo, nombre, peso,ti,descripcion,preciobase, telefono,ultimafecha;
    Double aumentoMunicipio,aumentoCliente,aumentoProducto,aumentoTotal,precioFinal,ultimoprecio,aumentoClientein;
    Pedido vPedido;
    ArrayList infocliente;
    public carrito(String idprod,Pedido ventanaPadre) {
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
        telefono=ventanaPadre.telefono;
        System.out.println((ArrayList)ventanaPadre.vliente);
        infocliente = (ArrayList)ventanaPadre.vliente.get(0);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        codigo=(String)datos.get(0);
        nombre=(String)datos.get(1);
        peso=(String)datos.get(2);
        ti=(String)datos.get(3);
        descripcion=(String)datos.get(4);
        preciobase=(String)datos.get(5);
        
        jTextField1.setText(codigo);
        jTextField2.setText(nombre);
        jTextField3.setText(peso);
        jTextArea1.setText(descripcion);
        
        calcularAumentos();
        jTextField5.setText(this.aumentoTotal.toString());
        DataBase db = new DataBase();
        
        jTextField4.setText(precioFinal.toString());
        

        setVisible(true);
        setTitle("Agregar producto");
        setLocationRelativeTo(null);
    }
    public Double calcularAumentos(){
        db=new DataBase();
        //ultimo precio ofrecido el producto y fecha de su pedido        
        ArrayList ultimo_precio_consulta=db.excecuteQuery("SELECT ultimoprecio, fecha FROM (SELECT ultimoprecio, fecha FROM hclientexproducto WHERE telefono = '"+telefono+"' AND codigo = '"+codigo+"') WHERE fecha = Max(fecha)");
        
        //si nunca se ofrecio
        if(db.isEmpty()){
            System.out.println("este producto nunca fue ofrecido");
            db=new DataBase();
            db.excecuteQuery("SELECT precioBase FROM producto WHERE codigo = '"+codigo+"'");
            this.ultimoprecio = Double.parseDouble(db.getDato(0,0)) ;
            //ultimo aumento por municipio
            
            db=new DataBase();
            db.excecuteQuery("SELECT * FROM (SELECT * FROM aumentomun WHERE(nombremunicipio = '"+(String)infocliente.get(6)+"')) WHERE(fechamodificacion = MAX(fechamodificacion)");
            if(db.isEmpty()){
                this.aumentoMunicipio=0.0;
            }
            else{
                //verifico si el cliente es un cliente comun para poder subirle por municipio
                if(((ArrayList)(vPedido.vliente.get(0))).get(4).equals("1")){
                    this.aumentoMunicipio=Double.parseDouble(db.getDato(0, 1));
                }
                else{
                    this.aumentoMunicipio=0.0;
                }
            }
            //ultimo aumento por cliente
            db = new DataBase();
            db.excecuteQuery("SELECT * FROM (SELECT * FROM aumentoclien WHERE telefono = '"+telefono+"') WHERE fechamodificacion = MAX(fechamodificacion)");
            if(db.isEmpty()){
                this.aumentoCliente=0.0;
            }
            else{
                this.aumentoCliente=Double.parseDouble(db.getDato(0, 1));
            }
            //ultimo aumento por cliente en el producto en especifico            
            db = new DataBase();
            db.excecuteQuery("SELECT * FROM (SELECT * FROM aumentoclienin WHERE telefono = '"+telefono+"' AND codigo = '"+codigo+"') WHERE fechamodificacion = MAX(fechamodificacion)");
            if(db.isEmpty()){
                this.aumentoClientein=0.0;
            }
            else{
                this.aumentoClientein=Double.parseDouble(db.getDato(0, 1));
            }
            
        }
        //si se ofrecio se buscan los aumentos que se hicieron desde la ultima oferta hasta hoy
        else{
            System.out.println("este producto fue ofrecido almenos una vez");
            ArrayList ultimo_precio_tupla =(ArrayList) ultimo_precio_consulta.get(0);
            String ultima_fecha =(String) ultimo_precio_tupla.get(1);
            String ultimo_precio = (String) ultimo_precio_tupla.get(0);
            //aumentos por municipio
            db = new DataBase();
            db.excecuteQuery("SELECT SUM(valor) FROM (SELECT * FROM aumentomun WHERE(nombremunicipio = '"+(String)infocliente.get(6)+"')) WHERE(fechamodificacion > '"+ultima_fecha+"')");
            if(db.isEmpty()){
                this.aumentoMunicipio=0.0;
            }
            else{
                //verifico si el cliente es un cliente comun para poder subirle por municipio
                if(((ArrayList)(vPedido.vliente.get(0))).get(4).equals("1")){
                   String sumaMuniciopio= db.getDato(0, 1);
                    this.aumentoMunicipio= Double.parseDouble(sumaMuniciopio);
                }
                else{
                    this.aumentoMunicipio=0.0;
                }
            }
            //aumentos por cliente
            db=new DataBase();
            db.excecuteQuery("SELECT SUM(valor) FROM (SELECT * FROM aumentoclien WHERE telefono = '"+telefono+"') WHERE fechamodificacion > '"+ultima_fecha+"'");
            if(db.isEmpty()){
                this.aumentoCliente=0.0;
            }
            else{
                this.aumentoCliente=Double.parseDouble(db.getDato(0, 1));
            }
            //ultimo aumento por cliente en el producto en especifico            
            db = new DataBase();
            db.excecuteQuery("SELECT SUM(VALOR) FROM (SELECT * FROM aumentoclienin WHERE telefono = '"+telefono+"' AND codigo = '"+codigo+"') WHERE fechamodificacion > '"+ultima_fecha+"'");
            if(db.isEmpty()){
                this.aumentoClientein=0.0;
            }
            else{
                this.aumentoClientein=Double.parseDouble(db.getDato(0, 1));
            }
            this.ultimoprecio= Double.parseDouble(ultimo_precio);
        }
        
        this.aumentoTotal=this.aumentoCliente+this.aumentoClientein+this.aumentoMunicipio;
        this.precioFinal=this.aumentoTotal+Double.parseDouble(this.preciobase);
        System.out.println(precioFinal);
        return(precioFinal);
        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton6 = new javax.swing.JToggleButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();

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

        jLabel41.setText("Descripcion");

        jButton14.setText("Agregar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel18.setText(" Precio por unidad final");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel19.setText("Aumento de precio por unidad");

        jTextField5.setEditable(false);
        jTextField5.setText("0");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jToggleButton6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addComponent(jLabel18)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19)))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton14))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(jTextField4)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel39))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel34)))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                                        .addComponent(jSpinner1))))))
                    .addComponent(jLabel33))
                .addContainerGap(165, Short.MAX_VALUE))
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
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        
        for (int i = 0; i < numerodeproductos; i++) {
            ArrayList producto = new ArrayList();
            ArrayList productoinsercion = new ArrayList();
            producto.add(codigo);
            productoinsercion.add(codigo);
            producto.add(nombre);
            productoinsercion.add(nombre);
            producto.add(peso);
            productoinsercion.add(peso);
            productoinsercion.add(ti);
            //producto.add(descripcion);
            //productoinsercion.add(descripcion);
            String f = jTextField4.getText();
            producto.add(f);
            productoinsercion.add(f);
            db = new DataBase();
            db.excecuteQuery("SELECT puntosxkilo FROM static");
            String pesoxkilo = db.getDato(0,0);
            System.out.println(pesoxkilo);
            String puntos;
            Double puntoso = ((Double.parseDouble(peso)) * (Double.parseDouble(pesoxkilo)));
            puntos = puntoso.toString();
            producto.add(puntos);
            productoinsercion.add(puntos);
            System.out.println(producto);
            System.out.println(productoinsercion);
            //actualizando puntos
            Double currentpuntos= Double.parseDouble(vPedido.jTextField17.getText()) + puntoso;
            this.vPedido.jTextField17.setText((currentpuntos).toString());
            //acttualizando precio bruto
            Double currentpreciobruto= Double.parseDouble(vPedido.jTextField20.getText()) + Double.parseDouble(f);
            this.vPedido.jTextField20.setText((currentpreciobruto).toString());
            //actualizando precio neto
            Double currentprecioneto= Double.parseDouble(vPedido.jTextField22.getText()) + Double.parseDouble(f);
            this.vPedido.jTextField22.setText((currentprecioneto).toString());
            this.vPedido.productos.add(producto);
            this.vPedido.productosInsercion.add(productoinsercion);
            
        }
        
        if(numerodeproductos > 0){
            this.vPedido.nuevo.removeAll();
            String[] cabecera= new String[5];
                    cabecera[0]="codigo";
                    cabecera[1]="nombre";
                    cabecera[2]="peso";
                    cabecera[3]="precio";
                    cabecera[4]="puntos";     
            SimpleTableDemo productoSQL1= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(this.vPedido.productos,5).result(),"productos","Carrito",this,"todaLaFila");
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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
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


