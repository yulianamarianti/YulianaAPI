package com.example.yulianaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    final String linkAPI = "https://ewinsutriandi.github.io/mockapi/bahasa_pemrograman.json";
    ArrayList<DataCenter> dataCenters = new ArrayList();
    JSONObject jsonObjectApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataAPi();
    }

    void setupListview () {
        ListView listview = findViewById(R.id.listView);
        Adapter adapter = new Adapter(this, dataCenters);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(onItemClick);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            DataCenter fSELECTED = dataCenters.get(position);
            Intent intent = new Intent(MainActivity.this, DetailContent.class);
            intent.putExtra("DATASELECTED", fSELECTED);
            startActivity(intent);
        }
    };

    void getDataAPi() {
        dataCenters.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, linkAPI, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jsonObjectApi = response;
                        refreshView();
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("error", error.toString());
                        Toast.makeText(MainActivity.this, "Erorr: Gagal Mengambil Data", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
        setupListview();
    }

    void refreshView() {
        Iterator<String> key = jsonObjectApi.keys();
        while(key.hasNext()) {
            String nameFramework = key.next();
            try {
                JSONObject data = jsonObjectApi.getJSONObject(nameFramework);
                String des = data.getString("description");
                String official_web = data.getString("read_more");
                String img = data.getString("logo");
                String helloWrold = data.getString("hello_world");

                dataCenters.add(new DataCenter(nameFramework, official_web, des, img, helloWrold));
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        setupListview();
    }
}