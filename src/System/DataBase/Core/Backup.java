/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System.DataBase.Core;

import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author santiquiroz
 */
public class Backup {
   public static void backup() {
   try {
      Process p = Runtime
            .getRuntime()
            .exec("C:/xampp/mysql/bin/mysqldump -uroot -p gases2 > C:/Users/santi/Desktop/SIABD/putoelquelolea.sql");

      InputStream is = p.getInputStream();
      FileOutputStream fos = new FileOutputStream("backup_pruebas.sql");
      byte[] buffer = new byte[1000];

      int leido = is.read(buffer);
      while (leido > 0) {
         fos.write(buffer, 0, leido);
         leido = is.read(buffer);
      }

      fos.close();

   } catch (Exception e) {
      e.printStackTrace();
   }
}

}
