package com.srabe.flashcard_app;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();

        if(allFlashcards != null && allFlashcards.size() > 0){
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());
        }
/*
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
*/
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView question = (TextView)findViewById(R.id.flashcard_question);
                TextView answer = (TextView)findViewById(R.id.answer);

                View answerSideView = findViewById(R.id.answer);

                //get the center for the clipping circle
                int cx = answerSideView.getWidth()/2;
                int cy = answerSideView.getHeight()/2;

                //get the final radius for the clipping circle
                float finalRadius = (float)Math.hypot(cx,cy);

                //create the animator for this view
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f,finalRadius);

                //hide the question and show the answer to prepare for playing animation
                question.setVisibility(View.INVISIBLE);
                answer.setVisibility(View.VISIBLE);

                anim.setDuration(300);
                anim.start();

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

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        findViewById(R.id.floatingActionButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //grab current Question and Answer strings
                String stringKey1 = ((TextView)findViewById(R.id.flashcard_question)).getText().toString();
                String stringKey2 = ((TextView)findViewById(R.id.answer)).getText().toString();

                //pass the two strings to AddCardActivity
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("stringKey1", stringKey1);
                intent.putExtra("stringKey2", stringKey2);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //this method is called when the animation is finished playing

                        //set the question and answer TextViews with data from the database
                        ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());

                        findViewById(R.id.flashcard_question).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //we don't need to worry about this method
                    }
                });

                findViewById(R.id.flashcard_question).startAnimation(leftOutAnim);

                //advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                //make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if(currentCardDisplayedIndex > allFlashcards.size() - 1){
                    currentCardDisplayedIndex = 0;
                }
         }
        });

        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            String string1 = data.getExtras().getString("string1");
            String string2 = data.getExtras().getString("string2");

            ((TextView)findViewById(R.id.flashcard_question)).setText(string1);
            ((TextView)findViewById(R.id.answer)).setText(string2);

            flashcardDatabase.insertCard(new Flashcard(string1, string2));
            allFlashcards = flashcardDatabase.getAllCards();
        }

        //snackbar
        Snackbar.make(findViewById(R.id.flashcard_question),
                "Card successfully created",
                Snackbar.LENGTH_SHORT)
                .show();
    }

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
}
