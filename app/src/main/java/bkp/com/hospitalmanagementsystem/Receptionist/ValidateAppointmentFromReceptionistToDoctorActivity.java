package bkp.com.hospitalmanagementsystem.Receptionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class ValidateAppointmentFromReceptionistToDoctorActivity extends AppCompatActivity {

    private TextView txtAid, txtName, txtProblem, txtNotes, txtStatus;
    private EditText edtDepartment, edtWard, edtFloor;
    private Button submitBtn;

    private String appointmentID = "";
    private DatabaseReference appointmentsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_appointment_from_receptionist_to_doctor);

        appointmentID = getIntent().getStringExtra("aid");
        appointmentsRef = FirebaseDatabase.getInstance().getReference().child("Appointment").child(appointmentID);

        txtAid = findViewById(R.id.vafrtd_aid);
        txtName = findViewById(R.id.vafrtd_name);
        txtProblem = findViewById(R.id.vafrtd_problems);
        txtNotes = findViewById(R.id.vafrtd_notes);
        edtDepartment = findViewById(R.id.vafrtd_department);
        edtWard = findViewById(R.id.vafrtd_ward);
        edtFloor = findViewById(R.id.vafrtd_floor);
        txtStatus = findViewById(R.id.vafrtd_status);
        submitBtn = findViewById(R.id.vafrtd_submit);

        displaySpecificAppointmentInfo();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });



    }

    private void applyChanges() {


        String aDepartment = edtDepartment.getText().toString();
        String aWard = edtWard.getText().toString();
        String aFloor = edtFloor.getText().toString();

        if(aDepartment.equals("")){
            Toast.makeText(this, "Please mention department.", Toast.LENGTH_SHORT).show();
        }else if(aWard.equals("")){
            Toast.makeText(this, "Please mention ward", Toast.LENGTH_SHORT).show();
        }else if(aFloor.equals("")){
            Toast.makeText(this, "Please mention floor", Toast.LENGTH_SHORT).show();
        }else{

            HashMap<String, Object>mp = new HashMap<>();
            mp.put("aid",appointmentID);
            mp.put("status","waiting");
            mp.put("department",aDepartment);
            mp.put("ward",aWard);
            mp.put("floor",aFloor);

            appointmentsRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ValidateAppointmentFromReceptionistToDoctorActivity.this, "Doctor assigned successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ValidateAppointmentFromReceptionistToDoctorActivity.this,ViewAppointmentReceptionistActivity.class);
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
                    String aDepartment = dataSnapshot.child("department").getValue().toString();
                    String aWard = dataSnapshot.child("ward").getValue().toString();
                    String aFloor = dataSnapshot.child("floor").getValue().toString();

                    txtAid.setText("Appointment ID :- " + aAid);
                    txtName.setText("Name :- " + aName);
                    txtProblem.setText("Problem :- " + aProblem);
                    txtNotes.setText("Notes :- " + aNotes);
                    txtStatus.setText("Status :- " + aStatus);

                    edtDepartment.setText("Department :- " + aDepartment);
                    edtWard.setText("Ward :- " + aWard);
                    edtFloor.setText("Floor :- " + aFloor);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
