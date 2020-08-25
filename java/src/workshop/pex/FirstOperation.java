package workshop.pex;

import com.intersystems.enslib.pex.*;
import com.intersystems.jdbc.IRISObject;
import com.intersystems.jdbc.IRIS;
import com.intersystems.gateway.GatewayContext;

import java.util.Properties;

public class FirstOperation extends BusinessOperation {
    // Connection to InterSystems IRIS
    private IRIS iris;

    // Name of our Producer
    public String CLIENTID;

    public void OnInit() throws Exception {

        LOGINFO("FirstOperation:OnInit()");
        
        iris = GatewayContext.getIRIS();
        LOGINFO(String.format("CLIENTID: %s", CLIENTID));
        
        return;
    }

    public void OnTearDown() throws Exception {
        LOGINFO("FirstOperation:OnTearDown()");
        return;
    }

    public Object OnMessage(Object request) throws Exception {
        FirstMessage response = new FirstMessage();
        response.value = ((FirstMessage)request).value.toUpperCase();

        return response;
    }
}
