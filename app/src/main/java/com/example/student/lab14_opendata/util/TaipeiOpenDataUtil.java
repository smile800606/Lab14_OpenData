package com.example.student.lab14_opendata.util;


import android.util.Log;

import com.example.student.lab14_opendata.MyApp.MyApp;
import com.example.student.lab14_opendata.api.TaipeiAttractionsOpenData;
import com.example.student.lab14_opendata.beans.TaipeiAttractionsBean;
import com.example.student.lab14_opendata.model.TaipeiAttractions;
import com.example.student.lab14_opendata.observer.Observer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaipeiOpenDataUtil {

    public static final String TAG = "LoadOpenData";

    public static void loadTaipeiAttractions(final Observer observer) {
        Call<TaipeiAttractionsBean> call = TaipeiAttractionsOpenData.apiService.getTaipeiAttractionsBean();
        call.enqueue(new Callback<TaipeiAttractionsBean>() {
            @Override
            public void onResponse(Call<TaipeiAttractionsBean> call, Response<TaipeiAttractionsBean> response) {

                if (!response.isSuccessful()) {  //沒有成功
                    String message = "onResponse() : Unsuccessful , response_code = " + response.code();
                    Log.d(TAG, message);
                    observer.OnError(message);
                    return;
                }
                //有成功
                TaipeiAttractionsBean bean = response.body();
                List<List<String>> imageUrlsList = new ArrayList<>();

                Log.d(TAG, "onResponse() : Successful");
                Log.d(TAG, "count = " + bean.getResult().getCount());

                for (int i = 0; i < bean.getResult().getCount(); i++) {
                    Log.d(TAG, "----------------" + i + "-------------");

                    TaipeiAttractionsBean.ResultBean.ResultsBean attraction =
                                                bean.getResult().getAttractions().get(i);

                    //一個字串裡有多個 Image URL 需要裁切
                    List<String> list = ImageUrlParser.split(attraction.getImagesURLS());

                    imageUrlsList.add(list);
                    //Log日誌
                    logAttraction(attraction);
                    logImageURLs(list);

                    // 將資料寄放在MyApp 建立model 封裝bean 與 imageUrlsList
                    MyApp.setTaipeiAttractions(new TaipeiAttractions(bean, imageUrlsList));
                    observer.OnCompleted();

                }

            }


            @Override
            public void onFailure(Call<TaipeiAttractionsBean> call, Throwable t) {
                observer.OnError(t.toString());
            }
        });
    }

    private static void logAttraction(TaipeiAttractionsBean.ResultBean.ResultsBean attraction) {
//        Log.d(TAG, attraction.getStitle() + "");
//        Log.d(TAG, attraction.getCategory() + "");
//        Log.d(TAG, attraction.getIntroduction() + "");
//        Log.d(TAG, attraction.getAddress() + "");
//        Log.d(TAG, attraction.getTransportation() + "");
//        Log.d(TAG, attraction.getMRT() + "");
//        Log.d(TAG, attraction.getLongitude() + "");
//        Log.d(TAG, attraction.getLatitude() + "");
//        Log.d(TAG, attraction.getMemoTime() + "");
        Log.d(TAG, attraction.getImagesURLS() + "");
    }

    private static void logImageURLs(List<String> list) {
        for (int j = 0; j < list.size(); j++) {
            Log.d(TAG, list.get(j).toString());
        }
    }

    private static class ImageUrlParser {
        public static List<String> split(String urls) {
            if (urls == null || urls.length() == 0) {
                return null;
            }
            List<String> list = new ArrayList<>();
            int start = urls.indexOf("http");
            int end = 0;
            while (start >= 0){
                end = urls.indexOf("http", start + 1);
                if (end < 0) {
                    list.add(urls.substring(start, urls.length()));
                    break;
                }
                list.add(urls.substring(start, end));
                start = end;
            }
            return list;
        }
    }
}