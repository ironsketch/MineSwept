package app2.linuxduck.com.mineswept;

public class Tile {
    private int num;
    private boolean visited;
    Tile(int num){
        this.num = num;
        this.visited = false;
    }
    void setNum(int n){this.num = n;}
    void setVisited(){this.visited = true;}
    int getNum(){return this.num;}
    boolean getVisited(){return this.visited;}
}
