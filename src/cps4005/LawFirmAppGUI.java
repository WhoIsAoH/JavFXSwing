package cps4005;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

public class LawFirmAppGUI extends JFrame {

    private DatabaseConnection connection;
    private JTextField clientIdField;
    private JTextField caseIdField;
    private JTextArea outputArea;

    public LawFirmAppGUI() {
        super("Law Firm App");
        connection = new DatabaseConnection();

        // Set up main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create input fields
        clientIdField = new JTextField(10);
        caseIdField = new JTextField(10);

        // Create output area
        outputArea = new JTextArea(20, 60);
        outputArea.setEditable(false);

        // Create buttons for client actions
        JButton viewClientButton = new JButton("View Client");
        JButton addClientButton = new JButton("Add Client");
        JButton deleteClientButton = new JButton("Delete Client");
        JButton viewAllClientsButton = new JButton("View All Clients");

        // Create buttons for case actions
        JButton viewCaseButton = new JButton("View Case");
        JButton addCaseButton = new JButton("Add Case");
        JButton deleteCaseButton = new JButton("Delete Case");
        JButton viewAllCasesButton = new JButton("View All Cases");

        // Create panels
        JPanel clientPanel = new JPanel();
        clientPanel.add(new JLabel("Client ID:"));
        clientPanel.add(clientIdField);
        clientPanel.add(viewClientButton);
        clientPanel.add(addClientButton);
        clientPanel.add(deleteClientButton);
        clientPanel.add(viewAllClientsButton);

        JPanel casePanel = new JPanel();
        casePanel.add(new JLabel("Case ID:"));
        casePanel.add(caseIdField);
        casePanel.add(viewCaseButton);
        casePanel.add(addCaseButton);
        casePanel.add(deleteCaseButton);
        casePanel.add(viewAllCasesButton);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add panels to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(clientPanel, BorderLayout.NORTH);
        getContentPane().add(casePanel, BorderLayout.CENTER);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        // Add action listeners to client buttons
        viewClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int clientId = askForClientIdIfNeeded();
                if (clientId != -1) {
                    viewClient(clientId);
                }
            }
        });

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClient();
            }
        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int clientId = askForClientIdIfNeeded();
                if (clientId != -1) {
                    deleteClient(clientId);
                }
            }
        });

        viewAllClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllClients();
            }
        });

        // Add action listeners to case buttons
        viewCaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int caseId = askForCaseIdIfNeeded();
                if (caseId != -1) {
                    viewCase(caseId);
                }
            }
        });

        addCaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCase();
            }
        });

        deleteCaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int caseId = askForCaseIdIfNeeded();
                if (caseId != -1) {
                    deleteCase(caseId);
                }
            }
        });

        viewAllCasesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllCases();
            }
        });
    }

    private int askForClientIdIfNeeded() {
        String clientIdString = clientIdField.getText();
        if (clientIdString != null && !clientIdString.isEmpty()) {
            try {
                return Integer.parseInt(clientIdString);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Client ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return -1;
    }

    private int askForCaseIdIfNeeded() {
        String caseIdString = caseIdField.getText();
        if (caseIdString != null && !caseIdString.isEmpty()) {
            try {
                return Integer.parseInt(caseIdString);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Case ID", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return -1;
    }

    private void viewClient(int clientId) {
        try {
            Client client = connection.getClient(clientId);
            if (client != null) {
                outputArea.setText("Client ID: " + client.getClient_id() + "\n" +
                        "Name: " + client.getClient_name() + "\n" +
                        "Address: " + client.getClient_address() + "\n" +
                        "Phone: " + client.getClient_phone() + "\n" +
                        "Email: " + client.getClient_email() + "\n");
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

    Object[] fields = {
            "Name:", nameField,
            "Address:", addressField,
            "Phone:", phoneField,
            "Email:", emailField
    };

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
            outputArea.setText("Error listing clients: " + ex.getMessage());
        }
    }

    private void viewCase(int caseId) {
        try {
            Case caseObject = connection.getCase(caseId);
            if (caseObject != null) {
                outputArea.setText("Case ID: " + caseObject.getCase_id() + "\n" +
                        "Case Number: " + caseObject.getCase_number() + "\n" +
                        "Title: " + caseObject.getCase_title() + "\n" +
                        "Description: " + caseObject.getCase_description() + "\n" +
                        "Status: " + caseObject.getCase_status() + "\n" +
                        "Date Filed: " + caseObject.getDate_filed() + "\n" +
                        "Date Closed: " + caseObject.getDate_closed() + "\n" +
                        "Client ID: " + caseObject.getClient_id() + "\n");
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
    JTextField dateFiledField = new JTextField(); // You can replace this with a date picker for better user experience
    JTextField dateClosedField = new JTextField(); // You can replace this with a date picker for better user experience
    JTextField clientIdField = new JTextField();

    Object[] fields = {
            "Case Number:", numberField,
            "Title:", titleField,
            "Description:", descriptionField,
            "Status:", statusField,
            "Date Filed:", dateFiledField,
            "Date Closed:", dateClosedField,
            "Client ID:", clientIdField
    };

    int result = JOptionPane.showConfirmDialog(null, fields, "Add Case", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        String number = numberField.getText();
        String title = titleField.getText();
        String description = descriptionField.getText();
        String status = statusField.getText();
        String dateFiled = dateFiledField.getText();
        String dateClosed = dateClosedField.getText();
        int clientId = Integer.parseInt(clientIdField.getText());
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

    private void viewAllCases() {
        try {
            List<Case> cases = connection.getAllCases();
            StringBuilder output = new StringBuilder();
            for (Case caseObject : cases) {
                output.append("Case ID: ").append(caseObject.getCase_id()).append("\n");
                output.append("Case Number: ").append(caseObject.getCase_number()).append("\n");
                output.append("Title: ").append(caseObject.getCase_title()).append("\n");
                output.append("Description: ").append(caseObject.getCase_description()).append("\n");
                output.append("Status: ").append(caseObject.getCase_status()).append("\n");
                output.append("Date Filed: ").append(caseObject.getDate_filed()).append("\n");
                output.append("Date Closed: ").append(caseObject.getDate_closed()).append("\n");
                output.append("Client ID: ").append(caseObject.getClient_id()).append("\n\n");
            }
            outputArea.setText(output.toString());
        } catch (SQLException ex) {
            outputArea.setText("Error listing cases: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LawFirmAppGUI().setVisible(true);
            }
        });
    }
}
