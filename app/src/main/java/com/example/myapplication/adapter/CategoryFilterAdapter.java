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
import com.example.myapplication.ui.product.ProductClickListener;
import com.example.myapplication.util.StringUtil;

import java.util.List;

public class CategoryFilterAdapter extends RecyclerView.Adapter<CategoryFilterAdapter.ProductViewHolder> {

    Context context;
    List<Category> categories;
    ProductClickListener productClickListener;


    public CategoryFilterAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
        this.productClickListener = (ProductClickListener) context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_filter_element_layout, parent, false);
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
        boolean choose = false;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categoryNameFilter);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choose = !choose;
                    productClickListener.onCategoryFilter(category.getId());
                    if(choose) {
                        name.setBackgroundResource(R.drawable.searchview_background);
                        name.setTextColor(Color.BLACK);
                    } else{
                        name.setBackgroundResource(R.drawable.category_filter_element_background);
                        name.setTextColor(Color.GRAY);
                    }
                }
            });
        }

        public void setCategory(Category category) {
            this.category = category;
        }

    }
}