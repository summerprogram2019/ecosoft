package uq.ecosoft.ctrack.model.activities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.java.Log;

@AllArgsConstructor @Log
public enum Activity {
    /**
     * These are the preset enums
     */
    WALKING(1,  "act_step","Walking",
            "If you take steps you are walking","steps",1),
    DRIVING(2, "act_drive", "Driving", "If it goes vroom you drive",
            "minutes", -50),
    BUS(3, "act_nostraw", "Straw Denial", "You helped prevent plastic waste",
                    "straws", -10),
    NO_STRAW(4, "act_bus", "Taking the bus",
            "If it goes vroom and you're with lots of others, you're in a bus.","minutes",
            20);

    @Getter Integer id;
    @Getter String key;
    @Getter String name;
    @Getter String description;
    @Getter String unitName;
    @Getter Integer greenValue;

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