/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.util.ArrayList;
import java.util.List;
import javax.crypto.spec.SecretKeySpec;
import org.smslib.AGateway;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Library;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.crypto.AESKey;
import org.smslib.modem.SerialModemGateway;

import lib.SaveConfig;
import lib.keyKonfigurasi;

/**
 *
 * @author F
 */
public class Pesan {
    private SaveConfig config = new SaveConfig();
    private keyKonfigurasi key = new keyKonfigurasi();
    
    public void sendMessage(ArrayList no_hp, String pesan) throws Exception {
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
        for(int i=0;i<no_hp.size();i++){
            OutboundMessage msg = new OutboundMessage(no_hp.get(i).toString(),config.getConfig().get(key.header)+pesan);
            Service.getInstance().sendMessage(msg);
        }
        /*
        for(int i=0;i<no_hp.length;i++){
            OutboundMessage msg = new OutboundMessage(no_hp[i], pesan);
            Service.getInstance().sendMessage(msg);
        }*/
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
        Service.getInstance().removeGateway(gateway);
    }
    
    public class OutboundNotification implements IOutboundMessageNotification{
        public void process(AGateway gateway, OutboundMessage msg){
            System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
            System.out.println(msg);
        }
    }
    
    public void readMessage()throws Exception{
        // Define a list which will hold the read messages.
        List<InboundMessage> msgList;
        // Create the notification callback method for inbound & status report
        // messages.
        InboundNotification inboundNotification = new InboundNotification();
        // Create the notification callback method for inbound voice calls.
        CallNotification callNotification = new CallNotification();
        //Create the notification callback method for gateway statuses.
        GatewayStatusNotification statusNotification = new GatewayStatusNotification();
        OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
        try
        {
            System.out.println("Example: Read messages from a serial gsm modem.");
            System.out.println(Library.getLibraryDescription());
            System.out.println("Version: " + Library.getLibraryVersion());
            // Create the Gateway representing the serial GSM modem.
            SerialModemGateway gateway = new SerialModemGateway("modem.com4", "COM4", 9600, "", "");
            // Set the modem protocol to PDU (alternative is TEXT). PDU is the default, anyway...
            gateway.setProtocol(AGateway.Protocols.PDU);
            
            // Do we want the Gateway to be used for Inbound messages?
            gateway.setInbound(true);
            // Do we want the Gateway to be used for Outbound messages?
            gateway.setOutbound(true);
            // Let SMSLib know which is the SIM PIN.
            gateway.setSimPin("0000");
            // Set up the notification methods.
            Service.getInstance().setInboundMessageNotification(inboundNotification);
            //Service.getInstance().setCallNotification(callNotification);
            //Service.getInstance().setGatewayStatusNotification(statusNotification);
            //Service.getInstance().setOrphanedMessageNotification(orphanedMessageNotification);
            // Add the Gateway to the Service object.
            Service.getInstance().addGateway(gateway);
            // Similarly, you may define as many Gateway objects, representing
            // various GSM modems, add them in the Service object and control all of them.
            // Start! (i.e. connect to all defined Gateways)
            Service.getInstance().startService();
            // Printout some general information about the modem.
            System.out.println();
            System.out.println("Modem Information:");
            System.out.println("  Manufacturer: " + gateway.getManufacturer());
            System.out.println("  Model: " + gateway.getModel());
            System.out.println("  Serial No: " + gateway.getSerialNo());
            System.out.println("  SIM IMSI: " + gateway.getImsi());
            System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
            System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
            System.out.println();
            // In case you work with encrypted messages, its a good time to declare your keys.
            // Create a new AES Key with a known key value. 
            // Register it in KeyManager in order to keep it active. SMSLib will then automatically
            // encrypt / decrypt all messages send to / received from this number.
            Service.getInstance().getKeyManager().registerKey("081511207629", new AESKey(new SecretKeySpec("0011223344556677".getBytes(), "AES")));
            // Read Messages. The reading is done via the Service object and
            // affects all Gateway objects defined. This can also be more directed to a specific
            // Gateway - look the JavaDocs for information on the Service method calls.
            msgList = new ArrayList<InboundMessage>();
            Service.getInstance().readMessages(msgList, InboundMessage.MessageClasses.ALL);
            for (InboundMessage msg : msgList)
                System.out.println(msg.getOriginator());
            // Sleep now. Emulate real world situation and give a chance to the notifications
            // methods to be called in the event of message or voice call reception.
            System.out.println("Now Sleeping - Hit <enter> to stop service.");
            System.in.read();
            System.in.read();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            Service.getInstance().stopService();
        }
    }
    
    public class InboundNotification implements IInboundMessageNotification
    {
        public void process(AGateway gateway, Message.MessageTypes msgType, InboundMessage msg)
        {
            if (msgType == Message.MessageTypes.INBOUND) {
                System.out.println(">>> New Inbound message detected from Gateway: " + gateway.getGatewayId());
                System.out.println("samyan Qayyum Wahla "+ msg.getOriginator());
            }
            else if (msgType == Message.MessageTypes.STATUSREPORT) System.out.println(">>> New Inbound Status Report message detected from Gateway: " + gateway.getGatewayId());
            System.out.println(msg);
        }
    }

    public class CallNotification implements ICallNotification
    {
        public void process(AGateway gateway, String callerId)
        {
            System.out.println(">>> New call detected from Gateway: " + gateway.getGatewayId() + " : " + callerId);
        }
    }

    public class GatewayStatusNotification implements IGatewayStatusNotification
    {
        public void process(AGateway gateway, AGateway.GatewayStatuses oldStatus, AGateway.GatewayStatuses newStatus)
        {
            System.out.println(">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: " + oldStatus + " -> NEW: " + newStatus);
        }
    }

    public class OrphanedMessageNotification implements IOrphanedMessageNotification
    {
        public boolean process(AGateway gateway, InboundMessage msg)
        {
            System.out.println(">>> Orphaned message part detected from " + gateway.getGatewayId());
            System.out.println(msg);
            // Since we are just testing, return FALSE and keep the orphaned message part.
            return false;
        }
    }
}
 