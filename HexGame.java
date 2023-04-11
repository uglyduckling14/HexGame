import java.util.ArrayList;
import java.util.Objects;

public class HexGame {
    int size;
    int LEFT=124;
    int RIGHT=125;
    int TOP = 122;
    int BOTTOM =123;
    DisjointSet red;
    DisjointSet blue;
    String[] teams;
    public HexGame(int size){
        this.size=size;
        teams = new String[(size*size)+5];
        blue= new DisjointSet(size);
        red = new DisjointSet(size);
        for(int i =0; i<size;i++){//connects edges with top/bottom/left/right
            red.union(i+1, TOP);
            red.union((size*size)-i,BOTTOM);
            blue.union((size*i)+1, LEFT);
            blue.union((size*(i+1)),RIGHT);
        }
    }
    public boolean playBlue(int position, boolean displayNeighbors){
        ArrayList<Integer>neighbors=displayNeighborsRed(position,0);
        //check to see if position is filled
        if(displayNeighbors){
            System.out.println("Cell "+position+":"+ neighbors);
        }
        if(isOccupied(position)){
            return false;
        }
        int i=0;
        while(i< neighbors.size()){
            if(Objects.equals(teams[neighbors.get(i)], "B")){
                blue.union(position, neighbors.get(i));
            }
            i++;
        }
        teams[position]="B";
        blue.union(position,position);
        if(blue.find(LEFT)==blue.find(RIGHT)){
            System.out.println("Blue wins with move at position "+ position);
            return true;
        }
        return false;
    }
    public boolean playRed(int position, boolean displayNeighbors){
        ArrayList<Integer>neighbors=displayNeighborsRed(position,1);
        //check to see if position is filled
        if(displayNeighbors){
            System.out.println("Cell "+position+":"+ neighbors);
        }
        if(isOccupied(position)){
            return false;
        }
        int i=0;
        boolean noNeighbor=true;
        while(i< neighbors.size()){
            if(Objects.equals(teams[neighbors.get(i)], "R")){
                red.union(position, neighbors.get(i));
                teams[position]="R";
                noNeighbor=false;
            }
            i++;
        }
        if(noNeighbor){
            teams[position]="R";
        }
        if(red.find(TOP)==red.find(BOTTOM)){
            System.out.println("Red wins at position "+red.find(TOP));
            return true;
        }
        return false;
    }
    private ArrayList<Integer> displayNeighborsRed(int position, int color){
        //color=red if color=1;
        //color=blue if color=0;
        boolean notTop = position>=size+1;
        boolean notBottom = position<(size*(size-1))+1;
        boolean notLeft = position%size!=1;
        boolean notRight = position%size!=0;
        ArrayList<Integer> neighborsRed= new ArrayList<>();
        if(!notTop){
            if(color==1){
                neighborsRed.add(TOP);
            }
        }else if(!notBottom && color==1){
            neighborsRed.add(BOTTOM);
        }
        if(!notLeft && color==0){
            neighborsRed.add(LEFT);
        }
        if(!notRight && color==0){
            neighborsRed.add(RIGHT);
        }
        if(notTop){
            neighborsRed.add(position-11);
        }
        if(notBottom){
            neighborsRed.add(position+11);
        }
        if(notLeft){
            neighborsRed.add(position-1);
        }
        if(notLeft && notBottom){
            neighborsRed.add(position+10);
        }
        if(notRight){
            neighborsRed.add(position+1);
        }
        if(notRight && notTop){
            neighborsRed.add(position-10);
        }
        return neighborsRed;
    }
    private boolean isOccupied(int position){
        return Objects.equals(getGrid(position), "R")||Objects.equals(getGrid(position), "B");
    }
    public String getGrid(int value){
        return teams[value];
    }
}
