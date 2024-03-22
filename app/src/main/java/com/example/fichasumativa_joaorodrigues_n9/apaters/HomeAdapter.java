package com.example.fichasumativa_joaorodrigues_n9.apaters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fichasumativa_joaorodrigues_n9.activitys.DetailView;
import com.example.fichasumativa_joaorodrigues_n9.classes.ProdutcsData;
import com.example.fichasumativa_joaorodrigues_n9.databinding.RcwHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> implements Filterable {

    // Variables
    private ArrayList<ProdutcsData> produtcsDataArrayList;
    private ArrayList<ProdutcsData> produtcsDataArrayListFull;

    // Constructor
    public HomeAdapter(ArrayList<ProdutcsData> produtcsDataArrayList) {
        this.produtcsDataArrayList = produtcsDataArrayList;
        this.produtcsDataArrayListFull = new ArrayList<>(produtcsDataArrayList);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RcwHomeBinding binding = RcwHomeBinding.inflate(inflater, parent, false);
        return new HomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        // Get position info
        ProdutcsData produtcsData = produtcsDataArrayList.get(position);
        // Set components
        Glide.with(holder.itemView.getContext())
                .load(produtcsData.getThunbnail())
                .into(holder.binding.thumbProduct);

        holder.binding.thumbTitle.setText(produtcsData.getTitle());
        holder.binding.thumbPrice.setText(String.format("%s€", produtcsData.getPrice()));
        holder.binding.thumbSubSubtitle.setText(produtcsData.getDescription());

        holder.binding.card.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailView.class);
            intent.putExtra("position", position);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return produtcsDataArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return productsFilter;
    }

    private Filter productsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProdutcsData> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(produtcsDataArrayListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ProdutcsData item : produtcsDataArrayListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            produtcsDataArrayList.clear();
            produtcsDataArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private RcwHomeBinding binding;

        public HomeViewHolder(RcwHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
