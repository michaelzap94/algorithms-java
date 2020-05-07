class SinglyLinkedList {
    // making this class Static so we can instantiate this class without having to
    // instantiate the outter(parent) class
    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // making this class Static so we can instantiate this class without having to
    // instantiate the outter(parent) class
    static class SLL {
        public Node head = null;
        public Node tail = null;
        public int length = 0;

        public void setHead(Node node) {
            // unshift()
            Node newNode = node;
            if(this.head == null && this.tail == null){
                this.head = newNode;
                this.tail = newNode;
            } else {//head exists
                //Make newNode point to current head
                newNode.next = this.head;
                //Make the newNode the new Head
                this.head = newNode;
            }
            this.length++;
        }

        public void setTail(Node node) {
            // push()
            Node newNode = node;
            if(this.head == null && this.tail == null){
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;//prevTail points to newNode
                this.tail = newNode;//the newNode becomes the new Tail
            }
            this.length++;
        }

        public Node get(int position){
            //if position is negative OR greater or equals than the length(indexoutofbounds)-> return null
            if(position < 0 || position >= this.length) {
                return null;
            } else {
                int counter = 0;
                Node currentNode = this.head;
                while(counter != position){//while we have not reached the requested position
                    //move currentNode pointer to next Node
                    currentNode = currentNode.next;
                    counter++;
                }//once we have reached the requested position, loop will STOP and currentNode == the requested Node

                return currentNode;
            }
        }

        public int findPosition(Node nodeToFind){
            //iterate through all nodes and return the one which matches the same value
            Node currentNode = this.head;
            int position = 0;
            while(currentNode != null && currentNode != nodeToFind){//if currentNode is null, we got to the end
                currentNode = currentNode.next;
                position++;
            };
            if(currentNode == null){
                //we got to the end with no found result so return -1
                return -1;
            } else {
                return position;         
            }
        }

        public void insertBefore(Node node, Node nodeToInsert) {
            //there has to exist a head for this method to make sense
            //you would need two pointers
            Node currentNode = this.head;
            Node previousFromCurrentNode = null;

            while(currentNode != null && currentNode != node){//if currentNode is null, we got to the end
                previousFromCurrentNode = currentNode;
                currentNode = currentNode.next;
            };
            if(currentNode != null){//we got to the end with SOME found result
                nodeToInsert.next = currentNode;
                //this will never be a tail but can be a head
                //if the currentNode was the head, then the new head will be the nodeToInsert
                if(currentNode == this.head){
                    //previousFromCurrentNode will be null if currentNode is a head
                    this.head = nodeToInsert;
                } else {
                    //previousFromCurrentNode will NOT be null if currentNode is NOT a head
                    previousFromCurrentNode.next = nodeToInsert;
                }
                this.length++;
            }
        }

        public void insertAfter(Node node, Node nodeToInsert) {
            //there has to exist a head for this method to make sense
            Node currentNode = this.head;
            while(currentNode != null && currentNode != node){//if currentNode is null, we got to the end
                currentNode = currentNode.next;
            };
            if(currentNode != null){ //we got to the end with SOME found result
                // Node nodeAFTER = currentNode.next;//null if currentNode is the tail
                //make the nodeToInsert point to nodeAFTER
                nodeToInsert.next = currentNode.next;
                //make the currentNode point to nodeToInsert
                currentNode.next = nodeToInsert;
                this.length++;
                //this will never be a head but can be a tail
                //if the currentNode was the tail, then the new tail will be the nodeToInsert
                if(currentNode == this.tail){
                    this.tail = nodeToInsert;
                }
            }
        }

        public void insertAtPosition(int position, Node nodeToInsert) {
            // insert() -> NOT UPDATE
            //if position is negative OR greater or equals than the length(indexoutofbounds)-> return null
            if(position < 0 || position >= this.length) {
                assert true;
            } else if(position == 0) {//unshift()
                setHead(nodeToInsert);
            } else if(position == this.length - 1){//push()
                setTail(nodeToInsert);
            } else {
                //find the currentNode at position
                Node prevNodeAtPosition = this.get(position - 1);
                //Node nodeAtPosition = prevNodeAtPosition.next;
                //make the nodeToInsert point to nodeAtPosition, since we are not updating, just inserting
                nodeToInsert.next = prevNodeAtPosition.next;
                //make the prevNodeAtPosition point to nodeToInsert
                prevNodeAtPosition.next = nodeToInsert;

                this.length++;
            }
        }

        public void removeHead() {
            // shift()
            if(this.head != null) {
                this.head = this.head.next;//if only one item, head would be pointing to null
                this.length--;
                //if after removing the head there's no items left, tail was the head too, so:
                if(this.length == 0){
                    this.tail = null;//make the tail null
                }
            }
        }

        public void removeTail() {
            // pop() -> O(n) since we need to perform get();
            // since the tail will be referenced from the previous Node, we need to traverse through the list
            // till we get to the previous Node of Tail, so we can remove the pointer. prevNode.next = null;
            if(this.tail != null){
                if(this.length == 1) {//if only one item, this is the head and the tail
                    this.tail = null;//make the tail null
                    this.head = null;//make the head null
                } else {
                    Node prevNodeOfTail = this.get(this.length - 1);
                    prevNodeOfTail.next = null;
                    this.tail = prevNodeOfTail;
                }
                this.length--;
            }
        }

        public void remove(Node node) {
            if (node == this.head) { removeHead();}
            else if (node == this.tail) { removeTail();}
            else {
                //find the node first
                Node currentNode = this.head;
                Node previousFromCurrentNode = null;
                while(currentNode != null && currentNode != node){
                    previousFromCurrentNode = currentNode;
                    currentNode = currentNode.next;
                }
                if(currentNode != null) {//we found the node 
                    //previousFromCurrentNode will not be null here as we are capturing the (node == this.head)
                    previousFromCurrentNode.next = currentNode.next;
                    currentNode.next = null;//not needed
                    this.length--;
                }
            }
        }

        
        public void removeNodesWithValue(int value) {
            // Write your code here.
            Node currentNode = this.head;
            while(currentNode != null){//if currentNode is null, we got to the end or didn't even start
                int nodeValue = currentNode.value;
                Node nodeToRemove = currentNode;
                currentNode = currentNode.next;
                if(nodeValue == value){
                    remove(nodeToRemove);
                }
            }
        }

        public boolean containsNodeWithValue(int value) {
            boolean contains = false;
            Node currentNode = this.head;
            while(currentNode != null && currentNode.value != value){//if currentNode is null, we got to the end
                currentNode = currentNode.next;
            };
            if(currentNode != null && currentNode.value == value){
                contains = true;
            }
            return contains;
        }

        @Override
        public String toString() {
            return "Head: " + this.head.value + "| Tail: "+ this.tail.value + "| Length: " + this.length;
        }
    }

    public static void main(String[] args) {
        SLL sll = new SLL();
        Node n1 = new Node(11);
        Node n2 = new Node(12);
        Node n3 = new Node(13);
        Node n4 = new Node(14);
        Node n5 = new Node(15);
        Node n6 = new Node(16);
        sll.setHead(n1);
        sll.setHead(n2);//make head
        sll.setTail(n6);
        sll.insertAtPosition(2, n5);
        sll.insertAfter(n5, n4);
        sll.insertBefore(n2, n3);//make head
        System.out.println(sll);
        sll.remove(n6);
        System.out.println(sll);
        System.out.println(sll.findPosition(n6));
        System.out.println(sll.containsNodeWithValue(12));
        System.out.println(sll.containsNodeWithValue(120));
        System.out.println(sll.get(0).value);
        sll.removeNodesWithValue(13);
        System.out.println(sll);
        sll.removeHead();
        sll.removeHead();
        sll.removeTail();
        sll.removeTail();
        System.out.println(sll.get(0));//null
        
    }
}
