import javax.xml.crypto.Data;
import java.util.Scanner;
import java.util.Vector;

public class StateMachine
{
    enum Level {
        MAIN,
        DISPLAY,
        ADD,
        DELETE,
        EXIT,
    }

    public void run()
    {
        Level status = Level.MAIN;

        DataAccessObject doa = new DataAccessObject();
        //doa.connect();

        while (Level.EXIT != status)
        {

            switch (status) {
                case MAIN:
                {
                    int value = mainMenu();
                    status = Level.values()[value];

                    break;
                }
                case DISPLAY:

                    displayAll(doa);
                    status =  Level.MAIN;

                    break;
                case ADD:

                    addEmployee(doa);
                    status =  Level.MAIN;

                    break;
                case DELETE:
                    deleteData(doa);
                    status = Level.MAIN;
                    break;
                case EXIT:
                    break;
                 default:
                     System.out.println("Wrong value");
                     status = Level.MAIN;
                     break;

            }
        }
    }
    public void deleteData(DataAccessObject doa)
    {
        System.out.println("Indeks: ");
        Scanner input = new Scanner( System.in );
        int rowNumber = input.nextInt();
        doa.deleteRow(rowNumber);
    }


    public int mainMenu()
    {
        System.out.println("MENU");
        System.out.println();
        System.out.println("1. Lista pracowników ");
        System.out.println("2. Dodaj pracownika  ");
        System.out.println("3. Usuń pracownika  ");
        System.out.println("4. wyjdź  ");
        System.out.println();


        Scanner input = new Scanner( System.in );
        int a = input.nextInt();
        return a;
    }

    public void displayAll(DataAccessObject doa)
    {
        Vector<Employee> emoployees = doa.getData();

        for ( Employee var : emoployees){
            System.out.println("------------------------------------------");
            //System.out.println(var.getPosition());

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
    public void addEmployee(DataAccessObject doa)
    {
        System.out.println("[M]enager/[T]rader:");
        Scanner input = new Scanner( System.in );
        String out = input.next();

        switch (out)
        {
            case "M":
            case "m": {
                Manager mr = new Manager();
                mr.setPosition("Manager");
                System.out.println("PESEL:");
                mr.setPESEL(input.next());
                System.out.println("Name:");
                mr.setName(input.next());
                System.out.println("Surname:");
                mr.setSurname(input.next());
                System.out.println("Salary:");
                mr.setSalary(input.nextInt());
                System.out.println("Phone:");
                mr.setPhone(input.next());
                System.out.println("Supplement:");
                mr.setSupplement(input.nextInt());
                System.out.println("CardId:");
                mr.setCardID(input.next());
                System.out.println("Cost limit:");
                mr.setCost_limit(input.nextInt());

                doa.insertData(mr);

                break;
            }
            case "T":
            case "t": {
                Trader tr = new Trader();

                tr.setPosition("Trader");
                System.out.println("PESEL:");
                tr.setPESEL(input.next());
                System.out.println("Name:");
                tr.setName(input.next());
                System.out.println("Surname:");
                tr.setSurname(input.next());
                System.out.println("Salary:");
                tr.setSalary(input.nextInt());
                System.out.println("Phone:");
                tr.setPhone(input.next());
                System.out.println("Commission:");
                tr.setCommission(input.nextInt());
                System.out.println("Commission limit:");
                tr.setCommission_limit(input.nextInt());

                 doa.insertData(tr);
            }

           default:
               System.out.println("Wrong walue");

        }


    }




}
