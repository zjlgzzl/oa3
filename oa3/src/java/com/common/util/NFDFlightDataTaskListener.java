/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.NFDFlightDataTaskListener
 *  com.common.util.TimerManager
 *  com.oa.busin.service.BusinService
 *  javax.servlet.ServletContext
 *  javax.servlet.ServletContextEvent
 *  javax.servlet.ServletContextListener
 *  org.springframework.web.context.WebApplicationContext
 *  org.springframework.web.context.support.WebApplicationContextUtils
 */
package com.common.util;

import com.common.util.TimerManager;
import com.oa.busin.service.BusinService;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NFDFlightDataTaskListener
implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext((ServletContext)sce.getServletContext());
        BusinService businService = (BusinService)ctx.getBean(BusinService.class);
        new TimerManager(businService);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}

