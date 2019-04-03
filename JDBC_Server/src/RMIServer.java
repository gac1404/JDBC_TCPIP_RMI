import java.rmi.RemoteException;
        import java.rmi.registry.LocateRegistry;
        import java.rmi.registry.Registry;

public class RMIServer {

    public RMIServer()
    {
        try {
            Registry registry = LocateRegistry.createRegistry(5099);
            registry.rebind("EmployeeService", new RMIRunnable());
        }
        catch (RemoteException e)
        {
            System.out.println(e);
        }

    }

}
