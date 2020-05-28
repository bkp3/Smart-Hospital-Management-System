package bkp.com.hospitalmanagementsystem.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.MainActivity;
import bkp.com.hospitalmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PatientRegistrationActivity extends AppCompatActivity {

    private EditText nameEdt,mobileEdt, emailEdt, passwordEdt;
    private Button registerPatientBtn;
    private TextView alreadyAccountPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);

        nameEdt = findViewById(R.id.patient_name);
        mobileEdt = findViewById(R.id.patient_mobile);
        emailEdt = findViewById(R.id.patient_email);
        passwordEdt = findViewById(R.id.patient_password);

        registerPatientBtn = findViewById(R.id.create_account_patient_btn);
        alreadyAccountPatient = findViewById(R.id.already_account_patient);

        alreadyAccountPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientRegistrationActivity.this, PatientLoginActivity.class);
                startActivity(intent);
            }
        });

        registerPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPatient();
            }
        });



    }

    private void registerPatient() {

        String name = nameEdt.getText().toString();
        String mobile= mobileEdt.getText().toString();
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();



        if(name.isEmpty()){
            Toast.makeText(this, "fill name", Toast.LENGTH_SHORT).show();
        }else if(mobile.isEmpty()){
            Toast.makeText(this, "fill mobile number", Toast.LENGTH_SHORT).show();
        }else if(email.isEmpty()){
            Toast.makeText(this, "fill email", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(this, "fill password", Toast.LENGTH_SHORT).show();
        }else{

            validatingMobile(name,mobile,email,password);

        }


    }

    private void validatingMobile(final String name, final String mobile, final String email, final String password) {

        final DatabaseReference dataRef;
        dataRef = FirebaseDatabase.getInstance().getReference();
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("PatientInfo").child(mobile).exists())){

                    HashMap<String, Object> mp = new HashMap<>();
                    mp.put("name",name);
                    mp.put("mobile",mobile);
                    mp.put("email",email);
                    mp.put("password",password);

                    dataRef.child("PatientInfo").child(mobile).updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(PatientRegistrationActivity.this,"Account created successfully...",Toast.LENGTH_LONG).show();
                                Toast.makeText(PatientRegistrationActivity.this,"Now you can log in...",Toast.LENGTH_LONG).show();

                                nameEdt.setText("");
                                mobileEdt.setText("");
                                emailEdt.setText("");
                                passwordEdt.setText("");

                                Intent intent = new Intent(PatientRegistrationActivity.this, PatientLoginActivity.class);
                                startActivity(intent);


                            }else{

                                Toast.makeText(PatientRegistrationActivity.this,"Network error: Please try again after sometimes...",Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                }else{

                    Toast.makeText(PatientRegistrationActivity.this,"this email already exists",Toast.LENGTH_LONG).show();

                    Toast.makeText(PatientRegistrationActivity.this,"Please try again using another email",Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(PatientRegistrationActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}

