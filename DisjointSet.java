import java.util.Arrays;

public class DisjointSet {
    int size;//number of elements
    int [] parents; //parents[p] points to p parent, thus if p= parents[p] then p is its own parent
    int[] s;
    public DisjointSet(int size){
        if(size<=0){
            System.out.println("Size cannot be less than 1");
            return;
        }
        this.size = (size*size)+5;
        s = new int[this.size];
        parents= new int[this.size];
        for (int S=0; S<s.length;S++) {
            parents[S]=S;
            s[S]=1;
        }
    }
    public void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);
        if(node1==node2) {
            return;
        }
        if (s[node2] < s[node1]) {
        // node2 is larger, because it is more negative
            parents[node1] = node2;
            s[node2] += s[node1]; // add the size from node1 to node2
        } else {
        // node1 is equal or larger
            parents[node2] = node1;
            s[node1] += s[node2]; // add the size from node2 to node1
        }
    }
    public int find(int node){
        if(parents[node]!=node){
            parents[node]=find(parents[node]);
        }
        return parents[node];
    }
}
