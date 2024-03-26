package repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import data.DBAccess;
import data.FishingZoneDAO;
import herrero.julen.rodrocks.R;
import model.FishingZone;

public class FishingZoneRepo {

    FishingZoneDAO fishingZoneDAO;
    Executor executor = Executors.newSingleThreadExecutor();

    public FishingZoneRepo(Application application) {
        fishingZoneDAO = DBAccess.obtainInstance(application).getFishingZoneDAO();
    }

    public LiveData<List<FishingZone>> get() {
        return fishingZoneDAO.getFishingZones();
    }

    public void insert(FishingZone fishingZone) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                fishingZoneDAO.insert(fishingZone);
            }
        });
    }

    public void update(FishingZone fishingZone) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                fishingZoneDAO.update(fishingZone);
            }
        });
    }

    public void delete(FishingZone fishingZone) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                fishingZoneDAO.delete(fishingZone);
            }
        });
    }
}
