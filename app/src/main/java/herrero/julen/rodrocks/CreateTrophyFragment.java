package herrero.julen.rodrocks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import herrero.julen.rodrocks.databinding.FragmentCreateTrophyBinding;
import model.Trophy;
import viewmodel.TrophyViewModel;


public class CreateTrophyFragment extends Fragment {

    FragmentCreateTrophyBinding binding;
    NavController navController;
    TrophyViewModel trophyViewModel;
    Executor executor;

    String fishNameInput, fishSizeInput, locationInput, descriptionInput;
    float fishSizeFloat;
    public CreateTrophyFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_trophy, container, false);
        return (binding = FragmentCreateTrophyBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        trophyViewModel = new ViewModelProvider(requireActivity()).get(TrophyViewModel.class);
        executor = Executors.newSingleThreadExecutor();

        binding.addTrophyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    fishNameInput = binding.fishNameAdd.getText().toString();
                    fishSizeInput = binding.fishSizeAdd.getText().toString();
                    fishSizeFloat = Float.valueOf(fishSizeInput);
                    locationInput = binding.locationAdd.getText().toString();
                    descriptionInput = binding.descriptionTrophyAdd.getText().toString();

                    Trophy trophy = new Trophy(fishNameInput, fishSizeFloat, locationInput, descriptionInput);
                    trophyViewModel.add(trophy);

                    Toast t = Toast.makeText(getContext(), "Success creating a Fishing Trophy", Toast.LENGTH_LONG);
                    t.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_createTrophyFragment_to_homeFragment);
            }
        });
    }
}