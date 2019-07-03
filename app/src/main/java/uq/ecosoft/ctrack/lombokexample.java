package uq.ecosoft.ctrack;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.flogger.Flogger;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@RequiredArgsConstructor @AllArgsConstructor @Log
public class lombokexample {

    @Getter @Setter @NonNull
    private String foo;

    @Getter @Setter
    private String bar;

    private void method1() {
        log.info("foobar");
    }

}
