import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Printer
    extends Thread
{
    FileWriter writer;

    Printer(int id)
    {
        String filepath = String.format("PRINTER%d", id);
        try {
            File f = new File(filepath);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("File already exists");
        }

        try {
            this.writer = new FileWriter(filepath);
        } catch (IOException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    void print(StringBuffer b)
    {
        try {
            writer.write(b.toString());
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
