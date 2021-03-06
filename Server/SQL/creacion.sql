#-----------------------------------------------------estructura-----------------------------------------------------------------
CREATE TABLE static (
codigo VARCHAR(20) PRIMARY KEY,
limPuntos VARCHAR(20),
limPuntosAcomulables VARCHAR(20),
puntosxkilo VARCHAR(20),
obsequio VARCHAR (80),
puntos_descontados_por_obsequio VARCHAR(80),
alerta_retencion VARCHAR(1),
tiempo_de_gracia VARCHAR(20),
tiempo_de_redencion VARCHAR(20)
);

CREATE TABLE usuario(
nick VARCHAR(80) PRIMARY KEY,
pasword VARCHAR(80) NOT NULL,
nombre VARCHAR (80),
administrador VARCHAR(1) NOT NULL,
comun VARCHAR(1) NOT NULL
);

CREATE   TABLE empleado (
cedula VARCHAR(80) PRIMARY KEY,
nombre VARCHAR(80) NOT NULL,
direccion VARCHAR(80) NOT NULL,
telefono VARCHAR(80) NOT NULL,
activo VARCHAR (1) NOT NULL,
inactivo VARCHAR (1) NOT NULL,
despedido VARCHAR(1) NOT NULL
);

CREATE   TABLE pago (
fechahora VARCHAR(80) PRIMARY KEY,
id_usuario VARCHAR(80) REFERENCES USUARIO(nick) ON DELETE SET NULL,
id_empleado VARCHAR(80) REFERENCES EMPLEADO(cedula) ON DELETE SET NULL,
monto VARCHAR (80) NOT NULL
);

CREATE   TABLE municipio(
nombre VARCHAR(80) PRIMARY KEY
);

CREATE TABLE cliente (
telefono VARCHAR(80) PRIMARY KEY,
nombre VARCHAR(80),
fecha_registro VARCHAR(80) NOT NULL,
puntos VARCHAR(80) NOT NULL,
empresarial VARCHAR(1) NOT NULL,
comun Varchar(1) NOT NULL,
id_municipio VARCHAR(80) REFERENCES municipio(nombre) ON DELETE SET NULL, 
promedio_dias_pedido VARCHAR(20),
fecha_ultimo_pedido VARCHAR(20),
no_molestar VARCHAR(1),
numero_dias_pedido VARCHAR(80),
numero_pedidos VARCHAR(80)
);

CREATE   TABLE productoe (
codigo VARCHAR(80),
nombre VARCHAR (80),
peso VARCHAR(80) NOT NULL,
tipo VARCHAR(80) NOT NULL,
id_cliente VARCHAR(80)REFERENCES CLIENTE(telefono),
PRIMARY KEY (codigo,id_cliente)
);

CREATE   TABLE colore(
color VARCHAR(80),
precio VARCHAR(80) NOT NULL,
disponible VARCHAR (1),
id_productoe_codigo VARCHAR(80) REFERENCES productoe(codigo),
id_productoe_id_cliente VARCHAR(80) REFERENCES productoe(id_cliente),
PRIMARY KEY(color,id_productoe_codigo,id_productoe_id_cliente) 
);

CREATE TABLE productoepredeterminado(
codigo VARCHAR(80),
nombre VARCHAR (80),
peso VARCHAR(80) NOT NULL,
tipo VARCHAR(80) NOT NULL,
PRIMARY KEY (codigo)
);

CREATE TABLE colorepredeterminado(
color VARCHAR(80),
precio VARCHAR(80) NOT NULL,
disponible VARCHAR (1),
id_productoe_codigo VARCHAR(80) REFERENCES productoe(codigo),
PRIMARY KEY(color,id_productoe_codigo) 
);

CREATE TABLE pedido (
fecha VARCHAR(80) NOT NULL,
numero VARCHAR(80) NOT NULL,
PRIMARY KEY(fecha,numero),
direccion VARCHAR(20) NOT NULL,
nota VARCHAR(130),
precio_total VARCHAR(80) NOT NULL,
estado VARCHAR(80) NOT NULL,
id_usuario VARCHAR(80) REFERENCES USUARIO(nick) ON DELETE SET NULL,
id_empleado VARCHAR(80) REFERENCES EMPLEADO(cedula) ON DELETE SET NULL,
id_cliente VARCHAR(80) REFERENCES CLIENTE(telefono) ON DELETE SET NULL,
tipo VARCHAR(80),
id_municipio VARCHAR(80),
condonado VARCHAR(80),
puntos VARCHAR (80),
obsequio VARCHAR (80),
puntos_descontados_por_obsequio	VARCHAR(80),
bodega VARCHAR(80),
horaRegistro VARCHAR(80)
);

CREATE TABLE productoexpedido(
consecutivo VARCHAR(80),
nombre VARCHAR (80),
color VARCHAR(80) NOT NULL,
peso VARCHAR (80) NOT NULL,
precio VARCHAR(80) NOT NULL,
puntos VARCHAR (80) NOT NULL,
id_productoe_codigo VARCHAR(80) REFERENCES productoe(codigo),
id_productoe_id_cliente VARCHAR(80) REFERENCES productoe(id_cliente),
id_pedido_fecha VARCHAR(80)REFERENCES pedido(fecha) ON DELETE CASCADE,
id_pedido_numero VARCHAR(80)REFERENCES pedido(numero) ON DELETE CASCADE,
PRIMARY KEY (consecutivo,id_pedido_fecha,id_pedido_numero)
);

CREATE TABLE productom(
codigo VARCHAR(80),
nombre varchar(80),
peso VARCHAR(80) NOT NULL,
tipo VARCHAR(80) NOT NULL,
id_municipio VARCHAR(80) REFERENCES municipio(nombre),
PRIMARY KEY (id_municipio,codigo)
);

CREATE TABLE colorm(
color VARCHAR(80),
precio VARCHAR(80) NOT NULL,
disponible VARCHAR (1),
id_productom_codigo VARCHAR(80) REFERENCES productom(codigo),
id_productom_id_municipio VARCHAR(80) REFERENCES productom(id_municipio),
PRIMARY KEY (id_productom_id_municipio,id_productom_codigo,color)
);

CREATE TABLE productomxpedido (
consecutivo VARCHAR(80),
nombre VARCHAR (80),
color VARCHAR(80) NOT NULL,
peso VARCHAR (80) NOT NULL,
precio VARCHAR(80) NOT NULL,
puntos VARCHAR (80) NOT NULL,
id_productom_codigo VARCHAR(80) REFERENCES productom(codigo),
id_productom_id_municipio VARCHAR(80) REFERENCES productom(id_municipio),
id_pedido_fecha VARCHAR(80) REFERENCES pedido(fecha) ON DELETE CASCADE,
id_pedido_numero VARCHAR(80) REFERENCES pedido(numero) ON DELETE CASCADE,
PRIMARY KEY ( consecutivo,id_pedido_fecha,id_pedido_numero)
);

CREATE TABLE notificacion (
consecutivo VARCHAR(80) NOT NULL,
fechahora VARCHAR(80) NOT NULL,
tipo VARCHAR(80) NOT NULL,
visto VARCHAR(1) NOT NULL,
mensaje VARCHAR(80) NOT NULL,
nick VARCHAR(80) REFERENCES usuario(nick),
PRIMARY KEY (consecutivo , nick)
);

#------------------------------------------------------triggers--------------------------------------------------------------
DELIMITER $$
CREATE OR REPLACE TRIGGER  eliminar_clientes AFTER DELETE ON
    cliente FOR EACH ROW
BEGIN
DELETE FROM clienteNuevo WHERE telefono = OLD.telefono;
END $$
DELIMITER ;;

DELIMITER $$
CREATE OR REPLACE TRIGGER  actualizar_fechas_cliente AFTER INSERT ON
    pedido FOR EACH ROW
BEGIN
    DECLARE
        DIAS_QUE_SE_DEMORO INT ; DECLARE PROMEDIO_VIEJO INT ; DECLARE NUMERO_DIAS_PEDIDO_VARIABLE INT ; DECLARE NUMERO_PEDIDOS_VARIABLE INT ; DECLARE TELEFONO_CLIENTE VARCHAR (20) ;             DECLARE PROMEDIO_NUEVO INT ; DECLARE HOY DATE ; DECLARE FECHA_ULTIMO_PEDIDO_VARIABLE  DATE;
    
    SET HOY := NEW.fecha;
    SET TELEFONO_CLIENTE := NEW.id_cliente;
SET PROMEDIO_VIEJO := (SELECT promedio_dias_pedido FROM cliente WHERE telefono = TELEFONO_CLIENTE);
SET FECHA_ULTIMO_PEDIDO_VARIABLE := (SELECT fecha_ultimo_pedido FROM cliente WHERE telefono = TELEFONO_CLIENTE);
    SET NUMERO_DIAS_PEDIDO_VARIABLE :=(SELECT numero_dias_pedido FROM cliente WHERE telefono = TELEFONO_CLIENTE);
    SET NUMERO_PEDIDOS_VARIABLE := (SELECT numero_pedidos FROM cliente WHERE telefono = TELEFONO_CLIENTE);
    SET DIAS_QUE_SE_DEMORO := (SELECT DATEDIFF(HOY, FECHA_ULTIMO_PEDIDO_VARIABLE ));
    
    UPDATE cliente SET promedio_dias_pedido =(NUMERO_DIAS_PEDIDO_VARIABLE / NUMERO_PEDIDOS_VARIABLE),fecha_ultimo_pedido = HOY,numero_dias_pedido =(NUMERO_DIAS_PEDIDO_VARIABLE + DIAS_QUE_SE_DEMORO),numero_pedidos =(NUMERO_PEDIDOS_VARIABLE + 1)   WHERE telefono = TELEFONO_CLIENTE ;
END $$
DELIMITER ;;

DELIMITER $$
CREATE OR REPLACE TRIGGER crear_p_de_cliente_empresa_por_defec_agregar_cliente_n AFTER INSERT ON
    cliente FOR EACH ROW
BEGIN
	DECLARE TELEFONO_CLIENTE VARCHAR(80);
    DECLARE codigop VARCHAR(80);
    DECLARE nombrep VARCHAR(80);
    DECLARE pesop VARCHAR(80);
    DECLARE liquidop VARCHAR(80);
    
    DECLARE colorp VARCHAR(80);
    DECLARE preciop VARCHAR(80);
    DECLARE disponiblep VARCHAR(80);
    DECLARE id_productoe_codigop VARCHAR(80);
    
    
    DECLARE not_found INT DEFAULT 0;
    
    DECLARE cursor_productose_defecto CURSOR FOR
    SELECT * FROM productoepredeterminado;
    
    DECLARE cursor_colore_defecto CURSOR FOR
    SELECT * FROM colorepredeterminado;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET not_found = 1;
    
    

    SET TELEFONO_CLIENTE := NEW.telefono;
    

    INSERT INTO clienteNuevo(telefono,nombre,fecha_registro,puntos,empresarial,comun,id_municipio,promedio_dias_pedido,fecha_ultimo_pedido,no_molestar,numero_dias_pedido,numero_pedidos) values(NEW.telefono,NEW.nombre,NEW.fecha_registro,NEW.puntos,NEW.empresarial,NEW.comun,NEW.id_municipio,NEW.promedio_dias_pedido,NEW.fecha_ultimo_pedido,NEW.no_molestar,NEW.numero_dias_pedido,NEW.numero_pedidos);
    
    IF (NEW.empresarial = 1) THEN
        OPEN cursor_productose_defecto;
        FETCH cursor_productose_defecto INTO codigop, nombrep, pesop, liquidop;
            WHILE (not_found = 0) DO
                INSERT INTO productoe (codigo, nombre, peso, tipo, id_cliente)VALUES (codigop, nombrep, pesop, liquidop, TELEFONO_CLIENTE); 
                FETCH cursor_productose_defecto INTO codigop, nombrep, pesop, liquidop;
            END WHILE ;
        CLOSE cursor_productose_defecto;
		
        SET not_found := 0;
        
        OPEN cursor_colore_defecto;
            FETCH cursor_colore_defecto INTO colorp, preciop, disponiblep, id_productoe_codigop;
            WHILE (not_found = 0) DO
                
                INSERT INTO colore (color, precio, disponible, id_productoe_codigo, id_productoe_id_cliente)
                VALUES (colorp, preciop, disponiblep, id_productoe_codigop, TELEFONO_CLIENTE); 
                
                FETCH cursor_colore_defecto INTO colorp, preciop, disponiblep, id_productoe_codigop;
            END WHILE;
        CLOSE cursor_colore_defecto;
    END IF;
END $$
DELIMITER ;;

#------------------------------------------------------procedimiento----------------------------------------------------------

DELIMITER $$

CREATE OR REPLACE PROCEDURE procedimiento_actualizar_te_pasaste() 
    BEGIN
    DECLARE aux INT;
    CREATE OR REPLACE TABLE te_pasaste AS SELECT * FROM cliente WHERE ((fecha_ultimo_pedido IS NOT NULL)AND(SELECT DATEDIFF((SELECT CURDATE()),cliente.fecha_ultimo_pedido ))>(promedio_dias_pedido+(SELECT tiempo_de_gracia FROM static))AND(no_molestar = 0));

    SELECT COUNT(*) INTO aux FROM te_pasaste;

    IF aux = 0 THEN
        UPDATE STATIC SET alerta_retencion = 0 WHERE 1;
    ELSE
        UPDATE STATIC SET alerta_retencion = 1 WHERE 1;
    END IF;
    END

$$
DELIMITER ;;
#------------------------------------------------------nota--------------------------------------------
#al importar desde un archivo .sql puede corromper la tabla count, eso se corrige asi:

#mysql_upgrade --force -uroot -p

#y reiniciando el servicio de mysql

#--------------------------------------------------------evento-----------------------------------------------------------------


SET GLOBAL event_scheduler := ON;




CREATE OR REPLACE EVENT actualizar_te_pasast
ON SCHEDULE EVERY 60 MINUTE STARTS NOW()ON COMPLETION PRESERVE
DO
CALL procedimiento_actualizar_te_pasaste();


#------------------------------------------------------insercion-------------------------------------------------------------
INSERT INTO static(codigo, limPuntos,limPuntosAcomulables,puntosxkilo,obsequio,puntos_descontados_por_obsequio,tiempo_de_gracia,tiempo_de_redencion ) VALUES( '1','4320','4320','80','','4320','5','100');