# toolbar 使用

* [简单应用](#简单应用)
* [自定义ToolBar](#自定义toolbar)
* [参考地址](#参考地址)


## 简单应用
1. 调用android.support.v7.widget.Toolbar这个类，可以再里面嵌套自定义控件，
不过如果toolbar上给自定义控件留出的位置不是很多。

```
<android.support.v7.widget.Toolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="#4876FF">

    <!--自定义控件-->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Clock" />
</android.support.v7.widget.Toolbar>
```
2. 设置menu菜单
```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@id/action_search"
        android:icon="@mipmap/ic_search"
        android:title="@string/menu_search"
        app:showAsAction="ifRoom" />

    <item
        android:id="@id/action_notification"
        android:icon="@mipmap/ic_notifications"
        android:title="@string/menu_notification"
        app:showAsAction="ifRoom" />

    <item
        android:id="@+id/action_item1"
        android:title="@string/item_01"
        app:showAsAction="never" />

    <item
        android:id="@+id/action_item2"
        android:title="@string/item_02"
        app:showAsAction="never" />
</menu>
```

3. 隐藏原有的导航栏

* 可以给Activity设置一个NoActionBar的Theme
* 如果继承的是AppCompatActivity这个类，可以再基类中调用supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
* 如果继承的是Activity这个类，可以在基类中调用requestWindowFeature(Window.FEATURE_NO_TITLE)

4. Activity中处理
toolbar.setNavigationIcon 设置导航栏图标
toolbar.setLogo 设置logo
toolbar.setTitle 设置标题
toolbar.setSubtitle 设置子标题
toolbar.inflateMenu 设置右上角填充菜单
toolbar.setOnMenuItemClickListener 填充菜单的点击事件
```
toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//设置导航栏图标
toolbar.setLogo(R.mipmap.ic_launcher);//设置app logo
toolbar.setTitle("Title");//设置主标题
toolbar.setSubtitle("Subtitle");//设置子标题

toolbar.inflateMenu(R.menu.menu_base_tool_bar);//设置右上角的填充菜单
toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int menuItemId = item.getItemId();
        if (menuItemId == R.id.action_search) {
            Toast.makeText(ToolBarActivity.this , R.string.menu_search , Toast.LENGTH_SHORT).show();

        } else if (menuItemId == R.id.action_notification) {
            Toast.makeText(ToolBarActivity.this , R.string.menu_notification , Toast.LENGTH_SHORT).show();

        } else if (menuItemId == R.id.action_item1) {
            Toast.makeText(ToolBarActivity.this , R.string.item_01 , Toast.LENGTH_SHORT).show();

        } else if (menuItemId == R.id.action_item2) {
            Toast.makeText(ToolBarActivity.this , R.string.item_02 , Toast.LENGTH_SHORT).show();

        }
        return true;
    }
});
```
可以修改标题的大小颜色，和子标题的大小颜色
setTitleTextColor、setTitleTextAppearance、setSubtitleTextColor、setSubtitleTextAppearance


## 自定义ToolBar
有些功能原有的toolbar无法实现，但是toolbar继承ViewGroup，所以可以自定义个新toolbar

例如中间标题样式。
```
public class CustomToolBar extends Toolbar {
    public CustomToolBar(Context context) {
        super(context);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private TextView tvTitleLeft;
    private TextView tvTitle;
    private TextView tvTitleRight;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvTitleLeft = (TextView) findViewById(R.id.tv_title_left);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitleRight = (TextView) findViewById(R.id.tv_title_right);
    }

    /**
     * 获取标题控件
     * @return
     */
    public TextView getMainTitle(){
        return tvTitle;
    }

    /**
     * 获取左侧控件
     * @return
     */
    public TextView getMainTitleLeft(){
        return tvTitleLeft;
    }

    /**
     * 获取右侧控件
     * @return
     */
    public TextView getMainTitleRight(){
        return tvTitleRight;
    }

    //设置主title的内容
    public void setMainTitle(String text) {
        if(tvTitle != null){
            this.setTitle(" ");
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(text);
        }
    }

    //设置主title的内容文字的颜色
    public void setMainTitleColor(int color) {
        if(tvTitle != null)
            tvTitle.setTextColor(color);
    }

    //设置title左边文字
    public void setMainTitleLeftText(String text) {
        if(tvTitleLeft != null){
            tvTitleLeft.setVisibility(View.VISIBLE);
            tvTitleLeft.setText(text);
        }
    }

    //设置title左边文字颜色
    public void setMainTitleLeftColor(int color) {
        if(tvTitleLeft != null)
            tvTitleLeft.setTextColor(color);
    }

    //设置title左边图标
    public void setMainTitleLeftDrawable(int res) {
        if(tvTitleLeft != null){
            Drawable dwLeft = ContextCompat.getDrawable(getContext(), res);
            dwLeft.setBounds(0, 0, dwLeft.getMinimumWidth(), dwLeft.getMinimumHeight());
            tvTitleLeft.setCompoundDrawables(dwLeft, null, null, null);
        }
    }

    //设置title右边文字
    public void setMainTitleRightText(String text) {
        if(tvTitleRight != null){
            tvTitleRight.setVisibility(View.VISIBLE);
            tvTitleRight.setText(text);
        }
    }

    //设置title右边文字颜色
    public void setMainTitleRightColor(int color) {
        if(tvTitleRight != null)
            tvTitleRight.setTextColor(color);
    }

    //设置title右边图标
    public void setMainTitleRightDrawable(int res) {
        if(tvTitleRight != null){
            Drawable dwRight = ContextCompat.getDrawable(getContext(), res);
            dwRight.setBounds(0, 0, dwRight.getMinimumWidth(), dwRight.getMinimumHeight());
            tvTitleRight.setCompoundDrawables(null, null, dwRight, null);
        }
    }

    //设置toolbar状态栏颜色
    public void setToolbarBackground(int res) {
        this.setBackgroundResource(res);
    }

    //设置toolbar左边图标
    public void setToolbarLeftBackImageRes(int res) {
        this.setNavigationIcon(res);
    }

    //设置toolbar左边文字
    public void setToolbarLeftText(String text) {
        this.setNavigationContentDescription(text);
    }

    //设置toolbar的标题
    public void setToolbarTitle(String text) {
        this.setTitle(text);
    }

    //设置toolbar标题的颜色
    public void setToolbarTitleColor(int color) {
        this.setTitleTextColor(color);
    }

    //设置toolbar子标题
    public void setToolbarSubTitleText(String text) {
        this.setSubtitle(text);
    }

    //设置toolbar子标题颜色
    public void setToolbarSubTitleTextColor(int color) {
        this.setSubtitleTextColor(color);
    }

}

```
xml中配置
```
<?xml version="1.0" encoding="utf-8"?>
<com.dawn.toolbardawn.CustomToolBar
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="#4876FF"
    app:contentInsetLeft="0dp"
    app:contentInsetStart="0dp">
    <TextView
        android:id="@+id/tv_title_left"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:textSize="16dp"/>
    <TextView
        android:id="@+id/tv_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"/>
    <TextView
            android:id="@+id/tv_title_right"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right"
            android:layout_marginRight="10dp"
            android:textSize="16dp"/>
</com.dawn.toolbardawn.CustomToolBar>
```
主xml只需要include进来就可以啦。

## 参考地址

[http://www.jianshu.com/p/79604c3ddcae](http://www.jianshu.com/p/79604c3ddcae)
[http://www.jianshu.com/p/8d59d7a01f4c](http://www.jianshu.com/p/8d59d7a01f4c)