package com.catata.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.tvTexto);

        recuperarInfo();
    }

    private void recuperarInfo() {
        //Recuperamos un fichero de preferencias compartidas con identificador INFO
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("INFO",MODE_PRIVATE);

        //Lo añadimos a nuestro textView
        textView.setText(preferences.getString(MainActivity.TEXTO,"Default"));
    }
}