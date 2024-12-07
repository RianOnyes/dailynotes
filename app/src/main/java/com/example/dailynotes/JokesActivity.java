package com.example.dailynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class JokesActivity extends AppCompatActivity {
    Button btnMainAct, btnDailyQuotes;
    FloatingActionButton btnRefreshJokes;
    ImageButton btnMenu, btnMenuAside;
    NavigationView navBar;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jokes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Init Widget
        btnMainAct = findViewById(R.id.btnMainAct);
        btnDailyQuotes = findViewById(R.id.btnDailyQuotes);
        btnMenu = findViewById(R.id.btnMenu);
        btnMenuAside = findViewById(R.id.btnMenuAside);
        btnRefreshJokes = findViewById(R.id.btnRefreshJokes);
        navBar = findViewById(R.id.navBar);
        webView = findViewById(R.id.webView);

//        Run Functions
        getJokes();
        refreshJokes();
        menuVisibility();
        navMenu();

    }

//    Functions
    public void refreshJokes() {
        btnRefreshJokes.setOnClickListener(View -> {
            getJokes();
        });
    }

    public void getJokes() {
        WebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        webView.loadUrl("https://pb.ecampus.id");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setGeolocationEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        webView.loadUrl("https://jokesbapak2.reinaldyrafli.com/api/");

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
        btnDailyQuotes.setOnClickListener(View -> {
            Intent intent = new Intent(this, QuotesActivity.class);
            startActivity(intent);
        });
    }
}