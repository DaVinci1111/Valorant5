package com.example.valorant5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.valorant5.databinding.FragmentNewAgentBinding;



public class NewHabitFragment extends Fragment {

    public static final String TAG = "NewHabitFragment";

    private FragmentNewAgentBinding binding;
    private NavController navController;
    private HabitsViewModel habitsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewAgentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }


        habitsViewModel = new ViewModelProvider(this).get(HabitsViewModel.class);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newHabit();
                navController.navigate(R.id.action_newHabitFragment_to_habitsFragment);
            }
        });
    }

    private void newHabit() {
        String nameHabit = binding.nombre.getText().toString().trim();

        if (!nameHabit.isEmpty()) {
            Habit newHabit = new Habit(nameHabit);
            habitsViewModel.insertar(newHabit);
        } else {
            Toast.makeText(requireContext(), "Please enter a habit name", Toast.LENGTH_SHORT).show();
        }
    }
}

