package bkp.com.hospitalmanagementsystem.Admin;

import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.Admin.ManageDoctor.AdminManageDoctorActivity;
import bkp.com.hospitalmanagementsystem.Admin.ManageReceptionist.AdminManageReceptionistActivity;
import bkp.com.hospitalmanagementsystem.MainActivity;
import bkp.com.hospitalmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class AdminHomeActivity extends AppCompatActivity {

    private TextView doctorTxt, receptionistTxt, cashierTxt, pharmacyTxt;
    private Button logoutBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        mAuth =FirebaseAuth.getInstance();

        doctorTxt = findViewById(R.id.manage_doctor_admin);
        receptionistTxt = findViewById(R.id.manage_receptionist_admin);
        logoutBtn = findViewById(R.id.admin_logout);

        doctorTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminHomeActivity.this, AdminManageDoctorActivity.class);
                startActivity(intent);
            }
        });

        receptionistTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminHomeActivity.this, AdminManageReceptionistActivity.class);
                startActivity(intent);
            }
        });







        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                Toast.makeText(AdminHomeActivity.this, "Logged out.", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
}
