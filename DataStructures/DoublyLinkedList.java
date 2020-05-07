class DoublyLinkedList {
    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static class DLL {
        public Node head = null;
        public Node tail = null;
        public int length = 0;

        public void setHead(Node node) {
            //unshift()
            if(this.head == null){
                this.head = node;
                this.tail = node;
            } else {
                Node prevHead = this.head;
                node.next = prevHead;
                prevHead.prev = node;
                this.head = node;
            }
        }

        public void setTail(Node node) {
            //push()
            if(this.tail == null){
                this.head = node;
                this.tail = node;
            } else {
                Node prevTail = this.tail;
                prevTail.next = node;
                node.prev = prevTail;
                this.tail = node;
            }
        }

        public void insertBefore(Node node, Node nodeToInsert) {
            // Write your code here.
        }

        public void insertAfter(Node node, Node nodeToInsert) {
            // Write your code here.
        }

        public void insertAtPosition(int position, Node nodeToInsert) {
            // Write your code here.
        }

        public void removeNodesWithValue(int value) {
            // Write your code here.
        }

        public void remove(Node node) {
            // Write your code here.
        }

        public boolean containsNodeWithValue(int value) {
            // Write your code here.
            return false;
        }
    }

    public static void main(String[] args) {
        DLL dll = new DLL();
        Node n1 = new Node(11);
        Node n2 = new Node(12);
        Node n3 = new Node(13);
        Node n4 = new Node(14);
        Node n5 = new Node(15);
        Node n6 = new Node(16);
        dll.setHead(n1);
        dll.setHead(n2);// make head
        dll.setTail(n6);
        dll.insertAtPosition(2, n5);
        dll.insertAfter(n5, n4);
        dll.insertBefore(n2, n3);// make head
        System.out.println(dll);
        dll.remove(n6);
        System.out.println(dll);
        System.out.println(dll.findPosition(n6));
        System.out.println(dll.containsNodeWithValue(12));
        System.out.println(dll.containsNodeWithValue(120));
        System.out.println(dll.get(0).value);
        dll.removeNodesWithValue(13);
        System.out.println(dll);
        dll.removeHead();
        dll.removeHead();
        dll.removeTail();
        dll.removeTail();
        System.out.println(dll.get(0));// null

    }
}