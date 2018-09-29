package json.xxl.com.lalertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by xxl on 2017/10/18.
 */

class AlertController {

    private AlertDialog mAlertDialog;
    private Window mWindow;
    private DialogViewHelper mDialogViewHelper = null;

    AlertController(AlertDialog alertDialog, Window window) {
        this.mAlertDialog = alertDialog;
        this.mWindow = window;
    }

    /**
     * 获取Dialog内文本内容
     *
     * @param viewId
     * @return
     */
    String getText(int viewId) {
        if (mDialogViewHelper == null) {
            throw new IllegalArgumentException("Please show dialog first");
        }
        return mDialogViewHelper.getText(viewId);
    }

    /**
     * 获取Dialog内 控件
     * @param viewId
     * @return
     */
    View getView(int viewId) {
        if (mDialogViewHelper == null) {
            throw new IllegalArgumentException("Please show dialog first");
        }
        return mDialogViewHelper.getView(viewId);
    }
    /**
     * Dialog内控件点击事件
     *
     * @param viewId
     * @param listener
     */
    void setOnClickLisenter(int viewId, View.OnClickListener listener) {
        if (mDialogViewHelper == null) {
            throw new IllegalArgumentException("Please show dialog first");
        }
        mDialogViewHelper.setOnClickLisenter(viewId, listener);
    }



    static class AlertParams {
        final Context mContext;
        //dialog ThmeId
        final int mThemeId;
        //Dialog ContentView
         View mView;
        //Dialog ContentView id
         int mViewLayoutResId;
        //点击空白处是否可以取消
         boolean mCancelable = true;
        //dialog默认弹出位置
         int mGravity = Gravity.CENTER;
        //Dialog Cancel监听
         DialogInterface.OnCancelListener mOnCancelListener;
        //Dialog Dismiss监听
         DialogInterface.OnDismissListener mOnDismissListener;
        //Dialog Key 监听
         DialogInterface.OnKeyListener mOnKeyListener;
        //Dialog 存放文字的集合
         SparseArray<CharSequence> mTextArray = new SparseArray();
        //Dialog 存放View点击事件的集合
         SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        //Dailog默认宽度
         int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        //Dialog默认高度
         int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
        //动画
         int mAnimation = 0;//默认动画设置为R.style.
        //设置控件是否隐藏的集合
        SparseArray<Boolean> mVisibleArray = new SparseArray<>();

        AlertParams(Context context, int themeId) {
            this.mContext = context;
            this.mThemeId = themeId;
        }

        /**
         * dialog构建方法
         */
        public void apply(AlertController alertController) {

            if (mView != null) {
                alertController.mDialogViewHelper = new DialogViewHelper(mView);
            }

            if (mViewLayoutResId != 0) {
                alertController.mDialogViewHelper = new DialogViewHelper(mContext, mViewLayoutResId);
            }

            if (alertController.mDialogViewHelper == null) {
                throw new IllegalArgumentException("setContentView can't be null");
            }

            //设置dialog布局
            alertController.mAlertDialog.setContentView(alertController.mDialogViewHelper.getContentView());

            //dialog文本设置
            //取出文本,设置给Dialog的View
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                alertController.mDialogViewHelper.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            //dialog点击事件设置
            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                alertController.mDialogViewHelper.setOnClickLisenter(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            //dialog里面控件隐藏设置
            int visibleArraySize = mVisibleArray.size();
            for (int i = 0; i < visibleArraySize; i++) {
                alertController.mDialogViewHelper.setVisible(mVisibleArray.keyAt(i), mVisibleArray.valueAt(i));
            }

            if (mOnDismissListener != null) {
                alertController.mAlertDialog.setOnDismissListener(mOnDismissListener);
            }

            //设置dialog自定义效果，全屏，从底部弹出，设置宽高，默认动画，动画等
            //dialog动画
            alertController.mWindow.setWindowAnimations(mAnimation);
            Window window = alertController.mWindow;
            //dialog位置
            window.setGravity(mGravity);
            //设置宽高
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = mWidth;
            lp.height = mHeight;
            window.setAttributes(lp);
        }

    }
}
