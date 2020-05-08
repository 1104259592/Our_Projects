package com.hq.app.mylibrary.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.hq.app.mylibrary.utils.PagingUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * Created by Administrator on 2020/4/27.
 * 在SmartRefreshLayout基础上，编辑分页刷新控件
 */
public class PagingSmartRefreshLayout extends SmartRefreshLayout {

    private PagingUtil pageUtil;

    public PagingSmartRefreshLayout(Context context) {
        super(context);
        init();
    }

    //布局加载时，走此构造
    public PagingSmartRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        pageUtil = new PagingUtil(callBack);
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageUtil.firstPage();
            }
        });

        setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageUtil.nextPage();
            }
        });
    }

    //分页工具回调
    PagingUtil.PageCallBack callBack = new PagingUtil.PageCallBack() {
        @Override
        public void onFirstPage() {
            if (onPagingRefreshListener != null) {
                onPagingRefreshListener.onRefresh(pageUtil.getPages(), pageUtil.getPageSize());
            }
        }

        @Override
        public void onNextPage(boolean isLastPage) {
            if (onPagingRefreshListener != null) {
                onPagingRefreshListener.onLoadMore(pageUtil.getPages(), pageUtil.getPageSize(), isLastPage);
            }
        }
    };

    //解析数据并返回数据
    public <T> List<T> getList(String gsonString, Class<T> cls) {
        return pageUtil.getList(gsonString, cls);
    }

    //刷新第一页
    public void pagingRefresh() {
        pageUtil.firstPage();
    }

    //设置每页数量
    public void setPageSize(int pageSize) {
        pageUtil.setPageSize(pageSize);
    }

    //获取总数量
    public int getTotal() {
        return pageUtil.getTotal();
    }

    //关闭刷新
    public void pagingFinishRefresh() {
        if (pageUtil.isFirstPage()) {
            finishRefresh();
        } else {
            finishLoadMore();
        }
    }

    //分页刷新监听
    private OnPagingRefreshListener onPagingRefreshListener;

    public interface OnPagingRefreshListener {
        void onRefresh(int page, int pageSize);

        void onLoadMore(int page, int pageSize, boolean isLastPage);
    }

    public void setOnPagingRefreshListener(OnPagingRefreshListener onPagingRefreshListener) {
        this.onPagingRefreshListener = onPagingRefreshListener;
    }

}