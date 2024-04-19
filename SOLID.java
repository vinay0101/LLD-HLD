// Single Responsibility principle - A class should have 1 reason to change

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

// Does not follow - There are 3 reasons to change in this case - if logic of any of the 3 methods - calculateTotal, printInvoice, saveToDB changes
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
