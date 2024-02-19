package com.example.firebasedia2_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        Log.i("prueba",FirebaseAuth.getInstance().toString());
        firebaseFirestore= FirebaseFirestore.getInstance();

        EditText editText= findViewById(R.id.idEmail);
        EditText editText1= findViewById(R.id.idContra);
        Button button= findViewById(R.id.button);
        TextView textView= findViewById(R.id.textView);

        Toast.makeText(this,FirebaseAuth.getInstance().toString(), Toast.LENGTH_SHORT).show();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // la contraseña no puede ser menor de 6
                String usuario = editText.getText().toString();
                String contraseña = editText1.getText().toString();
                mAuth.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Usuario creado correctamente
                            textView.setText("Usuario creado correctamente");
                        } else {
                            // Error al crear el usuario
                            String errorMessage = "Usuario no creado: " + task.getException().getMessage();
                            textView.setText(errorMessage);

                            // Imprimir detalles del error en el log
                            Log.e("TAG", "Error al crear el usuario: " + task.getException().getMessage());
                        }
                    }
                });
            }
        });

    }
}