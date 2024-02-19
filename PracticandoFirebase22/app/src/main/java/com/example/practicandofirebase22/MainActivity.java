package com.example.practicandofirebase22;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        Log.i("probando", FirebaseAuth.getInstance().toString());

        firebaseFirestore = FirebaseFirestore.getInstance();

        EditText editText = findViewById(R.id.IdEmailAddress);
        EditText editText1 = findViewById(R.id.IdPassword);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button2);

        Toast.makeText(this, FirebaseAuth.getInstance().toString(), Toast.LENGTH_SHORT).show();
        Button btnIrAActividad2 = findViewById(R.id.btnIrAActividad2);

        btnIrAActividad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un Intent para abrir la nueva actividad
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Inicia la nueva actividad
                startActivity(intent);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editText.getText().toString();
                String contra = editText1.getText().toString();
                firebaseAuth.createUserWithEmailAndPassword(usuario, contra).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Operación de creación de usuario exitosa, ahora puedes iniciar la nueva actividad.
                            Toast.makeText(MainActivity.this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();

                            // Mueve estas líneas aquí para que se ejecuten después de que el usuario se haya creado.
                            //Intent pasa = new Intent(MainActivity.this, MainActivity2.class);
                            //startActivity(pasa);
                        } else {
                            Toast.makeText(MainActivity.this, "Usuario no creado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Estas líneas se mueven aquí o pueden ejecutarse independientemente de la operación de creación de usuario.
                Toast.makeText(MainActivity.this, "Funciona", Toast.LENGTH_SHORT).show();
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editText.getText().toString();
                String contra = editText1.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(usuario, contra).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Verifica si la operación de inicio de sesión fue exitosa.
                        if (task.isSuccessful()) {
                            // Muestra un mensaje de tostada indicando que el usuario ha iniciado sesión correctamente.
                            Toast.makeText(MainActivity.this, "Logeado", Toast.LENGTH_SHORT).show();

                        } else {
                            // Muestra un mensaje de tostada indicando que la contraseña o el usuario son incorrectos.
                            Toast.makeText(MainActivity.this, "Contraseña o usuario erroneo", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
    @Override
    public void onStart () {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
