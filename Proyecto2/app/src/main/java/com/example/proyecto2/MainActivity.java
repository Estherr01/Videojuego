package com.example.proyecto2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mostrar=findViewById(R.id.boton1);

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CrearNotificacion("Titulo","Contenido","Politicas", NotificationCompat.PRIORITY_HIGH,100);



            }

            //Le pasas los parametros que quieras, entre ellos ahora, se esta pasando la prioridad y el numero de notificacion



        });


    }
    public void CrearNotificacion(String titulo,String contenido,String channedID,int prioridad, int notificacionid){

        Intent intent = new Intent(this, clasenotificacion.class);
        intent.setAction(intent.ACTION_VIEW);
        PendingIntent pendingIntent =PendingIntent.getActivity(this, notificacionid,intent,PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notificationBuilder=
                new NotificationCompat.Builder(getApplicationContext(),channedID)
                        .setSmallIcon(R.drawable.notificacion)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.fondonoti))
                        .setAutoCancel(true)
                        .setVibrate(new long[]{500,500,500})
                        .setPriority(prioridad)
                        .setContentTitle(titulo)
                        .setContentText(contenido)
                        .setContentIntent(pendingIntent)
                        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

                            NotificationChannel channel = new NotificationChannel(channedID,channedID, NotificationManager.IMPORTANCE_HIGH);
                            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                            notificationManager.createNotificationChannel(channel);

                        }
                        Notification notification = notificationBuilder.build();
                        notificationManager.notify(notificacionid,notification);





    }
}