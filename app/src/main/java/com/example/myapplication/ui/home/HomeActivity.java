package com.example.myapplication.ui.home;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.Slider.Adapter.CategoriesListAdapter;
import com.example.myapplication.Slider.Adapter.FilmListAdapter;
import com.example.myapplication.Slider.Adapter.SliderAdapter;
import com.example.myapplication.Slider.Domain.GenreItems;
import com.example.myapplication.Slider.Domain.ListFilm;
import com.example.myapplication.Slider.Domain.SliderItems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private Handler slideHandler = new Handler();
    RecyclerView.Adapter adapterBestMovies, adapterCategories, adapterUpcoming;
    private RecyclerView recyclerViewBestMovies, recyclerViewCategories, recyclerViewUpcoming;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2, mStringRequest3;
    private ProgressBar loading1, loading2, loading3;


    public void initView(){
        viewPager2 = viewPager2.findViewById(R.id.viewpageSlider);
        recyclerViewBestMovies = viewPager2.findViewById(R.id.view1);
        recyclerViewBestMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpcoming = viewPager2.findViewById(R.id.view3);
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategories = viewPager2.findViewById(R.id.view2);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        loading1 = findViewById(R.id.progressBar1);
        loading2 = findViewById(R.id.progressBar2);
        loading3 = findViewById(R.id.progressBar3);

    }

    public void banners(){
        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.movie1));
        sliderItems.add(new SliderItems(R.drawable.movie2));
        sliderItems.add(new SliderItems(R.drawable.movie3));
        sliderItems.add(new SliderItems(R.drawable.movie4));
        sliderItems.add(new SliderItems(R.drawable.movie5));

        viewPager2.setAdapter(new SliderAdapter(sliderItems,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r *0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(slideR);
            }
        });
    }

    public void sendRequestBestMovies(){
        mRequestQueue = Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", (Response.Listener<String>) response -> {
            Gson gson = new Gson();
            loading1.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response,ListFilm.class);
            adapterBestMovies = new FilmListAdapter(items);
            recyclerViewBestMovies.setAdapter(adapterBestMovies);
        }, error -> {
        loading1.setVisibility(View.GONE);
            Log.i("Error", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest);
    }

    public void sendRequestUpComming(){
        mRequestQueue = Volley.newRequestQueue(this);
        loading3.setVisibility(View.VISIBLE);
        mStringRequest3 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=2", (Response.Listener<String>) response -> {
            Gson gson = new Gson();
            loading3.setVisibility(View.GONE);
            ListFilm items = gson.fromJson(response,ListFilm.class);
            adapterUpcoming = new FilmListAdapter(items);
            recyclerViewUpcoming.setAdapter(adapterUpcoming);
        }, error -> {
            loading3.setVisibility(View.GONE);
            Log.i("Error", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest3);
    }

    public void sendRequestCategories(){
        mRequestQueue = Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/genres", (Response.Listener<String>) response -> {
            Gson gson = new Gson();
            loading2.setVisibility(View.GONE);
            ArrayList<GenreItems> catList = gson.fromJson(response, new TypeToken<ArrayList<GenreItems>>(){}.getType());
            adapterCategories = new CategoriesListAdapter(catList);
            recyclerViewCategories.setAdapter(adapterCategories);
        }, error -> {
            loading2.setVisibility(View.GONE);
            Log.i("Error", "onErrorResponse: " + error.toString());
        });
        mRequestQueue.add(mStringRequest2);
    }


    private Runnable slideR = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(slideR);
    }

    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(slideR, 2000);
    }


}
