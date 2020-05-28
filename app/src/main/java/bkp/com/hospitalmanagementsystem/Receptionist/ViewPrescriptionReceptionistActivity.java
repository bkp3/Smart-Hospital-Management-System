package bkp.com.hospitalmanagementsystem.Receptionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.Doctor.EditPrescriptionDoctorActivity;
import bkp.com.hospitalmanagementsystem.Doctor.ViewPrescriptionDoctorActivity;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.ViewFolder.PrescriptionViewHolder;
import bkp.com.hospitalmanagementsystem.model.Prescriptions;
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

public class ViewPrescriptionReceptionistActivity extends AppCompatActivity {

    private DatabaseReference PrescriptionsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescription_receptionist);

        Paper.init(this);

        PrescriptionsRef = FirebaseDatabase.getInstance().getReference().child("Prescription");

        recyclerView = findViewById(R.id.recycler_menu_receptionist_prescription);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Prescriptions>options = new FirebaseRecyclerOptions.Builder<Prescriptions>().setQuery(PrescriptionsRef,Prescriptions.class).build();
        FirebaseRecyclerAdapter<Prescriptions,PrescriptionViewHolder> adapter = new FirebaseRecyclerAdapter<Prescriptions, PrescriptionViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PrescriptionViewHolder holder, int position, @NonNull final Prescriptions model) {

                holder.txtAid.setText("Appointment ID :- " + model.getAid());
                holder.txtStatus.setText("Status :- " + model.getStatus());
                holder.txtMedicine.setText("Medicine :- " + model.getMedicine());
                holder.txtPrecautions.setText("Precautions :- " + model.getPrecautions());


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ViewPrescriptionReceptionistActivity.this, PaymentReceptionistActivity.class);
                        intent.putExtra("aid",model.getAid());
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_items_layout, parent, false);
                PrescriptionViewHolder holder = new PrescriptionViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();





    }
}
