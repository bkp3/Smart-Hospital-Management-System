package bkp.com.hospitalmanagementsystem.ViewFolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.Interface.ItemClickListener;
import bkp.com.hospitalmanagementsystem.R;

public class PrescriptionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtAid,txtStatus,txtMedicine,txtPrecautions;
    public ItemClickListener listener;

    public PrescriptionViewHolder(@NonNull View itemView) {
        super(itemView);

        txtAid = (TextView)itemView.findViewById(R.id.prescription_id);
        txtStatus = (TextView)itemView.findViewById(R.id.prescription_status);
        txtMedicine = (TextView)itemView.findViewById(R.id.prescription_medicine);
        txtPrecautions = (TextView)itemView.findViewById(R.id.prescription_precautions);

    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);
    }
}
