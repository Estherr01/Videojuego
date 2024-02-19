package com.example.buscaminasentrega;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buscaminasentrega.R;

public class ConfigActivity  extends AppCompatActivity {

    EditText nBombsText;
    RadioGroup rDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nBombsText = findViewById(R.id.et_number_bombs);
        rDifficulty = findViewById(R.id.radio_difficulty);

        loadPrefs();

        rDifficulty.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.easy){
                nBombsText.setText("6");
            }else if (checkedId == R.id.normal){
                nBombsText.setText("10");
            }else if (checkedId == R.id.difficult){
                nBombsText.setText("14");
            }
        });
    }

    public void savePrefs(View view){

        int idSelected = rDifficulty.getCheckedRadioButtonId();
        RadioButton selected = findViewById(idSelected);
        String difficultyValue = selected.getContentDescription().toString();

        int nBombs = Integer.parseInt(nBombsText.getText().toString());

        boolean isValid = false;

        switch (difficultyValue){
            case "easy":
                if(nBombs > 0 && nBombs < 25)
                    isValid = true;
                break;
            case "normal":
                if(nBombs > 0 && nBombs < 64)
                    isValid = true;
                break;
            case "difficult":
                if(nBombs > 0 && nBombs < 144)
                    isValid = true;
                break;
        }

        if(isValid){
            SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit(); //editor de preferencias
            editor.putInt("bombs",nBombs);
            editor.putString("difficult",difficultyValue);
            editor.commit();

            Toast toast = Toast.makeText(getApplicationContext(), "Changes saved", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "Changes not saved (number of bombs not valids)", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
    }

    public void loadPrefs(){
        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);

        int bombas = preferences.getInt("bombs",10);
        String difficultyValue = preferences.getString("difficult","normal");
        nBombsText.setText(bombas+"");

        RadioButton seleccionar;

        switch (difficultyValue){
            case "easy":
                seleccionar = findViewById(R.id.easy);
                seleccionar.setChecked(true);
                break;
            case "normal":
                seleccionar = findViewById(R.id.normal);
                seleccionar.setChecked(true);
                break;
            case "difficult":
                seleccionar = findViewById(R.id.difficult);
                seleccionar.setChecked(true);
                break;
            default:
                break;
        }
    }

    public void finish(View view){
        finish();
    }
}
