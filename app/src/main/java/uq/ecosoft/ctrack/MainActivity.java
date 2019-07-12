package uq.ecosoft.ctrack;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLException;

import lombok.extern.java.Log;
import uq.ecosoft.ctrack.controller.DatabaseConnector;
import uq.ecosoft.ctrack.controller.UserDatabase;
import uq.ecosoft.ctrack.model.StepCounter.StepDetector;
import uq.ecosoft.ctrack.model.StepCounter.StepListener;
import uq.ecosoft.ctrack.model.User;

@Log
public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps;

    // Navbar buttons //
    private ImageView homeImage;
    private ImageView activityImage;
    private ImageView socialImage;
    private ImageView profileImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        return;

        /*
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
        */
    }

    public void linkToLogin(View view) {
        setContentView(R.layout.activity_main);
    }

    public void linkToBetterUI(View view) {
        setContentView(R.layout.activity_main2);
    }

    public void linkToHome(View view) {
        setContentView(R.layout.home);
        initImageView();
        toggleImage(homeImage, "home");
    }

    public void initImageView() {
        homeImage = findViewById(R.id.imageHome);
        activityImage = findViewById(R.id.imageActivities);
        socialImage = findViewById(R.id.imageSocial);
        profileImage = findViewById(R.id.imageProfile);
    }

    public void toggleImage(ImageView img, String imageName) {
        switch (imageName) {
            case "home": {
                img.setImageResource(R.drawable.new_home2);
                break;
            }case "activity": {
                img.setImageResource(R.drawable.new_activity2);
                break;
            }case "social": {
                img.setImageResource(R.drawable.new_social2);
                break;
            }case "profile": {
                img.setImageResource(R.drawable.new_profile2);
                break;
            }default: {
                System.exit(-1);
            }
        }
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
        setContentView(R.layout.activities_home2);
        initImageView();
        toggleImage(activityImage, "activity");
    }

    public void linkToSteps(View view) {
        setContentView(R.layout.step_tracking);
        initImageView();
        toggleImage(activityImage, "activity");
    }

    public void linkToGoals(View view) {
        setContentView(R.layout.goals);
        initImageView();
        toggleImage(activityImage, "activity");
    }

    public void linkToChallenges(View view) {
        setContentView(R.layout.daily_challenges2);
        initImageView();
        toggleImage(activityImage, "activity");
    }

    public void linkToFuture(View view) {
        setContentView(R.layout.future_activities2);
        initImageView();
        toggleImage(activityImage, "activity");
    }

    public void linkToSocial(View view) {
        setContentView(R.layout.social_leaderboard);
        initImageView();
        toggleImage(socialImage, "social");
    }

    public void linkToFriends(View view) {
        setContentView(R.layout.social_friends);
        initImageView();
        toggleImage(socialImage, "social");
    }

    public void linkToManage(View view) {
        setContentView(R.layout.social_manage_friends);
        initImageView();
        toggleImage(socialImage, "social");
    }

    public void linkToProfile(View view) {
        setContentView(R.layout.profile);
        initImageView();
        toggleImage(profileImage, "profile");
    }

    public void linkToPoints(View view) {
        setContentView(R.layout.points);
        initImageView();
        toggleImage(profileImage, "profile");
    }

    public void linkToGarden(View view) {
        setContentView(R.layout.garden);
        initImageView();
        toggleImage(profileImage, "profile");
    }

    public void linkToSettings(View view) {
        setContentView(R.layout.settings);
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        String s = TEXT_NUM_STEPS + numSteps;
        textView.setText(s);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(sensorEvent.timestamp, sensorEvent.values[0],
                    sensorEvent.values[1], sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // do nothing
    }
}
