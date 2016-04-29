package com.example.student.lab14_opendata.util;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.student.lab14_opendata.MyApp.MyApp;
import com.example.student.lab14_opendata.R;
import com.example.student.lab14_opendata.model.TaipeiAttractions;

import java.util.List;

public class AttractionsRecyclerViewAdapter
        extends RecyclerView.Adapter<AttractionsRecyclerViewAdapter.MyViewHolder> {

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attraction, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TaipeiAttractions ta = MyApp.getTaipeiAttractions();
        if (ta == null) {
            holder.m_tv_title.setText("" + position);//現在是第幾項
            return ;
        }
        //設定景點名稱與種類
        holder.m_tv_title.setText(position + "" + ta.getSubTitle(position));
        holder.m_tv_category.setText(ta.getCategory(position));

        //取得圖片網址
        Context context = holder.m_im_image.getContext();
        List<List<String>> list =MyApp.getTaipeiAttractions().getImageUrlsList();

        //如果陣列有資料
        if (list.size() > 0){
            //讀取目前position(第幾項)景點的圖片網址陣列
            List<String> imageUrls = list.get(position);
            String imageUrl = imageUrls.get(0);//讀取陣列中的第一張圖

            //透過Glide在背景載入並設定圖片
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.image)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.m_im_image);

        }
    }

    @Override
    public int getItemCount() {
        TaipeiAttractions ta = MyApp.getTaipeiAttractions();
        if (ta == null) {
            return 10;
        }
        return ta.getCount(); //回傳一共有幾個景點
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView m_tv_title;
        private TextView m_tv_category;
        private ImageView m_im_image;

        //建構子
        public MyViewHolder(View itemView) {
            super(itemView);
            m_tv_title = (TextView)itemView.findViewById(R.id.tv_stitle);
            m_tv_category = (TextView)itemView.findViewById(R.id.tv_category);
            m_im_image = (ImageView)itemView.findViewById(R.id.iv_image);
        }
    }
}
