package workshop.pex;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.intersystems.enslib.pex.*;
import com.intersystems.jdbc.IRISObject;
import com.intersystems.jdbc.IRIS;
import com.intersystems.gateway.GatewayContext;

public class FirstService extends BusinessService {
    
    public String TargetConfigName;

    public void OnInit() throws Exception {

        LOGINFO("FirstService:OnInit()");
        
        // Verificar que las propiedades esten correctamente informadas
        if (TargetConfigName == null )
        {
            LOGWARNING("Falta valor para TargetConfigName; es necesario asignarle un valor en RemoteSettings");
        }else
        {
            LOGINFO("TargetConfigname=" + TargetConfigName);
        }
        
        return;
    }

    public Object OnProcessInput(Object messageInput) throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        
        //crear un nuevo Objeto de Petición
        FirstMessage myRequest = new FirstMessage();
        myRequest.value = "La Hora de envio es: " + dtf.format(now);

        //Para Enviar Sin esperar una respuesta:
        //SendRequestAsync("PEX.Webinar.FirstOperation", myRequest);

        //Para Enviar y Esperar la respuesta con un timeout de 20 segundos:
        FirstMessage myResponse=(FirstMessage) SendRequestSync(TargetConfigName, myRequest, 20);

        return null;
    }


}