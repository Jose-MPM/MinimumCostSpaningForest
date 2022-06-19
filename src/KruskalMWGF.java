/**
 * Class that builds the minimum weight-generating forest of a given graph in a text file
 */

import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.util.PriorityQueue;
import structureDS.DisjointSets;

public class KruskalMWGF{

    /** 
     * Class that allows us to define how we will comparate the edges
     * through the weight of each edge (The third element of each edge(tupla)).
     */
    public static class CompareWeights implements Comparator<String>{

        @Override
        public int compare(String str1, String str2){
            String[] arr1 = str1.split(",");
            String[] arr2 = str2.split(",");
            int result = 0;
            int w1 = Integer.parseInt(arr1[2]);
            int w2 = Integer.parseInt(arr2[2]);

            if(w1 < w2){
                result = -1;
            }else if(w1 > w2){
                result = 1;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        try {

            File dataG = new File(args[0]);
            Scanner scE = new Scanner(dataG); 
            int numEdges = -1;
            while(scE.hasNext()){ // Calculates the number of edges
                numEdges = numEdges+1;
                scE.nextLine();
            }
            System.out.println("Number of Edges: "+ numEdges);
            Scanner scV = new Scanner(dataG); 
            String line = scV.nextLine();
            String[] strV = line.split(",");
            int[] v = new int[strV.length];
            int count = 0;
            int nOfV = v.length;
            for(int i = 0; i < nOfV; i++){
                v[i] = Integer.parseInt(strV[i]);
                count = count + 1;
            }
            scV.close();
            System.out.println("Number of Vertices: "+nOfV);

            DisjointSets d = new DisjointSets(nOfV); // Set of sets xd
            for (int j = 0; j < nOfV-1; j++) {
                d.makeSet(v[j]);  // at the begining we have one set for each vertex
            }

            Comparator<String> compareW = new CompareWeights();
            PriorityQueue<String> pQ = new PriorityQueue<>(numEdges, compareW);
            
            Scanner scG = new Scanner(dataG);
            String frtL = scG.nextLine(); // The first line represent the vertices of G
            System.out.println("Vertices of G: "+frtL+".");
            System.out.println("Edges of G:");
            while(scG.hasNext()){ // Insert each edge in our Priority Queue according to its cost(CompareWeight)
                String e = scG.nextLine();
                System.out.println(e);
                pQ.add(e); 
                
            }

            System.out.println();
            int kgT = 0;
            int ke = 0;
            int n = 0;
            System.out.println("Edges that make up our Forest :");
            
            // Construct the minimium weight generator tree
            while(!pQ.isEmpty() && ke < nOfV-1){
                String e = pQ.remove();  
                String[] elements = e.split(",");
                int vi = Integer.parseInt(elements[0]);
                int vf = Integer.parseInt(elements[1]);
                int kgE = Integer.parseInt(elements[2]);
                int s1 = d.findSet(vi);
                int s2 = d.findSet(vf);
                
                if (s1 != s2) {
                    n++;
                    System.out.println("e("+n+"):  --->  "+e);
                    System.out.println("Vi: "+vi+", Vf: "+vf+ ", Edge cost: "+kgE);
                    kgT = kgT+kgE; // cost total 
                    d.union(vi, vf);
                    ke++;
                }
            }
             System.out.println("Total cost of the minimum weight-generating forest: "+kgT+".");
             scE.close();
             scG.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}