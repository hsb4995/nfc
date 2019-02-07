package com.harjot.kiosknfc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cdflynn.android.library.checkview.CheckView;

/**
 * Created by harjot on 7/2/19.
 */

public class ChequeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        Log.e("inNFC","NFC Act called");
        sendRequestToRegisterChequeDrop();
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
                                if(status.equals("success")){
                                    //show confirmation for cheque drop
//                                    CheckView mc = (CheckView) findViewById(R.id.check);
//                                    mc.check();
                                    TextView tx = findViewById(R.id.textView);
                                    tx.setText("Thanks for confirming cheque Drop ");
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
