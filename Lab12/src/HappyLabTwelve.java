/**
 * Created by JiaxinZhang on 11/13/2017.
 */
import java.util.*;


public class HappyLabTwelve {
    public static void main(String[] args) {
        //exerciseBFS();
        //exerciseDFS();
        //exerciseMeasureDegrees();
        System.out.println(getBreadthFirstTraversal(1, instantiateSampleGraph()));
        System.out.println(measureDegrees(instantiateSampleGraph()));
    }

    public static void exerciseDFS() {
        System.out.println("############################");
        System.out.println("This is in DFS traversal section: ");
        GraphInterface<Integer> simpleGraph = instantiateSampleGraph();
        Queue<Integer> simpleGraphDfsResult = getDepthFirstTraversal(1, simpleGraph);
        outputQueue(simpleGraphDfsResult);
        simpleGraph = instantiateSampleGraph();
        simpleGraphDfsResult = getDepthFirstTraversal(7, simpleGraph);
        outputQueue(simpleGraphDfsResult);

        GraphInterface<Integer> emptyGraph = instantiateEmptyGraph();
        Queue<Integer> emptyGraphDfsResult = getDepthFirstTraversal(1, emptyGraph);
        outputQueue(emptyGraphDfsResult);
        System.out.println("############################");
    }

    public static void exerciseBFS() {
        System.out.println("############################");
        System.out.println("This is in BFS traversal section: ");
        GraphInterface<Integer> simpleGraph = instantiateSampleGraph();
        Queue<Integer> simpleGraphDfsResult = getBreadthFirstTraversal(1, simpleGraph);
        outputQueue(simpleGraphDfsResult);
        simpleGraph = instantiateSampleGraph();
        simpleGraphDfsResult = getBreadthFirstTraversal(7, simpleGraph);
        outputQueue(simpleGraphDfsResult);

        GraphInterface<Integer> emptyGraph = instantiateEmptyGraph();
        Queue<Integer> emptyGraphDfsResult = getBreadthFirstTraversal(1, emptyGraph);
        outputQueue(emptyGraphDfsResult);
        System.out.println("############################");
    }

    public static void exerciseMeasureDegrees() {
        System.out.println("############################");
        System.out.println("This is in degree measurement section: ");
        GraphInterface<Integer> simpleGraph = instantiateSampleGraph();
        Map<Integer, Integer> simpleGraphMeasureDegreesMap =measureDegrees(simpleGraph);
        outputMap(simpleGraphMeasureDegreesMap);

        GraphInterface<Integer> emptyGraph = instantiateEmptyGraph();
        Map<Integer, Integer> emptyGraphMeasureDegreesMap = measureDegrees(emptyGraph);
        outputMap(emptyGraphMeasureDegreesMap);
        System.out.println("############################");
    }

    public static void outputQueue(Queue<Integer> queue) {
        if (queue.size() == 0) {
            System.out.println("Queue is empty");
        } else {
            System.out.println("Queue contains: ");
            while (!queue.isEmpty()) {
                System.out.print(queue.poll() + " ");
            }
            System.out.println();
        }
    }

    public static void outputMap(Map<Integer, Integer> map) {
        if (null != map) {
            for (Integer vertexLabel: map.keySet()) {
                if (map.get(vertexLabel) != 0)
                    System.out.println(String.format("The degree for the vertex labeled " + vertexLabel + " is " +
                        map.get(vertexLabel)));
            }
        }
    }

    public static Queue<Integer> getBreadthFirstTraversal(Integer origin, GraphInterface<Integer> graph) {
        Queue<Integer> breadthFST=new LinkedList<>();
        Queue<VertexInterface> track=new LinkedList<>();
        track.add(graph.getVertex(origin));
        while(!track.isEmpty()){
            VertexInterface temp=track.poll();
            temp.visit();
            breadthFST.add((Integer)temp.getLabel());
            Iterator<VertexInterface<Integer>> neighbourIterator=temp.getNeighborIterator();
            while(neighbourIterator.hasNext()){
                VertexInterface<Integer> catch1=neighbourIterator.next();
                if(!catch1.isVisited()){
                    track.add(catch1);
                }
            }
        }
        return breadthFST;
    }

    public static Queue<Integer> getDepthFirstTraversal(Integer origin, GraphInterface<Integer> graph) {
        return null;
    }

    public static Map<Integer, Integer> measureDegrees(GraphInterface<Integer> graph) {
        Integer cont=0;
        Map<Integer,Integer> mapForAll=new HashMap<>();
        Queue<Integer> tempQueue=getBreadthFirstTraversal(1,graph);
        while(!tempQueue.isEmpty()){
            Integer now=tempQueue.poll();
            VertexInterface<Integer> temp=graph.getVertex(now);
            Iterator<VertexInterface<Integer>> neighbourIterator=temp.getNeighborIterator();
            while(neighbourIterator.hasNext()){
                neighbourIterator.next();
                cont++;
            }
            mapForAll.put(now,cont);
            cont=0;
        }
        return mapForAll;
    }

    public static GraphInterface<Integer> instantiateSampleGraph() {
        GraphInterface<Integer> simpleGraph = new SimpleGraph();
        for (int i = 0; i < 10; i++)
            simpleGraph.addVertex(i);
        simpleGraph.addEdge(1, 6, 1.0);
        simpleGraph.addEdge(6, 1, 1.0);
        simpleGraph.addEdge(1, 9, 2.0);
        simpleGraph.addEdge(9, 1, 2.0);
        simpleGraph.addEdge(9, 7, 3.0);
        simpleGraph.addEdge(7, 9, 3.0);
        simpleGraph.addEdge(9, 8, 4.0);
        simpleGraph.addEdge(8, 9, 4.0);
        simpleGraph.addEdge(6, 5, 5.0);
        simpleGraph.addEdge(5, 6, 5.0);
        simpleGraph.addEdge(6, 4, 6.0);
        simpleGraph.addEdge(4, 6, 6.0);
        simpleGraph.addEdge(6, 2, 7.0);
        simpleGraph.addEdge(2, 6, 7.0);
        simpleGraph.addEdge(6, 3);
        simpleGraph.addEdge(3, 6);
        return simpleGraph;
    }

    public static GraphInterface<Integer> instantiateEmptyGraph() {
        return new SimpleGraph();
    }
}
