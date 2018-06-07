import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Entry {
    private Date dateTime;
    private String content;

    Entry(String content) {
        this.content = content;
        this.dateTime = new Date();
    }
}
