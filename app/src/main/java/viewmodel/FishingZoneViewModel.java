package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import model.FishingZone;
import repositories.FishingZoneRepo;

public class FishingZoneViewModel extends AndroidViewModel {

    FishingZoneRepo fishingZoneRepo;

    MutableLiveData<FishingZone> fishingZoneSelected;

    public FishingZoneViewModel(@NonNull Application application) {
        super(application);
        fishingZoneRepo = new FishingZoneRepo(application);
    }

    public LiveData<List<FishingZone>> get() {
        return fishingZoneRepo.get();
    }

    public void add(FishingZone fishingZone) {
        fishingZoneRepo.insert(fishingZone);
    }

    void delete(FishingZone fishingZone) {
        fishingZoneRepo.delete(fishingZone);
    }

    void update(FishingZone fishingZone) {
        fishingZoneRepo.update(fishingZone);
    }

    void select(FishingZone fishingZone) {
        fishingZoneSelected.setValue(fishingZone);
    }

    MutableLiveData<FishingZone> selected() {
        return fishingZoneSelected;
    }
}
