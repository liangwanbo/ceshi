package com.cblue.xutils.sample;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import com.lidroid.xutils.sample.R;


@ContentView(R.layout.mybitmaputils)
public class BitmapUtilsActivity extends Activity {
	
	@ViewInject(R.id.bitmaputils_iv)
	private ImageView bitmapUtils_IV;
	private BitmapUtils mBitmapUtils;
	private Context mContext;
	private static final String SAVE_FILE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath();
	private static final String WEB_URL="http://192.172.10.210:8080/Android/baidu.gif";
	private BitmapDisplayConfig mBitmapDisplayConfig ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = getApplication().getApplicationContext();
		ViewUtils.inject(this);
		Log.i("BitmapUtilsActivity", WEB_URL);
		showPic();
		
	}
	
	private void showPic(){
		//得到当前运行内存的1/8
		int memoryCacheSize = (int) ((Runtime.getRuntime().maxMemory())/8);
		 mBitmapUtils = new BitmapUtils(mContext,SAVE_FILE_PATH,memoryCacheSize);
		 //设置显示配置
		 mBitmapDisplayConfig = new BitmapDisplayConfig();
		 AlphaAnimation mAlphaAnimation = new AlphaAnimation(0.0f,1.0f);
		 mAlphaAnimation.setDuration(2*100);
		 mBitmapDisplayConfig.setAnimation(mAlphaAnimation);
		 mBitmapDisplayConfig.setBitmapConfig(Config.RGB_565);
		 
		 //设置显示
		 mBitmapUtils.display(bitmapUtils_IV, WEB_URL, mBitmapDisplayConfig);
		 mBitmapUtils.configDefaultBitmapMaxSize(50, 50);
		 mBitmapUtils.configDefaultCacheExpiry(30*1000);
		 mBitmapUtils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
		 mBitmapUtils.configDefaultLoadingImage(R.drawable.ic_launcher);
		 	 
	}

}
