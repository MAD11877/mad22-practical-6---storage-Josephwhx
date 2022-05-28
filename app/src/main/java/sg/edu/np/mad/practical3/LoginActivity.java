package sg.edu.np.mad.practical3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child("mad").child("Username").setValue("mad");
        mDatabase.child("Users").child("mad").child("Password").setValue("1187");

        Button myLoginButton = findViewById(R.id.loginButton);
        myLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText myUsername = findViewById(R.id.editTextUsername);
                EditText myPassword = findViewById(R.id.editTextPassword);

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Users").child("mad");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String dataUsername = snapshot.child("Username").getValue().toString();
                        String dataPassword = snapshot.child("Password").getValue().toString();

                        if(dataUsername.equals(myUsername.getText().toString()) && dataPassword.equals(myPassword.getText().toString())){
                            Intent myIntent = new Intent(LoginActivity.this, ListActivity.class);
                            startActivity(myIntent);
                            Toast.makeText(LoginActivity.this, "Valid", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Invalid. Pls Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}