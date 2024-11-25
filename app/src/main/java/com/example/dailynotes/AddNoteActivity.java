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

public class AddNoteActivity extends AppCompatActivity {
    EditText inputNote;
    Button btnAddNote, btnCancel;

    private static final String SHARED_PREF_NAME = "com.dailynotes.sharedpref_key";
    private static final String KEY_SESSION = "session_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_note);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inputNote = findViewById(R.id.inputNote);
        btnAddNote = findViewById(R.id.btnAddNote);
        btnCancel = findViewById(R.id.btnCancel);

        btnAddNote.setOnClickListener(v -> {
            addNote();
        });
        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void addNote() {
        RequestQueue queueReg = Volley.newRequestQueue(getApplicationContext());
        Connections connection = new Connections();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, connection.addNote,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String jsonMessage = jsonResponse.getString("message");

                            Toast.makeText(getApplicationContext(), jsonMessage,
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                            startActivity(intent);
                        } catch (JSONException e) {
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
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                String sessionKey = sharedPreferences.getString(KEY_SESSION, null);

                Map<String, String> params = new HashMap<>();
                params.put("note", inputNote.getText().toString());
                params.put("session_key", sessionKey);

                return params;
            }
        };
        queueReg.add(stringRequest);
    }
}