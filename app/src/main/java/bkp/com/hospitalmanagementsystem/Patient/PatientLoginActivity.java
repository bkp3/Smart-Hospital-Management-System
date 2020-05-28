package bkp.com.hospitalmanagementsystem.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.PrevalentPatient.PrevalentPatient;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.model.PatientInfo;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientLoginActivity extends AppCompatActivity {

    private EditText mobileEdtPatient, passwordEdtPatient;
    private Button loginBtnPatient;
    private TextView newUserPatient;

    private String parentdbName="PatientInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        Paper.init(this);

        mobileEdtPatient = findViewById(R.id.mobile_number_patient);
        passwordEdtPatient = findViewById(R.id.password_patient);
        loginBtnPatient = findViewById(R.id.login_patient);
        newUserPatient = findViewById(R.id.new_user_patient);

        newUserPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginActivity.this,PatientRegistrationActivity.class);
                startActivity(intent);
            }
        });

        loginBtnPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPatient();
            }
        });
    }

    private void loginPatient() {

        final String mobile = mobileEdtPatient.getText().toString();
        final String password = passwordEdtPatient.getText().toString();

        if(mobile.equals("") && password.equals("")){
            Toast.makeText(this, "Invalid information.", Toast.LENGTH_SHORT).show();
        }else{
            allowAccessToAccountPatient(mobile,password);
        }


    }

    private void allowAccessToAccountPatient(final String mobile, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentdbName).child(mobile).exists()) {

                    PatientInfo patientInfo = dataSnapshot.child(parentdbName).child(mobile).getValue(PatientInfo.class);

                    if (patientInfo.getMobile().equals(mobile)) {
                        if (patientInfo.getPassword().equals(password)) {

                            Toast.makeText(PatientLoginActivity.this, "Logged in successfully...", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(PatientLoginActivity.this, PatientHomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PrevalentPatient.currentOnlinePatient = patientInfo;
                            startActivity(intent);

                        } else {
                            Toast.makeText(PatientLoginActivity.this, "Password is incorrect...", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(PatientLoginActivity.this, "Account with this " + mobile + "number does not exists.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
