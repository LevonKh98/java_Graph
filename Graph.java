import java.util.*; 
import java.io.*; 
import java.util.Scanner;


public class Graph
{
           static final  int INFINITY = 10000;  //constant used for distance array
           private ArrayList<ArrayList<Integer>>    adjList;  //required data structure

           private String filename;
           private int nVertices;
           private int nEdges;
           private boolean[] visited;
           private int[] parent;
            private int[] distance;
               
            
          

public Graph ( String inputFileName)  { 
    nVertices = 0;
    nEdges = 0;
    filename = inputFileName;
    readInputData();
 }

private void  readInputData()  { 

    Scanner sc;
    try {
        
    
     sc = new Scanner(new File(filename));
        
        nVertices = sc.nextInt();
        //nEdges = sc.nextInt();
        adjList = new ArrayList<ArrayList<Integer>>(nVertices);
        for (int i = 0; i < nVertices; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        while (sc.hasNextInt()) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            nEdges++;
            adjList.get(u).add(v);
        
    }
    }
   
    
    catch (FileNotFoundException e) {
            
        e.printStackTrace();
    }

   
  }  //Add edges to adjlist in the order they are read in
                                                                     // from the file

public void printGraph( )  {
            System.out.println("The number of vertices is " + nVertices);
            System.out.println("The number of edges is " + nEdges);
            for (int i = 0; i < nVertices; i++) {
                System.out.print("Vertex "+ i + ": ");
                for (int j = 0; j < adjList.get(i).size(); j++) {
                    System.out.print(adjList.get(i).get(j) + " ");
                }
                System.out.println();
            }
  }

public int get_nVertices()  { 
    return nVertices;
 }

public int get_nEdges()  {  
    return nEdges;
} 





/* --------------  dfsTraversal ------------------------------------*/

public void dfsTraversal ( int vertex)  {
    visited = new boolean[nVertices];
    for (int i = 0; i < nVertices; i++) {
        visited[i] = false;
    }
    System.out.println("The dfs traversal starting from vertex " + vertex + " is: ");
    rdfs(vertex);
    System.out.println();
  } 
                    /*Instantiate and initialize visited array. Prints the dfs traversal vertices
                                    by calling the method rdfs */

private void rdfs( int vertex)  { 
    visited[vertex] = true;
    System.out.print(vertex + " ");
    for (int i = 0; i < adjList.get(vertex).size(); i++) {
        if (visited[adjList.get(vertex).get(i)] == false) {
            rdfs(adjList.get(vertex).get(i));
        }
    }
 }  //recursive dfs method



/* ------------------------------bfsTraversal ------------------------------*/

public void bfsTraversal ( int vertex, boolean verbose)   { 
    visited = new boolean[nVertices];
    parent = new int[nVertices];
    distance = new int[nVertices];
    for (int i = 0; i < nVertices; i++) {
        visited[i] = false;
        parent[i] = -1;
        distance[i] = INFINITY;
    }
    Queue<Integer>
    
    q = new LinkedList<Integer>();
    q.add(vertex);
    visited[vertex] = true;
    distance[vertex] = 0;
    if (verbose) {
        System.out.println("The bfs traversal starting from vertex " + vertex + " is: ");
    }
    while (!q.isEmpty()) {
        int u = q.remove();
        if (verbose) {
            System.out.print(u + " ");
           
    
}
        
       // System.out.println();

        for (int i = 0; i < adjList.get(u).size(); i++) {
            if (visited[adjList.get(u).get(i)] == false) {
                q.add(adjList.get(u).get(i));
                visited[adjList.get(u).get(i)] = true;
                parent[adjList.get(u).get(i)] = u;
                distance[adjList.get(u).get(i)] = distance[u] + 1;
                //System.out.println("Distance is");
               // System.out.println(distance[i]);
              
             

            }
        }
    }
    if (verbose) {
    System.out.println();
    System.out.print("Distance is  ");

    for (int k = 0; k < adjList.size(); k++) {
        if(distance[k] !=10000){
        System.out.print(distance[k]+" ");
        }
    else{
        System.out.print(0+" ");

    }}
    System.out.println();
    System.out.print("Parent is  ");
    for (int j = 0; j < adjList.size(); j++) {

    System.out.print(parent[j]+" ");}

    }
    
 } 
                      /*Instantiate and initialize visited, parent and distance arrays
                          Use a queue. Print the bfs traversal vertices only if verbose == true.
                           Computes parent and distance arrays*/


public void printShortestPaths (int vertex )  {

    bfsTraversal(vertex, false);
    System.out.println("The shortest paths from vertex " + vertex + " are: ");
    for (int i = 0; i < nVertices; i++) {
        if (distance[i] != INFINITY) {
            System.out.print("The distance from " + vertex + " to " + i + " is " + distance[i] + " and the path is: " + i);
            int j = i;
            while (parent[j] != -1) {
                System.out.print(" <- " + parent[j]);
                j = parent[j];
            }
            System.out.println();
        } else {
            System.out.println("There is no path from " + vertex + " to " + i);
        }
    }

 }
/*This method calls  bfsTraversal(vertex, false). Then  
 prints shortest paths from vertex to all reachable vertices 
using parent and distance arrays */


/* ----------------------  Is there a path from x to y? ----------------------*/

public boolean path(int x, int y) {
    visited = new boolean[nVertices];
    for (int i = 0; i < nVertices; i++) {
        visited[i] = false;
    }
    return rpath(x, y);
 } 
                 /*Instantiate and initialize visited array. 
                     Returns true if there is a path from x to y
                    else returns false. Use recursion by calling the method rpath */

private boolean rpath(int x, int y)  {
    visited[x] = true;
    if (x == y) {
        return true;
    }
    for (int i = 0; i < adjList.get(x).size(); i++) {
        if (visited[adjList.get(x).get(i)] == false) {
            if (rpath(adjList.get(x).get(i), y)) {
                return true;
            }
        }
    }
    return false;
}
  




public static void main( String[] args)
{

    int start;
    
    Graph g = new Graph("graph4.txt");
            
    /* ------------------ Test 1 ------------------------*/
    System.out.println("\nTest 1  Print graph");
    g.printGraph();
                            
     

    /* ------------------Test 2 --------------------------
    start = 1;
    System.out.println("\nTest 2   dfsTraversal from start = "+ start);
    g.dfsTraversal(start);
   
   /*-----------------------Test 3 -----------------------------
    start = 1;
    System.out.println("\nTest 3   bfsTraversal from start = " + start 
                + " and verbose = true");
    g.bfsTraversal(start, true);
    
    /* ------------- Test 4 ------------------------------
    start = 5;
    System.out.println("\nTest 4   printShortestPaths from vertex " + start);
    g.printShortestPaths(start);
        
    /*----------------------Test 5 -------------------------*  
    start = 1;
    System.out.println("\nTest 5   Print Shortest Paths from start = " + start);
    g.printShortestPaths(start);

    /*---------------------- Test 6 --------------------------
    System.out.println("\nTest 6 Check for paths");
    System.out.println( "There is a path from 1 to 3:  " + g.path(1,3));
    System.out.println( "There is a path from 2 to 0:  " + g.path(2,0));
    System.out.println( "There is a path from 6 to 1:  " + g.path(6,1));
    System.out.println( "There is a path from 5 to 8:  " + g.path(5,8));
*/
   

    
}

/*     ---------------------------------OUTPUT--------------------------------
 
  Test 1  Print graph
The number of vertices is 9
The number of edges is 11
Vertex 0: 3
Vertex 1: 2
Vertex 2: 4
Vertex 3: 4 6
Vertex 4: 6 5 1
Vertex 5: 2 0
Vertex 6:
Vertex 7: 8
Vertex 8:

Test 2   dfsTraversal from start = 1
The dfs traversal starting from vertex 1 is:
1 2 4 6 5 0 3

Test 3   bfsTraversal from start = 4 and verbose = true
The bfs traversal starting from vertex 4 is:
4 6 5 1 2 0 3
Distance is  2 1 2 3 0 1 1 0 0
Parent is  5 4 5 0 -1 4 4 -1 -1
Test 4   printShortestPaths from vertex 5
The shortest paths from vertex 5 are:
The distance from 5 to 0 is 1 and the path is: 0 <- 5
The distance from 5 to 1 is 3 and the path is: 1 <- 4 <- 2 <- 5
The distance from 5 to 2 is 1 and the path is: 2 <- 5
The distance from 5 to 3 is 2 and the path is: 3 <- 0 <- 5
The distance from 5 to 4 is 2 and the path is: 4 <- 2 <- 5
The distance from 5 to 5 is 0 and the path is: 5
The distance from 5 to 6 is 3 and the path is: 6 <- 4 <- 2 <- 5
There is no path from 5 to 7
There is no path from 5 to 8

Test 5   Print Shortest Paths from start = 1
The shortest paths from vertex 1 are:
The distance from 1 to 0 is 4 and the path is: 0 <- 5 <- 4 <- 2 <- 1
The distance from 1 to 1 is 0 and the path is: 1
The distance from 1 to 2 is 1 and the path is: 2 <- 1
The distance from 1 to 3 is 5 and the path is: 3 <- 0 <- 5 <- 4 <- 2 <- 1
The distance from 1 to 4 is 2 and the path is: 4 <- 2 <- 1
The distance from 1 to 5 is 3 and the path is: 5 <- 4 <- 2 <- 1
The distance from 1 to 6 is 3 and the path is: 6 <- 4 <- 2 <- 1
There is no path from 1 to 7
There is no path from 1 to 8

Test 6 Check for paths
There is a path from 1 to 3:  true
There is a path from 2 to 0:  true
There is a path from 6 to 1:  false
There is a path from 5 to 8:  false
  */



   } //end Graph

