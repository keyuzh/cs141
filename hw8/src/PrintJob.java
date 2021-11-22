import java.io.FilenameFilter;

class PrintJobThread
    extends Thread
{
    StringBuffer line = new StringBuffer(); // only allowed one line to reuse for read from disk and print to printer
    String fileName;

    PrintJobThread(String fn)
    {
        this.fileName = fn;
    }
    
    public void run()
    {
        System.out.println("printing");
        this.line.append(fileName);
        FileInfo f = mainClass.diskm.dirm.lookup(this.line);
        // could there be an error here if f is undefined

        int start = f.startingSector;
        int d = f.diskNumber;
        int p = mainClass.pm.request();
        // for i in 0 to f.fileLength-1:
        for (int i = 0; i < f.fileLength-1; i++) {
            mainClass.disks[d].read(start+i, line);
            // note we can have many file readers, so no disk request required
            mainClass.printers[p].print(line);
        }
        mainClass.pm.release(p);
    }
}
