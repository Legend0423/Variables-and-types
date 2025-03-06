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

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("********RECEIPT********\n");
        receipt.append("Order ID: ").append(orderID).append("\n");
        receipt.append("Pizza Ingredients: ").append(pizzaIngredients).append("\n");
        receipt.append("Pizza Size: ").append(pizzaSize).append("\n");
        receipt.append("Extra Cheese: ").append(extraCheese).append("\n");
        receipt.append("Order Total: ").append(orderTotal).append("\n");
        receipt.append("Sides: ").append(sides).append("\n");
        receipt.append("Drinks: ").append(drinks).append("\n");
        return receipt.toString();
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);

        String[] ingredients = getValidPizzaIngredients(scanner);
        setPizzaIngredients(ingredients[0] + ", " + ingredients[1] + ", " + ingredients[2]);

 
        String pizzaSize = getValidPizzaSize(scanner);
        setPizzaSize(pizzaSize);

        System.out.println("Do you want extra cheese (Y/N):");
        setExtraCheese(scanner.nextLine());

        String sideDish = getValidSideDish(scanner);
        setSides(sideDish);

        String drink = getValidDrink(scanner);
        setDrinks(drink);

        System.out.println("Would you like the chance to pay only half for your order? (Y/N):");
        String wantDiscount = scanner.nextLine();

        if (wantDiscount.equalsIgnoreCase("Y")) {
            isItYourBirthday();
        } else {
            makeCardPayment();
        }
    }

    private String[] getValidPizzaIngredients(Scanner scanner) {
        String[] ingredients = new String[3];
        boolean validIngredients = false;

        while (!validIngredients) {
            System.out.println("Please pick any three of the following ingredients:\n1. Mushroom\n2. Paprika\n3. Sun-dried tomatoes\n4. Chicken\n5. Pineapple\nEnter any three choices (1, 2, 3,…) separated by spaces:");
            String[] choices = scanner.nextLine().split(" ");
            if (choices.length == 3) {
                try {
                    int ingChoice1 = Integer.parseInt(choices[0]);
                    int ingChoice2 = Integer.parseInt(choices[1]);
                    int ingChoice3 = Integer.parseInt(choices[2]);
                    if (ingChoice1 >= 1 && ingChoice1 <= 5 && ingChoice2 >= 1 && ingChoice2 <= 5 && ingChoice3 >= 1 && ingChoice3 <= 5) {
                        validIngredients = true;
                        ingredients[0] = getIngredientName(ingChoice1);
                        ingredients[1] = getIngredientName(ingChoice2);
                        ingredients[2] = getIngredientName(ingChoice3);
                    } else {
                        System.out.println("Invalid choice(s). Please pick only from the given list:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice(s). Please pick only from the given list:");
                }
            } else {
                System.out.println("Invalid choice(s). Please pick only from the given list:");
            }
        }
        return ingredients;
    }

    private String getIngredientName(int choice) {
        switch (choice) {
            case 1:
                return "Mushroom";
            case 2:
                return "Paprika";
            case 3:
                return "Sun-dried tomatoes";
            case 4:
                return "Chicken";
            case 5:
                return "Pineapple";
            default:
                return "";
        }
    }

    private String getValidPizzaSize(Scanner scanner) {
        int sizeChoice;
        boolean validSize = false;
        String pizzaSize = "";

        while (!validSize) {
            System.out.println("What size should your pizza be? 1. Large\n2. Medium\n3. Small\nEnter only one choice (1, 2, or 3):");
            try {
                sizeChoice = scanner.nextInt();
                scanner.nextLine(); 
                if (sizeChoice >= 1 && sizeChoice <= 3) {
                    validSize = true;
                    switch (sizeChoice) {
                        case 1:
                            pizzaSize = "Large";
                            break;
                        case 2:
                            pizzaSize = "Medium";
                            break;
                        case 3:
                            pizzaSize = "Small";
                            break;
                    }
                } else {
                    System.out.println("Invalid choice. Please pick only from the given list:");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please pick only from the given list:");
                scanner.nextLine();
            }
        }
        return pizzaSize;
    }

    private String getValidSideDish(Scanner scanner) {
        int sideDishChoice;
        boolean validSideDish = false;
        String sideDish = "";

        while (!validSideDish) {
            System.out.println("Following are the side dish that go well with your pizza:\n1. Calzone\n2. Garlic bread\n3. Chicken puff\n4. Muffin\n5. Nothing for me\nWhat would you like? Pick one (1, 2, 3,…):");
            try {
                sideDishChoice = scanner.nextInt();
                scanner.nextLine(); 
                if (sideDishChoice >= 1 && sideDishChoice <= 5) {
                    validSideDish = true;
                    switch (sideDishChoice) {
                        case 1:
                            sideDish = "Calzone";
                            break;
                        case 2:
                            sideDish = "Garlic bread";
                            break;
                        case 3:
                            sideDish = "Chicken puff";
                            break;
                        case 4:
                            sideDish = "Muffin";
                            break;
                        case 5:
                            sideDish = "Nothing for me";
                            break;
                    }
                } else {
                    System.out.println("Invalid choice. Please pick only from the given list:");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please pick only from the given list:");
                scanner.nextLine();
            }
        }
        return sideDish;
    }

    private String getValidDrink(Scanner scanner) {
        int drinkChoice;
        boolean validDrink = false;
        String drink = "";

        while (!validDrink) {
            System.out.println("Choose from one of the drinks below. We recommend Coca Cola:\n1. Coca Cola\n2. Cold coffee\n3. Cocoa Drink\n4. No drinks for me\nEnter your choice:");
            try {
                drinkChoice = scanner.nextInt();
                scanner.nextLine(); 
                if (drinkChoice >= 1 && drinkChoice <= 4) {
                    validDrink = true;
                    switch (drinkChoice) {
                        case 1:
                            drink = "Coca Cola";
                            break;
                        case 2:
                            drink = "Cold coffee";
                            break;
                        case 3:
                            drink = "Cocoa Drink";
                            break;
                        case 4:
                            drink = "No drinks for me";
                            break;
                    }
                } else {
                    System.out.println("Invalid choice. Please pick only from the given list:");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Please pick only from the given list:");
                scanner.nextLine();
            }
        }
        return drink;
    }

    private void isItYourBirthday() {
        Scanner scanner = new Scanner(System.in);
        boolean validDate = false;
        LocalDate birthdate;

        while (!validDate) {
            System.out.println("Please enter your birthday in the format yyyy-MM-dd:");
            String birthdateStr = scanner.nextLine();
            try {
                birthdate = LocalDate.parse(birthdateStr);
                LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
                LocalDate oneTwentyYearsAgo = LocalDate.now().minusYears(120);
                if (birthdate.isAfter(oneTwentyYearsAgo) && birthdate.isBefore(fiveYearsAgo)) {
                    System.out.println("Invalid date. You are either too young or too dead to order.\nPlease enter a valid date:");
                } else {
                    validDate = true;
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
            } catch (Exception e) {
                System.out.println("Invalid date. Please enter a valid date:");
            }
        }
    }

    private void makeCardPayment() {
        Scanner scanner = new Scanner(System.in);
        boolean validExpiry = false;
        String expiryDate;

        while (!validExpiry) {
            System.out.println("Please enter your card number:");
            long cardNumber = scanner.nextLong();
            System.out.println("Please enter the card's expiry date (in the format yyyy-MM):");
            expiryDate = scanner.next();
            try {
                LocalDate currentDate = LocalDate.now();
                LocalDate cardExpiry = LocalDate.parse(expiryDate + "-01");
                if (cardExpiry.isBefore(currentDate)) {
                    System.out.println("Invalid date. Please enter a future date.");
                } else {
                    validExpiry = true;
                    System.out.println("Please enter the card's CVV number:");
                    int cvv = scanner.nextInt();
                    processCardPayment(cardNumber, expiryDate, cvv);
                    System.out.println(toString());
                }
            } catch (Exception e) {
                System.out.println("Invalid date. Please enter a valid date.");
            }
        }
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        boolean validCard = false;

        while (!validCard) {
            if (cardNumberStr.length() == 14 && cardNumber != BLACKLISTED_NUMBER) {
                validCard = true;
                System.out.println("Card accepted");
            } else {
                System.out.println("Invalid card. Please enter a 14 - digit number that is not blacklisted.");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please enter your card number:");
                cardNumber = scanner.nextLong();
                cardNumberStr = Long.toString(cardNumber);
            }
        }

        char firstCardDigit = cardNumberStr.charAt(0);
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