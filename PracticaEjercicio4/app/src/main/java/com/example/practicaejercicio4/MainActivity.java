package com.example.practicaejercicio4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioG;
    RadioButton rb1,rb2,rb3;
    Button button;
    TextView text1,text2,text3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioG=(RadioGroup) findViewById(R.id.radioG);

        rb2=(RadioButton) findViewById(R.id.rb2);
        rb3=(RadioButton) findViewById(R.id.rb3);
        text1=(TextView) findViewById(R.id.text1);
        text2=(TextView) findViewById(R.id.text2);
        text3=(TextView) findViewById(R.id.text3);
        button=(Button) findViewById(R.id.button);

       RadioButton radioButton= findViewById(R.id.rb1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rb1.isChecked()){
                    text1.setText("Has pulsado opcion 1");
                }else if (rb2.isChecked()){
                    text2.setText("Has pulsado la opcion 2");
                }else{
                    text3.setText("Has pulsado la opcion 3");
                }
            }
        });

    }
}