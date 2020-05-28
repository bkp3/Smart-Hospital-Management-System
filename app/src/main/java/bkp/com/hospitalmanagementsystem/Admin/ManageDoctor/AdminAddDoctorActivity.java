package bkp.com.hospitalmanagementsystem.Admin.ManageDoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminAddDoctorActivity extends AppCompatActivity {

    private EditText nameEdt, mobileEdt, qualificationEdt, emailEdt, passwordEdt, hospitalAddressEdt;
    private Button addDoctorBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_doctor);



        nameEdt = findViewById(R.id.add_doctor_name);
        mobileEdt = findViewById(R.id.add_doctor_mobile);
        qualificationEdt = findViewById(R.id.add_doctor_qualification);
        emailEdt = findViewById(R.id.add_doctor_email);
        passwordEdt = findViewById(R.id.add_doctor_password);
        hospitalAddressEdt = findViewById(R.id.add_doctor_hospital_address);

        addDoctorBtn = findViewById(R.id.add_doctor_btn);

        addDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();

            }
        });



    }

    private void saveData() {

        String name = nameEdt.getText().toString();
        String mobile= mobileEdt.getText().toString();
        String qualification = qualificationEdt.getText().toString();
        String email = emailEdt.getText().toString();
        String password = passwordEdt.getText().toString();
        String hospitalAddress = hospitalAddressEdt.getText().toString();



        if(name.isEmpty()){
            Toast.makeText(this, "fill name", Toast.LENGTH_SHORT).show();
        }else if(mobile.isEmpty()){
            Toast.makeText(this, "fill mobile number", Toast.LENGTH_SHORT).show();
        }else if(qualification.isEmpty()){
            Toast.makeText(this, "fill qualification", Toast.LENGTH_SHORT).show();
        }else if(email.isEmpty()){
            Toast.makeText(this, "fill email", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(this, "fill password", Toast.LENGTH_SHORT).show();
        }else if(hospitalAddress.isEmpty()){
            Toast.makeText(this, "fill hospital address", Toast.LENGTH_SHORT).show();
        }else{

            validatingEmail(name,mobile,qualification,email,password,hospitalAddress);

        }


    }

    private void validatingEmail(final String name, final String mobile, final String qualification, final String email, final String password, final String hospitalAddress) {

        final  DatabaseReference dataRef;
        dataRef = FirebaseDatabase.getInstance().getReference();
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("DoctorInfo").child(mobile).exists())){

                    HashMap<String, Object>mp = new HashMap<>();
                    mp.put("name",name);
                    mp.put("mobile",mobile);
                    mp.put("qualification",qualification);
                    mp.put("email",email);
                    mp.put("password",password);
                    mp.put("hospitalAddress",hospitalAddress);

                    dataRef.child("DoctorInfo").child(mobile).updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(AdminAddDoctorActivity.this,"Doctor data is added...",Toast.LENGTH_LONG).show();

                                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password);

                                nameEdt.setText("");
                                mobileEdt.setText("");
                                qualificationEdt.setText("");
                                emailEdt.setText("");
                                passwordEdt.setText("");
                                hospitalAddressEdt.setText("");

                                Intent intent = new Intent(AdminAddDoctorActivity.this, AdminManageDoctorActivity.class);
                                startActivity(intent);


                            }else{

                                Toast.makeText(AdminAddDoctorActivity.this,"Network error: Please try again after sometimes...",Toast.LENGTH_LONG).show();

                            }

                        }
                    });
                }else{

                    Toast.makeText(AdminAddDoctorActivity.this,"this email already exists",Toast.LENGTH_LONG).show();

                    Toast.makeText(AdminAddDoctorActivity.this,"Please try again using another email",Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(AdminAddDoctorActivity.this, AdminManageDoctorActivity.class);
                    startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
