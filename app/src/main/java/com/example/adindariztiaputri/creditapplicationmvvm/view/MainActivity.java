package com.example.adindariztiaputri.creditapplicationmvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adindariztiaputri.creditapplicationmvvm.R;
import com.example.adindariztiaputri.creditapplicationmvvm.model.DataJson;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ListKodepos;
import com.example.adindariztiaputri.creditapplicationmvvm.model.ListTempat;
import com.example.adindariztiaputri.creditapplicationmvvm.viewmodel.ProvinsiModelView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String mDate, mProvinsi, mKabupaten, mKecamatan, mKelurahan, mKodepos;
    private TextView showDate;
    Context mContext;
    Spinner spinnerProvinsi, spinnerKabupaten, spinnerKecamatan, spinnerKelurahan, spinnerKodepos;
    private int tempProvinsi;
    List<ListTempat> arrayProvinsi, arrayKabupaten, arrayKecamatan, arrayKelurahan;
    List<ListKodepos> arrayKodepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;

        showDate = findViewById(R.id.show_date);

        spinnerProvinsi = findViewById(R.id.province);
        spinnerKabupaten = findViewById(R.id.kabupaten);
        spinnerKecamatan = findViewById(R.id.kecamatan);
        spinnerKelurahan = findViewById(R.id.kelurahan);
        spinnerKodepos = findViewById(R.id.kodepos);

        final EditText mName = findViewById(R.id.nama);
        final EditText mPhone = findViewById(R.id.phone_number);
        final EditText mEmail = findViewById(R.id.email);
        final EditText mStreetAddress = findViewById(R.id.street_address);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sName = mName.getText().toString();
                String sPhone = mPhone.getText().toString();
                String sEmail = mEmail.getText().toString();
                String sStreetAddress = mStreetAddress.getText().toString();
                String addressCity = mProvinsi + " " + mKabupaten + " " + mKecamatan + " " + mKelurahan;

                DataJson dataJson = new DataJson(sName, sPhone,sEmail, mDate, sStreetAddress, addressCity, mKodepos );
                sendApplication(dataJson);

//                move to next page
                showApplicationPage();
            }
        });

//        connect with viewModel for provinsi
        ProvinsiModelView modelProvinsi = ViewModelProviders.of(this).get(ProvinsiModelView.class);
        modelProvinsi.getProvinsi().observe(this, new Observer<List<ListTempat>>() {
            @Override
            public void onChanged(@Nullable final List<ListTempat> listTempats) {
                ArrayAdapter<ListTempat> adapter = new ArrayAdapter<ListTempat>(mContext,android.R.layout.simple_spinner_dropdown_item, listTempats);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                arrayProvinsi = listTempats;
                spinnerProvinsi.setAdapter(adapter);
            }
        });

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int id_prov = arrayProvinsi.get(position).getId();
                tempProvinsi = id_prov;
                mProvinsi = arrayProvinsi.get(position).getNama();
                Toast.makeText(mContext, "id prov"+ tempProvinsi, Toast.LENGTH_SHORT).show();

                if (tempProvinsi != 0) {
                    kabupatenCoba(tempProvinsi);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int id_kabupaten = arrayKabupaten.get(position).getId();
                mKabupaten = arrayKabupaten.get(position).getNama();

                if (id_kabupaten != 0){
                    getKecamatan(id_kabupaten);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int id_kecamatan = arrayKecamatan.get(position).getId();
                mKecamatan = arrayKecamatan.get(position).getNama();

                if (id_kecamatan != 0){
                    getKelurahan(id_kecamatan);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String namaKelurahan = arrayKelurahan.get(position).getNama();
                mKelurahan = namaKelurahan;

                if (namaKelurahan != null){
                    getKodepos(namaKelurahan);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerKodepos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mKodepos = arrayKodepos.get(position).getKodepos();
//                String namaKelurahan = arrayKelurahan.get(position).getNama();
//
//                if (namaKelurahan != null){
//                    getKodepos(namaKelurahan);
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    view for calendar
    public void showDatePicker(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);

        mDate = dateMessage;

        Toast.makeText(this, "Date: " + dateMessage, Toast.LENGTH_SHORT).show();

        showDate.setText(dateMessage);
    }

    public void kabupatenCoba(int tempProvinsi){
//        Toast.makeText(mContext, "aku kepanggil", Toast.LENGTH_SHORT).show();

        ProvinsiModelView modelProvinsi = ViewModelProviders.of(this).get(ProvinsiModelView.class);
        modelProvinsi.getKabupaten(tempProvinsi).observe(this, new Observer<List<ListTempat>>() {

            @Override
            public void onChanged(@Nullable List<ListTempat> listTempats) {
                ArrayAdapter<ListTempat> adapter = new ArrayAdapter<ListTempat>(mContext,android.R.layout.simple_spinner_dropdown_item, listTempats);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKabupaten.setAdapter(adapter);
                arrayKabupaten = listTempats;
            }
        });

    }

    public void getKecamatan(int id_kabupaten){
//        Toast.makeText(mContext, "aku kepanggil", Toast.LENGTH_SHORT).show();

        ProvinsiModelView modelProvinsi = ViewModelProviders.of(this).get(ProvinsiModelView.class);
        modelProvinsi.getKecamatan(id_kabupaten).observe(this, new Observer<List<ListTempat>>() {

            @Override
            public void onChanged(@Nullable List<ListTempat> listTempats) {
                ArrayAdapter<ListTempat> adapter = new ArrayAdapter<ListTempat>(mContext,android.R.layout.simple_spinner_dropdown_item, listTempats);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKecamatan.setAdapter(adapter);
                arrayKecamatan = listTempats;
            }
        });

    }

    public void getKelurahan(int id_kecamatan){
//        Toast.makeText(mContext, "aku kepanggil", Toast.LENGTH_SHORT).show();

        ProvinsiModelView modelProvinsi = ViewModelProviders.of(this).get(ProvinsiModelView.class);
        modelProvinsi.getKelurahan(id_kecamatan).observe(this, new Observer<List<ListTempat>>() {

            @Override
            public void onChanged(@Nullable List<ListTempat> listTempats) {
                ArrayAdapter<ListTempat> adapter = new ArrayAdapter<ListTempat>(mContext,android.R.layout.simple_spinner_dropdown_item, listTempats);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKelurahan.setAdapter(adapter);
                arrayKelurahan = listTempats;
            }
        });

    }

    public void getKodepos(String kelurahan){
        ProvinsiModelView modelProvinsi = ViewModelProviders.of(this).get(ProvinsiModelView.class);
        modelProvinsi.getKodepos(kelurahan).observe(this, new Observer<List<ListKodepos>>() {
            @Override
            public void onChanged(@Nullable List<ListKodepos> listKodepos) {
                ArrayAdapter<ListKodepos> adapter = new ArrayAdapter<ListKodepos>(mContext, android.R.layout.simple_spinner_dropdown_item, listKodepos);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerKodepos.setAdapter(adapter);
                arrayKodepos = listKodepos;
            }
        });
    }

    public void sendApplication(DataJson dataJson){
        ProvinsiModelView model = ViewModelProviders.of(this).get(ProvinsiModelView.class);
        model.sendApplication(dataJson).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(mContext, "idnya adalah " + s, Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getSharedPreferences("CreditApp", MODE_PRIVATE);
                preferences.edit().putString("id", s).commit();
            }
        });
    }
//
//    }

    private void showApplicationPage(){
        Intent intent = new Intent(this, show_application.class);
        startActivity(intent);
    }


}
