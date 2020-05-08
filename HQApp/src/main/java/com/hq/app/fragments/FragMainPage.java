package com.hq.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hq.app.R;
import com.hq.app.activitys.EventDetailActivity;
import com.hq.app.adapters.EventAdapter;
import com.hq.app.entities.EventEntity;
import com.hq.app.mylibrary.fragments.BaseFragment;
import com.hq.app.mylibrary.utils.RetrofitRequestUtils.RetrofitUtil;
import com.hq.app.mylibrary.views.PagingSmartRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 清盘操作
 */
public class FragMainPage extends BaseLocalFragment {
    private final int GETPROLIST = 0;//获取产品列表

    private RecyclerView mRlvMainDisplay;
    private PagingSmartRefreshLayout mPsrl;

    private EventEntity[] eventEntities = {
            new EventEntity("2手苹果", "https://images.unsplash.com/photo-1558981001-1995369a39cd?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "降价大甩卖"),
            new EventEntity("2手苹果", "https://images.unsplash.com/photo-1587467440782-154ba8654ac0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "降价大甩卖"),
            new EventEntity("2手苹果", "https://images.unsplash.com/photo-1587443836182-345f84c18961?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "降价大甩卖"),
            new EventEntity("2手苹果", "https://images.unsplash.com/photo-1587461244603-2f5b56351de2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "降价大甩卖"),
            new EventEntity("2手苹果", "https://unsplash.com/photos/HjR4bD5Kq7I", "降价大甩卖"),
            new EventEntity("2手苹果", "https://images.unsplash.com/photo-1587432816476-d8df44f42bb8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "降价大甩卖"),
    };

    private EventAdapter eventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (myView == null) {
            myView = View.inflate(activity, R.layout.frag_main_page, null);
        }
        initView(myView);
        init();
        return myView;
    }

    private void initView(View myView) {
        mRlvMainDisplay = (RecyclerView) myView.findViewById(R.id.rlv_main_display);
        mPsrl = (PagingSmartRefreshLayout) myView.findViewById(R.id.psrl);
    }

    private void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2);
        mRlvMainDisplay.setLayoutManager(layoutManager);
        eventAdapter = new EventAdapter(getList());
        mRlvMainDisplay.setAdapter(eventAdapter);
        eventAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                baseStartIntent(EventDetailActivity.startEventDetailActivity(activity,
                        eventAdapter.getItem(position).getTitle(), eventAdapter.getItem(position).getPic()));
            }
        });

        mPsrl.setOnPagingRefreshListener(new PagingSmartRefreshLayout.OnPagingRefreshListener() {
            @Override
            public void onRefresh(int page, int pageSize) {
                showMessage("页数：" + page + "\n每页数量：" + pageSize);
                mPsrl.pagingFinishRefresh();
            }

            @Override
            public void onLoadMore(int page, int pageSize, boolean isLastPage) {
                showMessage("页数：" + page + "\n每页数量：" + pageSize + "\n最后一页:" + isLastPage);
                mPsrl.pagingFinishRefresh();
            }
        });
    }

    //获取产品列表
    private List<EventEntity> getList() {
        List<EventEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(eventEntities.length);
            list.add(0, eventEntities[index]);
        }
        return list;
    }

    //获取产品列表
    private void getProList(int current, int size) {
        Map<String, String> map = new HashMap<>();
        map.put("current", current + "");
        map.put("size", size + "");
        RetrofitUtil.requestGet("", map, GETPROLIST, httpRetrofitCallback, activity);
    }

    //接口数据回调
    RetrofitUtil.HttpRetrofitCallback httpRetrofitCallback = new RetrofitUtil.HttpRetrofitCallback() {
        @Override
        public void onSuccess(Message message) {
            String resultCon = (String) message.obj;
            switch (message.what) {
                case GETPROLIST://获取产品列表
                    mPsrl.pagingFinishRefresh();
                    break;
            }
        }

        @Override
        public void onError(Message message) {
            showMessage((String) message.obj);
            switch (message.what) {
                case GETPROLIST://获取产品列表
                    mPsrl.pagingFinishRefresh();
                    break;
            }
        }

        @Override
        public void onNotify(String msg) {
            showMessage(msg);
        }
    };
}
