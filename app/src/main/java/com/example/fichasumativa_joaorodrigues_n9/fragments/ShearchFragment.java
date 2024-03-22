package com.example.fichasumativa_joaorodrigues_n9.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.fichasumativa_joaorodrigues_n9.R;
import com.example.fichasumativa_joaorodrigues_n9.apaters.HomeAdapter;
import com.example.fichasumativa_joaorodrigues_n9.classes.AllData;
import com.example.fichasumativa_joaorodrigues_n9.classes.ProdutcsData;
import com.example.fichasumativa_joaorodrigues_n9.databinding.FragmentShearchBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ShearchFragment extends Fragment {

    //binding
    private FragmentShearchBinding binding;

    //variables
    private ArrayList<ProdutcsData> produtcsDataArrayList;
    HomeAdapter homeAdapter;


    public ShearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentShearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set icon search visible
        MenuItem iconSeach = binding.topAppBar.getMenu().findItem(R.id.iconSearch);
        iconSeach.setVisible(true);
        //////////////////////////

        produtcsDataArrayList = AllData.getInstance().getProdtucs();

        setRecyclerView(produtcsDataArrayList);

        //search
        binding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.iconSearch) {
                binding.searchTextField.setVisibility(binding.searchTextField.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                return true;
            }
            return false;
        });

        //listener de pesquisa
        binding.searchTextField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Get input text
                String inputText = binding.searchTextField.getEditText().getText().toString();

                // Filter the list
                homeAdapter.getFilter().filter(inputText);
            }
        });

        //hide search
        binding.searchTextField.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    binding.searchTextField.setVisibility(View.GONE);
                }
            }
        });

    }

    private void setRecyclerView(ArrayList<ProdutcsData> produtcsData) {
        //set adapter
        binding.rcwShearch.setHasFixedSize(true);
        binding.rcwShearch.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(produtcsData);
        binding.rcwShearch.setAdapter(homeAdapter);

        //set info and visibility
        binding.workInProgress.setVisibility(View.GONE);
        binding.rcwShearch.setVisibility(View.VISIBLE);

    }
}