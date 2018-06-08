public class InputBuffer {
    private StringBuffer buffer;

    public InputBuffer() {
        buffer = new StringBuffer();
    }

    public void update(String character) {
        buffer.append(character);
    }

    public String get() {
        return buffer.toString();
    }

    public void wipe() {
        buffer.delete(0, buffer.length());
    }

    public boolean isEmpty(){
        return  buffer.length() == 0;
    }

}
