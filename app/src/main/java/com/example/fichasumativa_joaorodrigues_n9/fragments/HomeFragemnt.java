package com.example.fichasumativa_joaorodrigues_n9.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.toolbox.JsonObjectRequest;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.fichasumativa_joaorodrigues_n9.R;
import com.example.fichasumativa_joaorodrigues_n9.apaters.HomeAdapter;
import com.example.fichasumativa_joaorodrigues_n9.classes.AllData;
import com.example.fichasumativa_joaorodrigues_n9.classes.ProdutcsData;
import com.example.fichasumativa_joaorodrigues_n9.databinding.FragmentHomeFragemntBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class HomeFragemnt extends Fragment {

    //binding
    private FragmentHomeFragemntBinding binding;

    //adapter
    private HomeAdapter homeAdapter;

    public HomeFragemnt() {
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
        binding = FragmentHomeFragemntBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (AllData.getInstance().getProdtucs().size() == 0) {
            getApiData();
        } else {
            setRecyclerView(AllData.getInstance().getProdtucs());
            String count = binding.registerCount.getText().toString().replace("$", String.valueOf(AllData.getInstance().getProdtucs().size()));
            binding.registerCount.setText(count);
        }
    }

    private void getApiData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://dummyjson.com/products";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray productsArray = response.getJSONArray("products");
                            Type listType = new TypeToken<ArrayList<ProdutcsData>>() {
                            }.getType();
                            ArrayList<ProdutcsData> produtcsDataArrayList = new Gson().fromJson(productsArray.toString(), listType);

                            //set data in singleton
                            AllData.getInstance().setProdutcsDataArrayList(produtcsDataArrayList);

                            //set data in recyclerView
                            setRecyclerView(produtcsDataArrayList);

                            Snackbar.make(binding.getRoot(), "Dados recebidos com sucesso", Snackbar.LENGTH_LONG).show();
                            String count = binding.registerCount.getText().toString().replace("$", String.valueOf(AllData.getInstance().getProdtucs().size()));
                            binding.registerCount.setText(count);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(binding.getRoot(), "Error: " + error.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                }
        );

        queue.add(jsonObjectRequest);

    }

    private void setRecyclerView(ArrayList<ProdutcsData> produtcsData) {
        //set adapter
        binding.rcwHome.setHasFixedSize(true);
        binding.rcwHome.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(produtcsData);
        binding.rcwHome.setAdapter(homeAdapter);

        //set info and visibility
        binding.workInProgress.setVisibility(View.GONE);
        binding.rcwHome.setVisibility(View.VISIBLE);
        binding.registerCount.setVisibility(View.VISIBLE);
    }
}