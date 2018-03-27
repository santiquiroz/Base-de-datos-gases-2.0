
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
 * @author santiquiroz e isamelTheMemeMaster
 * @version 0.5.1
 * @since JConexionDB 1.0
 */
public class Pedido extends View implements IView{
   DataBase db = new DataBase();
   Fecha fecha = new Fecha();
   String usuario,telefono;
   public String today;
   public ArrayList productos;
   public ArrayList productosv;
   public LinkedList productosInsercion;
   public ArrayList ultimo1,vliente; 
        JInternalFrame frame = new JInternalFrame("pedido viejo");
        JInternalFrame frame2 = new JInternalFrame("pedido nuevo");
        JInternalFrame frame3 = new JInternalFrame("product");
   
    public Pedido(String telefono,String usrer) {
        productos = new ArrayList();
        productosInsercion = new LinkedList();
        productosv = new ArrayList();
        this.telefono=telefono;
        usuario=usrer;
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setTitle("Nuevo pedido");
        setLocationRelativeTo(null);
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
       db=new DataBase();
            
            vliente = db.excecuteQuery("SELECT * FROM cliente WHERE telefono="+ telefono);
        db = new DataBase();
        //ultimo pedido
         ultimo1 = db.excecuteQuery("SELECT * FROM pedido WHERE fecha LIKE (SELECT MAX(fecha) FROM pedido) AND telefono LIKE "+telefono);
        
        
        
        //llenando pedido viejo
        if(!db.isEmpty()){
            int ui = ultimo1.size()-1;
            ArrayList hola =  (ArrayList) ultimo1.get(ui);
            System.out.println(hola);
            
            int eso = vliente.size()-1;
            ArrayList newvliente =  (ArrayList) vliente.get(eso);
            
            System.out.println(vliente);
            
            jTextField1.setText((String) hola.get(0));
            
            jTextField2.setText((String) hola.get(1));
            
            jTextField3.setText((String) hola.get(2));
            
            jTextField6.setText((String) hola.get(2));
            
            //puntos que tenia la ultima vez
            jTextField16.setText((String) hola.get(4));
            
            jTextArea1.setText((String) hola.get(7));
            jTextArea2.setText((String) hola.get(7));
            
            jTextField4.setText((String) newvliente.get(6));
            
            jTextField10.setText((String) hola.get(4));
            jTextField11.setText((String) hola.get(4));
            
            jTextField9.setText((String) hola.get(3));
            
            jTextField13.setText((String) hola.get(8));
            
            jTextField14.setText((String) hola.get(5));
            
            jTextField15.setText((String) hola.get(6));
            
            jTextField17.setText("0");
            jTextField20.setText("0");
            jTextField22.setText("0");
            
            jTextField21.setText((String) hola.get(9));
            
            
            db=new DataBase();
            //obteniendo productos que compro la ultima vez
             productosv = db.excecuteQuery("SELECT producto.codigo,nombre,peso,precio,puntos,consecutivo FROM productoxpedido INNER JOIN producto ON productoxpedido.codigo = producto.codigo AND fecha LIKE "+"'"+((String)hola.get(0))+"'"+" AND numero LIKE "+"'"+((String)hola.get(1))+"'");
            int ala = productosv.size()-1;
            ArrayList newproductos =  (ArrayList) productosv.get(ala);
            System.out.println(productosv);
            String[] cabecera= new String[6];
            cabecera[0]="codigo";
            cabecera[1]="nombre";
            cabecera[2]="peso";
            cabecera[3]="precio";
            cabecera[4]="puntos";
            cabecera[5]="consecutivo";
            SimpleTableDemo viejoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(productosv,6).result(),"productos","Carrito",this,"todaLaFila");
            viejo.add( frame );
            SimpleTableDemo newContentPane = new SimpleTableDemo();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(viejoSQL);
            frame.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
            
        }
        else{
            String[] cabecera= new String[5];
                cabecera[0]="codigo";
                cabecera[1]="nombre";
                cabecera[2]="peso";
                cabecera[3]="precio";
                cabecera[4]="puntos";
                SimpleTableDemo viejoSQL= new SimpleTableDemo(cabecera,new Object[0][5],"productos","Carrito",this,"todaLaFila");
                viejo.add( frame );
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                SimpleTableDemo newContentPane = new SimpleTableDemo();
                newContentPane.setOpaque(true); //content panes must be opaque
                frame.setContentPane(viejoSQL);
                frame.setBorder(null);
                ((javax.swing.plaf.basic.BasicInternalFrameUI)frame.getUI()).setNorthPane(null);
        }
        System.out.println("creo el viejo");
        db=new DataBase();
        jComboBox1.setSelectedItem(db.excecuteQuery("SELECT municipio FROM cliente WHERE telefono = '"+telefono+"'"));
        db=new DataBase();
        today = (fecha.toString("A-M-D"));
        ArrayList date = db.excecuteQuery("SELECT * FROM pedido WHERE fecha ="+"'"+today+"'");
        if(db.isEmpty()){
            jTextField8.setText("1");
        }
        else{ 
            db=new DataBase();
            date = db.excecuteQuery("SELECT * FROM pedido WHERE fecha = "+"'"+today+"'"+" AND numero LIKE (SELECT MAX(numero) FROM pedido)");
            ArrayList newdate =  (ArrayList) date.get(date.size()-1);
            int orden = Integer.parseInt((String) newdate.get(1))+1;
            jTextField8.setText(Integer.toString(orden));
        }
        //info cliente
        
        
        //llenando pedido nuevo
        db= new DataBase();
            ArrayList municipios = db.excecuteQuery("SELECT nombremunicipio FROM municipio");
        
            jComboBox1.removeAllItems();
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            for (int i = 0; i < municipios.size(); i++) {
                modeloCombo.addElement(((ArrayList)municipios.get(i)).get(0));
            }
        jComboBox1.setModel(modeloCombo); 
        
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
        
        jTextField10.setText(telefono);
        
        jTextField7.setText(fecha.toString("A-M-D"));
        jTextField24.setText(usuario);
        
        //Display the window.
        frame.pack();
        
        frame.setVisible(true);
        
       //llenando por defecto la tabla del nuevo pedido 
        String[] cabecera= new String[5];
        cabecera[0]="codigo";
        cabecera[1]="nombre";
        cabecera[2]="peso";
        cabecera[3]="precio";
        cabecera[4]="puntos";
        SimpleTableDemo nuevoSQL= new SimpleTableDemo(cabecera,new Object[0][5],"productos","Carrito",this,"todaLaFila");
        nuevo.add( frame2 );
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SimpleTableDemo newContentPane = new SimpleTableDemo();
        newContentPane.enable(false);        
        newContentPane.setOpaque(true); //content panes must be opaque
        frame2.setContentPane(nuevoSQL);
        frame2.setBorder(null);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)frame2.getUI()).setNorthPane(null);
        frame2.pack();
        
        frame2.setVisible(true);
        
        System.out.println("creo el nuevo vacio");
        
        //llenando por defecto la tabla de los productos 
        ArrayList productosconsulta = db.excecuteQuery("SELECT * FROM producto WHERE disponible = 1");
            int f = productosconsulta.size()-1;
            ArrayList newproductos =  (ArrayList) productosconsulta.get(f);
            System.out.println(productosconsulta);
            cabecera= new String[7];
            cabecera[0]="codigo";
            cabecera[1]="nombre";
            cabecera[2]="peso";
            cabecera[3]="tipo";
            cabecera[4]="descripcion";
            cabecera[5]="precio base";
            cabecera[6]="disponible";
            SimpleTableDemo productoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(productosconsulta,7).result(),"productos","Carrito",this,"todaLaFila");
            productoslayout.add( frame3 );
            SimpleTableDemo newContentPaneproducto = new SimpleTableDemo();
            newContentPaneproducto.enable(false);
            newContentPaneproducto.setOpaque(true); //content panes must be opaque
            frame3.setContentPane(productoSQL);
            frame3.setBorder(null);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frame3.getUI()).setNorthPane(null);
            frame3.pack();
            frame3.setVisible(true);
            System.out.println("creo los productos por defecto");
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
        jButton5 = new javax.swing.JButton();
        nuevo = new javax.swing.JPanel();
        viejo = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
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
        productoslayout = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jTextField25 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();

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

        jButton4.setText("Descontar %");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Obsequio:");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nuevoLayout = new javax.swing.GroupLayout(nuevo);
        nuevo.setLayout(nuevoLayout);
        nuevoLayout.setHorizontalGroup(
            nuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        nuevoLayout.setVerticalGroup(
            nuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout viejoLayout = new javax.swing.GroupLayout(viejo);
        viejo.setLayout(viejoLayout);
        viejoLayout.setHorizontalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        viejoLayout.setVerticalGroup(
            viejoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
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

        jLabel17.setText("Obsequio");

        jTextField15.setEditable(false);
        jTextField15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField15ActionPerformed(evt);
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

        jTextField18.setEditable(false);
        jTextField18.setText("0");
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
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

        javax.swing.GroupLayout productoslayoutLayout = new javax.swing.GroupLayout(productoslayout);
        productoslayout.setLayout(productoslayoutLayout);
        productoslayoutLayout.setHorizontalGroup(
            productoslayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        productoslayoutLayout.setVerticalGroup(
            productoslayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton2.setText("Registrar pedido");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField25.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField25KeyPressed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("INGRESE LOS DATOS CONOCIDOS");

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

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Liquido", "Envase", "Otro", "Desconocido" }));
        jComboBox5.setSelectedIndex(3);
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel43.setText("Disponible");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desconocido", "Disponible", "No Disponible" }));
        jComboBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox7ActionPerformed(evt);
            }
        });

        jLabel44.setText("Precio Base");

        jTextField31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField31KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addGap(100, 100, 100)
                        .addComponent(jComboBox7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel41)
                            .addComponent(jLabel44))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextField31)
                                            .addComponent(jTextField30)
                                            .addComponent(jTextField26, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jButton12)
                                .addGap(117, 117, 117)))))
                .addGap(220, 220, 220))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel36)
                .addGap(35, 35, 35)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jButton12)
                .addContainerGap(94, Short.MAX_VALUE))
        );

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

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "en proceso", "pago", "fiado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel26))
                                        .addGap(71, 71, 71)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                            .addComponent(jTextField9)
                                            .addComponent(jTextField23)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel28))
                                        .addGap(96, 96, 96)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField13, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                                            .addComponent(jTextField14)
                                            .addComponent(jTextField15)
                                            .addComponent(jTextField16)
                                            .addComponent(jTextField21)
                                            .addComponent(jTextField27)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel20))
                                        .addGap(58, 58, 58)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                                            .addComponent(jTextField1)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(155, 155, 155))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(viejo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel21))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                    .addComponent(jTextField8)
                                    .addComponent(jTextField6)
                                    .addComponent(jTextField7)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel22)
                                                .addComponent(jLabel24)
                                                .addComponent(jLabel29))
                                            .addGap(36, 36, 36))
                                        .addComponent(jButton4))
                                    .addComponent(jLabel19))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel27))
                                .addGap(90, 90, 90)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField24)
                                    .addComponent(jTextField10)
                                    .addComponent(jComboBox2, 0, 273, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(123, 123, 123)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(productoslayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(232, 232, 232))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
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
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel13)
                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel8)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viejo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productoslayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nuevo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel16))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel28)
                                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)
                                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))))))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField15ActionPerformed

    private void jTextField16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField16ActionPerformed

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

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
       // TODO add your handling code here:
       String c = (String)jComboBox2.getSelectedItem();
        System.out.println(c);
       String[] d = c.split("-");
        System.out.println(d);
       String cedulaEmpleado = d[1];
        db= new DataBase();
        db.insertar3("INSERT INTO pedido (fecha,numero,direccion, cedula, telefono, condonado, obsequio, nota, precioBruto, precioNeto, nick, estado ) VALUES ("+"'"+today+"',"+"'"+jTextField8.getText()+"',"+"'"+jTextField6.getText()+"',"+"'"+cedulaEmpleado+"',"+"'"+telefono+"',"+"'"+jTextField19.getText()+"',"+"'"+jTextField18.getText()+"',"+"'"+jTextArea2.getText()+"',"+"'"+jTextField20.getText()+"',"+"'"+jTextField22.getText()+"',"+"'"+jTextField24.getText()+"',"+"'"+jComboBox3.getSelectedItem()+"'"+")");
        
       int numeroInserciones = this.productosInsercion.size()-1;
       if(numeroInserciones >= 0)
        for (int i = 0; i <= numeroInserciones; i++) {
           ArrayList productoi = (ArrayList)productosInsercion.get(i);
            db= new DataBase();
            db.insertar3("INSERT INTO productoxpedido(precio, puntos, consecutivo, fecha, numero, codigo) VALUES('"+productoi.get(4)+"','"+productoi.get(5)+"','"+(i+1)+"','"+today+"','"+jTextField8.getText()+"','"+productoi.get(0)+"')");
        }
        
        db=new DataBase();
        db.actualizar("UPDATE producto SET precioBase = precioBase + 1 WHERE disponible= 1");
        new ImpresionTermica();
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
        String[] cabecera= new String[7];
                cabecera[0]="codigo";
                cabecera[1]="nombre";
                cabecera[2]="peso";
                cabecera[3]="tipo";
                cabecera[4]="descripcion";
                cabecera[5]="precio base";
                cabecera[6]="disponible";
        boolean b1 = IO.textfield_requerido(jTextField25);//codigo
        boolean b2 = IO.textfield_requerido(jTextField26);//nombre
        boolean b3 = IO.textfield_requerido(jTextField30);//peso
        boolean b4 = IO.textfield_requerido(jTextField31);//preciobase
        boolean b5 = !(((String)jComboBox5.getSelectedItem())).equals("Desconocido");//tipo
        boolean b6 = !(((String)jComboBox7.getSelectedItem())).equals("Desconocido");//disponible
        
        if(b1==true || b2==true || b3==true || b4==true || b5==true || b6==true){
            db=new DataBase();
            String disponibilidad = (String)jComboBox7.getSelectedItem();
            if(disponibilidad.equals("Disponible")){
                disponibilidad="1";
            }
            else if(disponibilidad.equals("No disponible")){
                disponibilidad="0";
            }
            ArrayList datos = db.excecuteQuery("SELECT * FROM producto WHERE codigo='"+jTextField25.getText()+"' OR nombre='"+jTextField26.getText()+"' OR peso='"+jTextField30.getText()+"' OR precioBase ='"+jTextField31.getText()+"' OR tipo='"+(String)jComboBox5.getSelectedItem()+"' OR disponible='"+disponibilidad+"'");
            if(db.isEmpty()){
                JOptionPane.showMessageDialog(null, "No existe un producto con esas caracteristicas");
            }
            else{
                SimpleTableDemo productoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(datos,7).result(),"productos","Carrito",this,"todaLaFila");
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
        }
        else{
            db=new DataBase();
            ArrayList datos =db.excecuteQuery("SELECT * FROM producto");
            if(db.isEmpty()){
                JOptionPane.showMessageDialog(null,"No existen productos registrados");
            }
            else{
                SimpleTableDemo productoSQL= new SimpleTableDemo(cabecera,new ConvertidorAMatriz(datos,7).result(),"productos","Carrito",this,"todaLaFila");
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

    private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox7ActionPerformed

    private void jTextField31KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField31KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31KeyPressed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
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
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    public javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
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
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
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
    
}


