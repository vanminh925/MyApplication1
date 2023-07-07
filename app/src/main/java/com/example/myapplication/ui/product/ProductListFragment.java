package com.example.myapplication.ui.product;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryFilterAdapter;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.dao.DatabaseHandler;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {

    SearchView searchView;

    RecyclerView productRclView;
    ProductAdapter productAdapter;
    List<Product> productList;
    List<Product> productDisplayList;

    RecyclerView categoriesFilterRclView;
    List<Integer> categoryIdFilterLst;
    List<Category> categories;
    CategoryFilterAdapter categoryFilterAdapter;
    ImageButton addProduct, categoriesBtn;

    DatabaseHandler dao;
    ProductClickListener clickListener;

    public ProductListFragment(ProductClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new DatabaseHandler(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.products_list_fragment_layout, container, false);
        productRclView = view.findViewById(R.id.productRclView);
        searchView = view.findViewById(R.id.searchView);
        categoriesFilterRclView = view.findViewById(R.id.categoriesFilterRclView);
        addProduct = view.findViewById(R.id.btnAddProduct);
        categoriesBtn = view.findViewById(R.id.categories);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setup();
    }

    private void setup(){
        categoryIdFilterLst = new ArrayList<>();
        productList = dao.getAllProduct();
        productDisplayList = new ArrayList<>(productList);
        categories = dao.getAllCategory();
        productAdapter = new ProductAdapter((Context) getActivity(), productDisplayList, (ProductClickListener) getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        productRclView.setLayoutManager(gridLayoutManager);
        productRclView.setAdapter(productAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println(s);
                productDisplayList.clear();
                for(Product pr: productList){
                    if(pr.getName().toLowerCase().contains(s.toLowerCase())){
                        productDisplayList.add(pr);
                    }
                }
                productAdapter.notifyDataSetChanged();
                return false;
            }
        });

        categoryFilterAdapter = new CategoryFilterAdapter(getActivity(), categories);
        GridLayoutManager categoryFilterGrid = new GridLayoutManager(getActivity(), 1, GridLayoutManager.HORIZONTAL, false);
        categoriesFilterRclView.setLayoutManager(categoryFilterGrid);
        categoriesFilterRclView.setAdapter(categoryFilterAdapter);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.createProduct();
            }
        });

        categoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.switchCategoryPage();
            }
        });
    }

    public void onCategoryFilter(int categoryId) {
        if(categoryIdFilterLst.contains(categoryId)){
            int index = categoryIdFilterLst.indexOf(categoryId);
            categoryIdFilterLst.remove(index);
        } else{
            categoryIdFilterLst.add(categoryId);
        }
        productDisplayList.clear();
        if(categoryIdFilterLst.isEmpty()){
            productDisplayList.addAll(productList);
        } else{
            for(int id : categoryIdFilterLst) {
                List<Product> products = dao.getAllProductByCategoryId(id);
                productDisplayList.addAll(products);
            }
        }

        productAdapter.notifyDataSetChanged();
    }
}

