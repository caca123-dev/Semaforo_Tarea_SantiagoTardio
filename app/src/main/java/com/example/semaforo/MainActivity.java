package com.example.semaforo;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private View focoRojo, focoAmarillo, focoVerde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        focoRojo=findViewById(R.id.focoRojo);
        focoAmarillo=findViewById(R.id.focoAmarillo);
        focoVerde=findViewById(R.id.focoVerde);
        Thread hiloSemaforo = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (true){
                        runOnUiThread(() -> setFoco(Color.RED, Color.DKGRAY, Color.DKGRAY));
                        Thread.sleep(5000);
                        runOnUiThread(() -> setFoco(Color.DKGRAY, Color.YELLOW, Color.DKGRAY));
                        Thread.sleep(5000);
                        runOnUiThread(() -> setFoco(Color.DKGRAY, Color.DKGRAY, Color.GREEN));
                        Thread.sleep(5000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        hiloSemaforo.start();
    }
    private void setFoco(int colorRojo, int colorAmarillo, int colorVerde) {
        ((GradientDrawable)focoRojo.getBackground()).setColor(colorRojo);
        ((GradientDrawable)focoAmarillo.getBackground()).setColor(colorAmarillo);
        ((GradientDrawable)focoVerde.getBackground()).setColor(colorVerde);
    }
}