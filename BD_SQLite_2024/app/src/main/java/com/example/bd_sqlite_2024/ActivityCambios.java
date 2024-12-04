package com.example.bd_sqlite_2024;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import database.EscuelaBD;

public class ActivityCambios extends Activity {

    EditText cajaNumC, cajaNombre, cajaEdad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        cajaNumC = findViewById(R.id.cajaNumeroControlCambios);
        cajaNombre = findViewById(R.id.cajaNombreCambios);
        cajaEdad = findViewById(R.id.cajaEdadCambios);

    }//ON CREATE

    public void cambiarAlumno(View v){
        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                bd.alumnoDAO().updateAlumnoByID(cajaNombre.getText().toString(), (byte)Integer.parseInt(cajaEdad.getText().toString()), cajaNumC.getText().toString());
                //Log.i("MSJ->", "Eliminado Correctamente");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getBaseContext(),"Eliminado Correctamente", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }
}
