package com.cndll.shapetest.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class StereoView extends ViewGroup {
    //可对外进行设置的参数
    private int mStartScreen = 1;//开始时的item位置
    private float resistance = 1.8f;//滑动阻力
    private Scroller mScroller;
    private float mAngle = 90;//两个item间的夹角
    private boolean isCan3D = true;//是否开启3D效果

    private Context mContext;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private Camera mCamera;
    private Matrix mMatrix;
    private int mWidth;//容器的宽度
    private int mHeight;//容器的高度
    private static final int standerSpeed = 2000;
    private static final int flingSpeed = 800;
    private int addCount;//记录手离开屏幕后，需要新增的页面次数
    private int alreadyAdd = 0;//对滑动多页时的已经新增页面次数的记录
    private boolean isAdding = false;//fling时正在添加新页面，在绘制时不需要开启camera绘制效果，否则页面会有闪动
    private int mCurScreen = 1;//记录当前item
    private IStereoListener iStereoListener;
    private float mDownX, mDownY, mTempY;
    private boolean isSliding = false;

    private State mState = State.Normal;

    public StereoView(Context context) {
        this(context, null);
    }

    public StereoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(mContext);
    }

    /**
     * 初始化数据
     */
    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mCamera = new Camera();
        mMatrix = new Matrix();
        if (mScroller == null) {
            mScroller = new Scroller(context);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
        //滑动到设置的StartScreen位置
        scrollTo(mCurScreen * mWidth, 0);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                final int childWidth = childView.getMeasuredWidth();
                childView.layout(childLeft, 0, childLeft + childWidth,
                        childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isSliding = false;
                mDownX = x;
                mTempY = mDownY = y;
                if (!mScroller.isFinished()) {
                    //当上一次滑动没有结束时，再次点击，强制滑动在点击位置结束
                    mScroller.setFinalX(mScroller.getCurrX());
                    mScroller.abortAnimation();
                    scrollTo(getScrollX(),0);
                    isSliding = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isSliding) {
                    isSliding = isCanSliding(ev);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isSliding;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        final float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                if (isSliding) {
                    int deltaX = (int) (mDownX - x);
                    mDownX = x;
                    scrollBy(deltaX, 0);
                    if (mScroller.isFinished()) {
                        //因为要循环滚动
                        recycleMove(deltaX);
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                if (isSliding) {
                    isSliding = false;
                    mVelocityTracker.computeCurrentVelocity(1000);
                    int velocityX = (int) mVelocityTracker.getXVelocity();

                    if (velocityX > 500 && mCurScreen > 0) {
                        mState = State.ToPre;
                    } else if (velocityX < -500
                            && mCurScreen < getChildCount() - 1) {
                        mState = State.ToNext;
                    } else {
                        mState = State.Normal;
                    }
                    //根据mState进行相应的变化
                    changeByState(velocityX);
                }
                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public boolean isCanSliding(MotionEvent ev) {
        float moveX;
        float moveY;
        moveX = ev.getX();
        mTempY = moveY = ev.getY();
        if (Math.abs(moveY - mDownX) > mTouchSlop && (Math.abs(moveY - mDownY) > (Math.abs(moveX - mDownX)))) {
            return true;
        }
        return false;
    }

    private void changeByState(float yVelocity) {
        alreadyAdd = 0;//重置滑动多页时的计数
        if (getScrollY() != mHeight) {
            switch (mState) {
                case Normal:
                    toNormalAction();
                    break;
                case ToPre:
                    toPreAction(yVelocity);
                    break;
                case ToNext:
                    toNextAction(yVelocity);
                    break;
            }
            invalidate();
        }
    }

    /**
     * mState = State.Normal 时进行的动作
     */
    private void toNormalAction() {
        int startY;
        int delta;
        int duration;
        mState = State.Normal;
        addCount = 0;
        startY = getScrollX();
        delta = mWidth * mStartScreen - getScrollX();
        duration = (Math.abs(delta)) * 4;
        mScroller.startScroll(startY, 0, delta, 0, duration);
    }

    /**
     * mState = State.ToPre 时进行的动作
     *
     * @param yVelocity 竖直方向的速度
     */
    private void toPreAction(float yVelocity) {
        int startX;
        int delta;
        int duration;
        mState = State.ToPre;
        addPre();//增加新的页面
        //计算松手后滑动的item个数
        int flingSpeedCount = (yVelocity - standerSpeed) > 0 ? (int) (yVelocity - standerSpeed) : 0;
        addCount = flingSpeedCount / flingSpeed + 1;
        //mScroller开始的坐标
        startX = getScrollX() + mWidth;
        setScrollX(startX);
        //mScroller移动的距离
        delta = -(startX - mStartScreen * mWidth) - (addCount - 1) * mWidth;
        duration = (Math.abs(-getScrollX())) * 2;
        mScroller.startScroll(startX, 0, -getScrollX(), 0, duration);
        addCount--;
    }

    /**
     * mState = State.ToNext 时进行的动作
     *
     * @param yVelocity 竖直方向的速度
     */
    private void toNextAction(float yVelocity) {
        int startY;
        int delta;
        int duration;
        mState = State.ToNext;
        addNext();
        int flingSpeedCount = (Math.abs(yVelocity) - standerSpeed) > 0 ? (int) (Math.abs(yVelocity) - standerSpeed) : 0;
        addCount = flingSpeedCount / flingSpeed + 1;
        startY = getScrollX() - mWidth;
        setScrollX(startY);
        delta = mHeight * mStartScreen - startY + (addCount - 1) * mHeight;
//        LogUtil.m("多后一页startY " + startY + " yVelocity " + yVelocity + " delta " + delta + "  getScrollY() " + getScrollY() + " addCount " + addCount);
        duration = (Math.abs(getScrollX())) * 2;
        mScroller.startScroll(startY, 0, getScrollX(), 0, duration);
        addCount--;
    }


    @Override
    public void computeScroll() {
        //滑动没有结束时，进行的操作
        if (mScroller.computeScrollOffset()) {
            if (mState == State.ToPre) {
                scrollTo(mScroller.getCurrX() + mWidth * alreadyAdd, mScroller.getCurrY());
                if (getScrollX() < (mWidth + 2) && addCount > 0) {
                    isAdding = true;
                    addPre();
                    alreadyAdd++;
                    addCount--;
                }
            } else if (mState == State.ToNext) {
                scrollTo(mScroller.getCurrX() - mWidth * alreadyAdd, mScroller.getCurrY());
                if (getScrollX() > (mWidth) && addCount > 0) {
                    isAdding = true;
                    addNext();
                    addCount--;
                    alreadyAdd++;
                }
            } else {
                //mState == State.Normal状态
                scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            }
            postInvalidate();
        }
        //滑动结束时相关用于计数变量复位
        if (mScroller.isFinished()) {
            alreadyAdd = 0;
            addCount = 0;
        }
    }

    /**
     * 把第一个item移动到最后一个item位置
     */
    private void addNext() {
        mCurScreen = (mCurScreen + 1) % getChildCount();
        int childCount = getChildCount();
        View view = getChildAt(0);
        removeViewAt(0);
        addView(view, childCount - 1);
        if (iStereoListener != null) {
            iStereoListener.toNext(mCurScreen);
        }
    }

    /**
     * 把最后一个item移动到第一个item位置
     */
    private void addPre() {
        mCurScreen = ((mCurScreen - 1) + getChildCount()) % getChildCount();
        int childCount = getChildCount();
        View view = getChildAt(childCount - 1);
        removeViewAt(childCount - 1);
        addView(view, 0);
        if (iStereoListener != null) {
            iStereoListener.toPre(mCurScreen);
        }
    }

    private void recycleMove(int delta) {
        delta = delta % mWidth;
        delta = (int) (delta / resistance);
        if (Math.abs(delta) > mWidth / 4) {
            return;
        }
        scrollBy(delta, 0);
        if (getScrollX() < 5 && mStartScreen != 0) {
            addPre();
            scrollBy(mWidth, 0);
        } else if (getScrollX() > (getChildCount() - 1) * mWidth - 5) {
            addNext();
            scrollBy(-mWidth, 0);
        }

    }

    public enum State {
        Normal, ToPre, ToNext
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (!isAdding && isCan3D) {
            //当开启3D效果并且当前状态不属于 computeScroll中 addPre() 或者addNext()
            //如果不做这个判断，addPre() 或者addNext()时页面会进行闪动一下
            //我当时写的时候就被这个坑了，后来通过log判断，原来是computeScroll中的onlayout,和子Child的draw触发的顺序导致的。
            //知道原理的朋友希望可以告知下
            for (int i = 0; i < getChildCount(); i++) {
                drawScreen(canvas, i, getDrawingTime());
            }
        } else {
            isAdding = false;
            super.dispatchDraw(canvas);
        }
    }

    private void drawScreen(Canvas canvas, int i, long drawingTime) {
        final int width = getWidth();
        final int scrollWidth = i * width;
        final int scrollX = this.getScrollX();
        // 偏移量不足的时
        if (scrollWidth > scrollX + width || scrollWidth + width < scrollX) {
            return;
        }
        final View child = getChildAt(i);
        final int faceIndex = i;
        final float currentDegree = getScrollX() * (mAngle / getMeasuredWidth());
        final float faceDegree = currentDegree - faceIndex * mAngle;
        if (faceDegree > 90 || faceDegree < -90) {
            return;
        }
        final float centerX = (scrollWidth < scrollX) ? scrollWidth + width
                : scrollWidth;
        final float centerY = getHeight() / 2;
        canvas.save();
        mCamera.save();
        mCamera.rotateY(-faceDegree);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();
        mMatrix.preTranslate(-centerX, -centerY);
        mMatrix.postTranslate(centerX, centerY);
        canvas.concat(mMatrix);
        drawChild(canvas, child, drawingTime);
        canvas.restore();
    }


    /**
     * 设置第一页展示的页面
     *
     * @param startScreen (0,getChildCount-1)
     * @return
     */
    public StereoView setStartScreen(int startScreen) {
        if (startScreen <= 0 || startScreen >= (getChildCount() - 1)) {
            throw new IndexOutOfBoundsException("请输入规定范围内startScreen位置号");

        }
        this.mStartScreen = startScreen;
        this.mCurScreen = startScreen;
        return this;
    }

    /**
     * 设置移动阻力
     *
     * @param resistance (0,...)
     * @return
     */
    public StereoView setResistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    /**
     * 设置滚动时interpolator插补器
     *
     * @param mInterpolator
     * @return
     */
    public StereoView setInterpolator(Interpolator mInterpolator) {
        mScroller = new Scroller(mContext, mInterpolator);
        return this;
    }

    /**
     * 设置滚动时两个item的夹角度数
     *
     * @param mAngle [0f,180f]
     * @return
     */
    public StereoView setAngle(float mAngle) {
        this.mAngle = 180f - mAngle;
        return this;
    }

    /**
     * 是否开启3D效果
     *
     * @param can3D
     * @return
     */
    public StereoView setCan3D(boolean can3D) {
        isCan3D = can3D;
        return this;
    }

    /**
     * 跳转到指定的item
     *
     * @param itemId [0,getChildCount-1]
     * @return
     */
    public StereoView setItem(int itemId) {

//        LogUtil.m("之前curScreen " + mCurScreen);
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
//            LogUtil.m("强制完成");
        }
        if (itemId < 0 || itemId > (getChildCount() - 1)) {
            throw new IndexOutOfBoundsException("请输入规定范围内item位置号");

        }
        if (itemId > mCurScreen) {
            //setScrollY(mStartScreen * mHeight);
            toNextAction(-standerSpeed - flingSpeed * (itemId - mCurScreen - 1));
        } else if (itemId < mCurScreen) {
            //setScrollY(mStartScreen * mHeight);
            toPreAction(standerSpeed + (mCurScreen - itemId - 1) * flingSpeed);
        }
//        LogUtil.m("之后curScreen " + mCurScreen + " getScrollY " + getScrollY());
        return this;
    }

    /**
     * 上一页
     *
     * @return
     */
    public StereoView toPre() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
//            LogUtil.m("强制完成");
        }
        toPreAction(standerSpeed);
        return this;
    }

    /**
     * 下一页
     *
     * @return
     */
    public StereoView toNext() {
        if (!mScroller.isFinished()) {
            mScroller.abortAnimation();
//            LogUtil.m("强制完成");
        }
        toNextAction(-standerSpeed);
        return this;
    }


    public interface IStereoListener {
        //上滑一页时回调
        void toPre(int curScreen);

        //下滑一页时回调
        void toNext(int curScreen);
    }

    public void setiStereoListener(IStereoListener iStereoListener) {
        this.iStereoListener = iStereoListener;
    }
}
