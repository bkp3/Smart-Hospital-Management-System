package bkp.com.hospitalmanagementsystem.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.Patient.EditAppointmentPatient;
import bkp.com.hospitalmanagementsystem.Patient.PatientHomeActivity;
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

public class EditPrescriptionDoctorActivity extends AppCompatActivity {

    private TextView txtAid, txtStatus;
    private EditText edtMedicine, edtPrecautions;
    private Button submitBtn;

    private String appointmentID = "";
    private DatabaseReference prescriptionsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_prescription_doctor);

        appointmentID = getIntent().getStringExtra("aid");
        prescriptionsRef = FirebaseDatabase.getInstance().getReference().child("Prescription").child(appointmentID);

        txtAid = findViewById(R.id.epd_aid);
        txtStatus = findViewById(R.id.epd_status);
        edtMedicine = findViewById(R.id.epd_medicine);
        edtPrecautions = findViewById(R.id.epd_precautions);
        submitBtn = findViewById(R.id.epd_submit);

        displaySpecificAppointmentInfo();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });


    }

    private void applyChanges() {

        String aMedicine = edtMedicine.getText().toString();
        String aPrecautions = edtPrecautions.getText().toString();




        if(aMedicine.equals("")){
            Toast.makeText(this, "Please mention medicine.", Toast.LENGTH_SHORT).show();
        }else if(aPrecautions.equals("")){
            Toast.makeText(this, "Please mention precautions", Toast.LENGTH_SHORT).show();
        }else{

            HashMap<String, Object> mp = new HashMap<>();
            mp.put("aid",appointmentID);
            mp.put("status","billDues");
            mp.put("medicine",aMedicine);
            mp.put("precautions",aPrecautions);

            prescriptionsRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(EditPrescriptionDoctorActivity.this, "Prescription Updated successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditPrescriptionDoctorActivity.this, DoctorHomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });


        }


    }

    private void displaySpecificAppointmentInfo() {

        prescriptionsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String aAid = dataSnapshot.child("aid").getValue().toString();
                    String aStatus = dataSnapshot.child("status").getValue().toString();
                    String aMedicine = dataSnapshot.child("medicine").getValue().toString();
                    String aPrecautions = dataSnapshot.child("precautions").getValue().toString();

                    txtAid.setText("Appointment ID :- " + aAid);
                    txtStatus.setText("Status :- " + aStatus);
                    edtMedicine.setText(aMedicine);
                    edtPrecautions.setText(aPrecautions);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
