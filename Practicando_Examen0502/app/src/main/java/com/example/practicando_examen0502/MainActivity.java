package com.example.practicando_examen0502;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    //private  FirebaseFirestore fireBaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.idContra);
        EditText editText1 = findViewById(R.id.idEmail);
        Button button = findViewById(R.id.button);
        Button boton= findViewById(R.id.idBotonPantalla);

        mAuth = FirebaseAuth.getInstance();

        Log.i("prueba", FirebaseAuth.getInstance().toString());
        Toast.makeText(this, FirebaseAuth.getInstance().toString(), Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editText1.getText().toString();
                String contraseña = editText.getText().toString();

                mAuth.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Usuario no creado", Toast.LENGTH_SHORT).show();
                            Log.e("TAG", "Error al crear usuario: " + task.getException().getMessage());
                        }

                        // Mueve el Toast "Funciona" aquí para que se muestre después de la operación
                        // Toast.makeText(MainActivity.this, "Funciona", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Pantalla2.class);
                startActivity(intent);
            }
        });


    }
}
