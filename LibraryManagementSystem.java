public class LibraryManagementSystem {
    class Book {
        String title;
        String author;
        String genre;
        String id;
        boolean available;
        Book prev,next;
        public Book(String title, String author, String genre, String id, boolean available) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.id = id;
            this.available = available;
            this.next = null;
            this.prev = null;
        }
    }
    public Book head = null;
    public Book tail = null;

    public void addBookAtBeginning(String title, String author, String genre, String id, boolean available) {
        Book newBook = new Book(title, author, genre, id, available);
        if(head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addBookAtEnd(String title, String author, String genre, String id, boolean available){
        Book newBook = new Book(title, author, genre, id, available);
        if(head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void removeBook(String id) {
        Book temp = head;
        while (temp != null && !temp.id.equals(id)) {
            temp = temp.next;
        }
        if (temp == null) return;

        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    public void searchBook(String titleOrAuthor) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equals(titleOrAuthor) || temp.author.equals(titleOrAuthor)) {
                System.out.println(temp.title + " Book found\nAuthor: " + temp.author + ", Genre: " + temp.genre + ", ID: " + temp.id + ", Is Available: " + temp.available);
            }
            temp = temp.next;
        }
    }

    public void updateMovieRating(String title, boolean newStatus) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equals(title)) {
                temp.available = newStatus;
                return;
            }
            temp = temp.next;
        }
    }

    public void displayBooksForward() {
        Book temp = head;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", ID: " + temp.id + ", Is Available: " + temp.available);
            temp = temp.next;
        }
        System.out.println();
    }

    public void displayBooksReverse() {
        Book temp = tail;
        while (temp != null) {
            System.out.println("Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", ID: " + temp.id + ", Is Available: " + temp.available);
            temp = temp.prev;
        }
        System.out.println();
    }

    public int countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Adding books
        library.addBookAtBeginning("The Alchemist", "Paulo Coelho", "Fiction", "B101", true);
        library.addBookAtEnd("Atomic Habits", "James Clear", "Self-help", "B102", true);
        library.addBookAtEnd("Harry Potter", "J.K. Rowling", "Fantasy", "B103", false);

        // Display books
        System.out.println("Books in forward order:");
        library.displayBooksForward();

        System.out.println("Books in reverse order:");
        library.displayBooksReverse();

        // Search for a book
        System.out.println("Searching for 'Atomic Habits':");
        library.searchBook("Atomic Habits");

        // Remove a book
        library.removeBook("B103");
        System.out.println("After removing 'Harry Potter':");
        library.displayBooksForward();

        // Update book availability
        library.updateMovieRating("The Alchemist", false);
        System.out.println("After updating availability:");
        library.displayBooksForward();

        // Count books
        System.out.println("Total books in the library: " + library.countBooks());
    }
}

/*
Books in forward order:
Title: The Alchemist, Author: Paulo Coelho, Genre: Fiction, ID: B101, Is Available: true
Title: Atomic Habits, Author: James Clear, Genre: Self-help, ID: B102, Is Available: true
Title: Harry Potter, Author: J.K. Rowling, Genre: Fantasy, ID: B103, Is Available: false

Books in reverse order:
Title: Harry Potter, Author: J.K. Rowling, Genre: Fantasy, ID: B103, Is Available: false
Title: Atomic Habits, Author: James Clear, Genre: Self-help, ID: B102, Is Available: true
Title: The Alchemist, Author: Paulo Coelho, Genre: Fiction, ID: B101, Is Available: true

Searching for 'Atomic Habits':
Atomic Habits Book found
Author: James Clear, Genre: Self-help, ID: B102, Is Available: true
After removing 'Harry Potter':
Title: The Alchemist, Author: Paulo Coelho, Genre: Fiction, ID: B101, Is Available: true
Title: Atomic Habits, Author: James Clear, Genre: Self-help, ID: B102, Is Available: true

After updating availability:
Title: The Alchemist, Author: Paulo Coelho, Genre: Fiction, ID: B101, Is Available: false
Title: Atomic Habits, Author: James Clear, Genre: Self-help, ID: B102, Is Available: true

Total books in the library: 2
*/
