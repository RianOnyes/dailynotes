package com.example.dailynotes;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

public class NoteViewActivity extends AppCompatActivity {
    EditText txtNoteView;
    TextView txtNoteDate;
    ImageButton btnBack, btnEdit, btnDelete, btnUpdate;

    private static final String SHARED_PREF_NAME = "com.dailynotes.sharedpref_key";
    private static final String KEY_SESSION = "session_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Init Widget
        txtNoteView = findViewById(R.id.txtNoteView);
        txtNoteDate = findViewById(R.id.txtNoteDate);
        btnBack = findViewById(R.id.btnBack);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);

//        Intent Get Data
        Intent intent = getIntent();
        String note = intent.getStringExtra("note");
        String date = intent.getStringExtra("date");
        String idNote = intent.getStringExtra("id");

//        Set Text Widget
        txtNoteView.setText(note);
        txtNoteDate.setText(date);

//        Run Functions
        backButton();
        editButton();
        deleteButton(idNote);
        updateButton(idNote);

    }

//    Functions
    public void backButton() {
        btnBack.setOnClickListener(View -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
    public void editButton() {
        btnEdit.setOnClickListener(View -> {
            txtNoteView.setEnabled(true);
            btnEdit.setVisibility(INVISIBLE);
            btnDelete.setVisibility(INVISIBLE);
            btnUpdate.setVisibility(VISIBLE);
        });
    }

    public void deleteButton(String idNote) {
        btnDelete.setOnClickListener(View -> {
            RequestQueue queueReg = Volley.newRequestQueue(getApplicationContext());
            Connections connection = new Connections();
            String url = connection.deleteNote + "?id=" + idNote;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                String jsonMessage = jsonResponse.getString("message");

                                Toast.makeText(getApplicationContext(), jsonMessage,
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(NoteViewActivity.this,
                                        MainActivity.class);
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
            });
            queueReg.add(stringRequest);
        });
    }

    public void updateButton(String idNote) {
        btnUpdate.setOnClickListener(View -> {
            RequestQueue queueUpdate = Volley.newRequestQueue(getApplicationContext());
            Connections connection = new Connections();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, connection.updateNote,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                String jsonMessage = jsonResponse.getString("message");

                                Toast.makeText(getApplicationContext(), jsonMessage,
                                        Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(NoteViewActivity.this,
                                        MainActivity.class);
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

                    Map<String, String> params = new HashMap<>();
                    params.put("note", txtNoteView.getText().toString());
                    params.put("id", String.valueOf(Integer.parseInt(idNote)));

                    return params;
                }
            };
            queueUpdate.add(stringRequest);
        });
    }


}