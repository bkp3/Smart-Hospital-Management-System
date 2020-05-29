package bkp.com.hospitalmanagementsystem.ViewFolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.Interface.ItemClickListener;
import bkp.com.hospitalmanagementsystem.R;

public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    public TextView txtPhone,txtName,txtEmail,txtQualification,txtPassword,txtAddress;
    public ItemClickListener listener;

    public DoctorViewHolder(@NonNull View itemView) {
        super(itemView);

        txtPhone = (TextView)itemView.findViewById(R.id.admin_doctor_phone);
        txtName = (TextView)itemView.findViewById(R.id.admin_doctor_name);
        txtEmail = (TextView)itemView.findViewById(R.id.admin_doctor_email);
        txtQualification = (TextView)itemView.findViewById(R.id.admin_doctor_qualification);
        txtPassword = (TextView)itemView.findViewById(R.id.admin_doctor_password);
        txtAddress = (TextView)itemView.findViewById(R.id.admin_doctor_address);

    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);

    }
}
