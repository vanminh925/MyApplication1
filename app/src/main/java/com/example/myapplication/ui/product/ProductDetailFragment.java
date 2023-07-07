package com.example.myapplication.ui.product;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dao.DatabaseHandler;
import com.example.myapplication.dao.ImageApi;
import com.example.myapplication.model.Product;

public class ProductDetailFragment extends Fragment {

    int productId = 0;
    DatabaseHandler dao;
    ProductClickListener clickListener;

    public ProductDetailFragment(ProductClickListener clickListener) {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_fragment, container, false);
        setup(view);
        return view;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setup(View view) {
        Product product = dao.getProduct(productId);
        TextView productDetailName = view.findViewById(R.id.productDetailName);
        TextView productDetailCategory = view.findViewById(R.id.productDetailCategory);
        TextView productPrice = view.findViewById(R.id.productPrice);
        TextView productDetailDesc = view.findViewById(R.id.productDetailDesc);
        ImageView productDetailImg = view.findViewById(R.id.productDetailImg);
        productDetailName.setText(product.getName());
        productDetailCategory.setText(product.getCategory().getName());
        productPrice.setText(product.getUnitPrice() + "");
        productDetailDesc.setText(product.getDescription());
        ImageApi async = new ImageApi(product.getThumbnail(), productDetailImg);
        async.execute();
        ImageButton deleteProduct = view.findViewById(R.id.btnDeleteProduct);
        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Xác nhận xoá?")
                        .setMessage("Xác nhận xoá sản phẩm?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dao.deteteProduct(productId);
                                clickListener.showProductList();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        ImageButton editProduct = view.findViewById(R.id.btnUpdateProduct);
        editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.showEditProduct(product);
            }
        });
    }
}
