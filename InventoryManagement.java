public class InventoryManagement {
    class Item {
        String name;
        String id;
        int quantity;
        double price;
        Item next;
        public Item(String name, String id, int quantity, double price) {
            this.name = name;
            this.id = id;
            this.quantity = quantity;
            this.price = price;
            this.next = null;
        }
    }

    Item head = null;
    Item tail = null;

    public void addItemAtBeginning(String name, String id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            tail = newItem;
        } else {
            newItem.next = head;
            head = newItem;
        }
    }

    public void addItemAtEnd(String name, String id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
            tail = newItem;
        } else {
            tail.next = newItem;
            tail = newItem;
        }
    }

    public void addItemAtPosition(String name, String id, int quantity, double price, int position) {
        Item newItem = new Item(name, id, quantity, price);
        if (position == 1){
            addItemAtBeginning(name, id, quantity, price);
            return;
        }
        Item current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Position out of bounds");
        } else {
            newItem.next = current.next;
            current.next = newItem;
        }
    }

    public void removeItem(String id) {
        Item temp = head;
        Item prev = null;
        if (temp != null && temp.id.equals(id)) {
            head = temp.next;
            return;
        }
        while (temp != null && !temp.id.equals(id)) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;
    }

    public void updateItemQuantity(String id, int newQuantity) {
        Item current = head;
        while (current != null) {
            if (current.id.equals(id)) {
                current.quantity = newQuantity;
                System.out.println("Updated quantity for Item ID: " + id);
                return;
            }
            current = current.next;
        }
        System.out.println("Item with ID " + id + " not found.");
    }

    public void searchItem(String key) {
        Item current = head;
        while (current != null) {
            if (current.id.equals(key) || current.name.equalsIgnoreCase(key)) {
                System.out.println("Item Found: Name: " + current.name + ", ID: " + current.id + ", Quantity: " + current.quantity + ", Price: " + current.price);
                return;
            }
            current = current.next;
        }
        System.out.println("Item not found.");
    }

    public void calculateTotalValue() {
        double totalValue = 0;
        Item current = head;
        while (current != null) {
            totalValue += current.price * current.quantity;
            current = current.next;
        }
        System.out.println("Total Inventory Value: " + totalValue);
    }

    public void sortInventoryByName() {
        for (Item i = head; i != null; i = i.next) {
            for (Item j = i.next; j != null; j = j.next) {
                if (i.name.compareToIgnoreCase(j.name) > 0) {
                    swapItems(i, j);
                }
            }
        }
    }

    public void sortInventoryByPrice() {
        for (Item i = head; i != null; i = i.next) {
            for (Item j = i.next; j != null; j = j.next) {
                if (i.price > j.price) {
                    swapItems(i, j);
                }
            }
        }
    }

    private void swapItems(Item a, Item b) {
        String tempName = a.name;
        String tempId = a.id;
        int tempQuantity = a.quantity;
        double tempPrice = a.price;

        a.name = b.name;
        a.id = b.id;
        a.quantity = b.quantity;
        a.price = b.price;

        b.name = tempName;
        b.id = tempId;
        b.quantity = tempQuantity;
        b.price = tempPrice;
    }

    public void display() {
        Item current = head;
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Item Records in Inventory:");
        while (current != null) {
            System.out.print("Name: " + current.name);
            System.out.print(", ID: " + current.id);
            System.out.print(", Quantity: " + current.quantity);
            System.out.print(", Price: " + current.price);
            System.out.println();
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();

        inventory.addItemAtEnd("Pen", "0P01", 10, 10.0);
        inventory.addItemAtBeginning("Pencil", "1P01", 15, 2.5);
        inventory.addItemAtBeginning("Notebook", "0N01", 35, 25.0);
        inventory.addItemAtPosition("Eraser", "0E01", 21, 1.0, 2);
        inventory.display();

        inventory.removeItem("0E01");
        inventory.display();

        inventory.updateItemQuantity("1P01", 20);
        inventory.display();

        inventory.searchItem("Pen");
        inventory.searchItem("1P01");

        inventory.calculateTotalValue();

        inventory.sortInventoryByName();
        System.out.println("Sorted by Name:");
        inventory.display();

        inventory.sortInventoryByPrice();
        System.out.println("Sorted by Price:");
        inventory.display();
    }
}

/*Item Records in Inventory:
Name: Notebook, ID: 0N01, Quantity: 35, Price: 25.0
Name: Eraser, ID: 0E01, Quantity: 21, Price: 1.0
Name: Pencil, ID: 1P01, Quantity: 15, Price: 2.5
Name: Pen, ID: 0P01, Quantity: 10, Price: 10.0

Item Records in Inventory:
Name: Notebook, ID: 0N01, Quantity: 35, Price: 25.0
Name: Pencil, ID: 1P01, Quantity: 15, Price: 2.5
Name: Pen, ID: 0P01, Quantity: 10, Price: 10.0

Updated quantity for Item ID: 1P01
Item Records in Inventory:
Name: Notebook, ID: 0N01, Quantity: 35, Price: 25.0
Name: Pencil, ID: 1P01, Quantity: 20, Price: 2.5
Name: Pen, ID: 0P01, Quantity: 10, Price: 10.0

Item Found: Name: Pen, ID: 0P01, Quantity: 10, Price: 10.0
Item Found: Name: Pencil, ID: 1P01, Quantity: 20, Price: 2.5
Total Inventory Value: 1025.0
Sorted by Name:
Item Records in Inventory:
Name: Notebook, ID: 0N01, Quantity: 35, Price: 25.0
Name: Pen, ID: 0P01, Quantity: 10, Price: 10.0
Name: Pencil, ID: 1P01, Quantity: 20, Price: 2.5

Sorted by Price:
Item Records in Inventory:
Name: Pencil, ID: 1P01, Quantity: 20, Price: 2.5
Name: Pen, ID: 0P01, Quantity: 10, Price: 10.0
Name: Notebook, ID: 0N01, Quantity: 35, Price: 25.0
*/