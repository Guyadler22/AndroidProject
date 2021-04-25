package com.example.mypillsproject.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecyclerViewPills extends RecyclerView.Adapter<RecyclerViewPills.ViewHolder> implements Filterable {
    //original list
    private List<PillsItem> pillsItem;
    //copy list
    private List<PillsItem> temp;
    private pillActions pillAction;


    public RecyclerViewPills(pillActions pillAction) {
        this.pillsItem = new ArrayList<>();
        this.pillAction = pillAction;
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
            System.err.println("Filtered: " + constraint.toString());
            List<PillsItem> filteredList = new ArrayList<>();
            if (constraint.length() == 0 || constraint.toString().trim().length() < 1) {
                filteredList.addAll(temp);
                System.err.println("Size : " +  temp.size());
            } else {
                String filterPattern = constraint.toString();
                System.err.println("Pattern: " + filterPattern);
                for (PillsItem item : temp) {
                    if (item.getName().contains(filterPattern)) {
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
            List<PillsItem> resultsList = new ArrayList<>((List<PillsItem>) results.values);
            pillsItem.clear();
            pillsItem.addAll(resultsList);
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedString = formatter.format(Time);
        holder.pillsTimeView.setText(Time.getHours() + ":" + Time.getMinutes());
        holder.pillDateTake.setText(formattedString);

    }

    public void setPillsItem(List<PillsItem> pillsItem) {
        this.pillsItem = pillsItem;
        this.temp = new ArrayList<>(pillsItem);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return pillsItem.size();
    }
}