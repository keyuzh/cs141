public class UserThread
    extends Thread
    {
        String fileName;
        StringBuffer line;

        UserThread(String fn) {
            this.fileName = fn;
            this.line = new StringBuffer();
        }

        public void run() {
            processUserCommands(fileName);
        }

        void processUserCommands(String fileName2) {
        }
    }