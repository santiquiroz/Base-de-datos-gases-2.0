
package Aplicacion.Vistas;

import Aplicacion.Controller.IndexController;
import System.DataBase.Core.ConvertidorAMatriz;
import System.DataBase.Core.DataBase;
import System.Helper.IO;
import System.Helper.ManejoDeStrings;
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
 * @author santiquiroz e isamelTheMemeMaster
 * @version 0.5.1
 * @since JConexionDB 1.0
 */
public class Pedido extends View implements IView{
   DataBase db = new DataBase();
   String fechaActual;
   String usuario,telefono,nombre,fechaRegistro,puntos,empresarial,comun,idMunicipio;
   String ultimoFecha,ultimoNumero,ultimoDireccion,ultimoNota,ultimoPrecioTotal,ultimoEstado,ultimoIdUsuario,ultimoIdEmpleado,ultimoIdCliente,ultimoTipo,ultimoIdMunicipio;
   String[] cabecera;
   SimpleTableDemo productoSQL;
   public String today,ultimoCondonado,ultimoPuntos;
   public ArrayList productos;
   public ArrayList ultimoProductos;
   public LinkedList productosInsercion;
   public ArrayList ultimosPedidos,ultimoPedido,datosCliente; 
        JInternalFrame frame = new JInternalFrame("pedido viejo");
        JInternalFrame frame2 = new JInternalFrame("pedido nuevo");
        JInternalFrame frame3 = new JInternalFrame("product");
    private String ultimoObsequio;
    private String ultimoPuntosDescontadosPorObsequio;
    private ArrayList productosconsulta;
    private ArrayList busquedaProductos;
   
    public Pedido(String telefono,String q) {
        productos = new ArrayList();
        productosInsercion = new LinkedList();
        ultimoProductos = new ArrayList();
        ultimosPedidos=new ArrayList();
        db = new DataBase();
        db.excecuteQuery("SELECT CURRENT_DATE()");
        fechaActual=db.getDato(0,0);
        this.telefono=telefono;
        usuario=q;
        initComponents();
        //this.setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("Nuevo pedido");
        setLocationRelativeTo(null);
        this.setResizable(false);
        llenar(telefono);
       try {
           frame.setMaximum(true);
       } catch (PropertyVetoException ex) {
           Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           frame2.setMaximum(true);
       } catch (PropertyVetoException ex) {
           Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           frame3.setMaximum(true);
       } catch (PropertyVetoException ex) {
           Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public void llenar(String telefono){
        //obteniendo datos del cliente
       db=new DataBase();
       datosCliente = db.excecuteQuery("SELECT * FROM cliente WHERE telefono='"+ telefono+"'");
       nombre=db.getDato(0,1);
       fechaRegistro=db.getDato(0,2);
       puntos=db.getDato(0,3);
       empresarial=db.getDato(0,4);
       comun=db.getDato(0,5);
       idMunicipio=db.getDato(0,6);
       db = new DataBase();
       //obteniendo el ultimo pedido que realizo el cliente con su rol de empresarial o comun
       if(comun.equals("1")){
           System.out.println("control");
           ultimosPedidos = db.excecuteQuery("SELECT * FROM ((SELECT * FROM pedido WHERE(tipo = 'comun' AND id_cliente LIKE '"+telefono+"')) AS pedidosportipo) WHERE fecha LIKE (SELECT MAX(fecha) FROM ((SELECT * FROM pedido WHERE(tipo = 'comun' AND id_cliente LIKE '"+telefono+"'))AS pedidosportipo2))");
       
       }
       else{
           ultimosPedidos = db.excecuteQuery("SELECT * FROM ((SELECT * FROM pedido WHERE(tipo = 'empresarial' AND id_cliente LIKE '"+telefono+"')) AS pedidosportipo) WHERE fecha LIKE (SELECT MAX(fecha) FROM ((SELECT * FROM pedido WHERE(tipo = 'empresarial' AND id_cliente LIKE '"+telefono+"')) AS pedidosportipo2))");
       }
       
        //llenando pedido viejo en la interfaz grafica
        if(!db.isEmpty()){
            int ui = ultimosPedidos.size()-1;
            ArrayList ultimoPedido =  (ArrayList) ultimosPedidos.get(ui);
            ultimoFecha=(String) ultimoPedido.get(0);
            ultimoNumero=(String) ultimoPedido.get(1);
            ultimoDireccion=(String) ultimoPedido.get(2);
            ultimoNota=(String) ultimoPedido.get(3);
            ultimoPrecioTotal=(String) ultimoPedido.get(4);
            ultimoEstado=(String) ultimoPedido.get(5);
            ultimoIdUsuario=(String) ultimoPedido.get(6);
            ultimoIdEmpleado=(String) ultimoPedido.get(7);
            ultimoIdCliente=(String) ultimoPedido.get(8);
            ultimoTipo=(String) ultimoPedido.get(9);
            ultimoIdMunicipio = (String) ultimoPedido.get(10);
            ultimoCondonado = (String) ultimoPedido.get(11);
            ultimoPuntos = (String) ultimoPedido.get(12);
            ultimoObsequio = (String) ultimoPedido.get(13);
            ultimoPuntosDescontadosPorObsequio = (String) ultimoPedido.get(14);
            
            jTextField1.setText(ultimoFecha);
            
            jTextField2.setText(ultimoNumero);
            
            jTextField3.setText(ultimoDireccion);
            
            jTextArea1.setText(ultimoNota);
            
            jTextField4.setText(ultimoIdMunicipio);
            
            jTextField11.setText(ultimoIdCliente);
            
            jTextField9.setText(ultimoIdEmpleado);
            
            jTextField23.setText(ultimoIdUsuario);
            
            jTextField13.setText(""+(Float.parseFloat(ultimoPrecioTotal)+(Float.parseFloat(ultimoCondonado))));
            
            jTextField14.setText(ultimoCondonado);
            
            jTextField31.setText(ultimoPuntos);
            
            jTextField16.setText(ultimoPuntos);
            
            jTextField21.setText(ultimoPrecioTotal);
            
            jTextField27.setText(ultimoEstado);
            
            
            //obteniendo productos que compro la ultima vez
            
            
            db=new DataBase();
            if(ultimoTipo.equals("comun")){
                ultimoProductos=db.excecuteQuery("SELECT id_productom_codigo,consecutivo,nombre,color,peso,precio FROM productomxpedido WHERE (id_pedido_fecha = '"+ultimoFecha+"' AND id_pedido_numero = '"+ultimoNumero+"')");
            }
            else{
                ultimoProductos=db.excecuteQuery("SELECT id_productoe_codigo,consecutivo,nombre,color,peso,precio FROM productoexpedido WHERE (id_pedido_fecha = '"+ultimoFecha+"' AND id_pedido_numero = '"+ultimoNumero+"')");
            }
            //creando tabla de productos del ultimo predido
            String[] cabecera= new String[6];
            cabecera[0]="codigo";
            cabecera[1]="consecutivo";
            cabecera[2]="nombre";
            cabecera[3]="color";
            cabecera[4]="peso";
            cabecera[5]="precio";
            SimpleTableDemo viejoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(ultimoProductos,6).result(),"productos","",this,"todaLaFila");
            viejo.add( frame );
            SimpleTableDemo newContentPane = new SimpleTableDemo();
            newContentPane.setOpaque(true); 
            frame.setContentPane(viejoSQL);
            frame.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
        
            
        }
        else{
            ultimoProductos = new ArrayList(); 
            //creando tabla de productos del ultimo predido
        String[] cabecera= new String[6];
            cabecera[0]="codigo";
            cabecera[1]="consecutivo";
            cabecera[2]="nombre";
            cabecera[3]="color";
            cabecera[4]="peso";
            cabecera[5]="precio";
            SimpleTableDemo viejoSQL= new SimpleTableDemo(cabecera,new Object [0][6],"productos","Carrito",this,"todaLaFila");
            viejo.add( frame );
            SimpleTableDemo newContentPane = new SimpleTableDemo();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(viejoSQL);
            frame.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
        
        }
        
        
        //llenando datos del cliente en el pedido nuevo
        jTextField17.setText(puntos);
        jTextField31.setText(puntos);
        actualizarSistemaPuntos();
        db=new DataBase();
        jTextField12.setText(idMunicipio);
        db=new DataBase();
        ArrayList date = db.excecuteQuery("SELECT * FROM pedido WHERE fecha = (SELECT CURRENT_DATE())");
        if(db.isEmpty()){
            jTextField8.setText("1");
        }
        else{ 
            db=new DataBase();
            date = db.excecuteQuery("SELECT * FROM pedido WHERE fecha = (SELECT CURRENT_DATE())AND numero LIKE (SELECT MAX(num) FROM (SELECT CAST(numero AS INTEGER) AS num FROM pedido WHERE fecha = (SELECT CURRENT_DATE())) AS pedidoshoy)");
            ArrayList newdate =  (ArrayList) date.get(date.size()-1);
            int orden = Integer.parseInt((String) newdate.get(1))+1;
            jTextField8.setText(Integer.toString(orden));
        }
        db= new DataBase();
            ArrayList empleados = db.excecuteQuery("SELECT cedula, nombre FROM empleado");
        
            jComboBox2.removeAllItems();
            DefaultComboBoxModel modeloCombo2 = new DefaultComboBoxModel();
            ArrayList emp;
            for (int i = 0; i < empleados.size(); i++) {
                emp=(ArrayList)empleados.get(i);
                modeloCombo2.addElement(emp.get(1)+"-"+emp.get(0));
            }
        jComboBox2.setModel(modeloCombo2);
        String ultimoNombreEmpleado="";
        db = new DataBase();
        db.excecuteQuery("SELECT nombre FROM empleado WHERE cedula LIKE '"+ultimoIdEmpleado+"'");
        if (!db.isEmpty()) {
            ultimoNombreEmpleado=db.getDato(0,0);
        }
        jComboBox2.setSelectedItem(ultimoNombreEmpleado+"-"+ultimoIdEmpleado);
        
        jTextField10.setText(telefono);
        
        jTextField7.setText(fechaActual);
        jTextField24.setText(usuario);
        
        //Display the window.
        frame.pack();
        
        frame.setVisible(true);
        
        jTextField6.setText(jTextField3.getText());
        jTextArea2.setText(jTextArea1.getText());
        
        
       //llenando por defecto la tabla del nuevo pedido 
        cabecera= new String[6];
        cabecera[0]="codigo";
            cabecera[1]="consecutivo";
            cabecera[2]="nombre";
            cabecera[3]="color";
            cabecera[4]="peso";
            cabecera[5]="precio";
        SimpleTableDemo nuevoSQL= new SimpleTableDemo(cabecera,new Object[0][6],"productos","Carrito",this,"todaLaFila");
        nuevo.add( frame2 );
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SimpleTableDemo newContentPaneNuevo = new SimpleTableDemo();
        newContentPaneNuevo.enable(false);        
        newContentPaneNuevo.setOpaque(true); //content panes must be opaque
        frame2.setContentPane(nuevoSQL);
        frame2.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)frame2.getUI()).setNorthPane(null);
        frame2.pack();
        
        frame2.setVisible(true);
        
        //llenando por defecto la tabla de busqueda de los productos 
        db= new DataBase();
        if(comun.equals("1")){
             productosconsulta = db.excecuteQuery("SELECT codigo,nombre,peso,tipo FROM productom WHERE id_municipio = '"+idMunicipio+"'");
        }
        else{
            productosconsulta = db.excecuteQuery("SELECT codigo,nombre,peso,tipo FROM productoe WHERE id_cliente = '"+telefono+"'");
        }
        cabecera= new String[4];
            cabecera[0]="codigo";
            cabecera[1]="nombre";
            cabecera[2]="peso";
            cabecera[3]="tipo";
        if(!db.isEmpty()){
            productoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(productosconsulta,4).result(),"productos","Carrito",this,"todaLaFila");
        }
        else{
            productoSQL= new SimpleTableDemo(cabecera,new Object [0][4],"productos","Carrito",this,"todaLaFila");
        }
            productoslayout.add( frame3 );
            SimpleTableDemo newContentPaneproducto = new SimpleTableDemo();
            newContentPaneproducto.enable(false);
            newContentPaneproducto.setOpaque(true); //content panes must be opaque
            frame3.setContentPane(productoSQL);
            frame3.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frame3.getUI()).setNorthPane(null);
            frame3.pack();
            frame3.setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        nuevo = new javax.swing.JPanel();
        viejo = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jTextField12 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField31 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        productoslayout = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jTextField25 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ULTIMO PEDIDO");

        jButton1.setText("Cancelar");
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

        jLabel1.setText("Fecha");

        jLabel3.setText("Numero de pedido");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NUEVO PEDIDO");

        jLabel6.setText("Dirreccion");

        jTextField2.setEditable(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Municipio");

        jTextField4.setEditable(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel8.setText("Municipio");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel9.setText("Dirreccion");

        jLabel4.setText("Numero de pedido");

        jLabel10.setText("Fecha");

        jTextField7.setEditable(false);
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel11.setText("Empleado");

        jTextField9.setEditable(false);
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        jLabel12.setText("Telefono");

        jTextField10.setEditable(false);
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });

        jTextField11.setEditable(false);
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });

        jLabel13.setText("telefono");

        jLabel14.setText("Empleado");

        jButton4.setText("Descontar con puntos");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nuevoLayout = new javax.swing.GroupLayout(nuevo);
        nuevo.setLayout(nuevoLayout);
        nuevoLayout.setHorizontalGroup(
            nuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
        nuevoLayout.setVerticalGroup(
            nuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout viejoLayout = new javax.swing.GroupLayout(viejo);
        viejo.setLayout(viejoLayout);
        viejoLayout.setHorizontalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 405, Short.MAX_VALUE)
        );
        viejoLayout.setVerticalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        jLabel15.setText("Precio bruto");

        jLabel16.setText("Descuento");

        jTextField13.setEditable(false);
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setEditable(false);
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });

        jLabel18.setText("Puntos");

        jTextField16.setEditable(false);
        jTextField16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField16ActionPerformed(evt);
            }
        });

        jLabel19.setText("Precio bruto");

        jLabel22.setText("Puntos");

        jTextField17.setEditable(false);
        jTextField17.setText("0");
        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jTextField19.setEditable(false);
        jTextField19.setText("0");
        jTextField19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField19ActionPerformed(evt);
            }
        });

        jTextField20.setEditable(false);
        jTextField20.setText("0");
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jLabel23.setText("Precio neto");

        jTextField21.setEditable(false);
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jLabel24.setText("Precio neto");

        jTextField22.setEditable(false);
        jTextField22.setText("0");
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jLabel20.setText("Nota");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel21.setText("Nota");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("BUSCAR PRODUCTO");

        jButton2.setText("Registrar pedido");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel26.setText("Despachado por");

        jTextField23.setEditable(false);
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jLabel27.setText("Despachado por");

        jTextField24.setEditable(false);
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jLabel28.setText("Estado");

        jTextField27.setEditable(false);
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        jLabel29.setText("Estado");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "en cola", "en proceso", "pago", "fiado" }));

        jButton6.setText("x");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField12.setEditable(false);
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });

        jButton3.setText("Registrar  e imprimir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField31.setEditable(false);
        jTextField31.setText("0");
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        jLabel30.setText("Bodega:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        jComboBox6.setSelectedIndex(1);

        jLabel31.setText("Numero de facturas:");

        javax.swing.GroupLayout productoslayoutLayout = new javax.swing.GroupLayout(productoslayout);
        productoslayout.setLayout(productoslayoutLayout);
        productoslayoutLayout.setHorizontalGroup(
            productoslayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );
        productoslayoutLayout.setVerticalGroup(
            productoslayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTextField25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField25KeyPressed(evt);
            }
        });

        jLabel37.setText("Codigo");

        jLabel38.setText("Nombre");

        jTextField26.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField26KeyPressed(evt);
            }
        });

        jButton12.setText("Buscar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel41.setText("Peso");

        jTextField30.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField30KeyPressed(evt);
            }
        });

        jLabel42.setText("Tipo");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Liquido", "Envase", "Extintor", "Soldadura", "Gas industrial", "Otro", "Desconocido" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel42)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField30)
                        .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(220, 220, 220))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jButton12)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel26))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField23)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel20))
                                    .addGap(58, 58, 58)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField3)))))
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel27))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField24)
                                    .addComponent(jTextField10)
                                    .addComponent(jComboBox2, 0, 252, Short.MAX_VALUE)
                                    .addComponent(jTextField12)
                                    .addComponent(jScrollPane2)
                                    .addComponent(jTextField6)
                                    .addComponent(jTextField8)
                                    .addComponent(jTextField7)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel28))
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(106, 106, 106)
                                .addComponent(jTextField27))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(viejo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(107, 107, 107)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                            .addComponent(jTextField16))))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel29)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField22)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField19)
                                    .addComponent(jTextField20)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel30)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 343, Short.MAX_VALUE))
                            .addComponent(productoslayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel13))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 24, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(22, 22, 22)
                                        .addComponent(jLabel11))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel25)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                        .addComponent(viejo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(productoslayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(8, 8, 8)
                                .addComponent(jLabel28))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField9ActionPerformed

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        float puntos = Float.parseFloat(jTextField17.getText());
        float neto = Float.parseFloat(jTextField22.getText());
        float dif = neto-puntos;
        if (dif<0) {
            dif = dif * (-1);
            jTextField17.setText(dif+"");
            jTextField22.setText("0");
        }
        else{
            jTextField17.setText("0");
            jTextField22.setText(dif+"");
        }
        jButton4.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // obteniendo empleado selecto
       String c = (String)jComboBox2.getSelectedItem();
       String[] d = c.split("-");
       String cedulaEmpleado = d[1];
       String bodega = (String)jComboBox4.getSelectedItem();
       String tipoInsercion = "empresarial";
       
       if(comun.equals("1")){
           tipoInsercion="comun";
       }
       //calculando puntos ganados en la compra
       String puntosCompra = ((Float.parseFloat(jTextField17.getText()))-(Float.parseFloat(jTextField31.getText())))+"";
       //insertando pedido 
        db= new DataBase();
        db.insertar3("INSERT INTO pedido(fecha,horaRegistro,numero,direccion,nota,precio_total,estado,id_usuario,id_empleado,id_cliente,tipo,id_municipio,condonado,puntos,obsequio,puntos_descontados_por_obsequio, bodega) VALUES('"+fechaActual+"',(SELECT CURRENT_TIME()),'"+jTextField8.getText()+"','"+jTextField6.getText()+"','"+jTextArea2.getText()+"','"+jTextField22.getText()+"','"+jComboBox3.getSelectedItem()+"','"+this.usuario+"','"+cedulaEmpleado+"','"+telefono+"','"+tipoInsercion+"','"+jTextField12.getText()+"','"+jTextField19.getText()+"','"+puntosCompra+"','ninguno','0.0','"+bodega+"')");
 
       int numeroInserciones = this.productosInsercion.size()-1;
       String consecutivoInsercion, nombreInsercion, colorInsercion,pesoInsercion,puntosInsercion,codigoInsercion,municipioInsercion,clienteInsercion,fechaInsercion,numeroInsercion;
       String precioInsercion;
       fechaInsercion = fechaActual;
       numeroInsercion = jTextField8.getText();
       if(comun.equals("1")){
        if(numeroInserciones >= 0)
         for (int i = 0; i <= numeroInserciones; i++) {
             ArrayList producto= (ArrayList)productosInsercion.get(i);
            consecutivoInsercion = (String)producto.get(0);
            nombreInsercion = (String)producto.get(1);
            colorInsercion = (String)producto.get(2);
            pesoInsercion = (String)producto.get(3);
            precioInsercion = (String)producto.get(4);
            puntosInsercion = (String)producto.get(5);
            codigoInsercion = (String)producto.get(6);
            municipioInsercion = jTextField12.getText();
             db= new DataBase();
             db.insertar3("INSERT INTO productomxpedido(consecutivo,nombre,color,peso,precio,puntos,id_productom_codigo,id_productom_id_municipio,id_pedido_fecha,id_pedido_numero) VALUES('"+(i+1)+"','"+nombreInsercion+"','"+colorInsercion+"','"+pesoInsercion+"','"+precioInsercion+"','"+puntosInsercion+"','"+codigoInsercion+"','"+municipioInsercion+"','"+fechaInsercion+"','"+numeroInsercion+"')");
         }
       }
       else{
           for (int i = 0; i <= numeroInserciones; i++) {
                ArrayList producto= (ArrayList)productosInsercion.get(i);
            consecutivoInsercion = (String)producto.get(0);
            nombreInsercion = (String)producto.get(1);
            colorInsercion = (String)producto.get(2);
            pesoInsercion = (String)producto.get(3);
            precioInsercion = (String)producto.get(4);
            puntosInsercion = (String)producto.get(5);
            codigoInsercion = (String)producto.get(6);
            clienteInsercion = telefono;
             db= new DataBase();
             db.insertar3("INSERT INTO productoexpedido(consecutivo,nombre,color,peso,precio,puntos,id_productoe_codigo,id_productoe_id_cliente,id_pedido_fecha,id_pedido_numero) VALUES('"+(i+1)+"','"+nombreInsercion+"','"+colorInsercion+"','"+pesoInsercion+"','"+precioInsercion+"','"+puntosInsercion+"','"+codigoInsercion+"','"+clienteInsercion+"','"+fechaInsercion+"','"+numeroInsercion+"')");
         }
       }
        db.actualizar("UPDATE cliente SET puntos = '"+jTextField17.getText()+"' WHERE telefono LIKE '"+telefono+"'");
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField25KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField25KeyPressed
        //if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         //   BuscarProducto();
        //}
    }//GEN-LAST:event_jTextField25KeyPressed

    private void jTextField26KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField26KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        }
    }//GEN-LAST:event_jTextField26KeyPressed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        this.productoslayout.removeAll();
        String[] cabecera= new String[4];
        cabecera[0]="codigo";
        cabecera[1]="nombre";
        cabecera[2]="peso";
        cabecera[3]="tipo";
       
            db=new DataBase();
            String tipoS=(String)jComboBox5.getSelectedItem() ;
            if(tipoS.equals("Desconocido")){
                tipoS="";
            }
            if(comun.equals("1")){
                busquedaProductos=db.excecuteQuery("SELECT codigo, nombre, peso,tipo FROM productom WHERE id_municipio ='"+idMunicipio+"' AND codigo LIKE '"+jTextField25.getText()+"%' AND nombre LIKE '"+jTextField26.getText()+"%' AND peso LIKE '"+jTextField30.getText()+"%' AND tipo LIKE '"+tipoS+"%'");
            }
            else{
                busquedaProductos=db.excecuteQuery("SELECT codigo, nombre, peso,tipo FROM productoe WHERE id_cliente ='"+telefono+"'AND codigo LIKE '"+jTextField25.getText()+"%' AND nombre LIKE '"+jTextField26.getText()+"%' AND peso LIKE '"+jTextField30.getText()+"%' AND tipo LIKE '"+tipoS+"%'");
            }
            if(db.isEmpty()){
                SimpleTableDemo productoSQL= new SimpleTableDemo(cabecera,new Object [0][7],"productos","Carrito",this,"todaLaFila");
                productoslayout.add( frame3 );
                SimpleTableDemo newContentPaneproducto = new SimpleTableDemo();
                newContentPaneproducto.enable(false);
                newContentPaneproducto.setOpaque(true); //content panes must be opaque
                frame3.setContentPane(productoSQL);
                frame3.setBorder(null);
                ((javax.swing.plaf.basic.BasicInternalFrameUI)frame3.getUI()).setNorthPane(null);
                frame3.pack();
                frame3.setVisible(true);
            }
            else{
                SimpleTableDemo productoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(busquedaProductos,4).result(),"productos","Carrito",this,"todaLaFila");
                productoslayout.add( frame3 );
                SimpleTableDemo newContentPaneproducto = new SimpleTableDemo();
                newContentPaneproducto.enable(false);
                newContentPaneproducto.setOpaque(true); //content panes must be opaque
                frame3.setContentPane(productoSQL);
                frame3.setBorder(null);
                ((javax.swing.plaf.basic.BasicInternalFrameUI)frame3.getUI()).setNorthPane(null);
                frame3.pack();
                frame3.setVisible(true);
            } 
       try {
           frame3.setMaximum(true);
       } catch (PropertyVetoException ex) {
           Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
       }
        this.productoslayout.repaint();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField30KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField30KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField30KeyPressed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        float bruto = Float.parseFloat(jTextField20.getText());
        float neto = Float.parseFloat(jTextField22.getText());
        float p = Float.parseFloat(jTextField17.getText());
        float puntos = (bruto-neto)+p;
        jTextField17.setText(""+puntos);
        jTextField19.setText("0");
        jTextField22.setText(jTextField20.getText());
        jButton4.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      // obteniendo empleado selecto
       String c = (String)jComboBox2.getSelectedItem();
       String[] d = c.split("-");
       String cedulaEmpleado = d[1];
       String bodega = (String)jComboBox4.getSelectedItem();
       String tipoInsercion = "empresarial";
       
       if(comun.equals("1")){
           tipoInsercion="comun";
       }
       //calculando puntos ganados en la compra
       
       String puntosCompra = ((Float.parseFloat(jTextField17.getText()))-(Float.parseFloat(jTextField31.getText())))+"";
      //insertando en la pedido
        db= new DataBase();
        db.insertar3("INSERT INTO pedido(fecha,horaRegistro,numero,direccion,nota,precio_total,estado,id_usuario,id_empleado,id_cliente,tipo,id_municipio,condonado,puntos,obsequio,puntos_descontados_por_obsequio, bodega) VALUES('"+fechaActual+"',(SELECT CURRENT_TIME()),'"+jTextField8.getText()+"','"+jTextField6.getText()+"','"+jTextArea2.getText()+"','"+jTextField22.getText()+"','"+jComboBox3.getSelectedItem()+"','"+this.usuario+"','"+cedulaEmpleado+"','"+telefono+"','"+tipoInsercion+"','"+jTextField12.getText()+"','"+jTextField19.getText()+"','"+puntosCompra+"','ninguno','0.0','"+bodega+"')");
 
       int numeroInserciones = this.productosInsercion.size()-1;
       String consecutivoInsercion, nombreInsercion, colorInsercion,pesoInsercion,puntosInsercion,codigoInsercion,municipioInsercion,clienteInsercion,fechaInsercion,numeroInsercion;
       String precioInsercion;
       fechaInsercion = fechaActual;
       numeroInsercion = jTextField8.getText();
       if(comun.equals("1")){
        if(numeroInserciones >= 0)
         for (int i = 0; i <= numeroInserciones; i++) {
             ArrayList producto= (ArrayList)productosInsercion.get(i);
            consecutivoInsercion = (String)producto.get(0);
            nombreInsercion = (String)producto.get(1);
            colorInsercion = (String)producto.get(2);
            pesoInsercion = (String)producto.get(3);
            precioInsercion = (String)producto.get(4);
            puntosInsercion = (String)producto.get(5);
            codigoInsercion = (String)producto.get(6);
            municipioInsercion = jTextField12.getText();
             db= new DataBase();
             db.insertar3("INSERT INTO productomxpedido(consecutivo,nombre,color,peso,precio,puntos,id_productom_codigo,id_productom_id_municipio,id_pedido_fecha,id_pedido_numero) VALUES('"+(i+1)+"','"+nombreInsercion+"','"+colorInsercion+"','"+pesoInsercion+"','"+precioInsercion+"','"+puntosInsercion+"','"+codigoInsercion+"','"+municipioInsercion+"','"+fechaInsercion+"','"+numeroInsercion+"')");
         }
       }
       else{
           for (int i = 0; i <= numeroInserciones; i++) {
                ArrayList producto= (ArrayList)productosInsercion.get(i);
            consecutivoInsercion = (String)producto.get(0);
            nombreInsercion = (String)producto.get(1);
            colorInsercion = (String)producto.get(2);
            pesoInsercion = (String)producto.get(3);
            precioInsercion = (String)producto.get(4);
            puntosInsercion = (String)producto.get(5);
            codigoInsercion = (String)producto.get(6);
            clienteInsercion = telefono;
             db= new DataBase();
             db.insertar3("INSERT INTO productoexpedido(consecutivo,nombre,color,peso,precio,puntos,id_productoe_codigo,id_productoe_id_cliente,id_pedido_fecha,id_pedido_numero) VALUES('"+(i+1)+"','"+nombreInsercion+"','"+colorInsercion+"','"+pesoInsercion+"','"+precioInsercion+"','"+puntosInsercion+"','"+codigoInsercion+"','"+clienteInsercion+"','"+fechaInsercion+"','"+numeroInsercion+"')");
         }
       }
        db.actualizar("UPDATE cliente SET puntos = '"+jTextField17.getText()+"' WHERE telefono LIKE '"+telefono+"'");
        String stringProductos="|Nombre   |Color  |Peso|Precio    |\n";
        int numprod = this.productosInsercion.size();
        ManejoDeStrings ms = new ManejoDeStrings();
        for (int i = 0; i < numprod; i++) {
            stringProductos=stringProductos+ms.rellenar((ms.recortar((String)((ArrayList)productosInsercion.get(i)).get(1),0,10))," ",11,true)+ms.rellenar((ms.recortar((String)((ArrayList)productosInsercion.get(i)).get(2),0,7))," ",8,true)+ms.rellenar((ms.recortar((String)((ArrayList)productosInsercion.get(i)).get(3),0,4))," ",5,true)+ms.rellenar((ms.recortar((String)((ArrayList)productosInsercion.get(i)).get(4),0,10))," ",11,true)+"\n";
        }
        db= new DataBase();
            db.excecuteQuery("SELECT nombre FROM cliente WHERE telefono = '"+telefono+"'");
            String nombreCliente=db.getDato(0,0);
        String nombreMensajero=((((String)jComboBox2.getSelectedItem()).split("-")))[0];
        //imprimiendo facturas
        int numFacturas=Integer.parseInt((String)jComboBox6.getSelectedItem());
        for (int i = 0; i < numFacturas; i++) {
           new ImpresionTermica(jTextField7.getText(),jTextField8.getText(),nombreMensajero,nombreCliente,telefono, jTextField6.getText(), jTextField12.getText(),jTextArea2.getText(),stringProductos,ms.rellenar(jTextField22.getText()," ",21,false),ms.rellenar(jTextField17.getText()," ",10,false)).print();
        }
        
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField16;
    public javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    public javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    public javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    public javax.swing.JPanel nuevo;
    private javax.swing.JPanel productoslayout;
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

    private void actualizarSistemaPuntos() {
       float puntosActuales = Float.parseFloat(jTextField17.getText());
        DataBase b = new DataBase();
        b.excecuteQuery("SELECT limPuntos FROM static");
        float topePuntos = Float.parseFloat(b.getDato(0,0));
        if(topePuntos<=puntosActuales){
            jButton4.setEnabled(true);
        }
        else{
            jButton4.setEnabled(false);    
        }
    }
    
}


