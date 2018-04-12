package mulin.sharebus.ui.view.toast;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mulin.sharebus.R;


public class WinToast {

	public static void toast(Context context, int textRes) {
		CharSequence text = context.getResources().getText(textRes);
		makeText(context, text).show();
	}

	public static void toast(Context context, CharSequence sequence) {
		makeText(context, sequence).show();
	}

	public static void toastWithCat(Context context, int textRes, boolean isHappy) {
		CharSequence text = context.getResources().getText(textRes);
		toastWithCat(context, text, isHappy);
	}

	public static void toastWithCat(Context context, CharSequence text, boolean isHappy) {
		final Toast result = new Toast(context);

		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.ui_toast, null);
		result.setView(v);
		ImageView iv = (ImageView) v.findViewById(android.R.id.icon);
		if (isHappy) {
			iv.setImageResource(R.drawable.success);
		} else {
			iv.setImageResource(R.drawable.fail);
		}
		TextView tv = (TextView) v.findViewById(android.R.id.message);
		tv.setText(text);

		result.setGravity(Gravity.CENTER, 0, 0);
		result.setDuration(Toast.LENGTH_LONG);
		result.show();
	
		Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
	       public void run() {
	    	   result.cancel(); 
	       }
	    }, 500);
	    
	}
	
	public static void toastWithCatWithTime(Context context, CharSequence text, boolean isHappy, int duration) {
		final Toast result = new Toast(context);

		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.ui_toast, null);
		result.setView(v);
		ImageView iv = (ImageView) v.findViewById(android.R.id.icon);
		if (isHappy) {
			iv.setImageResource(R.drawable.success);
		} else {
			iv.setImageResource(R.drawable.fail);
		}
		TextView tv = (TextView) v.findViewById(android.R.id.message);
		tv.setText(text);

		result.setGravity(Gravity.CENTER, 0, 0);
		result.setDuration(Toast.LENGTH_SHORT);
		result.show();
	
		Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
	       public void run() {
	    	   result.cancel(); 
	       }
	    }, duration);
	    
	}
	
	
//	public static void toastWithCat(final Context context, final String word, final long time){
//		context.runOnUiThread(new Runnable() {	
//			public void run() {
//				final Toast toast = Toast.makeText(context, word, Toast.LENGTH_LONG);
//				toast.show();
//				Handler handler = new Handler();
//			        handler.postDelayed(new Runnable() {
//			           public void run() {
//			               toast.cancel(); 
//			           }
//			    }, time);
//			}
//		});
//	}
	
	

	public static Toast makeText(Context context, CharSequence text) {
		Toast result = new Toast(context);

		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflate.inflate(R.layout.ui_toast, null);
		result.setView(v);
		TextView tv = (TextView) v.findViewById(android.R.id.message);
		tv.setText(text);

		result.setGravity(Gravity.CENTER, 0, 0);
		result.setDuration(Toast.LENGTH_SHORT);

		return result;
	}
}
