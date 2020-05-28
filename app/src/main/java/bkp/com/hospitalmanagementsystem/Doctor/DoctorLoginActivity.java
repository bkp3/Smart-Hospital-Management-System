package bkp.com.hospitalmanagementsystem.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.PrevalentDoctor.PrevalentDoctor;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.model.DoctorInfo;
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

public class DoctorLoginActivity extends AppCompatActivity {

    private EditText mobileEdtDoctor, passwordEdtDoctor;
    private Button loginBtnDoctor;

    private String parentdbName="DoctorInfo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        Paper.init(this);

        mobileEdtDoctor = findViewById(R.id.mobile_number_doctor);
        passwordEdtDoctor = findViewById(R.id.password_doctor);
        loginBtnDoctor = findViewById(R.id.login_doctor);

        loginBtnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDoctor();
            }
        });

    }

    private void loginDoctor() {

        final String mobile = mobileEdtDoctor.getText().toString();
        final String password = passwordEdtDoctor.getText().toString();

        if(mobile.equals("") && password.equals("")){
            Toast.makeText(this, "Invalid information.", Toast.LENGTH_SHORT).show();
        }else{
            allowAccessToAccountDoctor(mobile,password);
        }

    }

    private void allowAccessToAccountDoctor(final String mobile, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentdbName).child(mobile).exists()) {

                    DoctorInfo doctorInfo = dataSnapshot.child(parentdbName).child(mobile).getValue(DoctorInfo.class);

                    if (doctorInfo.getMobile().equals(mobile)) {
                        if (doctorInfo.getPassword().equals(password)) {
                            Toast.makeText(DoctorLoginActivity.this, "Logged in successfully...", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(DoctorLoginActivity.this, DoctorHomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PrevalentDoctor.currentOnlineDoctor = doctorInfo;
                            startActivity(intent);
                        } else {
                            Toast.makeText(DoctorLoginActivity.this, "Password is incorrect...", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(DoctorLoginActivity.this, "Account with this " + mobile + "number does not exists.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        }


}

