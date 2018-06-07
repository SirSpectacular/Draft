import java.util.concurrent.TimeUnit;

public class Draft {

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter(new FileLogger("logging.txt"));
        Scanner scanner = new Scanner();

        while (true) {
            try {

                if (!scanner.isEmpty()) {
                    String content = scanner.getBuffer();
                    interpreter.addEntry(content);
                    scanner.wipeBuffer();
                }

                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
