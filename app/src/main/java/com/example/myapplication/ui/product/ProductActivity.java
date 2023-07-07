package com.example.myapplication.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;
import com.example.myapplication.ui.about.AboutActivity;
import com.example.myapplication.ui.category.CategoryActivity;

public class ProductActivity extends AppCompatActivity implements ProductClickListener {

    ProductListFragment productListFragment;
    ProductDetailFragment productDetailFragment;
    ProductEditFragment productEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(ProductActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                this.finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init(){
        productDetailFragment = new ProductDetailFragment(this);
        productEditFragment = new ProductEditFragment(this);
        productListFragment = new ProductListFragment(this);
        replaceFragment(productListFragment);
    }

    @Override
    public void showProductDetail(int productId) {
        productDetailFragment.setProductId(productId);
        replaceFragment(productDetailFragment);
    }

    @Override
    public void showProductList() {
        replaceFragment(productListFragment);
    }

    @Override
    public void onCategoryFilter(int categoryId) {
       productListFragment.onCategoryFilter(categoryId);
    }

    @Override
    public void showEditProduct(Product product) {
        productEditFragment.setProduct(product);
        replaceFragment(productEditFragment);
    }

    @Override
    public void createProduct() {
        productEditFragment.setProduct(null);
        replaceFragment(productEditFragment);
    }

    @Override
    public void switchCategoryPage() {
        Intent intent = new Intent(ProductActivity.this, CategoryActivity.class);
        startActivity(intent);
    }

    public void replaceFragment(Fragment newFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.productFragment, newFragment).commit();
    }



}