package Aplicacion.Vistas;
import Aplicacion.Controller.IndexController;
import System.DataBase.Core.DataBase;
import System.Helper.IO;
import System.MVC.Core.IView;
import System.MVC.Core.View;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


/**
 * Frame de la eliminacion del empleado
 * @author santiquiroz e isamelTheMemeMaster
 * @version 0.8.1(actualmente desechado)
 * @since JConexionDB 1.0
 */
public class EditarEmpleado extends View implements IView{
    DataBase db = new DataBase();
    String cedula;
    String nickUsuario;
    public EditarEmpleado(String puntero) {
        initComponents();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cedula=puntero;
        
        DataBase db = new DataBase();
        Map<String,String> map = new HashMap();
        map.put("cedula",cedula);
        db.getWhereEquals("empleado",map,null);
        jTextField1.setText(cedula);
        jTextField1.setEditable(false);
        
        nickUsuario= db.getDato(0,1);
        jTextField2.setText(db.getDato(0,1));
        jTextField3.setText(db.getDato(0,2));
        
        this.nickUsuario=nickUsuario;
        setVisible(true);
        setTitle("Editar empleado");
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
        jLabel41 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton6.setText("Salir");
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("EDITAR EMPLEADO");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel32.setText("Cedula");

        jLabel39.setText("Nombre");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel41.setText("Telefono");

        jButton14.setText("Editar");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Activo", "Inactivo", "Despedido"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Tipo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14)
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 64, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(144, 144, 144)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(25, 25, 25)
                .addComponent(jButton14)
                .addGap(28, 28, 28)
                .addComponent(jToggleButton6)
                .addGap(28, 28, 28))
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
        
        //boolean b1 = IO.textfield_requerido(jTextField2, jTextField3);
        
        
            
            String nombre=jTextField2.getText();
            String telefono=jTextField3.getText();
            
            
            Map<String,String> map = new HashMap();

            //map.put("nick", nick);
            map.put("nombre", nombre);
            map.put("telefono", telefono);
            
            if(jComboBox1.getSelectedItem().equals("Activo")){
                map.put("activo", "1");
                map.put("inactivo", "0");
                map.put("despedido", "0");
                
            }
            else if(jComboBox1.getSelectedItem().equals("Inactivo")){
                map.put("activo", "0");
                map.put("inactivo", "1");
                map.put("despedido", "0");
                
            }
            else{
                map.put("activo", "0");
                map.put("inactivo", "0");
                map.put("despedido", "1");
            }
            
            
            String[] arrayNick = new String[2];
            arrayNick[0]="cedula";
            arrayNick[1]=cedula;
            db.update("empleado", map, arrayNick);
            //db.actualizar("UPDATE usuario SET nombre=`"+nombreN+"`, password=`"+passwordN+"`WHERE (nick=`"+nickN+"`)");
            //db.excecuteQuery("UPDATE usuario SET nombre=`"+nombreN+"`, password=`"+passwordN+"`WHERE (nick=`"+nickN+"`)");
                       
        
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton14;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
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


