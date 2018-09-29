package json.xxl.com.lalertdialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by xxl on 2017/10/18.
 * json.xxl.com.lalertdialog.AlertDialog 可以实现链式调用
 * 可以修改Dialog和获取布局里面的文字
 * 可以设置点击事件，可以设置默认动画，可以设置宽高，可以设置弹出位置等
 */

public class AlertDialog extends Dialog {

    private static AlertController mAlert;

    public AlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    /**
     * 获取dialog里面的文本内容
     *
     * @param viewId
     * @return
     */
    public String getText(int viewId) {
        return mAlert.getText(viewId);
    }

    /**
     * 获取dialog里面的控件
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        return mAlert.getView(viewId);
    }

    /**
     * 不用Builder链式调用时候的点击事件
     */
    public void setOnClickLisenter(int viewId, View.OnClickListener lisenter) {
        mAlert.setOnClickLisenter(viewId, lisenter);
    }

    public static class Builder {
        private final AlertController.AlertParams P;

        public Builder(Context context) {
            this(context, R.style.AppDialogStyle);
        }

        public Builder(Context context, int themeId) {
            P = new AlertController.AlertParams(context, themeId);
        }

        /**
         * 设置dialog内容布局文件
         *
         * @param view
         * @return
         */
        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        /**
         * 设置dialog内容布局文件
         *
         * @param viewId
         * @return
         */
        public Builder setContentView(int viewId) {
            P.mView = null;
            P.mViewLayoutResId = viewId;
            return this;
        }

        /**
         * 设置文本
         *
         * @param textResId 设置的文本的id
         * @param text      文本内容
         * @return
         */
        public Builder setText(int textResId, CharSequence text) {
            P.mTextArray.put(textResId, text);
            return this;
        }

        /**
         * 设置点击事件
         *
         * @param resId    dialog 里面的控件id
         * @param listener 点击事件监听
         * @return
         */
        public Builder setOnClickLisenter(int resId, View.OnClickListener listener) {
            P.mClickArray.put(resId, listener);
            return this;
        }

        /**
         * 设置宽度全屏显示
         *
         * @return
         */
        public Builder setFullWidth() {
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 设置宽高
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 设置从底部弹出
         *
         * @param isAnimation 是否需要动画
         * @return
         */
        public Builder setFromBottom(boolean isAnimation) {
            if (isAnimation) {
                //动画样式
                P.mAnimation = R.style.DialogFromBottom;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 设置默认弹出动画
         *
         * @return
         */
        public Builder setDefaultAnimation() {
            P.mAnimation = R.style.DialogFromBottom;
            return this;
        }

        /**
         * 设置动画
         *
         * @param animtionStyleId 动画style资源id
         * @return
         */
        public Builder setAnimation(int animtionStyleId) {
            P.mAnimation = animtionStyleId;
            return this;
        }

        /**
         * 设置点击外部是否可以取消
         * @param cancelable
         * @return
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * 设置控件是否可见
         * @param resId
         * @param visible
         * @return
         */
        public Builder setVisible(int resId,boolean visible){
            P.mVisibleArray.put(resId,visible);
            return this;
        }


        AlertDialog create() {
            // We can't use Dialog's 3-arg constructor with the createThemeContextWrapper param,
            // so we always have to re-set the theme
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
