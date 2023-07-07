package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.DatabaseHandler;
import com.example.myapplication.ui.product.ProductActivity;

public class LoginActivity extends AppCompatActivity {

    DatabaseHandler dao;
    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dao = new DatabaseHandler(this);
        setup();
    }

    private void setup(){
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString();
                if(usernameStr.equals("")){
                    Toast.makeText(LoginActivity.this, "Vui lòng điền Tên Đăng Nhập", Toast.LENGTH_SHORT).show();
                    return;
                }
                String passwordStr = password.getText().toString();
                if(passwordStr.equals("")){
                    Toast.makeText(LoginActivity.this, "Vui lòng điền Mật Khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean check = dao.checkLogin(usernameStr, passwordStr);
                if(!check){
                    Toast.makeText(LoginActivity.this, "Tên Đăng Nhập hoặc Mật Khẩu không đúng. Vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(LoginActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }
}