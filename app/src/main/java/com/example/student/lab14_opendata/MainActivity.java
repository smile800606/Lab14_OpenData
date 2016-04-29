package com.example.student.lab14_opendata;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.student.lab14_opendata.observer.Observer;
import com.example.student.lab14_opendata.util.AttractionsRecyclerViewAdapter;
import com.example.student.lab14_opendata.util.TaipeiOpenDataUtil;



public class MainActivity extends AppCompatActivity implements Observer{

    private static final String TAG = "MainActivity";
    private RecyclerView m_rv_attraction;
    private AttractionsRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        TaipeiOpenDataUtil.loadTaipeiAttractions(this);
    }
    private void init() {
        m_rv_attraction = (RecyclerView)findViewById(R.id.rv_attractions);
        m_rv_attraction.setHasFixedSize(true);//告知畫面中的每個ITEM結構都相同
        //每個ITEM垂直排列
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        m_rv_attraction.setLayoutManager(llm);
        //建立和設定資料轉接器
        mAdapter = new AttractionsRecyclerViewAdapter();
        m_rv_attraction.setAdapter(mAdapter);
    }
    @Override
    public void OnCompleted() {
        Log.d(TAG, "OnCompleted");
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnError(String message) {
        Log.d(TAG, "OnError");
        Log.d(TAG, message);

    }
}

