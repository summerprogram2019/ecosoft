package uq.ecosoft.ctrack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor @AllArgsConstructor
public class lombokexample {
    @Getter @Setter @NonNull
    private String foo;

    @Getter @Setter
    private String bar;

}
