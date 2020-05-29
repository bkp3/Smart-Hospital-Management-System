package bkp.com.hospitalmanagementsystem.Admin.ManageReceptionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.Admin.ManageDoctor.AdminEditDoctorActivity;
import bkp.com.hospitalmanagementsystem.Admin.ManageDoctor.AdminViewDoctorActivity;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.ViewFolder.DoctorViewHolder;
import bkp.com.hospitalmanagementsystem.ViewFolder.ReceptionistViewHolder;
import bkp.com.hospitalmanagementsystem.model.ReceptionistInfo;
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

public class AdminViewReceptionistActivity extends AppCompatActivity {

    private DatabaseReference ReceptionistRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_receptionist);

        Paper.init(this);

        ReceptionistRef = FirebaseDatabase.getInstance().getReference().child("ReceptionistInfo");

        recyclerView = findViewById(R.id.recycler_menu_admin_receptionist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ReceptionistInfo>options = new FirebaseRecyclerOptions.Builder<ReceptionistInfo>().setQuery(ReceptionistRef,ReceptionistInfo.class).build();
        FirebaseRecyclerAdapter<ReceptionistInfo, ReceptionistViewHolder>adapter = new FirebaseRecyclerAdapter<ReceptionistInfo, ReceptionistViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ReceptionistViewHolder holder, int position, @NonNull final ReceptionistInfo model) {

                holder.txtPhone.setText("Phone Number :- " + model.getMobile());
                holder.txtName.setText("Name :- " + model.getName());
                holder.txtEmail.setText("Email :- " + model.getEmail());
                holder.txtQualification.setText("Qualification :- " + model.getQualification());
                holder.txtPassword.setText("Password :- " + model.getPassword());
                holder.txtAddress.setText("Address :- " + model.getHospitalAddress());


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(AdminViewReceptionistActivity.this, AdminEditReceptionistActivity.class);
                        intent.putExtra("phid",model.getMobile());
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public ReceptionistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receptionist_items_layout, parent, false);
                ReceptionistViewHolder holder = new ReceptionistViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
