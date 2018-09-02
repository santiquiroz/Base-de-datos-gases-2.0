/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System.Helper;

/**
 *
 * @author santiquiroz
 */

public class ManejoDeStrings {
    public ManejoDeStrings(){
    }
    //este metodo recorta el string desde un indice de inicio dado hasta un indice de final dado, si el fin sesale del tamaño del string original recorta hasta su size
    public String recortar(String texto,int inicio,int fin){
        if(inicio<=fin){
            if(texto.length()>=fin){
                texto=texto.substring(inicio,fin);
            }
            else{
                //texto=texto.substring(inicio,(texto.length()-1));
            }
        }
        
        return texto;
    }
    //este metodo añade el relleno dado en el texto original dado para asi lograr un tamaño largo deseado, si lado es false añade el relleno por la izquierda del texto original de lo contrario se añade por el lado derecho
    public String rellenar(String texto,String relleno,int largo,boolean lado){
        if(texto.length()<largo){
            int numeroderelleno=largo-texto.length()-1;
            String r = relleno;
            for (int i = 0; i < numeroderelleno; i++) {
                relleno=relleno+r;
            }
            if(lado){
                texto=texto+relleno;
            }
            else{
                texto=relleno+texto;
            }
        }
        return texto;
        
    }
            
}
