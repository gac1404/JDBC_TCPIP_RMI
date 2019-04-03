import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Vector;

public class StateMachine {

    Vector<Employee> emoployees;

    private RMIClient rmiClient = null;
    private String token = null;

    enum Level {
        MAIN,
        DOWNLOAD,
        DISPLAY,
        SAVE,
        EXIT,
        LOGIN,

    }

    public void run()
    {

        Level status = Level.LOGIN;

        while (Level.EXIT != status)
        {
            switch (status) {
                case LOGIN:
                {
                    int value = mainLogin();
                    status = Level.values()[value];

                    break;
                }
                case MAIN:
                {
                    int value = mainMenu();
                    status = Level.values()[value];

                    break;
                }
                case DOWNLOAD:
                {
                    mainDownload();
                    status =  Level.MAIN;

                    break;
                }
                case SAVE:
                {
                    System.out.println("Save");
                    saveToFile(emoployees);
                    status =  Level.MAIN;

                    break;
                }
                case DISPLAY:
                {
                    displayAll(emoployees);
                    status =  Level.MAIN;

                    break;
                }
                default:
                    System.out.println("Wrong value");
                    status = Level.MAIN;
                    break;

            }


        }

    }
    public int mainLogin()
    {
        if(rmiClient == null){
            try{
                rmiClient = new RMIClient();
            }
            catch (RemoteException e){
                System.out.println(e);
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        Scanner input = new Scanner( System.in );
        System.out.println("------------------------------------------");
        System.out.println("Nazwa użytkownika");
        String userName = input.next();
        System.out.println("Hasło");
        String password = input.next();

        token = rmiClient.login(userName , password);

        if(token.equals("")){
            System.out.println("Login failed");
            return (Level.LOGIN.ordinal());
        }
        else {
            System.out.println("Token granted");
            return (Level.MAIN.ordinal());
        }



    }

    public int mainMenu()
    {
        System.out.println("------------------------------------------");
        System.out.println("MENU");
        System.out.println();
        System.out.println("1. Pobierz listę z sieci");
        System.out.println("2. Wyświetl");
        System.out.println("3. Zapisz  ");
        System.out.println("4. Wyjdź  ");
        System.out.println();


        Scanner input = new Scanner( System.in );
        int a = input.nextInt();
        System.out.println("------------------------------------------");

        return a;
    }
    public void mainDownload()
    {
        TCPClient tcpClient = new TCPClient();
        tcpClient.connect();

        emoployees = tcpClient.downloadEmployeesDB(token);
        System.out.println("------------------------------------------");
        System.out.println("Data downloaded");
        System.out.println("------------------------------------------");
    }
    public void displayAll(Vector<Employee> emoployees)
    {

        for ( Employee var : emoployees){
            System.out.println("------------------------------------------");

            if(var.getPosition().equals("Manager") == true)
            {
                System.out.println("ID: " + var.getID());
                System.out.println("PESEL: " + var.getPESEL());
                System.out.println("Name:" + var.getName());
                System.out.println("Surname:" + var.getSurname());
                System.out.println("Salary:" + var.getSalary());
                System.out.println("Phone:" + var.getPhone());
                System.out.println("Supplement:" + ((Manager)var).getSupplement());
                System.out.println("CardId:" + ((Manager)var).getCardID());
                System.out.println("Cost limit:" + ((Manager)var).getCost_limit());
            }
            else if(var.getPosition().equals("Trader") == true)
            {
                System.out.println("ID: " + var.getID());
                System.out.println("PESEL: " + var.getPESEL());
                System.out.println("Name:" + var.getName());
                System.out.println("Surname:" + var.getSurname());
                System.out.println("Salary:" + var.getSalary());
                System.out.println("Phone:" + var.getPhone());
                System.out.println("Commission:" + ((Trader)var).getCommission());
                System.out.println("Commission limit:" + ((Trader)var).getCommission_limit());
            }

            System.out.println("------------------------------------------");
        }
    }
    public void saveToFile(Vector<Employee> emoployees)
    {
        try
        {
            PrintWriter out = new PrintWriter("DBContent.txt");
            out.println("test");

            for ( Employee var : emoployees) {

                if (var.getPosition().equals("Manager") == true) {
                    out.println("ID: " + var.getID());
                    out.println("PESEL: " + var.getPESEL());
                    out.println("Name:" + var.getName());
                    out.println("Surname:" + var.getSurname());
                    out.println("Salary:" + var.getSalary());
                    out.println("Phone:" + var.getPhone());
                    out.println("Supplement:" + ((Manager) var).getSupplement());
                    out.println("CardId:" + ((Manager) var).getCardID());
                    out.println("Cost limit:" + ((Manager) var).getCost_limit());
                } else if (var.getPosition().equals("Trader") == true) {
                    out.println("ID: " + var.getID());
                    out.println("PESEL: " + var.getPESEL());
                    out.println("Name:" + var.getName());
                    out.println("Surname:" + var.getSurname());
                    out.println("Salary:" + var.getSalary());
                    out.println("Phone:" + var.getPhone());
                    out.println("Commission:" + ((Trader) var).getCommission());
                    out.println("Commission limit:" + ((Trader) var).getCommission_limit());
                }

                out.println("------------------------------------------");
            }


            out.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }




}
