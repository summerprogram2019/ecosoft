package uq.ecosoft.ctrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

import uq.ecosoft.ctrack.model.StepCounter.StepDetector;
import uq.ecosoft.ctrack.model.StepCounter.StepListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

       textView = (TextView) findViewById(R.id.tv_steps);
        Button BtnStart = (Button) findViewById(R.id.btn_start);
        Button BtnStop = (Button) findViewById(R.id.btn_stop);



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

    public void linkToLogin(View view) {
        setContentView(R.layout.activity_main);
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

}
