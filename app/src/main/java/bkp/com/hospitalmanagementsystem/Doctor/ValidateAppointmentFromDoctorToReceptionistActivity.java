package bkp.com.hospitalmanagementsystem.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.Receptionist.ValidateAppointmentFromReceptionistToDoctorActivity;
import bkp.com.hospitalmanagementsystem.Receptionist.ViewAppointmentReceptionistActivity;

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

public class ValidateAppointmentFromDoctorToReceptionistActivity extends AppCompatActivity {

    private TextView txtAid, txtName, txtProblem, txtNotes, txtStatus;
    private EditText edtMedicine, edtPrecautions;
    private Button submitBtn;

    private String appointmentID = "";
    private DatabaseReference appointmentsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_appointment_from_doctor_to_receptionist);

        appointmentID = getIntent().getStringExtra("aid");
        appointmentsRef = FirebaseDatabase.getInstance().getReference().child("Appointment").child(appointmentID);

        txtAid = findViewById(R.id.vafdtr_aid);
        txtName = findViewById(R.id.vafdtr_name);
        txtProblem = findViewById(R.id.vafdtr_problems);
        txtNotes = findViewById(R.id.vafdtr_notes);
        edtMedicine = findViewById(R.id.vafdtr_medicine);
        edtPrecautions = findViewById(R.id.vafdtr_precaution);
        txtStatus = findViewById(R.id.vafdtr_status);
        submitBtn = findViewById(R.id.vafdtr_submit);

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
            Toast.makeText(this, "Please mention Medicine.", Toast.LENGTH_SHORT).show();
        }else if(aPrecautions.equals("")){
            Toast.makeText(this, "Please mention Precautions", Toast.LENGTH_SHORT).show();
        }else{

            HashMap<String, Object> mp = new HashMap<>();
            mp.put("aid",appointmentID);
            mp.put("status","billDues");
            mp.put("medicine",aMedicine);
            mp.put("precautions",aPrecautions);

            DatabaseReference prescriptionsRef;
            prescriptionsRef = FirebaseDatabase.getInstance().getReference().child("Prescription").child(appointmentID);

            prescriptionsRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        FirebaseDatabase.getInstance().getReference().child("Appointment").child(appointmentID).child("status").setValue("billDues");

                        Toast.makeText(ValidateAppointmentFromDoctorToReceptionistActivity.this, "Task successful.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ValidateAppointmentFromDoctorToReceptionistActivity.this, ViewAppointmentDoctorActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });


        }




    }

    private void displaySpecificAppointmentInfo() {

        appointmentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String aAid = dataSnapshot.child("aid").getValue().toString();
                    String aName = dataSnapshot.child("name").getValue().toString();
                    String aProblem = dataSnapshot.child("problem").getValue().toString();
                    String aNotes = dataSnapshot.child("notes").getValue().toString();
                    String aStatus = dataSnapshot.child("status").getValue().toString();

                    txtAid.setText("Appointment ID :- " + aAid);
                    txtName.setText("Name :- " + aName);
                    txtProblem.setText("Problem :- " + aProblem);
                    txtNotes.setText("Notes :- " + aNotes);
                    txtStatus.setText("Status :- " + aStatus);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
