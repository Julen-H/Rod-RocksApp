package data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.FishingZone;
import model.Trophy;
import model.User;

@Database(entities = {User.class, FishingZone.class, Trophy.class}, version = 1, exportSchema = false)
public abstract class DBAccess extends RoomDatabase {

    private static volatile DBAccess INSTANCE;

    public abstract UserDAO getUserDAO();

    public abstract FishingZoneDAO getFishingZoneDAO();

    public abstract TrophyDAO getTrophyDAO();

    public static DBAccess obtainInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized ((DBAccess.class)) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                                    DBAccess.class, "rodrocks.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
