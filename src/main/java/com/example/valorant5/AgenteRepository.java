package com.example.valorant5;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AgenteRepository {
    Executor exec = Executors.newSingleThreadExecutor();
    private HabitsDatabase.HabitDao habitDao;

    public AgenteRepository(Application application){
        habitDao = HabitsDatabase.obtenerInstancia(application).habitDao();
    }

    LiveData<List<Habit>> obtener() {
        resetHabits();
        return habitDao.obtener();
    }


    void insertar(Habit habit){
        exec.execute(new Runnable() {
            @Override
            public void run() {
                habitDao.insertar(habit);
            }
        });
    }

    void eliminar(Habit habit) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                habitDao.eliminar(habit);
            }
        });
    }

    public void actualizar(Habit habit) {
        exec.execute(new Runnable() {
            @Override
            public void run() {
                habitDao.actualizar(habit);
            }
        });
    }

    private void resetHabits(){
        LiveData<List<Habit>> livedatahb = habitDao.obtener();
        List<Habit> habits = livedatahb.getValue();

        if (habits != null) {
            LocalDate currentDate = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                currentDate = LocalDate.now();
            }

            for (Habit habit : habits) {
                LocalDate startDate = habit.getStartDate();
                long daysSinceStart = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    daysSinceStart = ChronoUnit.DAYS.between(startDate, currentDate);
                }

                if (daysSinceStart >= 7) {
                    habit.setChecked(false);
                    habitDao.actualizar(habit);
                }
            }
        }
    }
}
