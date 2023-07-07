package com.example.myapplication.ui.product;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dao.DatabaseHandler;
import com.example.myapplication.dao.ImageApi;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductEditFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Product product;
    DatabaseHandler dao;
    ProductClickListener clickListener;
    List<Category> categories;
    int categorySelectedIndex = 0;

    ImageButton btnSaveProduct;
    EditText edtProductName,edtProductPrice,edtProductImg, edtproductDetailDesc;
    Spinner drpProductCategory;
    ImageView productDetailImg;

    public ProductEditFragment(ProductClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new DatabaseHandler(getActivity());
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                clickListener.showProductList();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        categories = dao.getAllCategory();
    }

    @Override
    public void onResume() {
        super.onResume();
        setup();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_edit_fragment, container, false);
        btnSaveProduct = view.findViewById(R.id.btnSaveProduct);
        edtProductName = view.findViewById(R.id.edtProductName);
        edtProductPrice = view.findViewById(R.id.edtProductPrice);
        edtProductImg = view.findViewById(R.id.edtProductImg);
        edtproductDetailDesc = view.findViewById(R.id.edtproductDetailDesc);
        drpProductCategory = view.findViewById(R.id.drpProductCategory);
        productDetailImg = view.findViewById(R.id.productDetailImg);
        return view;
    }

    private void setup() {
        categorySelectedIndex = 0;
        if(product != null) {
            edtProductName.setText(product.getName());
            edtProductPrice.setText(product.getUnitPrice() + "");
            edtProductImg.setText(product.getThumbnail());
            edtproductDetailDesc.setText(product.getDescription());
            loadImg(product.getThumbnail());
        }else{
            edtProductName.setText("");
            edtProductPrice.setText("");
            edtProductImg.setText("");
            edtproductDetailDesc.setText("");
            loadImg("");
        }
        List<String> categoryNames = new ArrayList<>();
        for(int i = 0; i <categories.size(); i ++){
            Category category = categories.get(i);
            categoryNames.add(category.getName());
            if(product != null && product.getCategory() != null && product.getCategory().getId() != 0){
                if(category.getId() == product.getCategory().getId()){
                    categorySelectedIndex = i;
                }
            }
        }
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drpProductCategory.setAdapter(adapter);
        drpProductCategory.setSelection(categorySelectedIndex);
        drpProductCategory.setOnItemSelectedListener(this);
        edtProductImg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String imgSrc = edtProductImg.getText().toString();
                loadImg(imgSrc);
            }
        });
        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtProductName.getText().toString();
                if(edtProductName.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Vui lòng nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                String price = edtProductPrice.getText().toString();
                if(edtProductPrice.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Vui lòng nhập giá sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                String productImg = edtProductImg.getText().toString();
                if(edtProductImg.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Vui lòng nhập đường dẫn hình ảnh sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                String desc = edtproductDetailDesc.getText().toString();
                if(edtproductDetailDesc.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Vui lòng nhập mô tả sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }
                Category cate = categories.get(categorySelectedIndex);
                if(product != null) {
                    product.setName(name);
                    product.setUnitPrice(Double.parseDouble(price));
                    product.setThumbnail(productImg);
                    product.setDescription(desc);
                    product.setCategory(cate);
                    dao.updateProduct(product);
                } else{
                    product = new Product(0,name,cate,Double.parseDouble(price), desc, productImg);
                    dao.insertProduct(product);
                }
                clickListener.showProductList();
            }
        });


    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private void loadImg(String imgSrc){
        ImageApi async = new ImageApi(imgSrc, productDetailImg);
        async.execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categorySelectedIndex = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
