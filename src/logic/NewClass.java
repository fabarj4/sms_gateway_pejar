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
import logic.Ex_sendmessage;

public class NewClass {
    public static void main(String []args){
        Ex_sendmessage app = new Ex_sendmessage();
        String[] nohp = new String[]{"081546055645","081546055645"};
        try
        {
            //app.doIt("+6281546055645", "SMAN 7 BOGOR \n\n#Hello from SMSLib! 5");
            app.sendMessage(nohp, "SMAN 7 BOGOR \n\n#Hello from SMSLib! faisal");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
