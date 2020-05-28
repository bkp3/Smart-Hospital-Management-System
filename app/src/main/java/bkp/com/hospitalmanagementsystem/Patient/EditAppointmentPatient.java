package bkp.com.hospitalmanagementsystem.Patient;

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

public class EditAppointmentPatient extends AppCompatActivity {

    private  TextView txtAid;
    private EditText edtName, edtFname, edtAge,edtGender,edtProblem, edtNote;
    private Button submitBtn;

    private String appointmentID = "";
    private DatabaseReference appointmentsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment_patient);

        appointmentID = getIntent().getStringExtra("aid");
        appointmentsRef = FirebaseDatabase.getInstance().getReference().child("Appointment").child(appointmentID);

        txtAid = findViewById(R.id.eap_aid);
        edtName = findViewById(R.id.eap_name);
        edtFname = findViewById(R.id.eap_fname);
        edtAge = findViewById(R.id.eap_age);
        edtGender = findViewById(R.id.eap_gender);
        edtProblem = findViewById(R.id.eap_problem);
        edtNote = findViewById(R.id.eap_notes);

        submitBtn = findViewById(R.id.eap_submit);

        displaySpecificAppointmentInfo();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });

    }

    private void applyChanges() {

        String aName = edtName.getText().toString();
        String aFname = edtFname.getText().toString();
        String aAge = edtAge.getText().toString();
        String aGender = edtGender.getText().toString();
        String aProblem = edtProblem.getText().toString();
        String aNotes = edtNote.getText().toString();



        if(aName.equals("")){
            Toast.makeText(this, "Please mention name.", Toast.LENGTH_SHORT).show();
        }else if(aFname.equals("")){
            Toast.makeText(this, "Please mention father name", Toast.LENGTH_SHORT).show();
        }else if(aAge.equals("")){
            Toast.makeText(this, "Please mention age", Toast.LENGTH_SHORT).show();
        }else if(aGender.equals("")){
            Toast.makeText(this, "Please mention gender", Toast.LENGTH_SHORT).show();
        }else if(aProblem.equals("")){
            Toast.makeText(this, "Please mention problem", Toast.LENGTH_SHORT).show();
        }else if(aNotes.equals("")){
            Toast.makeText(this, "Please mention notes", Toast.LENGTH_SHORT).show();
        }else{

            HashMap<String, Object> mp = new HashMap<>();
            mp.put("aid",appointmentID);
            mp.put("name",aName);
            mp.put("fname",aFname);
            mp.put("age",aAge);
            mp.put("gender",aGender);
            mp.put("problem",aProblem);
            mp.put("notes",aNotes);

            appointmentsRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(EditAppointmentPatient.this, "Appointment Updated successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditAppointmentPatient.this, PatientHomeActivity.class);
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
                    String aFname = dataSnapshot.child("fname").getValue().toString();
                    String aAge = dataSnapshot.child("age").getValue().toString();
                    String aGender = dataSnapshot.child("gender").getValue().toString();
                    String aProblem = dataSnapshot.child("problem").getValue().toString();
                    String aNotes = dataSnapshot.child("notes").getValue().toString();

                    txtAid.setText("Appointment ID :- " + aAid);
                    edtName.setText(aName);
                    edtFname.setText(aFname);
                    edtAge.setText(aAge);
                    edtGender.setText(aGender);
                    edtProblem.setText(aProblem);
                    edtNote.setText(aNotes);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
