package bkp.com.hospitalmanagementsystem.Admin.ManageReceptionist;

import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminManageReceptionistActivity extends AppCompatActivity {
    private TextView addReceptionistTxt, viewReceptionisttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_receptionist);

        addReceptionistTxt = findViewById(R.id.add_receptionist_admin);
        viewReceptionisttxt = findViewById(R.id.view_receptionist_admin);

        addReceptionistTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminManageReceptionistActivity.this,AdminAddReceptionistActivity.class);
                startActivity(intent);
            }
        });
    }
}
