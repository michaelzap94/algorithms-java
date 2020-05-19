import java.util.ArrayList;

public class BinaryHeapMin {
    static class MinBinaryHeap{
        private ArrayList<Integer> values;
        public MinBinaryHeap(){
            values = new ArrayList<>();
        }

         //1) push the value to the end of the array
        //2) get the index of the parent -> floor ((n-1)/2)
        //3) Bubble up by swapping with parent while parent is more than value
        public void insert(int value){
            this.values.add(value);//push
            int indexOfNewValue = this.values.size() - 1;
            int parentIndex = (int) Math.floor((indexOfNewValue - 1) / 2);
            while(this.values.get(parentIndex) > value){
                int parentValue = this.values.get(parentIndex);
                this.values.set(parentIndex, value);
                this.values.set(indexOfNewValue, parentValue);

                indexOfNewValue = parentIndex;
                parentIndex = (int) Math.floor((indexOfNewValue - 1) / 2);
            }
        }

        //1) Store the root in a var to return it
        //2) replace the root with the element at the end
        //3) Bubble down by swapping the smallest of the children with current if any is smaller
        //index of left child -> 2n + 1 | right child -> 2n + 2
        public int extractMin(){
            //int root = this.values.remove(0);//removing from the start will make it o(n)
            if(this.values.size() == 0) return -1;
            int root = this.values.get(0);
            int last = this.values.remove(this.values.size() - 1);//removing from end o(1);
            if(this.values.size() > 0){
                this.values.set(0, last);
                int lastIndex = 0;
                while(true){
                    //compare last to children
                    int leftChildIndex = (2 * lastIndex) + 1;
                    int rightChildIndex = (2 * lastIndex) + 2;
                    //CHECK INBOUNDS OF INDECES
                    int leftChild = -1;
                    int rightChild = -1;
                    int indexToSwap = -1;
                    if(leftChildIndex < this.values.size()) {
                        leftChild = this.values.get(leftChildIndex);
                        if(leftChild < last) {//only set swap if leftChild is smaller
                            indexToSwap = leftChildIndex;
                        }
                    }
                    if(rightChildIndex < this.values.size()){
                        rightChild = this.values.get(rightChildIndex);
                        if((indexToSwap == -1 && rightChild < last) || //swap was never set to the left child
                            (indexToSwap != -1 && rightChild < leftChild)) { //swap was set to left BUT right is smaller
                            indexToSwap = rightChildIndex;
                        }
                    }

                    if(indexToSwap != -1) {
                        this.values.set(lastIndex, this.values.get(indexToSwap));
                        this.values.set(indexToSwap, last);
                        lastIndex = indexToSwap;
                    } else {
                        break;
                    }
                }
            }
            return root;
        }

    }
    public static void main(String[] args) {
        MinBinaryHeap newMinBH = new MinBinaryHeap();
        newMinBH.insert(41);
        newMinBH.insert(39);
        newMinBH.insert(33);
        newMinBH.insert(18);
        newMinBH.insert(27);
        newMinBH.insert(12);
        System.out.println(newMinBH.values);//[12, 27, 18, 41, 33, 39]
        newMinBH.insert(55);
        System.out.println(newMinBH.values);// [12, 27, 18, 41, 33, 39, 55]
        newMinBH.extractMin();//12
        System.out.println(newMinBH.values);// [18, 27, 39, 41, 33, 55]
    }
}