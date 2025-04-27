package com.example.dailynotes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
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

import java.util.HashMap;
import java.util.Map;

public class QuotesActivity extends AppCompatActivity {
    Button btnDailyJokes, btnMainAct;
    FloatingActionButton btnRefreshQuotes;
    ImageButton btnMenu, btnMenuAside;
    NavigationView navBar;
    TextView txtQuotes, txtAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quotes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Init Widget
        btnDailyJokes = findViewById(R.id.btnDailyJokes);
        btnMainAct = findViewById(R.id.btnMainAct);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenuAside = findViewById(R.id.btnMenuAside);
        navBar = findViewById(R.id.navBar);
        txtQuotes = findViewById(R.id.txtQuote);
        txtAuthor = findViewById(R.id.txtAuthor);
        btnRefreshQuotes = findViewById(R.id.btnRefreshQuote);

//        Run Functions
        navMenu();
        menuVisibility();
        getQuotes();
        refreshQuotes();

    }

//    Functions

    public  void refreshQuotes() {
        btnRefreshQuotes.setOnClickListener(View -> {
            getQuotes();
        });
    }

    public void getQuotes() {

        String url = "https://api.api-ninjas.com/v1/quotes?category=";
        String apiKey = "ennbLkQ+A+CuIw8BwcLOVw==0ICNWulexfOMMWBi";

        // Instantiate the RequestQueue
                RequestQueue queue = Volley.newRequestQueue(this);

        // Create the StringRequest
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the response
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    String quote = jsonArray.getJSONObject(0).getString("quote");
                                    String author = jsonArray.getJSONObject(0).getString("author");

                                    txtQuotes.setText("\"" + quote + "\"");
                                    txtAuthor.setText("-" + author);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                                Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                            }
                        }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("X-Api-Key", apiKey);
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

        // Add the request to the RequestQueue
                queue.add(stringRequest);
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

//    Nav Function
    public void navMenu() {
        btnMainAct.setOnClickListener(View -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        btnDailyJokes.setOnClickListener(View -> {
            Intent intent = new Intent(this, JokesActivity.class);
            startActivity(intent);
        });
    }
}