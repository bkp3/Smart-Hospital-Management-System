package bkp.com.hospitalmanagementsystem.ViewFolder;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.Interface.ItemClickListener;
import bkp.com.hospitalmanagementsystem.R;

public class AppointmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtAid,txtName,txtFname,txtAge, txtGender, txtPhone, txtProblems, txtNotes, txtStatus, txtDepartment,txtWard, txtFloor;
    public ItemClickListener listener;


    public AppointmentViewHolder(@NonNull View itemView) {
        super(itemView);

        txtAid = (TextView)itemView.findViewById(R.id.appointment_id);
        txtName = (TextView)itemView.findViewById(R.id.appointment_name);
        txtFname = (TextView)itemView.findViewById(R.id.appointment_fname);
        txtAge = (TextView)itemView.findViewById(R.id.appointment_age);
        txtGender = (TextView)itemView.findViewById(R.id.appointment_gender);
        txtPhone = (TextView)itemView.findViewById(R.id.appointment_phone);
        txtProblems = (TextView)itemView.findViewById(R.id.appointment_problems);
        txtNotes = (TextView)itemView.findViewById(R.id.appointment_notes);
        txtStatus = (TextView)itemView.findViewById(R.id.appointment_status);
        txtDepartment = (TextView)itemView.findViewById(R.id.appointment_department);
        txtWard = (TextView)itemView.findViewById(R.id.appointment_ward);
        txtFloor = (TextView)itemView.findViewById(R.id.appointment_floor);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);

    }
}
