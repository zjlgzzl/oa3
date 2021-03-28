/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.NFDFlightDataTimerTask
 *  com.common.util.NFDFlightDataTimerTask2
 *  com.common.util.TimerManager
 *  com.oa.busin.service.BusinService
 */
package com.common.util;

import com.common.util.NFDFlightDataTimerTask;
import com.common.util.NFDFlightDataTimerTask2;
import com.oa.busin.service.BusinService;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {
    private static final long PERIOD_DAY = 86400000L;

    public TimerManager(BusinService businService) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 1);
        calendar.set(12, 0);
        calendar.set(13, 0);
        Date date = calendar.getTime();
        System.out.println(date);
        System.out.println("before 方法比较：" + date.before(new Date()));
        if (date.before(new Date())) {
            date = this.addDay(date, 1);
            System.out.println(date);
        }
        Timer timer2 = new Timer();
        NFDFlightDataTimerTask2 task2 = new NFDFlightDataTimerTask2(businService);
        timer2.schedule((TimerTask)task2, date, 86400000L);
        if (calendar.get(5) != 10) {
            System.out.println("no arrive date");
            return;
        }
        Timer timer = new Timer();
        NFDFlightDataTimerTask task = new NFDFlightDataTimerTask(businService);
        timer.schedule((TimerTask)task, date, 86400000L);
    }

    public Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(5, num);
        return startDT.getTime();
    }
}

