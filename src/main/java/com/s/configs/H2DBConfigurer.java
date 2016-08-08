package com.s.configs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	public H2DBConfigurer() throws IOException, SQLException, ClassNotFoundException{
    	this.settings=new Properties();
    	this.settings.load(new FileInputStream("config.PROPERTIES"));
    	server=Server.createTcpServer().start(); 
    	RunScript.execute(getConnection(), new FileReader("ddl.sql"));
    	RunScript.execute(getConnection(), new FileReader("sample_data.sql"));
    	System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");
	}	
	public Connection getConnection() throws SQLException, ClassNotFoundException{
		if(settings!=null){
			Class.forName(settings.getProperty("db_driver"));
	        return DriverManager.getConnection("jdbc:h2:" + server.getURL() + "/mem:test",settings.getProperty("db_user"),settings.getProperty("db_password"));
		}else{
			return null;
		}
	}
	public void stopServer(){
		if(server!=null)server.stop();
	}
}
