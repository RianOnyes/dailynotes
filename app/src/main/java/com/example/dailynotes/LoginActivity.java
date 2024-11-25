package com.example.dailynotes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText logUsername, logPassword;
    Button btnLog, btnReg;

    private static final String SHARED_PREF_NAME = "com.dailynotes.sharedpref_key";
    private static final String KEY_SESSION = "session_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String sessionKey = sharedPreferences.getString(KEY_SESSION, null);
        if (sessionKey != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

//        Init Widget
        logUsername = findViewById(R.id.logUsername);
        logPassword = findViewById(R.id.logPassword);
        btnLog = findViewById(R.id.btnLog);
        btnReg = findViewById(R.id.btnReg);

//        Events
        btnLog.setOnClickListener(View -> {
            logUser();
        });

        btnReg.setOnClickListener(View -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }

//    Functions
    public void logUser() {
        RequestQueue queueReg = Volley.newRequestQueue(getApplicationContext());
        Connections connection = new Connections();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, connection.login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            JSONObject jsonResponse = new JSONObject(response);
                            String jsonMessage = jsonResponse.getString("message");
                            String jsonSessionKey = jsonResponse.getString("session_key");

                            Toast.makeText(getApplicationContext(), jsonMessage,
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), jsonSessionKey,
                                    Toast.LENGTH_SHORT).show();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_SESSION, jsonSessionKey);
                            editor.apply();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Login failed!",
                                    Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", logUsername.getText().toString());
                params.put("password", logPassword.getText().toString());

                return params;
            }
        };
        queueReg.add(stringRequest);
    }
}