package com.cblue.xutils.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import com.lidroid.xutils.sample.R;

@ContentView(R.layout.myviewutils)
public class ViewUtilsActivity extends Activity {

	@ViewInject(R.id.viewutils_btn)
	private Button viewUtilsButton;

	@ViewInject(R.id.viewutils_tv)
	private TextView viewUtilsTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
	}

	@OnClick(R.id.viewutils_btn)
	public void viewUtilsButtonOnClick(View view) {

		viewUtilsTextView.setText("aaa");
	}

}
