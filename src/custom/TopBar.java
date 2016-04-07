package custom;

import com.example.topbar.R;
import com.example.topbar.R.styleable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.view.View.OnClickListener;

public class TopBar extends RelativeLayout implements OnClickListener{

	//定义与attrs.xml中的自定义属性对应的属性
	private int mLeftTextColor;
	private Drawable mLeftBackground;
	private String mLeftText;
	
	private int mRightTextColor;
	private Drawable mRightBackground;
	private String mRightText;
	
	private String mTitleText;
	private int mTitleTextColor;
	private float mTitleTextSize;
	
	/*
	 * 定义我们要显示的布局
	 * 一个显示标题的TextView 两个分居两侧的标题
	 */
	private Button mLeftButton;
	private Button mRightButton;
	private TextView mTitleView;
	
	//用于控制控件的布局属性
	private LayoutParams mLeftParams;
	private LayoutParams mRightParams;
	private LayoutParams mTitleParams;
	
	//我们给button赋予一个id，便于在事件监听时候对应到相应button
	private final int mLeftButtonId = 1;
	private final int mRightButtonId = 2;
	
	//下面我们在这个类中定义的一个公共接口
	private OnTopBarClickListener onTopBarClickListener;
	
	//构造函数，attrs就是布局文件传过来的对应的属性
	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context,attrs);
		initView(context);
	}
	
	//将attrs.xml中定义的属性值存入typedArray中
	private void initAttrs(Context context, AttributeSet attrs){
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.TopBar);
		
		mLeftTextColor = ta.getColor(
				R.styleable.TopBar_leftTextColor,0);
		mLeftBackground = ta.getDrawable(
				R.styleable.TopBar_leftBackground);
		mLeftText = ta.getString(
				R.styleable.TopBar_leftText);
		
		mRightTextColor = ta.getColor(
				R.styleable.TopBar_rightTextColor,0);
		mRightBackground = ta.getDrawable(
				R.styleable.TopBar_rightBackground);
		mRightText = ta.getString(
				R.styleable.TopBar_rightText);
		
		mTitleTextSize = ta.getDimension(
				R.styleable.TopBar_titleTextSize, 10);
		mTitleTextColor =ta.getColor(
				R.styleable.TopBar_titleTextColor, 0);
		mTitleText = ta.getString(
				R.styleable.TopBar_titleText);
		
		ta.recycle();		
	}
	
	//初始化布局中的控件，并将属性分配给他们,以及给他们注册监听事件
	private void initView(Context context){
		mLeftButton = new Button(context);
		mRightButton = new Button(context);
		mTitleView = new TextView(context);
		
		mLeftButton.setTextColor(mLeftTextColor);
		mLeftButton.setBackground(mLeftBackground);
		mLeftButton.setText(mLeftText);
		
		mRightButton.setTextColor(mRightTextColor);
		mRightButton.setBackground(mRightBackground);
		mRightButton.setText(mRightText);
		
		mTitleView.setText(mTitleText);
		mTitleView.setTextColor(mTitleTextColor);
		mTitleView.setTextSize(mTitleTextSize);
		mTitleView.setGravity(Gravity.CENTER);
		
		mLeftButton.setId(mLeftButtonId);
		mLeftParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
		mLeftParams.addRule(RelativeLayout.CENTER_VERTICAL);
		addView(mLeftButton,mLeftParams);
		
		mRightButton.setId(mRightButtonId);
		mRightParams = new  LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
		mRightParams.addRule(RelativeLayout.CENTER_VERTICAL,TRUE);
		addView(mRightButton,mRightParams);
		
		mTitleParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
		addView(mTitleView,mTitleParams);
		
		mLeftButton.setOnClickListener(this);
		mRightButton.setOnClickListener(this);
	}
	
	/**
	 * 
	 * @param id  id=0标识leftButton  else 标识着rightButton
	 * @param flag flag = 0 意味着不显示该id控件
	 * button默认为显示
	 */
	public void setButtonVisable(int id, boolean flag){
		
		if(flag){
			if(id == 0){
				mLeftButton.setVisibility(View.VISIBLE);
			}else{
				mRightButton.setVisibility(View.VISIBLE);
			}
		}else{
			if(id == 0){
				mLeftButton.setVisibility(View.GONE);
			}else{
				mRightButton.setVisibility(View.GONE);
			}
		}
	}
	
	//重写借口的onclick方法，去调用自定义的接口的方法
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case mLeftButtonId:
			onTopBarClickListener.leftClick();
			break;
		case mRightButtonId:
			onTopBarClickListener.rightClick();
			break;
		}
	}
	
	//为外部增加一个方法去添加接口的监听事件
	public void setOnTopBarClickListener(OnTopBarClickListener onTopBarClickListener){
		this.onTopBarClickListener = onTopBarClickListener;
	}
	//自定义一个接口，供使用者为两个button添加事件
	public interface OnTopBarClickListener{
		void leftClick();
		void rightClick();
	}
}

