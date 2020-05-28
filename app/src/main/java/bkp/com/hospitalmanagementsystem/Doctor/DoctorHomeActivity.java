package bkp.com.hospitalmanagementsystem.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.Admin.AdminHomeActivity;
import bkp.com.hospitalmanagementsystem.MainActivity;
import bkp.com.hospitalmanagementsystem.PrevalentDoctor.PrevalentDoctor;
import bkp.com.hospitalmanagementsystem.R;
import io.paperdb.Paper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DoctorHomeActivity extends AppCompatActivity {

    private TextView viewAppointmentTxt, viewPrescriptionTxt, nameDoctorTxt;
    private Button logoutDoctorBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        Paper.init(this);

        nameDoctorTxt = findViewById(R.id.name_doctor);
        viewAppointmentTxt = findViewById(R.id.view_appointment_doctor);
        viewPrescriptionTxt = findViewById(R.id.view_prescription_doctor);
        logoutDoctorBtn = findViewById(R.id.logout_doctor);

        nameDoctorTxt.setText(PrevalentDoctor.currentOnlineDoctor.getName());

        viewAppointmentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this, ViewAppointmentDoctorActivity.class);
                startActivity(intent);
            }
        });

        viewPrescriptionTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorHomeActivity.this, ViewPrescriptionDoctorActivity.class);
                startActivity(intent);
            }
        });

        logoutDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().destroy();
                Intent intent = new Intent(DoctorHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });

    }
}
