package herrero.julen.rodrocks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import herrero.julen.rodrocks.databinding.FragmentTrophyRecyclerBinding;
import herrero.julen.rodrocks.databinding.FragmentTrophyViewHolderBinding;


public class TrophyViewHolder extends Fragment {

    public FragmentTrophyViewHolderBinding binding;

    public TrophyViewHolder(@NonNull FragmentTrophyRecyclerBinding inflate) {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trophy_view_holder, container, false);
    }
}