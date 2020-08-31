package workshop.pex;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.intersystems.enslib.pex.*;

/**
 * Simple Business Service implemented in Java.
 * Sends a FirstMessage to TargetConfigName
 */
public class FirstService extends BusinessService {
    
    /** TargetConfigName: production element name where message will be sent to */
    public String TargetConfigName;

    /** 
     * OnInit: initilize Business Service 
     */
    public void OnInit() throws Exception {
        LOGINFO("FirstService:OnInit()");
        
        // check init settings
        if (TargetConfigName == null) {
            LOGWARNING("Missing required TargetConfigName");
        } else {
            LOGINFO("TargetConfigname=" + TargetConfigName);
        }
        
        return;
    }

    /** 
     * OnProcessInput: process input from Inbound Adapter
     */
    public Object OnProcessInput(Object messageInput) throws Exception {

        // create a new FirstMessage request
        FirstMessage myRequest = new FirstMessage();
        
        // assign some content to the FirstMessage value
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        myRequest.value = "Sent time: " + dtf.format(now);

        // send FirtMessage to TargetConfigName using a 20s timeout:
        FirstMessage myResponse = (FirstMessage) SendRequestSync(TargetConfigName, myRequest, 20);

        return null;
    }


}