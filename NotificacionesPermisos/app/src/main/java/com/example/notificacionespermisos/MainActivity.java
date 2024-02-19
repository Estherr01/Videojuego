package com.example.notificacionespermisos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//notificaciones

    private static final String CHANNEL_ID = "my_channel_id";
    private MyNotificationManager notificationManager;
    private Button bNotificaciones;
    private NotificationManager banderaclick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //permisos manifest
        //dependencias grandel:app
        // strings
        //RECORDAR DARLE PERMISOS A LA APLICACION DESDE LOS AJUSTES DE LA APLICACION DEL EMULADOR
        //cuando haces click en la notificacion te envia al main

        //notificaciones
        bNotificaciones = findViewById(R.id.bNotificaciones);

        // Inicializamos la instancia de MyNotificationManager
        notificationManager = new MyNotificationManager(this);

        // Configuramos el clic del botón bNotificaciones para mostrar la notificación
        bNotificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Llama al método showNotification de MyNotificationManager
                notificationManager.showNotification("Título de la notificación", "Cuerpo de la notificación", R.drawable.usuario);
            }
        });

        createNotificationChannel();

    }
        //notificaciones
        private void createNotificationChannel () {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                channel.setDescription(description);

                NotificationManager notificationManager = getSystemService(NotificationManager.class); // Use 'getSystemService' directly
                notificationManager.createNotificationChannel(channel);
            }
        }

        public class MyNotificationManager {
            private static final String CHANNEL_ID = "my_channel_id";
            private Context mContext;

            public MyNotificationManager(Context context) {
                mContext = context;
            }

            public void showNotification(String textTitle, String textContent, int imageResourceId) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.notification_icon)
                        .setContentTitle(textTitle)
                        .setContentText(textContent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                // Agregar estilo de imagen grande a la notificación
                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
                bigPictureStyle.bigPicture(BitmapFactory.decodeResource(mContext.getResources(), imageResourceId));
                bigPictureStyle.setBigContentTitle("Título de la imagen");
                bigPictureStyle.setSummaryText("Texto de resumen de la imagen");
            //aqui hace lo de enviarte al main se podria cambiar para que entrara en otro lado
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_IMMUTABLE);

                builder.setContentIntent(pendingIntent);

                builder.setStyle(bigPictureStyle);

                NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        }


    }
