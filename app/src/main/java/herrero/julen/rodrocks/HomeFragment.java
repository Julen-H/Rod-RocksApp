package herrero.julen.rodrocks;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.Locale;
import java.util.zip.Inflater;

import herrero.julen.rodrocks.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    NavController navController;

    public HomeFragment() {
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
        //return inflater.inflate(R.layout.fragment_home, container, false);
        return(binding = FragmentHomeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        //Spinner
        String[] languages = new String[]{"No selection", "Euskera", "English", "Español"};
        String[] languagesCodes = new String[]{"", "eu", "en", "es"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.languageSpinner.setAdapter(adapter);

        binding.languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLanguageCode = languagesCodes[position];
                if (!selectedLanguageCode.isEmpty()) {
                    setLocale(selectedLanguageCode);
                } else if (selectedLanguageCode.equals("No selection")) {
                    
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.createZoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_createZoneFragment);
            }
        });

        binding.showZonesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_fishingZoneRecycler);
            }
        });

        binding.showTrophiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_trophyRecycler);
            }
        });

        binding.createTrophyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_createTrophyFragment);
            }
        });
    }

    private void setLocale(String languageCode) {
        Configuration configuration = getResources().getConfiguration();
        configuration.setLocale(new Locale(languageCode));
        // Reinicia la actividad actual para aplicar el nuevo idioma
        Intent intent = requireActivity().getIntent();
        requireActivity().finish();
        //requireActivity().overridePendingTransition(0, 0); // Evita transiciones de animación al reiniciar
        requireActivity().startActivity(intent);
    }
}