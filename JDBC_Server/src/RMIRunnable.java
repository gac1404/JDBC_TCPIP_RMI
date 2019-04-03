import java.rmi.RemoteException;
        import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class RMIRunnable extends UnicastRemoteObject implements RMIService {

    static Security security = null;

    public RMIRunnable() throws RemoteException{
        super();

        if(security != null){
            security = new Security();
        }
    }

    //@Override
    public String receiveMsg(String input) throws RemoteException {

        if(Security.validateCredentials(input)){
            return Security.generateToken();
        }
        else {
            return "";
        }

    }
}
