package uq.ecosoft.ctrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void basicBtn(View view) {
        TextView lb = findViewById(R.id.labelHello);
        if (lb.getText().equals(R.string.hello_world)) {
            lb.setText(R.string.goodbye_world);
        } else {
            lb.setText(R.string.hello_world);
        }
    }
}
