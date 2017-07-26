package security.bercy.com.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import security.bercy.com.redstartsecurity.R;
import security.bercy.com.utils.StreamUtils;



public class SplashActivity extends Activity {

    private static final int CODE_UPDATE_DIALOG = 0;
    private static final int CODE_URL_ERROR = 1;
    private static final int CODE_NET_ERROR = 2;
    private static final  int CODE_JSON_ERROR = 3;
    private TextView tvVersion;
    //m is server side
    private String mVersionName;
    private int mVersionCode;
    private String mDesc;
    private String mDownloadUrl;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case CODE_UPDATE_DIALOG:
                         showUpdateDialog();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(SplashActivity.this,"URL ERROR", Toast.LENGTH_SHORT).show();
                    break;
                case CODE_NET_ERROR:
                    Toast.makeText(SplashActivity.this,"INTERNET ERROR", Toast.LENGTH_SHORT).show();
                    break;
                case CODE_JSON_ERROR:
                    Toast.makeText(SplashActivity.this,"DATA ERROR", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvVersion.setText("Version: " + getVersionName());
        checkVersion();
    }


    /*
        get version name;
     */
    private String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "";
    }

    /*
        get intlocal version code;
     */
    private int getVersionCode() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /*
        get version from server and check
     */
    private void checkVersion() {
        new Thread() {

            @Override
            public void run() {
                Message msg = Message.obtain();
                HttpURLConnection conn = null;
                try {
                    URL url = new URL("http://10.0.2.2:8080/update.json");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.connect();
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = conn.getInputStream();
                        String result = StreamUtils.readFromStream(inputStream);
                        System.out.println("Server Return " + result);

                        //resolve json
                        JSONObject jo = new JSONObject(result);
                        mVersionName = jo.getString("versionName");
                        mVersionCode = jo.getInt("versionCode");
                        mDesc = jo.getString("description");
                        mDownloadUrl = jo.getString("downloadURL");
                        //System.out.println(mDesc);

                        if (mVersionCode > getVersionCode()) {
                            msg.what = CODE_UPDATE_DIALOG;

                        }
                    }

                } catch (MalformedURLException e) {
                    msg.what = CODE_URL_ERROR;
                    e.printStackTrace();
                } catch (IOException e) {
                    msg.what = CODE_NET_ERROR;
                    e.printStackTrace();
                } catch (JSONException e) {
                    msg.what = CODE_JSON_ERROR;
                    e.printStackTrace();
                }finally {
                    mHandler.sendMessage(msg);
                    if(conn!=null) {
                        conn.disconnect();
                    }
                }
            }

        }.start();

    }

    //update dialog
    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Version:" + mVersionName);
        builder.setMessage(mDesc);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Update now");
            }
        });
        builder.setNegativeButton("Later",null);
        builder.show();
    }

}
