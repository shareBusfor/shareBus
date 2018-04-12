package mulin.sharebus.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import mulin.sharebus.R;
import mulin.sharebus.util.LogUtils;

/**
 * Created by mulin on 2018/4/10.
 */

public class VerificationSmsCodeView extends LinearLayout implements TextWatcher, View.OnKeyListener {


    private final static String TYPE_NUMBER = "number";
    private final static String TYPE_TEXT = "text";
    private final static String TYPE_PASSWORD = "password";
    private final static String TYPE_PHONE = "phone";

    private final static String TAG = "VerificationSmsCodeView";

    private int box = 6;
    private int boxWidth = 60;
    private int boxHeight = 60;
    private int childHpadding = 14;
    private int childVpadding = 14;
    private String inputType = TYPE_PASSWORD;
    private Drawable boxBgFocus = null;
    private Drawable boxBgNormal = null;
    private Listener listener;
    private boolean focus = false;
    private List<EditText> mEditText = new ArrayList<>();
    private int currentPosition = 0;

    public VerificationSmsCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VerificationSmsCodeView);
        box = a.getInt(R.styleable.VerificationSmsCodeView_box,6);
        childHpadding = (int) a.getDimension(R.styleable.VerificationSmsCodeView_child_h_padding,0);
        childVpadding = (int) a.getDimension(R.styleable.VerificationSmsCodeView_child_v_padding,0);
        boxBgFocus = a.getDrawable(R.styleable.VerificationSmsCodeView_box_bg_focus);
        boxBgNormal = a.getDrawable(R.styleable.VerificationSmsCodeView_box_bg_normal);
        inputType = a.getString(R.styleable.VerificationSmsCodeView_inputType);
        boxWidth = (int) a.getDimension(R.styleable.VerificationSmsCodeView_child_width,boxWidth);
        boxHeight = (int) a.getDimension(R.styleable.VerificationSmsCodeView_child_height,boxHeight);
        initViews();
    }

    private void initViews(){
        for (int i = 0 ; i < box ; i++){

            final EditText editText = new EditText(getContext());
            LayoutParams layoutParams = new LayoutParams(boxWidth,boxHeight);
            layoutParams.bottomMargin = childVpadding;
            layoutParams.topMargin = childVpadding;
            layoutParams.leftMargin = childHpadding;
            layoutParams.rightMargin = childHpadding;
            layoutParams.gravity = Gravity.CENTER;

            editText.setOnKeyListener(this);

            editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    setBg();
                    setBg(editText,true);
                }
            });

            if ( i == 0){
                editText.requestFocus();
                setBg(editText,true);
            }else{
                setBg(editText,false);
            }
            editText.setTextColor(Color.BLACK);
            editText.setLayoutParams(layoutParams);
            editText.setGravity(Gravity.CENTER);
            editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
            editText.setFilters(new InputFilter[]{
                    new InputFilter.LengthFilter(1)
            });
            editText.setId(i);
            editText.setEms(1);
            editText.addTextChangedListener(this);

            addView(editText,i);
            mEditText.add(editText);
        }
    }

    private void backFocus(){
        int count = getChildCount();
        EditText editText;
        for (int i = count - 1; i >= 0 ; i--){
            editText = (EditText) getChildAt(i);
            if ( editText.getText().length() == 1){
                editText.requestFocus();
                setBg(mEditText.get(i),true);
                editText.setSelection(1);
                return;
            }
        }
    }

    private void focus(){
        int count = getChildCount();
        EditText editText;
        for (int i = 0; i < count ; i++){
            editText = (EditText) getChildAt(i);
            if ( editText.getText().length() < 1){
                editText.requestFocus();
                return;
            }
        }
    }

    private void setBg(EditText editText, boolean focus){
        if (boxBgNormal != null && !focus){
            editText.setBackground(boxBgNormal);
        }else if (boxBgFocus != null && focus){
            editText.setBackground(boxBgFocus);
        }
    }

    private void setBg(){
        int count = getChildCount();
        EditText editText;
        for ( int i = 0; i < count ; i++){
            editText = (EditText) getChildAt(i);
            if ( boxBgNormal != null && !focus){
                editText.setBackground(boxBgNormal);
            }else if (boxBgFocus != null && focus){
                editText.setBackground(boxBgFocus);
            }
        }
    }

    private void checkAndCommit(){
        StringBuilder stringBuilder = new StringBuilder();
        boolean full = true;
        for ( int i = 0 ; i < box ; i++){
            EditText editText = (EditText) getChildAt(i);
            String content = editText.getText().toString();
            if ( content.length() == 0){
                full = false;
                break;
            }else{
                stringBuilder.append(content);
            }
        }
        LogUtils.d(TAG,"checkCommit:"+stringBuilder.toString());
        if(full){

            if(listener != null){
                listener.onComplete(stringBuilder.toString());
                setEnabled(false);
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled){
        int childCount = getChildCount();

        for ( int i = 0 ; i < childCount ; i++){
            View child = getChildAt(i);
            child.setEnabled(enabled);
        }
    }

    public void setOnCompleteListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.d(TAG,"onMeasure");
        int count = getChildCount();
        for ( int i = 0 ; i < count ; i++){
            View child = getChildAt(i);
            this.measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
        if ( count > 0){
            View child = getChildAt(0);
            int cHeight = child.getMeasuredHeight();
            int cWidth = child.getMeasuredWidth();
            int maxH = cHeight + 2*childVpadding;
            int maxW = (cWidth + childHpadding) * box + childHpadding;
            setMeasuredDimension(resolveSize(maxW,widthMeasureSpec),resolveSize(maxH,heightMeasureSpec));

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        LogUtils.d(TAG,"onLayout");
        int childCount = getChildCount();
        for ( int i = 0 ; i < childCount; i ++){
            View child = getChildAt(i);
            child.setVisibility(View.VISIBLE);
            int cWidth = child.getMeasuredWidth();
            int cHeight = child.getMeasuredHeight();
            int cl = (i) * (cWidth+childHpadding);
            int cr = cl + cWidth;
            int ct = childVpadding;
            int cb = ct + cHeight;
            child.layout(cl,ct,cr,cb);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        if (start == 0 && count >= 1 && currentPosition != mEditText.size() - 1){
            currentPosition++;
            mEditText.get(currentPosition).requestFocus();
            setBg(mEditText.get(currentPosition),true);
            setBg(mEditText.get(currentPosition-1),false);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() == 0){

        }else{
            focus();
            checkAndCommit();
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

        EditText editText = (EditText) view;
        if (keyCode == KeyEvent.KEYCODE_DEL && editText.getText().length() == 0){
            int action = keyEvent.getAction();
            if (currentPosition != 0 && action == KeyEvent.ACTION_DOWN){
                currentPosition--;
                mEditText.get(currentPosition).requestFocus();
                setBg(mEditText.get(currentPosition),true);
                setBg(mEditText.get(currentPosition+1),false);
                mEditText.get(currentPosition).setText("");
            }
        }
        return false;
    }

    public interface Listener{
        void onComplete(String content);
    }
}
