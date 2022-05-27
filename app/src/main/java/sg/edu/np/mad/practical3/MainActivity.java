package sg.edu.np.mad.practical3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    UserDBHandler userDBHandler = new UserDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Receive intent
        Bundle bundle = getIntent().getExtras();
        String profileName = bundle.getString("ProfileName");
        String profileDesc = bundle.getString("ProfileDesc");
        int profileID = bundle.getInt("ProfileID");
        boolean profileStatus = bundle.getBoolean("ProfileStatus");


        //Create user Object
        User user1 = new User(profileName, profileDesc, profileID, profileStatus);

        //Get username text by id
        TextView userName = findViewById(R.id.userName);
        //Update username on view
        userName.setText(user1.getName());

        //Get description text by id
        TextView description = findViewById(R.id.userDescription);
        //Update the description
        description.setText(user1.getDescription());

        //Get follow button by id
        Button followButton = findViewById(R.id.followButton);
        //Update text on button by calling function
        checkFollowStatus(user1.isFollowed(), followButton);

        //Create on click function for follow button
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Update follow status
                if(user1.isFollowed() == true){
                    user1.setFollowed(false);

                    //update in database
                    userDBHandler.updateUser(user1);

                    // Display toast notification
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();

                }
                else if(user1.isFollowed() == false){
                    user1.setFollowed(true);

                    //update in database
                    userDBHandler.updateUser(user1);

                    // Display toast notification
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                }
                //Call function to update the text on button
                checkFollowStatus(user1.isFollowed(), followButton);

            }
        });

        // Get message button widget
        Button messageButton = findViewById(R.id.messageButton);

        // Create on click function for message button
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create intent for message button (link to message group activity)
                Intent messageGroupIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(messageGroupIntent);
            }
        });
    }
    //function to check and update follow button
    private void checkFollowStatus(boolean followed, Button followButton) {
        if(followed == true){
            // Update button text to unfollow
            followButton.setText("Unfollow");
        }
        else {

            // Update button text to follow
            followButton.setText("Follow");
        }
    }



}