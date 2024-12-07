package com.example.dailynotes;

import static android.view.View.INVISIBLE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnMainAct, btnDailyQuotes, btnDailyJokes, btnLogout;
    FloatingActionButton btnAddNote;
    ImageButton btnMenu, btnMenuAside;
    NavigationView navBar;
    RecyclerView noteCards;
    MainRVAdapter adapter;

    private static final String SHARED_PREF_NAME = "com.dailynotes.sharedpref_key";
    private static final String KEY_SESSION = "session_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Shared Preferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String sessionKey = sharedPreferences.getString(KEY_SESSION, null);
        if (sessionKey == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return;
        }

//        Init Widget
        btnMainAct = findViewById(R.id.btnMainAct);
        btnDailyQuotes = findViewById(R.id.btnDailyQuotes);
        btnDailyJokes = findViewById(R.id.btnDailyJokes);
        btnLogout = findViewById(R.id.btnLogout);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenuAside = findViewById(R.id.btnMenuAside);
        btnAddNote = findViewById(R .id.btnAddNote);
        navBar = findViewById(R.id.navBar);


//        Run Functions

        getNotes();
        addNote();
        menuVisibility();
        navMenu();
        userLogout();

    }

//    Functions

    public void getNotes() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String sessionKey = sharedPreferences.getString(KEY_SESSION, null);
        RequestQueue queueGet = Volley.newRequestQueue(getApplicationContext());
        Connections connection = new Connections();
        String url = connection.getNotes+"?session_key="+sessionKey;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ArrayList<String> notes = new ArrayList<>();
                            ArrayList<String> dates = new ArrayList<>();
                            ArrayList<String> ids = new ArrayList<>();

                            JSONObject jsonObject = new JSONObject(response);
                            String jsonMessage = jsonObject.getString("message");
                            JSONArray jsonArray = jsonObject.getJSONArray("notes");

                            Toast.makeText(getApplicationContext(), jsonMessage, Toast.LENGTH_SHORT).show();

                            for (int i =0; i < jsonArray.length(); i++){
                                JSONObject jsonNote = jsonArray.getJSONObject(i);

                                notes.add(jsonNote.getString("note"));
                                dates.add(jsonNote.getString("date"));
                                ids.add(jsonNote.getString("id_note"));
                            }

                            noteCards = findViewById(R.id.noteLists);
                            noteCards.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            adapter = new MainRVAdapter(getLayoutInflater(), notes, dates, ids);
                            noteCards.setAdapter(adapter);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        queueGet.add(stringRequest);
    }

    public void menuVisibility() {
        btnMenu.setOnClickListener(View -> {
            if (navBar.isShown()) {
                navBar.setVisibility(View.INVISIBLE);
            } else {
                navBar.setVisibility(View.VISIBLE);
            }
        });
        btnMenuAside.setOnClickListener(View -> {
            if (navBar.isShown()) {
                navBar.setVisibility(View.INVISIBLE);
            } else {
                navBar.setVisibility(View.VISIBLE);
            }
        });
    }

    public void userLogout() {
        btnLogout.setOnClickListener(View -> {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_SESSION, null);
            editor.apply();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void addNote() {
        btnAddNote.setOnClickListener(View -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
        });
    }

//    Nav Functions
    public void navMenu() {
        btnDailyQuotes.setOnClickListener(View -> {
            Intent intent = new Intent(this, QuotesActivity.class);
            startActivity(intent);
        });
        btnDailyJokes.setOnClickListener(View -> {
            Intent intent = new Intent(this, JokesActivity.class);
            startActivity(intent);
        });
    }
}