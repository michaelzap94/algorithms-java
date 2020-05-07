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

        public void setHead(Node node) {
            //unshift()
            if(this.head == null){
                this.head = node;
                this.tail = node;
            } else {
                Node prevHead = this.head;
                insertBefore(prevHead, node);
                // node.next = prevHead;
                // prevHead.prev = node;
                // this.head = node;
            }
        }

        public void setTail(Node node) {
            //push()
            if(this.tail == null){
                setHead(node);
            } else {
                Node prevTail = this.tail;
                insertAfter(prevTail, node);
                // prevTail.next = node;
                // node.prev = prevTail;
                // this.tail = node;
            }
        }

        //Assume it's a new Node OR an existing Node
        public void insertBefore(Node node, Node nodeToInsert) {
            //if we only have one node and we want to insert the same node before itself, return
            if(nodeToInsert == this.head && nodeToInsert == this.tail) return;
            //Assume it's an existing Node: first completly remove it
            remove(nodeToInsert);
            //Now you can add it as a brand new Node
            nodeToInsert.next = node;
            nodeToInsert.prev = node.prev;//null if head or prevNode if not head
            if(node.prev == null){//node is the head
                this.head = nodeToInsert;
            } else {
                Node prevNode = node.prev;//||nodeToInsert.prev
                prevNode.next = nodeToInsert;
            }
            node.prev = nodeToInsert;
        }

        //Assume it's a new Node OR an existing Node
        public void insertAfter(Node node, Node nodeToInsert) {
            //if we only have one node and we want to insert the same node after itself, return
            if(nodeToInsert == this.head && nodeToInsert == this.tail) return;
            //Assume it's an existing Node: first completly remove it
            remove(nodeToInsert);
            //Now you can add it as a brand new Node
            nodeToInsert.prev = node;
            nodeToInsert.next = node.next;//null if tail, nextNode if not null
            if(node.next == null){//if null it's a tail
                this.tail = nodeToInsert;
            } else {
                Node nextNode = node.next;
                nextNode.prev = nodeToInsert;
            }
            node.next = nodeToInsert;
        }

        //assume position starts at 1
        public void insertAtPosition(int position, Node nodeToInsert) {
            if(position < 0) {//if position is negative, pass
                return;
            } else if(position == 1) {//unshift()
                setHead(nodeToInsert);
            } else {
                Node currentNode = this.head;
                int counter = 1;
                while(currentNode!=null && position != counter){
                    currentNode = currentNode.next;
                    counter++;
                }
                if(currentNode!=null){//we found the position before getting to the tail, as if currentNode is null, it means we got to the tail
                    insertBefore(currentNode, nodeToInsert);
                } else {//if we got to the tail, then insert it there
                    setTail(nodeToInsert);
                }
            }
        }

        public void removeNodesWithValue(int value) {
            Node currentNode = this.head;
            while(currentNode!=null){
                //keep a reference of the Node to be removed
                Node nodeToBeRemoved = currentNode;
                //change the currentNode to nextNode
                currentNode = currentNode.next;          
                if(nodeToBeRemoved.value == value) {
                    remove(nodeToBeRemoved);
                }      
            }
        }

        public void remove(Node node) {
            if(node == this.head){
                this.head = node.next;//change head to node.next
            } 
            if(node == this.tail){
                this.tail = node.prev;
            } 
            //supposing node ALWAYS existS
            Node prevNode = node.prev;
            Node nextNode = node.next;
            if(prevNode != null){
                prevNode.next = nextNode;
            }
            if(nextNode != null){
                nextNode.prev = prevNode; 
            }
            
            node.next = null;//remove binding from node to node.next
            node.prev = null;//remove binding from node to node.prev
        }

        public boolean containsNodeWithValue(int value) {
            boolean contains = false;
            //because it's doubly we could do head to tail or tail to head
            Node currentNode = this.head;
            while(currentNode!=null && currentNode.value != value){
                currentNode = currentNode.next;
            }
            if(currentNode!=null){
                //currentNode contains some value that matches the value
                // && currentNode.value == value -> is redundant because we will get to this point iff there was a currentNode and the value was found, 
                // as if not found currentNode == null since that's the value the tail.next points to
                contains = true;
            }
            return contains;
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
        System.out.println(dll.containsNodeWithValue(12));
        System.out.println(dll.containsNodeWithValue(120));
        dll.removeNodesWithValue(13);
        System.out.println(dll);


    }
}