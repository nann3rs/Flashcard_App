package com.srabe.flashcard_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.floatingActionButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //setting onClickListener for Save button
        findViewById(R.id.floatingActionButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //grab inputted text from EditText views as Strings
                String question = ((EditText)findViewById(R.id.editText)).getText().toString();
                String answer2 = ((EditText)findViewById(R.id.editText2)).getText().toString();

                //putting the two string into an Intent
                Intent data = new Intent();
                data.putExtra("string1", question);
                data.putExtra("string2", answer2);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
