package com.example.myapplication.ui.student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.example.myapplication.models.Student;
import com.example.myapplication.room.AppDataBase;
import com.example.myapplication.room.FurDao;
import com.example.myapplication.room.StudentDao;
import com.example.myapplication.ui.dashboard.FurAdapter;

public class StudentFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private AppDataBase appDb;
    private StudentDao studentDao;
    private StudentAdapter studentAdapter;
    private NavController navController;
    RecyclerView catalog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        catalog = binding.catalog;
        studentAdapter = new StudentAdapter();
        catalog.setAdapter(studentAdapter);
        appDb = Room.databaseBuilder(binding.getRoot().getContext(), AppDataBase.class, "database")
                .fallbackToDestructiveMigration().allowMainThreadQueries().build();
        studentDao = appDb.studentDao();
        studentAdapter.setList(studentDao.getAll());


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}