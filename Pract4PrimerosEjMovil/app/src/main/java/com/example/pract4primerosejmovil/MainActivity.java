package com.example.pract4primerosejmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CheckBox checkBox= findViewById(R.id.checkBox);
        CheckBox checkBox1= findViewById(R.id.checkBox2);
        CheckBox checkBox2= findViewById(R.id.checkBox3);
        Button button= findViewById(R.id.button);
        TextView textView= findViewById(R.id.textView);
        TextView textView1= findViewById(R.id.textView2);
        TextView textView2= findViewById(R.id.textView3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    textView.setText("opcion 1");
                }else{
                    textView.setText("");
                }if (checkBox1.isChecked()){
                    textView1.setText("opcion 2");
                }else {
                    textView1.setText("");
                }if (checkBox2.isChecked()){
                    textView2.setText("opcion 3");
                }else{
                    textView2.setText("");
                }
            }
        });
    }
}