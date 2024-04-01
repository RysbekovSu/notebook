package com.example.myapplication.ui.student;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.databinding.CardItem2Binding;
import com.example.myapplication.models.Student;
import com.example.myapplication.room.AppDataBase;
import com.example.myapplication.room.StudentDao;
import com.example.myapplication.ui.dashboard.FurAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    CardItem2Binding binding;
    List<Student> list =new ArrayList();
    StudentDao studentDao;
    Context context;
    NavController navController;
    Student newStudent;
    EditText editText;
    public void setList(List<Student> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CardItem2Binding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        studentDao= Room.databaseBuilder(holder.binding.getRoot().getContext(),
                AppDataBase.class,
                "database").allowMainThreadQueries().build().studentDao();

        Student student =list.get(position);

        holder.binding.nameSurnameCard.setText(student.getName());


        newStudent = student;

        holder.binding.dropdownMenu.setOnClickListener(v1 -> {
            PopupMenu popup = new PopupMenu(holder.binding.getRoot().getContext(),
                    holder.binding.dropdownMenu);
            popup.getMenuInflater().inflate(R.menu.card_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @SuppressLint("IntentReset")
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getTitle().toString()) {
                        case "call":
                            if (ContextCompat.checkSelfPermission(holder.binding.getRoot().getContext(), android.Manifest.permission.CALL_PHONE) != PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions((Activity) holder.binding.getRoot().getContext(),
                                        new String[]{Manifest.permission.CALL_PHONE}, 0);
                            } else {

                                String num_student = holder.binding.nameSurnameCard.getText().toString().trim();

                                Uri call = Uri.parse("tel: " + num_student);

                                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                                holder.binding.getRoot().getContext().startActivity(intent);
                            }
                            break;
                        case "message":
                            holder.binding.myMessage.setVisibility(View.VISIBLE);

                            Intent intent = new Intent(Intent.ACTION_SEND);

                            intent.setData(Uri.parse("mailto:"));
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_SUBJECT, "My message");
                            intent.putExtra(Intent.EXTRA_TEXT, holder.binding.myMessage.getText().toString());

                            try {
                                holder.itemView.getContext().startActivity(Intent.createChooser(intent, "Send mail..."));
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(holder.itemView.getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case "delete":
                            AlertDialog alertDialog = new AlertDialog
                                    .Builder(holder.binding.getRoot().getContext()).create();
                            alertDialog.setTitle("Are you sure?");

                            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes, i want",
                                    new DialogInterface.OnClickListener() {
                                        @SuppressLint("NotifyDataSetChanged")
                                        public void onClick(DialogInterface dialog, int which) {
                                            studentDao.delete(student);
                                            list.remove(holder.getAdapterPosition());

                                            notifyDataSetChanged();
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No, i do not", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alertDialog.show();
                            break;
                        default:
                            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();

                    }
                    return true;

                }
            });
            popup.show();


        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardItem2Binding binding;
        public ViewHolder(@NonNull CardItem2Binding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Student student) {

        }
    }
}
