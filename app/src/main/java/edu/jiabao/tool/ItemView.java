package edu.jiabao.tool;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by 小辉 on 2017/3/21.
 * @des:
 *      此控件为LinearLayout加长型，加长的长度为原来的1/4，所以控件可分5份，
 *      控件设置为match_parent，那这边的4份为显示，剩余的一份超出屏幕为隐藏
 *      滑动效果已经监听，可按手指滑动来显示隐藏部分。
 */

public class ItemView extends LinearLayout{
    private int width;//控件的宽度
    private int height;//控件的高度
    private  boolean needShowItem=true;//删除键是否显示true没显示，需要显示
    private float x,down_X,count;//x为控件移动前的位置，count为移动的距离
    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ItemView(Context context) {
        this(context,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);//计算出来的宽高
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension((width*5/4), height);//加长宽度四分之一(可以自定义长度)
        x=getX();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        getParent().requestDisallowInterceptTouchEvent(true);
        int action = event.getAction();
        float my_width=getWidth();//这是加宽1/4后的长度
        switch (action){
            case MotionEvent.ACTION_DOWN:
                down_X=event.getX();//点击下去的坐标
                break;
            case MotionEvent.ACTION_MOVE:
                count=event.getX()-down_X;//移动的距离
                if(count>5||count<-5){
                    //横向滑动  定义为5摸索出来的
                    getParent().requestDisallowInterceptTouchEvent(true);
                    //当不显示删除的时候，只响应往左拉，也就是count<0
                    if(needShowItem&&count<0){
                        if(count<(-my_width/5)){
                            //超出控件位置，防止拉出空白
                            setX(x + (-my_width/5));
                        }else{
                            setX(x + count);
                        }
                    }
                    //当显示删除的时候，只响应往右拉，也就是count>0
                    if(!needShowItem&&count>0){
                        if(count>my_width/5){
                            //超出控件位置，防止拉出空白
                            setX(x + my_width/5);
                        }else {
                            setX(x + count);
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                if(needShowItem){
//                    删除未显示状态，拉出超过一半删除控件的位置，则显示删除
                    if(count<(-my_width/10)){
                        //往左偏移1/5的宽度，拉出删除
                        setX(x-(my_width/5));
                        x=getX();
                        //已显示状态，改变需要显示状态为false
                        needShowItem=false;
                    }else{
                        //拉出距离不够删除控件一半位置 ，回到原来的位置
                        setX(x);
                    }
                }else {
                    if(count>(my_width/10)){
                        setX(x+(my_width/5));
                        x=getX();
                        //删除键消失，改变需要显示状态为true
                        needShowItem=true;
                    }else{
                        setX(x);
                    }
                }
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
