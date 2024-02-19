package com.example.aadirdatosfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore fireBaseFirestore;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        fireBaseFirestore = FirebaseFirestore.getInstance();

        EditText editText= findViewById(R.id.editTextTextEmailAddress);
        EditText editText1= findViewById(R.id.editTextTextPassword);
        Button button= findViewById(R.id.button);
        Button button1= findViewById(R.id.button2);
        Toast.makeText(this, FirebaseAuth.getInstance().toString(), Toast.LENGTH_SHORT).show();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editText.getText().toString();
                String contraseña = editText1.getText().toString();
                mAuth.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Usuario creado correctamnte", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Usuario no creado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un nuevo objeto Map<String, Object> llamado "datos" para almacenar información.
                Map<String, Object> datos = new HashMap<>();

                // Agrega una entrada al mapa con la clave "nombre" y el valor "Esther".
                datos.put("nombre", "Pepa");

                // Agrega una entrada al mapa con la clave "apellidos" y una lista de apellidos ("Sanz", "Sánchez").
                datos.put("ciudades", Arrays.asList("Mallorca", "Madrid","Londres","Bélgica"));
                // Agrega una entrada al mapa con la clave "provincia" y el valor "Madrid".
                datos.put("provincia", "Madrid");

                // Obtiene una referencia a la colección "prueba1" y al documento con ID "prueba1" en Firestore, y establece los datos del mapa en ese documento.
                fireBaseFirestore.collection("Prueba2").document("probando2").set(datos);
            }
        });






        }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }


}