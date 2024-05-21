package com.grg.cps4005oop2308819;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class LawFirmAppGUI extends JFrame {

  private final DatabaseConnection connection;
  private final JTextArea outputArea;

  public LawFirmAppGUI() {
    super("Law Firm App");
    connection = new DatabaseConnection();

    // Set up main frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);

    // Create output area
    outputArea = new JTextArea(20, 60);
    outputArea.setEditable(false);

    // Create buttons for client actions
    JButton viewClientButton = new JButton("View Client");
    JButton addClientButton = new JButton("Add Client");
    JButton deleteClientButton = new JButton("Delete Client");
    JButton viewAllClientsButton = new JButton("View All Clients");
    JButton updateClientButton = new JButton("Update Client");

    // Create buttons for case actions
    JButton viewCaseButton = new JButton("View Case");
    JButton addCaseButton = new JButton("Add Case");
    JButton deleteCaseButton = new JButton("Delete Case");
    JButton viewAllCasesButton = new JButton("View All Cases");
    JButton updateCaseButton = new JButton("Update Case");

    // Create buttons for date actions
    JButton addDateButton = new JButton("Add Date");
    JButton viewDateButton = new JButton("View Date");
    JButton viewAllDatesButton = new JButton("View All Dates");
    JButton updateDateButton = new JButton("Update Date");
    JButton deleteDateButton = new JButton("Delete Date");

    // Create buttons for document actions
    JButton addDocumentButton = new JButton("Add Document");
    JButton viewDocumentButton = new JButton("View Document");
    JButton viewAllDocumentsButton = new JButton("View All Documents");
    JButton updateDocumentButton = new JButton("Update Document");
    JButton deleteDocumentButton = new JButton("Delete Document");

    JPanel buttonPanel = new JPanel(new GridLayout(4, 5, 10, 3)); // 4 rows, 5 columns with gaps

    // Add client buttons to panel
    buttonPanel.add(addClientButton);
    buttonPanel.add(viewClientButton);
    buttonPanel.add(viewAllClientsButton);
    buttonPanel.add(updateClientButton);
    buttonPanel.add(deleteClientButton);

    // Add case buttons to panel
    buttonPanel.add(addCaseButton);
    buttonPanel.add(viewCaseButton);
    buttonPanel.add(viewAllCasesButton);
    buttonPanel.add(updateCaseButton);
    buttonPanel.add(deleteCaseButton);

    // Add date buttons to panel
    buttonPanel.add(addDateButton);
    buttonPanel.add(viewDateButton);
    buttonPanel.add(viewAllDatesButton);
    buttonPanel.add(updateDateButton);
    buttonPanel.add(deleteDateButton);

    // Add document buttons to panel
    buttonPanel.add(addDocumentButton);
    buttonPanel.add(viewDocumentButton);
    buttonPanel.add(viewAllDocumentsButton);
    buttonPanel.add(updateDocumentButton);
    buttonPanel.add(deleteDocumentButton);

    JScrollPane scrollPane = new JScrollPane(outputArea);

    // Add components to the frame
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(buttonPanel, BorderLayout.CENTER);
    getContentPane().add(scrollPane, BorderLayout.SOUTH);

    // Add action listeners to client buttons
    viewClientButton.addActionListener(createClientActionListener("View"));
    addClientButton.addActionListener(createClientActionListener("Add"));
    deleteClientButton.addActionListener(createClientActionListener("Delete"));
    viewAllClientsButton.addActionListener(createClientActionListener("View All"));
    updateClientButton.addActionListener(createClientActionListener("Update"));

    // Add action listeners to case buttons
    viewCaseButton.addActionListener(createCaseActionListener("View"));
    addCaseButton.addActionListener(createCaseActionListener("Add"));
    deleteCaseButton.addActionListener(createCaseActionListener("Delete"));
    viewAllCasesButton.addActionListener(createCaseActionListener("View All"));
    updateCaseButton.addActionListener(createCaseActionListener("Update"));

    // Add action listeners to date buttons
    addDateButton.addActionListener(createDateActionListener("Add"));
    viewDateButton.addActionListener(createDateActionListener("View"));
    viewAllDatesButton.addActionListener(createDateActionListener("View All"));
    updateDateButton.addActionListener(createDateActionListener("Update"));
    deleteDateButton.addActionListener(createDateActionListener("Delete"));

    // Add action listeners to document buttons
    addDocumentButton.addActionListener(createDocumentActionListener("Add"));
    viewDocumentButton.addActionListener(createDocumentActionListener("View"));
    viewAllDocumentsButton.addActionListener(createDocumentActionListener("View All"));
    updateDocumentButton.addActionListener(createDocumentActionListener("Update"));
    deleteDocumentButton.addActionListener(createDocumentActionListener("Delete"));

    setVisible(true);
  }

  private ActionListener createClientActionListener(final String action) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
          @Override
          protected Void doInBackground() throws Exception {
            if (action.equals("View")) {
              askForClientId("View");
            } else if (action.equals("Add")) {
              addClient();
            } else if (action.equals("Delete")) {
              askForClientId("Delete");
            } else if (action.equals("View All")) {
              viewAllClients();
            } else if (action.equals("Update")) {
              askForClientId("Update");
            }
            return null;
          }
        };
        worker.execute();
      }
    };
  }

  private ActionListener createCaseActionListener(final String action) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
          @Override
          protected Void doInBackground() throws Exception {
            if (action.equals("View")) {
              askForCaseId("View");
            } else if (action.equals("Add")) {
              addCase();
            } else if (action.equals("Delete")) {
              askForCaseId("Delete");
            } else if (action.equals("View All")) {
              viewAllCases();
            } else if (action.equals("Update")) {
              askForCaseId("Update");
            }
            return null;
          }
        };
        worker.execute();
      }
    };
  }

  private ActionListener createDateActionListener(final String action) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
          @Override
          protected Void doInBackground() throws Exception {
            if (action.equals("Add")) {
              addDate();
            } else if (action.equals("View")) {
              askForDateId("View");
            } else if (action.equals("View All")) {
              viewAllDates();
            } else if (action.equals("Update")) {
              askForDateId("Update");
            } else if (action.equals("Delete")) {
              askForDateId("Delete");
            }
            return null;
          }
        };
        worker.execute();
      }
    };
  }

  private ActionListener createDocumentActionListener(final String action) {
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
          @Override
          protected Void doInBackground() throws Exception {
            if (action.equals("Add")) {
              addDocument();
            } else if (action.equals("View")) {
              askForDocumentId("View");
            } else if (action.equals("View All")) {
              viewAllDocuments();
            } else if (action.equals("Update")) {
              askForDocumentId("Update");
            } else if (action.equals("Delete")) {
              askForDocumentId("Delete");
            }
            return null;
          }
        };
        worker.execute();
      }
    };
  }

  private void askForClientId(String action) {
    JTextField clientIdField = new JTextField(10);
    Object[] fields = {"Client ID:", clientIdField};
    int result = JOptionPane.showConfirmDialog(null, fields, action + " Client", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      int clientId = Integer.parseInt(clientIdField.getText());
      if (action.equals("View")) {
        viewClient(clientId);
      } else if (action.equals("Delete")) {
        deleteClient(clientId);
      } else if (action.equals("Update")) {
        updateClient(clientId);
      }
    }
  }

  private void askForCaseId(String action) {
    JTextField caseIdField = new JTextField(10);
    Object[] fields = {"Case ID:", caseIdField};
    int result = JOptionPane.showConfirmDialog(null, fields, action + " Case", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      int caseId = Integer.parseInt(caseIdField.getText());
      if (action.equals("View")) {
        viewCase(caseId);
      } else if (action.equals("Delete")) {
        deleteCase(caseId);
      } else if (action.equals("Update")) {
        updateCase(caseId);
      }
    }
  }

  private void viewClient(int clientId) {
    try {
      Client client = connection.getClient(clientId);
      if (client != null) {
        outputArea.setText("Client ID: " + client.getClient_id() + "\n" + "Name: " + client.getClient_name() + "\n" + "Address: " + client.getClient_address() + "\n" + "Phone: " + client.getClient_phone() + "\n" + "Email: " + client.getClient_email() + "\n");
      } else {
        outputArea.setText("Client not found with ID: " + clientId);
      }
    } catch (SQLException ex) {
      outputArea.setText("Error fetching client: " + ex.getMessage());
    }
  }

  private void addClient() {
    JTextField nameField = new JTextField();
    JTextField addressField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField emailField = new JTextField();

    Object[] fields = {"Name:", nameField, "Address:", addressField, "Phone:", phoneField, "Email:", emailField};

    int result = JOptionPane.showConfirmDialog(null, fields, "Add Client", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      String name = nameField.getText();
      String address = addressField.getText();
      String phone = phoneField.getText();
      String email = emailField.getText();
      try {
        connection.addClient(name, address, phone, email);
        outputArea.setText("Client added successfully.");
      } catch (SQLException ex) {
        outputArea.setText("Error adding client: " + ex.getMessage());
      }
    }
  }

  private void deleteClient(int clientId) {
    try {
      connection.deleteClient(clientId);
      outputArea.setText("Client deleted successfully.");
    } catch (SQLException ex) {
      outputArea.setText("Error deleting client: " + ex.getMessage());
    }
  }

  private void updateClient(int clientId) {
    JTextField nameField = new JTextField();
    JTextField addressField = new JTextField();
    JTextField phoneField = new JTextField();
    JTextField emailField = new JTextField();

    Object[] fields = {"Name:", nameField, "Address:", addressField, "Phone:", phoneField, "Email:", emailField};

    try {
      Client client = connection.getClient(clientId);
      if (client != null) {
        nameField.setText(client.getClient_name());
        addressField.setText(client.getClient_address());
        phoneField.setText(client.getClient_phone());
        emailField.setText(client.getClient_email());

        int result = JOptionPane.showConfirmDialog(null, fields, "Update Client", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          String name = nameField.getText();
          String address = addressField.getText();
          String phone = phoneField.getText();
          String email = emailField.getText();
          connection.updateClient(clientId, name, address, phone, email);
          outputArea.setText("Client updated successfully.");
        }
      } else {
        outputArea.setText("No client found with the ID " + clientId);
      }
    } catch (SQLException ex) {
      outputArea.setText("Error updating client: " + ex.getMessage());
    }
  }

  private void viewAllClients() {
    try {
      List<Client> clients = connection.getAllClients();
      StringBuilder output = new StringBuilder();
      for (Client client : clients) {
        output.append("Client ID: ").append(client.getClient_id()).append("\n");
        output.append("Name: ").append(client.getClient_name()).append("\n");
        output.append("Address: ").append(client.getClient_address()).append("\n");
        output.append("Phone: ").append(client.getClient_phone()).append("\n");
        output.append("Email: ").append(client.getClient_email()).append("\n\n");
      }
      outputArea.setText(output.toString());
    } catch (SQLException ex) {
      outputArea.setText("Error fetching clients: " + ex.getMessage());
    }
  }

  private void viewCase(int caseId) {
    try {
      Case caseObj = connection.getCase(caseId);
      if (caseObj != null) {
        StringBuilder output = new StringBuilder();
        output.append("Case ID: ").append(caseObj.getCase_id()).append("\n");
        output.append("Case Number: ").append(caseObj.getCase_number()).append("\n");
        output.append("Title: ").append(caseObj.getCase_title()).append("\n");
        output.append("Description: ").append(caseObj.getCase_description()).append("\n");
        output.append("Status: ").append(caseObj.getCase_status()).append("\n");
        output.append("Date Filed: ").append(caseObj.getDate_filed()).append("\n");
        output.append("Date Closed: ").append(caseObj.getDate_closed()).append("\n");

        // Get the associated client
        Client client = caseObj.getClient_id();
        if (client != null) {
          output.append("Client Information:\n");
          output.append("Client ID: ").append(client.getClient_id()).append("\n");
          output.append("Name: ").append(client.getClient_name()).append("\n");
          output.append("Address: ").append(client.getClient_address()).append("\n");
          output.append("Phone: ").append(client.getClient_phone()).append("\n");
          output.append("Email: ").append(client.getClient_email()).append("\n");
        } else {
          output.append("No client information found for this case.\n");
        }
        outputArea.setText(output.toString());
      } else {
        outputArea.setText("Case not found with ID: " + caseId);
      }
    } catch (SQLException ex) {
      outputArea.setText("Error fetching case: " + ex.getMessage());
    }
  }

  private void addCase() {
    JTextField numberField = new JTextField();
    JTextField titleField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField statusField = new JTextField();
    JFormattedTextField dateFiledField = createDateFormattedTextField();
    JFormattedTextField dateClosedField = createDateFormattedTextField();

    JComboBox<String> clientDropdown = new JComboBox<>();
    try {
      List<Client> clients = connection.getAllClients();
      for (Client client : clients) {
        clientDropdown.addItem(client.getClient_name());
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Error fetching clients: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    Object[] fields = {"Case Number:", numberField, "Title:", titleField, "Description:", descriptionField, "Status:", statusField, "Date Filed (YYYY-MM-DD):", dateFiledField, "Date Closed (YYYY-MM-DD):", dateClosedField, "Client:", clientDropdown};

    int result = JOptionPane.showConfirmDialog(null, fields, "Add Case", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      String number = numberField.getText();
      String title = titleField.getText();
      String description = descriptionField.getText();
      String status = statusField.getText();
      String dateFiled = dateFiledField.getText();
      String dateClosed = dateClosedField.getText();
      int clientId = clientDropdown.getSelectedIndex() + 1; // Index starts from 0

      try {
        connection.addCase(number, title, description, status, dateFiled, dateClosed, clientId);
        outputArea.setText("Case added successfully.");
      } catch (SQLException ex) {
        outputArea.setText("Error adding case: " + ex.getMessage());
      }
    }
  }

  private void deleteCase(int caseId) {
    try {
      connection.deleteCase(caseId);
      outputArea.setText("Case deleted successfully.");
    } catch (SQLException ex) {
      outputArea.setText("Error deleting case: " + ex.getMessage());
    }
  }

  private void updateCase(int caseId) {
    JTextField numberField = new JTextField();
    JTextField titleField = new JTextField();
    JTextField descriptionField = new JTextField();
    JTextField statusField = new JTextField();
    JFormattedTextField dateFiledField = createDateFormattedTextField();
    JFormattedTextField dateClosedField = createDateFormattedTextField();

    JComboBox<String> clientDropdown = new JComboBox<>();
    try {
      List<Client> clients = connection.getAllClients();
      for (Client client : clients) {
        clientDropdown.addItem(client.getClient_name());
      }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, "Error fetching clients: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    Object[] fields = {"Case Number:", numberField, "Title:", titleField, "Description:", descriptionField, "Status:", statusField, "Date Filed (YYYY-MM-DD):", dateFiledField, "Date Closed (YYYY-MM-DD):", dateClosedField, "Client:", clientDropdown};

    try {
      Case existingCase = connection.getCase(caseId);
      if (existingCase != null) {
        numberField.setText(existingCase.getCase_number());
        titleField.setText(existingCase.getCase_title());
        descriptionField.setText(existingCase.getCase_description());
        statusField.setText(existingCase.getCase_status());
        dateFiledField.setText(existingCase.getDate_filed().toString());
        dateClosedField.setText(existingCase.getDate_closed().toString());

        int result = JOptionPane.showConfirmDialog(null, fields, "Update Case", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
          String number = numberField.getText();
          String title = titleField.getText();
          String description = descriptionField.getText();
          String status = statusField.getText();
          Date dateFiled = Date.valueOf(dateFiledField.getText());
          Date dateClosed = Date.valueOf(dateFiledField.getText());

          int clientId = clientDropdown.getSelectedIndex() + 1; // Index starts from 0

          connection.updateCase(caseId, number, title, description, status, dateFiled, dateClosed);
          outputArea.setText("Case updated successfully.");
        }
      } else {
        outputArea.setText("No case found with the ID " + caseId);
      }
    } catch (SQLException ex) {
      outputArea.setText("Error updating case: " + ex.getMessage());
    }
  }

  private void viewAllCases() {
    try {
      List<Case> cases = connection.getAllCases();
      StringBuilder output = new StringBuilder();
      for (Case caseObj : cases) {
        output.append("Case ID: ").append(caseObj.getCase_id()).append("\n");
        output.append("Case Number: ").append(caseObj.getCase_number()).append("\n");
        output.append("Title: ").append(caseObj.getCase_title()).append("\n");
        output.append("Description: ").append(caseObj.getCase_description()).append("\n");
        output.append("Status: ").append(caseObj.getCase_status()).append("\n");
        output.append("Date Filed: ").append(caseObj.getDate_filed()).append("\n");
        output.append("Date Closed: ").append(caseObj.getDate_closed()).append("\n");

        // Get the associated client
        Client client = caseObj.getClient_id();
        if (client != null) {
          output.append("Client Information:\n");
          output.append("Client ID: ").append(client.getClient_id()).append("\n");
          output.append("Name: ").append(client.getClient_name()).append("\n");
          output.append("Address: ").append(client.getClient_address()).append("\n");
          output.append("Phone: ").append(client.getClient_phone()).append("\n");
          output.append("Email: ").append(client.getClient_email()).append("\n");
        } else {
          output.append("No client information found for this case.\n");
        }
        output.append("\n");
      }
      outputArea.setText(output.toString());
    } catch (SQLException ex) {
      outputArea.setText("Error fetching cases: " + ex.getMessage());
    }
  }

  //date
  private void askForDateId(String action) {
    String dateIdStr = JOptionPane.showInputDialog("Enter Date ID:");
    if (dateIdStr != null) {
      int dateId = Integer.parseInt(dateIdStr);
      if (action.equals("View")) {
        viewDate(dateId);
      } else if (action.equals("Update")) {
        updateDate(dateId);
      } else if (action.equals("Delete")) {
        deleteDate(dateId);
      }
    }
  }

  private void viewDate(int dateId) {
    try {
      Important_Dates date = connection.viewDate(dateId);
      if (date != null) {
        String output = "Date ID: " + date.getDate_id() + "\n" +
                "Event Date: " + date.getEvent_date() + "\n" +
                "Event Description: " + date.getEvent_description() + "\n";
        outputArea.setText(output);
      } else {
        outputArea.setText("Date not found with ID: " + dateId);
      }
    } catch (SQLException e) {
      outputArea.setText("Error viewing date: " + e.getMessage());
    }
  }

  private void addDate() {
    JTextField caseIdField = new JTextField();
    JTextField eventDateField = new JTextField();
    JTextField eventDescriptionField = new JTextField();

    Object[] fields = {"Case ID:", caseIdField, "Event Date:", eventDateField, "Event Description:", eventDescriptionField};

    int result = JOptionPane.showConfirmDialog(null, fields, "Add Date", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      int caseId = Integer.parseInt(caseIdField.getText());
      Date eventDate = Date.valueOf(eventDateField.getText());
      String eventDescription = eventDescriptionField.getText();

      try {
        connection.addDate(caseId, eventDate, eventDescription);
        outputArea.setText("Date added successfully.");
      } catch (SQLException e) {
        outputArea.setText("Error adding date: " + e.getMessage());
      }
    }
  }

  private void updateDate(int dateId) {
    JTextField newEventDateField = new JTextField();
    JTextField newEventCaseField = new JTextField();
    JTextField newEventDescriptionField = new JTextField();

    Object[] fields = {"New Event Date:", newEventDateField, "New Case Id:", newEventCaseField, "New Event Description:", newEventDescriptionField};

    int result = JOptionPane.showConfirmDialog(null, fields, "Update Date", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      Date newEventDate = Date.valueOf(newEventDateField.getText());
      int newCaseId = Integer.parseInt(newEventCaseField.getText());
      String newEventDescription = newEventDescriptionField.getText();

      try {
        connection.updateDate(dateId, newCaseId, newEventDate, newEventDescription);
        outputArea.setText("Date updated successfully.");
      } catch (SQLException e) {
        outputArea.setText("Error updating date: " + e.getMessage());
      }
    }
  }

  private void deleteDate(int dateId) {
    try {
      connection.deleteDate(dateId);
      outputArea.setText("Date deleted successfully.");
    } catch (SQLException e) {
      outputArea.setText("Error deleting date: " + e.getMessage());
    }
  }

  private void viewAllDates() {
    try {
      List<Important_Dates> dateList = connection.viewAllDate();
      StringBuilder output = new StringBuilder("List of Dates:\n");
      for (Important_Dates date : dateList) {
        output.append("Date ID: ").append(date.getDate_id()).append("\n");
        output.append("Event Date: ").append(date.getEvent_date()).append("\n");
        output.append("Event Description: ").append(date.getEvent_description()).append("\n\n");
      }
      outputArea.setText(output.toString());
    } catch (SQLException e) {
      outputArea.setText("Error viewing all dates: " + e.getMessage());
    }
  }

  private void askForDocumentId(String action) {
    String documentIdStr = JOptionPane.showInputDialog("Enter Document ID:");
    if (documentIdStr != null) {
      int documentId = Integer.parseInt(documentIdStr);
      if (action.equals("View")) {
        viewDocument(documentId);
      } else if (action.equals("Update")) {
        updateDocument(documentId);
      } else if (action.equals("Delete")) {
        deleteDocument(documentId);
      }
    }
  }

  private void viewDocument(int documentId) {
    try {
      Documents document = connection.viewDocument(documentId);
      if (document != null) {
        String output = "Document ID: " + document.getDocument_id() + "\n" +
                "Document Name: " + document.getDocument_name() + "\n" +
                "Document Type: " + document.getDocument_type() + "\n" +
                "Document Path: " + document.getDocument_path() + "\n";
        outputArea.setText(output);
      } else {
        outputArea.setText("No document found with ID: " + documentId);
      }
    } catch (SQLException e) {
      outputArea.setText("Error viewing document: " + e.getMessage());
    }
  }

  private void addDocument() {
    JTextField caseIdField = new JTextField();
    JTextField documentNameField = new JTextField();
    JTextField documentTypeField = new JTextField();
    JTextField documentPathField = new JTextField();

    Object[] fields = {"Case ID:", caseIdField, "Document Name:", documentNameField, "Document Type:", documentTypeField, "Document Path:", documentPathField};

    int result = JOptionPane.showConfirmDialog(null, fields, "Add Document", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      int caseId = Integer.parseInt(caseIdField.getText());
      String documentName = documentNameField.getText();
      String documentType = documentTypeField.getText();
      String documentPath = documentPathField.getText();

      try {
        connection.addDocument(caseId, documentName, documentType, documentPath);
        outputArea.setText("Document added successfully.");
      } catch (SQLException e) {
        outputArea.setText("Error adding document: " + e.getMessage());
      }
    }
  }

  private void updateDocument(int documentId) {
    JTextField newNameField = new JTextField();
    JTextField newTypeField = new JTextField();
    JTextField newPathField = new JTextField();
    JTextField newCaseField = new JTextField();

    Object[] fields = {"New Document Name:", newNameField, "New Document Type:", newTypeField, "New Document Path:", newPathField, "New Case Id:", newCaseField};

    int result = JOptionPane.showConfirmDialog(null, fields, "Update Document", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      String newName = newNameField.getText();
      String newType = newTypeField.getText();
      String newPath = newPathField.getText();
      int newCaseId = Integer.parseInt(newCaseField.getText());

      try {
        connection.updateDocument(documentId, newCaseId, newName, newType, newPath);
        outputArea.setText("Document updated successfully.");
      } catch (SQLException e) {
        outputArea.setText("Error updating document: " + e.getMessage());
      }
    }
  }

  private void deleteDocument(int documentId) {
    try {
      connection.deleteDocument(documentId);
      outputArea.setText("Document deleted successfully.");
    } catch (SQLException e) {
      outputArea.setText("Error deleting document: " + e.getMessage());
    }
  }

  private void viewAllDocuments() {
    try {
      List<Documents> documents = connection.viewAllDocuments();
      StringBuilder output = new StringBuilder("List of Documents:\n");
      for (Documents document : documents) {
        output.append("Document ID: ").append(document.getDocument_id()).append("\n");
        output.append("Document Name: ").append(document.getDocument_name()).append("\n");
        output.append("Document Type: ").append(document.getDocument_type()).append("\n");
        output.append("Document Path: ").append(document.getDocument_path()).append("\n\n");
      }
      outputArea.setText(output.toString());
    } catch (SQLException e) {
      outputArea.setText("Error viewing all documents: " + e.getMessage());
    }
  }

  private JFormattedTextField createDateFormattedTextField() {
    JFormattedTextField textField = new JFormattedTextField(createFormatter("####-##-##"));
    textField.setColumns(10);
    return textField;
  }

  private MaskFormatter createFormatter(String format) {
    MaskFormatter formatter = null;
    try {
      formatter = new MaskFormatter(format);
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return formatter;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        LawFirmAppGUI app = new LawFirmAppGUI();
        app.setVisible(true);
      }
    });
  }
}
