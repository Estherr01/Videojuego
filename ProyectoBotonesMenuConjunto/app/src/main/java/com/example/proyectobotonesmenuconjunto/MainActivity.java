package com.example.proyectobotonesmenuconjunto;
// Paquete: com.example.tuapp
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        // Configura el Navigation Drawer
        NavigationViewSetup.setupWithNavController(this, drawerLayout);

        // Configura el Bottom Navigation
        BottomNavigationSetup.setupWithNavController(this);

        // Configura la barra de acción para mostrar el botón de navegación
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavigationDrawerHandler.openDrawer(drawerLayout);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (NavigationDrawerHandler.isDrawerOpen(drawerLayout)) {
            NavigationDrawerHandler.closeDrawer(drawerLayout);
        } else {
            super.onBackPressed();
        }
    }
}
