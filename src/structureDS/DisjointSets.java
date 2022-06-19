package structureDS;
/**
 * Class that represent the collection of Disjoint(foreign) sets.
 */
public class DisjointSets{
    
    private int[] root;  // represent of v_i 
    private int[] range;

    /**
     * Constructor of the class.
     * 
     * @param n number of subsets.
     */
    public DisjointSets(int n){
        this.root = new int[n+1];
        this.range = new int[n+1];
    }

    /**
     * Creates the foreign set containing the vertex v.
     * 
     * @param v vertex 
     */
    public void makeSet(int v){ //apunta así mismo
        this.root[v]=v;
    }


    /**
     * Find the set that has vertex v
     * @param v vertex v
     * @return the set that has vertex v
     * 
     */
    public int findSet(int v){
        //int result = v;
        if(this.root[v] == v){ //apunta así mismo
            return v;
        }else{
            //result = this.findSet(this.root[v]);
            return this.findSet(this.root[v]);
        }
        //return resultado;
    }
    /**
     * The vertex(root) that has the largest set will be the root of the new set. 
     * 
     * @param v1 initial vertex
     * @param v2 end vertex
     * 
     */
    public void union(int v1, int v2){
        int s1 = this.findSet(v1); // represent the 
        int s2 = this.findSet(v2);
        if (this.range[s1] < this.range[s2]) {
            this.root[s1] = s2;
        } else if (this.range[s1] > this.range[s2]) {
            this.root[s2] = s1;
        } else { // case where we have the same size and no matter how we choose
            this.root[s2] = s1; 
            this.range[v1] += 1;
        }
    }
}   