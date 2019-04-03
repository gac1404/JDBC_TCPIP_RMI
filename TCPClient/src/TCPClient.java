import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.util.Arrays;
import java.util.List;

class TCPClient {

    private Socket s;

    private PrintWriter pr;
    private InputStreamReader in;
    private BufferedReader bf;

    public TCPClient()
    {
        //connect();
    }

    public  Vector<Employee> downloadEmployeesDB(String token)
    {
        Vector<String> rawData = new Vector<>();

        try {

            //token = "requestDB";
            pr.println(token);
            pr.flush();

            String str = null;

            while(true)
            {
                str = bf.readLine();

                if(str.equals("q")){
                    break;
                }

                rawData.add(str);
            }

            pr.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Vector<Employee> employees = convert2Emploees(rawData);

        return employees;
    }
    public boolean connect()
    {
        try {
            s = new Socket("localhost", 4999);

            pr = new PrintWriter(s.getOutputStream());
            in = new InputStreamReader(s.getInputStream());
            bf = new BufferedReader(in);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
    public Vector<Employee> convert2Emploees(Vector<String> rawData)
    {
        Vector<Employee> employees = new Vector<>();

        for(var str : rawData){

            String[] employeeStr = str.split(",");


            if(employeeStr[0].equals("Manager"))
            {
                Manager manager = new Manager();
                manager.setPosition(employeeStr[0]);
                manager.setID(Integer.parseInt(employeeStr[1]));
                manager.setPESEL(employeeStr[2]);
                manager.setName(employeeStr[3]);
                manager.setSurname(employeeStr[4]);
                manager.setSalary(Integer.parseInt(employeeStr[5]));
                manager.setPhone(employeeStr[6]);
                manager.setSupplement(Integer.parseInt(employeeStr[7]));
                manager.setCardID(employeeStr[8]);
                manager.setCost_limit(Integer.parseInt(employeeStr[9]));

                employees.add(manager);
            }
            else if(employeeStr[0].equals("Trader"))
            {
                Trader trader = new Trader();

                trader.setPosition(employeeStr[0]);
                trader.setID(Integer.parseInt(employeeStr[1]));
                trader.setPESEL(employeeStr[2]);
                trader.setName(employeeStr[3]);
                trader.setSurname(employeeStr[4]);
                trader.setSalary(Integer.parseInt(employeeStr[5]));
                trader.setPhone(employeeStr[6]);
                trader.setCommission(Integer.parseInt(employeeStr[7]));
                trader.setCommission_limit(Integer.parseInt(employeeStr[8]));

                employees.add(trader);
            }
        }

        return employees;
    }



}