package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        Group1Fragment firstFragment = new Group1Fragment();
        Group2Fragment secondFragment = new Group2Fragment();
        FragmentManager manager = getSupportFragmentManager();

        // Get group 1 button
        Button group1Button = findViewById(R.id.groupOneButton);
        // Create on click function
        group1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .replace(R.id.frameLayout, firstFragment, firstFragment.getTag())
                        .commit();
            }
        });

        // Get group 2 button
        Button group2Button = findViewById(R.id.groupTwoButton);
        // Create on click function
        group2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.beginTransaction()
                        .replace(R.id.frameLayout, secondFragment, secondFragment.getTag())
                        .commit();
            }
        });
    }
}