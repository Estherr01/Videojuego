package com.example.ejercicio4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioG;
    private RadioButton rb1,rb2,rb3;
    private TextView textV;
    private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioG=(RadioGroup)findViewById(R.id.idRadioG);
        rb1=(RadioButton) findViewById(R.id.RadioBut1);
        rb2=(RadioButton) findViewById(R.id.radioButton2);
        rb3=(RadioButton) findViewById(R.id.radioButton3);
        textV=(TextView) findViewById(R.id.textView);
        bt=(Button) findViewById(R.id.boton);

        //Listener
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.isChecked()){
                    textV.setText("Has seleccionado la primera opción");
                } else if (rb2.isChecked()) {
                   textV.setText("Has seleccionado la segunda opción");
                } else if (rb3.isChecked()) {
                    textV.setText("Has seleccionado la tercera opción");
                }
            }
        });
    }


}