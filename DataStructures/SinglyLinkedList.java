class SinglyLinkedList {
    //making this class Static so we can instantiate this class without having to instantiate the outter(parent) class
    static class Node {
        public int value;
        public Node prev;
        public Node next;
    
        public Node(int value) {
          this.value = value;
        }
    }

    //making this class Static so we can instantiate this class without having to instantiate the outter(parent) class
    static class SLL {
      public Node head;
      public Node tail;
      public int length = 0;
  
      public void setHead(Node node) {
        // Write your code here.//unshift()
              
      }
  
      public void setTail(Node node) {
        // Write your code here.
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

    public static void main(String[] args){
        SLL sll = new SLL();
        int len = sll.length;
        System.out.println("SLL Length: "+len );
    }
}
  