/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System.Impresion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ImpresionTermica {
public ImpresionTermica() {
try {
FileWriter fw = new FileWriter("COM4:");

PrintWriter pw = new PrintWriter(fw);
String s = "PROBANDO ";

int i, len = s.length();

for (i = 0; len > 80; i += 80) {
pw.print(s.substring(i, i + 80));
pw.print("\r\n");
len -= 80;
}

if (len > 0) {
pw.print(s.substring(i));
pw.print("\r\n");
}

pw.close();
} catch (IOException e) {
System.out.println(e);
}
}
}