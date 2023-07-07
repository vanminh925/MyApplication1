package com.example.myapplication.ui.product;

import com.example.myapplication.model.Product;

public interface ProductClickListener {
    public void showProductDetail(int productId);

    public void showProductList();

    public void onCategoryFilter(int categoryId);

    public void showEditProduct(Product product);

    public void createProduct();

    public void switchCategoryPage();
}
