package repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import data.DBAccess;
import data.TrophyDAO;
import model.Trophy;

public class TrophyRepo {

    TrophyDAO trophyDAO;

    Executor executor = Executors.newSingleThreadExecutor();

    public TrophyRepo(Application application) {
        trophyDAO = DBAccess.obtainInstance(application).getTrophyDAO();
    }

    public LiveData<List<Trophy>> get() {
        return trophyDAO.getTrophies();
    }

    public void insert(Trophy trophy) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                trophyDAO.insert(trophy);
            }
        });
    }

    public void update(Trophy trophy) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                trophyDAO.update(trophy);
            }
        });
    }

    public void delete(Trophy trophy) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                trophyDAO.delete(trophy);
            }
        });
    }
}
