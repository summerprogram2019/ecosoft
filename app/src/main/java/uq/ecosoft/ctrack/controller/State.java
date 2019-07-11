package uq.ecosoft.ctrack.controller;

import lombok.Getter;
import lombok.Setter;

public class State {
    @Getter @Setter
    public static String username;

    @Getter @Setter
    public static int uid;

    @Getter @Setter
    public static boolean loggedIn;

    /**
     * Helper function to login
     * @param username username to login
     * @param uid id of the logged in user
     */
    public static void stateLogin(String username, int uid) {
        setUsername(username);
        setUid(uid);
        setLoggedIn(true);
    }
}
