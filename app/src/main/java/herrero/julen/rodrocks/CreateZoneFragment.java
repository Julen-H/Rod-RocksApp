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
import java.util.zip.Inflater;

import herrero.julen.rodrocks.databinding.FragmentCreateZoneBinding;
import model.FishingZone;
import viewmodel.FishingZoneViewModel;


public class CreateZoneFragment extends Fragment {

    String zoneNameInput, townInput, waterTypeInput, zoneDescInput;
    FragmentCreateZoneBinding binding;
    NavController navController;
    FishingZoneViewModel fishingZoneViewModel;
    Executor executor;

    public CreateZoneFragment() {
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
        //return inflater.inflate(R.layout.fragment_create_zone, container, false);
        return(binding = FragmentCreateZoneBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        fishingZoneViewModel = new ViewModelProvider(requireActivity()).get(FishingZoneViewModel.class);
        executor = Executors.newSingleThreadExecutor();

        binding.addZoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    zoneNameInput = binding.addZoneName.getText().toString();
                    townInput = binding.townNameAdd.getText().toString();
                    waterTypeInput = binding.waterTypeAdd.getText().toString();
                    zoneDescInput = binding.descriptionAdd.getText().toString();

                    FishingZone fishingZone = new FishingZone(zoneNameInput, townInput, waterTypeInput, zoneDescInput);

                    fishingZoneViewModel.add(fishingZone);

                    Toast t = Toast.makeText(getContext(), "Success creating a Fishing Zone", Toast.LENGTH_LONG);
                    t.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_createZoneFragment_to_homeFragment);
            }
        });
    }
}