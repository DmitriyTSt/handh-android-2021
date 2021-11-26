package ru.dmitriyt.lesson11

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.OvershootInterpolator
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import kotlin.math.abs
import kotlin.math.max


internal class MyCustomView : View {

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private var showText = false
    private var textPos = 0
    private var text = ""
    private var diameter = 0

    private var animation: ValueAnimator? = null
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        val spSize = 16
        val scaledSizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spSize.toFloat(), resources.displayMetrics
        )
        textSize = scaledSizeInPixels
    }
    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
    }
    private val textBounds = Rect()

    //PHYSICAL BASED ANIMATIONS
    private val gestureListener: GestureDetector.OnGestureListener = object : SimpleOnGestureListener() {

        //Constants
        private val MIN_DISTANCE_MOVED = 50
        private val MIN_TRANSLATION = 0f
        private val FRICTION = 1.1f

        override fun onDown(arg0: MotionEvent): Boolean {
            return true
        }

        override fun onFling(downEvent: MotionEvent, moveEvent: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            //downEvent : when user puts his finger down on the view
            //moveEvent : when user lifts his finger at the end of the movement
            val distanceInX = abs(moveEvent.rawX - downEvent.rawX)
            val distanceInY = abs(moveEvent.rawY - downEvent.rawY)

            //mTvFlingDistance.setText("distanceInX : " + distanceInX + "\n" + "distanceInY : " + distanceInY);
            val maxTranslationX = ((this@MyCustomView.parent as ViewGroup).width - width).toFloat()
            val maxTranslationY = ((this@MyCustomView.parent as ViewGroup).height - height).toFloat()
            if (distanceInX > MIN_DISTANCE_MOVED) {
                //Fling Right/Left
                val flingX = FlingAnimation(this@MyCustomView, DynamicAnimation.TRANSLATION_X)
                flingX.setStartVelocity(velocityX)
                    .setMinValue(MIN_TRANSLATION) // minimum translationX property
                    .setMaxValue(maxTranslationX) // maximum translationX property
                    .setFriction(FRICTION)
                    .start()
            } else if (distanceInY > MIN_DISTANCE_MOVED) {
                //Fling Down/Up
                val flingY = FlingAnimation(this@MyCustomView, DynamicAnimation.TRANSLATION_Y)
                flingY.setStartVelocity(velocityY)
                    .setMinValue(MIN_TRANSLATION) // minimum translationY property
                    .setMaxValue(maxTranslationY) // maximum translationY property
                    .setFriction(FRICTION)
                    .start()
            }
            return true
        }
    }
    private val gestureDetector = GestureDetector(context, gestureListener)

    private fun init(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MyCustomView,
            0, 0
        )
        try {
            showText = a.getBoolean(R.styleable.MyCustomView_showText, false)
            textPos = a.getInteger(R.styleable.MyCustomView_labelPosition, 0)
            text = a.getString(R.styleable.MyCustomView_text).orEmpty()
        } finally {
            a.recycle()
        }
    }


    fun isShowText(): Boolean {
        return showText
    }

    fun setShowText(showText: Boolean) {
        this.showText = showText
        //перерисовка
        invalidate()
        //показать диаграмму https://stackoverflow.com/questions/13856180/usage-of-forcelayout-requestlayout-and-invalidate
        //requestLayout();
    }

    fun setText(text: String?) {
        this.text = text.orEmpty()
        //перерисовка
        invalidate()
        //показать диаграмму https://stackoverflow.com/questions/13856180/usage-of-forcelayout-requestlayout-and-invalidate
        // requestLayout();
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        Log.d("TAG", "onSizeChanged w = $w, h = $h")
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("TAG onMeasure w", MeasureSpec.toString(widthMeasureSpec))
        Log.d("TAG onMeasure h", MeasureSpec.toString(heightMeasureSpec))
        textPaint.getTextBounds(text, 0, text.length, textBounds)
        val minw: Int = paddingLeft + paddingRight + textBounds.width()
        val w: Int = resolveSizeAndState(minw, widthMeasureSpec, 0)
        val minh: Int = paddingBottom + paddingTop + textBounds.height()
        val h: Int = resolveSizeAndState(minh, heightMeasureSpec, 0)
        Log.d("TAG", "w= $w, h= $h")
        val side = max(w, h)
        setMeasuredDimension(side, side)
        // setMeasuredDimension(w, h);

        // пояснялка
//        int desiredWidth = 100;
//        int desiredHeight = 100;
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width;
//        int height;
//
//        //Measure Width
//        if (widthMode == MeasureSpec.EXACTLY) {
//            //Must be this size
//            width = widthSize;
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            //Can't be bigger than...
//            width = Math.min(desiredWidth, widthSize);
//        } else {
//            //Be whatever you want
//            width = desiredWidth;
//        }
//
//        //Measure Height
//        if (heightMode == MeasureSpec.EXACTLY) {
//            //Must be this size
//            height = heightSize;
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            //Can't be bigger than...
//            height = Math.min(desiredHeight, heightSize);
//        } else {
//            //Be whatever you want
//            height = desiredHeight;
//        }
//
//        //MUST CALL THIS
//        setMeasuredDimension(width, height);
    }

    override fun onDraw(canvas: Canvas) {
        Log.d("TAG", "onDraw")
        super.onDraw(canvas)
        canvas.drawCircle(width / 2.0f, height / 2.0f, diameter / 2.0f, circlePaint)
        val fm: Paint.FontMetrics = textPaint.fontMetrics
        canvas.drawText(text, 0f, textBounds.height() - fm.descent, textPaint)

        //https://stackoverflow.com/questions/3654321/measuring-text-height-to-be-drawn-on-canvas-android
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {

//        // TODO показать документацию, рассказать про типы кликов
        val touch = false
        return if (touch) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    toggleMyAnimation()
                    true
                }
                else -> false
            }
        } else {
            //TODO про физические анимации, как либа
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun toggleMyAnimation() {
        var enabled = false
        if (enabled) {
            //ValueAnimator
            if (animation != null) {
                animation?.cancel()
                animation = null
            } else {
                animation = ValueAnimator.ofInt(0, width).apply {
                    duration = 1000
                    repeatMode = ValueAnimator.REVERSE
                    repeatCount = ValueAnimator.INFINITE
                    addUpdateListener { animation ->
                        diameter = animation.animatedValue as Int
                        invalidate()
                    }
                    start()
                }
            }
            return
        }
        enabled = false
        if (enabled) {
            //OBJECT ANIMATOR, рссказать про геттеры и сеттеры, интерполятор, слушатель
            val transitionAnimator = ObjectAnimator.ofFloat(this, "translationX", 0f, 300f)
            transitionAnimator.duration = 1000
            transitionAnimator.interpolator = DecelerateInterpolator()
            transitionAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationCancel(animation: Animator) {
                    super.onAnimationCancel(animation)
                }

                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                }

                override fun onAnimationRepeat(animation: Animator) {
                    super.onAnimationRepeat(animation)
                }

                override fun onAnimationStart(animation: Animator) {
                    super.onAnimationStart(animation)
                }

                override fun onAnimationPause(animation: Animator) {
                    super.onAnimationPause(animation)
                }

                override fun onAnimationResume(animation: Animator) {
                    super.onAnimationResume(animation)
                }
            })
            transitionAnimator.start()
            return
        }
        enabled = false
        if (enabled) {
            // ANIMATION SET
            val fadeOut = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f)
            fadeOut.duration = 1000
            val fadeIn = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
            fadeIn.duration = 1000
            val mAnimationSet = AnimatorSet()
            mAnimationSet.play(fadeIn).after(fadeOut)
            mAnimationSet.start()
        }
    }
}