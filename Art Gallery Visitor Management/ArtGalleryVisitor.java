import java.io.Serializable;
//Creating an abstract parent class
public abstract class ArtGalleryVisitor implements Serializable{
    //Attributes with protected access modifier
    protected int visitorId;
    protected String fullName,gender,contactNumber,registrationDate,ticketType,cancellationReason,artworkName;
    protected double ticketCost,rewardPoints,refundableAmount,finalPrice,discountAmount,artworkPrice;
    protected int visitCount;
    protected final int cancelLimit=3; //setting cancel limit to 3
    protected int cancelCount,buyCount;
    protected boolean isActive, isBought;

    //The constructor accepting parameters
    public ArtGalleryVisitor(int visitorId,String fullName,String gender,String contactNumber,String registrationDate,double ticketCost,String ticketType){
        //assigning the parameter values
        this.visitorId=visitorId;
        this.fullName=fullName;
        this.gender=gender;
        this.contactNumber=contactNumber;
        this.registrationDate=registrationDate;
        this.ticketCost=ticketCost;
        this.ticketType=ticketType;

        //initializing parameter values
        this.visitCount=0;
        this.rewardPoints=0;
        this.cancelCount=0;
        this.buyCount=0;
        this.discountAmount=0;
        this.finalPrice=0;
        this.refundableAmount=0;
        //setting isActive and isBought as false
        this.isActive=false;
        this.isBought=false;
        //seting cancellationReason to null
        this.cancellationReason=" ";   
    }
    // Accessor(getter) methods -----> return type
    // Accessor(getter) methods -----> return type for visitorId
    public int getVisitorId(){
        return visitorId; 
    }
    // Accessor(getter) methods -----> return type for FullName
    public String getFullName(){
        return fullName; 
    }
    // Accessor(getter) methods -----> return type for Gender
    public String getGender(){
        return gender; 
    }
    // Accessor(getter) methods -----> return type for ContactNumber
    public String getContactNumber(){ 
        return contactNumber; 
    }
    // Accessor(getter) methods -----> return type for registratonDate
    public String getRegistrationDate(){
        return registrationDate; 
    }
    // Accessor(getter) methods -----> return type for TicketCost
    public double getTicketCost(){
        return ticketCost; 
    }
    // Accessor(getter) methods -----> return type for TicketType
    public String getTicketType(){
        return ticketType; 
    }
    // Accessor(getter) methods -----> return type for VisitCount
    public int getVisitCount(){
        return visitCount; 
    }
    // Accessor(getter) methods -----> return type for RewardPoints
    public double getRewardPoints(){
        return rewardPoints; 
    }
    // Accessor(getter) methods -----> return type for CancelLimit
    public int getCancelLimit(){
        return cancelLimit; 
    }
    // Accessor(getter) methods -----> return type for CancelCount    
    public int getCancelCount(){
        return cancelCount; 
    }
    // Accessor(getter) methods -----> return type for CancellationReason  
    public String getCancellationReason(){
        return cancellationReason; 
    }
    // Accessor(getter) methods -----> return type for RefundableAmount
    public double getRefundableAmount(){
        return refundableAmount; 
    }
    // Accessor(getter) methods -----> return type for IsActive
    public boolean getIsActive(){
        return isActive; 
    }
    // Accessor(getter) methods -----> return type for IsBought
    public boolean getIsBought(){
        return isBought; 
    }
    // Accessor(getter) methods -----> return type for BuyCount
    public int getBuyCount(){
        return buyCount; 
    }
    // Accessor(getter) methods -----> return type for FinalPrice
    public double getFinalPrice(){
        return finalPrice; 
    }
    // Accessor(getter) methods -----> return type for DiscountAmount
    public double getDiscountAmount(){
        return discountAmount; 
    }
    // Accessor(getter) methods -----> return type for ArtworkName
    public String getArtworkName(){
        return artworkName; 
    }
    // Accessor(getter) methods -----> return type for artworkPrice
    public double getArtworkPrice(){
        return artworkPrice; 
    }

    // Mutator(Setter) methods
    public void setFullName(){
    }

    public void setGender( ){
    }

    public void setContactNumber( ){
    }

    // Method to log visit
    public void logVisit() {
        visitCount++; //number of times the visitor  enters, count increases by 1.

        this.isActive = true; //indicated visitors are  currently active in the gallery
    }

    // Abstract methods
    // Declaring an abstract method named buyProduct() with a return type of String
    public abstract String buyProduct(String artworkName, double artworkPrice);

    // Declaring an abstract method named discount with a return type of double
    public abstract double calculateDiscount();

    public abstract double calculateRewardPoint();

    /*creating cancelProduct() abstract method with 
    string return type that take parameters*/
    public abstract String cancelProduct(String artworkName, String cancellationReason);

    /*Declaring an abstract method named generateBill() 
    with no parameters and a void return type*/
    public abstract String generateBill();

    // Display method - returns values only
    public String display() {
        return visitorId + "," +
        fullName + "," +
        gender + "," +
        contactNumber + "," +
        registrationDate + "," +
        ticketType + "," +
        ticketCost + "," +
        (artworkName != null ? artworkName : "") + "," +
        artworkPrice + "," +
        discountAmount + "," +
        rewardPoints + "," +
        refundableAmount + "," +
        finalPrice + "," +
        (cancellationReason != null ? cancellationReason : "");
    }
}



