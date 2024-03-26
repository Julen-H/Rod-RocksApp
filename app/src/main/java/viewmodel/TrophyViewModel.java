package viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import model.Trophy;
import repositories.TrophyRepo;

public class TrophyViewModel extends AndroidViewModel {

    TrophyRepo trophyRepo;

    MutableLiveData<Trophy> trophySelected = new MutableLiveData<>();

    public TrophyViewModel(@NonNull Application application) {
        super(application);
        trophyRepo = new TrophyRepo(application);
    }

    public LiveData<List<Trophy>> get() {
        return trophyRepo.get();
    }

    public void add(Trophy trophy) {
        trophyRepo.insert(trophy);
    }

    void delete(Trophy trophy) {
        trophyRepo.delete(trophy);
    }

    void update(Trophy trophy) {
        trophyRepo.update(trophy);
    }

    void select(Trophy trophy) {
        trophySelected.setValue(trophy);
    }

    MutableLiveData<Trophy> selected() {
        return trophySelected;
    }
}
