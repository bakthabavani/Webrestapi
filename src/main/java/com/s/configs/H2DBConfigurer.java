package com.s.configs;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.h2.tools.RunScript;
import org.h2.tools.Server;

public class H2DBConfigurer {
	private Properties settings=null;
	private Server server=null;
	private static H2DBConfigurer configurer=null;
	private H2DBConfigurer() throws IOException, SQLException, ClassNotFoundException{
    	this.settings=new Properties();
    	this.settings.load(new FileInputStream("config.PROPERTIES"));
    	server=Server.createTcpServer().start(); 
    	RunScript.execute(getConnection(), new FileReader("ddl.sql"));
    	RunScript.execute(getConnection(), new FileReader("sample_data.sql"));    	
	}	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		if(settings!=null){
			Class.forName(settings.getProperty("db_driver"));
	        return DriverManager.getConnection(settings.getProperty("db_connection"),settings.getProperty("db_user"),settings.getProperty("db_password"));
		}else{
			return null;
		}
	}
	public void stopServer(){
		if(server!=null)server.stop();
	}
	public static H2DBConfigurer getConfigurer() throws ClassNotFoundException, IOException, SQLException{
		if(H2DBConfigurer.configurer==null){
			H2DBConfigurer.configurer=new H2DBConfigurer();		
		}
		return H2DBConfigurer.configurer;
	}
}
