package com.example.k22411casampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import models.Customer;
import models.ListCustomer;
import models.ListEmployee;

public class CustomerManagementActivity extends AppCompatActivity {
    ListView lvCustomer;
    ListCustomer lc=new ListCustomer();
    ArrayAdapter<Customer> adapter;

    MenuItem menu_new_customer;
    MenuItem menu_broadcast_advertising;

    MenuItem menu_help;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvents();
    }

    private void addEvents() {
        //Nhấn vào customer thì mở chi tiết
        lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Customer c=adapter.getItem(position);
                openCustomerDetailActivity(c);
            }
        });
    }

    private void openCustomerDetailActivity(Customer c) {
        Intent intent=new Intent(CustomerManagementActivity.this,
                CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER",c);
        startActivity(intent);
    }

    private void addView() {
        lvCustomer=findViewById(R.id.lvCustomer);
        adapter=new ArrayAdapter<>(
                CustomerManagementActivity.this,
                android.R.layout.simple_list_item_1);

        lc.generate_sample_dataset();
        adapter.addAll(lc.getCustomers());
        lvCustomer.setAdapter(adapter);

        menu_new_customer=findViewById(R.id.menu_new_customer);
        menu_broadcast_advertising=findViewById(R.id.menu_broadcast_advertising);
        menu_help=findViewById(R.id.menu_help);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_new_customer)
        {
            Toast.makeText(this,"Mở màn hình thêm khách hàng mới",Toast.LENGTH_LONG).show();
            openNewCustomerActivity();
        }
        else if(item.equals(menu_broadcast_advertising))
        {
            Toast.makeText(this, "Bắn tim quảng cáo tới hàng loạt khách hàng", Toast.LENGTH_LONG).show();
        //Tìm hiểu firebase Cloud message
        }
        else if (item.getItemId()==R.id.menu_help)
        {
            Toast.makeText(this, "Mở màn hình trợ giúp khách hàng", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openNewCustomerActivity() {
        //hôm sau làm. Nhấn vào đôi tượng thì mở màn hình chi tiết lên

    }
}