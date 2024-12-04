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

public class ActivityBajas extends Activity {

    EditText cajaNumeroC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        cajaNumeroC = findViewById(R.id.cajaNumControlBaja);

    }//ON CREATE

    public void eliminarAlumno(View v){
        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                bd.alumnoDAO().deleteByID(cajaNumeroC.getText().toString());
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
