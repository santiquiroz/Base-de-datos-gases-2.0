Como configurar el servidor:
-Configurando la instancia de ubuntu:
	sudo apt-get update
	sudo apt-get install apache2
	sudo /etc/init.d/apache2 restart
	
	sudo apt-get update -y && sudo apt-get install mariadb-server
	
	-asegurando el mysql

		sudo mysql -u root -p

		use mysql;
		update user set plugin='' where User='root';
		exit

		sudo /etc/init.d/mysql restart

		sudo /usr/bin/mysql_secure_installation

		sudo mysql -u root -p
	sudo apt-get install php libapache2-mod-php
	sudo systemctl restart apache2
	
	sudo add-apt-repository universe
	sudo apt install phpmyadmin php-mbstring php-gettext
	sudo phpenmod mbstring
	sudo systemctl restart apache2
-configurando phpmyadmin
	https://www.digitalocean.com/community/tutorials/como-instalar-en-ubuntu-18-04-la-pila-lamp-linux-apache-mysql-y-php-es
	sudo ufw app list
	sudo ufw app info "Apache Full"
	sudo ufw allow in "Apache Full"
	https://askubuntu.com/questions/387062/how-to-solve-the-phpmyadmin-not-found-issue-after-upgrading-php-and-apache
	sudo ln -s /usr/share/phpmyadmin /var/www/
	sudo nano /etc/apache2/apache2.conf
	
	añadir:Include /etc/phpmyadmin/apache.conf
	
-configurando usuarios
	CREATE USER 'phpmyadminuser' IDENTIFIED BY 'password';
	GRANT ALL PRIVILEGES ON *.* TO 'phpmyadminuser' WITH GRANT OPTION;


-acceso remoto
	https://www.ochobitshacenunbyte.com/2018/03/14/configurar-mariadb-y-mysql-para-acceso-remoto/
	
	sudo nano /etc/mysql/mariadb.conf.d/50-server.cnf
	
	comentar linea del 127.0.1.1
	
	sudo service mysql restart
	sudo service mariadb restart
	

-subiendo respaldo 
	mysql -u root -pPassword123 wp_users < wp_users.sql	
