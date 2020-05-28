package bkp.com.hospitalmanagementsystem.Receptionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.PrevalentReceptionist.PrevalentReceptionist;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.model.ReceptionistInfo;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReceptionistLoginActivity extends AppCompatActivity {

    private EditText mobileEdtReceptionist, passwordEdtReceptionist;
    private Button loginBtnReceptionist;

    private String parentdbName="ReceptionistInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_login);

        Paper.init(this);

        mobileEdtReceptionist = findViewById(R.id.mobile_number_receptionist);
        passwordEdtReceptionist = findViewById(R.id.password_receptionist);
        loginBtnReceptionist = findViewById(R.id.login_receptionist);

        loginBtnReceptionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginReceptionist();
            }
        });

    }

    private void loginReceptionist() {

        final String mobile = mobileEdtReceptionist.getText().toString();
        final String password = passwordEdtReceptionist.getText().toString();

        if(mobile.equals("") && password.equals("")){
            Toast.makeText(this, "Invalid information.", Toast.LENGTH_SHORT).show();
        }else{
            allowAccessToAccountReceptionist(mobile,password);
        }

    }

    private void allowAccessToAccountReceptionist(final String mobile, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentdbName).child(mobile).exists()) {

                    ReceptionistInfo receptionistInfo = dataSnapshot.child(parentdbName).child(mobile).getValue(ReceptionistInfo.class);

                    if (receptionistInfo.getMobile().equals(mobile)) {
                        if (receptionistInfo.getPassword().equals(password)) {
                            Toast.makeText(ReceptionistLoginActivity.this, "Logged in successfully...", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ReceptionistLoginActivity.this, ReceptionistHomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PrevalentReceptionist.currentOnlineReceptionist = receptionistInfo;
                            startActivity(intent);
                        } else {
                            Toast.makeText(ReceptionistLoginActivity.this, "Password is incorrect...", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(ReceptionistLoginActivity.this, "Account with this " + mobile + "number does not exists.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
