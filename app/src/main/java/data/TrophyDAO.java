package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.Trophy;

@Dao
public interface TrophyDAO {

    @Query("SELECT * FROM trophy")
    LiveData<List<Trophy>> getTrophies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Trophy trophy);

    @Update
    void update(Trophy trophy);

    @Delete
    void delete(Trophy trophy);
}
