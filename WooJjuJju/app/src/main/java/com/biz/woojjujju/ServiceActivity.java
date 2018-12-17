package com.biz.woojjujju;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.biz.woojjujju.helper.ServiceViewAdapter;
import com.biz.woojjujju.models.GoDataListVO;
import com.biz.woojjujju.models.ServiceDataListVO;
import com.biz.woojjujju.service.AllDataGetterService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.biz.woojjujju.SearchActivity.SERV_ID;

public class ServiceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GoDataListVO vo;

    String servId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        recyclerView = findViewById(R.id.txt_data_list);


        servId = getIntent().getStringExtra(SERV_ID);
        Log.d("servID", servId);

        AllDataGetterService allDataGetterService= new AllDataGetterService();
        try {
            List<ServiceDataListVO> goDataLists
                    = (List<ServiceDataListVO>) allDataGetterService.execute(servId,"TEST").get(); // 지금부터 task 를 작동하라

            ServiceViewAdapter adapter = new ServiceViewAdapter(goDataLists);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
