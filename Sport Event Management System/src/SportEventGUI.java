
import java.awt.*;
import javax.swing.*;

public class SportEventGUI extends JFrame {

    // Event objects
    private final Event cricket;
    private final Event badminton;
    private final Event kabaddi;
    private final Event cycling;

    // UI Components
    private JTextField nameField;
    private JComboBox<String> genderBox;
    private JTextField scoreField;
    private JComboBox<String> eventBox;
    private JTextArea outputArea;

    public SportEventGUI() {
        // Initialize events
        cricket = new Event("Cricket", "Male");
        badminton = new Event("Badminton", "Female");
        kabaddi = new Event("Kabaddi", "Both");
        cycling = new Event("Cycling", "Both");

        // Set up the frame
        setTitle("Sport Event Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        createInputPanel();
        createOutputPanel();
        createButtonPanel();

        // Make the frame visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Participant Information"));

        // Add components
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Gender:"));
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        inputPanel.add(genderBox);

        inputPanel.add(new JLabel("Score:"));
        scoreField = new JTextField();
        inputPanel.add(scoreField);

        inputPanel.add(new JLabel("Event:"));
        eventBox = new JComboBox<>(new String[]{"Cricket", "Badminton", "Kabaddi", "Cycling"});
        inputPanel.add(eventBox);

        // Add panel to frame
        add(inputPanel, BorderLayout.NORTH);
    }

    private void createOutputPanel() {
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Event Details"));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        outputPanel.add(scrollPane, BorderLayout.CENTER);

        add(outputPanel, BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Participant");
        addButton.addActionListener(e -> addParticipant());

        JButton viewButton = new JButton("View Event Details");
        viewButton.addActionListener(e -> viewEventDetails());

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            nameField.setText("");
            scoreField.setText("");
            outputArea.setText("");
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addParticipant() {
        try {
            // Get input values
            String name = nameField.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            int score = Integer.parseInt(scoreField.getText().trim());
            String eventName = (String) eventBox.getSelectedItem();

            // Validate input
            if (name.isEmpty()) {
                showMessage("Please enter a name");
                return;
            }

            // Create participant
            Participant participant = new Participant(name, gender, score);

            // Add to selected event
            switch (eventName) {
                case "Cricket" ->
                    cricket.addParticipant(participant);
                case "Badminton" ->
                    badminton.addParticipant(participant);
                case "Kabaddi" ->
                    kabaddi.addParticipant(participant);
                case "Cycling" ->
                    cycling.addParticipant(participant);
            }

            outputArea.append("Added " + name + " to " + eventName + "\n");

            // Clear fields
            nameField.setText("");
            scoreField.setText("");

        } catch (NumberFormatException ex) {
            showMessage("Please enter a valid score (numeric value)");
        }
    }

    private void viewEventDetails() {
        String eventName = (String) eventBox.getSelectedItem();
        outputArea.setText(""); // Clear previous output

        switch (eventName) {
            case "Cricket" ->
                displayEventInfo(cricket);
            case "Badminton" ->
                displayEventInfo(badminton);
            case "Kabaddi" ->
                displayEventInfo(kabaddi);
            case "Cycling" ->
                displayEventInfo(cycling);
        }
    }

    private void displayEventInfo(Event event) {
        outputArea.append("=== " + event.eventName + " Event Details ===\n");
        outputArea.append("Gender Constraint: " + event.genderConstraint + "\n");
        outputArea.append("Total Participants: " + event.participantCount + "\n");

        // Calculate gender counts
        int maleCount = 0;
        int femaleCount = 0;

        for (int i = 0; i < event.participantCount; i++) {
            if (event.participants[i].gender.equals("Male")) {
                maleCount++;
            } else {
                femaleCount++;
            }
        }

        outputArea.append("Male Participants: " + maleCount + "\n");
        outputArea.append("Female Participants: " + femaleCount + "\n");
        outputArea.append("Average Score: " + event.calculateAverageScore() + "\n\n");

        // List participants
        outputArea.append("Participants:\n");
        for (int i = 0; i < event.participantCount; i++) {
            Participant p = event.participants[i];
            outputArea.append((i + 1) + ". " + p.name + " (" + p.gender + ") - Score: " + p.score + "\n");
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Create GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new SportEventGUI());
    }
}
