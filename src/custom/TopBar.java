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

	//������attrs.xml�е��Զ������Զ�Ӧ������
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
	 * ��������Ҫ��ʾ�Ĳ���
	 * һ����ʾ�����TextView �����־�����ı���
	 */
	private Button mLeftButton;
	private Button mRightButton;
	private TextView mTitleView;
	
	//���ڿ��ƿؼ��Ĳ�������
	private LayoutParams mLeftParams;
	private LayoutParams mRightParams;
	private LayoutParams mTitleParams;
	
	//���Ǹ�button����һ��id���������¼�����ʱ���Ӧ����Ӧbutton
	private final int mLeftButtonId = 1;
	private final int mRightButtonId = 2;
	
	//����������������ж����һ�������ӿ�
	private OnTopBarClickListener onTopBarClickListener;
	
	//���캯����attrs���ǲ����ļ��������Ķ�Ӧ������
	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context,attrs);
		initView(context);
	}
	
	//��attrs.xml�ж��������ֵ����typedArray��
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
	
	//��ʼ�������еĿؼ����������Է��������,�Լ�������ע������¼�
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
	 * @param id  id=0��ʶleftButton  else ��ʶ��rightButton
	 * @param flag flag = 0 ��ζ�Ų���ʾ��id�ؼ�
	 * buttonĬ��Ϊ��ʾ
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
	
	//��д��ڵ�onclick������ȥ�����Զ���Ľӿڵķ���
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
	
	//Ϊ�ⲿ����һ������ȥ��ӽӿڵļ����¼�
	public void setOnTopBarClickListener(OnTopBarClickListener onTopBarClickListener){
		this.onTopBarClickListener = onTopBarClickListener;
	}
	//�Զ���һ���ӿڣ���ʹ����Ϊ����button����¼�
	public interface OnTopBarClickListener{
		void leftClick();
		void rightClick();
	}
}

