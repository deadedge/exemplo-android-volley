package com.example.fichasumativa_joaorodrigues_n9.classes;

import java.util.ArrayList;

public class AllData {
    private static AllData instance;
    private ArrayList<ProdutcsData> produtcsDataArrayList = new ArrayList<>();

    //private constructor
    private AllData(){}

    //method for get instance
    public static AllData getInstance(){
        if(instance == null){
            instance = new AllData();
        }
        return instance;
    }

    //method for get data and set data
    public ArrayList<ProdutcsData> getProdtucs(){
        return produtcsDataArrayList;
    }

    public void setProdutcsDataArrayList(ArrayList<ProdutcsData> produtcsDataArrayList){
        this.produtcsDataArrayList = produtcsDataArrayList;
    }
}
