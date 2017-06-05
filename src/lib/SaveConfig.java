/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Properties;

/**
 *
 * @author F
 */
public class SaveConfig {
    public Hashtable<String,String> getConfig() throws FileNotFoundException, IOException{
        Hashtable<String, String> dataKonfigurasi = new Hashtable<String, String>();
        File configFile = new File("config.properties");
        FileReader reader = new FileReader(configFile);
        Properties props = new Properties();
        props.load(reader);

        String host = props.getProperty("host");
        String port_database = props.getProperty("port_database");
        String port_modem = props.getProperty("port_modem");
        String no_hp = props.getProperty("no_hp");
        String header = props.getProperty("header");
        dataKonfigurasi.put("host", host);
        dataKonfigurasi.put("port_database", port_database);
        dataKonfigurasi.put("port_modem", port_modem);
        dataKonfigurasi.put("no_hp", no_hp);
        dataKonfigurasi.put("header", header);
        System.out.println("Host name is: " + host);
        System.out.println("Port database is: " + port_database);
        System.out.println("Port modem is: " + port_modem);
        System.out.println("NO HP modem is: " + no_hp);
        System.out.println(header);
        reader.close();
        return dataKonfigurasi;
    } 
    
    public void setConfig(String host,String port_database, String port_modem, String no_hp, String header) throws FileNotFoundException, IOException{
        File configFile = new File("config.properties");
        try {
            Properties props = new Properties();
            props.setProperty("host", host);
            props.setProperty("port_database", port_database);
            props.setProperty("port_modem", port_modem);
            props.setProperty("no_hp", no_hp);
            props.setProperty("header", header);
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "host settings");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
}
