import java.time.LocalDateTime;
class TicketReservationSystem {
    class Ticket {
        int ticketID;
        String customerName;
        String movieName;
        String seatNumber;
        LocalDateTime bookingTime;
        Ticket next;

        public Ticket(int ticketID, String customerName, String movieName, String seatNumber) {
            this.ticketID = ticketID;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = LocalDateTime.now();
            this.next = null;
        }
    }
    public Ticket head = null;
    public Ticket tail = null;
    public int totalTickets = 0;

    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber);

        if (head == null) {
            head = newTicket;
            tail = newTicket;
            tail.next = head;
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
        totalTickets++;
        System.out.println("Ticket booked successfully! Ticket ID: " + ticketID);
    }

    public void removeTicket(int ticketID) {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        Ticket current = head, prev = null;

        do {
            if (current.ticketID == ticketID) {
                if (current == head && current == tail) { // Only one ticket
                    head = null;
                    tail = null;
                } else if (current == head) { // Removing first ticket
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) { // Removing last ticket
                    prev.next = head;
                    tail = prev;
                } else { // Removing middle ticket
                    prev.next = current.next;
                }
                totalTickets--;
                System.out.println("Ticket " + ticketID + " has been canceled.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket ID " + ticketID + " not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        System.out.println("\nCurrent Booked Tickets:");
        Ticket temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketID + ", Customer: " + temp.customerName +
                    ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber +
                    ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicket(String searchKey) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;
        boolean found = false;

        do {
            if (temp.customerName.equalsIgnoreCase(searchKey) || temp.movieName.equalsIgnoreCase(searchKey)) {
                System.out.println("\nTicket Found: Ticket ID: " + temp.ticketID + ", Customer: " + temp.customerName +
                        ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber +
                        ", Booking Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No ticket found for the given search criteria.");
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }
    public static void main(String[] args) {
        TicketReservationSystem reservationSystem = new TicketReservationSystem();

        reservationSystem.addTicket(101, "Alice", "Inception", "A10");
        reservationSystem.addTicket(102, "Bob", "Interstellar", "B5");
        reservationSystem.addTicket(103, "Charlie", "Avatar", "C7");

        reservationSystem.displayTickets();

        reservationSystem.searchTicket("Interstellar");
        reservationSystem.searchTicket("David"); // Not found case

        reservationSystem.removeTicket(102);

        reservationSystem.displayTickets();

        System.out.println("\nTotal Booked Tickets: " + reservationSystem.getTotalTickets());
    }
}

/*Ticket booked successfully! Ticket ID: 101
Ticket booked successfully! Ticket ID: 102
Ticket booked successfully! Ticket ID: 103

Current Booked Tickets:
Ticket ID: 101, Customer: Alice, Movie: Inception, Seat: A10, Booking Time: 2025-03-18T20:40:43.988
Ticket ID: 102, Customer: Bob, Movie: Interstellar, Seat: B5, Booking Time: 2025-03-18T20:40:43.989
Ticket ID: 103, Customer: Charlie, Movie: Avatar, Seat: C7, Booking Time: 2025-03-18T20:40:43.989

Ticket Found: Ticket ID: 102, Customer: Bob, Movie: Interstellar, Seat: B5, Booking Time: 2025-03-18T20:40:43.989
No ticket found for the given search criteria.
Ticket 102 has been canceled.

Current Booked Tickets:
Ticket ID: 101, Customer: Alice, Movie: Inception, Seat: A10, Booking Time: 2025-03-18T20:40:43.988
Ticket ID: 103, Customer: Charlie, Movie: Avatar, Seat: C7, Booking Time: 2025-03-18T20:40:43.989

Total Booked Tickets: 2
*/
