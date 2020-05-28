package bkp.com.hospitalmanagementsystem.Doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.Receptionist.ValidateAppointmentFromReceptionistToDoctorActivity;
import bkp.com.hospitalmanagementsystem.Receptionist.ViewAppointmentReceptionistActivity;
import bkp.com.hospitalmanagementsystem.ViewFolder.AppointmentViewHolder;
import bkp.com.hospitalmanagementsystem.model.Appointments;
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

public class ViewAppointmentDoctorActivity extends AppCompatActivity {

    private DatabaseReference AppointmentsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment_doctor);

        Paper.init(this);

        AppointmentsRef = FirebaseDatabase.getInstance().getReference().child("Appointment");

        recyclerView = findViewById(R.id.recycler_menu_doctor);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Appointments>options = new FirebaseRecyclerOptions.Builder<Appointments>().setQuery(AppointmentsRef,Appointments.class).build();
        FirebaseRecyclerAdapter<Appointments, AppointmentViewHolder> adapter = new FirebaseRecyclerAdapter<Appointments, AppointmentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position, @NonNull final Appointments model) {

                holder.txtAid.setText("Appointment ID :- " + model.getAid());
                holder.txtName.setText("Name :- " + model.getName());
                holder.txtFname.setText("Father Name :- " + model.getFname());
                holder.txtAge.setText("Age :- " + model.getAge());
                holder.txtGender.setText("Gender :- " + model.getGender());
                holder.txtPhone.setText("Phone :- " + model.getPhone());
                holder.txtProblems.setText("Problems :- " + model.getProblem());
                holder.txtNotes.setText("Notes :- " + model.getNotes());
                holder.txtStatus.setText("Status :- " + model.getStatus());
                holder.txtDepartment.setText("Department :- " + model.getDepartment());
                holder.txtWard.setText("Ward Number :- " + model.getWard());
                holder.txtFloor.setText("Floor Number :- " + model.getFloor());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ViewAppointmentDoctorActivity.this, ValidateAppointmentFromDoctorToReceptionistActivity.class);
                        intent.putExtra("aid",model.getAid());
                        startActivity(intent);

                    }
                });



            }

            @NonNull
            @Override
            public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_items_layout, parent, false);
                AppointmentViewHolder holder = new AppointmentViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();



    }
}
