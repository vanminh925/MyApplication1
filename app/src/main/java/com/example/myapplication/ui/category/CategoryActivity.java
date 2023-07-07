package com.example.myapplication.ui.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.model.Category;
import com.example.myapplication.ui.about.AboutActivity;
import com.example.myapplication.ui.product.ProductActivity;

public class CategoryActivity extends AppCompatActivity implements CategoryClickListener{

    CategoryListFragment categoryListFragment;
    CategoryDetailFragment categoryDetailFragment;
    CategoryEditFragment categoryEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        categoryListFragment = new CategoryListFragment(this);
        categoryDetailFragment = new CategoryDetailFragment(this);
        categoryEditFragment = new CategoryEditFragment(this);
        replaceFragment(categoryListFragment);
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
                Intent intent = new Intent(CategoryActivity.this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                this.finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void replaceFragment(Fragment newFragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.categoryFragment, newFragment).commit();
    }

    @Override
    public void switchProductPage() {
        Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
        startActivity(intent);
    }

    @Override
    public void categoryDetail(int id) {
        categoryDetailFragment.setCategoryId(id);
        replaceFragment(categoryDetailFragment);
    }

    @Override
    public void showList() {
        replaceFragment(categoryListFragment);
    }

    @Override
    public void showEdit(Category category) {
        categoryEditFragment.setCategory(category);
        replaceFragment(categoryEditFragment);
    }
}