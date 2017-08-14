package security.bercy.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import security.bercy.com.redstartsecurity.R;

/**
 * Created by Bercy on 8/11/17.
 * 第一个设置向导
 */

public class SetUp4Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setup4);
    }
    public void next(View view) {
        startActivity(new Intent(this,LostFindActivity.class));
        finish();
    }
    public void back(View view) {
        startActivity(new Intent(this,SetUp3Activity.class));
        finish();
    }
}
