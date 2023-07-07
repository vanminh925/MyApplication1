package com.example.myapplication.ui.category;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.DatabaseHandler;
import com.example.myapplication.model.Category;

public class CategoryEditFragment extends Fragment {

    private Category category;
    private CategoryClickListener categoryClickListener;
    EditText edtName;
    DatabaseHandler dao;
    ImageButton btnSave;

    public CategoryEditFragment(CategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new DatabaseHandler(getActivity());
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                categoryClickListener.showList();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(category != null) {
            edtName.setText(category.getName());
        } else{
            edtName.setText("");
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if(name.equals("")){
                    Toast.makeText(getActivity(), "Vui lòng nhập tên danh mục", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(category != null) {
                    category.setName(name);
                    dao.updateCategory(category);
                } else{
                    category = new Category(0, name);
                    dao.insertCategory(category);
                }
                categoryClickListener.showList();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_edit_fragment, container, false);
        edtName = view.findViewById(R.id.edtName);
        btnSave = view.findViewById(R.id.btnSave);
        return view;
    }

    public void setCategory(Category category){
        this.category = category;
    }
}