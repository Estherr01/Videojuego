package com.example.rbuttons15_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup= findViewById(R.id.radioG);
        RadioButton radioButton= findViewById(R.id.radioButton1);
        RadioButton radioButton1= findViewById(R.id.radioButton2);
        RadioButton radioButton2= findViewById(R.id.radioButton3);
        Button button= findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioButton.isChecked()){
                    Toast.makeText(MainActivity.this, "Opcion 1", Toast.LENGTH_SHORT).show();
                }else if (radioButton1.isChecked()){
                    Toast.makeText(MainActivity.this, "Opcion 2", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Opcion 3", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}