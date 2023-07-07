package com.example.myapplication.ui.category;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.dao.DatabaseHandler;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryListFragment extends Fragment {

    CategoryClickListener categoryClickListener;
    DatabaseHandler dao;
    ImageButton productsBtn, btnAdd;
    RecyclerView categoriesRcview;
    SearchView searchView;

    List<Category> categories;
    List<Category> categoryDisplayList;
    CategoryAdapter categoryAdapter;

    public CategoryListFragment(CategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new DatabaseHandler(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        setup();
    }

    private void setup(){
        productsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.switchProductPage();
            }
        });

        categories = dao.getAllCategory();
        categoryDisplayList = new ArrayList<>(categories);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryDisplayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false);
        categoriesRcview.setAdapter(categoryAdapter);
        categoriesRcview.setLayoutManager(gridLayoutManager);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                categoryDisplayList.clear();
                for(Category category : categories){
                    if(category.getName().toLowerCase().contains(s.toLowerCase())){
                        categoryDisplayList.add(category);
                    }
                }
                categoryAdapter.notifyDataSetChanged();
                return false;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.showEdit(null);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_list_fragment, container, false);
        productsBtn = view.findViewById(R.id.productsBtn);
        categoriesRcview = view.findViewById(R.id.categoriesRcview);
        searchView = view.findViewById(R.id.searchView);
        btnAdd = view.findViewById(R.id.btnAdd);
        return view;
    }
}