package com.cblue.xutils.sample;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import com.lidroid.xutils.sample.R;

/**
 * 需要添加权限
 * @author Administrator
 *
 */
@ContentView(R.layout.mydownload)
public class DownloadActivity extends Activity {
	
	@ViewInject(R.id.download_btn)
	private Button downloadBtn;
	private ProgressBar mProgressBar; 
	HttpUtils mHttpUtils;
	
	private static final String WEB_URL="http://192.172.10.210:8080/Android/800.apk";
	private static final String LOCAL_FILE_PATH=Environment.getExternalStorageDirectory()+File.separator+"x.gif";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(DownloadActivity.this);
		mHttpUtils = new HttpUtils();
		mProgressBar = new ProgressBar(DownloadActivity.this);
	}
	
	@OnClick(R.id.download_btn)
	public void downloadBtnOnClick(View view){
		LogUtils.i("downloadBtnOnClick");
		LogUtils.i("LOCAL_FILE_PATH="+LOCAL_FILE_PATH);
		mHttpUtils.download(WEB_URL, LOCAL_FILE_PATH, new RequestCallBack<File>() {
			
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				LogUtils.i("downloadBtnOnClick--onStart");
				 mProgressBar.setMax(100);
				 mProgressBar.setVisibility(View.VISIBLE);
			}
			
		   @Override
			public void onLoading(long total, long current, boolean isUploading) {
				// TODO Auto-generated method stub
				
			   if(isUploading){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					mProgressBar.setProgress((int) (current*100/total));
				}
			}
			
			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
				// TODO Auto-generated method stub
				LogUtils.i("downloadBtnOnClick--onSuccess");
				mProgressBar.setVisibility(View.GONE);
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
	}

}
