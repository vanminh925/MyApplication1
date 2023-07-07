package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Category;
import com.example.myapplication.ui.category.CategoryClickListener;
import com.example.myapplication.ui.product.ProductClickListener;
import com.example.myapplication.util.StringUtil;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ProductViewHolder> {

    Context context;
    List<Category> categories;
    CategoryClickListener categoryClickListener;


    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        this.categoryClickListener = (CategoryClickListener) context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_element_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.setCategory(category);
        holder.name.setText(StringUtil.formatProductName(category.getName()));
    }

    @Override
    public int getItemCount() {
        return categories == null ? 0 : categories.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        Category category;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categoryName);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoryClickListener.categoryDetail(category.getId());
                }
            });
        }
        public void setCategory(Category category) {
            this.category = category;
        }

    }
}