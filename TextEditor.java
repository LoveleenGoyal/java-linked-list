public class TextEditor {
    class State {
        String text;
        State prev, next;

        public State(String text) {
            this.text = text;
            this.prev = null;
            this.next = null;
        }
    }

    public State head = null, tail = null, current = null;
    public int size;
    private final int MAX_HISTORY = 10;

    public void addText(String newText){
        String fullText = (current == null) ? newText : current.text + " " + newText;
        State state = new State(fullText);
        if(head == null) {
            head = tail = current = state;
        } else {
            current.next = state;
            state.prev = current;
            current = state;
            tail = state;
        }
        size++;
        if (size > MAX_HISTORY) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            size--;
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: " + current.text);
        } else {
            System.out.println("No text available.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.addText("Hello");
        editor.displayCurrentState();
        editor.addText("World");
        editor.addText("!");
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState();

        editor.redo();
        editor.displayCurrentState();
    }
}

/*Current State: Hello
Current State: Hello World !
Current State: Hello World
Current State: Hello World !
*/