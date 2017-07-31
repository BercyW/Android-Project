package security.bercy.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import security.bercy.com.redstartsecurity.R;

/**
 * Created by Bercy on 7/25/17.
 */

public class IndexActivity extends Activity {
    private GridView gvHome;
    private String[] mItems = new String[]{"Lost", "SoftWare", "Contact", "Task", "Data", "Virus",
            "Cache", "Tools", "Setting"};
    private int[] mPics = new int[]{R.drawable.home_safe, R.drawable.home_apps, R.drawable.home_callmsgsafe,
            R.drawable.home_taskmanager, R.drawable.home_netmanager, R.drawable.home_trojan,
            R.drawable.home_sysoptimize, R.drawable.home_tools,
            R.drawable.home_settings};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        gvHome = (GridView) findViewById(R.id.gv_home);
        gvHome.setAdapter(new HomeAdapter());
    }

    class HomeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mItems.length;
        }

        @Override
        public Object getItem(int position) {
            return mItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(IndexActivity.this, R.layout.home_list_items, null);
            ImageView ivItem = (ImageView) view.findViewById(R.id.iv_item);
            TextView tvItem = (TextView) view.findViewById(R.id.tv_item);
            ivItem.setImageResource(mPics[position]);
            tvItem.setText(mItems[position]);
            return view;
        }
    }
}
