class PrintJobThread
    extends Thread
{
    StringBuffer line = new StringBuffer(); // only allowed one line to reuse for read from disk and print to printer

    PrintJobThread()
    {
    }
    
    void print(String fileName)
    {
    }
}
