package json.xxl.com.lalertdialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by xxl on 2017/10/18.
 * Dialog View的辅助类
 */

class DialogViewHelper {
    private View mContentView;
    //软引用用来存放view,防止内存泄漏
    private SparseArray<WeakReference<View>> mSparseArray;

     DialogViewHelper() {
        mSparseArray = new SparseArray<>();
    }

     DialogViewHelper(View contentView) {
        this();
        this.mContentView = contentView;
    }

     DialogViewHelper(Context context, int viewLayoutResId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(viewLayoutResId, null);
    }

     View getContentView() {
        return mContentView;
    }

    /**
     * 设置文本
     *
     * @param viewId 控件id
     * @param text   文本内容
     */
     void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        if (tv != null)
            tv.setText(text);
    }

    /**
     * 获取Dialog布局文件里面的文字
     */
     String getText(int viewId) {
        TextView view = getView(viewId);
        String text = null;
        if (view != null) {
            text = view.getText().toString();
        }
        return text;
    }

    /**
     * 设置控件是否可见
     *
     * @param viewId
     * @param visible 是否可见
     */
    void setVisible(int viewId, Boolean visible) {
        View view = getView(viewId);
        if (view != null) {
            if (visible) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    void setOnClickLisenter(int viewId, View.OnClickListener listener) {
        getView(viewId).setOnClickListener(listener);
    }

    /**
     * findViewById 获取View
     *
     * @param viewId
     */
     <T extends View> T getView(int viewId) {
        //为了减少findViewById次数，用软引用的集合来存放view,相当于做了缓存，
        // 每次findViewById之后把获取到的View保存起来，下次调用先从缓存里面取，取不到再去findView
        WeakReference<View> wrView = mSparseArray.get(viewId);
        View view;
        if (wrView != null) {
            view = wrView.get();
            return (T) view;
        }
        view = mContentView.findViewById(viewId);
        if (view != null) {
            mSparseArray.put(viewId, new WeakReference<View>(view));
        }
        return (T) view;
    }
}
