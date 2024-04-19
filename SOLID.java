// 1. Single Responsibility principle - A class should have 1 reason to change

class Marker{
    String name;
    String color;
    int year;
    int price;

    public Marker(String name, String color, int year, int price) {
        this.name = name;
        this.color = color;
        this.year = year;
        this.price = price;
    }
}

// Does not follow the principle - There are 3 reasons to change in this case - if logic of any of the 3 methods - calculateTotal, printInvoice, saveToDB changes
class Invoice {
    private Marker marker;
    private int quantity;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculateTotal(){
        int price = ((marker.price) * this.quantity);
        return price;
    }
    public void printInvoice(){
        //print the Invoice
    }
    public void saveToDB(){
        // Save the data into DB
    }
}

// Follows - Made classes such that each follows single logic
class Invoice {
    private Marker marker;
    private int quantity;

    public Invoice(Marker marker, int quantity) {
        this.marker = marker;
        this.quantity = quantity;
    }
    public int calculateTotal(){
        int price = ((marker.price) * this.quantity);
        return price;
    }
}

class InvoiceDao {
    Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB(){
        // Save the data into DB
    }
}

class InvoicePrinter {
    private Invoice invoice;
    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }
    public void printInvoice(){
        //print the Invoice
    }
}

// 2. Open/Closed Principle - Open for extension but closed for Modification

// If there is a request to add new functionality for saving to File - then need to add new method in class
class InvoiceDao {
    Invoice invoice;

    public InvoiceDao(Invoice invoice) {
        this.invoice = invoice;
    }

    public void saveToDB(){
        // Save the data into DB
    }
    public void saveToFile(String fileName){
        // Save invoice in the File with the given name
    }
}

// Solution -> Use interface to allow it for extension but not able to modidy
interface InvoiceDao{
    public void save(Invoice invoice);
}

class SQLDatabaseInvoiceDao implements InvoiceDao{
    @Override
    public void save(){
        // Save the data into SQL DB
    }
}
class FileInvoiceDao implements InvoiceDao{
    @Override
    public void save(){
        // Save to File
    }
}

class NoSQLDatabaseInvoiceDao implements InvoiceDao{
    @Override
    public void save(){
        // Save the data into No-SQL DB
    }
}

// 3. Liskov Substitution Principle - If Class B is subtype of Class A, then we should be able to replace object of A with B without breaking the behaviour of the program
//                                    Subclass should extend the capability of parent class not narrow it down

interface Bike {
    void turnOnEngine();
    void accelerate();
｝
    
class MotorCycle implements Bike {
    boolean isEngineOn; 
    int speed;
    
    public void turnOnEngine() {
        //turn on the engine!
        isEngineOn = true;
    }
    public void accelerate() {
        //increase the speed
        speed = speed + 10;
    }
}
    
class Bicycle implements Bike {
    public void turnOnEngine() {
        throw new AssertionError ("there is no engine");
    ｝
    public void accelerate () {
        //do something
    ｝
}

// 4. Interface Segmented Principle - Interfaces should be such, that client should implement unnecessary functions they do not need
        
interface RestaurantEmployee {
    void washDishes (); 
    void serveCustomers();
    void cookFood ();
}
        
class waiter implements RestaurantEmployee {
    public void washDishes(){
        //not my job
    }
    public void serveCustomers() {
        //yes and here is my implemenation
        System.out.println("serving the customer");
    }
    public void cookFood(){
        // not my job
    }
}

// Solution - 
        
interface WaiterInterface {
    void serveCustomers (); 
    void takeOrder ();
｝
    
interface ChefInterface {
    void cookFood(); 
    void decideMenu(); 
}

class waiter implements WaiterInterface{
    public void serveCustomers() {
        System.out.println("serving the customer");
    }
    public void takeorder (){
        System.out.println("taking orders");
    }
}

// 5. Dependency Inversion Principle - Class should depend on interfaces rather than concrete classes

//Problem - If there were wirelessKeyboard or wirelessMouse we won't be able to incorporate in it 
class MacBook {
    private final WiredKeyboard keyboard; 
    private final WiredMouse mouse;
    
    public MacBook() {
        keyboard = new WiredKeyboard ();
        mouse = new WiredMouse ();
    }
｝

//Solution - Use interface keyboard and mouse and make a class that implements the interface
class MacBook {
    private final Keyboard keyboard;
    private final Mouse mouse;
    
    public MacBook(Keyboard keyboard, Mouse mouse) {
        this. keyboard = keyboard;
        this.mouse = mouse; 
    }
｝
