package security.bercy.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import security.bercy.com.redstartsecurity.R;

/**
 * Created by Bercy on 8/11/17.
 * 第一个设置向导
 */

public class SetUp2Activity extends BaseSetupActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup2);

    }

    @Override
    public void showBackPage() {
        startActivity(new Intent(this, SetUp1Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_back_in, R.anim.trans_back_out);
    }

    @Override
    public void showNextPage() {
        startActivity(new Intent(this, SetUp3Activity.class));
        finish();
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
    }

}
