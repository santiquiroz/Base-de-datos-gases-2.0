/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RespaldarBD.java
 *
 * Created on 01-ene-2013, 22:16:23
 */
package backup.Vista;

import backup.Controlador.Seleccion;
import backup.Vista.Principal;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Tottus
 */
public class RespaldarBD extends javax.swing.JDialog {

    /** Creates new form RespaldarBD */
    String user="root";
    String password="";
    String bd="gases2";
    String path="";
    String backup="mysqldump --user="+user+" --password="+password+" -v "+bd+" > "+path;
    private String extension=".sql";
    Runtime rt = Runtime.getRuntime();
    Principal p;
    public RespaldarBD(Principal parent, boolean modal) {
        super(parent, modal);
        p=parent;
        initComponents();
        this.setLocationRelativeTo(p);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txtruta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnexaminar = new javax.swing.JButton();
        btnrespaldar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        radbtntxt = new javax.swing.JRadioButton();
        radbtnsql = new javax.swing.JRadioButton();

        buttonGroup1.add(radbtnsql);
        buttonGroup1.add(radbtntxt);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Respaldar Base de Datos");

        txtruta.setEditable(false);

        jLabel1.setText("Ruta :");

        btnexaminar.setText("Examinar");
        btnexaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexaminarActionPerformed(evt);
            }
        });

        btnrespaldar.setText("Respaldar");
        btnrespaldar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrespaldarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel1.setEnabled(false);

        radbtntxt.setSelected(true);
        radbtntxt.setText(".txt");
        radbtntxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radbtntxtActionPerformed(evt);
            }
        });

        radbtnsql.setText(".sql");
        radbtnsql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radbtnsqlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radbtntxt, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(radbtnsql)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radbtntxt)
                    .addComponent(radbtnsql))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtruta, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnrespaldar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnexaminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexaminar))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnrespaldar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnexaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexaminarActionPerformed
        Seleccion o=new Seleccion();
        o.ResetFiltros();
        File file= o.guardar(this);
        if(file!=null){
           String cad= file.getAbsolutePath();
           txtruta.setText(cad);
        }
    }//GEN-LAST:event_btnexaminarActionPerformed

    private void btnrespaldarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrespaldarActionPerformed
        if(txtruta.getText().length()>0){
            try {
                File file;
                file=new File(txtruta.getText());
                path=file.getAbsolutePath()+extension;
                System.out.println(""+path);
                int c=JOptionPane.showConfirmDialog(p, "Desea Crear una Copia de Seguridad en esta ruta \n"+path, "Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(c==JOptionPane.YES_OPTION){
                    System.out.println(path);
                    backup="c:/xampp/mysql/bin/mysqldump -uroot --all-databases -r "+path.replace("\\", "/");
                    System.out.println(backup);
                    rt.exec(backup);
                    //System.out.println(backup.replace("/", "\\"));
                    //rt.exec(backup.replace("/", "\\"));
                    JOptionPane.showMessageDialog(p, "Respaldo Creado Correctamente en "+file.getPath());
                    this.dispose();
                }
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }else
            JOptionPane.showMessageDialog(this, "Complete Todos Los Campos");
    }//GEN-LAST:event_btnrespaldarActionPerformed

    private void radbtnsqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radbtnsqlActionPerformed
        extension=".sql";
    }//GEN-LAST:event_radbtnsqlActionPerformed

    private void radbtntxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radbtntxtActionPerformed
        extension=".txt";
    }//GEN-LAST:event_radbtntxtActionPerformed

   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexaminar;
    private javax.swing.JButton btnrespaldar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton radbtnsql;
    private javax.swing.JRadioButton radbtntxt;
    private javax.swing.JTextField txtruta;
    // End of variables declaration//GEN-END:variables
}
