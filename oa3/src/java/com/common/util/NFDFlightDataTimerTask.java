/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.NFDFlightDataTimerTask
 *  com.oa.busin.service.BusinService
 */
package com.common.util;

import com.oa.busin.service.BusinService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

public class NFDFlightDataTimerTask
extends TimerTask {
    private BusinService businService;
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public NFDFlightDataTimerTask(BusinService businService) {
        this.businService = businService;
    }

    @Override
    public void run() {
        try {
            this.businService.updateLocks();
            System.out.println("执行当前时间" + formatter.format(Calendar.getInstance().getTime()));
        }
        catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------" + e.getMessage());
        }
    }
}

