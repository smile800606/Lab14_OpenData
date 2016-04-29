package com.example.student.lab14_opendata.model;


import com.example.student.lab14_opendata.beans.TaipeiAttractionsBean;

import java.util.List;

public class TaipeiAttractions {

    private TaipeiAttractionsBean bean;
    private List<TaipeiAttractionsBean.ResultBean.ResultsBean> attractions;
    private List<List<String>> imageUrlsList;

    public TaipeiAttractions(TaipeiAttractionsBean bean, List<List<String>> imageUrlsList) {
        this.bean = bean;
        this.attractions = bean.getResult().getAttractions();
        this.imageUrlsList = imageUrlsList;
    }
    public int getCount() {
        return bean.getResult().getCount();
    }
    public String getSubTitle(int index) {
        return attractions.get(index).getStitle();
    }
    public String getCategory(int index){
        return attractions.get(index).getCategory();
    }
    public List<List<String>> getImageUrlsList(){
        return imageUrlsList;
    }
    }



