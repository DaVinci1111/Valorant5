package com.example.valorant5;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;


@Database(entities = { Habit.class }, version = 2)
@TypeConverters(ConversorLocalDate.class)
public abstract class HabitsDatabase extends RoomDatabase {


    public abstract HabitDao habitDao();

    private static volatile HabitsDatabase INSDB;


    @Dao
    public interface HabitDao {
        @Query("SELECT * FROM habit")
        LiveData<List<Habit>> obtener();

        @Insert
        void insertar(Habit habit);

        @Update
        void actualizar(Habit habit);

        @Delete
        void eliminar(Habit habit);

    }

    public static synchronized HabitsDatabase obtenerInstancia(final Context context) {
        if (INSDB == null) {
            synchronized (HabitsDatabase.class) {
                if (INSDB == null) {
                    INSDB = Room.databaseBuilder(context,
                                   HabitsDatabase.class, "habits.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSDB;
    }

}


