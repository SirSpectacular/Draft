import org.jline.reader.*

public class Scanner {
    private StringBuffer buffer;

    public Scanner() {
        startScanning();
    }

    private void startScanning() {
        Thread thread = new Thread(() -> {
            //TODO: Coś tam, updateBuffor;
            consoleReader;

        });
        thread.start();
    }

    private void updateBuffer(char c) {
        buffer.append(c);
    }

    public String getBuffer() {
        return buffer.toString();
    }

    public void wipeBuffer() {
        buffer.delete(0, buffer.length());
    }

    public boolean isEmpty(){
        return  buffer.length() == 0;
    }

}
