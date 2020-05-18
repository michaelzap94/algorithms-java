import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
 
class UndirectedUnweightedGraph {
    static class Node{
        String name;
        ArrayList<Node> connections;
        public Node(String name){
            this.name = name;
            this.connections = new ArrayList<>();
        }
    }

    static class UUGraph{
        public HashMap<String, List<String>> adjacencyList;
        public UUGraph(){
            this.adjacencyList = new HashMap<>();
        }
        //add node and empty array of edges connected to it, if not in map already.
        public void addVertex(String vertex){
            if(!adjacencyList.containsKey(vertex)){
                adjacencyList.put(vertex, new ArrayList<>());
            }
        }

        //add edges to an existing node.
        public void addEdge(String v1, String v2){
            //both verteces must exist first
            if(adjacencyList.containsKey(v1) && adjacencyList.containsKey(v2)){
                List<String> vertecesListV1 = adjacencyList.get(v1);
                List<String> vertecesListV2 = adjacencyList.get(v2);
                //IT'S A UNDIRECTED UNWEIGHTED GRAPH
                vertecesListV1.add(v2);
                vertecesListV2.add(v1);
            }
        }
        
        //1)we need to remove it from dict/array and also 2)from the nodes that point to this vertex
        public void removeVertex(String vertex){
            if(adjacencyList.containsKey(vertex)){
                //loop through the list of connected vertices to find out which other verteces hold connections to this vertex
                List<String> vertecesList = adjacencyList.get(vertex);
                for (int i = 0; i < vertecesList.size(); i++) {
                    String vertexRef = vertecesList.get(i);
                    //for each vertex holding a ref to this vertex remove the ref to this node
                    removeEdge(vertex, vertexRef);
                }
                adjacencyList.remove(vertex);
            }
        }

        public void removeEdge(String v1, String v2){
            //both verteces must exist first
            if(adjacencyList.containsKey(v1) && adjacencyList.containsKey(v2)){
                List<String> vertecesListV1 = adjacencyList.get(v1);
                List<String> vertecesListV2 = adjacencyList.get(v2);
                //IT'S A UNDIRECTED UNWEIGHTED GRAPH
                List<String> newVertecesListV1 = vertecesListV1.stream()         // convert list to stream
                .filter(vertexName -> !(vertexName==v2))     // filter it out
                .collect(Collectors.toList()); 
                adjacencyList.put(v1, newVertecesListV1);

                List<String> newVertecesListV2 = vertecesListV2.stream()         // convert list to stream
                .filter(vertexName -> !(vertexName==v1))     // filter it out
                .collect(Collectors.toList()); 
                adjacencyList.put(v2, newVertecesListV2);
            }
        }

        public void dfs_rec(){

        }

        public void dfs_iter(){

        }

        public void bfs_iter(){

        }
    }

    public static void main(String[] args) {
        UUGraph g = new UUGraph();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addVertex("D");
        g.addVertex("E");
        g.addVertex("F");
        g.addEdge("A","B");
        g.addEdge("A","C");
        g.addEdge("B","D");
        g.addEdge("C","E");
        g.addEdge("D","E");
        g.addEdge("D","F");
        g.addEdge("E","F");
        System.out.println(g.adjacencyList);//{A=[B, C], B=[A, D], C=[A, E], D=[B, E, F], E=[C, D, F], F=[D, E]}
        g.removeEdge("A","B");
        System.out.println(g.adjacencyList);//{A=[C], B=[D], C=[A, E], D=[B, E, F], E=[C, D, F], F=[D, E]}
        g.removeVertex("E");
        System.out.println(g.adjacencyList);//{A=[C], B=[D], C=[A], D=[B, F], E=[], F=[D]}


        // System.out.println(g.dfs_rec("A"));//[ 'A', 'B', 'D', 'E', 'C', 'F' ]
        // System.out.println(g.dfs_iter("A"));//[ 'A', 'C', 'E', 'F', 'D', 'B' ]
        // System.out.println(g.bfs("A"));//[ 'A', 'B', 'C', 'D', 'E', 'F' ]
    }
}