package com.catata.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TEXTO = "Texto";

    EditText editText;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //addPreferencesFromResource(R.xml.settings);


        editText = (EditText) findViewById(R.id.etTexto);

        recuperar(null);


        /*Para obtener la referencia a las preferencias compartidas*/
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this /* Activity context */);
        String name = sharedPreferences.getString("edit_text_preference_1", "");

        Log.i("PREFERENCIA", name);

        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                int a =0;
                a=a+1;
                Map<String,?> preferencias = prefs.getAll();
                String s = preferencias.get(key).toString();
            }
        };

        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardar(View view) {

        /*Preferencias guardadas en en archivo común*/
        /*SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXTO, editText.getText().toString());
        editor.commit(); //Devuelve true o false en función de si ha ido o no OK
        */

        /*Fin Preferencias en archivo común*/


        /*Preferencias guardadas en archivo específico*/
        SharedPreferences miInfo = getApplicationContext().getSharedPreferences("INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = miInfo.edit();
        editor1.putString(TEXTO, editText.getText().toString());
        editor1.apply(); //Es asíncrono no devuelve true o false. También podemos usar commit()
    }


    public void recuperar(View view) {

        /*SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String texto = sharedPreferences.getString(TEXTO, "default");*/


        SharedPreferences sharedPreferences = this.getSharedPreferences("INFO",Context.MODE_PRIVATE);
        String texto = sharedPreferences.getString(TEXTO, "default");

        editText.setText(texto);
    }

    public void goNext(View view) {
        Intent i = new Intent(this,MainActivity2.class);

        startActivity(i);
    }
}