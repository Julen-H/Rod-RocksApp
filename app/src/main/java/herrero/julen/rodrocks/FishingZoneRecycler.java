package herrero.julen.rodrocks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import herrero.julen.rodrocks.databinding.FragmentFishingZoneRecyclerBinding;
import herrero.julen.rodrocks.databinding.FragmentFishingZoneViewHolderBinding;
import model.FishingZone;
import viewmodel.FishingZoneViewModel;

public class FishingZoneRecycler extends Fragment {

    FragmentFishingZoneRecyclerBinding binding;
    private NavController navController;
    private FishingZoneViewModel fishingZoneViewModel;

    public FishingZoneRecycler() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fishing_zone_recycler, container, false);
        return (binding = FragmentFishingZoneRecyclerBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fishingZoneViewModel = new ViewModelProvider(requireActivity()).get(FishingZoneViewModel.class);
        navController = Navigation.findNavController(view);
        FishingZoneAdapter fishingZoneAdapter = new FishingZoneAdapter();

        binding.ZoneRecycler.setAdapter(fishingZoneAdapter);
        binding.ZoneRecycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        fishingZoneViewModel.get().observe(getViewLifecycleOwner(), new Observer<List<FishingZone>>() {
            @Override
            public void onChanged(List<FishingZone> fishingZoneList) {
                fishingZoneAdapter.establishList(fishingZoneList);
            }
        });
    }

    class FishingZoneViewHolder extends RecyclerView.ViewHolder {
        private final FragmentFishingZoneViewHolderBinding binding;

        public FishingZoneViewHolder(FragmentFishingZoneViewHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class FishingZoneAdapter extends RecyclerView.Adapter<FishingZoneViewHolder> {

        List<FishingZone> fishingZoneList;

        @NonNull
        @Override
        public FishingZoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FishingZoneViewHolder(FragmentFishingZoneViewHolderBinding.inflate(getLayoutInflater(), parent, false));
        }

        public FishingZone getFishingZone(int position) {
            return fishingZoneList.get(position);
        }

        @Override
        public void onBindViewHolder(@NonNull FishingZoneViewHolder holder, int position) {
            FishingZone fishingZone = fishingZoneList.get(position);

            holder.binding.zoneNameRecycler.setText(fishingZone.getZone_name());
            holder.binding.townNameRecycler.setText(fishingZone.getTown());
            holder.binding.waterTypeRecycler.setText(fishingZone.getWater_type());
            holder.binding.descriptionRecycler.setText(fishingZone.getZone_description());

            holder.binding.showMapsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String locationUrl = "http://maps.google.com/maps?q=" + holder.binding.townNameRecycler.getText().toString();
                    Uri getLoc = Uri.parse(locationUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW, getLoc);
                    Log.d("URL_DEBUG", getLoc.toString());
                    startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return fishingZoneList != null ? fishingZoneList.size() : 0;
            //return fishingZoneList.size();
        }

        public void establishList(List<FishingZone> fishingZoneList) {
            this.fishingZoneList = fishingZoneList;
            notifyDataSetChanged();
        }
    }
}