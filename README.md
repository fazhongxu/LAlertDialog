## LAlertDialog 使用介绍

### 给我一个你想显示的View 然后设置数据 我还你一个你要的Dialog

### 好处  全程只有1个好处 就是使用   方便!!! 


### UI显示
#### 你想要我长成什么样，你就给我穿一个布局或者布局id，然后你去处理数据就可以了。
#### 这样也就支持任何View，任何 View

#### UI复用
#### 长相差不多的UI可以无限复用

#### 如： 左边取消，右边确定  想要修改文字  为 左边 确定，右边关闭  .setText(R.id.ui_dialog_input_et,"我们一起学猫叫～～～") 并支持颜色 背景等修改（你都拿到View了，想干什么都是你的是）

### 使用方式 
```

   alertDialog =
               // 这里是kotlin 方式不用new  Java 使用需要new 创建对象
               //Builder(this) 有两个构造方法  单个参数 Context 两个参数的构造 第一为 Context 第二个为自定义的dialog 的主题 
               //如 Builder(this,R.style.AppDialogStyle)
               
               AlertDialog.Builder(this)   
                        //设置布局 参数 布局id 或者 布局 view
                       .setContentView(R.layout.ui_test_dialog_layout) 
                       //设置弹出和消失的默认动画           
                       .setDefaultAnimation()  
                       // 设置dialog 布局内容充满全屏                                   
                       .setFullWidth()      
                        // 点击外部是否可以消失 参数 true 或 false                                      
                       .setCancelable(false)     
                       //设置dialog 内部 某个控件可见不可见 false 为 gone ; true 为 Visible 默认Visible                                
                       .setVisible(R.id.ui_dialog_ok_btn,false)   
                       // 给控件设置文本 参数1 控件id  参数2 需要设置的文本内容         
                       .setText(R.id.ui_dialog_input_et,"我们一起学猫叫～～～") 
                       // 设置dialog 底部弹出 参数 true 表示 底部弹出时带动画; false 表示 弹出时不带动画  默认屏幕是中国间弹出 
                       .setFromBottom(false)     
                       //设置自定义宽高                                  
                       .setWidthAndHeight(900,300) 
                       //设置自定义弹出和消失动画****                  
                       .setAnimation(R.style.DialogFromBottom)  
                       .setOnClickLisenter(R.id.ui_dialog_ok_btn) {
                               //do Something
                       }       
                       .show()                                                     

                    // 根据资源id 获取 控件 
                    val btn : Button = alertDialog?.getView(R.id.ui_dialog_ok_btn) as Button

                    // 获取到控件之后 做需要做的操作
                    btn.setTextColor(ContextCompat.getColor(this,R.color.colorAccent))

                    btn.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimaryDark))
```
### Dependency

#### Step 1. Add the JitPack repository to your build file
    
```
        allprojects {
                repositories {
                    ...
                    maven { url 'https://jitpack.io' }
                }
            }
```
#### Step 2. Add the dependency

```
    dependencies {
	        implementation 'com.github.fazhongxu:LAlertDialog:v0.0.2'
	}
```

#### version report

    v0.0.1  基本功能实现 第一个版本
    
    v0.0.2  放出 Builder(this,R.style.AppDialogStyle) 两个构造函数的访问权限  

### 效果图 
![image] (https://github.com/fazhongxu/LAlertDialog/blob/master/images/001.png)
![image] (https://github.com/fazhongxu/LAlertDialog/blob/master/images/002.png)


###demo apk
### Demo 地址  https://github.com/fazhongxu/LAlertDialog/blob/master/apk/lalertdialog.apk
