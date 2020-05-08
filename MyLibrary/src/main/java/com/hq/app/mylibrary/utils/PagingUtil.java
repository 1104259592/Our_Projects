package com.hq.app.mylibrary.utils;

import java.util.List;

/**
 * 分页工具
 */
public class PagingUtil {

    private int total;//总数量
    private int pageSize = 20;//每页数量
    private int pages = 1;//页数
    private boolean isFirstPage = false;//是否是第一页
    private boolean isLastPage = false;//是否是最后一页
    private PageCallBack callBack;

    public PagingUtil(PageCallBack callBack) {
        this.callBack = callBack;
    }

    //解析数据，判断是否是最后一页并返回数据
    public <T> List<T> getList(String gsonString, Class<T> cls) {
        List<T> list = GsonUtil.GsonToList(gsonString, cls);
        if (list == null || list.size() == 0) {
            setLastPage();
        }
        return list;
    }

    //设置每页数量
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    //获取每页数量
    public int getPageSize() {
        return pageSize;
    }

    //获取总数量
    public int getTotal() {
        return total;
    }

    //第一页
    public void firstPage() {
        pages = 1;
        isFirstPage = true;
        isLastPage = false;
        if (callBack != null) {
            callBack.onFirstPage();
        }
    }

    //下一页
    public void nextPage() {
        pages++;
        isFirstPage = false;
        if (callBack != null) {
            callBack.onNextPage(isLastPage);
        }
    }

    //获取当前页数
    public int getPages() {
        return pages;
    }

    //最后一页赋值
    public void setLastPage() {
        pages--;
        isLastPage = true;
    }

    //是否是第一页
    public boolean isFirstPage() {
        return isFirstPage;
    }

    //是否是最后一页
    public boolean isLastPage() {
        return isLastPage;
    }

    //分页回调
    public interface PageCallBack {
        void onFirstPage();

        void onNextPage(boolean isLastPage);
    }
}
