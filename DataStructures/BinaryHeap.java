import java.util.ArrayList;
class BinaryHeap {
    static class MaxBinaryHeap{
        private ArrayList<Integer> values;
        public MaxBinaryHeap(){
            this.values = new ArrayList<>();
        }
        //1) Store the root in a var to return it
        //2) replace the root with the element at the end
        //3) Bubble down by swapping the biggest of the children with current if any is larger
        //index of left child -> 2n + 1 | right child -> 2n + 2
        public int extractMax(){
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
                        if(leftChild > last) {//only set swap if leftChild is larger than element
                            indexToSwap = leftChildIndex;
                        }
                    }
                    if(rightChildIndex < this.values.size()){
                        rightChild = this.values.get(rightChildIndex);
                        if((indexToSwap == -1 && rightChild > last) || //swap was never set to left and rightchild is larger than element
                            (indexToSwap != -1 && rightChild > leftChild)) { //swap was set to left BUT right is smaller
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
        //1) push the value to the end of the array
        //2) get the index of the parent -> floor ((n-1)/2)
        //3) Bubble up by swapping with parent while parent is less than value
        public void insert(int value){
            this.values.add(value);//push
            int indexOfNewValue = this.values.size() - 1;
            int parentIndex = (int) Math.floor((indexOfNewValue - 1) / 2);
            while(this.values.get(parentIndex) < value){
                int parentValue = this.values.get(parentIndex);
                this.values.set(parentIndex, value);
                this.values.set(indexOfNewValue, parentValue);

                indexOfNewValue = parentIndex;
                parentIndex = (int) Math.floor((indexOfNewValue - 1) / 2);
            }
        }
    }    
    public static void main(String[] args) {
        MaxBinaryHeap newMaxBH = new MaxBinaryHeap();
        newMaxBH.insert(41);
        newMaxBH.insert(39);
        newMaxBH.insert(33);
        newMaxBH.insert(18);
        newMaxBH.insert(27);
        newMaxBH.insert(12);
        System.out.println(newMaxBH.values);//[41, 39, 33, 18, 27, 12]
        newMaxBH.insert(55);
        System.out.println(newMaxBH.values);// [ 55, 39, 41, 18, 27, 12, 33 ]
        newMaxBH.extractMax();//55
        System.out.println(newMaxBH.values);// [41, 39, 33, 18, 27, 12]
    }
}