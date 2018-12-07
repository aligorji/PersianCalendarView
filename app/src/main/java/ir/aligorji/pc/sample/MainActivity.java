package ir.aligorji.pc.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import ir.aligorji.persiancalendar.PersianCalendarView;
import ir.aligorji.persiancalendar.core.PersianCalendarHandler;
import ir.aligorji.persiancalendar.core.interfaces.OnMonthChangedListener;
import ir.aligorji.persiancalendar.core.models.CalendarEvent;
import ir.aligorji.persiancalendar.core.models.PersianDate;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PersianCalendarView persianCalendarView = (PersianCalendarView) findViewById(R.id.persian_calendar);
        final PersianCalendarHandler calendar = persianCalendarView.getCalendar();
        final PersianDate today = calendar.getToday();
        final TextView txtDayMonth = (TextView) findViewById(R.id.txt_day_month);
        final TextView txtYear = (TextView) findViewById(R.id.txt_year);

        /*calendar.addLocalEvent(new CalendarEvent(
                today, "Custom event", false
        ));
        calendar.addLocalEvent(new CalendarEvent(
                today.clone().rollDay(2,true), "Custom event 2", true
        ));
        calendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onChanged(PersianDate date) {
                Toast.makeText(MainActivity.this, calendar.getMonthName(date),Toast.LENGTH_SHORT).show();
            }
        });
        persianCalendarView.setOnDayClickedListener(new OnDayClickedListener() {
            @Override
            public void onClick(PersianDate date) {
                for(CalendarEvent e : calendar.getAllEventsForDay(date))
                Toast.makeText(MainActivity.this, e.getTitle(), Toast.LENGTH_LONG).show();


                calendar.addLocalEvent(new CalendarEvent(
                        today.clone().rollDay(2, false), "Some event that will be added in runtime", false
                ));
                persianCalendarView.update();
            }
        });*/

        calendar.setOnMonthChangedListener(new OnMonthChangedListener()
        {
            @Override
            public void onChanged(PersianDate date)
            {
                //Toast.makeText(MainActivity.this, calendar.getMonthName(date), Toast.LENGTH_SHORT).show();
                //String dayAndMonth = calendar.getWeekDayName(date) + calendar.formatNumber(date.getDayOfMonth())
                //        + calendar.getMonthName(date);
                txtDayMonth.setText(calendar.getMonthName(date));
                txtYear.setText(calendar.formatNumber(date.getYear()));
            }
        });


        calendar.addLocalEvent(new CalendarEvent(
                new PersianDate(1397, 9, 16), "Custom event", false, R.drawable.shape_custom_event
        ));

        calendar.addLocalEvent(new CalendarEvent(
                new PersianDate(1397, 9, 5), "Custom event", false, R.drawable.shape_custom_event
        ));


        calendar.setHighlightLocalEvents(true);
        calendar.setHighlightOfficialEvents(false);


        persianCalendarView.update();
    }
}
