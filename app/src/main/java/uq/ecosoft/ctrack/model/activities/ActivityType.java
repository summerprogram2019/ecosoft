package uq.ecosoft.ctrack.model.activities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

@AllArgsConstructor @Log
public enum ActivityType {
    /**
     * These are the preset enums
     */
    WALKING(0, "Walking",
            "If you take steps you are walking","steps",10)
    , DRIVING(1, "Driving", "If it goes vroom you drive",
            "minutes", -100);

    @Getter
    Integer id;
    @Getter
    String name;
    @Getter
    String description;
    @Getter
    String unitName;
    @Getter
    Integer greenValue;


    public Boolean isGreen() {
        return (greenValue > 0);
    }

    public Boolean isEvil() {
        return (greenValue < 0);
    }

    public Boolean isNeutural() {
        return (greenValue == 0);
    }

}