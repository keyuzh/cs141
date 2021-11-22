public class mainClass {

    static UserThread users[];
    static Disk disks[];
    static Printer printers[];
    static DiskManager diskm;
    static PrinterManager pm;

    public static void main(String[] args) {
        int numOfUsers = Integer.parseInt(args[0].substring(1));
        String[] userInputs = new String[numOfUsers];
        for (int i = 0; i < numOfUsers; i++) {
            userInputs[i] = args[1+i];
        }

        int numOfDisks = Integer.parseInt(args[numOfUsers+1].substring(1));
        int numOfPrinters = Integer.parseInt(args[numOfUsers+2].substring(1));

        System.out.println(String.format("numOfUsers: %d, numOfDisks: %d, numOfPrinters: %d", numOfUsers, numOfDisks, numOfPrinters));
        for (String string : userInputs) {
            System.out.println(string);
        }

        users = new UserThread[numOfUsers];
        for (int i = 0; i < numOfUsers; i++) {
            users[i] = new UserThread(i+1);
            System.out.println("b");
        }

        disks = new Disk[numOfDisks];
        for (int i = 0; i < numOfDisks; i++) {
            disks[i] = new Disk();
        }

        printers = new Printer[numOfPrinters];
        for (int i = 0; i < numOfPrinters; i++) {
            printers[i] = new Printer(i+1);
        }

        diskm = new DiskManager(numOfDisks);
        pm = new PrinterManager(numOfPrinters);

        for (int i = 0; i < numOfUsers; i++) {
            users[i].start();
        }
    }
}
