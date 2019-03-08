package com.example.adindariztiaputri.creditapplicationmvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adindariztiaputri.creditapplicationmvvm.R;
import com.example.adindariztiaputri.creditapplicationmvvm.model.DataJson;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ListTempat;
import com.example.adindariztiaputri.creditapplicationmvvm.viewmodel.ProvinsiModelView;

import java.util.List;

public class show_application extends AppCompatActivity {
    private TextView saName, saPhone, saEmail, saDob, saAddress, saCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_application);

        Button btn = findViewById(R.id.show_application);

        saName = findViewById(R.id.show_name);
        saPhone = findViewById(R.id.show_phone);
        saEmail = findViewById(R.id.show_email);
        saDob = findViewById(R.id.show_dob);
        saAddress = findViewById(R.id.show_address);
        saCity = findViewById(R.id.show_city);

        SharedPreferences preferences = getSharedPreferences("CreditApp", MODE_PRIVATE);
        String idPreference = preferences.getString("id","");

        ProvinsiModelView modelProvinsi = ViewModelProviders.of(this).get(ProvinsiModelView.class);
        modelProvinsi.showApplication(idPreference).observe(this, new Observer<DataJson>() {
            @Override
            public void onChanged(@Nullable DataJson dataJson) {
                saName.setText(dataJson.getName_());
                saPhone.setText(dataJson.getPhone_());
                saEmail.setText(dataJson.getEmail_());
                saDob.setText(dataJson.getDate_of_birth_());
                saAddress.setText(dataJson.getAddress_());
                saCity.setText(dataJson.getRef1_address_());
            }
        });
//        modelProvinsi.getProvinsi().observe(this, new Observer<List<ListTemp

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences preferences = getSharedPreferences("CreditApp", MODE_PRIVATE);
//                String idPreference = preferences.getString("id","");
//
//
//
//            }
//        });

    }
}
