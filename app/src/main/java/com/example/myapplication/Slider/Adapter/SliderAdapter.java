/*  GROUP 17
    Ngo Le Thien An - ITITDK21030
    Huynh Thanh Thuy - ITITIU21325
    Cao Hoang Khoi Nguyen - ITITDK21048
    Nguyen Dinh Thang - ITITIU21309
    Purpose: This Java class is a RecyclerView adapter designed for a slider, managing the display of images with Glide, implementing looping behavior, and supporting transformations using the ViewPager2.
*/
package com.example.myapplication.Slider.Adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;
import com.example.myapplication.Slider.Domain.SliderItems;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private List<SliderItems> sliderItem;
    private ViewPager2 viewPager2;
    private Context context;

    public SliderAdapter(List<SliderItems> sliderItem, ViewPager2 viewPager2) {
        this.sliderItem = sliderItem;
        this.viewPager2 = viewPager2;
    }


    @NonNull
    @Override
    public SliderAdapter.SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slider_item_container,parent,false
        ));
    }




    @Override
    public void onBindViewHolder(@NonNull SliderAdapter.SliderViewHolder holder, int position) {
        holder.setImage(sliderItem.get(position));
        if(position == sliderItem.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {

        return sliderItem.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImage(SliderItems sliderItems) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(60));

            Glide.with(context)
                    .load(sliderItems.getImageUrl())
                    .apply(requestOptions)
                    .into(imageView);
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItem.addAll(sliderItem);
            notifyDataSetChanged();
        }
    };
}
