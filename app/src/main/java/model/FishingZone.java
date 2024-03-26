package model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.checkerframework.common.aliasing.qual.Unique;

@Entity(tableName = "fishing_zone", indices = @Index(value = {"zone_name"}, unique = true))
public class FishingZone {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    String zone_name;
    String town;
    String water_type;
    String zone_description;

    public FishingZone(String zone_name, String town, String water_type, String zone_description) {
        this.zone_name = zone_name;
        this.town = town;
        this.water_type = water_type;
        this.zone_description = zone_description;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getWater_type() {
        return water_type;
    }

    public void setWater_type(String water_type) {
        this.water_type = water_type;
    }

    public String getZone_description() {
        return zone_description;
    }

    public void setZone_description(String zone_description) {
        this.zone_description = zone_description;
    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    @Override
    public String toString() {
        return "FishingZone{" +
                /*"id=" + id +*/
                ", zone_name='" + zone_name + '\'' +
                ", town='" + town + '\'' +
                ", water_type='" + water_type + '\'' +
                ", zone_description='" + zone_description + '\'' +
                '}';
    }
}
