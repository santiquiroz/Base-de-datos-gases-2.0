/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System.DataBase.Core;

import java.util.ArrayList;

/**
 *
 * @author santiago
 */
public class ConvertidorAMatriz{
    ArrayList l;
    int filas,columnas;
    public ConvertidorAMatriz(ArrayList lista,int c) {
        this.l=lista;
        this.filas=lista.size();
        this.columnas=c;
    }
    public Object [][]result(){
        Object[][] matriz = new Object[this.filas][this.columnas];
        ArrayList <Object> aux=new ArrayList();
        for(int i=0;i<this.filas;i++){
            aux=(ArrayList<Object>) this.l.get(i);
            
            for(int j=0;j<this.columnas;j++){
                matriz[i][j]=aux.get(j);
            }
        }
        return matriz;
    }
}
