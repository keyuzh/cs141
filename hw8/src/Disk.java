class Disk
    // extends Thread
{
    
    static final int NUM_SECTORS = 1024;

    StringBuffer sectors[] = new StringBuffer[NUM_SECTORS];

    void write(int sector, StringBuffer data)
    {
        this.sectors[sector] = data;
    }

    void read(int sector, StringBuffer data)
    {
        data = this.sectors[sector];
    }
}
