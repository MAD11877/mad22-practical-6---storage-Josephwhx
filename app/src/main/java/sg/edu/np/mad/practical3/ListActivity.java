package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity implements ProfileListener{

    private String TAG = "ListActivity";
    private ArrayList<User> userList = new ArrayList<>();
    UserDBHandler userDBHandler = new UserDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Check database for existing data
        if(userDBHandler.getUsers() == null) {
            // Create 20 user object in arraylist
            for (int i = 1; i <= 20; i++) {
                User user = new User("Name" + generateStringNumber(), "Description: " + generateStringNumber(), i, getFollow());
                userList.add(user);
            }
            //Add user array into db
            userDBHandler.addUser(userList);
        }

        ArrayList<User> userDataList = userDBHandler.getUsers();
        System.out.println(userDataList);

        RecyclerView recyclerView = findViewById(R.id.recyclerList);
        ProfileAdapter mAdapter = new ProfileAdapter(userDataList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        // Get profile icon image widget
        //ImageView profileIcon = findViewById(R.id.iconImage);


    }
    // Create function to generate a random string number
    public String generateStringNumber(){
        Random random = new Random();
        return Integer.toString(random.nextInt());
    }

    // Create function to return true or false
    public boolean getFollow(){
        Random random = new Random();
        int number = random.nextInt(2);

        if(number == 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onIconClick(User user) {
        // Create alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage(user.getName());
        builder.setCancelable(true);
        // set positive button
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // Create Intent
                Intent viewProfile = new Intent(ListActivity.this, MainActivity.class);
                viewProfile.putExtra("ProfileName", user.getName());
                viewProfile.putExtra("ProfileDesc", user.getDescription());
                viewProfile.putExtra("ProfileID", user.getId());
                viewProfile.putExtra("ProfileStatus", user.isFollowed());
                // Start Intent
                startActivity(viewProfile);
            }
        });
        // Set negative button
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();

    }

}