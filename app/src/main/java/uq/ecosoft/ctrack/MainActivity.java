package uq.ecosoft.ctrack;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.MenuPopupWindow;

import lombok.extern.java.Log;
import uq.ecosoft.ctrack.model.StepCounter.StepDetector;
import uq.ecosoft.ctrack.model.StepCounter.StepListener;

@Log
public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps = 0;

    // Navbar buttons //
    private ImageView homeImage;
    private ImageView activityImage;
    private ImageView socialImage;
    private ImageView profileImage;

    // Steps //
    private int totalSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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

    public void linkToHomeFromLogin(View view) {
        EditText editUser  = (EditText) findViewById(R.id.editText);
        EditText editPassword  = (EditText) findViewById(R.id.editText2);

        String username = editUser.getText().toString();
        String password = editPassword.getText().toString();

        if (username.equals("HarryG") && password.equals("Russian")) {
            setContentView(R.layout.home);
            initImageView();
            toggleImage(homeImage, "home");
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Invalid Credentials");
            alertDialog.setMessage("The username or password you entered were incorrect. " +
                    "Please enter the correct credentials and try again");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    (dialogInterface, i) -> dialogInterface.dismiss());
            alertDialog.show();
        }
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

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        textView = (TextView) findViewById(R.id.tv_steps);
        final Button BtnStart = (Button) findViewById(R.id.btn_start_stop);
        Boolean started = false;


        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (BtnStart.getText().toString().toLowerCase().equals("start tracking")) {
                    numSteps = 0;
                    sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                    BtnStart.setBackgroundColor(Color.RED);
                    BtnStart.setText("STOP TRACKING");
                } else {
                    sensorManager.unregisterListener(MainActivity.this);
                    BtnStart.setBackgroundColor(Color.GREEN);
                    BtnStart.setText("START TRACKING");
                    totalSteps += numSteps;
                }


            }
        });
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
        if (totalSteps > 0) {
            ((TextView) findViewById(R.id.labelPoints)).setText(String.format("%d GP", totalSteps));
        }

        updateTree();
    }

    public void updateTree() {
        if (totalSteps < 10) {
            // do nothing
        } else if (totalSteps < 20) {
            // stage 2 tree
            ((ImageView) findViewById(R.id.imageGarden)).setImageResource(R.drawable.tree2);
        } else if (totalSteps < 50) {
            // stage 3 tree
            ((ImageView) findViewById(R.id.imageGarden)).setImageResource(R.drawable.tree3);
        } else {
            // stage 4 tree
            ((ImageView) findViewById(R.id.imageGarden)).setImageResource(R.drawable.tree4);
        }
    }

    public void linkToPoints(View view) {
        setContentView(R.layout.points);
        initImageView();
        toggleImage(profileImage, "profile");

        if (totalSteps > 0) {
            ((TextView) findViewById(R.id.labelPoints)).setText(String.format("%d GP", totalSteps));
            ((TextView) findViewById(R.id.textView4)).setText(String.format("%d Steps made to save mother nature", totalSteps));
        }
    }

    public void linkToGarden(View view) {
        setContentView(R.layout.garden);
        initImageView();
        toggleImage(profileImage, "profile");

        updateTree();
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
