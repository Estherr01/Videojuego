package com.example.menuflotante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        }
    @Override
    public boolean onCreateOptionsMenu(menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_item1:
                Toast.makeText(this, "Opcion 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_item2:
                Toast.makeText(this, "Opcion 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_item3:
                Toast.makeText(this, "Opcion 1", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }