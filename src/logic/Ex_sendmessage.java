/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author F
 */
public class Ex_sendmessage {
    public void sendMessage(String[] no_hp, String pesan) throws Exception {
        OutboundNotification outboundNotification = new OutboundNotification();
        System.out.println("Example: Send message from a serial gsm modem.");
        System.out.println(Library.getLibraryDescription());
        System.out.println("Version: " + Library.getLibraryVersion());
        SerialModemGateway gateway = new SerialModemGateway("modem.com4", "COM5", 9600, "", "");
        gateway.setInbound(true);
        gateway.setOutbound(true);
        Service.getInstance().setOutboundMessageNotification(outboundNotification);
        Service.getInstance().addGateway(gateway);
        Service.getInstance().startService();
        System.out.println();
        System.out.println("Modem Information:");
        System.out.println("  Manufacturer: " + gateway.getManufacturer());
        System.out.println("  Model: " + gateway.getModel());
        System.out.println("  Serial No: " + gateway.getSerialNo());
        System.out.println("  SIM IMSI: " + gateway.getImsi());
        System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
        System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
        System.out.println();
        for(int i=0;i<no_hp.length;i++){
            OutboundMessage msg = new OutboundMessage(no_hp[i], pesan);
            Service.getInstance().sendMessage(msg);
        }
        //OutboundMessage msg = new OutboundMessage("+6281546055645", "SMAN 7 BOGOR \n\n#Hello from SMSLib! 5");
        //Service.getInstance().sendMessage(msg);
        /*
        int i=0;
        while(i<5){
        msg = new OutboundMessage("+6285710481030", "Hello from SMSLib! 4 Linda ini pake sms gateway XD. udah bisa dong :p");
        Service.getInstance().sendMessage(msg);
        i++;
        }*/
        System.out.println("Now Sleeping - Hit <enter> to terminate.");
        Service.getInstance().stopService();
    }
    
    public class OutboundNotification implements IOutboundMessageNotification{
        public void process(AGateway gateway, OutboundMessage msg){
            System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
            System.out.println(msg);
        }
    }
    
    public static void main(String args[])
    {
                Ex_sendmessage app = new Ex_sendmessage();
                try
                {
                        //app.doIt();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}
