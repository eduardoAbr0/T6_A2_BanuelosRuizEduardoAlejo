package driver;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entities.Alumno;

@Dao
public interface alumnoDAO {
    //ALTAS
    @Insert
    public void agregarAlumno(Alumno alumno);

    //BAJAS
    @Delete
    public void eliminarAlumno(Alumno alumno);

    @Query("DELETE FROM alumno WHERE numControl=:nc")
    public void deleteByID(String nc);

    //CAMBIOS
    @Update
    public void updateAlumno(Alumno alumno);

    @Query("UPDATE alumno SET nombre=:n, edad=:e WHERE numControl=:nc")
    public void updateAlumnoByID(String n, byte e, String nc);

    //CONSULTAS
    @Query("SELECT * FROM alumno")
    public List<Alumno> mostrarTodos();

    @Query("SELECT * FROM alumno WHERE nombre=:n")
    public List<Alumno> buscarPorNombre(String n);
}
