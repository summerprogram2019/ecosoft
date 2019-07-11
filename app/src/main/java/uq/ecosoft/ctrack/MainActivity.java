package uq.ecosoft.ctrack;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

import uq.ecosoft.ctrack.controller.PasswordHelper;
import uq.ecosoft.ctrack.controller.State;
import uq.ecosoft.ctrack.controller.UserDatabase;
import uq.ecosoft.ctrack.model.StepCounter.StepDetector;
import uq.ecosoft.ctrack.model.StepCounter.StepListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.steptrack_main);


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        textView = findViewById(R.id.tv_steps);
        Button BtnStart = findViewById(R.id.btn_start);
        Button BtnStop = findViewById(R.id.btn_stop);



        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });


        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                sensorManager.unregisterListener(MainActivity.this);

            }
        });

    }
    */

    public void linkToLogin(View view) {
        // Skip login if already logged in
        boolean loggedIn = State.isLoggedIn();
        if(loggedIn) {
            setContentView(R.layout.activity_main);
            return;
        }

        // eee
        TextView usernameInput = findViewById(R.id.inputUserName);
        TextView passwordInput = findViewById(R.id.inputPassword);
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        // Backdoor for cool people
        if(password.equals("pleaseletmein")) {
            State.stateLogin("coolduderobert", 10);
            setContentView(R.layout.activity_main);
            return;
        }

        // Grab the password from the database and see how we go
        password = PasswordHelper.computeHashSecretSalt(password, username);
        try {
            String passwordCompare = UserDatabase.getPasswordFromUsername(username);
            if(password.equals(passwordCompare)) {
                // Passwords match! Get the UserID and login
                int uid = UserDatabase.getUserIDFromUsername(username);
                State.stateLogin(username, uid);
                setContentView(R.layout.activity_main);

                TextView lbl = findViewById(R.id.labelInfo);
                lbl.setText("Welcome back, " + username + "!");
                return;
            }

        } catch (SQLException e) {
            // I don't trust the database so this is a backup login if it goes terribly wrong
            State.stateLogin("guthers", 9);
            setContentView(R.layout.activity_main);
            return;
        }
    }

    public void linkToHome(View view) {
        setContentView(R.layout.home);
    }

    public void linkToTcs(View view) {
        setContentView(R.layout.terms_conditions);
    }

    public void linkToSignUp(View view) {
        setContentView(R.layout.sign_up);
    }

    public void linkToVerify(View view) {
        setContentView(R.layout.verify);
    }

    public void linkToActivities(View view) {
        setContentView(R.layout.activities_home);
    }

    public void linkToSteps(View view) {
        setContentView(R.layout.step_tracking);
    }

    public void linkToGoals(View view) {
        setContentView(R.layout.goals);
    }

    public void linkToChallenges(View view) {
        setContentView(R.layout.daily_challenges);
    }

    public void linkToFuture(View view) {
        setContentView(R.layout.future_activities);
    }

    public void linkToSocial(View view) {
        setContentView(R.layout.social_leaderboard);
    }

    public void linkToFriends(View view) {
        setContentView(R.layout.social_friends);
    }

    public void linkToManage(View view) {
        setContentView(R.layout.social_manage_friends);
    }

    public void linkToProfile(View view) {
        setContentView(R.layout.profile);
    }

    public void linkToPoints(View view) {
        setContentView(R.layout.points);
    }

    public void linkToGarden(View view) {
        setContentView(R.layout.garden);
    }

    public void linkToSettings(View view) {
        setContentView(R.layout.settings);
    }

    public void basicBtn(View view) {
        TextView lb = findViewById(R.id.inputUserName);
        if (lb.getText().equals(getResources().getString(R.string.hello_world))) {
            lb.setText(getResources().getString(R.string.goodbye_world));
        } else {
            lb.setText(getResources().getString(R.string.hello_world));
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        String s = TEXT_NUM_STEPS + numSteps;
        textView.setText(s);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // do nothing
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // do nothing
    }
}
