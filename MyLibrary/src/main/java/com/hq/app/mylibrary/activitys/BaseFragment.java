package com.hq.app.mylibrary.activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.hq.app.mylibrary.R;
import com.hq.app.mylibrary.utils.ToastUtil;
import com.hq.app.mylibrary.views.CircularAnim;
import com.hq.app.mylibrary.views.MyAnim;

public class BaseFragment extends Fragment {

	private final String TAG = "BaseFragment";
	protected Activity activity;
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		super.onAttach(activity);
	}

	protected View myView;
	protected MyAnim myAnim;

	private receiver rec;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	//公用方法初始化
	private void init() {
		myAnim = new MyAnim();

		rec = new receiver();
		IntentFilter filter = new IntentFilter("NotifyAction");
		activity.registerReceiver(rec, filter);
	}

	//消息提示
	protected void showMessage(String msg) {
		ToastUtil.showShort(activity, msg);
	}

	/**
	 * 跳转活动页动画
	 */
	protected void baseStartIntent(View view, final Intent intent) {
		CircularAnim.fullActivity(activity, view)
				.colorOrImageRes(R.mipmap.person)
				.go(new CircularAnim.OnAnimationEndListener() {
					@Override
					public void onAnimationEnd() {
						startActivity(intent);
					}
				});
	}

	class receiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent intent) {
			if (intent.getAction().equals("NotifyAction")) {
				String packageName = intent.getExtras().getString("packageName");
				if (installPackageListener != null) {
					installPackageListener.onPackageResult(packageName);
				}
			}
		}
	}
	private InstallPackageListener installPackageListener;
	public interface InstallPackageListener {
		void onPackageResult(String packageName);
	}

	protected void setInstallPackageListener(InstallPackageListener installPackageListener) {
		this.installPackageListener = installPackageListener;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		activity.unregisterReceiver(rec);
		if (myView != null) {
			ViewGroup vg = (ViewGroup) myView.getParent();
			if (vg != null) {
				vg.removeView(myView);
			}
		}
	}
}
