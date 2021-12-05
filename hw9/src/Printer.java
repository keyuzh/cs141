import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Printer
    extends Thread
{
    // FileWriter writer;
    String filepath;
    static int speedMultiplier = 2750;
    int printerID;

    Printer(int id)
    {
        this.setDaemon(true);
        this.filepath = String.format("PRINTER%d", id);
        this.printerID = id;
        try {
            File f = new File(this.filepath);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("File already exists");
        }

        // try {
        //     this.writer = new FileWriter(filepath);
        // } catch (IOException e) {
        //     //TODO: handle exception
        //     e.printStackTrace();
        // }
    }

    void print(StringBuffer b)
    {
        try {
            // System.out.println("writing ");
            FileWriter writer = new FileWriter(this.filepath, true);
            if (mainClass.showGUI) {
                speedMultiplier = (int) (2750.0 * Gui.getSpeed());
                mainClass.gui.changeButtonStatus("printer", this.printerID, 
                    "printing<br/>Line: " + b.toString(), java.awt.Color.cyan);
            }
            writer.write(b.toString() + "\n");
            writer.close();
            Thread.sleep(speedMultiplier);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mainClass.showGUI) {
            mainClass.gui.changeButtonStatus("printer", this.printerID, 
                "idle", java.awt.Color.green);
        }
    }
}
