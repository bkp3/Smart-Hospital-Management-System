package bkp.com.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.Aboutus.AboutusRegistrationActivity;
import bkp.com.hospitalmanagementsystem.Admin.AdminLoginActivity;
import bkp.com.hospitalmanagementsystem.Doctor.DoctorLoginActivity;
import bkp.com.hospitalmanagementsystem.Patient.PatientRegistrationActivity;
import bkp.com.hospitalmanagementsystem.Receptionist.ReceptionistLoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout admin,doctor,patient,receptionist,clinic,aboutus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = findViewById(R.id.admin_llh);
        doctor = findViewById(R.id.doctor_llh);
        patient = findViewById(R.id.patient_llh);
        receptionist = findViewById(R.id.receptionist_llh);

        aboutus = findViewById(R.id.aboutus_llh);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);

            }
        });

        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatientRegistrationActivity.class);
                startActivity(intent);

            }
        });

        receptionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReceptionistLoginActivity.class);
                startActivity(intent);

            }
        });



        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutusRegistrationActivity.class);
                startActivity(intent);

            }
        });
    }
}
