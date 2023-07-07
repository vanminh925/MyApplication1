package com.example.myapplication.ui.category;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ProductAdapter;
import com.example.myapplication.dao.DatabaseHandler;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;

import java.util.List;

public class CategoryDetailFragment extends Fragment {

    CategoryClickListener categoryClickListener;
    DatabaseHandler dao;
    int categoryId;
    TextView categoryName;
    ImageButton btnDelete;
    ImageButton btnUpdate;
    RecyclerView someProducts;

    public CategoryDetailFragment(CategoryClickListener categoryClickListener) {
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
        Category category = dao.getCategory(categoryId);
        categoryName.setText(category.getName());
        List<Product> productDisplayList = dao.getAllProductByCategoryId(categoryId);
        ProductAdapter productAdapter = new ProductAdapter(getActivity(), productDisplayList, null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        someProducts.setLayoutManager(gridLayoutManager);
        someProducts.setAdapter(productAdapter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productList = dao.getAllProductByCategoryId(categoryId);
                if(productList.size() > 0){
                    Toast.makeText(getActivity(),"Không thể xoá danh mục do còn sản phẩm thuộc danh mục này", Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(getActivity())
                        .setTitle("Xác nhận xoá?")
                        .setMessage("Xác nhận xoá danh mục?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dao.deteteCategory(categoryId);
                                categoryClickListener.showList();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickListener.showEdit(category);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_detail_fragment, container, false);
        categoryName = view.findViewById(R.id.categoryName);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        someProducts = view.findViewById(R.id.someProducts);
        return view;
    }

    public void setCategoryId(int id){
        this.categoryId = id;
    }
}