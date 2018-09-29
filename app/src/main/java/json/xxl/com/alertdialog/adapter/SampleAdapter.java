package json.xxl.com.alertdialog.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import json.xxl.com.alertdialog.R;

/**
 * Created by xxl on 2018/9/29.
 * Description :
 */

public class SampleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SampleAdapter(@Nullable List<String> data) {
        super(R.layout.item_sample_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.title, item);
    }
}
