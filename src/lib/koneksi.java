/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author F
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import lib.SaveConfig;
import lib.keyKonfigurasi;

public class koneksi {
    private SaveConfig config = new SaveConfig();
    private keyKonfigurasi key = new keyKonfigurasi();
    private static Connection mysqlkonek;
    public Connection Connect() throws ClassNotFoundException, IOException{
        try {
            String url="jdbc:mysql://"+config.getConfig().get(key.host)+":"+config.getConfig().get(key.port_database)+"/pejar"; //url database
            String user="root"; //user database
            String pass=""; //password database
            Class.forName("com.mysql.jdbc.Driver");
            mysqlkonek = DriverManager.getConnection(url,user,pass);        
            return mysqlkonek;
        } catch (SQLException e) {
            System.err.println("koneksi gagal "+e.getMessage()); //perintah menampilkan error pada koneksi
        }
        return mysqlkonek;
    }        
}
