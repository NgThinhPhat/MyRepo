package com.example.rss_reader.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rss_reader.Activities.DTO.Item;
import com.example.rss_reader.Activities.DTO.ItemAdapter;
import com.example.rss_reader.Activities.DTO.RssFeed;
import com.example.rss_reader.Activities.Loaders.FeedLoader;
import com.example.rss_reader.R;

import java.util.List;

public class RssReaderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<RssFeed> {
    EditText editTextRss;
    Button btnLoad;
    RecyclerView recyclerView;
    private static final int LOADER_ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initControl();
        if(getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        }
    }
    private void initView() {
        editTextRss = findViewById(R.id.editTextRss);
        btnLoad = findViewById(R.id.btnLoad);
        recyclerView = findViewById(R.id.itemList);
    }

    private void initControl() {
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryString = editTextRss.getText().toString();

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected() && queryString.length()!=0) {
                    Bundle queryBundle = new Bundle();
                    queryBundle.putString("queryString", queryString);
                    getSupportLoaderManager().restartLoader(LOADER_ID, queryBundle, RssReaderActivity.this);
                } else {
                    if (queryString.length() == 0) {
                    } else {
                    }
                }
            }
        });
    }

    @NonNull
    @Override
    public Loader<RssFeed> onCreateLoader(int id, @Nullable Bundle args) {
        return new FeedLoader(this, args.getString("queryString")+".rss");
    }
    @Override
    public void onLoadFinished(@NonNull Loader<RssFeed> loader, RssFeed data) {
        try {
            List<Item> itemsArray;
            itemsArray = data.getChannels().get(0).getItems();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            ItemAdapter itemAdapter = new ItemAdapter(itemsArray,getApplicationContext());
            recyclerView.setAdapter(itemAdapter);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}
