package com.example.fichasumativa_joaorodrigues_n9.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.fichasumativa_joaorodrigues_n9.R;
import com.example.fichasumativa_joaorodrigues_n9.classes.AllData;
import com.example.fichasumativa_joaorodrigues_n9.classes.ProdutcsData;
import com.example.fichasumativa_joaorodrigues_n9.databinding.ActivityDetailViewBinding;

import java.util.ArrayList;

public class DetailView extends AppCompatActivity {

    //binding
    private ActivityDetailViewBinding binding;
    //variables
    private ArrayList<ProdutcsData> charactersInfosArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get data from intent
        int position = getIntent().getIntExtra("position", 0);

        //get data from singleton
        charactersInfosArrayList = AllData.getInstance().getProdtucs();

        //set image
        Glide.with(this).
                load(charactersInfosArrayList.get(position).getThunbnail()).
                centerCrop().
                into(binding.imgCharacter);

        //set topbar title
        binding.topAppBar.setTitle(charactersInfosArrayList.get(position).getTitle());

        //set name
        binding.txtName.setText(binding.txtName.getText().toString().replace("$", charactersInfosArrayList.get(position).getTitle()));

        //set price
        binding.txtPrice.setText(binding.txtPrice.getText().toString().replace("$", charactersInfosArrayList.get(position).getPrice()));

        //set description
        binding.txtDescription.setText(binding.txtDescription.getText().toString().replace("$", charactersInfosArrayList.get(position).getDescription()));

    }
}