package uq.ecosoft.ctrack.model.activities;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.java.Log;

@Data @Log
public class ActivityInstance {
    @NonNull ActivityType activityType;
    @NonNull Date time;
    @NonNull Integer units;

    public Integer getScore() {
        return activityType.getGreenValue() * units;
    }
}
