import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

// creating a child class (EliteVisitor class) that  extends ArtGalleryVisitor
class EliteVisitor extends ArtGalleryVisitor{
    // Unique attributes for EliteVisitor
    private boolean assignedPersonalArtAdvisor;
    private boolean exclusiveEventAccess;

    // Constructor that accepts seven parameters
    public EliteVisitor(int visitorId, String fullName, String gender, String contactNumber,
    String registrationDate, double ticketCost, String ticketType) {
        // Calling the parent class constructor
        super(visitorId, fullName, gender, contactNumber, registrationDate, ticketCost, ticketType);
        // Setting default values
        this.assignedPersonalArtAdvisor = false;
        this.exclusiveEventAccess = false;
    }

    //Getter methods for each attribute
    public boolean getAssignedPersonalArtAdvisor(){
        return assignedPersonalArtAdvisor;
    }

    public boolean getExclusiveEventAccess(){
        return exclusiveEventAccess; 
    }

    // Method to assign a personal art advisor if reward points are more than 5000
    public boolean assignPersonalArtAdvisor() {
        if (rewardPoints > 5000) {
            assignedPersonalArtAdvisor = true;
        }
        return assignedPersonalArtAdvisor;
    }

    // Method to grant exclusive event access if the personal art advisor is assigned
    public boolean exclusiveEventAccess() {
        if (assignedPersonalArtAdvisor) {
            exclusiveEventAccess = true;
        }
        return exclusiveEventAccess;
    }
    // Overriding the buyProduct method
    @Override
    public String buyProduct(String artworkName, double artworkPrice) {
        // Check if the visitor is active
        if (isActive == false) {
            return "Please log in before making a purchase.";
        } 
        // If product hasn't been bought before
        else if (isBought == false || (this.artworkName == null || !this.artworkName.equals(artworkName))) {
            this.artworkName = artworkName;
            this.artworkPrice = artworkPrice;
            this.isBought = true;
            this.buyCount++;
            return "Purchase successful.";
        } 
        else {
            return "This artwork has already been purchased.";
        }
    }

    //Overriding the calculateDiscount method
    @Override
    public double calculateDiscount() {
        if (isBought == true) {    
            // Elite visitors get 40% discount
            discountAmount = artworkPrice * 0.40;
            finalPrice = artworkPrice - discountAmount;
            return discountAmount;
        } else { // If no product is bought, no discount
            return 0;
        }
    }

    //Overriding the calculateRewardPoint method
    @Override
    public double calculateRewardPoint() {
        if (isBought == true) {
            // For every 1 unit, 10 reward points
            rewardPoints = finalPrice * 10;
            return rewardPoints;
        } else {          // If not bought, no reward points
            return 0;
        }
    }

    // Private method to terminate the visitor account
    private void terminateVisitor() {
        isActive = false;
        assignedPersonalArtAdvisor = false;
        exclusiveEventAccess = false;
        visitCount = 0;
        cancelCount = 0;
        rewardPoints = 0;
    }

    //Overriding the cancelProduct method
    @Override
    public String cancelProduct(String artworkName, String cancellationReason) {
        if (cancelCount >= cancelLimit) {      // If cancellation limit reached
            terminateVisitor();
            return "Account session expired due to exceeding cancellation limit.";
        }
        // If the product matches and has been bought
        if (buyCount > 0 && this.artworkName != null && this.artworkName.equals(artworkName)) {
            this.artworkName = null;
            isBought = false;
            refundableAmount = artworkPrice - (artworkPrice * 0.05);
            rewardPoints -= finalPrice * 10;
            cancelCount++;
            buyCount--;
            this.cancellationReason = cancellationReason;
            return "Product cancelled. Refund: " + refundableAmount;
        } else if (buyCount == 0) {
            return "No product to cancel.";
        } else {
            return "Artwork name doesn't match.";
        }
    }

    @Override
    public String generateBill() {
        if (!isBought) {
            return "No purchase made to generate bill.";
        } else {
            String bill = "\n------Your Bill------\n"
                + "Visitor ID      : " + visitorId + "\n"
                + "Visitor Name    : " + fullName + "\n"
                + "Artwork Name    : " + artworkName + "\n"
                + "Artwork Price   : Rs. " + artworkPrice + "\n"
                + "Discount Amount : Rs. " + discountAmount + "\n"
                + "Final Price     : Rs. " + finalPrice + "\n";

            System.out.println(bill); // Optional: print to console

            // Write bill to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("EliteVisitorBill_" + visitorId + ".txt"))) {
                writer.write(bill);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bill; // Return the bill string as well
        }
    }

    //Overriding the display method to show all the details
    @Override
    public String display() {
        return super.display() + "," + assignedPersonalArtAdvisor + "," + exclusiveEventAccess;
    }

    // Main method to test EliteVisitor
    public static void main(String[] args) {
        // Create EliteVisitor object
        EliteVisitor visitor = new EliteVisitor(
                201,
                "Kritee Khadka",
                "Female",
                "9812121212",
                "2006-05-26",
                2000,
                "Elite"
            );

        // Log visit
        visitor.logVisit();

        // Buy artwork
        String purchaseMsg = visitor.buyProduct("The Sunset Vibes", 3000);
        System.out.println(purchaseMsg);

        // Calculate discount
        double discount = visitor.calculateDiscount();
        System.out.println("Discount applied: Rs. " + discount);
        // Calculate reward points
        double points = visitor.calculateRewardPoint();
        System.out.println("Reward points earned: " + points);
        // Try assigning personal advisor
        boolean advisorAssigned = visitor.assignPersonalArtAdvisor();
        System.out.println("Personal Art Advisor Assigned: " + advisorAssigned);
        // Grant exclusive event access
        boolean eventAccess = visitor.exclusiveEventAccess();
        System.out.println("Exclusive Event Access: " + eventAccess);
        // Display all details
        visitor.display();
        // Generate bill
        visitor.generateBill();
        // Cancel product
        String cancelMsg = visitor.cancelProduct("The Scream", "Not satisfied");
        System.out.println(cancelMsg);
        // Display again after cancellation
        visitor.display();
    }
}

