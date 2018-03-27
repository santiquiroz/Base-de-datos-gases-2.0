/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System.DataBase.Core;

/**
 * Esta interfaz es creada para arreglar un bug en ORM y en ActiveRecord
 * @author nekszer
 * @version 1.5.1
 * @since 1.5.1
 */
public @interface SQLNotation {
    
    /**
     * Permite asignar el atributo como llave primaria en el modelo ORM y ActiveRecord
     * @return boolean
     */
    public boolean PrimaryKey() default false;

    /**
     * Permite asignar el atributo como llave foranea en el modelo ORM y ActiveRecord
     * @return boolean
     */
    public boolean ForeignKey() default false;
    
}
