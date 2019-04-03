import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

    public String receiveMsg(String input) throws RemoteException;

}
