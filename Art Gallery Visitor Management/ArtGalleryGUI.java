// Importing necessary libraries for GUI creation and event handling
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.io.*;
// Main class that handles the GUI logic and user interactions
public class ArtGalleryGUI extends JFrame implements ActionListener {

    // List to store visitor information
    private ArrayList<ArtGalleryVisitor> visitors = new ArrayList<>();

    // Declaring GUI components
    private JFrame frame;
    private JLabel titleLabel, idLabel, nameLabel, contactLabel, ticketPriceLabel,
    artworkNameLabel, artworkPriceLabel, cancellationLabel, genderLabel, dateLabel, typeLabel;
    private JTextField visitorIdField, fullNameField, contactNumberField, ticketPriceField,
    artworkNameField, artworkPriceField, cancelReasonField;

    private JComboBox<String> dayCombo, monthCombo, yearCombo, ticketTypeCombo;
    private JRadioButton maleRadio, femaleRadio, othersRadio;
    private ButtonGroup genderGroup;

    private JButton addVisitorButton, logVisitButton, buyProductButton, assignAdvisorButton, checkUpgradeButton,
    calculateDiscountButton, calculateRewardPointsButton, cancelProductButton, generateBillButton,
    displayVisitorButton, clearFieldsButton, saveToFileButton, readFromFileButton;

    // Constructor to design and display the GUI window
    public ArtGalleryGUI() {
        // Creating the main window
        frame = new JFrame("Art Gallery Visitor Management");
        frame.setSize(1200, 700);                          // Wide and tall window
        frame.setLayout(null);                              // Manual positioning
        frame.getContentPane().setBackground(Color.ORANGE); // Background color

        // Setting up the title
        titleLabel = new JLabel("Art Gallery Visitor Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(400, 10, 400, 30);           // Centered title
        frame.add(titleLabel);

        // Label for Visitor ID
        idLabel = new JLabel("Visitor ID:");
        idLabel.setBounds(50, 70, 150, 30);
        frame.add(idLabel);

        //TextField for VisitorID
        visitorIdField = new JTextField();
        visitorIdField.setBounds(220, 70, 400, 30);
        frame.add(visitorIdField);

        //Label for Full Name
        nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(50, 120, 150, 30);
        frame.add(nameLabel);

        //TextField for FullName
        fullNameField = new JTextField();
        fullNameField.setBounds(220, 120, 400, 30);
        frame.add(fullNameField);

        // Label for Contact Number
        contactLabel = new JLabel("Contact No:");
        contactLabel.setBounds(50, 170, 150, 30);
        frame.add(contactLabel);

        //Textfield for contactNumber
        contactNumberField = new JTextField();
        contactNumberField.setBounds(220, 170, 400, 30);
        frame.add(contactNumberField);

        //Label for Gender Selection using radio buttons
        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 220, 150, 30);
        frame.add(genderLabel);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(220, 220, 100, 30);

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(330, 220, 100, 30);

        othersRadio = new JRadioButton("Others");
        othersRadio.setBounds(440, 220, 100, 30);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(othersRadio);

        frame.add(maleRadio);
        frame.add(femaleRadio);
        frame.add(othersRadio);

        // Label for Registration Date using combo box
        dateLabel = new JLabel("Registration Date:");
        dateLabel.setBounds(50, 270, 150, 30);
        frame.add(dateLabel);

        dayCombo = new JComboBox<>();
        for (int i = 1; i <= 31; i++) dayCombo.addItem(String.valueOf(i));
        dayCombo.setBounds(220, 270, 60, 30);
        frame.add(dayCombo);

        monthCombo = new JComboBox<>();
        for (int i = 1; i <= 12; i++) monthCombo.addItem(String.valueOf(i));
        monthCombo.setBounds(290, 270, 60, 30);
        frame.add(monthCombo);

        yearCombo = new JComboBox<>();
        for (int i = 1980; i <= 2026; i++) yearCombo.addItem(String.valueOf(i));
        yearCombo.setBounds(360, 270, 100, 30);
        frame.add(yearCombo);

        // Label for Ticket Type and Price 
        typeLabel = new JLabel("Ticket Type:");
        typeLabel.setBounds(50, 320, 150, 30);
        frame.add(typeLabel);

        ticketTypeCombo = new JComboBox<>(new String[]{"Standard", "Elite"});
        ticketTypeCombo.setBounds(220, 320, 400, 30);
        frame.add(ticketTypeCombo);

        ticketPriceLabel = new JLabel("Ticket Price:");
        ticketPriceLabel.setBounds(50, 370, 150, 30);
        frame.add(ticketPriceLabel);

        ticketPriceField = new JTextField("1000"); // default Standard price
        ticketPriceField.setBounds(220, 370, 400, 30);
        ticketPriceField.setEditable(false);
        frame.add(ticketPriceField);

        // Automatically update ticket price based on selection
        ticketTypeCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (ticketTypeCombo.getSelectedItem().equals("Standard")) {
                        ticketPriceField.setText("1000");
                    } else {
                        ticketPriceField.setText("2000");
                    }
                }
            });

        // Label for Artwork Name 
        artworkNameLabel = new JLabel("Artwork Name:");
        artworkNameLabel.setBounds(50, 420, 150, 30);
        frame.add(artworkNameLabel);

        //Textfield for Artwork name
        artworkNameField = new JTextField();
        artworkNameField.setBounds(220, 420, 400, 30);
        frame.add(artworkNameField);

        // Label for Artwork Price 
        artworkPriceLabel = new JLabel("Artwork Price:");
        artworkPriceLabel.setBounds(50, 470, 150, 30);
        frame.add(artworkPriceLabel);

        //Textfield for Artwork Price
        artworkPriceField = new JTextField();
        artworkPriceField.setBounds(220, 470, 400, 30);
        frame.add(artworkPriceField);

        // Label  for Cancellation Reason
        cancellationLabel = new JLabel("Cancellation Reason:");
        cancellationLabel.setBounds(50, 520, 180, 30);
        frame.add(cancellationLabel);

        //TextField for cancellation reason
        cancelReasonField = new JTextField();
        cancelReasonField.setBounds(220, 520, 400, 30);
        frame.add(cancelReasonField);

        // Add Visitor Button
        addVisitorButton = new JButton("Add Visitor");
        addVisitorButton.setBounds(50, 560, 120, 30);
        addVisitorButton.setBackground(Color.YELLOW);
        addVisitorButton.addActionListener(this);
        frame.add(addVisitorButton);

        // Log Visit Button
        logVisitButton = new JButton("Log Visit");
        logVisitButton.setBounds(180, 560, 120, 30);
        logVisitButton.setBackground(Color.YELLOW);
        logVisitButton.addActionListener(this);
        frame.add(logVisitButton);

        //Buy product Button
        buyProductButton = new JButton("Buy Product");
        buyProductButton.setBounds(310, 560, 130, 30);
        buyProductButton.setBackground(Color.YELLOW);
        buyProductButton.addActionListener(this);
        frame.add(buyProductButton);

        // Generate Bill Button
        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(450, 560, 130, 30);
        generateBillButton.setBackground(Color.YELLOW);
        generateBillButton.addActionListener(this);
        frame.add(generateBillButton);

        // Check Upgrade Button
        checkUpgradeButton = new JButton("Check Upgrade");
        checkUpgradeButton.setBounds(590, 560, 130, 30);
        checkUpgradeButton.setBackground(Color.YELLOW);
        checkUpgradeButton.addActionListener(this);
        frame.add(checkUpgradeButton);

        // Assign Advisor Button
        assignAdvisorButton = new JButton("Assign Advisor");
        assignAdvisorButton.setBounds(730, 560, 130, 30);
        assignAdvisorButton.setBackground(Color.YELLOW);
        assignAdvisorButton.addActionListener(this);
        frame.add(assignAdvisorButton);

        // Calculate Discount Button
        calculateDiscountButton = new JButton("Calculate Discount");
        calculateDiscountButton.setBounds(870, 560, 150, 30);
        calculateDiscountButton.setBackground(Color.YELLOW);
        calculateDiscountButton.addActionListener(this);
        frame.add(calculateDiscountButton);

        // Cancel Product Button
        cancelProductButton = new JButton("Cancel Product");
        cancelProductButton.setBounds(50, 600, 130, 30);
        cancelProductButton.setBackground(Color.YELLOW);
        cancelProductButton.addActionListener(this);
        frame.add(cancelProductButton);

        // Display Visitor Button
        displayVisitorButton = new JButton("Display Visitor");
        displayVisitorButton.setBounds(190, 600, 130, 30);
        displayVisitorButton.setBackground(Color.YELLOW);
        displayVisitorButton.addActionListener(this);
        frame.add(displayVisitorButton);

        // Save To File Button
        saveToFileButton = new JButton("Save To File");
        saveToFileButton.setBounds(330, 600, 140, 30);
        saveToFileButton.setBackground(Color.YELLOW);
        saveToFileButton.addActionListener(this);
        frame.add(saveToFileButton);

        // Clear Fields Button
        clearFieldsButton = new JButton("Clear Fields");
        clearFieldsButton.setBounds(480, 600, 140, 30);
        clearFieldsButton.setBackground(Color.YELLOW);
        clearFieldsButton.addActionListener(this);
        frame.add(clearFieldsButton);

        // Read From File Button
        readFromFileButton = new JButton("Read From File");
        readFromFileButton.setBounds(630, 600, 160, 30);
        readFromFileButton.setBackground(Color.YELLOW);
        readFromFileButton.addActionListener(this);
        frame.add(readFromFileButton);

        // Calculate Reward Points Button
        calculateRewardPointsButton = new JButton("Calculate Reward Points");
        calculateRewardPointsButton.setBounds(800, 600, 180, 30);
        calculateRewardPointsButton.setBackground(Color.YELLOW);
        calculateRewardPointsButton.addActionListener(this);
        frame.add(calculateRewardPointsButton);

        //  Displaying the GUI
        frame.setVisible(true);
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("VisitorsINFO.dat"))) {
            while (true) {
                try {
                    ArtGalleryVisitor v = (ArtGalleryVisitor) oi.readObject();
                    visitors.add(v);
                } catch (EOFException eof) {
                    // End of file reached
                    break;
                }
            }
        } catch (FileNotFoundException fnf) {
            JOptionPane.showMessageDialog(null, "No saved visitor data found.");
        } catch (IOException ioEx) {
            JOptionPane.showMessageDialog(null, "IO Error: " + ioEx.getMessage());
        } catch (ClassNotFoundException cnfEx) {
            JOptionPane.showMessageDialog(null, "Class not found: " + cnfEx.getMessage());
        }
    }

    //a method to check empty fields
    public boolean CheckingForEmptyFields(){
        // Check for empty required fields
        if (visitorIdField.getText().trim().isEmpty()) {//check for empty visitor id
            JOptionPane.showMessageDialog(frame, "Error! Please Enter Visitor ID.","Error", JOptionPane.ERROR_MESSAGE);
            return true; // Stop further execution
        }

        if( fullNameField.getText().trim().isEmpty()){//check for empty full name
            JOptionPane.showMessageDialog(frame, "Error! Please Enter Full Name.","Error", JOptionPane.ERROR_MESSAGE);
            return true; // Stop further execution
        }

        if(contactNumberField.getText().trim().isEmpty()){//check for empty contact number
            JOptionPane.showMessageDialog(frame, "Error! Please Enter Contact Number.","Error", JOptionPane.ERROR_MESSAGE);
            return true; // Stop further execution
        }

        if(ticketPriceField.getText().trim().isEmpty()){//check for empty ticket price
            JOptionPane.showMessageDialog(frame, "Error! Please Enter Ticket Price or select Ticket Type.","Error", JOptionPane.ERROR_MESSAGE);
            return true; // Stop further execution
        }

        //check for empty gender
        if (!maleRadio.isSelected() && 
        !femaleRadio.isSelected() && 
        !othersRadio.isSelected()) {

            JOptionPane.showMessageDialog(frame, "Error! Please Select a gender.","Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        //check for empty combo boxes
        if (dayCombo.getSelectedItem() == null ||//checking for empty day 
        monthCombo.getSelectedItem() == null ||//checking for empty month
        yearCombo.getSelectedItem() == null ||//checking for empty year
        ticketTypeCombo.getSelectedItem() == null)//checking for empty ticket type
        {

            JOptionPane.showMessageDialog(frame, "Error! Please Select Date and Ticket Type.","Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        return false;// no empty fields
    }

    //event handling for buttons
    @Override
    public void actionPerformed(ActionEvent e ){
        //when a ticket type is selected then price should automatically be added accordingly
        if(e.getSource()==ticketTypeCombo){
            String ticket=(String)ticketTypeCombo.getSelectedItem();//getting selected item in combobox
            switch(ticket){
                case "Standard"://ticket Type
                    ticketPriceField.setText(String.valueOf(1000));
                    break;

                case "Elite":
                    ticketPriceField.setText(String.valueOf(2000));
                    break;

                default:
                    ticketPriceField.setText("Price");
            }
        }

        //Button 1: adding visitor 
        //when add visitor button is clicked
        if (e.getSource() == addVisitorButton) {

            //cheking for empty fields before adding visitors details
            if (CheckingForEmptyFields()) {
                return; // stop if any field is empty
            }

            try {
                int visitorId = Integer.parseInt(visitorIdField.getText().trim());

                String fullName = fullNameField.getText().trim();
                String gender = maleRadio.isSelected() ? "Male"
                    : femaleRadio.isSelected() ? "Female"
                    : "Others";
                String contactNumber = contactNumberField.getText().trim();
                String day = (String)  dayCombo.getSelectedItem();
                String month = (String) monthCombo .getSelectedItem();
                String year = (String) yearCombo.getSelectedItem();
                String registrationDate = day + "/" + month + "/" + year;
                String ticketType = (String) ticketTypeCombo.getSelectedItem();
                double ticketPrice=Double.valueOf(ticketPriceField.getText());

                // Check for duplicate ID
                for (ArtGalleryVisitor i : visitors) {
                    if (i.getVisitorId() == visitorId) {
                        JOptionPane.showMessageDialog(frame, "Error! Visitor ID already exists. Please enter a unique ID.","Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (visitorId < 0) {
                    JOptionPane.showMessageDialog(frame, "Error! ID must be Positive.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!fullName.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(frame, "Error! Name must be alphabets only!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (contactNumber.length() != 10) {
                    JOptionPane.showMessageDialog(frame, "Error! Contact number must be exactly 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(contactNumber.length() <=0){
                    JOptionPane.showMessageDialog(frame, "Error! Contact number must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    Long.parseLong(contactNumber);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error! Contact number must contain only digits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create visitor object
                ArtGalleryVisitor visitor;
                if(ticketType.equals("Select")){
                    JOptionPane.showMessageDialog(frame, "Please Select Ticket Type","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(ticketType.equals("Standard")) {
                    visitor = new StandardVisitor(visitorId, fullName, gender, contactNumber, registrationDate,ticketPrice, ticketType);
                } else {
                    visitor = new EliteVisitor(visitorId, fullName, gender, contactNumber, registrationDate, ticketPrice, ticketType);
                }

                // Add to visitor list
                visitors.add(visitor);
                JOptionPane.showMessageDialog(frame, "Visitor added successfully.");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error! Please enter valid numeric values for Visitor ID.","Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // button 2: log visit button
        if (e.getSource() == logVisitButton) {
            // ask Visitor ID using JOptionPane input dialog
            String id = JOptionPane.showInputDialog(frame, "Enter Visitor ID:");

            if (id != null) { // user didn't press Cancel
                try {
                    int visitorId = Integer.parseInt(id);

                    // creating visitor object
                    ArtGalleryVisitor visitor;

                    if (visitorId < 0) {
                        JOptionPane.showMessageDialog(frame, "Error! ID must be Positive.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // checking for duplicate id
                    boolean found = false;
                    for (ArtGalleryVisitor i : visitors) {
                        if (i.getVisitorId() == visitorId) {
                            i.logVisit(); // method from ArtGalleryVisitor class
                            JOptionPane.showMessageDialog(frame, 
                                "Visit logged successfully for Visitor ID: " + visitorId);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog(frame, 
                            "Visitor ID not found in the list!", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, 
                        "Invalid ID! Please enter a number.", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        // Button 3: Buy Product
        if (e.getSource() == buyProductButton) {
            try {
                // Asking for Visitor ID
                String id = JOptionPane.showInputDialog(frame, "Enter Visitor ID:");
                if (id == null) return; // Stop execution here if user pressed Cancel

                int visitorId = Integer.parseInt(id.trim());
                if (visitorId < 0) {
                    JOptionPane.showMessageDialog(frame, "Error! ID must be Positive.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Asking for Artwork Name
                String artworkName = JOptionPane.showInputDialog(frame, "Enter Artwork Name:");
                if (artworkName == null || artworkName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Artwork name cannot be empty.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                artworkName = artworkName.trim();

                // Ask for Artwork Price
                String price = JOptionPane.showInputDialog(frame, "Enter Artwork Price:");
                if (price == null) return; // user pressed Cancel
                double artworkPrice = Double.parseDouble(price.trim());

                if (artworkPrice <= 0) {
                    JOptionPane.showMessageDialog(frame, "Artwork price must be a positive value.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean artworkAlreadyPurchased = false;

                // Check if artwork is already purchased
                for (ArtGalleryVisitor i : visitors) {

                    if (i.getArtworkName() != null 
                    && i.getArtworkName().equalsIgnoreCase(artworkName)) {

                        if (i.getVisitorId() == visitorId){
                            // Same visitor already bought this artwork
                            JOptionPane.showMessageDialog(frame, 
                                "You have already purchased this artwork.", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            // Another visitor already bought it
                            artworkAlreadyPurchased = true;
                            break;
                        }
                    }
                }

                if (artworkAlreadyPurchased) {
                    JOptionPane.showMessageDialog(frame, 
                        "This artwork has already been purchased by another visitor.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean found = false;

                // Find the visitor and allow purchase
                for (ArtGalleryVisitor visitor : visitors) {
                    if (visitor.getVisitorId() == visitorId) {
                        String buy = visitor.buyProduct(artworkName, artworkPrice);
                        JOptionPane.showMessageDialog(frame, buy);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame, 
                        "Error! Visitor ID not found.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, 
                    "Please enter valid numeric values for Visitor ID and Artwork Price.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Button 4: Assign Personal Art Advisor
        if (e.getSource() == assignAdvisorButton) {
            // Ask for Visitor ID using JOptionPane
            String id = JOptionPane.showInputDialog(frame, "Enter Visitor ID:");
            if (id == null) return; // Stop if user pressed Cancel

            try {
                int visitorId = Integer.parseInt(id.trim());
                boolean found = false;

                for (ArtGalleryVisitor visitor : visitors) {
                    if (visitor.getVisitorId() == visitorId) {
                        found = true;

                        // Check if the visitor is an instance of EliteVisitor
                        if (visitor instanceof EliteVisitor) {
                            EliteVisitor elite = (EliteVisitor) visitor;
                            boolean assigned = elite.assignPersonalArtAdvisor();

                            if(assigned){
                                JOptionPane.showMessageDialog(frame, "Personal Art Advisor assigned successfully!");
                                return;
                            }
                            else{
                                JOptionPane.showMessageDialog(frame,"Not enough reward points to assign an advisor. You need over 5000.");
                            }

                        } else {
                            JOptionPane.showMessageDialog(frame, "Only Elite Visitors can have a Personal Art Advisor.");
                        }
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame, "Visitor ID not found.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid numeric Visitor ID.");
            }
        }

        // Button 5: Check for Upgrade
        if (e.getSource() == checkUpgradeButton) {
            // Ask for Visitor ID using JOptionPane
            String id = JOptionPane.showInputDialog(frame, "Enter Visitor ID:");
            if (id == null) return; // Stop if user pressed Cancel

            try {
                int visitorId = Integer.parseInt(id.trim());
                boolean found = false;

                for (ArtGalleryVisitor visitor : visitors) {
                    if (visitor.getVisitorId() == visitorId) {
                        found = true;

                        // Check if the visitor is a StandardVisitor
                        if (visitor instanceof StandardVisitor) {
                            StandardVisitor standard = (StandardVisitor) visitor;

                            // Call the method to check discount upgrade
                            boolean upgraded = standard.checkDiscountUpgrade();

                            String message = upgraded
                                ? "Congrats! You are eligible for discount upgrade."
                                : "Sorry! You are not yet eligible for discount upgrade.";
                            JOptionPane.showMessageDialog(frame, message);

                        } else {
                            // Visitor is not StandardVisitor
                            JOptionPane.showMessageDialog(frame, "Sorry! This option is for Standard Visitors only.");
                        }
                        break; // Exit loop once found
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame, "Error! Visitor ID not found.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid numeric Visitor ID.");
            }
        }

        // Button 6: Calculate Discount
        if(e.getSource() == calculateDiscountButton){
            int visitorId = Integer.valueOf(visitorIdField.getText());
            boolean found = false;

            for (ArtGalleryVisitor visitor : visitors) {
                if (visitor.getVisitorId() == visitorId) {
                    // Calling the calculateDiscount() method
                    double discount = visitor.calculateDiscount();
                    // Displaying with 2 decimal places
                    JOptionPane.showMessageDialog(frame,
                        "Discount Amount = Rs. " + String.format("%.2f", discount));
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Error! Visitor ID not found.");
            }
        }

        // Button 7: Calculate Reward Points
        if(e.getSource() == calculateRewardPointsButton){
            int visitorId = Integer.valueOf(visitorIdField.getText());
            boolean found = false;

            for (ArtGalleryVisitor visitor : visitors) {
                if (visitor.getVisitorId() == visitorId) {
                    // Calling the calculateRewardPoint() method
                    double rewards = visitor.calculateRewardPoint();
                    // Displaying with 2 decimal places
                    JOptionPane.showMessageDialog(frame,
                        "Reward Points = " + String.format("%.2f", rewards));
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Error! Visitor ID not found.");
            }
        }

        // Button 8: Cancel Product
        if (e.getSource() == cancelProductButton) {
            // Ask for Visitor ID
            String id = JOptionPane.showInputDialog(frame, "Enter Visitor ID:");
            if (id == null) return; // Stop if user pressed Cancel

            try {
                int visitorId = Integer.parseInt(id.trim());
                boolean found = false;

                // Ask for Artwork Name
                String artworkName = JOptionPane.showInputDialog(frame, "Enter Artwork Name:");
                if (artworkName == null || artworkName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Artwork name cannot be empty.");
                    return;
                }
                artworkName = artworkName.trim();

                // Ask for Cancellation Reason
                String cancelationReason = JOptionPane.showInputDialog(frame, "Enter Cancellation Reason:");
                if (cancelationReason == null || cancelationReason.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Cancellation reason cannot be empty.");
                    return;
                }
                cancelationReason = cancelationReason.trim();

                // Loop through visitors to find the matching Visitor ID
                for (ArtGalleryVisitor visitor : visitors) {
                    if (visitor.getVisitorId() == visitorId) {
                        String cancelProduct = visitor.cancelProduct(artworkName, cancelationReason);
                        JOptionPane.showMessageDialog(frame, cancelProduct);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame, "Error! Visitor ID not found.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid numeric Visitor ID.");
            }
        }

        // Button: Generate Bill
        if (e.getSource() == generateBillButton) {
            try {
                int visitorId = Integer.parseInt(visitorIdField.getText());
                boolean found = false;

                for (ArtGalleryVisitor v : visitors) {
                    if (v.getVisitorId() == visitorId) {
                        String bill = v.generateBill();  
                        JOptionPane.showMessageDialog(frame, bill); 

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(frame, "Visitor ID not found.");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        }

        /// Button 10: Display Details button
        if (e.getSource() == displayVisitorButton) {

            // Column names for the table
            String[] columnNames = {"Visitor ID", 
                    "Full Name",
                    "Gender",
                    "Contact No.",
                    "Registration date",
                    "Ticket Type",
                    "Ticket Cost",
                    "Artwork Name", 
                    "Artwork Price",
                    "Discount Amount",
                    "Reward Points",
                    "Refund",
                    "Final Price",
                    "Cancellation Reason"};

            // Create table model and table
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for(ArtGalleryVisitor v: visitors){
                // Use display() method from parent class
                String data = v.display(); // returns comma-separated values
                String[] rowData = data.split(","); // split into array
                // Add the visitor data as a row
                model.addRow(rowData);
            }

            JTable table=new JTable(model);
            // Wrap table in scroll pane
            JScrollPane scrollPane = new JScrollPane(table);
            // Show in a dialog
            JDialog dialog = new JDialog();
            dialog.add(scrollPane);
            dialog.setSize(1100,600);
            dialog.setVisible(true);

        }

        //button11: clear field button
        /*When the "Clear Fields" button is pressed, 
         * all input text fields should be cleared. */    
        if(e.getSource() == clearFieldsButton){

            visitorIdField .setText("");
            fullNameField.setText("");
            artworkNameField.setText("");
            artworkPriceField.setText("");
            contactNumberField.setText("");
            ticketPriceField.setText("");
            cancelReasonField.setText("Write a Cancellation Reason:");

            ticketTypeCombo.setSelectedIndex(0);
            dayCombo.setSelectedIndex(0);
            monthCombo.setSelectedIndex(0);
            yearCombo.setSelectedIndex(0);

            maleRadio.setSelected(true); // Reset gender
        }
        
        //Button 12 : save to file button
        if(e.getSource() == saveToFileButton) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("VisitorsINFO.txt"));
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("VisitorsINFO.dat"))) {

                // Header
                String header = String.format("%-10s %-20s %-10s %-15s %-15s %-12s %-12s %-20s %-12s",
                        "VisitorID", "Full Name", "Gender", "Contact No", "Reg Date", 
                        "Ticket Type", "Ticket Cost", "Artwork Name", "Artwork Price");
                writer.write(header);
                writer.newLine();
                writer.write("=".repeat(130));
                writer.newLine();

                // Write data
                for (ArtGalleryVisitor v : visitors) {
                    os.writeObject(v); // save object
                    String row = String.format("%-10d %-20s %-10s %-15s %-15s %-12s %-12.2f %-20s %-12.2f",
                            v.getVisitorId(), v.getFullName(), v.getGender(), v.getContactNumber(),
                            v.getRegistrationDate(), v.getTicketType(), v.getTicketCost(),
                            v.getArtworkName() != null ? v.getArtworkName() : "-",
                            v.getArtworkPrice());
                    writer.write(row);
                    writer.newLine();
                }

                JOptionPane.showMessageDialog(frame, "Data saved successfully!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage());
            }
        }

 
        //Button 13: read from file button
        if(e.getSource() == readFromFileButton) {
            try {
                BufferedReader br = new BufferedReader(new FileReader("VisitorsINFO.txt"));
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                JTextArea textArea = new JTextArea(sb.toString());
                textArea.setEditable(false);
                textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new java.awt.Dimension(1000, 400));
                JOptionPane.showMessageDialog(frame, scrollPane, "Visitors INFO", JOptionPane.PLAIN_MESSAGE);

                // Read objects
                visitors.clear();
                try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream("VisitorsINFO.dat"))) {
                    while (true) {
                        try {
                            ArtGalleryVisitor v = (ArtGalleryVisitor) oi.readObject();
                            visitors.add(v);
                        } catch (EOFException eof) {
                            break;
                        }
                    }
                }

                JOptionPane.showMessageDialog(frame, "Visitor objects loaded successfully!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage());
            }
        }

    }

    // Main method to run the program
    public static void main(String[] args) {
        new ArtGalleryGUI(); // Create and show GUI
    }

}
