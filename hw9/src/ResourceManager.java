public class ResourceManager {
    boolean isFree[];

    ResourceManager(int numberOfItems) {
        isFree = new boolean[numberOfItems];
        for (int i=0; i<isFree.length; ++i)
            isFree[i] = true;
    }

    synchronized int request() {
        while (true) {
            for (int i = 0; i < isFree.length; ++i) {
                if ( isFree[i] ) {
                    isFree[i] = false;
                    return i;
                }
            }
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // block until someone releases Resource
        }
    }

    synchronized void release( int index ) {
        isFree[index] = true;
        this.notify(); // let a blocked thread run
    }
}
