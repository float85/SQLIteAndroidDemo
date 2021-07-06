package com.example.sqldemoandroid43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.sqldemoandroid43.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SQLHelperDemo helperDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        helperDemo = new SQLHelperDemo(this);

        binding.btnAdd.setOnClickListener(v -> {

            String name = binding.etName.getText().toString();
            int price = Integer.parseInt(binding.etPrice.getText().toString());
            int number = Integer.parseInt(binding.etNumber.getText().toString());

            helperDemo.onAddProduct(name, price, number);
        });

        binding.btnEdit.setOnClickListener(v -> {
            int id = Integer.parseInt(binding.etName.getText().toString());
            int price = Integer.parseInt(binding.etPrice.getText().toString());
            int number = Integer.parseInt(binding.etNumber.getText().toString());

            helperDemo.onUpdateProduct(id, price, number);
        });

        binding.btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> productListr = helperDemo.onGetProduct();
            }
        });

    }
}