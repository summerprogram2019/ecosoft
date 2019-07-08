package uq.ecosoft.ctrack.model.activities;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.java.Log;

@Data @Log
public class ActivityInstance {
    @NonNull Activities.ACTIVITY_TYPE activity;
    @NonNull Date time;
    @NonNull Integer units;

    public Integer getScore() {
        return Activities.getActivity(activity).getGreenValue() * units;
    }
}
