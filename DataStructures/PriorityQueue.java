import java.util.ArrayList;

public class PriorityQueue {
    static class Node{
        public int value;
        public int priority;
        public Node(int value, int priority){
            this.value = value;
            this.priority = priority;
        }
        @Override
        public String toString() {
            return "Value: " + value + " | priority: " + priority;
        }
    }
    static class MinBinaryHeap{
        private ArrayList<Node> values;
        public MinBinaryHeap(){
            values = new ArrayList<>();
        }

         //1) push the value to the end of the array
        //2) get the index of the parent -> floor ((n-1)/2)
        //3) Bubble up by swapping with parent while parent is more than value
        public void enqueue(Node value){
            values.add(value);
            int indexOfValue = values.size() - 1;
            int parentIndex = (int) Math.floor((indexOfValue - 1)/2);
            while(indexOfValue > 0 && values.get(indexOfValue).priority < values.get(parentIndex).priority){
                values.set(indexOfValue, values.get(parentIndex));
                values.set(parentIndex, value);
                indexOfValue = parentIndex;
                parentIndex = (int) Math.floor((indexOfValue - 1)/2);
            }
        }

        //1) Store the root in a var to return it
        //2) replace the root with the element at the end
        //3) Bubble down by swapping the smallest of the children with current if any is smaller
        //index of left child -> 2n + 1 | right child -> 2n + 2
        public Node dequeue(){
            Node root = values.get(0);//1)
            Node end = values.remove(values.size() - 1);
            //if node removed was not the root
            if(values.size() > 0){
                //2) replace the root with the element at end
                values.set(0, end);
                sinkDown();
            }
            return root;
        }

        //3)
        public void sinkDown(){
            int currentIndex = 0;//end
            Node currentNode = values.get(currentIndex);
            int length = values.size();
            while(true){
                int swapIndex = -1;
                int childLeftIndex = (currentIndex * 2) + 1;
                int childRightIndex = (currentIndex * 2) + 2;
                Node childLeft = null;
                Node childRight = null;
                //check inbounds
                if(childLeftIndex < length) {
                    childLeft = values.get(childLeftIndex);
                    if(childLeft.priority < currentNode.priority){
                        swapIndex = childLeftIndex;
                    }
                }
                if(childRightIndex < length) {
                    childRight = values.get(childRightIndex);
                    if( swapIndex == -1 && childRight.priority < currentNode.priority || //left was not set and this is less than current priority
                    swapIndex != -1 && childRight.priority < childLeft.priority){//left was set but this is less than left's priority
                        swapIndex = childRightIndex;
                    }
                }
                if(swapIndex != -1){
                    Node swapNode = values.get(swapIndex);
                    values.set(currentIndex, swapNode);
                    values.set(swapIndex, currentNode);
                    currentIndex = swapIndex;
                } else {
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        MinBinaryHeap newMinBH = new MinBinaryHeap();
        newMinBH.enqueue(new Node(11,41));
        newMinBH.enqueue(new Node(22,39));
        newMinBH.enqueue(new Node(33,33));
        newMinBH.enqueue(new Node(44,18));
        newMinBH.enqueue(new Node(55,27));
        newMinBH.enqueue(new Node(66,12));
        System.out.println(newMinBH.values);//[12, 27, 18, 41, 33, 39]
        newMinBH.enqueue(new Node(77,55));
        System.out.println(newMinBH.values);// [12, 27, 18, 41, 33, 39, 55]
        System.out.println(newMinBH.dequeue());//Node(66,12)
        System.out.println(newMinBH.values);// [18, 27, 39, 41, 33, 55]
    }
}