package com.example.dogapp.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dogapp.R;
import com.example.dogapp.databinding.FragmentDetailsBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.model.Height;
import com.example.dogapp.model.Weight;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Map;

public class DetailsFragment extends Fragment {

    private DogBreed dog;
    private Height height;
    private Weight weight;
    private ImageView imvDog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            dog = (DogBreed) getArguments().getSerializable("dog");
            height = dog.getHeight();
            weight = dog.getWeight();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailsBinding binding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.fragment_details, null, false);
        View viewRoot = binding.getRoot();
        binding.setDog(dog);
        binding.setHeight(height);
        binding.setWeight(weight);
        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imvDog = getView().findViewById(R.id.detail_imv);
        Picasso.get().load(dog.getUrl()).into(imvDog);
    }
}