package com.harjot.kiosknfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cdflynn.android.library.checkview.CheckView;

public class MainActivity extends AppCompatActivity {

    File directoryToStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        directoryToStore = getBaseContext().getExternalFilesDir("TestFolder");
        if (!directoryToStore.exists()) {
            if (directoryToStore.mkdir()) ; //directory is created;
        }
        sendRequestToRegister();
       // CheckView mc = (CheckView) findViewById(R.id.check);
       // mc.uncheck();
    }

    public void sendRequestToRegisterChequeDrop() {
        Log.v("register call yay !", "yay");
        String name="Harjot Singh";
        String num="9891971399";
        JSONObject json = new JSONObject();
        String url = "http://alphaomega.southindia.cloudapp.azure.com/parse/functions/chequedrop";
        try {
            json.put("name", name);
            json.put("phone",num);
            JsonObjectRequest strRequest = new JsonObjectRequest(url, json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.v("Res is ------------------->", response.toString());

                                JSONObject res = response.getJSONObject("result");
                                String status = res.getString("status");
                                Log.v("status",status);
                                if(status.equals("success")){
                                    //show confirmation for cheque drop
//                                    CheckView mc = (CheckView) findViewById(R.id.check);
//                                    //mc.check();
                                    TextView tx = findViewById(R.id.textView);
                                    tx.setText("Thanks for confirming cheque Drop ");
                                } else{

                                }
//                                String fname = cust.getString("firstName");
//                                String lname = cust.getString("lastName");
//                                JSONObject ordSum = res.getJSONObject("orderSummary");
//                                String price = ordSum.getString("orderTotalPrice");
//                                String curr = ordSum.getString("currency");
//                                String status = ordSum.getString("deliveryStatus");
                                //EditText ordnum = findViewById(R.id.editText);
//                                String num=ordnum.getText().toString();
//                                String result = "Dear "+fname+" "+lname+" your order: "+num+" has status "+status+" amount paid:"+curr+" "+price;
//                                TextView txt = (TextView) findViewById(R.id.editText2);
                                //txt.setText(result);
                            } catch (Exception e) {
                                Log.v("error", (e.getMessage() == null) ? "failed" : e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("error", (error.getMessage() == null) ? "failed" : error.getMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("X-Parse-Application-Id", "myAppId");
                    params.put("X-Parse-REST-API-Key", "myRestKey");
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(strRequest);
        } catch (Exception e) {
            Log.v("eroor", e.getMessage());

        }
    }
    public void sendRequestToRegister() {
        Log.v("register call yay !", "yay");
        String name="Harjot Singh";
        String num="9891971399";
        JSONObject json = new JSONObject();
        String url = "http://alphaomega.southindia.cloudapp.azure.com/parse/functions/customernfc";
        try {
            json.put("name", name);
            json.put("phone",num);
            JsonObjectRequest strRequest = new JsonObjectRequest(url, json,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.v("Res is ------------------->", response.toString());
                                JSONObject res = response.getJSONObject("result");
                                String status = res.getString("status");
                                if(status.equals("success")){
                                    //show confirmation for cheque drop
//                                    CheckView mc = (CheckView) findViewById(R.id.check);
//                                    mc.check();
                                    TextView tx = findViewById(R.id.textView);
                                    tx.setText("Welcome to DBS Hyderabad ");
                                } else{

                                }
                            } catch (Exception e) {
                                Log.v("error", (e.getMessage() == null) ? "failed" : e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v("error", (error.getMessage() == null) ? "failed" : error.getMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("X-Parse-Application-Id", "myAppId");
                    params.put("X-Parse-REST-API-Key", "myRestKey");
                    return params;
                }
            };

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(strRequest);
        } catch (Exception e) {
            Log.v("eroor", e.getMessage());

        }
    }

}

