package com.lolstudio.retrofit.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.lolstudio.retrofit.R;
import com.lolstudio.retrofit.face.OnHomeTouchListener;
import com.lolstudio.retrofit.model.MeiZhiModelBean;
import com.lolstudio.retrofit.model.MeiZhiModelBean.Results;
import com.lolstudio.retrofit.presenter.MeiZhiPresenter;
import com.lolstudio.retrofit.ui.adapter.HomeAdapter;
import com.lolstudio.retrofit.view.MeiZhiView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lolstudio on 2016/4/18.
 */
public class MainActivity extends AppCompatActivity implements MeiZhiView, OnHomeTouchListener {
    private TextView tv_meizhi;
    private ProgressDialog dialog;
    private RecyclerView rv_test;
    private MeiZhiPresenter presenter;
    private HomeAdapter adapter;
    private List<MeiZhiModelBean.Results> mMeiziList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMeiziList = new ArrayList<>();
        initView();
        rv_test.setLayoutManager(new LinearLayoutManager(this));
        rv_test.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new HomeAdapter(mMeiziList);
        rv_test.setAdapter(adapter);

        dialog = ProgressDialog.show(this, "demo", "wait");
        presenter = new MeiZhiPresenter(this);
        adapter.setOnTouchListener(this);
        presenter.loadData();


        /*步骤1：UI实现View方法，引用Presenter
        步骤2：Presenter调用Model，走Model具体逻辑
        步骤3：Model逻辑实现，回调Presenter方法
        步骤4：Presenter回调View，即回到UI，回调View方法*/
    }

    private void initView() {
        tv_meizhi = (TextView) findViewById(R.id.tv_meizhi);
        rv_test = (RecyclerView) findViewById(R.id.rv_test);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    @Override
    public void showData(List<Results> beanList) {
        tv_meizhi.setText("size>>" + beanList.size());
        adapter.addAll(beanList);
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void onTouch(Context con, Results bean) {
        Toast.makeText(con, bean.getWho(), Toast.LENGTH_SHORT).show();
    }
}
