public class OS141 {

    int NUM_USERS=4, NUM_DISKS=2, NUM_PRINTERS=3;
    String userFileNames[];
    UserThread users[];
    Disk disks[];
    Printer printers[];
    DiskManager diskManager;
    PrinterManager printerManager;

    OS141(String argv[]) {
        configure(argv);

        userFileNames = new String[NUM_USERS];
        users = new UserThread[NUM_USERS];
        for (int i = 0; i < NUM_USERS; i++) {
            users[i] = new UserThread(i);
            userFileNames[i] = "USER" + i;
        }
        disks = new Disk[NUM_DISKS];
        for (int i = 0; i < NUM_DISKS; i++) {
            disks[i] = new Disk(i);
        }
        printers = new Printer[NUM_PRINTERS];
        for (int i = 0; i < NUM_PRINTERS; i++) {
            printers[i] = new Printer(i);
        }
        diskManager = new DiskManager();
        printerManager = new PrinterManager();
    }

    void configure(String argv[]) { 
    }

    public static void main(String[] args) {
        OS141 os = new OS141(args);
        for (int i = 0; i < os.NUM_USERS; i++) {
            os.users[i].start();
        }
    }
}
