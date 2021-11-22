import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class UserThread
    extends Thread
{
    Scanner sc;
    StringBuffer sb;
    String filepath;

    UserThread(int id)
    {
        // this.filepath = String.format("USER%d", id);
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
                System.out.println("print");
                printFile(ln[1]);
                break;
            default:
                break;
        }
    }

    void saveFile(String filename) {
        // request next free disk
        int diskNumber = mainClass.diskm.request();
        int offset = mainClass.diskm.getNextFreeSector(diskNumber);
        int fileLines = 0;

        while (true) {
            String line = this.sc.nextLine();

            this.sb.delete(0, this.sb.length());
            this.sb.append(line);
            if (this.sb.toString().equals(".end")) {
                System.out.println("ENDDDx");
                this.sb.delete(0, this.sb.length());
                this.sb.append(filename);
                mainClass.diskm.dirm.enter(this.sb, new FileInfo(diskNumber, offset, fileLines));
                break;
            }
            else {
                mainClass.disks[diskNumber].write(offset + fileLines, this.sb);
                fileLines++;
            }
        }
        mainClass.diskm.setNextFreeSector(diskNumber, offset+fileLines);
        mainClass.diskm.release(diskNumber);
        System.out.print("save file");

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
            System.out.println(line);
            this.sb.delete(0, this.sb.length());
            this.sb.append(line);
            processCommand();
        }
        System.out.println("end");

        sc.close();
    }
}
