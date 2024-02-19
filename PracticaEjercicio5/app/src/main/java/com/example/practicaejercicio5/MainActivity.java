package com.example.practicaejercicio5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.textV1);
        CheckBox check1 = findViewById(R.id.check1);
        CheckBox check2 = findViewById(R.id.check2);
        CheckBox check3 = findViewById(R.id.check3);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String resultado = "";

                if (check1.isChecked()) {
                    resultado += "Check 1";
                }
                if (check2.isChecked()) {
                    resultado += "Check 2";
                }
                if (check3.isChecked()) {
                    resultado += "Check 3";
                }

                if (!check1.isChecked() && !check2.isChecked() && !check3.isChecked()) {

                    text.setText("Ningun check marcado");
                } else {
                    text.setText(resultado);
                }

            }
        });
    }
}