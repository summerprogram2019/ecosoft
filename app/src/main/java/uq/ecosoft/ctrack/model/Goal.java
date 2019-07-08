package uq.ecosoft.ctrack.model;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.java.Log;

@Data @Log
public class Goal {
    @NonNull
    Integer id;

    @NonNull
    String description;




}
