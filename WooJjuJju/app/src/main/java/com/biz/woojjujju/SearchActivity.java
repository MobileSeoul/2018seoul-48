package com.biz.woojjujju;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.biz.woojjujju.helper.GoDataViewAdapter;
import com.biz.woojjujju.models.GoDataListVO;
import com.biz.woojjujju.service.GoDataGetterService;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.biz.woojjujju.helper.GoDataViewAdapter.getListVO;

public class SearchActivity extends AppCompatActivity implements GoDataViewAdapter.OnItemClickListener{
    RecyclerView recyclerView ;
    int menu_id;
    public static final String SERV_ID = "servId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        menu_id = getIntent().getIntExtra("MENU_ID",0);
        Log.d("menuid"," "+menu_id);
        recyclerView = findViewById(R.id.txt_list1) ; //recyclerView.findViewById(R.id.txt_list1);
        // 데이터 가져오기
        GoDataGetterService goDataGetterService = new GoDataGetterService();
        try {
            List<GoDataListVO> goDataLists
                    = (List<GoDataListVO>)goDataGetterService.execute(String.valueOf(menu_id),"TEST").get(); // 지금부터 task 를 작동하라

            GoDataViewAdapter adapter = new GoDataViewAdapter(goDataLists);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setOnItemClickListener(SearchActivity.this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.smoothScrollToPosition(0);
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                recyclerView.scrollToPosition(0);
                return false;
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,ServiceActivity.class);
        intent.putExtra(SERV_ID,getListVO.get(position).getServId());

        startActivity(intent);
    }
}
