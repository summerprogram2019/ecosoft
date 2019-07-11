package uq.ecosoft.ctrack;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
