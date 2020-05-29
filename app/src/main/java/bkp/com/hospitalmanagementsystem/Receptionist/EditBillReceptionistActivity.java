package bkp.com.hospitalmanagementsystem.Receptionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import bkp.com.hospitalmanagementsystem.R;

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

import java.sql.Ref;
import java.util.HashMap;

public class EditBillReceptionistActivity extends AppCompatActivity {

    private TextView txtAid, txtStatus;
    private EditText edtMedicineRates, edtBill;
    private Button submitBtn;

    private String appointmentID = "";
    private DatabaseReference BillRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bill_receptionist);

        appointmentID = getIntent().getStringExtra("aid");
        BillRef = FirebaseDatabase.getInstance().getReference().child("Bill").child(appointmentID);

        txtAid = findViewById(R.id.ebr_aid);
        txtStatus = findViewById(R.id.ebr_status);
        edtBill = findViewById(R.id.ebr_bill);
        edtMedicineRates = findViewById(R.id.ebr_medicinerates);
        submitBtn = findViewById(R.id.ebr_submit);

        displaySpecificAppointmentInfo();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyChanges();
            }
        });
    }

    private void applyChanges() {

        String aMedicineRates = edtMedicineRates.getText().toString();
        String aBill = edtBill.getText().toString();




        if(aMedicineRates.equals("")){
            Toast.makeText(this, "Please mention medicine rates.", Toast.LENGTH_SHORT).show();
        }else if(aBill.equals("")){
            Toast.makeText(this, "Please mention Bill", Toast.LENGTH_SHORT).show();
        }else{

            HashMap<String, Object> mp = new HashMap<>();
            mp.put("aid",appointmentID);
            mp.put("status","success");
            mp.put("medicinerates",aMedicineRates);
            mp.put("bill",aBill);

            DatabaseReference billRef;
            billRef = FirebaseDatabase.getInstance().getReference().child("Bill").child(appointmentID);

            billRef.updateChildren(mp).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){

                        FirebaseDatabase.getInstance().getReference().child("Appointment").child(appointmentID).child("status").setValue("success");
                        FirebaseDatabase.getInstance().getReference().child("Prescription").child(appointmentID).child("status").setValue("success");

                        Toast.makeText(EditBillReceptionistActivity.this, "update payment successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditBillReceptionistActivity.this, ReceptionistHomeActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            });


        }


    }

    private void displaySpecificAppointmentInfo() {

        BillRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String aAid = dataSnapshot.child("aid").getValue().toString();
                    String aStatus = dataSnapshot.child("status").getValue().toString();
                    String aMedicineRates = dataSnapshot.child("medicinerates").getValue().toString();
                    String aBill = dataSnapshot.child("bill").getValue().toString();


                    txtAid.setText("Appointment ID :- " + aAid);
                    txtStatus.setText("Status :- " + aStatus);
                    edtMedicineRates.setText(aMedicineRates);
                    edtBill.setText(aBill);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
