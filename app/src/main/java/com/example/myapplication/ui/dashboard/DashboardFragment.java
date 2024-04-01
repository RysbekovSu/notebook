package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.example.myapplication.room.AppDataBase;
import com.example.myapplication.room.FurDao;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private AppDataBase appDb;
    private FurDao furDao;
    private FurAdapter furAdapter;
    private NavController navController;
    RecyclerView catalog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        catalog = binding.catalog;
        furAdapter = new FurAdapter();
        catalog.setAdapter(furAdapter);
        appDb = Room.databaseBuilder(binding.getRoot().getContext(), AppDataBase.class, "database")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        furDao = appDb.furDao();
        furAdapter.setList(furDao.getAll());


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}