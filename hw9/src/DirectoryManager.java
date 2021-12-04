import java.util.Hashtable;

public class DirectoryManager {
    public DirectoryManager() {

    }
    private Hashtable<String, FileInfo> T = new Hashtable<String, FileInfo>();

    void enter(StringBuffer fileName, FileInfo file) {
        T.put(fileName.toString(), file);
    }

    FileInfo lookup(StringBuffer fileName) {
        return T.get(fileName.toString());
    }
}
