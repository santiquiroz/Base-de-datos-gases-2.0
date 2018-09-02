/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System.DataBase.Core;

import java.io.FileInputStream;
import java.io.OutputStream;

/**
 *
 * @author santiquiroz
 */
public class Restore {
   public static void restore() {
   try {
      Process p = Runtime
            .getRuntime()
            .exec("C:/xampp/mysql/bin/mysql -u root -p'' gases2");

      OutputStream os = p.getOutputStream();
      FileInputStream fis = new FileInputStream("backup_pruebas.sql");
      byte[] buffer = new byte[1000];

      int leido = fis.read(buffer);
      while (leido > 0) {
         os.write(buffer, 0, leido);
         leido = fis.read(buffer);
      }

      os.flush();
      os.close();
      fis.close();

   } catch (Exception e) {
      e.printStackTrace();
   }
}
}
