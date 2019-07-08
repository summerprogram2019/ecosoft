package uq.ecosoft.ctrack.model.activities;

import lombok.NonNull;
import lombok.extern.java.Log;
import uq.ecosoft.ctrack.model.activities.impl.ActivityDrive;
import uq.ecosoft.ctrack.model.activities.impl.ActivityWalk;

@Log
public abstract class Activities {
    public enum ACTIVITY_TYPE {
        WALKING, DRIVING
    }

    private static ActivityWalk activityWalk = new ActivityWalk();
    private static ActivityDrive activityDrive = new ActivityDrive();

    public static Activity getActivity(@NonNull Activities.ACTIVITY_TYPE type) {
        switch (type) {
            case WALKING:
                return activityWalk;
            case DRIVING:
                return activityDrive;
            default:
                log.warning("Unknown enum!!! " + type.name());
                return null;
        }
    }
}
