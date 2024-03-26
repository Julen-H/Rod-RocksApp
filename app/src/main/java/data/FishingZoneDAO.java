package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import model.FishingZone;

@Dao
public interface FishingZoneDAO {

    @Query("SELECT * FROM fishing_zone")
    LiveData<List<FishingZone>> getFishingZones();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FishingZone fishingZone);

    @Update
    void update(FishingZone fishingZone);

    @Delete
    void delete(FishingZone fishingZone);
}
