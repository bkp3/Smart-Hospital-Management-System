package bkp.com.hospitalmanagementsystem.ViewFolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import bkp.com.hospitalmanagementsystem.Interface.ItemClickListener;
import bkp.com.hospitalmanagementsystem.R;

public class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtAid,txtStatus,txtMedicineRates,txtBill;
    public ItemClickListener listener;

    public BillViewHolder(@NonNull View itemView) {
        super(itemView);

        txtAid = (TextView)itemView.findViewById(R.id.bill_id);
        txtStatus = (TextView)itemView.findViewById(R.id.bill_status);
        txtMedicineRates = (TextView)itemView.findViewById(R.id.bill_medicinerates);
        txtBill = (TextView)itemView.findViewById(R.id.bill_bill);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v,getAdapterPosition(),false);

    }
}
