package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Store.db";
    boolean isCreating = false;
    SQLiteDatabase currentDB = null;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && currentDB != null){
            return currentDB;
        }
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && currentDB != null){
            return currentDB;
        }
        return super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        isCreating = true;
        currentDB = sqLiteDatabase;
        sqLiteDatabase.execSQL("CREATE TABLE PRODUCTS (id integer primary key AUTOINCREMENT, name text, categoryId int, unitPrice real, thumbnail text, description text)");
        sqLiteDatabase.execSQL("CREATE TABLE CATEGORIES (id integer primary key AUTOINCREMENT, name text)");
        sqLiteDatabase.execSQL("CREATE TABLE USERS (id integer primary key AUTOINCREMENT, username text, password text)");
        prepareData();
    }

    public void prepareData() {
        Category category1 = new Category(0, "Trang trí nhà cửa");
        Category category2 = new Category(0, "Dụng cụ bếp");
        Category category3 = new Category(0, "Thời trang");
        Category category4 = new Category(0, "Điện tử");
        Category category5 = new Category(0, "Điện thoại");
        Category category6 = new Category(0, "Đồng hồ thông minh");
        Category category7 = new Category(0, "Công nghệ cao");
        Category category8 = new Category(0, "SmartHome");
        insertCategory(category1);
        insertCategory(category2);
        insertCategory(category3);
        insertCategory(category4);
        insertCategory(category5);
        insertCategory(category6);
        insertCategory(category7);
        insertCategory(category8);
        Product product = new Product(0, "[Siêu HOT] Đèn Ngủ Chiếu Sao Tự Xoay 3D", new Category(1, ""), 169000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/beca50e46d2088fc5ad3c74aff5cc112");
        Product product2 = new Product(0, "Đèn Ngủ 3D Led Nhiều Mẫu Hình Cực Đẹp - 3 màu (Được chọn hình)", new Category(1, ""), 55000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/b0c4d1c4443fb7c2d9b97cd8681f444e");
        Product product3 = new Product(0, "Đèn Ngủ Led Để Bàn Chân Gỗ Tự Nhiên [CMART.COM.VN]", new Category(1, ""), 99000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/9333b4ea1c3df6693e9484487917b0c2");
        Product product4 = new Product(0, "Đèn Ngủ 3D Led Nhiều Mẫu Hình Cực Đẹp - 3 màu- 001", new Category(1, ""), 55000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/6df240e4782c481b88966ada56d92753");
        Product product5 = new Product(0, "Đèn ngủ silicon Trâu con dễ thương có điều khiển từ xa 16 màu Đồ trang trí Quà tặng đẹp [BH 1 đổi 1]", new Category(1, ""), 275000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/b4fb9f4fc4af2c52843a440134e907ef");
        Product product6 = new Product(0, "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", new Category(3, ""), 23000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/47fa76fecd88f10eee9768dbd301ed95");
        Product product7 = new Product(0, "Đồng hồ thời trang nữ Gogey dây da êm tay, mặt tròn nhỏ xinh xắn, chống trày xước tốt", new Category(3, ""), 27000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/78e4450b07162a4e233620efbdeebae2");
        Product product8 = new Product(0, "Đồng Hồ Nam WWOOR 8826 Máy Nhật Dây Thép Mành Cao Cấp - Nhiều Màu", new Category(3, ""), 245500, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/42e07e90ee3fa5a7e65238145f50d8fd");
        Product product9 = new Product(0, "Đồng Hồ Nữ Chính Hãng ULZZANG 2702 Dây Lưới Nam Châm Cao Cấp.", new Category(3, ""),79000 , "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/ee5fdb8d1a095b9062d2021a4d790fda");
        Product product10 = new Product(0, "Vòng tay theo dõi sức khoẻ Mi Band 5 Xiaomi", new Category(4, ""), 569000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/e6b47b0e53fdb23c2ec61736609d9a51");
        Product product11 = new Product(0, "Mũ Bảo Hiểm Nửa Đầu 1/2 Nhiều Tem Siêu HOT - Hàng Cao Cấp", new Category(4, ""), 99000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/ea069dc13e5f7ca7bfcc00c0c1daeda5");
        Product product12 = new Product(0, "Tai nghe phone 6/6s Zin - \uD83C\uDF81Tặng bao đựng + dây cuốn\uD83C\uDF81 - Bảo hành 12 tháng lỗi 1 đổi 1", new Category(4, ""), 79000, "Giới thiệu sản phẩm", "https://cf.shopee.vn/file/7969a15babb7f6703138615e33956eb1");
        insertProduct(product);
        insertProduct(product2);
        insertProduct(product3);
        insertProduct(product4);
        insertProduct(product5);
        insertProduct(product6);
        insertProduct(product7);
        insertProduct(product8);
        insertProduct(product9);
        insertProduct(product10);
        insertProduct(product11);
        insertProduct(product12);
        insertUser("username", "password");
    }

    public boolean insertUser(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        db.insert("USERS", null, contentValues);
        return true;
    }

    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from USERS where username = '"+username+"' and password = '" + password+"'", null );
        if(res.moveToFirst()){
            return true;
        }
        return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PRODUCTS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CATEGORIES");
        onCreate(sqLiteDatabase);
    }

    public boolean insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("categoryId", product.getCategory().getId());
        contentValues.put("unitPrice", product.getUnitPrice());
        contentValues.put("description", product.getDescription());
        contentValues.put("thumbnail", product.getThumbnail());
        db.insert("PRODUCTS", null, contentValues);
        return true;
    }

    public boolean updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("name", product.getName());
        contentValues.put("categoryId", product.getCategory().getId());
        contentValues.put("unitPrice", product.getUnitPrice());
        contentValues.put("description", product.getDescription());
        contentValues.put("thumbnail", product.getThumbnail());
        db.update("PRODUCTS", contentValues, "id = ? ", new String[] { Integer.toString(product.getId()) } );
        return true;
    }

    public Integer deteteProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PRODUCTS",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select id, name, categoryId, unitPrice, description, thumbnail from PRODUCTS where id="+id+"", null );
        if(res.moveToFirst()){
            Category cate = getCategory(res.getInt(2));
            Product product = new Product(res.getInt(0),
                            res.getString(1),
                            cate,
                            res.getDouble(3),
                            res.getString(4),
                            res.getString(5));
            return product;
        }
        return null;
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select id, name, categoryId, unitPrice, description, thumbnail from PRODUCTS", null );
        if (res.moveToFirst()) {
            do {
                Category cate = getCategory(res.getInt(2));
                Product product = new Product(res.getInt(0),
                        res.getString(1),
                        cate,
                        res.getDouble(3),
                        res.getString(4),
                        res.getString(5));
                products.add(product);
            } while (res.moveToNext());

        }
        return products;
    }

    public List<Product> getAllProductByCategoryId(int id) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select id, name, categoryId, unitPrice, description, thumbnail from PRODUCTS where categoryId = "+ id, null );
        if (res.moveToFirst()) {
            do {
                Category cate = getCategory(res.getInt(2));
                Product product = new Product(res.getInt(0),
                        res.getString(1),
                        cate,
                        res.getDouble(3),
                        res.getString(4),
                        res.getString(5));
                products.add(product);
            } while (res.moveToNext());
        }
        return products;
    }

    public boolean insertCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("name", category.getName());
        db.insert("CATEGORIES", null, contentValues);
        return true;
    }

    public boolean updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("name", category.getName());
        db.update("CATEGORIES", contentValues, "id = ? ", new String[] { Integer.toString(category.getId()) } );
        return true;
    }

    public Integer deteteCategory(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("CATEGORIES",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select id, name from CATEGORIES where id="+id+"", null );
        if(res.moveToFirst()){
            Category category = new Category(res.getInt(0), res.getString(1));
            return category;
        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select id, name from CATEGORIES", null );
        res.moveToFirst();
        if (res.moveToFirst()) {
            do {
                Category category = new Category(res.getInt(0), res.getString(1));
                categories.add(category);
            } while (res.moveToNext());

        }
        return categories;
    }

}
