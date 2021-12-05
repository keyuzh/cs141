class Disk
    extends Thread
{
    
    static final int NUM_SECTORS = 1024;
    static int speedMultiplier = 200;
    int diskID;

    StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];

    public Disk(int id) {
        this.setDaemon(true);
        // System.out.println("create disk");
        for (int i = 0; i < sectors.length; i++) {
            sectors[i] = new StringBuffer();
        }
        this.diskID = id;
        // System.out.println("x");
    }

    void copyStringBuffer(StringBuffer to, StringBuffer from) {
        to.delete(0, to.length());
        to.append(from);
    }

    void write(int sector, StringBuffer data)
    {
        if (mainClass.showGUI) {
            speedMultiplier = (int) (200.0 * Gui.getSpeed());
            mainClass.gui.changeButtonStatus("disk", this.diskID, 
                "writing sector "+sector+"<br/>Line: " + data.toString(), java.awt.Color.red);
        }
        copyStringBuffer(this.sectors[sector], data);
        try {
            Thread.sleep(speedMultiplier);
        } catch (InterruptedException e) {
            //TODO: handle exception
            // System.out.println("write");
        }
        if (mainClass.showGUI) {
            mainClass.gui.changeButtonStatus("disk", this.diskID, 
                "idle", java.awt.Color.green);
        }
    }

    void read(int sector, StringBuffer data)
    {
        if (mainClass.showGUI) {
            speedMultiplier = (int) (200.0 * Gui.getSpeed());
            mainClass.gui.changeButtonStatus("disk", this.diskID, 
                "reading sector "+sector+"<br/>Line: " + data.toString(), java.awt.Color.yellow);
        }
        copyStringBuffer(data, this.sectors[sector]);
        try {
            Thread.sleep(speedMultiplier);
        } catch (InterruptedException e) {
            //TODO: handle exception
            // System.out.println("read");
        }
        if (mainClass.showGUI) {
            mainClass.gui.changeButtonStatus("disk", this.diskID, 
                "idle", java.awt.Color.green);
        }
    }

    void printDisks(){
        for (int i = 0; i < 10; i++) {
            System.out.println(sectors[i].toString());
        }
    }
}
