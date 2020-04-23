package com.hq.app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hq.app.R;
import com.hq.app.activitys.EventDetailActivity;
import com.hq.app.model.entities.EventEntity;

import java.util.List;

/**
 * @Class: EventAdapter
 * @Description:
 * @author: zhangqi
 * @Date: 2020/4/21
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Context mContext;

    private List<EventEntity> mEventEntityList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView eventImageView;
        TextView eventName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            eventImageView = itemView.findViewById(R.id.event_item_image);
            eventName = itemView.findViewById(R.id.event_item_name);
        }
    }

    public EventAdapter(List<EventEntity> mEventEntityList) {
        this.mEventEntityList = mEventEntityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null){
            mContext = viewGroup.getContext();
        }
        View v = LayoutInflater.from(mContext).inflate(R.layout.event_itemlayout, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = viewHolder.getAdapterPosition();
                EventEntity eventEntity = mEventEntityList.get(adapterPosition);
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                intent.putExtra(EventDetailActivity.EVENT_NAME,eventEntity.getTitle());
                intent.putExtra(EventDetailActivity.EVENT_IMAGE_ID,eventEntity.getPic());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EventEntity eventEntity = mEventEntityList.get(i);
        viewHolder.eventName.setText(eventEntity.getTitle());
        Glide.with(mContext).load(eventEntity.getPic())
        .into(viewHolder.eventImageView);

    }

    @Override
    public int getItemCount() {
        return mEventEntityList.size();
    }

}
