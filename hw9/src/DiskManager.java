public class DiskManager 
    extends ResourceManager {

    // Disk disks[];
    int nextFreeSector[];
    DirectoryManager dirm;

    DiskManager(int numberOfItems) {
        super(numberOfItems);
        // disks = new Disk[numberOfItems];
        dirm = new DirectoryManager();

        nextFreeSector = new int[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            nextFreeSector[i] = 0;
        }
    }

    int getNextFreeSector(int diskNum) {
        return this.nextFreeSector[diskNum];
    }

    void setNextFreeSector(int diskNum, int newOffset) {
        this.nextFreeSector[diskNum] = newOffset;
    }
    
}
