package com.hq.app.model.impl;

import android.content.Context;

import com.hq.app.R;
import com.hq.app.model.ThemeSettingModel;
import com.hq.app.model.entities.ThemeEntity;
import com.hq.app.presenter.OnThemeSelectListener;
import com.hq.app.mylibrary.utils.ThemeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题设置model实现类
 */
public class ThemeSettingModelImpl implements ThemeSettingModel {

    private Context context;
    private OnThemeSelectListener listener;

    public ThemeSettingModelImpl(Context context, OnThemeSelectListener listener) {
        this.context = context;
        this.listener = listener;
    }

    //获取所有主题数据并回调
    @Override
    public void getThemeData() {
        //接口请求代码示例
//        Map<String, String> map = new HashMap<>();
//        String url = String.format("");
//        RetrofitUtil.request(url, map, 0, callback, (Activity) context);

        List<ThemeEntity> list = new ArrayList<>();
        list.add(new ThemeEntity("默认", R.color.colorPrimary, ThemeUtil.ThemeStyle.DEFAULT));
        list.add(new ThemeEntity("蓝色", R.color.titleColor, ThemeUtil.ThemeStyle.BLUE));
        list.add(new ThemeEntity("黑色", R.color.black, ThemeUtil.ThemeStyle.BLACK));

        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getThemeStyle() == ThemeUtil.getThemeStyle(context)) {
                position = i;
                break;
            }
        }

        listener.onThemeSelectData(list, position);
    }

    //接口返回值回调
//    private RetrofitUtil.HttpRetrofitCallback callback = new RetrofitUtil.HttpRetrofitCallback() {
//        @Override
//        public void onSuccess(Message message) {
//            switch (message.what) {
//                case 0:
//                    DialogUtil.notifyDialog(context, "返回值\n" + message.obj);
//                    break;
//            }
//        }
//
//
//        @Override
//        public void onError(String msg) {
//            DialogUtil.notifyDialog(context, msg);
//        }
//
//        @Override
//        public void onNotify(String notify) {
//            DialogUtil.notifyDialog(context, notify);
//        }
//    };

    //选择回调
    @Override
    public void select(int position) {
        listener.onThemeSelectResult(position);
    }
}
