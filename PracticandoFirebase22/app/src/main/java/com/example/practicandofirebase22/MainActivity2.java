package com.example.practicandofirebase22;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    private FirebaseFirestore fireBaseFirestore;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       mAuth = FirebaseAuth.getInstance();
        fireBaseFirestore = FirebaseFirestore.getInstance();
        Toast.makeText(this, FirebaseAuth.getInstance().toString(), Toast.LENGTH_SHORT).show();

        Button button2= findViewById(R.id.button3);

       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Map<String,Object> añadirDatos= new HashMap<>();
               añadirDatos.put("Nombre", "Rubén");
               añadirDatos.put("Apellidos", Arrays.asList("Sanz","Guzmán","Pérez"));
               añadirDatos.put("Edad", 22);
               fireBaseFirestore.collection("Datos2").document("Practicando").set(añadirDatos);
           }
       });



    }
}