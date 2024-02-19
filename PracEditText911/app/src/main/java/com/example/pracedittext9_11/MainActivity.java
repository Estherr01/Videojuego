package com.example.pracedittext9_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText= findViewById(R.id.editText);
        EditText editText1= findViewById(R.id.editText2);
        EditText editText2= findViewById(R.id.editTextPhone);
        Button button= findViewById(R.id.button);
        TextView textView= findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= editText.getText().toString();
                String dni= editText1.getText().toString();
                String telefono= editText2.getText().toString();
                String mensaje= "El nombre " + nombre+ "tiene el siguiente dni"
                        + dni+ " y el siguiente telefono"+ telefono;

                textView.setText(mensaje);
            }
        });


    }
}