package entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alumno {
    @PrimaryKey
    @NonNull
    public String numControl;

    @NonNull
    @ColumnInfo(name="Nombre")
    public String nombre;

    @NonNull
    @ColumnInfo(name = "Edad")
    public byte edad;

    public Alumno(@NonNull String numControl, @NonNull String nombre, byte edad) {
        this.numControl = numControl;
        this.nombre = nombre;
        this.edad = edad;
    }

    @NonNull
    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(@NonNull String numControl) {
        this.numControl = numControl;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }
}
