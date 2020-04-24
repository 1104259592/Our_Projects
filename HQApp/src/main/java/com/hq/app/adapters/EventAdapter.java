package com.hq.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hq.app.R;
import com.hq.app.activitys.EventDetailActivity;
import com.hq.app.model.entities.EventEntity;
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
