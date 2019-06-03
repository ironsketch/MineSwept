package app2.linuxduck.com.mineswept;

import android.util.Log;

import java.util.ArrayList;

public class Game {
    private double difficulty;
    private int size;
    private int reveals;
    private ArrayList<ArrayList<Tile>> board;
    private ArrayList<Pair <Integer, Integer>> bombs;
    private boolean won;
    private boolean lost;
    Game(double difficulty, int size){
        this.difficulty = difficulty;
        this.size = size;
        this.reveals = 0;
        this.won = false;
        this.lost = false;
        this.board = new ArrayList<ArrayList<Tile>>();
        this.bombs = new ArrayList<Pair <Integer, Integer>>();
        buildBoard();
    }
    void buildBoard(){
        // Setting how many tiles need to be cleared
        this.reveals = (this.size * this.size) - this.bombs.size();

        // Setting up columns and blank tiles
        for(int i = 0; i < this.size; i++){
            this.board.add(new ArrayList<Tile>());
            for(int j = 0; j < this.size; j++){
                Tile t = new Tile(0);
                this.board.get(i).add(t);
            }
        }

        // Creating bomb locations
        for(int i = 0; i < (this.size * this.size * this.difficulty); i++){
            int a = (int)(Math.random() * this.size);
            int b = (int)(Math.random() * this.size);
            Pair<Integer, Integer> tmp = new Pair<Integer, Integer>(a, b);
            this.bombs.add(tmp);
        }

        // Setting bombs
        for(int i = 0; i < this.bombs.size(); i++) {
            Log.e("b","b");
            this.board.get(this.bombs.get(i).a).get(this.bombs.get(i).b).setNum(9);
        }

        // Setting numbers
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                int bombCount = 0;
                // Check LEFT TOP
                if(i > 0 && j > 0)
                    if(this.board.get(i - 1).get(j - 1).getNum() == 9)
                        bombCount++;
                // Check TOP
                if(i > 0)
                    if(this.board.get(i - 1).get(j).getNum() == 9)
                        bombCount++;
                // Check TOP RIGHT
                if(i > 0 && j < size - 1)
                    if(this.board.get(i - 1).get(j + 1).getNum() == 9)
                        bombCount++;
                // Check RIGHT
                if(j < size - 1)
                    if(this.board.get(i).get(j + 1).getNum() == 9)
                        bombCount++;
                // Check RIGHT BOTTOM
                if(i < size - 1 && j < size - 1)
                    if(this.board.get(i + 1).get(j + 1).getNum() == 9)
                        bombCount++;
                // Check BOTTOM
                if(i < size - 1)
                    if(this.board.get(i + 1).get(j).getNum() == 9)
                        bombCount++;
                // Check BOTTOM LEFT
                if(i < size - 1 && j > 0)
                    if(this.board.get(i + 1).get(j - 1).getNum() == 9)
                        bombCount++;
                // Check LEFT
                if(j > 0)
                    if(this.board.get(i).get(j - 1).getNum() == 9)
                        bombCount++;
                this.board.get(i).get(j).setNum(bombCount);
            }
        }

    }
    public void clear(int a, int b){

    }
    public void setLost(){this.lost = true;}
    public int getSize(){return this.size;}
    public ArrayList<ArrayList<Tile>> getBoard(){return board;}
}
