import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

public class SliceoHeaven {

    public String storeName;
    public String storeAddress;
    public String storeEmail;
    public String storePhone;
    public String storeMenu;

    private String pizzaIngredients;
    private double pizzaPrice;
    private String sides;
    private String drinks;
    private String orderID;
    private double orderTotal;
    private String pizzaSize;
    private String extraCheese;

    public static final String DEF_ORDER_ID = "DEF-SOH-099";
    public static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    public static final double DEF_ORDER_TOTAL = 15.00;
    private static final long BLACKLISTED_NUMBER = 12345678901234L;

    public SliceoHeaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sides = "";
        this.drinks = "";
        this.pizzaSize = "";
        this.extraCheese = "";
    }

    public SliceoHeaven(String orderID, String pizzaIngredients, double orderTotal) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
        this.sides = "";
        this.drinks = "";
        this.pizzaSize = "";
        this.extraCheese = "";
    }

    public String getPizzaIngredients() {
        return pizzaIngredients;
    }

    public void setPizzaIngredients(String pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public String getSides() {
        return sides;
    }

    public void setSides(String sides) {
        this.sides = sides;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(String extraCheese) {
        this.extraCheese = extraCheese;
    }

    private void printReceipt() {
        System.out.println("********RECEIPT********");
        System.out.println("Order ID: " + orderID);
        System.out.println("Pizza Ingredients: " + pizzaIngredients);
        System.out.println("Pizza Size: " + pizzaSize);
        System.out.println("Extra Cheese: " + extraCheese);
        System.out.println("Order Total: " + orderTotal);
        System.out.println("Sides: " + sides);
        System.out.println("Drinks: " + drinks);
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter three ingredients for your pizza (use spaces to separate ingredients):");
        String[] ingredients = scanner.nextLine().split(" ");
        setPizzaIngredients(ingredients[0] + ", " + ingredients[1] + ", " + ingredients[2]);

        System.out.println("Enter size of pizza (Small, Medium, Large):");
        setPizzaSize(scanner.nextLine());

        System.out.println("Do you want extra cheese (Y/N):");
        setExtraCheese(scanner.nextLine());

        System.out.println("Enter one side dish (Calzone, Garlic bread, None):");
        setSides(scanner.nextLine());

        System.out.println("Enter drinks(Cold Coffee, Cocoa drink, Coke, None):");
        setDrinks(scanner.nextLine());

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.nextLine();

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
    }

    private void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your birthday in the format yyyy-MM-dd:");
        String birthdateStr = scanner.nextLine();
        LocalDate birthdate = LocalDate.parse(birthdateStr);

        LocalDate today = LocalDate.now();
        int age = Period.between(birthdate, today).getYears();

        if (age < 18 && birthdate.getDayOfMonth() == today.getDayOfMonth() && birthdate.getMonth() == today.getMonth()) {
            System.out.println("Congratulations! You pay only half the price for your order");
            setOrderTotal(getOrderTotal() / 2);
        } else {
            System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
        }
        makeCardPayment();
    }

    private void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your card number:");
        long cardNumber = scanner.nextLong();

        System.out.println("Please enter the card's expiry date (in the format yyyy-MM):");
        String expiryDate = scanner.next();

        System.out.println("Please enter the card's CVV number:");
        int cvv = scanner.nextInt();

        processCardPayment(cardNumber, expiryDate, cvv);
        printReceipt();
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);

        if (cardNumberStr.length() == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
        }

        char firstCardDigit = cardNumberStr.charAt(0);

        if (cardNumber == BLACKLISTED_NUMBER) {
            System.out.println("Card is blacklisted. Please use another card");
        }

        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);

        String cardNumberToDisplay = firstCardDigit + cardNumberStr.substring(1, cardNumberStr.length() - 4).replaceAll("\\d", "*") + lastFourDigits;

        System.out.println("First card digit: " + firstCardDigit);
        System.out.println("Last four digits: " + lastFourDigits);
        System.out.println("Card number to display: " + cardNumberToDisplay);
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, String specialPrice) {
        StringBuilder specialInfo = new StringBuilder();
        specialInfo.append("Pizza of the day: ").append(pizzaOfTheDay).append("\n");
        specialInfo.append("Side of the day: ").append(sideOfTheDay).append("\n");
        specialInfo.append("Special price: ").append(specialPrice);
        System.out.println(specialInfo.toString());
    }

    public void makePizza() {
        System.out.println("Making pizza with ingredients: " + getPizzaIngredients());
    }

    public static void main(String[] args) {
        SliceoHeaven sliceoHeaven = new SliceoHeaven();
        sliceoHeaven.takeOrder();
    }
}