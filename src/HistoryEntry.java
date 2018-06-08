import java.util.Date;

public class HistoryEntry {
    private Date dateTime;
    private String content;

    HistoryEntry(String content) {
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
