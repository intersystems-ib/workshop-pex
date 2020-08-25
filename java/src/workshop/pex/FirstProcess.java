package workshop.pex;

import com.intersystems.enslib.pex.*;
import com.intersystems.jdbc.IRISObject;
import com.intersystems.jdbc.IRIS;
import com.intersystems.gateway.GatewayContext;


public class FirstProcess extends BusinessProcess {
    public String Timeout = "PT10S";
    public String TargetConfigName;

    public void OnInit() throws Exception {

        LOGINFO("FirstProcess:OnInit()");
        
        // Verificar que las propiedades esten correctamente informadas
        if (TargetConfigName == null) {
            LOGWARNING("Falta valor para TargetConfigName; es necesario asignarle un valor en RemoteSettings");
        } else {
            LOGINFO("TargetConfigname=" + TargetConfigName);
        }
        
        return;
    }

    public Object OnRequest(Object request) throws Exception {
        LOGINFO("OnRequest");
        SendRequestAsync(TargetConfigName, (Message)request, true); //ResponseRequired=true
        SetTimer(Timeout, "HasTimedOut");
        return null;
    }
      
    public Object OnResponse(Object request, java.lang.Object response, Object callRequest, Object callResponse, String completionKey) throws Exception {
        LOGINFO("OnResponse, CompletionKey=" + completionKey);
        if (completionKey!= "HasTimedOut") {
            response = (FirstMessage)callResponse;
        }
        LOGINFO("Response:" + response.toString());
        return response;
    }
      
      public java.lang.Object OnComplete(java.lang.Object request, java.lang.Object response) throws java.lang.Exception {
        LOGINFO("OnComplete");
        return response;
      }

}