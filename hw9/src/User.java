import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UserThread
    extends Thread
{
    Scanner sc;
    StringBuffer sb;
    String filepath;
    int userID;

    UserThread(int id)
    {
        // this.filepath = String.format("USER%d", id);
        this.userID = id;
        this.filepath = String.format("inputs/USER%d", id);
        this.sb = new StringBuffer();
    }
    
    void processCommand() {
        String[] ln = this.sb.toString().split(" ");
        switch (ln[0]) {
            case ".save":
                saveFile(ln[1]);
                break;
            case ".print":
                // System.out.println("print");
                printFile(ln[1]);
                break;
            default:
                break;
        }
    }

    void saveFile(String filename) {
        // request next free disk

        if (mainClass.showGUI) {
            mainClass.gui.changeButtonStatus("user", this.userID, 
                "requesting a disk", java.awt.Color.red);
        }
        int diskNumber = mainClass.diskm.request();
        int offset = mainClass.diskm.getNextFreeSector(diskNumber);
        int fileLines = 0;

        while (true) {
            String line = this.sc.nextLine();

            this.sb.delete(0, this.sb.length());
            this.sb.append(line);
            if (this.sb.toString().equals(".end")) {
                // System.out.println("ENDDDx");
                this.sb.delete(0, this.sb.length());
                this.sb.append(filename);
                mainClass.diskm.dirm.enter(this.sb, new FileInfo(diskNumber, offset, fileLines));
                break;
            }
            else {
                if (mainClass.showGUI) {
                    mainClass.gui.changeButtonStatus("user", this.userID, 
                        "writing to disk" + (diskNumber+1) + "<br/>Line: " + this.sb.toString(), java.awt.Color.yellow);
                }
                mainClass.disks[diskNumber].write(offset + fileLines, this.sb);
                fileLines++;
            }
        }
        mainClass.diskm.setNextFreeSector(diskNumber, offset+fileLines);
        mainClass.diskm.release(diskNumber);
        if (mainClass.showGUI) {
            mainClass.gui.changeButtonStatus("user", this.userID, 
                "idle", java.awt.Color.green);
        }
        // System.out.print("save file");

        // for (int i = 0; i < 3; i++) {
        //     mainClass.disks[i].printDisks();
        // }
    }

    void printFile(String filename) {
        PrintJobThread pjt = new PrintJobThread(filename);
        pjt.start();
    }

    public void run() {
        File f = new File(this.filepath);
        try {
            this.sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (this.sc.hasNextLine()) {
            String line = this.sc.nextLine();
            // System.out.println(line);
            this.sb.delete(0, this.sb.length());
            this.sb.append(line);
            processCommand();
        }
        sc.close();

        if (mainClass.showGUI) {
            mainClass.gui.changeButtonStatus("user", this.userID, 
                "finished", java.awt.Color.green);
        }
    }
}
