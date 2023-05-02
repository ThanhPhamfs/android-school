package vn.edu.tdc.customizeattributes.customViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.newdatetimetext.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


@SuppressLint("AppCompatCustomView")
public class DateView extends TextView {
    private String delimiter;
    private boolean fancyText;

    public DateView(Context context) {
        super(context);
        setDate();
    }

    public DateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateView);
        final int n = a.getIndexCount();
        for (int i = 0; i < n; ++i) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.DateView_delimiter:
                    delimiter = a.getString(attr);
                    setDate();
                    break;

                case R.styleable.DateView_fancyText:
                    fancyText = a.getBoolean(attr, false);
                    fancyText();
                    break;
            }
        }
        a.recycle();
    }

    public DateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDate();
    }


    // Private functions
    private void setDate() {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");
        String today = dateFormat.format(Calendar.getInstance().getTime());
        setText(today);  // self = DateView = subclass of TextView
    }

    private void fancyText() {
        if (this.fancyText) {
            setTextColor(Color.WHITE);
            setShadowLayer(9, 1, 1, Color.rgb(44, 44, 40));
        }
    }
}
