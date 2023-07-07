package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.ImageApi;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.product.ProductClickListener;
import com.example.myapplication.util.StringUtil;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    List<Product> productList;
    ProductClickListener productClickListener;

    public ProductAdapter(Context context, List<Product> productList, ProductClickListener productClickListener) {
        this.context = context;
        this.productList = productList;
        this.productClickListener = productClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_element_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.setProduct(product);
        holder.name.setText(StringUtil.formatProductName(product.getName()));
        holder.price.setText(StringUtil.convertPriceToDisplay(product.getUnitPrice()));
        holder.category.setText(StringUtil.formatProductCategory(product.getCategory().getName()));
        if(productClickListener == null){
            holder.detailBtn.setVisibility(View.GONE);
        }
        ImageApi async = new ImageApi(product.getThumbnail(), holder.productImg);
        async.execute();
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        ImageView productImg;
        TextView name, price, category;
        ImageButton detailBtn;
        Product product;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.productImg);
            name = itemView.findViewById(R.id.productName);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.productPrice);
            detailBtn = itemView.findViewById(R.id.detailBtn);
            detailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productClickListener.showProductDetail(product.getId());
                }
            });
        }

        public void setProduct(Product product) {
            this.product = product;
        }
    }


}