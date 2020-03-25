package com.hq.app.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hq.app.mobile.R;
import com.hq.app.model.entities.ThemeEntity;

import java.util.List;

/**
 * 主题设置类适配器
 */
public class ThemeSettingAdapter extends BaseQuickAdapter<ThemeEntity, BaseViewHolder> {

    private int selectIndex = -1;

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, ThemeEntity item) {
        int position = helper.getAdapterPosition();
        helper.setBackgroundRes(R.id.theme_setting_item_01, item.getThemeStyleColor());
        helper.setText(R.id.theme_setting_item_02, item.getThemeStyleName());
        if (position == selectIndex) {
            helper.setText(R.id.theme_setting_item_03, "√");
        } else {
            helper.setText(R.id.theme_setting_item_03, "");
        }
    }

    public ThemeSettingAdapter(List<ThemeEntity> datas) {
        super(R.layout.adapter_theme_setting, datas);//此处直接将布局文件传入即可
    }
}
