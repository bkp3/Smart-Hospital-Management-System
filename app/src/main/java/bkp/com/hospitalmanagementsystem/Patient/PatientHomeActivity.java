package bkp.com.hospitalmanagementsystem.Patient;

import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.MainActivity;
import bkp.com.hospitalmanagementsystem.PrevalentPatient.PrevalentPatient;
import bkp.com.hospitalmanagementsystem.R;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientHomeActivity extends AppCompatActivity {

    private TextView newAppointmentTxt, viewAppointmentTxt,viewPrescriptionTxt,viewBillTxt, viewProfileTxt, namePatientTxt;
    private Button logoutPatientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        Paper.init(this);

        namePatientTxt = findViewById(R.id.display_name_patient);
        newAppointmentTxt = findViewById(R.id.new_appointment_patient);
        viewAppointmentTxt = findViewById(R.id.view_appointment_patient);
        viewPrescriptionTxt = findViewById(R.id.view_prescription_patient);
        viewBillTxt = findViewById(R.id.view_bill_patient);
        viewProfileTxt = findViewById(R.id.view_profile_patient);
        logoutPatientBtn = findViewById(R.id.logout_patient);

        namePatientTxt.setText("Welcome " + PrevalentPatient.currentOnlinePatient.getName());

        newAppointmentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, NewAppointmentPatientActivity.class);
                startActivity(intent);

            }
        });

        viewAppointmentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, ViewAppointmentPatientActivity.class);
                startActivity(intent);

            }
        });

        viewPrescriptionTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, ViewPrescriptionPatientActivity.class);
                startActivity(intent);

            }
        });

        viewBillTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, ViewBillPatientActivity.class);
                startActivity(intent);

            }
        });
        viewProfileTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientHomeActivity.this, ViewProfilePatientActivity.class);
                startActivity(intent);
            }
        });



        logoutPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().destroy();
                Intent intent = new Intent(PatientHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });
    }
}
