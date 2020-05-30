package bkp.com.hospitalmanagementsystem.Receptionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.Admin.ManageReceptionist.AdminEditReceptionistActivity;
import bkp.com.hospitalmanagementsystem.Admin.ManageReceptionist.AdminViewReceptionistActivity;
import bkp.com.hospitalmanagementsystem.PrevalentReceptionist.PrevalentReceptionist;
import bkp.com.hospitalmanagementsystem.R;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ViewProfileReceptionistActivity extends AppCompatActivity {

    private EditText edtPhone, edtName,edtEmail,edtQualification,edtPassword,edtAddress;
    private Button submitBtn;

    private String phoneID = "";
    private DatabaseReference ReceptionistRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_receptionist);

        Paper.init(this);

        phoneID = PrevalentReceptionist.currentOnlineReceptionist.getMobile();
        ReceptionistRef = FirebaseDatabase.getInstance().getReference().child("ReceptionistInfo").child(phoneID);

        edtPhone = findViewById(R.id.vpr_phone);
        edtName = findViewById(R.id.vpr_name);
        edtEmail = findViewById(R.id.vpr_email);
        edtQualification = findViewById(R.id.vpr_qualification);
        edtPassword = findViewById(R.id.vpr_password);
        edtAddress = findViewById(R.id.vpr_address);
        submitBtn = findViewById(R.id.vpr_submit);

        displaySpecificAppointmentInfo();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });
    }

    private void applyChanges() {

        String aPhone = edtPhone.getText().toString();
        String aName = edtName.getText().toString();
        String aEmail = edtEmail.getText().toString();
        String aQaulification = edtQualification.getText().toString();
        String aPassword = edtPassword.getText().toString();
        String aAddress = edtAddress.getText().toString();

        if(aPhone.equals("")){
            Toast.makeText(this, "fill phone.", Toast.LENGTH_SHORT).show();
        }else if(aName.equals("")){
            Toast.makeText(this, "fill name", Toast.LENGTH_SHORT).show();
        }else if(aEmail.equals("")){
            Toast.makeText(this, "fill email", Toast.LENGTH_SHORT).show();
        }else if(aQaulification.equals("")){
            Toast.makeText(this, "fill qualification", Toast.LENGTH_SHORT).show();
        }else if(aPassword.equals("")){
            Toast.makeText(this, "fill password", Toast.LENGTH_SHORT).show();
        }else if(aAddress.equals("")){
            Toast.makeText(this, "fill address", Toast.LENGTH_SHORT).show();
        }else{

            HashMap<String, Object> mp = new HashMap<>();
            mp.put("mobile",phoneID);
            mp.put("name",aName);
            mp.put("email",aEmail);
            mp.put("qualification",aQaulification);
            mp.put("password",aPassword);
            mp.put("hospitalAddress",aAddress);


            ReceptionistRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(ViewProfileReceptionistActivity.this, "update successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ViewProfileReceptionistActivity.this, ReceptionistHomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });


        }

    }



    private void displaySpecificAppointmentInfo() {

        ReceptionistRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String aPhone = dataSnapshot.child("mobile").getValue().toString();
                    String aName = dataSnapshot.child("name").getValue().toString();
                    String aEmail = dataSnapshot.child("email").getValue().toString();
                    String aQualification = dataSnapshot.child("qualification").getValue().toString();
                    String aPassword = dataSnapshot.child("password").getValue().toString();
                    String aAddress = dataSnapshot.child("hospitalAddress").getValue().toString();

                    edtPhone.setText(aPhone);
                    edtName.setText(aName);
                    edtEmail.setText(aEmail);
                    edtQualification.setText(aQualification);
                    edtPassword.setText(aPassword);
                    edtAddress.setText(aAddress);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
