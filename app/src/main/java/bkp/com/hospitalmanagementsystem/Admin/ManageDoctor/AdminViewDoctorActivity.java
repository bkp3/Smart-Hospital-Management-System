package bkp.com.hospitalmanagementsystem.Admin.ManageDoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.Receptionist.PaymentReceptionistActivity;
import bkp.com.hospitalmanagementsystem.Receptionist.ViewPrescriptionReceptionistActivity;
import bkp.com.hospitalmanagementsystem.ViewFolder.DoctorViewHolder;
import bkp.com.hospitalmanagementsystem.ViewFolder.PrescriptionViewHolder;
import bkp.com.hospitalmanagementsystem.model.DoctorInfo;
import io.paperdb.Paper;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminViewDoctorActivity extends AppCompatActivity {

    private DatabaseReference DoctorsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_doctor);

        Paper.init(this);

        DoctorsRef = FirebaseDatabase.getInstance().getReference().child("DoctorInfo");

        recyclerView = findViewById(R.id.recycler_menu_admin_doctor);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<DoctorInfo>options = new FirebaseRecyclerOptions.Builder<DoctorInfo>().setQuery(DoctorsRef,DoctorInfo.class).build();
        FirebaseRecyclerAdapter<DoctorInfo, DoctorViewHolder>adapter = new FirebaseRecyclerAdapter<DoctorInfo, DoctorViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DoctorViewHolder holder, int position, @NonNull final DoctorInfo model) {

                holder.txtPhone.setText("Phone Number :- " + model.getMobile());
                holder.txtName.setText("Name :- " + model.getName());
                holder.txtEmail.setText("Email :- " + model.getEmail());
                holder.txtQualification.setText("Qualification :- " + model.getQualification());
                holder.txtPassword.setText("Password :- " + model.getPassword());
                holder.txtAddress.setText("Address :- " + model.getHospitalAddress());


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(AdminViewDoctorActivity.this, AdminEditDoctorActivity.class);
                        intent.putExtra("phid",model.getMobile());
                        startActivity(intent);

                    }
                });
            }

            @NonNull
            @Override
            public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_items_layout, parent, false);
                DoctorViewHolder holder = new DoctorViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }
}
