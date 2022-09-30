package com.example.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // 0: 0, 1: X
    int playerActive = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},
                                {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};
    boolean isGameActive = true;
    int count;

    @SuppressLint("SetTextI18n")
    public void onClickImage(View view)  {
        ImageView img = (ImageView)view;
        img.setTranslationY(-1000f);

        int tappedImage = Integer.parseInt(img.getTag().toString());

        if(gameState[tappedImage] == 2 && isGameActive) {
            count++;
            gameState[tappedImage] = playerActive;
            if (playerActive == 0) {
                playerActive = 1;
                img.setImageResource(R.drawable.oo);

                TextView status = findViewById(R.id.textViewTurn);
                status.setText("X's Turn");
            } else {
                playerActive = 0;
                img.setImageResource(R.drawable.xx);

                TextView status = findViewById(R.id.textViewTurn);
                status.setText("0's Turn");
            }
            img.animate().translationYBy(1000).setDuration(200);
        }
        int draw = 1;
        for(int[] winningPosition: winningPositions){
            if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                    && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                    && gameState[winningPosition[0]] != 2){
                String winner;
                draw = 0;
                isGameActive = false;
                if(gameState[winningPosition[0]] == 0){
                    winner = "0 Is Win";
                } else {
                    winner = "X Is Win";
                }
                TextView status = findViewById(R.id.textViewTurn);
                status.setText(winner);

                Button playAgainButton = findViewById(R.id.button);
                playAgainButton.setVisibility(View.VISIBLE);
            }

            if (count == 9 && draw ==1){
                TextView status = findViewById(R.id.textViewTurn);
                status.setText("Match Is Draw");

                Button playAgainButton = findViewById(R.id.button);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void onClickButton(View view){
        isGameActive = true;
        playerActive = 0;
        count = 0;

        Arrays.fill(gameState, 2);

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView01)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        Button playAgainButton = findViewById(R.id.button);
        playAgainButton.setVisibility(View.INVISIBLE);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playAgainButton = findViewById(R.id.button);
        playAgainButton.setVisibility(View.INVISIBLE);
    }
}