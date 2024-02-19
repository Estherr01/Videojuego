package com.example.practica_firebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.AggregateQuery;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentChange.Type;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.PersistentCacheSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.database.collection.*;
import com.google.firestore.v1.WriteResult;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private  FirebaseFirestore fireBaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Log.i("prueba", FirebaseAuth.getInstance().toString());
        fireBaseFirestore = FirebaseFirestore.getInstance();

        EditText editText = findViewById(R.id.editTextText);
        EditText editText1 = findViewById(R.id.editTextTextPassword);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button2);
        Button button2 = findViewById(R.id.button3);
        TextView textView = findViewById(R.id.textView);
        Toast.makeText(this, FirebaseAuth.getInstance().toString(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(MainActivity.this, "Usuario creado correctamnte", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Usuario no creado", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Toast.makeText(MainActivity.this, "Funciona", Toast.LENGTH_SHORT).show();
            }
        });

        // Establece un escuchador para el evento de clic en el botón llamado "button1".
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el texto ingresado en el campo de texto llamado "editText" y lo guarda en la variable "usuario".
                String usuario = editText.getText().toString();

                // Obtiene el texto ingresado en el campo de texto llamado "editText1" y lo guarda en la variable "contraseña".
                String contraseña = editText1.getText().toString();

                // Utiliza el servicio de autenticación de Firebase para intentar iniciar sesión con el correo electrónico y la contraseña proporcionados.
                mAuth.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
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


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea un nuevo objeto Map<String, Object> llamado "datos" para almacenar información.
                Map<String, Object> datos = new HashMap<>();

                // Agrega una entrada al mapa con la clave "nombre" y el valor "Esther".
                datos.put("nombre", "Esther");

                // Agrega una entrada al mapa con la clave "apellidos" y una lista de apellidos ("Sanz", "Sánchez").
                datos.put("apellidos", Arrays.asList("Sanz", "Sánchez"));

                // Agrega una entrada al mapa con la clave "poblacion" y el valor "Belmonte de Tajo".
                datos.put("poblacion", "Belmonte de Tajo");

                // Agrega una entrada al mapa con la clave "provincia" y el valor "Madrid".
                datos.put("provincia", "Madrid");

                // Obtiene una referencia a la colección "prueba1" y al documento con ID "prueba1" en Firestore,
                // y establece los datos del mapa en ese documento.
                fireBaseFirestore.collection("prueba1").document("prueba1").set(datos);
            }
        });

        // CONSULTAS
        // Se accede a la colección "prueba1" en Firestore
        fireBaseFirestore.collection("prueba1")

                // Se aplica un filtro para buscar documentos cuyo campo "apellidos" contenga al menos uno de los valores especificados ("Sanz" o "Sánchez")
                .whereArrayContainsAny("apellidos", Arrays.asList("Sanz", "Sánchez"))

                // Se ejecuta la consulta y se obtiene un objeto Task que representa el estado de la operación
                .get()

                // Se agrega un listener para manejar el resultado de la consulta
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Si la consulta fue exitosa, se itera sobre los resultados obtenidos
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                // Se registra en el log la información de cada documento (ID y datos)
                                Log.i("TAG", documentSnapshot.getId() + " => " + documentSnapshot.getData());
                            }
                        } else {
                            // Si hay un error en la consulta, se registra el mensaje de error en el log
                            Log.i(TAG, "Error: " + task.getException());
                        }
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
