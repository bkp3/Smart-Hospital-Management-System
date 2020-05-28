package bkp.com.hospitalmanagementsystem.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.CounterId;
import bkp.com.hospitalmanagementsystem.PrevalentPatient.PrevalentPatient;
import bkp.com.hospitalmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewAppointmentPatientActivity extends AppCompatActivity {

    private EditText idEdt, nameEdt, fNameEdt, ageEdt, genderEdt, phoneEdt, addressEdt, problemEdt, noteEdt;
    private Button submitBtn;


    CounterId cId = new CounterId();
    private int x = cId.getCounter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment_patient);

        idEdt = findViewById(R.id.patient_id_post);
        nameEdt = findViewById(R.id.patient_name_post);
        fNameEdt = findViewById(R.id.patient_father_name_post);
        ageEdt = findViewById(R.id.patient_age_post);
        genderEdt = findViewById(R.id.patient_gender_post);
//        phoneEdt = findViewById(R.id.patient_mobile_post);
//        addressEdt = findViewById(R.id.patient_address_post);
        problemEdt = findViewById(R.id.patient_problem_post);
        noteEdt = findViewById(R.id.patient_notes_post);

        submitBtn = findViewById(R.id.patient_complaint_post);



        idEdt.setText("ID :- " + x);



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
            }
        });

    }

    private void saveData() {

        String name = nameEdt.getText().toString();
        String fname = fNameEdt.getText().toString();
        String age = ageEdt.getText().toString();
        String gender = genderEdt.getText().toString();

        String problem = problemEdt.getText().toString();
        String note = noteEdt.getText().toString();

        if(name.isEmpty()){
            Toast.makeText(this, "fill name", Toast.LENGTH_SHORT).show();
        }else if(fname.isEmpty()){
            Toast.makeText(this, "fill father name", Toast.LENGTH_SHORT).show();
        }else if(age.isEmpty()){
            Toast.makeText(this, "fill age", Toast.LENGTH_SHORT).show();
        }else if(gender.isEmpty()){
            Toast.makeText(this, "fill gender", Toast.LENGTH_SHORT).show();
        }else if(problem.isEmpty()){
            Toast.makeText(this, "Please state problems", Toast.LENGTH_SHORT).show();
        }else if(note.isEmpty()){
            Toast.makeText(this, "fill other notes", Toast.LENGTH_SHORT).show();
        }else{
            validatingMobile(name,fname,age,gender,problem,note);

        }



    }

    private void validatingMobile(final String name, final String fname, final String age, final String gender, final String problem, final String note) {

        final DatabaseReference dataRef;
        dataRef = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> mp = new HashMap<>();
        mp.put("id",x+"");
        mp.put("name",name);
        mp.put("fname",fname);
        mp.put("age",age);
        mp.put("gender",gender);
        mp.put("phone",PrevalentPatient.currentOnlinePatient.getMobile());
        mp.put("problem",problem);
        mp.put("notes",note);

        mp.put("status","waiting");

        dataRef.child("Appointment").child(String.valueOf(x)).updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){

                    x = x + 1;
                    cId.setCounter(x);

                    Toast.makeText(NewAppointmentPatientActivity.this,"Complaint posted successfully...",Toast.LENGTH_LONG).show();
                    //Toast.makeText(NewAppointmentPatientActivity.this,"please wait sometimes...",Toast.LENGTH_LONG).show();

                    idEdt.setText("ID :- " + x);
                    nameEdt.setText("");
                    fNameEdt.setText("");
                    ageEdt.setText("");
                    genderEdt.setText("");
                    problemEdt.setText("");
                    nameEdt.setText("");


                    Intent intent = new Intent(NewAppointmentPatientActivity.this, PatientHomeActivity.class);
                    startActivity(intent);


                }else{

                    Toast.makeText(NewAppointmentPatientActivity.this,"Network error: Please try again after sometimes...",Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}
