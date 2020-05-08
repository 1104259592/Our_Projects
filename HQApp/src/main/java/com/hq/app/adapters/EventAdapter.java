package com.hq.app.adapters;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hq.app.R;
import com.hq.app.entities.EventEntity;
import com.hq.app.mylibrary.baseconfigure.App;

import java.util.List;

/**
 * @Class: EventAdapter
 * @Description:
 * @author: zhangqi
 * @Date: 2020/4/21
 */
public class EventAdapter extends BaseQuickAdapter<EventEntity, BaseViewHolder> {


    public EventAdapter(@Nullable List<EventEntity> data) {
        super(R.layout.event_itemlayout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventEntity item) {
        helper.setText(R.id.event_item_name,item.getTitle());
        Glide.with(App.getContext()).load(item.getPic())
        .into((ImageView) helper.getView(R.id.event_item_image));
    }


}
