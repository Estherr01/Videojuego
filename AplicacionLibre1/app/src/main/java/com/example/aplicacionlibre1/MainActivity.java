package com.example.aplicacionlibre1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText password = findViewById(R.id.editPassword);
        EditText password1 = findViewById(R.id.editPassword2);
        Button button = findViewById(R.id.button2);
        TextView textView1 = findViewById(R.id.text3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordText = password.getText().toString(); // Obtén la contraseña 1
                String password1Text = password1.getText().toString(); // Obtén la contraseña 2

                if (passwordText.equals(password1Text)) {
                    textView1.setText(R.string.pass_correcta);//Contaseña 1 tiene que se igual a contraseña 2
                } else {
                    textView1.setText(R.string.pass_incorrecta);
                }
            }
        });
    }
}