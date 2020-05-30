package bkp.com.hospitalmanagementsystem.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.PrevalentPatient.PrevalentPatient;
import bkp.com.hospitalmanagementsystem.PrevalentReceptionist.PrevalentReceptionist;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.Receptionist.ReceptionistHomeActivity;
import bkp.com.hospitalmanagementsystem.Receptionist.ViewProfileReceptionistActivity;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ViewProfilePatientActivity extends AppCompatActivity {

    private EditText edtPhone, edtName, edtEmail, edtPassword;
    private Button submitBtn;

    private String phoneID = "";
    private DatabaseReference PatientRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_patient);

        Paper.init(this);

        phoneID = PrevalentPatient.currentOnlinePatient.getMobile();
        PatientRef = FirebaseDatabase.getInstance().getReference().child("PatientInfo").child(phoneID);

        edtPhone = findViewById(R.id.vpp_phone);
        edtName = findViewById(R.id.vpp_name);
        edtEmail = findViewById(R.id.vpp_email);
        edtPassword = findViewById(R.id.vpp_password);
        submitBtn = findViewById(R.id.vpp_submit);

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
        String aPassword = edtPassword.getText().toString();

        if (aPhone.equals("")) {
            Toast.makeText(this, "fill phone.", Toast.LENGTH_SHORT).show();
        } else if (aName.equals("")) {
            Toast.makeText(this, "fill name", Toast.LENGTH_SHORT).show();
        } else if (aEmail.equals("")) {
            Toast.makeText(this, "fill email", Toast.LENGTH_SHORT).show();
        } else if (aPassword.equals("")) {
            Toast.makeText(this, "fill password", Toast.LENGTH_SHORT).show();
        } else {

            HashMap<String, Object> mp = new HashMap<>();
            mp.put("mobile", phoneID);
            mp.put("name", aName);
            mp.put("email", aEmail);
            mp.put("password", aPassword);


            PatientRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(ViewProfilePatientActivity.this, "update successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ViewProfilePatientActivity.this, PatientHomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });


        }

    }


    private void displaySpecificAppointmentInfo() {

        PatientRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String aPhone = dataSnapshot.child("mobile").getValue().toString();
                    String aName = dataSnapshot.child("name").getValue().toString();
                    String aEmail = dataSnapshot.child("email").getValue().toString();
                    String aPassword = dataSnapshot.child("password").getValue().toString();

                    edtPhone.setText(aPhone);
                    edtName.setText(aName);
                    edtEmail.setText(aEmail);
                    edtPassword.setText(aPassword);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
