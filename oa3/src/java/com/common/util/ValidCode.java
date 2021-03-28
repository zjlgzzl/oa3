/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.util.ValidCode
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 *  org.apache.struts2.ServletActionContext
 */
package com.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class ValidCode {
    private Random random = new Random();
    private int width = 78;
    private int height = 25;
    private int lineSize = 5;
    private int stringNum = 4;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();

    public void createCode() {
        HttpSession session = this.request.getSession();
        BufferedImage image = new BufferedImage(this.width, this.height, 4);
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, this.width, this.height);
        g.setFont(new Font("Times New Roman", 0, 20));
        g.setColor(this.getRandColor(110, 133));
        for (int i = 0; i <= this.lineSize; ++i) {
            this.drowLine(g);
        }
        StringBuffer randomString = new StringBuffer();
        for (int i = 1; i <= this.stringNum; ++i) {
            g.setColor(new Color(this.random.nextInt(101), this.random.nextInt(111), this.random.nextInt(121)));
            String rand = String.valueOf(this.getRandomString(this.random.nextInt("123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ".length())));
            randomString.append(rand);
            g.translate(this.random.nextInt(1), this.random.nextInt(3));
            g.drawString(rand, 13 * i, 16);
        }
        session.removeAttribute("validCode");
        session.setAttribute("validCode", (Object)randomString.toString());
        g.dispose();
        try {
            this.response.setContentType("image/jpeg");
            this.response.setHeader("Pragma", "No-cache");
            this.response.setHeader("Cache-Control", "no-cache");
            this.response.setDateHeader("Expire", 0L);
            ImageIO.write((RenderedImage)image, "JPEG", (OutputStream)this.response.getOutputStream());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getSessionCode() {
        String validCode = this.request.getSession().getAttribute("validCode").toString();
        this.response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            try {
                out = this.response.getWriter();
                out.print(validCode);
            }
            catch (IOException e) {
                e.printStackTrace();
                out.flush();
                out.close();
            }
        }
        finally {
            out.flush();
            out.close();
        }
    }

    private Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + this.random.nextInt(bc - fc - 16);
        int g = fc + this.random.nextInt(bc - fc - 14);
        int b = fc + this.random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    private void drowLine(Graphics g) {
        int x = this.random.nextInt(this.width);
        int y = this.random.nextInt(this.height);
        int xl = this.random.nextInt(13);
        int yl = this.random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    public String getRandomString(int num) {
        return String.valueOf("123456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ".charAt(num));
    }
}

