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

