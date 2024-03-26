package herrero.julen.rodrocks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import herrero.julen.rodrocks.databinding.FragmentFishingZoneRecyclerBinding;
import herrero.julen.rodrocks.databinding.FragmentFishingZoneViewHolderBinding;


public class FishingZoneViewHolder extends Fragment {

    public FragmentFishingZoneViewHolderBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fishing_zone_view_holder, container, false);
    }
}