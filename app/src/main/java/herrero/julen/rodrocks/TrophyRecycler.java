package herrero.julen.rodrocks;

import android.content.Intent;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import herrero.julen.rodrocks.databinding.FragmentTrophyRecyclerBinding;
import herrero.julen.rodrocks.databinding.FragmentTrophyViewHolderBinding;
import model.Trophy;
import viewmodel.TrophyViewModel;

public class TrophyRecycler extends Fragment {

    FragmentTrophyRecyclerBinding binding;
    private NavController navController;
    private TrophyViewModel trophyViewModel;

    public TrophyRecycler() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_trophy_recycler, container, false);
        return (binding = FragmentTrophyRecyclerBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trophyViewModel = new ViewModelProvider(requireActivity()).get(TrophyViewModel.class);
        navController = Navigation.findNavController(view);
        TrophyAdapter trophyAdapter = new TrophyAdapter();

        binding.TrophyRecycler.setAdapter(trophyAdapter);
        binding.TrophyRecycler.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        trophyViewModel.get().observe(getViewLifecycleOwner(), new Observer<List<Trophy>>() {
            @Override
            public void onChanged(List<Trophy> trophyList) {
                trophyAdapter.establishList(trophyList);
            }
        });

    }

    class TrophyViewHolder extends RecyclerView.ViewHolder {
        private final FragmentTrophyViewHolderBinding binding;

        public TrophyViewHolder(FragmentTrophyViewHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class TrophyAdapter extends RecyclerView.Adapter<TrophyViewHolder> {
        List<Trophy> trophyList;

        @NonNull
        @Override
        public TrophyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TrophyViewHolder(FragmentTrophyViewHolderBinding.inflate(getLayoutInflater(), parent, false));
        }

        public Trophy getTrophy(int position) {
            return trophyList.get(position);
        }

        @Override
        public void onBindViewHolder(@NonNull TrophyViewHolder holder, int position) {
            Trophy trophy = trophyList.get(position);

            /*holder.binding.trophyNameHolder.setText(holder.binding.trophyNameHolder.getText().toString() + " " + trophy.getFish_name());
            holder.binding.fishSizeHolder.setText(holder.binding.fishSizeHolder.getText().toString() + " " + trophy.getSize());
            holder.binding.trophyLocationHolder.setText(holder.binding.trophyLocationHolder.getText().toString() + " " + trophy.getLocation());
            holder.binding.trophyDescriptionHolder.setText(holder.binding.trophyDescriptionHolder.getText().toString() + " " + trophy.getTrophy_description());
            */
            holder.binding.trophyNameHolder.setText(trophy.getFish_name());
            holder.binding.fishSizeHolder.setText(String.valueOf(trophy.getSize()));
            holder.binding.trophyLocationHolder.setText(trophy.getLocation());
            holder.binding.trophyDescriptionHolder.setText(trophy.getTrophy_description());

            holder.binding.shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String shareText = "This is my best capture, i called it: " + holder.binding.trophyNameHolder.getText().toString() +
                            " , his weight was: " + holder.binding.fishSizeHolder.getText().toString() + " and it was captured in: " +
                            holder.binding.trophyLocationHolder.getText().toString() + " . Here some info about it: " +
                            holder.binding.trophyDescriptionHolder.getText().toString() + " . Rod & Rocks it a nice App, give a try!";

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT, shareText);
                    intent.setType("text/plain");

                    Intent prt = Intent.createChooser(intent, null);
                    startActivity(prt);
                }
            });
        }

        @Override
        public int getItemCount() {
            return trophyList != null ? trophyList.size() : 0;
        }

        public void establishList(List<Trophy> trophyList) {
            this.trophyList = trophyList;
            notifyDataSetChanged();
        }
    }
}