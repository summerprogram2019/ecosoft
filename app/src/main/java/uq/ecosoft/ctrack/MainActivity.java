package uq.ecosoft.ctrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void linkToHome(View view) {
        setContentView(R.layout.home);
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

    public void linkToProfile(View view) {
        setContentView(R.layout.profile);
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
