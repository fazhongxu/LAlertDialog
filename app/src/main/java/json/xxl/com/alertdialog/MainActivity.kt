package json.xxl.com.alertdialog

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import json.xxl.com.alertdialog.adapter.SampleAdapter
import json.xxl.com.lalertdialog.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var alertDialog : AlertDialog ?= null
    var mItmes = listOf("从底部弹出不带动画","从底部弹出带动画","从中间弹出","点击外部不能取消",
            "点击外部可以取消","获取输入框内的内容","自定义宽高","宽度充满全屏","修改按钮文字和输入框文字","隐藏控件")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // show dialog
        val adapter = SampleAdapter(mItmes)

        adapter.setOnItemClickListener { adapter, view, position ->

            when(position) {
                0 -> {
                    alertDialog =
                            AlertDialog.Builder(this,R.style.AppDialogStyle)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setFromBottom(false)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                    }
                                    .show()
                }

                1 -> {
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setFromBottom(true)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                    }
                                    .show()
                }

                2->{
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                    }
                                    .show()
                }

                3 -> {
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setCancelable(false)
                                    .setFromBottom(true)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                    }
                                    .show()
                }

                4->{
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setCancelable(true)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                    }
                                    .show()
                }

                5->{
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setCancelable(true)
                                    .setFromBottom(true)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                        val text = alertDialog?.getText(R.id.ui_dialog_input_et)
                                        if(!TextUtils.isEmpty(text)){
                                            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    .show()
                }

                6 ->{
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setWidthAndHeight(900,500)
                                    .setFromBottom(true)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                        val text = alertDialog?.getText(R.id.ui_dialog_input_et)
                                        if(!TextUtils.isEmpty(text)){
                                            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    .show()
                }

                7 ->{
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setFullWidth()
                                    .setFromBottom(true)
                                    .setText(R.id.ui_dialog_ok_btn,"get")
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                        val text = alertDialog?.getText(R.id.ui_dialog_input_et)
                                        if(!TextUtils.isEmpty(text)){
                                            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    .show()
                }

                8 ->{
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setFromBottom(true)
                                    .setText(R.id.ui_dialog_ok_btn,"Click Me")
                                    .setText(R.id.ui_dialog_input_et,"学猫叫,不学土拨鼠叫了～～～")
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                        val text = alertDialog?.getText(R.id.ui_dialog_input_et)
                                        if(!TextUtils.isEmpty(text)){
                                            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                    .show()

                    val btn : Button = alertDialog?.getView(R.id.ui_dialog_ok_btn) as Button

                    btn.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

                    btn.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark))
                }

                9 -> {
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)
                                    .setFromBottom(true)
                                    .setVisible(R.id.ui_dialog_ok_btn,false)
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                                        alertDialog?.dismiss()
                                        val text = alertDialog?.getText(R.id.ui_dialog_input_et)
                                        if(!TextUtils.isEmpty(text)){
                                            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
                                        }
                                    }

                                    .show()
                }

               /* else ->{
                    alertDialog =
                            AlertDialog.Builder(this)
                                    .setContentView(R.layout.ui_test_dialog_layout)            //设置布局 参数 布局id 或者 布局 view
                                    .setDefaultAnimation()                                     //设置弹出和消失的默认动画
                                    .setFullWidth()                                            // 设置dialog 布局内容充满全屏
                                    .setCancelable(false)                                      // 点击外部是否可以消失 参数 true 或 false
                                    .setVisible(R.id.ui_dialog_ok_btn,false)            //设置dialog 内部 某个控件可见不可见 false 为 gone ; true 为 Visible 默认Visible
                                    .setText(R.id.ui_dialog_input_et,"我们一起学猫叫～～～") // 给控件设置文本 参数1 控件id  参数2 需要设置的文本内容
                                    .setFromBottom(false)                                       // 设置dialog 底部弹出 参数 true 表示 底部弹出时带动画; false 表示 弹出时不带动画  默认屏幕是中国间弹出
                                    .setWidthAndHeight(900,300)                   //设置自定义宽高
                                    .setAnimation(R.style.DialogFromBottom)                   //设置自定义弹出和消失动画
                                    .setOnClickLisenter(R.id.ui_dialog_ok_btn) {      // 点击事件
                                        alertDialog?.dismiss()
                                        val text = alertDialog?.getText(R.id.ui_dialog_input_et)
                                        if(!TextUtils.isEmpty(text)){
                                            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    .show()

                    // 根据资源id 获取 控件
                    val btn : Button = alertDialog?.getView(R.id.ui_dialog_ok_btn) as Button

                    // 获取到控件之后 做需要做的操作
                    btn.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

                    btn.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark))
                }*/
            }

        }

        sample_rv.adapter = adapter



    }
}
