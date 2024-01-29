package com.example.valorant5;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;



import kotlin.jvm.functions.Function1;

public class HabilidadViewModel extends AndroidViewModel {
    Habilidad entrenador;

    LiveData<Integer> ejercicioLiveData;
    LiveData<String> repeticionLiveData;

    public HabilidadViewModel(@NonNull Application application) {
        super(application);

        entrenador = new Habilidad();

        ejercicioLiveData = Transformations.switchMap(entrenador.ordenLiveData, new Function1<String, LiveData<Integer>>() {

            String ejercicioAnterior;

            @Override
            public LiveData<Integer> invoke(String orden) {

                String ejercicio = orden.split(":")[0];

                if(!ejercicio.equals(ejercicioAnterior)){
                    ejercicioAnterior = ejercicio;
                    int imagen;
                    switch (ejercicio) {
                        case "EJERCICIO1":
                        default:
                            imagen = R.drawable.jett_valo;
                            break;
                        case "EJERCICIO2":
                            imagen = R.drawable.chamer5;
                            break;
                        case "EJERCICIO3":
                            imagen = R.drawable.neon;
                            break;
                        case "EJERCICIO4":
                            imagen = R.drawable.iso;
                            break;
                    }

                    return new MutableLiveData<>(imagen);
                }
                return null;
            }
        });

        repeticionLiveData = Transformations.switchMap(entrenador.ordenLiveData, new Function1<String, LiveData<String>>() {
            @Override
            public LiveData<String> invoke(String orden) {
                return new MutableLiveData<>(orden.split(":")[1]);
            }
        });
    }

    LiveData<Integer> obtenerEjercicio(){
        return ejercicioLiveData;
    }

    LiveData<String> obtenerRepeticion(){
        return repeticionLiveData;
    }
}