import java.util.UUID;

public class Main
{

    public static void main(String[] args)
    {

        StateMachine stateMachine = new StateMachine();

        //RMI server start
        RMIServer rmiServer = new RMIServer();

        //run network solution
        Runnable r = new TCPServer();
        Thread t = new Thread(r);

        t.start();

        //main program start
        stateMachine.run();

    }

}
