package com.example.practicaej5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.text1);
        CheckBox checkBox = findViewById(R.id.check1);
        CheckBox checkBox1 = findViewById(R.id.check2);
        CheckBox checkBox2 = findViewById(R.id.check3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado = "";

                if (checkBox.isChecked()) {
                    resultado += "Check 1";
                }
                if (checkBox1.isChecked()) {
                    resultado+="Check 2";
                }

                if(checkBox2.isChecked()){
                    resultado+="Check3";
                }

                if(!checkBox.isChecked()&&!checkBox1.isChecked()&& !checkBox2.isChecked()){
                    textView.setText("Ninguna opcion marcada");
                }else{
                    textView.setText(resultado);
                }
            }
        });
    }
}