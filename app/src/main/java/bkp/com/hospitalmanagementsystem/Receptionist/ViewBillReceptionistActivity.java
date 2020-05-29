package bkp.com.hospitalmanagementsystem.Receptionist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.R;
import bkp.com.hospitalmanagementsystem.ViewFolder.BillViewHolder;
import bkp.com.hospitalmanagementsystem.ViewFolder.PrescriptionViewHolder;
import bkp.com.hospitalmanagementsystem.model.Bill;
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

public class ViewBillReceptionistActivity extends AppCompatActivity {

    private DatabaseReference BillRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bill_receptionist);

        Paper.init(this);

        BillRef = FirebaseDatabase.getInstance().getReference().child("Bill");

        recyclerView = findViewById(R.id.recycler_menu_bill_prescription);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Bill>options = new FirebaseRecyclerOptions.Builder<Bill>().setQuery(BillRef,Bill.class).build();
        FirebaseRecyclerAdapter<Bill, BillViewHolder>adapter = new FirebaseRecyclerAdapter<Bill, BillViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BillViewHolder holder, int position, @NonNull final Bill model) {

                holder.txtAid.setText("Appointment ID :- " + model.getAid());
                holder.txtStatus.setText("Status :- " + model.getStatus());
                holder.txtMedicineRates.setText("Medicine Rates :- " + model.getMedicinerates());
                holder.txtBill.setText("Bill :- " + model.getBill());


                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(ViewBillReceptionistActivity.this, EditBillReceptionistActivity.class);
                        intent.putExtra("aid",model.getAid());
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_items_layout, parent, false);
                BillViewHolder holder = new BillViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
