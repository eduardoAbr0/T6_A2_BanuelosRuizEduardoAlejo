package com.example.bd_sqlite_2024;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import database.EscuelaBD;
import entities.Alumno;

public class ActivityConsultas extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Alumno> datos = null;
    EditText cajaNombre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        cajaNombre = findViewById(R.id.caja_Consultas);

        recyclerView = findViewById(R.id.lista_Alumnos);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                datos = (ArrayList<Alumno>) bd.alumnoDAO().mostrarTodos();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new CustomAdapter(datos);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();

    }

    public void consultaNombre(View v){
        EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());

        new Thread(new Runnable() {
            @Override
            public void run() {
                datos = (ArrayList<Alumno>) bd.alumnoDAO().buscarPorNombre(cajaNombre.getText().toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new CustomAdapter(datos);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}//class

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<Alumno> localDatSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        public ViewHolder(View view){
            super(view);
            textView = view.findViewById(R.id.textView_recycle);
        }

        public TextView getTextView(){
            return textView;
        }
    }

    public CustomAdapter(ArrayList<Alumno> dataset){
        localDatSet = dataset;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.textview_recycleview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Alumno alumno = localDatSet.get(position);

        holder.getTextView().setText("Numero Control: "+ alumno.getNumControl() +"\nNombre: "+ alumno.getNombre()+ "\nEdad: "+ alumno.getEdad());
    }

    @Override
    public int getItemCount() {
        return localDatSet.size();
    }
}//CustomAdapter
