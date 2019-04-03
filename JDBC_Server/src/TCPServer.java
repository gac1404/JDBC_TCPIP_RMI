import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {

    TCPServer(){};

    public void run() {

        try {
            int i = 0;

            ServerSocket s = new ServerSocket(4999);

            while(true)
            {
                Socket incoming = s.accept();
                Runnable r = new EchoThreadDB(incoming);
                Thread t = new Thread(r);
                t.start();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
