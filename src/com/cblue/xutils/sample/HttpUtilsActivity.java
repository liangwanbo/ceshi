package com.cblue.xutils.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import com.lidroid.xutils.sample.R;

@ContentView(R.layout.myhttputils)
public class HttpUtilsActivity extends Activity {

	
	@ViewInject(R.id.httputils_btn)
	private Button httpUtils_Button;
	
	@ViewInject(R.id.httputils_tv)
	private TextView httpUtils_TextView;
	
	
	private HttpUtils httpUtils;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		httpUtils = new HttpUtils();
		
	}
	
	
	@OnClick(R.id.httputils_btn)
	public void httpUtilsButtonOnClick(View view){
		 send();
		
	}
	
	/**
	 * 访问网络
	 */
	public void send(){
		httpUtils.configTimeout(4000);
		httpUtils.send(HttpMethod.GET, "http://www.baidu.com", new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				// TODO Auto-generated method stub
				 httpUtils_TextView.setText(responseInfo.result);
				
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
