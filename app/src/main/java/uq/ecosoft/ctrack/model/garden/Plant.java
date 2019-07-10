package uq.ecosoft.ctrack.model.garden;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.java.Log;

@Data @Log
public class  Plant {
    @NonNull Integer id;
    @NonNull String name;
    @NonNull Integer price;
    @NonNull Boolean inStore;
}
