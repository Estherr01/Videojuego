package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



private RadioButton idRadio1,idRadio2,idRadio3;
private RadioGroup idRgroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Creamos variables de un tipo y las damos el valor
        idRadio1=(RadioButton)findViewById(R.id.idRadio1);
        idRadio2=(RadioButton)findViewById(R.id.idRadio2);
        idRadio3=(RadioButton) findViewById(R.id.idRadio3);
        idRgroup=(RadioGroup) findViewById(R.id.idRgroup);

        //Listener
        idRgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(idRadio1.isChecked() == true){
                    Toast.makeText(MainActivity.this, "Has pulsado la primera opcion", Toast.LENGTH_SHORT).show();
                } else if (idRadio2.isChecked()== true) {
                    Toast.makeText(MainActivity.this, "Has pulsado la segunda opcion", Toast.LENGTH_SHORT).show();
                } else if (idRadio3.isChecked()==true) {
                    Toast.makeText(MainActivity.this, "Has pulsado la tercera opcion", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "No has pulsado ninguna opcion", Toast.LENGTH_SHORT).show();
                }


            }
        });
    };

    }






