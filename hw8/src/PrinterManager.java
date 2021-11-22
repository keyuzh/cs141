public class PrinterManager 
    extends ResourceManager {

    Printer printers[];

    PrinterManager(int numberOfItems) {
        super(numberOfItems);
        printers = new Printer[numberOfItems];
    }
    
}
