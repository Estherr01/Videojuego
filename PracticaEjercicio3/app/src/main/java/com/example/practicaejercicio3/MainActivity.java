package com.example.practicaejercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declaramos las variables
    RadioButton rb1,rb2,rb3;
    RadioGroup idRadioG;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb1=(RadioButton) findViewById(R.id.rb1);
        rb2=(RadioButton) findViewById(R.id.rb2);
        rb3=(RadioButton) findViewById(R.id.rb3);
        idRadioG=(RadioGroup) findViewById(R.id.idRadioG);
        button=(Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rb1.isChecked()){
                    Toast.makeText(MainActivity.this, "Has seleccionado la priemra opcion", Toast.LENGTH_SHORT).show();
                } else if (rb2.isChecked()) {
                    Toast.makeText(MainActivity.this, "Has seleccionado la segunda opcion", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Has selecciona la tercera opcion ", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}