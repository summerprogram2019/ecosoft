package uq.ecosoft.ctrack.model.activities;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.java.Log;

@Data @Log
public class ActivityInstance {
    @NonNull Activity activity;
    @NonNull Date time;
    @NonNull Integer points;

    public Integer getScore() {
        return activity.getGreenValue() * points;
    }
}
