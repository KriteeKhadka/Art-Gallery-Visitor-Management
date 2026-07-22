import java.io.*;
//creating child class that extends ArtGalleryVisitor
public class StandardVisitor extends ArtGalleryVisitor  {   
    //creating additional private attributes
    private boolean isEligibleForDiscountUpgrade;
    private final int visitLimit;
    private float discountPercent;

    //creating constructor for StandardVisitor accepting seven parameters
    public StandardVisitor(int visitorId, String fullName, String gender, String contactNumber, 
    String registrationDate, double ticketCost, String ticketType) {
        //calling immediate superclass constructor                     
        super(visitorId, fullName, gender, contactNumber, registrationDate, ticketCost, ticketType);
        //initializing the value of attributes
        this.visitLimit = 5;
        this.discountPercent = 0.1f;
        this.isEligibleForDiscountUpgrade = false;
    }

    //accessor(getter) method for each attribute
    public boolean getIsEligibleForDiscountUpgrade() {
        return isEligibleForDiscountUpgrade; 
    }

    public int getVisitLimit() {
        return visitLimit;
    }

    public float getDiscountPercent() {
        return discountPercent; 
    }

    /*creating a method to evaluate whether the visitor is eligible for 
    discount upgrade or not*/
    public boolean checkDiscountUpgrade() {
        // If visit count reaches or exceeds limit, upgrade discount
        if (visitCount >= visitLimit) {
            isEligibleForDiscountUpgrade = true;
            discountPercent = 0.15f;    //upgradded discount
        }
        // Return current eligibility status
        return isEligibleForDiscountUpgrade;
    }

    // Overriding the buyProduct method
    @Override
    public String buyProduct(String artworkName, double artworkPrice) {
        //checking if the user is active
        if (!isActive) {   //!isActive ---> isActive==false       
            return "Please log in before making a purchase!!";   // Visitor must log in before purchasing  
        } else if (!isBought || (this.artworkName == null || !this.artworkName.equals(artworkName))) {
            // Buying a new product
            this.artworkName = artworkName;
            this.artworkPrice = artworkPrice;
            this.isBought = true;   //setting isBrought to true
            this.buyCount++;        // incrementing buyCount by 1
            return "Purchase successful";
        } else {
            return "This artwork has already been purchased";  // Same artwork already bought
        }
    }

    /*overriding calculateDiscount() method to calculate discount based on 
    on the value returned by checkDiscountUpgrade() method.*/
    @Override
    public double calculateDiscount() {
        // If artwork is not bought, return 0 as no discount is applied
        if (!isBought) {
            /*If product has not been bought 
            then no discount should be applied*/
            discountAmount = 0.0f;
            return discountAmount;
        } else {
            // Check and update discount percent if eligible
            checkDiscountUpgrade();
            //calculating discount amount and final price
            discountAmount = artworkPrice * discountPercent;
            finalPrice = artworkPrice - discountAmount;
            return discountAmount;
        }
    }

    // Overriding the calculateRewardPoint method
    @Override
    public double calculateRewardPoint() {
        //checks if the product had not been bought
        if (!isBought) {
            //If not bought ,no reward points should be given
            return 0;
        } else {
            //If bought, calculate and add reward points
            //For every 1 unit of final price, 5 reward points are given
            rewardPoints = finalPrice * 5;
            //Return the updated reward points
            return rewardPoints;
        }
    }

    // Private method to deactivate the visitor and reset (attributes) relevant data
    private void terminateVisitor() {
        isActive = false;
        isEligibleForDiscountUpgrade = false;
        visitCount = 0;
        cancelCount = 0;
        rewardPoints = 0;
    }

    // Overriding the cancelProduct method
    @Override
    public String cancelProduct(String artworkName, String cancellationReason) {
        // If cancellation limit exceeded, deactivate account
        if (cancelCount >= cancelLimit) {
            terminateVisitor();
            return "Account session expired because of exceeding cancellation limit.";
        }

        // If a product has been bought and artwork matches
        if (buyCount > 0 && this.artworkName != null && this.artworkName.equals(artworkName)) {
            this.artworkName = null;
            isBought = false;
            refundableAmount = artworkPrice - (artworkPrice * 0.10);
            rewardPoints -= finalPrice * 5;
            cancelCount++;
            buyCount--;
            this.cancellationReason = cancellationReason;

            // Return confirmation with refund info
            return "Product cancelled. Refund: Rs. " + refundableAmount;
        } else if (buyCount == 0) {
            return "No product to cancel.";
        } else {
            return "Artwork name doesn't match.";
        }
    }

    // Overriding generateBill method
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

            System.out.println(bill); // print to console

            // Write bill to file
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("StandardVisitorBill_" + visitorId + ".txt"))) {
                writer.write(bill);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bill; // Return the bill string
        }
    }

    // Overriding the display method to show all visitor details
    @Override
    public String display() {
        return super.display() + "," + isEligibleForDiscountUpgrade + "," + visitLimit + "," + discountPercent;
    }

    // Main method to test StandardVisitor
    public static void main(String[] args) {
        // Create StandardVisitor object
        StandardVisitor visitor = new StandardVisitor(
                100,
                "Kritee Khadka",
                "Female",
                "9834000000",
                "2006-04-05",
                1000,
                "Standard"
            );

        // Log a visit
        visitor.logVisit();

        // Buy artwork
        String purchaseMsg = visitor.buyProduct("Starry Night", 18000);
        System.out.println(purchaseMsg);

        // Calculate discount
        double discount = visitor.calculateDiscount();
        System.out.println("Discount applied: Rs. " + discount);

        // Calculate reward points
        double points = visitor.calculateRewardPoint();
        System.out.println("Reward points earned: " + (int) points);

        // Display visitor details
        System.out.println(visitor.display());

        // Generate bill and save to file
        visitor.generateBill();

        // Cancel product
        String cancelMsg = visitor.cancelProduct("Starry Night", "Changed mind");
        System.out.println(cancelMsg);

        // Display again after cancellation
        System.out.println(visitor.display());
    }

}
