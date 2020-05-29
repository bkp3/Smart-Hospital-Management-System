package bkp.com.hospitalmanagementsystem.Admin.ManageDoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.Receptionist.PaymentReceptionistActivity;
import bkp.com.hospitalmanagementsystem.Receptionist.ReceptionistHomeActivity;

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

public class AdminEditDoctorActivity extends AppCompatActivity {


    private EditText edtPhone, edtName,edtEmail,edtQualification,edtPassword,edtAddress;
    private Button submitBtn;

    private String phoneID = "";
    private DatabaseReference DoctorRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_doctor);

        phoneID = getIntent().getStringExtra("phid");
        DoctorRef = FirebaseDatabase.getInstance().getReference().child("DoctorInfo").child(phoneID);

        edtPhone = findViewById(R.id.aed_phone);
        edtName = findViewById(R.id.aed_name);
        edtEmail = findViewById(R.id.aed_email);
        edtQualification = findViewById(R.id.aed_qualification);
        edtPassword = findViewById(R.id.aed_password);
        edtAddress = findViewById(R.id.aed_address);
        submitBtn = findViewById(R.id.aed_submit);

        displaySpecificAppointmentInfo();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });

    }

    private void applyChanges() {

        String aPhone = edtPhone.getText().toString();
        String aName = edtName.getText().toString();
        String aEmail = edtEmail.getText().toString();
        String aQaulification = edtQualification.getText().toString();
        String aPassword = edtPassword.getText().toString();
        String aAddress = edtAddress.getText().toString();

        if(aPhone.equals("")){
            Toast.makeText(this, "fill phone.", Toast.LENGTH_SHORT).show();
        }else if(aName.equals("")){
            Toast.makeText(this, "fill name", Toast.LENGTH_SHORT).show();
        }else if(aEmail.equals("")){
            Toast.makeText(this, "fill email", Toast.LENGTH_SHORT).show();
        }else if(aQaulification.equals("")){
            Toast.makeText(this, "fill qualification", Toast.LENGTH_SHORT).show();
        }else if(aPassword.equals("")){
            Toast.makeText(this, "fill password", Toast.LENGTH_SHORT).show();
        }else if(aAddress.equals("")){
            Toast.makeText(this, "fill address", Toast.LENGTH_SHORT).show();
        }else{

            HashMap<String, Object> mp = new HashMap<>();
            mp.put("mobile",phoneID);
            mp.put("name",aName);
            mp.put("email",aEmail);
            mp.put("qualification",aQaulification);
            mp.put("password",aPassword);
            mp.put("hospitalAddress",aAddress);


            DoctorRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(AdminEditDoctorActivity.this, "update successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminEditDoctorActivity.this, AdminViewDoctorActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });


        }

    }

    private void displaySpecificAppointmentInfo() {

        DoctorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String aPhone = dataSnapshot.child("mobile").getValue().toString();
                    String aName = dataSnapshot.child("name").getValue().toString();
                    String aEmail = dataSnapshot.child("email").getValue().toString();
                    String aQualification = dataSnapshot.child("qualification").getValue().toString();
                    String aPassword = dataSnapshot.child("password").getValue().toString();
                    String aAddress = dataSnapshot.child("hospitalAddress").getValue().toString();

                    edtPhone.setText(aPhone);
                    edtName.setText(aName);
                    edtEmail.setText(aEmail);
                    edtQualification.setText(aQualification);
                    edtPassword.setText(aPassword);
                    edtAddress.setText(aAddress);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
