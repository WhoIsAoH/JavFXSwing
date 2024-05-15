package cps4005;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

public class LawFirmAppGUI extends JFrame {

    private DatabaseConnection connection;
    private JTextArea outputArea;

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

        // Create buttons for case actions
        JButton viewCaseButton = new JButton("View Case");
        JButton addCaseButton = new JButton("Add Case");
        JButton deleteCaseButton = new JButton("Delete Case");
        JButton viewAllCasesButton = new JButton("View All Cases");

        // Create panels
        JPanel clientPanel = new JPanel();
        clientPanel.add(viewClientButton);
        clientPanel.add(addClientButton);
        clientPanel.add(deleteClientButton);
        clientPanel.add(viewAllClientsButton);

        JPanel casePanel = new JPanel();
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
                askForClientId("View");
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
                askForClientId("Delete");
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
                askForCaseId("View");
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
                askForCaseId("Delete");
            }
        });

        viewAllCasesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllCases();
            }
        });
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
            }
        }
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
            outputArea.setText("Error fetching clients: " + ex.getMessage());
        }
    }

    private void viewCase(int caseId) {
        try {
            Case caseObj = connection.getCase(caseId);
            if (caseObj != null) {
                outputArea.setText("Case ID: " + caseObj.getCase_id() + "\n" +
                        "Case Number: " + caseObj.getCase_number() + "\n" +
                        "Title: " + caseObj.getCase_title() + "\n" +
                        "Description: " + caseObj.getCase_description() + "\n" +
                        "Status: " + caseObj.getCase_status() + "\n" +
                        "Date Filed: " + caseObj.getDate_filed() + "\n" +
                        "Date Closed: " + caseObj.getDate_closed() + "\n");
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

        Object[] fields = {
                "Case Number:", numberField,
                "Title:", titleField,
                "Description:", descriptionField,
                "Status:", statusField,
                "Date Filed (YYYY-MM-DD):", dateFiledField,
                "Date Closed (YYYY-MM-DD):", dateClosedField,
                "Client:", clientDropdown
        };

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
                output.append("Date Closed: ").append(caseObj.getDate_closed()).append("\n\n");
            }
            outputArea.setText(output.toString());
        } catch (SQLException ex) {
            outputArea.setText("Error fetching cases: " + ex.getMessage());
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
