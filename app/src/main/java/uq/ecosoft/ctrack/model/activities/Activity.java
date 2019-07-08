package uq.ecosoft.ctrack.model.activities;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.java.Log;

@Data @Log
public abstract class Activity {
    @NonNull Integer id;
    @NonNull String name;
    @NonNull String description;

    // These parameters should be applicable to all activities
    @NonNull String unitName; // unit name e.g. steps, kilometers
    @NonNull Integer greenValue; // How green is this thing? Can be -ve

    public Boolean isActivityGreen() {
        return (greenValue > 0);
    }

    public Boolean isActivityEvil() {
        return (greenValue < 0);
    }

    public Boolean isActivityCarbonNatural() {
        return (greenValue == 0);
    }
}
