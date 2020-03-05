package com.srabe.flashcard_app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //change colors of the choice made; red = wrong; green = correct
        findViewById(R.id.choice1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.choice1)).setBackgroundColor(getResources().getColor(R.color.red));
                ((TextView)findViewById(R.id.choice3)).setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        findViewById(R.id.choice2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.choice2)).setBackgroundColor(getResources().getColor(R.color.red));
                ((TextView)findViewById(R.id.choice3)).setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        findViewById(R.id.choice3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.choice3)).setBackgroundColor(getResources().getColor(R.color.green));
            }
        });

        //reset quiz
        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.choice1)).setBackgroundColor(getResources().getColor(R.color.beige));
                ((TextView)findViewById(R.id.choice2)).setBackgroundColor(getResources().getColor(R.color.beige));
                ((TextView)findViewById(R.id.choice3)).setBackgroundColor(getResources().getColor(R.color.beige));
            }
        });

        //toggle function
        findViewById(R.id.toggle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView choice1 = (TextView)findViewById(R.id.choice1);
                TextView choice2 = (TextView)findViewById(R.id.choice2);
                TextView choice3 = (TextView)findViewById(R.id.choice3);

                if(choice1.getVisibility()==View.VISIBLE) {
                    choice1.setVisibility(View.INVISIBLE);
                    choice2.setVisibility(View.INVISIBLE);
                    choice3.setVisibility(View.INVISIBLE);
                }else if(choice1.getVisibility()==View.INVISIBLE){
                    choice1.setVisibility(View.VISIBLE);
                    choice2.setVisibility(View.VISIBLE);
                    choice3.setVisibility(View.VISIBLE);
                }
            }
        });

     /*   findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView question = (TextView)findViewById(R.id.flashcard_question);
                TextView answer = (TextView)findViewById(R.id.answer);
                question.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.VISIBLE);

            }
        });

        findViewById(R.id.answer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView answer = (TextView)findViewById(R.id.answer);
                TextView question = (TextView)findViewById(R.id.flashcard_question);
                answer.setVisibility(View.INVISIBLE);
                question.setVisibility(View.VISIBLE);
            }
        });

      */
    }
}
