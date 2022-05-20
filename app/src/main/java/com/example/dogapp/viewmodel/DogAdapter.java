package com.example.dogapp.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> implements Filterable {

    private static ArrayList<DogBreed> dogs;
    private static ArrayList<DogBreed> dogsOld;

    public DogAdapter(ArrayList<DogBreed> dogs) {
        this.dogs = dogs;
        this.dogsOld = dogs;
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DogBreed dog = dogs.get(position);

        holder.txtTen.setText(dog.getName());
        holder.txtDescrip.setText(dog.getTemperament());
        Picasso.get().load(dog.getUrl()).into(holder.imgView_Item);
    }


    @Override
    public int getItemCount() {
        return dogs.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if(search.isEmpty()){
                    dogs = dogsOld;
                }
                else {
                    ArrayList<DogBreed> searchList = new ArrayList<DogBreed>();
                    for (DogBreed dog : dogsOld){
                        if(dog.getName().toLowerCase().contains(search.toLowerCase())){
                            searchList.add(dog);
                        }
                    }
                    dogs = searchList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = dogs;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dogs =( ArrayList<DogBreed>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTen, txtDescrip;
        public ImageView imgView_Item;
        public ConstraintLayout layoutItem;
        public ViewHolder(View view){
            super(view);

            txtTen = view.findViewById(R.id.txtTen);
            txtDescrip = view.findViewById(R.id.txtDescrip);
            imgView_Item = view.findViewById(R.id.imgView_Item);
            layoutItem = view.findViewById(R.id.layout_item);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DogBreed dog = dogs.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dog", dog);
                    Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);
                }
            });

        }
    }
}
