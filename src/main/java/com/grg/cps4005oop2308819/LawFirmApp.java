package com.grg.cps4005oop2308819;

import javax.swing.*;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class LawFirmApp {
  private JTextArea outputArea;

  // Other methods and properties...

  public String getOutputAreaText() {
    return outputArea.getText();
  }
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private DatabaseConnection connection;
  private Scanner scanner;

  public LawFirmApp() {
  }


  public LawFirmApp(DatabaseConnection conn) {
    this.connection = conn;
    this.scanner = new Scanner(System.in);
  }

  public static void main(String[] args) throws SQLException {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter your email:");
    String email = scanner.nextLine();

    System.out.println("Enter your password:");
    String password = scanner.nextLine();

    DatabaseConnection conn = new DatabaseConnection();
    boolean isAuthenticated = conn.authenticate(email, password);

    if (isAuthenticated) {
        System.out.println("Authentication successful!");

        System.out.println("What do you want? GUI or CUI?");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("gui")) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new LawFirmAppGUI().setVisible(true);
                }
            });
        } else if (choice.equals("cui")) {
            conn = new DatabaseConnection();
            LawFirmApp app = new LawFirmApp(conn);
            app.cui();
            conn.dbClose();
        } else {
            System.out.println("Invalid choice. Please enter 'gui' or 'cui'.");
        }
    } else {
        System.out.println("Authentication failed. Please check your email and password.");
    }

    scanner.close();
  }

  public void cui() {
    System.out.println("Object Oriented Programming");
    while (true) {
      System.out.println("\n\n========================================\n");
      System.out.println("+--------------------------------------+");
      System.out.println("| options\t|   Actions            |");
      System.out.println("+--------------------------------------+");
      System.out.println("| 1       \t|  Case Files          |");
      System.out.println("| 2       \t|  Client Information  |");
      System.out.println("| 3       \t|  Date Files          |");
      System.out.println("| 4       \t|  Document Files      |");
      System.out.println("+--------------------------------------+");
      System.out.println("Choose one option:");
      int firstSelect = scanner.nextInt();
      scanner.nextLine();
      switch (firstSelect) {
        case 1 -> this.caseFiles();
        case 2 -> this.clientInformation();
        case 3 -> this.dateFiles();
        case 4 -> this.documentMenu();
        default -> System.out.println("Invalid option selected.");

      }
    }
  }

  private void caseFiles() {
    System.out.println("\n\n========================================\n");
    System.out.println("+--------------------------------------+");
    System.out.println("| options\t|   Actions            |");
    System.out.println("+--------------------------------------+");
    System.out.println("| 1       \t|  Add Case            |");
    System.out.println("| 2       \t|  View Single Case    |");
    System.out.println("| 3       \t|  View All Case       |");
    System.out.println("| 4       \t|  Update Case         |");
    System.out.println("| 5       \t|  Delete Case         |");
    System.out.println("+--------------------------------------+");
    System.out.println("Please choose the options:");
    int option = scanner.nextInt();
    scanner.nextLine();
    switch (option) {
      case 1 -> this.addCase();
      case 2 -> this.viewCase();
      case 3 -> this.viewAllCase();
      case 4 -> this.updateCase();
      case 5 -> this.deleteCase();
      default -> System.out.println("Invalid option selected.");
    }
  }

  private void clientInformation() {
    System.out.println("\n\n========================================\n");
    System.out.println("+--------------------------------------+");
    System.out.println("| Options\t|   Actions            |");
    System.out.println("+--------------------------------------+");
    System.out.println("| 1       \t|  Add Client          |");
    System.out.println("| 2       \t|  View Single Client  |");
    System.out.println("| 3       \t|  View All Client     |");
    System.out.println("| 4       \t|  Update Client       |");
    System.out.println("| 5       \t|  Delete Client       |");
    System.out.println("+--------------------------------------+");
    System.out.println("Please choose an option:");

    int option = scanner.nextInt();
    scanner.nextLine();
    switch (option) {
      case 1 -> addClient();
      case 2 -> viewClient();
      case 3 -> viewAllClient();
      case 4 -> updateClient();
      case 5 -> deleteClient();
      default -> System.out.println("Invalid option selected.");
    }
  }

  private void dateFiles() {
    System.out.println("\n\n========================================\n");
    System.out.println("+--------------------------------------+");
    System.out.println("| Options\t|   Actions            |");
    System.out.println("+--------------------------------------+");
    System.out.println("| 1       \t|  Add Date            |");
    System.out.println("| 2       \t|  View Single Date    |");
    System.out.println("| 3       \t|  View All Date       |");
    System.out.println("| 4       \t|  Update Date         |");
    System.out.println("| 5       \t|  Delete Date         |");
    System.out.println("+--------------------------------------+");
    System.out.println("Please choose an option:");

    int option = scanner.nextInt();
    scanner.nextLine();
    switch (option) {
      case 1 -> addDate();
      case 2 -> viewDate();
      case 3 -> viewAllDate();
      case 4 -> updateDate();
      case 5 -> deleteDate();
      default -> System.out.println("Invalid option selected.");
    }
  }

  private void documentMenu() {
    System.out.println("\n\n========================================\n");
    System.out.println("+--------------------------------------+");
    System.out.println("| Option\t|   Action             |");
    System.out.println("+--------------------------------------+");
    System.out.println("| 1      \t|  Add Document        |");
    System.out.println("| 2      \t|  View Single Document|");
    System.out.println("| 3      \t|  View All Documents  |");
    System.out.println("| 4      \t|  Update Document     |");
    System.out.println("| 5      \t|  Delete Document     |");
    System.out.println("+--------------------------------------+");
    System.out.println("Please choose an option:");

    int option = scanner.nextInt();
    scanner.nextLine();

    switch (option) {
      case 1 -> addDocument();
      case 2 -> viewDocument();
      case 3 -> viewAllDocuments();
      case 4 -> updateDocument();
      case 5 -> deleteDocument();
      default -> System.out.println("Invalid option selected. Please try again.");
    }
  }

  private void addClient() {
    System.out.println("Enter Client Name: ");
    String name = scanner.nextLine();
    System.out.println("Enter Client address: ");
    String address = scanner.nextLine();
    System.out.println("Enter Client phone: ");
    String phone = scanner.nextLine();
    System.out.println("Enter Client email: ");
    String email = scanner.nextLine();
    try {
      connection.addClient(name, address, phone, email);
      System.out.println("Client Added Successfully");
    } catch (SQLException e) {
      System.out.println("Error adding client" + e);
    }
  }

  private void viewClient() {
    System.out.println("Enter Client Id: ");
    int clientId = scanner.nextInt();
    scanner.nextLine();
    try {
      Client client = connection.getClient(clientId);
      if (client != null) {
        System.out.println("Id: " + client.getClient_id());
        System.out.println("Name: " + client.getClient_name());
        System.out.println("Address: " + client.getClient_address());
        System.out.println("Phone: " + client.getClient_phone());
        System.out.println("Email: " + client.getClient_email());
      }
    } catch (SQLException e) {
      System.out.println("Error while fetching client" + e.getMessage());
    }
  }

  public void viewAllClient() {
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

  public void updateClient() {
    System.out.print("Enter client ID: ");
    int clientId = scanner.nextInt();
    scanner.nextLine();

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
        connection.updateClient(clientId, name, address, phone, email);
        System.out.println("Client updated successfully.");
      } else {
        System.out.println("No client found with the ID " + clientId);
      }
    } catch (SQLException e) {
      System.out.println("Error updating client: " + e.getMessage());
    }
  }

  private void deleteClient() {
    System.out.print("Enter client ID: ");
    int clientId = scanner.nextInt();
    scanner.nextLine();

    try {
      connection.deleteClient(clientId);
      System.out.println("Client deleted successfully.");
    } catch (SQLException e) {
      System.out.println("Error deleting client: " + e.getMessage());
    }
  }

  // case options:
  private void addCase() {
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
    try {
      connection.addCase(number, title, description, status, dateFiled, dateClosed, clientId);
      System.out.println("Case Added Successfully");
    } catch (SQLException e) {
      System.out.println("Error adding Case" + e);
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

  public void viewAllCase() {
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

  private void updateCase() {
    System.out.print("Enter Case ID: ");
    int caseId = scanner.nextInt();
    scanner.nextLine();

    try {
      Case existingCase = connection.getCase(caseId);
      if (existingCase != null) {
        System.out.println("Enter new Case Number: ");
        String number = scanner.nextLine();
        System.out.println("Enter new Case Title: ");
        String title = scanner.nextLine();
        System.out.println("Enter new Case Description: ");
        String description = scanner.nextLine();
        System.out.println("Enter new Case Status: ");
        String status = scanner.nextLine();
        System.out.println("Enter new Date Filed: ");
        Date dateFiled = Date.valueOf(scanner.nextLine());
        System.out.println("Enter new Date Closed: ");
        Date dateClosed = Date.valueOf(scanner.nextLine());

        connection.updateCase(caseId, number, title, description, status, dateFiled, dateClosed);
        System.out.println("Case updated successfully.");
      } else {
        System.out.println("No case found with the ID " + caseId);
      }
    } catch (SQLException e) {
      System.out.println("Error updating case: " + e.getMessage());
    }
  }

  private void deleteCase() {
    System.out.print("Enter client ID: ");
    int clientId = scanner.nextInt();
    scanner.nextLine();

    try {
      connection.deleteClient(clientId);
      System.out.println("Client deleted successfully.");
    } catch (SQLException e) {
      System.out.println("Error deleting client: " + e.getMessage());
    }
  }

  // Add date
  private void addDate() {
    System.out.print("Enter Case ID: ");
    int caseId = scanner.nextInt();
    scanner.nextLine();
    System.out.print("Enter Event Date (YYYY-MM-DD): ");
    String eventDateStr = scanner.nextLine();
    Date eventDate = Date.valueOf(eventDateStr);
    System.out.print("Enter Event Description: ");
    String eventDescription = scanner.nextLine();

    try {
      connection.addDate(caseId, eventDate, eventDescription);
      System.out.println("Date added successfully.");
    } catch (SQLException e) {
      System.out.println("Error adding date: " + e.getMessage());
    }
  }

  // View date
  private void viewDate() {
    System.out.print("Enter Date ID: ");
    int dateId = scanner.nextInt();
    scanner.nextLine();
    try {
      Important_Dates date = connection.viewDate(dateId);
      if (date != null) {
        System.out.println("Important_Dates:");
        System.out.println("Date ID: " + date.getDate_id());
        System.out.println("Event Date: " + date.getEvent_date());
        System.out.println("Event Description: " + date.getEvent_description());

        Case caseObject = date.getCase_id();
        if (caseObject != null) {
          System.out.println("\nAssociated Case:");
          System.out.println("Case ID: " + caseObject.getCase_id());
          System.out.println("Case Number: " + caseObject.getCase_number());
          System.out.println("Title: " + caseObject.getCase_title());
          System.out.println("Description: " + caseObject.getCase_description());
          System.out.println("Status: " + caseObject.getCase_status());
          System.out.println("Date Filed: " + caseObject.getDate_filed());
          System.out.println("Date Closed: " + caseObject.getDate_closed());

          Client client = caseObject.getClient_id();
          if (client != null) {
            System.out.println("\nAssociated Client:");
            System.out.println("Client ID: " + client.getClient_id());
            System.out.println("Name: " + client.getClient_name());
            System.out.println("Address: " + client.getClient_address());
            System.out.println("Phone: " + client.getClient_phone());
            System.out.println("Email: " + client.getClient_email());
          } else {
            System.out.println("Client information not found for this case.");
          }
        } else {
          System.out.println("\nAssociated Case information not found for this date.");
        }
      } else {
        System.out.println("Date not found with ID: " + dateId);
      }
    } catch (SQLException e) {
      System.out.println("Error viewing date: " + e.getMessage());
    }
  }

  // View all date
  private void viewAllDate() {
    try {
      List<Important_Dates> dateList = connection.viewAllDate();
      System.out.println("List of Date:");
      for (Important_Dates date : dateList) {
        System.out.println("Important_Dates:");
        System.out.println("Date ID: " + date.getDate_id());
        System.out.println("Event Date: " + date.getEvent_date());
        System.out.println("Event Description: " + date.getEvent_description());

        Case caseObject = date.getCase_id();
        if (caseObject != null) {
          System.out.println("\nAssociated Case:");
          System.out.println("Case ID: " + caseObject.getCase_id());
          System.out.println("Case Number: " + caseObject.getCase_number());
          System.out.println("Title: " + caseObject.getCase_title());
          System.out.println("Description: " + caseObject.getCase_description());
          System.out.println("Status: " + caseObject.getCase_status());
          System.out.println("Date Filed: " + caseObject.getDate_filed());
          System.out.println("Date Closed: " + caseObject.getDate_closed());

          Client client = caseObject.getClient_id();
          if (client != null) {
            System.out.println("\nAssociated Client:");
            System.out.println("Client ID: " + client.getClient_id());
            System.out.println("Name: " + client.getClient_name());
            System.out.println("Address: " + client.getClient_address());
            System.out.println("Phone: " + client.getClient_phone());
            System.out.println("Email: " + client.getClient_email());
          } else {
            System.out.println("Client information not found for this case.");
          }
        } else {
          System.out.println("\nAssociated Case information not found for this date.");
        }
      }
    } catch (SQLException e) {
      System.out.println("Error viewing all date: " + e.getMessage());
    }
  }

  // Update date
  private void updateDate() {
    System.out.print("Enter Date ID: ");
    int dateId = scanner.nextInt();
    scanner.nextLine();

    try {
      Important_Dates date = connection.viewDate(dateId);
      if (date != null) {
        System.out.println("Current Event Date: " + date.getEvent_date());
        System.out.println("Current Event Description: " + date.getEvent_description());
        System.out.print("Enter New Event Date (YYYY-MM-DD): ");
        String newEventDate = scanner.nextLine();
        Date eventDate = Date.valueOf(newEventDate);

        System.out.print("Enter New Event Description: ");
        String newEventDescription = scanner.nextLine();

        connection.updateDate(dateId, date.getCase_id().getCase_id(), eventDate, newEventDescription);
        System.out.println("Date updated successfully.");
      } else {
        System.out.println("Date not found with ID: " + dateId);
      }
    } catch (SQLException e) {
      System.out.println("Error updating date: " + e.getMessage());
    }
  }

  // Delete date
  private void deleteDate() {
    System.out.print("Enter Date ID: ");
    int dateId = scanner.nextInt();
    scanner.nextLine();
    try {
      connection.deleteDate(dateId);
      System.out.println("Date deleted successfully.");
    } catch (SQLException e) {
      System.out.println("Error deleting date: " + e.getMessage());
    }
  }

  //documents case:
  private void addDocument() {
    System.out.print("Enter Case ID: ");
    int caseId = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Enter Document Name: ");
    String documentName = scanner.nextLine();

    System.out.print("Enter Document Type: ");
    String documentType = scanner.nextLine();

    System.out.print("Enter Document Path: ");
    String documentPath = scanner.nextLine();

    try {
      connection.addDocument(caseId, documentName, documentType, documentPath);
      System.out.println("Document added successfully.");
    } catch (SQLException e) {
      System.out.println("Error adding document: " + e.getMessage());
    }
  }

  private void viewDocument() {
    System.out.print("Enter Document ID: ");
    int documentId = scanner.nextInt();
    scanner.nextLine();

    try {
      Documents document = connection.viewDocument(documentId);
      if (document != null) {
        System.out.println("Document Details:");
        System.out.println("Document ID: " + document.getDocument_id());
        System.out.println("Document Name: " + document.getDocument_name());
        System.out.println("Document Type: " + document.getDocument_type());
        System.out.println("Document Path: " + document.getDocument_path());
        Case clientCase = document.getCase_id();
        if (clientCase != null) {
          System.out.println("\nAssociated Case:");
          System.out.println("Case ID: " + clientCase.getCase_id());
          System.out.println("Case Number: " + clientCase.getCase_number());
        }
      } else {
        System.out.println("No document found with ID: " + documentId);
      }
    } catch (SQLException e) {
      System.out.println("Error viewing document: " + e.getMessage());
    }
  }

  private void viewAllDocuments() {
    try {
      List<Documents> documents = connection.viewAllDocuments();
      System.out.println("List of Documents:");
      for (Documents document : documents) {
        System.out.println("Document ID: " + document.getDocument_id());
        System.out.println("Document Name: " + document.getDocument_name());
        System.out.println("Document Type: " + document.getDocument_type());
        System.out.println("Document Path: " + document.getDocument_path());
        Case clientCase = document.getCase_id();
        if (clientCase != null) {
          System.out.println("\nAssociated Case:");
          System.out.println("Case ID: " + clientCase.getCase_id());
        }
      }
    } catch (SQLException e) {
      System.out.println("Error viewing all documents: " + e.getMessage());
    }
  }

  private void updateDocument() {
    System.out.print("Enter Document ID: ");
    int documentId = scanner.nextInt();
    scanner.nextLine();

    try {
      Documents document = connection.viewDocument(documentId);
      if (document != null) {
        System.out.print("Enter New Document Name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter New Document Type: ");
        String newType = scanner.nextLine();

        System.out.print("Enter New Document Path: ");
        String newPath = scanner.nextLine();

        System.out.print("Enter New Document Path: ");
        int newClientId = scanner.nextInt();
        scanner.nextLine();

        connection.updateDocument(documentId, newClientId, newName, newType, newPath);
        System.out.println("Document updated successfully.");
      } else {
        System.out.println("Document not found with ID: " + documentId);
      }
    } catch (SQLException e) {
      System.out.println("Error updating document: " + e.getMessage());
    }
  }

  private void deleteDocument() {
    System.out.print("Enter Document ID: ");
    int documentId = scanner.nextInt();
    scanner.nextLine();

    try {
      connection.deleteDocument(documentId);
      System.out.println("Document deleted successfully.");
    } catch (SQLException e) {
      System.out.println("Error deleting document: " + e.getMessage());
    }
  }


}
