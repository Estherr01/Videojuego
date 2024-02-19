package com.example.practicaimagenes8_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean fotoActual=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView= findViewById(R.id.imageView2);
        Button button= findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fotoActual){
                    imageView.setImageResource(R.drawable.ic_launcher_background);
                }else{
                 imageView.setImageResource(R.drawable.ic_launcher_foreground);
                }
                fotoActual=!fotoActual;
            }
        });

    }
}