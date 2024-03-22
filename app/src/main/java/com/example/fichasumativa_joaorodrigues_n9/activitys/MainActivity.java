package com.example.fichasumativa_joaorodrigues_n9.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fichasumativa_joaorodrigues_n9.R;
import com.example.fichasumativa_joaorodrigues_n9.databinding.ActivityMainBinding;
import com.example.fichasumativa_joaorodrigues_n9.fragments.HomeFragemnt;
import com.example.fichasumativa_joaorodrigues_n9.fragments.ShearchFragment;

public class MainActivity extends AppCompatActivity {
    //binding
    private ActivityMainBinding binding;

    //current fragment
    private Fragment currentFragemt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //set default fragment
        replaceFragment(new HomeFragemnt());

        binding.bottomNavigationView.setOnItemSelectedListener(item ->
        {

            //reset the variable for the new fragment
            Fragment selectedFragment = null;

            //select the fragment
            if (item.getItemId() == R.id.home){
                selectedFragment = new HomeFragemnt();

            } else if (item.getItemId() == R.id.shearch){
                selectedFragment = new ShearchFragment();

            }

            //if the fragment is not the same as the current fragment
            if (currentFragemt == null ||  !selectedFragment.getClass().equals(currentFragemt.getClass())){
                //replace the fragment
                replaceFragment(selectedFragment);
            }

            return true;
        });
    }

    //replace the actual fragment
    private void replaceFragment(Fragment fragment)
    {

        currentFragemt = fragment; // update current fragment
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }
}