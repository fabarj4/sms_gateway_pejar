/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author F
 */
import gnu.io.CommPortIdentifier;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;
import logic.Ex_sendmessage;
import lib.SaveConfig;
import lib.Pesan;

public class NewClass {
    public static void main(String []args) throws Exception{
        
        SaveConfig config = new SaveConfig();
        config.getConfig();
        //Pesan p = new Pesan();
        //p.readMessage();
        //new NewClass().list();
        //Ex_sendmessage app = new Ex_sendmessage();
        //System.out.println(app.checkport("COM4"));
        //System.out.println(app.checkport("COM5"));
        //System.out.println(app.checkport("COM8"));
        /*for(int i=1;i<=10;i++){
            System.out.println(app.checkport("COM5"));
        }*/
        //Properties defaultProps = new Properties();
        //Properties props = new Properties();
        
        /*Ex_sendmessage app = new Ex_sendmessage();
        String[] nohp = new String[]{"081546055645","081546055645"};
        ArrayList nohp1 = new ArrayList(Arrays.asList(nohp));
        try
        {
            //app.doIt("+6281546055645", "SMAN 7 BOGOR \n\n#Hello from SMSLib! 5");
            //app.sendMessage(nohp, "SMAN 7 BOGOR \n\n#Hello from SMSLib! faisal -1");
            File configFile = new File("config.properties");
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            String host = props.getProperty("host");

            System.out.print("Host name is: " + host);
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
    }
    
     protected void list() throws Exception {
    // get list of ports available on this particular computer,
    // by calling static method in CommPortIdentifier.
    
    Ex_sendmessage app = new Ex_sendmessage();
    Enumeration pList = CommPortIdentifier.getPortIdentifiers();
    
    // Process the list.
    /*while (pList.hasMoreElements()) {
      CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
      System.out.print("Port " + cpi.getName() + " ");
      if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
        System.out.println("is a Serial Port: " + cpi);
      } else if (cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
        System.out.println("is a Parallel Port: " + cpi);
      } else if (cpi.getPortType() == CommPortIdentifier.PORT_I2C) {
        System.out.println("is a I2C Port: " + cpi);
      } else if (cpi.getPortType() == CommPortIdentifier.PORT_RAW) {
        System.out.println("is a RAW Port: " + cpi);
      } else {
        System.out.println("is an Unknown Port: " + cpi);
      }
      
      //if(cpi.getName() boolean comPortSuccess = );
    }*/
    System.out.println();
    //Ex_sendmessage app = new Ex_sendmessage();
    Enumeration ports = CommPortIdentifier.getPortIdentifiers();
        
        while(ports.hasMoreElements())
        {
            CommPortIdentifier cpi = (CommPortIdentifier)ports.nextElement();
            if(cpi.getPortType() == CommPortIdentifier.PORT_SERIAL){
                System.out.println(cpi.getName());   
                /*if(app.checkport(cpi.getName())){
                    System.out.println(" Port yang di pake");
                }else{
                    System.out.println();
                }*/
            }
        }
  }
}
