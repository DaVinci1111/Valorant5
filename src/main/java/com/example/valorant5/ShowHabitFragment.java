package com.example.valorant5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.valorant5.databinding.FragmentShowAgenteBinding;


public class ShowHabitFragment extends Fragment {
    private FragmentShowAgenteBinding binding;
    private HabitsViewModel habitsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentShowAgenteBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        habitsViewModel = new ViewModelProvider(requireActivity()).get(HabitsViewModel.class);

        habitsViewModel.selectedHabit.observe(getViewLifecycleOwner(), new Observer<Habit>() {
            @Override
            public void onChanged(Habit habit) {
                binding.titleshowagente.setText(habit.getTitle());
                setupCheckBoxes(habit);
                habitsViewModel.actualizar(habit);
            }
        });
    }

    private void setupCheckBoxes(Habit habit) {
        CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int day = getDayFromCheckBoxId(buttonView.getId());
                habit.setDaySelected(day, isChecked);
                habitsViewModel.actualizar(habit);
            }
        };

        binding.mondayCheckBox.setOnCheckedChangeListener(checkBoxListener);
        binding.tuesdayCheckBox.setOnCheckedChangeListener(checkBoxListener);
        binding.wednesdayCheckBox.setOnCheckedChangeListener(checkBoxListener);
        binding.thursdayCheckBox.setOnCheckedChangeListener(checkBoxListener);


        binding.mondayCheckBox.setChecked(habit.isDaySelected(0));
        binding.tuesdayCheckBox.setChecked(habit.isDaySelected(1));
        binding.wednesdayCheckBox.setChecked(habit.isDaySelected(2));
        binding.thursdayCheckBox.setChecked(habit.isDaySelected(3));

    }

    private int getDayFromCheckBoxId(int checkBoxId) {
        if (checkBoxId == R.id.mondayCheckBox) {
            return 0;
        } else if (checkBoxId == R.id.tuesdayCheckBox) {
            return 1;
        } else if (checkBoxId == R.id.wednesdayCheckBox) {
            return 2;
        } else if (checkBoxId == R.id.thursdayCheckBox) {
            return 3;
        } else {
            return -1;
        }
    }
}