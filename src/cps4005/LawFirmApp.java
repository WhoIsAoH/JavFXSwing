package cps4005;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author bikash
 */
public class LawFirmApp {
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private DatabaseConnection connection;
    private Scanner scanner;
    public LawFirmApp(){}

    public LawFirmApp(DatabaseConnection conn) {
        this.connection = conn;
        this.scanner = new Scanner(System.in);
    }
    
//    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);

        System.out.println("What do you want? GUI or CUI?");
        String choice = scanner.nextLine().toLowerCase(); // Convert input to lowercase for case-insensitive comparison

        if (choice.equals("gui")) {
            // Run GUI
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new LawFirmAppGUI().setVisible(true);
                }
            });
        } else if (choice.equals("cui")) {
            // Run CUI
            DatabaseConnection conn = new DatabaseConnection();
            LawFirmApp app = new LawFirmApp(conn);
            app.cui();
            conn.dbClose();
        } else {
            System.out.println("Invalid choice. Please enter 'gui' or 'cui'.");
        }

        scanner.close();
        
        
//        DatabaseConnection conn = new DatabaseConnection();
//        LawFirmApp app = new LawFirmApp(conn);
//        app.cui();
//        conn.dbClose();
    }
    
    public void cui(){
        System.out.println("Object Oriented Programming");
        while(true){
            System.out.println("\n\n========================================\n");
            System.out.println("+--------------------------------------+");
            System.out.println("| options\t|   Actions            |");
            System.out.println("+--------------------------------------+");
            System.out.println("| 1       \t|  Case Files          |");
            System.out.println("| 2       \t|  Client Information  |");
            System.out.println("+--------------------------------------+");
            System.out.println("Choose one option");
            int firstSelect = scanner.nextInt();
            scanner.nextLine();
            switch(firstSelect){
                case 1 -> this.caseFiles();
                case 2 -> this.clientInformation();
                default -> {throw new IllegalArgumentException("Invalid input: " + firstSelect);}
            } 
        }

    }  
    
    private void caseFiles(){
        System.out.println("\n\n========================================\n");
            System.out.println("options\t|   Actions");
            System.out.println("1 \t|  Add Case");
            System.out.println("2 \t|  View Single Case");
            System.out.println("3 \t|  View All Case");
            System.out.println("4 \t|  Update Case");
            System.out.println("5 \t|  Delete Case");
            System.out.println("Please choose the options:");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch(option){
                case 1 ->this.addCase();
                case 2 -> this.viewCase();
                case 3 -> this.viewAllCase();
                case 4 -> this.updateCase();
                case 5 -> this.deleteCase();
            }
    }
    private void clientInformation(){
      System.out.println("\n\n========================================\n");
            System.out.println("options\t|   Actions");
            System.out.println("1 \t|  Add Client");
            System.out.println("2 \t|  View Single Client");
            System.out.println("3 \t|  View All Client");
            System.out.println("4 \t|  Update Client");
            System.out.println("5 \t|  Delete Client");
            System.out.println("Please choose the options:");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch(option){
                case 1:
                    this.addClient();
                    break;
                case 2:
                    this.viewClient();
                    break;
                case 3:
                    this.viewAllClient();
                    break;
                case 4:
                    this.updateClient();
                    break;
                case 5:
                    this.deleteClient();
                    break;
                default:
                    break;
            }  
    }
    
    private void addClient(){
        System.out.println("Enter Client Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Client address: ");
        String address = scanner.nextLine();
        System.out.println("Enter Client phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter Client email: ");
        String email = scanner.nextLine();
        try{
            connection.addClient(name, address,phone,email);
            System.out.println("Client Added Successfully");
        }catch(SQLException e){
            System.out.println("Error adding client"+e); 
        }  
    }
    
    private void viewClient(){
        System.out.println("Enter Client Id: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        try{
            Client client = connection.getClient(clientId);
            if(client!=null){
                System.out.println("Id: " + client.getClient_id());
                System.out.println("Name: " + client.getClient_name());
                System.out.println("Address: " + client.getClient_address());
                System.out.println("Phone: " + client.getClient_phone());
                System.out.println("Email: " + client.getClient_email());
            }
        }catch(SQLException e){
            System.out.println("Error while fetching client"+ e.getMessage());
        }
    }
//    
    private void viewAllClient() {
        try {
            System.out.println("List of Clients:");
            for (Client client : connection.getAllClients()) {
                System.out.println("Id: " + client.getClient_id());
                System.out.println("Name: " + client.getClient_name());
                System.out.println("Address: " + client.getClient_address());
                System.out.println("Phone: " + client.getClient_phone());
                System.out.println("Email: " + client.getClient_email());
            }
        } catch (SQLException e) {
            System.out.println("Error listing clients: " + e.getMessage());
        }
    }
//    
    private void updateClient() {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            Client client = connection.getClient(clientId);
            if (client != null) {
                System.out.println("Enter Client Name: ");
                String name = scanner.nextLine();
                System.out.println("Enter Client address: ");
                String address = scanner.nextLine();
                System.out.println("Enter Client phone: ");
                String phone = scanner.nextLine();
                System.out.println("Enter Client email: ");
                String email = scanner.nextLine();
                connection.updateClient(clientId, name, address,phone,email);
                System.out.println("Client updated successfully.");
            } else {
                System.out.println("No client found with the ID " + clientId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
    }

    private  void deleteClient() {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            connection.deleteClient(clientId);
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }
    
    // case options:
    private void addCase(){
        System.out.println("Enter Case number: ");
        String number = scanner.nextLine();
        System.out.println("Enter Case Title: ");
        String title = scanner.nextLine();
        System.out.println("Enter Case Description: ");
        String description = scanner.nextLine();
        System.out.println("Enter Case Status: ");
        String status = scanner.nextLine();
        System.out.println("Enter Case date filed: ");
        String dateFiled = scanner.nextLine();
        System.out.println("Enter Case date closed: ");
        String dateClosed = scanner.nextLine();
        System.out.println("Enter Client id: ");
        int clientId = scanner.nextInt();
        scanner.nextLine();
        try{
            connection.addCase(number, title, description, status, dateFiled, dateClosed, clientId);
            System.out.println("Case Added Successfully");
        }catch(SQLException e){
            System.out.println("Error adding Case"+e); 
        }  
    }
    
    private void viewCase() {
    System.out.println("Enter Case Id: ");
    int caseId = scanner.nextInt();
    scanner.nextLine();
    try {
        Case caseObject = connection.getCase(caseId);
        if (caseObject != null) {
            System.out.println("Id: " + caseObject.getCase_id());
            System.out.println("Case Number: " + caseObject.getCase_number());
            System.out.println("Title: " + caseObject.getCase_title());
            System.out.println("Description: " + caseObject.getCase_description());
            System.out.println("Status: " + caseObject.getCase_status());
            System.out.println("Date Filed: " + caseObject.getDate_filed());
            System.out.println("Date Closed: " + caseObject.getDate_closed());
            // Assuming you have a getClient() method to fetch client information
            Client client = caseObject.getClient_id();
            if (client != null) {
                System.out.println("Client Information:");
                System.out.println("Client ID: " + client.getClient_id());
                System.out.println("Name: " + client.getClient_name());
                System.out.println("Address: " + client.getClient_address());
                System.out.println("Phone: " + client.getClient_phone());
                System.out.println("Email: " + client.getClient_email());
            } else {
                System.out.println("Client information not found for this case.");
            }
        } else {
            System.out.println("Case not found with ID: " + caseId);
        }
    } catch (SQLException e) {
        System.out.println("Error while fetching case: " + e.getMessage());
    }
}

//    
    private void viewAllCase() {
    try {
        System.out.println("List of Cases:");
        for (Case caseObject : connection.getAllCases()) {
            System.out.println("Case Id: " + caseObject.getCase_id());
            System.out.println("Case Number: " + caseObject.getCase_number());
            System.out.println("Title: " + caseObject.getCase_title());
            System.out.println("Description: " + caseObject.getCase_description());
            System.out.println("Status: " + caseObject.getCase_status());
            System.out.println("Date Filed: " + caseObject.getDate_filed());
            System.out.println("Date Closed: " + caseObject.getDate_closed());
            
            // Get the associated client
            Client client = caseObject.getClient_id();
            if (client != null) {
                System.out.println("Client Information:");
                System.out.println("Client ID: " + client.getClient_id());
                System.out.println("Name: " + client.getClient_name());
                System.out.println("Address: " + client.getClient_address());
                System.out.println("Phone: " + client.getClient_phone());
                System.out.println("Email: " + client.getClient_email());
            } else {
                System.out.println("No client information found for this case.");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error listing cases: " + e.getMessage());
    }
}

//    
    private void updateCase() {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            Client client = connection.getClient(clientId);
            if (client != null) {
                System.out.println("Enter Client Name: ");
                String name = scanner.nextLine();
                System.out.println("Enter Client address: ");
                String address = scanner.nextLine();
                System.out.println("Enter Client phone: ");
                String phone = scanner.nextLine();
                System.out.println("Enter Client email: ");
                String email = scanner.nextLine();
                connection.updateClient(clientId, name, address,phone,email);
                System.out.println("Client updated successfully.");
            } else {
                System.out.println("No client found with the ID " + clientId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating client: " + e.getMessage());
        }
    }

    private  void deleteCase() {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        try {
            connection.deleteClient(clientId);
            System.out.println("Client deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting client: " + e.getMessage());
        }
    }
    
}
