package com.example.valorant5;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class HabitsViewModel extends AndroidViewModel {

    private AgenteRepository agenteRepository;

    private LiveData<List<Habit>> hbLiveData;

    MutableLiveData<Habit> selectedHabit = new MutableLiveData<>();

    public HabitsViewModel(@NonNull Application application) {
        super(application);

        agenteRepository = new AgenteRepository(application);
        hbLiveData = agenteRepository.obtener();
    }

    LiveData<List<Habit>> obtener() {
        return hbLiveData;
    }

    void insertar(Habit habit) {
        agenteRepository.insertar(habit);
    }

    void eliminar(Habit habit) {
        agenteRepository.eliminar(habit);
    }

    void actualizar(Habit habit) {
        habit.setChecked(!habit.isChecked());
        Log.d("ViewModel", "Actualizar habit: " + habit.getId() + ", isChecked: " + habit.isChecked());
        agenteRepository.actualizar(habit);
    }

    void select(Habit habit) {
        selectedHabit.setValue(habit);
    }

    MutableLiveData<Habit> getSelectedHabit() {
        return selectedHabit;
    }

    // Método para obtener el título del hábito seleccionado
    String getSelectedHabitTitle() {
        Habit habit = selectedHabit.getValue();
        return habit != null ? habit.getTitle() : "";
    }
}
