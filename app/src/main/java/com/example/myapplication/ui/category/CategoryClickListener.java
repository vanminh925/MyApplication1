package com.example.myapplication.ui.category;

import com.example.myapplication.model.Category;

public interface CategoryClickListener {
    public void switchProductPage();

    public void categoryDetail(int id);

    public void showList();

    public void showEdit(Category category);
}
