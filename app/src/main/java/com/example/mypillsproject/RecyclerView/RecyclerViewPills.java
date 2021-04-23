package com.example.mypillsproject.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypillsproject.R;
import com.example.mypillsproject.pillActions;
import com.example.mypillsproject.viewModels.PillsItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewPills extends RecyclerView.Adapter<RecyclerViewPills.ViewHolder> implements Filterable {
    //original list
    private List<PillsItem> pillsItem;
    //copy list
    private List<PillsItem> allPills;

    private pillActions pillAction;


    public RecyclerViewPills(List<PillsItem> pillsItem, pillActions pillAction) {
        this.pillsItem = pillsItem;
        this.pillAction = pillAction;
        allPills = new ArrayList<>(pillsItem);

    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    // Using filter for the search bar but it didn't work

    Filter filter = new Filter() {
        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PillsItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(pillsItem);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (PillsItem item : allPills) {
                    if (item.getName().contains(filterPattern.toLowerCase())) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        //run on ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            pillsItem.clear();
            pillsItem.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mgTake, pillNameTake, pillsTimeView;
        ImageView deleteIcon;
        ImageView editIcon;
        TextView pillDateTake;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            mgTake = itemView.findViewById(R.id.Rvpill_mgView);
            pillNameTake = itemView.findViewById(R.id.Rvpill_NameView);
            deleteIcon = itemView.findViewById(R.id.ic_delete);
            editIcon = itemView.findViewById(R.id.ic_edit);
            //SET TIME ON THIS TEXT VIEW:
            pillsTimeView = itemView.findViewById(R.id.RvpillTimeView);
            pillDateTake = itemView.findViewById(R.id.rvPillDateView);

        }
    }

    @NonNull
    @Override
    public RecyclerViewPills.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewPills.ViewHolder holder, int position) {

        final PillsItem pillItem = pillsItem.get(position);
        DatePicker pillDate = pillItem.getDatePicker();
        String pillName = pillItem.getName();
        int mg = pillItem.getMg();
        Date Time = new Date(pillItem.getTime());



        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pillAction.updatePill(pillItem);
            }
        });
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pillAction.deletePill(pillItem);
            }
        });


        holder.mgTake.setText(String.valueOf(mg));
        holder.pillNameTake.setText(pillName);
        holder.pillsTimeView.setText(Time.getHours() + ":" + Time.getMinutes());
        holder.pillDateTake.setText(pillDate.toString());

    }

    public void setPillsItem(List<PillsItem> pillsItem) {
        this.pillsItem = pillsItem;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return pillsItem.size();
    }
}