import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Printer
    extends Thread
{
    // FileWriter writer;
    String filepath;

    Printer(int id)
    {
        this.setDaemon(true);
        this.filepath = String.format("PRINTER%d", id);
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
            System.out.println("writing ");
            FileWriter writer = new FileWriter(this.filepath);
            writer.write(b.toString());
            writer.close();
            Thread.sleep(2750);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
