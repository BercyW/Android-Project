package security.bercy.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;

import android.widget.RelativeLayout;
import android.widget.TextView;


import security.bercy.com.redstartsecurity.R;

/**
 * Created by Bercy on 8/4/17.
 */

public class SettingItemView extends RelativeLayout {

    TextView ivTitle;
    TextView ivDesc;
    CheckBox cbStatus;

    public SettingItemView(Context context) {
        super(context);
        ininView();
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView();
    }

    public SettingItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView();
    }

    /*
        initail layout
     */
    private void ininView() {
        //自定义好的布局文件设置给当前的settingItemView
        View.inflate(getContext(), R.layout.view_setting_items, this);
        ivTitle = (TextView) findViewById(R.id.tv_title);
        ivDesc = (TextView) findViewById(R.id.tv_desc);
        cbStatus = (CheckBox) findViewById(R.id.cb_status);
    }
    public void setTitle(String title) {
        ivTitle.setText(title);
    }
    public void setDesc(String desc) {
        ivDesc.setText(desc);
    }

    public boolean isChecked() {
        return cbStatus.isChecked();
    }
    public void setChecked(boolean check) {
        cbStatus.setChecked(check);
    }
}
