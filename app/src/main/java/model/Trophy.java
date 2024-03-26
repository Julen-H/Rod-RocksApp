package model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "trophy", indices = @Index(value = {"fish_name"}, unique = true))
public class Trophy {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    String fish_name;
    float size;
    String location;
    String trophy_description;

    public Trophy (String fish_name, float size, String location, String trophy_description) {
        this.fish_name = fish_name;
        this.size = size;
        this.location = location;
        this.trophy_description = trophy_description;
    }

    public String getFish_name() {
        return fish_name;
    }

    public void setFish_name(String fish_name) {
        this.fish_name = fish_name;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTrophy_description() {
        return trophy_description;
    }

    public void setTrophy_description(String trophy_description) {
        this.trophy_description = trophy_description;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    @Override
    public String toString() {
        return "Trophy{" +
                /*"id=" + id +*/
                ", fish_name='" + fish_name + '\'' +
                ", size=" + size +
                ", location='" + location + '\'' +
                ", trophy_description='" + trophy_description + '\'' +
                '}';
    }
}
