package ir.aligorji.persiancalendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import ir.aligorji.persiancalendar.core.PersianCalendarHandler;
import ir.aligorji.persiancalendar.core.fragments.CalendarFragment;
import ir.aligorji.persiancalendar.core.interfaces.OnDayClickedListener;
import ir.aligorji.persiancalendar.core.interfaces.OnDayLongClickedListener;
import ir.aligorji.persiancalendar.core.interfaces.OnMonthChangedListener;
import ir.aligorji.persiancalendar.core.models.PersianDate;

/**
 * Created by MADNESS on 3/23/2017.
 */

public class PersianCalendarView extends FrameLayout {
    private PersianCalendarHandler mCalendarHandler;
    CalendarFragment mCalendarFragment = null;

    public PersianCalendarView(Context context) {
        super(context);
        makeView(context,null);
    }

    public PersianCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        makeView(context,attrs);
    }

    public PersianCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        makeView(context,attrs);
    }

    private void makeView(Context context, AttributeSet attrs){
        mCalendarHandler = PersianCalendarHandler.getInstance(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_calendar, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.PersianCalendarView, 0, 0);

        mCalendarHandler.setDaysFontSize(typedArray.getDimensionPixelSize(
                R.styleable.PersianCalendarView_pcv_fontSize,
                25));

        mCalendarHandler.setHeadersFontSize(typedArray.getDimensionPixelSize(
                R.styleable.PersianCalendarView_pcv_headersFontSize,
                20));

        mCalendarHandler.setEventBackground(typedArray.getResourceId(
                R.styleable.PersianCalendarView_pcv_backgroundEvent,
                mCalendarHandler.getEventBackground()));

        mCalendarHandler.setTodayBackground(typedArray.getResourceId(
                R.styleable.PersianCalendarView_pcv_backgroundToday,
                mCalendarHandler.getTodayBackground()));

        mCalendarHandler.setSelectedDayBackground(typedArray.getResourceId(
                R.styleable.PersianCalendarView_pcv_backgroundSelectedDay,
                mCalendarHandler.getSelectedDayBackground()));

        mCalendarHandler.setColorDayName(typedArray.getColor(
                R.styleable.PersianCalendarView_pcv_colorDayName,
                mCalendarHandler.getColorDayName()));

        mCalendarHandler.setColorBackground(typedArray.getColor(
                R.styleable.PersianCalendarView_pcv_colorBackground,
                mCalendarHandler.getColorBackground()));

        mCalendarHandler.setColorHolidaySelected(typedArray.getColor(
                R.styleable.PersianCalendarView_pcv_colorHolidaySelected,
                mCalendarHandler.getColorHolidaySelected()));

        mCalendarHandler.setColorHoliday(typedArray.getColor(
                R.styleable.PersianCalendarView_pcv_colorHoliday,
                mCalendarHandler.getColorHoliday()));

        mCalendarHandler.setColorNormalDaySelected(typedArray.getColor(
                R.styleable.PersianCalendarView_pcv_colorNormalDaySelected,
                mCalendarHandler.getColorNormalDaySelected()));

        mCalendarHandler.setColorNormalDay(typedArray.getColor(
                R.styleable.PersianCalendarView_pcv_colorNormalDay,
                mCalendarHandler.getColorNormalDay()));

        mCalendarHandler.setIncludeOfficialEvents(typedArray.getBoolean(
                R.styleable.PersianCalendarView_pcv_includeOfficialEvents,
                mCalendarHandler.includeOfficialEvents()));

        mCalendarHandler.setSelectableItem(typedArray.getBoolean(
                R.styleable.PersianCalendarView_pcv_selectableItem,
                mCalendarHandler.isSelectableItem()));

        try {
            mCalendarFragment = CalendarFragment.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        setBackgroundColor(mCalendarHandler.getColorBackground());
        FragmentManager m = ((AppCompatActivity)getContext()).getSupportFragmentManager();
        m.beginTransaction()
                .replace(R.id.fragment_holder,
                        mCalendarFragment,
                        CalendarFragment.class.getName())
                .commit();

        typedArray.recycle();
    }

    public void update(){
        this.invalidate();
        setBackgroundColor(mCalendarHandler.getColorBackground());
        if(mCalendarHandler.getOnEventUpdateListener() != null)
            mCalendarHandler.getOnEventUpdateListener().update();
    }

    public void goToDate(PersianDate date){
        mCalendarFragment.bringDate(date);
    }

    public void goToToday(){
        mCalendarFragment.bringDate(mCalendarHandler.getToday());
    }
    public void goToNextMonth(){
        goToMonthFromNow(1);
    }

    public void goToPreviousMonth(){
        goToMonthFromNow(-1);
    }

    public void goToMonthFromNow(int offset){
        mCalendarFragment.changeMonth(-offset);
    }

    public PersianCalendarHandler getCalendar() {
        return mCalendarHandler;
    }

    public void setOnDayClickedListener(OnDayClickedListener listener){
        mCalendarHandler.setOnDayClickedListener(listener);
    }

    public void setOnDayLongClickedListener(OnDayLongClickedListener listener){
        mCalendarHandler.setOnDayLongClickedListener(listener);
    }

    public void setOnMonthChangedListener(OnMonthChangedListener listener){
        mCalendarHandler.setOnMonthChangedListener(listener);
    }
}
