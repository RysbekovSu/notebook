package com.example.myapplication.ui.sInsert;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSInsertBinding;
import com.example.myapplication.models.Fur;
import com.example.myapplication.models.Student;
import com.example.myapplication.room.AppDataBase;
import com.example.myapplication.room.FurDao;
import com.example.myapplication.room.StudentDao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SInsertFragment extends Fragment {


    private FragmentSInsertBinding binding;

    private AppDataBase appDataBase;


    private StudentDao studentDao;

    private ActivityResultLauncher<String> content_l;



    NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSInsertBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSave.setOnClickListener(v2 -> {

            String nameSur = binding.editN.getText().toString();

            if (nameSur.isEmpty()) {
                Toast.makeText(requireActivity(), "Заполните поле", Toast.LENGTH_SHORT).show();
            } else {
                 {

                    Student student = new Student(nameSur);

                    this.appDataBase = Room.databaseBuilder(binding.getRoot().getContext(), AppDataBase.class, "database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();

                     studentDao = appDataBase.studentDao();
                     studentDao.insert(student);


                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                    navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
                    navController.navigate(R.id.action_navigation_sInsert_to_navigation_student);

                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}