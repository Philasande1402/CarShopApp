package carshopapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarShopApp {

    public static void main(String[] args)  {
        
        final String URL ="jdbc:derby://localhost:1527/CarShop";
        
        // Establish connection to the database
        Connection conn = null;
        
        // Create a Statement object
        Statement statement =null;
        
        // Execute the SELECT *FROM CARS 
        ResultSet rs = null;
        
        // I n i t i a l i z e Scanner for user input
        Scanner scanner = new Scanner(System.in);
        
        try {
            
            conn = DriverManager.getConnection(URL);
            statement = conn.createStatement();
            
            boolean running = true;
            
            while(running)
            {
                //Display menu
                System.out.println("Menu:");
                System.out.println("1. Check Stock");
                System.out.println("2. Exit");
                System.out.println("Choose an option: ");
                int choice = scanner.nextInt();
                
                rs = statement.executeQuery("SELECT * FROM Cars");
                
                switch(choice)
                {
                    case 1:
                        // Call the checkStockByYear function
                        checkStockByYear(rs);
                        break;
                        
                    case 2:
                        running=false;
                        System.out.println("Exiting...");
                        break;
                        
                    default:
                        System.out.println("Invalid option.Please choose a valid option ");
                        break;
                }
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            try{
                if(conn!=null) conn.close();
                if(statement!=null) conn.close();
                if(rs!=null) rs.close();
            }catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        scanner.close();
        
        
    }
    
    private static void checkStockByYear(ResultSet rs) throws SQLException{
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a manufacture year ? ");
        int userManufactureYear=scanner.nextInt();
        
        int countNumberOfCars=0;
        
        while(rs.next())
        {
            int manufacture_year=rs.getInt("manufacture_year");
            if(userManufactureYear==manufacture_year)
            {
                countNumberOfCars++;
            }
        }
        System.out.println("The number of cars available for the year "+userManufactureYear+" is "+countNumberOfCars);
        
    }
    
}
