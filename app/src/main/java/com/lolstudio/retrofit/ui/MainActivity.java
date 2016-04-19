package com.lolstudio.retrofit.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lolstudio.retrofit.R;
import com.lolstudio.retrofit.model.MeiZhiModelBean;
import com.lolstudio.retrofit.presenter.MeiZhiPresenter;
import com.lolstudio.retrofit.view.MeiZhiView;


/**
 * Created by lolstudio on 2016/4/18.
 */
public class MainActivity extends AppCompatActivity implements MeiZhiView {
    private TextView tv_meizhi;
    private ProgressDialog dialog;
    private com.lolstudio.retrofit.presenter.MeiZhiPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_meizhi = (TextView) findViewById(R.id.tv_meizhi);
        dialog = ProgressDialog.show(this, "demo", "wait");
        presenter = new MeiZhiPresenter(this);
        presenter.loadData();

        /*步骤1：UI实现View方法，引用Presenter
        步骤2：Presenter调用Model，走Model具体逻辑
        步骤3：Model逻辑实现，回调Presenter方法
        步骤4：Presenter回调View，即回到UI，回调View方法*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showData(MeiZhiModelBean bean) {
        tv_meizhi.setText(bean.toString());
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }
}
