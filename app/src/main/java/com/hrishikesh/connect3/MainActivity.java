package com.hrishikesh.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;

    boolean gameIsActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view ;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.red);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningState : winningStates){

                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2 )  {

                    gameIsActive = false;
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    if(gameState[winningState[0]]== 0) {

                        winnerMessage.setText("Red has won the game") ;

                    } else {

                        winnerMessage.setText("Yellow has won the game");
                    }
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);
                }else {

                    boolean draw  = true;
                    for(int counterState : gameState){

                        if(counterState == 2){

                            draw = false;
                        }
                    }
                    if(draw) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("The game is a draw") ;
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }

            }
        }

    }

    public void playAgain(View view) {

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for( int i = 0; i < gameState.length ; i++ ) {

            gameState[i] = 2 ;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for( int i = 0 ; i < gridLayout.getChildCount() ; i ++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
