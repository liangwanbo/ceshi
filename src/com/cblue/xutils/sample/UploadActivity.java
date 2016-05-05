package com.cblue.xutils.sample;

import java.io.File;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import com.lidroid.xutils.sample.R;

@ContentView(R.layout.myupload)
public class UploadActivity extends Activity {

	@ViewInject(R.id.upload_pic_btn)
	private Button upload_pic_btn;
	private HttpUtils httpUtils;
	private static final String TAG = UploadActivity.class.getSimpleName();
	private static final String FILE_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator
			+ "a.xml";
	private static final String UPLOAD_URL = "http://192.172.10.210:8080/Android/servlet/UploadServlet";
    private ProgressDialog mProgressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		httpUtils = new HttpUtils();
		mProgressDialog = new ProgressDialog(UploadActivity.this);
	}

	@OnClick(R.id.upload_pic_btn)
	public void uploadPic(View view) {
		Toast.makeText(UploadActivity.this, FILE_PATH, 1).show();
		LogUtils.i(FILE_PATH);
		// 初始化请求参数
		RequestParams params = new RequestParams();
		File file = new File(FILE_PATH);
		params.addHeader("name", "value");
		// 在请求参数中加入参数实体
		params.addBodyParameter("file", file);
		httpUtils.send(HttpMethod.POST, UPLOAD_URL, params,
				new RequestCallBack<String>() {
					@Override
					public void onStart() {
						// TODO Auto-generated method 
						mProgressDialog.setTitle("正在下载。。。。");
						mProgressDialog.show();
						
						
					}
                    @Override
                    public void onLoading(long total, long current,
                    		boolean isUploading) {
                    	// TODO Auto-generated method stub
                    	LogUtils.i("isUploading="+isUploading+"total="+total+"current="+current+"percent="+(int) (current*100/total));
                    	if(isUploading){
                    	 mProgressDialog.setProgress((int) (current*100/total));
                    	}
                    	
                    }
					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						    LogUtils.i(responseInfo.result);
						    mProgressDialog.dismiss();
						    
					}

					@Override
					public void onFailure(HttpException error, String msg) {

					}
				});

	}

}
