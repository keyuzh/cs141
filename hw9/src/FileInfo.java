public class FileInfo {
    int diskNumber;
    int startingSector;
    int fileLength;

    FileInfo(int dn, int ss, int fl) {
        this.diskNumber = dn;
        this.startingSector = ss;
        this.fileLength = fl;
    }
}
