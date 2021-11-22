class Disk
    extends Thread
{
    
    static final int NUM_SECTORS = 1024;

    StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];

    public Disk() {
        this.setDaemon(true);
        System.out.println("create disk");
        for (int i = 0; i < sectors.length; i++) {
            sectors[i] = new StringBuffer();
        }
        System.out.println("x");
    }

    void copyStringBuffer(StringBuffer to, StringBuffer from) {
        to.delete(0, to.length());
        to.append(from);
    }

    void write(int sector, StringBuffer data)
    {
        copyStringBuffer(this.sectors[sector], data);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //TODO: handle exception
            System.out.println("write");
        }
    }

    void read(int sector, StringBuffer data)
    {
        copyStringBuffer(data, this.sectors[sector]);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            //TODO: handle exception
            System.out.println("read");
        }
    }

    void printDisks(){
        for (int i = 0; i < 10; i++) {
            System.out.println(sectors[i].toString());
        }
    }
}
