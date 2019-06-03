package app2.linuxduck.com.mineswept;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;

public class MainActivity extends AppCompatActivity {
    private Game game;
    private ImageButton five;
    private ImageButton ten;
    private ImageButton twenty;
    private ImageButton fifty;
    private ImageButton onehundred;
    private ImageButton onefifty;
    private ImageButton twofifty;
    private ImageButton fivehundred;
    private Button back;
    private Spinner difficulty;
    private double diff;
    private Context context;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);

        context = getApplicationContext();
        layout = findViewById(R.id.boardtemp);

        five = findViewById(R.id.five);
        ten = findViewById(R.id.ten);
        twenty = findViewById(R.id.twenty);
        fifty = findViewById(R.id.fifty);
        onehundred = findViewById(R.id.onehundred);
        onefifty = findViewById(R.id.onefifty);
        twofifty = findViewById(R.id.twofifty);
        fivehundred = findViewById(R.id.fivehundred);
        //back = findViewById(R.id.back);
        difficulty = (Spinner) findViewById(R.id.spindiff);
        diff = .1;
        String[] difficulties = getResources().getStringArray(R.array.difficultyarr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_spinner_item, difficulties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);

        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String choice = (String)parent.getItemAtPosition(pos);
                if(choice.equals("Easy"))
                    diff = .1;
                if(choice.equals("Medium"))
                    diff = .2;
                if(choice.equals("Hard"))
                    diff = .3;
                if(choice.equals("Very Hard"))
                    diff = .4;
                if(choice.equals("Woah"))
                    diff = .5;
                if(choice.equals("Jeeze"))
                    diff = .6;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff,5);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff, 10);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });

        twenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff, 15);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });

        fifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff, 20);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });

        onehundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff, 25);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });

        onefifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff, 30);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });

        twofifty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff, 40);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });

        fivehundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.GONE);
                game = new Game(diff, 50);
                visibleBoard(game.getBoard());
                findViewById(R.id.boardtemp).setVisibility(View.VISIBLE);
            }
        });
/*        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.sizes).setVisibility(View.VISIBLE);
                findViewById(R.id.boardtemp).setVisibility(View.GONE);
                save();
            }
        });*/
    }
    public void save(){

    }
    public void visibleBoard(ArrayList<ArrayList<Tile>> board){
        layout.removeAllViews();
        for(int i = 0; i < game.getSize(); i++){
            LinearLayout column = new LinearLayout(this);
            column.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            column.setOrientation(LinearLayout.VERTICAL);
            for(int j = 0; j < game.getSize(); j++){
                final int a = i;
                final int b = j;
                ImageView tile = new ImageView(this);
                if(game.getBoard().get(i).get(j).getVisited())
                    tile.setImageDrawable(getNumberedTile(game.getBoard().get(i).get(j).getNum()));
                else
                    tile.setImageDrawable(getNumberedTile(10));
                if(!game.getBoard().get(a).get(b).getVisited()) {
                    tile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (game.getBoard().get(a).get(b).getNum() == 0)
                                game.clear(a, b);
                            else if (game.getBoard().get(a).get(b).getNum() == 9)
                                game.setLost();
                            game.getBoard().get(a).get(b).setVisited();
                            visibleBoard(game.getBoard());
                        }
                    });
                }
                column.addView(tile);
            }
            layout.addView(column);
        }
    }
    public Drawable getNumberedTile(int num){
        if(num == 1)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.one, null);
        if(num == 2)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.two, null);
        if(num == 3)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.three, null);
        if(num == 4)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.four, null);
        if(num == 5)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.five, null);
        if(num == 6)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.six, null);
        if(num == 7)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.seven, null);
        if(num == 8)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.eight, null);
        if(num == 9)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.bomb, null);
        if(num == 10)
            return ResourcesCompat.getDrawable(getResources(), R.drawable.tile, null);
        return ResourcesCompat.getDrawable(getResources(), R.drawable.blank, null);
    }
}
