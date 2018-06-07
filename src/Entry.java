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

    @Override
    public String toString() {
        return "[" + dateTime.toString() + "]" + " \"" + content +"\"" ;
    }

    public String getContent() {
        return content;
    }

    public Date getDateTime() {
        return dateTime;
    }
}
