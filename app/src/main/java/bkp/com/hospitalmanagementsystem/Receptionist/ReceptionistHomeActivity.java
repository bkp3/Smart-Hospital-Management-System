package bkp.com.hospitalmanagementsystem.Receptionist;

import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.Doctor.DoctorHomeActivity;
import bkp.com.hospitalmanagementsystem.MainActivity;
import bkp.com.hospitalmanagementsystem.PrevalentDoctor.PrevalentDoctor;
import bkp.com.hospitalmanagementsystem.PrevalentReceptionist.PrevalentReceptionist;
import bkp.com.hospitalmanagementsystem.R;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReceptionistHomeActivity extends AppCompatActivity {

    private TextView postPharmacyTxt, viewPharmacyTxt, viewComplaintTxt, viewStatusTxt, nameReceptionistTxt;
    private Button logoutReceptionistBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptionist_home);

        Paper.init(this);

        nameReceptionistTxt = findViewById(R.id.name_receptionist);




        logoutReceptionistBtn = findViewById(R.id.logout_receptionist);

        nameReceptionistTxt.setText(PrevalentReceptionist.currentOnlineReceptionist.getName());

        logoutReceptionistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.book().destroy();
                Intent intent = new Intent(ReceptionistHomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });
    }
}
