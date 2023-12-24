package com.company.numberguessinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private TextView textViewRight, textViewLeft, textViewHint;
    private Button buttonSubmit;
    private EditText editTextGuess;
    Random r=new Random();
    int random;
    int remainingChances=5;
    boolean twoDigits, threeDigits, fourDigits;

    ArrayList<Integer> guessList=new ArrayList<>();
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textViewRight=findViewById(R.id.textViewRight);
        textViewLeft=findViewById(R.id.textViewLeft);
        textViewHint=findViewById(R.id.textViewHint);
        buttonSubmit=findViewById(R.id.buttonSubmit);
        editTextGuess=findViewById(R.id.editTextGuess);

        twoDigits= getIntent().getBooleanExtra("one", false);
        threeDigits=getIntent().getBooleanExtra("two",false);
        fourDigits=getIntent().getBooleanExtra("three",false);

        if (twoDigits){
            random= r.nextInt(90)+10;
            int randomLow=random-6;
            int randomUp=random+9;
            textViewHint.setText("HINT! The number lies between "+randomLow+" & "+randomUp);
        }
        if (threeDigits){
            random= r.nextInt(900)+100;
            int randomLow=random-10;
            int randomUp=random+19;
            textViewHint.setText("HINT! The number lies between "+randomLow+" & "+randomUp);
        }
        if (fourDigits){
            random= r.nextInt(9000)+1000;
            int randomLow=random-11;
            int randomUp=random+9;
            textViewHint.setText("HINT! The number lies between "+randomLow+" & "+randomUp);
        }

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess=editTextGuess.getText().toString();
                if (guess==""){
                    Toast.makeText(GameActivity.this,"Please enter your Guess First!",Toast.LENGTH_LONG).show();
                }
                else {

                    textViewRight.setVisibility(View.VISIBLE);
                    textViewLeft.setVisibility(View.VISIBLE);
                    count++;
                    remainingChances--;

                    int userGuess=Integer.parseInt(guess);
                    guessList.add(userGuess);

                    textViewLeft.setText("Your guess: "+guess);
                    textViewRight.setText("Remaining Chances "+remainingChances);

                    if (random==userGuess){
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("GREAT JOB! My Fav Number is "+random
                                      +"\n\n You guessed it in "+count+" Chances."+"\n\n Your  Guesses: "+guessList
                                      +"\n\n Would you like to Play Again?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();

                    }
                    if (random>userGuess){
                        textViewHint.setText("Close! Try increasing your guess");
                    }
                    if (random<userGuess){
                        textViewHint.setText("Close! Try decreasing your guess");
                    }

                    if (remainingChances==0){
                        AlertDialog.Builder builder=new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("Number Guessing Game");
                        builder.setCancelable(false);
                        builder.setMessage("OOPS! you are out of guesses." +"\n\n My Fav Number was "+random
                                +"\n\n Your  Guesses: "+guessList
                                +"\n\n Would you like to Play Again?");

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(GameActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();

                    }
                    editTextGuess.setText("");
                }
            }
        });
    }
}