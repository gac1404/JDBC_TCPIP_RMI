import java.sql.*;
import java.util.Vector;

public class DataAccessObject
{

    private String url = "jdbc:mysql://localhost:3306/employeesdb";
    private String username = "root";
    private String password = "";
    private String db = "jdbc_example";
    private String driver = "com.mysql.jdbc.Driver";

    private Connection conn = null;


    public DataAccessObject()
    {
        connect();
    }

    public boolean connect()
    {
        try
        {
            java.lang.Class.forName(driver);
            conn = DriverManager.getConnection( url , username , password);
            conn.setAutoCommit(false);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteRow(int ID)
    {
        try
        {
            String selectSQL = "DELETE FROM employeeslist WHERE ID = ?";
            PreparedStatement st = conn.prepareStatement(selectSQL);
            st.setInt(1, ID);

            st.executeUpdate();
            conn.commit();

            st.close();
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Vector<Employee> getData()
    {
        Vector<Employee> data = new Vector<Employee>();

        try {
            String query = "SELECT * FROM employeeslist";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);


            while (rs.next())
            {
                String var = rs.getString("Position");
                String mvar = "Manager";

                if(var == null)
                {
                    System.out.println("null");
                }
                else if(var.equals("Manager") == true)
                {
                    Manager manager = new Manager();
                    manager.setPosition(rs.getString("Position"));
                    manager.setPESEL(rs.getString("PESEL"));
                    manager.setName(rs.getString("Name"));
                    manager.setSurname(rs.getString("Surname"));
                    manager.setSalary(rs.getInt("Salary"));
                    manager.setPhone(rs.getString("Phone"));
                    manager.setSupplement(rs.getInt("Supplement"));
                    manager.setCardID(rs.getString("CardId"));
                    manager.setCost_limit(rs.getInt("Cost_limit"));
                    manager.setID(rs.getInt("ID"));

                    data.add(manager);
                }
                else if(var.equals("Trader") == true)
                {
                    Trader trader = new Trader();

                    trader.setPosition(rs.getString("Position"));
                    trader.setPESEL(rs.getString("PESEL"));
                    trader.setName(rs.getString("Name"));
                    trader.setSurname(rs.getString("Surname"));
                    trader.setSalary(rs.getInt("Salary"));
                    trader.setPhone(rs.getString("Phone"));
                    trader.setCommission(rs.getInt("Commission"));
                    trader.setCommission_limit(rs.getInt("Commission_limit"));
                    trader.setID(rs.getInt("ID"));

                    data.add(trader);
                }
            }

            st.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return data;
    }
    public void insertData(Employee employee)
    {
        try {
            if(employee.getPosition().equals("Manager")) {
                    String INSERT_SQL = "INSERT INTO employeeslist "+"(PESEL, Name, Surname, Salary, Phone, Supplement, CardID, Cost_limit, Position) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";

                Manager manager = (Manager)employee;

                PreparedStatement st = conn.prepareStatement(INSERT_SQL);
                st.setString( 1 , manager.getPESEL());
                st.setString( 2 , manager.getName());
                st.setString( 3 , manager.getSurname());
                st.setInt(    4 , manager.getSalary());
                st.setString( 5 , manager.getPhone());
                st.setString( 9,  manager.getPosition());
                st.setInt(    6, ((Manager) manager).getSupplement());
                st.setString( 7, ((Manager) manager).getCardID());
                st.setInt(    8, ((Manager) manager).getCost_limit());

                st.executeUpdate();
                conn.commit();

                st.close();
            }
          if(employee.getPosition().equals("Trader")) {

              String INSERT_SQL = "INSERT INTO employeeslist "+"(PESEL, Name, Surname, Salary, Phone, Commission, Commission_limit, Position) " +
                      "VALUES (?,?,?,?,?,?,?,?)";
              PreparedStatement st = conn.prepareStatement(INSERT_SQL);

              st.setString( 1 , employee.getPESEL());
              st.setString( 2 , employee.getName());
              st.setString( 3 , employee.getSurname());
              st.setInt(    4 , employee.getSalary());
              st.setString( 5 , employee.getPhone());
              st.setString( 8,  employee.getPosition());
              st.setInt(    6, ((Trader) employee).getCommission());
              st.setInt(    7, ((Trader) employee).getCommission_limit());


              st.executeUpdate();
              conn.commit();

              st.close();
          }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }




}
