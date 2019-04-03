import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {

    RMIService service = null;

    public RMIClient() throws RemoteException, NotBoundException, MalformedURLException {

        service = (RMIService) Naming.lookup("rmi://localhost:5099/EmployeeService");
        //System.out.println("---" + service.receiveMsg("hey server"));
    }
    public String login(String userName , String password)
    {
        String loginStr = userName + "|" + password;

        try {
            return service.receiveMsg(loginStr);
        }
        catch (RemoteException e){
            System.out.println(e);
            return "";
        }
    }


}
