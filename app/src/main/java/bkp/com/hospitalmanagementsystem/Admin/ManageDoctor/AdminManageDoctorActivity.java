package bkp.com.hospitalmanagementsystem.Admin.ManageDoctor;

import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminManageDoctorActivity extends AppCompatActivity {

    private TextView addDoctorTxt, viewDoctortxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_doctor);

        addDoctorTxt = findViewById(R.id.add_doctor_admin);
        viewDoctortxt = findViewById(R.id.view_doctor_admin);


        addDoctorTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminManageDoctorActivity.this, AdminAddDoctorActivity.class);
                startActivity(intent);

            }
        });
        viewDoctortxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminManageDoctorActivity.this, AdminViewDoctorActivity.class);
                startActivity(intent);
            }
        });








    }
}
