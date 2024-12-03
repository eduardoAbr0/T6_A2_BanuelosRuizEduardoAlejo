package com.example.bd_sqlite_2024;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import database.EscuelaBD;
import entities.Alumno;

public class ActivityAltas extends Activity {

    EditText cajaNumControl, cajaNombre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        cajaNumControl = findViewById(R.id.cajaNumControl);
        cajaNombre = findViewById(R.id.cajaNombre);

    }//ON CREATE

    public void agregarAlumno(View v){
        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        //Ejecutar la linea en un HILO por separado del Hilo principal de GUI
        new Thread(new Runnable() {
            @Override
            public void run() {
                bd.alumnoDAO().agregarAlumno(new Alumno("01","Sam",(byte)22));
                Log.i("MSJ->", "Insertado Correctamente");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(),"Insertado Correctamente", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
    }

}
