package ir.aligorji.persiancalendar.core.models;

import android.support.annotation.DrawableRes;

/**
 * CalendarEvent
 *
 * @author ebraminio
 */
public class CalendarEvent {
    private PersianDate mDate;
    private String mTitle;
    private boolean mHoliday;
    @DrawableRes
    private final int mEventBackground;

    public CalendarEvent(PersianDate date, String title, boolean holiday,@DrawableRes int background) {
        this.mDate = date;
        this.mTitle = title;
        this.mHoliday = holiday;
        this.mEventBackground = background;
    }

    public PersianDate getDate() {
        return mDate;
    }

    public void setDate(PersianDate date) {
        this.mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public boolean isHoliday() {
        return mHoliday;
    }

    public void setHoliday(boolean holiday) {
        this.mHoliday = holiday;
    }
    public int getEventBackground()
    {
        return mEventBackground;
    }
}