package vn.edu.tdc.newsnackview.views;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Snake extends View {
	private Context context;
	private int color, foodColor;
	private final Paint paint = new Paint();
	private int bigLevel = 20;
	private int bigLevelDx = 1;
	private int longLevel = 4;
	private int lineThick = 3;
	private int foodNumber = 20;
	private int foodBig = 20;
	boolean isUpdate = false;
	float eventX, eventY;

	private ArrayList<CoorPoint> snakeCoordinates = new ArrayList<CoorPoint>();
	private ArrayList<CoorPoint> foodCoordinates = new ArrayList<CoorPoint>();
	private float firstX = 300, firstY = 300, dx, dy;// , oldX = firstX, oldY =
														// firstY;

	public void initiation() {
		for (int i = 0; i < longLevel; ++i) {
			CoorPoint point = new CoorPoint(firstX - i * 2 * bigLevel, firstY);
			snakeCoordinates.add(point);
		}

		Random rn = new Random();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int rangeWidth = size.x;
		int rangeHeight = size.y;

		// Log.d("test", rangeWidth+"");
		for (int i = 0; i < foodNumber; ++i) {
			CoorPoint point = new CoorPoint(rn.nextInt(rangeWidth), rn.nextInt(rangeHeight));
			foodCoordinates.add(point);
		}
	}

	public Snake(Context context, int color, int foodColor) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.color = color;
		this.foodColor = foodColor;
		// paint.setColor(color);
		paint.setStrokeWidth(lineThick);
		initiation();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		// Log.d("test", firstX-oldX+"");
		if (isUpdate) {
			update();
		}

		// Draw food
		paint.setColor(foodColor);
		for (int i = 0; i < foodNumber; ++i) {
			if(!foodCoordinates.get(i).isEated()){
				canvas.drawCircle(foodCoordinates.get(i).x, foodCoordinates.get(i).y, foodBig, paint);
			}
		}

		// Draw snake
		paint.setColor(color);
		for (int i = 0; i < longLevel; ++i) {
			canvas.drawCircle(snakeCoordinates.get(i).x,
					snakeCoordinates.get(i).y, bigLevel, paint);

			if (i < longLevel - 1) {
				float dx1 = snakeCoordinates.get(i).x
						- snakeCoordinates.get(i + 1).x;
				float dy1 = snakeCoordinates.get(i).y
						- snakeCoordinates.get(i + 1).y;
				float distance = (float) (Math.sqrt((dx1 * dx1) + (dy1 * dy1)));
				float step = distance / (2 * bigLevel);
				float stepDX = Math.abs(dx1 / (step + 1));
				float stepDY = Math.abs(dy1 / (step + 1));
				// Log.d("test", dx1 + " " + dy1 + " " + distance+" "+ step);
				// Log.d("test", stepDX + " " + stepDY);
				if (step > 1) {
					for (int j = 1; j < step; ++j) {
						if (dx1 >= 0 && dy1 >= 0) {
							canvas.drawCircle(snakeCoordinates.get(i).x - j
									* stepDX, snakeCoordinates.get(i).y - j
									* stepDY, bigLevel, paint);
						} else if (dx1 >= 0 && dy1 < 0) {
							canvas.drawCircle(snakeCoordinates.get(i).x - j
									* stepDX, snakeCoordinates.get(i).y + j
									* stepDY, bigLevel, paint);
						} else if (dx1 < 0 && dy1 < 0) {
							canvas.drawCircle(snakeCoordinates.get(i).x + j
									* stepDX, snakeCoordinates.get(i).y + j
									* stepDY, bigLevel, paint);
						} else {
							canvas.drawCircle(snakeCoordinates.get(i).x + j
									* stepDX, snakeCoordinates.get(i).y - j
									* stepDY, bigLevel, paint);
						}

					}
				}
			}

			if (i == longLevel - 1) {

				if ((dx >= 0) && (dy >= 0)) {
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x + bigLevel,
							snakeCoordinates.get(0).y - bigLevel, paint);
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x + bigLevel,
							snakeCoordinates.get(0).y + bigLevel, paint);
				} else if ((dx >= 0) && (dy < 0)) {
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x + bigLevel,
							snakeCoordinates.get(0).y - bigLevel, paint);
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x + bigLevel,
							snakeCoordinates.get(0).y + bigLevel, paint);
				} else if ((dx < 0) && (dy < 0)) {
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x - bigLevel,
							snakeCoordinates.get(0).y - bigLevel, paint);
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x - bigLevel,
							snakeCoordinates.get(0).y + bigLevel, paint);
				} else {
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x - bigLevel,
							snakeCoordinates.get(0).y - bigLevel, paint);
					canvas.drawLine(snakeCoordinates.get(0).x,
							snakeCoordinates.get(0).y,
							snakeCoordinates.get(0).x - bigLevel,
							snakeCoordinates.get(0).y + bigLevel, paint);
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		int action = event.getAction();
		eventX = event.getX();
		eventY = event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (!(snakeCoordinates.get(0).getX() - bigLevel < eventX
					&& eventX < snakeCoordinates.get(0).getX() + bigLevel * 2
							+ 20
					&& snakeCoordinates.get(0).getY() - 20 < eventY && eventY < snakeCoordinates
					.get(0).getY() + bigLevel * 2 + 20))
				return false;
			break;
		case MotionEvent.ACTION_MOVE:
			isUpdate = true;
			for (int i = 0; i < foodNumber; ++i) {
				float dx1 = foodCoordinates.get(i).getX() - eventX;
				float dy1 = foodCoordinates.get(i).getY() - eventY;
				float distance = (float) (Math.sqrt((dx1 * dx1) + (dy1 * dy1)));
				if (distance <= foodBig) {
					//foodCoordinates.remove(i);
					foodCoordinates.get(i).setEated(true);
					bigLevel +=bigLevelDx;
					longLevel++;
					CoorPoint point = new CoorPoint(snakeCoordinates.get(0).getX(),snakeCoordinates.get(0).getY());
					snakeCoordinates.add(0,point);
				}
			}
			break;
		}
		invalidate();
		return true;
	}

	public void update() {
		isUpdate = false;
		dx = eventX - snakeCoordinates.get(0).x;
		dy = eventY - snakeCoordinates.get(0).y;
		// Log.d("test", dx + " "+dy);

		for (int i = longLevel - 1; i > 0; --i) {
			snakeCoordinates.get(i).setX(snakeCoordinates.get(i - 1).x);
			snakeCoordinates.get(i).setY(snakeCoordinates.get(i - 1).y);
		}

		snakeCoordinates.get(0).setX(eventX);
		snakeCoordinates.get(0).setY(eventY);

		/*
		 * if((dx>=0)&&(dy>=0)){
		 * snakeCoordinates.get(0).setX(snakeCoordinates.get
		 * (1).x+bigLevel*Math.sqrt(2));
		 * snakeCoordinates.get(0).setY(snakeCoordinates
		 * .get(1).y+bigLevel*Math.sqrt(2)); } else if((dx>=0)&&(dy<0)){
		 * snakeCoordinates
		 * .get(0).setX(snakeCoordinates.get(1).x+bigLevel*(float)Math.sqrt(2));
		 * snakeCoordinates
		 * .get(0).setY(snakeCoordinates.get(1).y-bigLevel*(float)Math.sqrt(2));
		 * } else if((dx<0)&&(dy<0)){
		 * snakeCoordinates.get(0).setX(snakeCoordinates
		 * .get(1).x-bigLevel*(float)Math.sqrt(2));
		 * snakeCoordinates.get(0).setY(
		 * snakeCoordinates.get(1).y-bigLevel*(float)Math.sqrt(2)); } else {
		 * snakeCoordinates
		 * .get(0).setX(snakeCoordinates.get(1).x-bigLevel*(float)Math.sqrt(2));
		 * snakeCoordinates
		 * .get(0).setY(snakeCoordinates.get(1).y+bigLevel*(float)Math.sqrt(2));
		 * }
		 */
	}

	public int getBigLevel() {
		return bigLevel;
	}

	public void setBigLevel(int bigLevel) {
		this.bigLevel = bigLevel;
	}

	public int getLongLevel() {
		return longLevel;
	}

	public void setLongLevel(int longLevel) {
		this.longLevel = longLevel;
	}

	public int getLineThick() {
		return lineThick;
	}

	public void setLineThick(int lineThick) {
		this.lineThick = lineThick;
	}
}
