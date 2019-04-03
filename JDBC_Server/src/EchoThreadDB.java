import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.nio.CharBuffer;
import java.util.Vector;
import java.util.stream.Stream;

class EchoThreadDB implements Runnable {

    private Socket socket;


    public EchoThreadDB(Socket i)
    {
        socket = i;
        System.out.println("Hello!!");
    }

    public void run()
    {
        try {
        //init network
        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        PrintWriter pr = new PrintWriter(socket.getOutputStream());

        DataAccessObject doa = new DataAccessObject();
        Vector<Employee> employees = doa.getData();

        Vector<String> stringsToSend = getStringToSend(employees);


        String strCommand = null;

        while (true) {

            strCommand = bf.readLine();
           // System.out.println("token: " + strCommand);

            if (Security.validateToken(strCommand))
            {
                for(var str : stringsToSend){
                    pr.println(str);
                    pr.flush();
                }
                break;
            }
        }

        pr.println("q");
        pr.flush();

        socket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Vector<String> getStringToSend(Vector<Employee> emoployees)
    {
        Vector<String> stringsToSend = new Vector<String>();

        for(var employee : emoployees)
        {
            if(employee.getPosition().equals("Manager"))
            {
                String str =
                    employee.getPosition()   + "," +
                    employee.getID()         + "," +
                    employee.getPESEL()      + "," +
                    employee.getName()       + "," +
                    employee.getSurname()    + "," +
                    employee.getSalary()     + "," +
                    employee.getPhone()      + "," +
                    ((Manager) employee).getSupplement() + "," +
                    ((Manager) employee).getCardID()     + "," +
                    ((Manager) employee).getCost_limit();

                stringsToSend.add(str);
            }
            else if(employee.getPosition().equals("Trader"))
            {
                String str =
                    employee.getPosition()  + "," +
                    employee.getID()        + "," +
                    employee.getPESEL()     + "," +
                    employee.getName()      + "," +
                    employee.getSurname()   + "," +
                    employee.getSalary()    + "," +
                    employee.getPhone()     + "," +
                    ((Trader) employee).getCommission()         + "," +
                    ((Trader) employee).getCommission_limit();


                stringsToSend.add(str);
            }
        }

        return stringsToSend;
    }


}