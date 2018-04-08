package mulin.sharebus.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ProDemoContext {

	private static ProDemoContext self;
	private SharedPreferences mPreferences;

	public Context mContext;

	public final String USERNAME = "USERNAME";//id
	public final String APPNAME = "ShareBus";
	public final String ISFIRSTSTART = "ISFIRST";
	public final String USERID = "USERID";


	public static ProDemoContext getInstance() {

		if (self == null) {
			self = new ProDemoContext();
		}

		return self;
	}

	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern.compile("^[1]+[1-9]+\\d{9}");
		Matcher m = p.matcher(mobiles);
		System.out.println(m.matches() + "---");
		return m.matches();

	}

	/**
	 *
	 * @param c
	 * @return
	 */
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param str
	 * @return
	 */
	public String chinaToUnicode(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int chr1 = str.charAt(i);
			if (chr1 >= 19968 && chr1 <= 171941) {
				result += "\\u" + Integer.toHexString(chr1);
			} else {
				result += str.charAt(i);
			}
		}
		return result;
	}



	public ProDemoContext() {
	}



	public String getUsername() {
		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		return mPreferences.getString(USERNAME, null);
	}

	public void setISFIRSTSTART(boolean isfirststart) {

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putBoolean(ISFIRSTSTART, isfirststart);
		editor.commit();
	}

	public Boolean getISFIRSTSTART() {
		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		return mPreferences.getBoolean(ISFIRSTSTART,true);
	}

	public void setUsername(String username) {

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putString(USERNAME, username);
		editor.commit();
	}

	public void setLocationAddress(String address){

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putString("locationAddress", address);
		editor.commit();

	}

	public String getLocationAdddress(){

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		return mPreferences.getString("locationAddress", null);
	}

	public void setLontitude(Double lontitude){

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putString("lontitude", lontitude+"");
		editor.commit();
	}

	public Double getLontitude(){

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		String longti = mPreferences.getString("lontitude", null);
		return Double.parseDouble(longti);
	}


	public void setLatitude(Double latitude){

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putString("latitude", latitude+"");
		editor.commit();
	}

	public Double getLatitude(){

		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		String lati = mPreferences.getString("latitude", null);
		return Double.parseDouble(lati);
	}

	public String getUserID() {
		if (mPreferences == null)
			mPreferences = mContext.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);

		return mPreferences.getString("ID", null);
	}



	public void init(Context context) {

		mContext = context;
		mPreferences = PreferenceManager.getDefaultSharedPreferences(context);

	}



	public ProDemoContext(Context context) {
		self = this;
	}

	/**
	 *
	 * @param context
	 *            Context
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}


	public SharedPreferences getSharedPreferences() {
		return mPreferences;
	}

	public void setSharedPreferences(SharedPreferences sharedPreferences) {
		this.mPreferences = sharedPreferences;
	}



	public String convert(String time) {
		long mill = Long.parseLong(time) * 1000;
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}






	public static Bitmap decodeSampledBitmapFromResource(String res, int resId, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(res, options);
		int init = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inSampleSize = init < 2 ? 2 : init;
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(res, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	
}
