package bkp.com.hospitalmanagementsystem.ViewFolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.Interface.ItemClickListener;
import bkp.com.hospitalmanagementsystem.R;

public class ReceptionistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtPhone,txtName,txtEmail,txtQualification,txtPassword,txtAddress;
    public ItemClickListener listener;

    public ReceptionistViewHolder(@NonNull View itemView) {
        super(itemView);

        txtPhone = (TextView)itemView.findViewById(R.id.admin_receptionist_phone);
        txtName = (TextView)itemView.findViewById(R.id.admin_receptionist_name);
        txtEmail = (TextView)itemView.findViewById(R.id.admin_receptionist_email);
        txtQualification = (TextView)itemView.findViewById(R.id.admin_receptionist_qualification);
        txtPassword = (TextView)itemView.findViewById(R.id.admin_receptionist_password);
        txtAddress = (TextView)itemView.findViewById(R.id.admin_receptionist_address);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
