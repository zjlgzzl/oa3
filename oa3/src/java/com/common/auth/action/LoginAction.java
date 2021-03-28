/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.LoginAction
 *  com.common.auth.action.UserInfo
 *  com.common.auth.service.RoleService
 *  com.common.auth.service.UserService
 *  com.common.emp.service.EmpService
 *  com.common.entity.TEmp
 *  com.common.entity.TUser
 *  com.common.entity.VUserFun
 *  com.common.util.MD5
 *  com.opensymphony.xwork2.ActionSupport
 *  javax.servlet.http.Cookie
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 *  net.sf.json.JSONArray
 *  org.apache.struts2.ServletActionContext
 */
package com.common.auth.action;

import com.common.auth.action.UserInfo;
import com.common.auth.service.RoleService;
import com.common.auth.service.UserService;
import com.common.emp.service.EmpService;
import com.common.entity.TEmp;
import com.common.entity.TUser;
import com.common.entity.VUserFun;
import com.common.util.MD5;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

public class LoginAction
extends ActionSupport {
    private String userName;
    private String passWord;
    private String validCode;
    private String oldPwd;
    private String newPwd;
    private String newPwd2;
    private UserService userService;
    private RoleService roleService;
    private EmpService empService;
    HttpServletRequest request = ServletActionContext.getRequest();
    HttpServletResponse response = ServletActionContext.getResponse();
    HttpSession session = this.request.getSession();

    public String login() {
        if (this.userName == null || this.userName.trim().length() == 0) {
            this.request.setAttribute("errInfo", (Object)"用户名不能为空！");
            return "login";
        }
        if (this.passWord == null || this.passWord.trim().length() == 0 || this.request.getSession().getAttribute("validCode") == null) {
            this.request.setAttribute("errInfo", (Object)"密码不能为空！");
            return "login";
        }
        if (this.validCode == null || this.validCode.trim().length() == 0) {
            this.request.setAttribute("errInfo", (Object)"验证码不能为空！");
            return "login";
        }
        if (!this.validCode.equalsIgnoreCase(this.request.getSession().getAttribute("validCode").toString())) {
            this.request.setAttribute("errInfo", (Object)"验证码输入错误！");
            return "login";
        }
        TUser user = this.userService.findByUsername(this.userName);
        if (user == null) {
            this.request.setAttribute("errInfo", (Object)"用户不存在");
            return "login";
        }
        if (user.getCState() == 0) {
            this.request.setAttribute("errInfo", (Object)"用户已注销");
            return "login";
        }
        if (!MD5.getStr((String)this.passWord).equals(user.getCPassword())) {
            this.request.setAttribute("errInfo", (Object)"密码输入错误");
            return "login";
        }
        this.session.setAttribute("userID", (Object)user.getCId());
        this.session.setAttribute("userName", (Object)this.userName);
        this.session.setAttribute("passWord", (Object)this.passWord);
        this.session.setAttribute("adminFlg", (Object)user.getCAdminflag());
        if (!"admin".equals(user.getCUsername())) {
            TEmp tmp = this.empService.getEntityById((Serializable)user.getCEmpid());
            this.session.setAttribute("manageFlg", (Object)tmp.getCManageflag());
            this.session.setAttribute("uppId", (Object)tmp.getTDept().getCId());
        }
        this.session.setAttribute("empId", (Object)user.getCEmpid());
        ArrayList<VUserFun> topRoleList = new ArrayList<VUserFun>();
        ArrayList<VUserFun> subRoleList = new ArrayList<VUserFun>();
        List roleList = this.roleService.getUserFunList(user.getCId().intValue());
        int fun202 = 0;
        int fun203 = 0;
        int fun204 = 0;
        int fun205 = 0;
        int fun505 = 0;
        int fun303 = 0;
        int fun305 = 0;
        int fun507 = 0;
        int fun508 = 0;
        int fun509 = 0;
        int fun510 = 0;
        int fun511 = 0;
        int fun207 = 0;
        int fun208 = 0;
        int fun209 = 0;
        int fun210 = 0;
        int fun211 = 0;
        int fun212 = 0;
        int fun213 = 0;
        int fun214 = 0;
        int fun411 = 0;
        int fun514 = 0;
        int fun515 = 0;
        int fun711 = 0;
        int fun713 = 0;
        int fun306 = 0;
        int fun216 = 0;
        int fun217 = 0;
        int fun219 = 0;
        int fun220 = 0;
        int fun221 = 0;
        int fun607 = 0;
        int fun606 = 0;
        int fun608 = 0;
        int fun618 = 0;
        int fun307 = 0;
        int fun308 = 0;
        int fun309 = 0;
        int fun310 = 0;
        int fun619 = 0;
        int fun620 = 0;
        int fun621 = 0;
        int fun516 = 0;
        int fun311 = 0;
        int fun224 = 0;
        int fun225 = 0;
        int fun623 = 0;
        int fun226 = 0;
        int fun624 = 0;
        int fun228 = 0;
        int fun229 = 0;
        int fun231 = 0;
        int fun232 = 0;
        int fun314 = 0;
        int fun315 = 0;
        int fun316 = 0;
        int fun318 = 0;
        int fun625 = 0;
        int fun627 = 0;
        int fun628 = 0;
        int fun629 = 0;
        int fun317 = 0;
        int fun319 = 0;
        int fun201 = 0;
        int fun206 = 0;
        int fun302 = 0;
        int fun240 = 0;
        int fun241 = 0;
        int fun242 = 0;
        int fun243 = 0;
        if (roleList != null && roleList.size() > 0) {
            for (int i = 0; i < roleList.size(); ++i) {
                if (((VUserFun)roleList.get(i)).getClevel() == 1) {
                    topRoleList.add((VUserFun)roleList.get(i));
                    continue;
                }
                if (((VUserFun)roleList.get(i)).getClevel() != 2) continue;
                subRoleList.add((VUserFun)roleList.get(i));
                if (((VUserFun)roleList.get(i)).getFunid() == 201) {
                    fun201 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 206) {
                    fun206 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 302) {
                    fun302 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 202) {
                    fun202 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 203) {
                    fun203 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 505) {
                    fun505 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 303) {
                    fun303 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 305) {
                    fun305 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 507) {
                    fun507 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 508) {
                    fun508 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 509) {
                    fun509 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 510) {
                    fun510 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 511) {
                    fun511 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 204) {
                    fun204 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 205) {
                    fun205 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 207) {
                    fun207 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 208) {
                    fun208 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 209) {
                    fun209 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 210) {
                    fun210 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 211) {
                    fun211 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 212) {
                    fun212 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 213) {
                    fun213 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 214) {
                    fun214 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 224) {
                    fun224 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 411) {
                    fun411 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 514) {
                    fun514 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 515) {
                    fun515 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 711) {
                    fun711 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 713) {
                    fun713 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 306) {
                    fun306 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 216) {
                    fun216 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 217) {
                    fun217 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 618) {
                    fun618 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 219) {
                    fun219 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 220) {
                    fun220 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 607) {
                    fun607 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 606) {
                    fun606 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 608) {
                    fun608 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 307) {
                    fun307 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 308) {
                    fun308 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 309) {
                    fun309 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 310) {
                    fun310 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 221) {
                    fun221 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 619) {
                    fun619 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 620) {
                    fun620 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 621) {
                    fun621 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 516) {
                    fun516 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 311) {
                    fun311 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 225) {
                    fun225 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 623) {
                    fun623 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 226) {
                    fun226 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 624) {
                    fun624 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 228) {
                    fun228 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 229) {
                    fun229 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 231) {
                    fun231 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 314) {
                    fun314 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 315) {
                    fun315 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 625) {
                    fun625 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 627) {
                    fun627 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 628) {
                    fun628 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 232) {
                    fun232 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 629) {
                    fun629 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 317) {
                    fun317 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 319) {
                    fun319 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 316) {
                    fun316 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 240) {
                	fun240 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 241) {
                	fun241 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 242) {
                	fun242 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() == 243) {
                	fun243 = 1;
                }
                if (((VUserFun)roleList.get(i)).getFunid() != 318) continue;
                fun318 = 1;
            }
        }
        this.session.setAttribute("topRoleList", topRoleList);
        this.session.setAttribute("subRoleList", subRoleList);
        this.session.setAttribute("fun202", (Object)fun202);
        this.session.setAttribute("fun203", (Object)fun203);
        this.session.setAttribute("fun204", (Object)fun204);
        this.session.setAttribute("fun205", (Object)fun205);
        this.session.setAttribute("fun505", (Object)fun505);
        this.session.setAttribute("fun303", (Object)fun303);
        this.session.setAttribute("fun305", (Object)fun305);
        this.session.setAttribute("fun507", (Object)fun507);
        this.session.setAttribute("fun508", (Object)fun508);
        this.session.setAttribute("fun509", (Object)fun509);
        this.session.setAttribute("fun510", (Object)fun510);
        this.session.setAttribute("fun511", (Object)fun511);
        this.session.setAttribute("fun207", (Object)fun207);
        this.session.setAttribute("fun208", (Object)fun208);
        this.session.setAttribute("fun209", (Object)fun209);
        this.session.setAttribute("fun210", (Object)fun210);
        this.session.setAttribute("fun211", (Object)fun211);
        this.session.setAttribute("fun212", (Object)fun212);
        this.session.setAttribute("fun213", (Object)fun213);
        this.session.setAttribute("fun214", (Object)fun214);
        this.session.setAttribute("fun411", (Object)fun411);
        this.session.setAttribute("fun514", (Object)fun514);
        this.session.setAttribute("fun515", (Object)fun515);
        this.session.setAttribute("fun711", (Object)fun711);
        this.session.setAttribute("fun306", (Object)fun306);
        this.session.setAttribute("fun713", (Object)fun713);
        this.session.setAttribute("fun216", (Object)fun216);
        this.session.setAttribute("fun217", (Object)fun217);
        this.session.setAttribute("fun618", (Object)fun618);
        this.session.setAttribute("fun219", (Object)fun219);
        this.session.setAttribute("fun220", (Object)fun220);
        this.session.setAttribute("fun607", (Object)fun607);
        this.session.setAttribute("fun608", (Object)fun608);
        this.session.setAttribute("fun307", (Object)fun307);
        this.session.setAttribute("fun309", (Object)fun309);
        this.session.setAttribute("fun310", (Object)fun310);
        this.session.setAttribute("fun221", (Object)fun221);
        this.session.setAttribute("fun619", (Object)fun619);
        this.session.setAttribute("fun620", (Object)fun620);
        this.session.setAttribute("fun621", (Object)fun621);
        this.session.setAttribute("fun516", (Object)fun516);
        this.session.setAttribute("fun311", (Object)fun311);
        this.session.setAttribute("fun224", (Object)fun224);
        this.session.setAttribute("fun225", (Object)fun225);
        this.session.setAttribute("fun623", (Object)fun623);
        this.session.setAttribute("fun226", (Object)fun226);
        this.session.setAttribute("fun624", (Object)fun624);
        this.session.setAttribute("fun228", (Object)fun228);
        this.session.setAttribute("fun229", (Object)fun229);
        this.session.setAttribute("fun231", (Object)fun231);
        this.session.setAttribute("fun606", (Object)fun606);
        this.session.setAttribute("fun314", (Object)fun314);
        this.session.setAttribute("fun315", (Object)fun315);
        this.session.setAttribute("fun625", (Object)fun625);
        this.session.setAttribute("fun627", (Object)fun627);
        this.session.setAttribute("fun628", (Object)fun628);
        this.session.setAttribute("fun232", (Object)fun232);
        this.session.setAttribute("fun629", (Object)fun629);
        this.session.setAttribute("fun317", (Object)fun317);
        this.session.setAttribute("fun319", (Object)fun319);
        this.session.setAttribute("fun316", (Object)fun316);
        this.session.setAttribute("fun318", (Object)fun318);
        this.session.setAttribute("fun201", (Object)fun201);
        this.session.setAttribute("fun206", (Object)fun206);
        this.session.setAttribute("fun302", (Object)fun302);
        this.session.setAttribute("fun308", (Object)fun308);
        this.session.setAttribute("fun240", (Object)fun240);
        this.session.setAttribute("fun241", (Object)fun241);
        this.session.setAttribute("fun242", (Object)fun242);
        this.session.setAttribute("fun243", (Object)fun243);
        int maxAge = 31536000;
        Cookie cookie = new Cookie("cookie_uname", this.userName);
        cookie.setMaxAge(maxAge);
        this.response.addCookie(cookie);
        return "success";
    }

    public String exit() {
        this.session.invalidate();
        return "success";
    }

    public String forwardPwd() {
        return "success";
    }

    public String changePwd() {
        if (!this.newPwd.equals(this.newPwd2)) {
            this.request.setAttribute("errInfo", (Object)"两次密码输入不一致");
            return "input";
        }
        if (!this.session.getAttribute("passWord").toString().equals(this.oldPwd)) {
            this.request.setAttribute("errInfo", (Object)"旧密码错误");
            return "input";
        }
        try {
            this.userService.updateUserPwd((Serializable)Integer.valueOf(UserInfo.getUserId()), MD5.getStr((String)this.newPwd));
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public void getFuncTree() {
        block6: {
            JSONArray jsonArray = new JSONArray();
            jsonArray = this.roleService.getUserFunArray(UserInfo.getUserId());
            this.response.setContentType("text/html;charset=utf-8");
            this.response.setCharacterEncoding("utf-8");
            PrintWriter writer = null;
            try {
                try {
                    writer = this.response.getWriter();
                    if (jsonArray == null) {
                        this.response.getWriter().print("");
                        break block6;
                    }
                    this.response.getWriter().print((Object)jsonArray);
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("write jsonArray error: FunctionAction.java---" + e);
                    writer.flush();
                    writer.close();
                }
            }
            finally {
                writer.flush();
                writer.close();
            }
        }
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getValidCode() {
        return this.validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getOldPwd() {
        return this.oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return this.newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getNewPwd2() {
        return this.newPwd2;
    }

    public void setNewPwd2(String newPwd2) {
        this.newPwd2 = newPwd2;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setEmpService(EmpService empService) {
        this.empService = empService;
    }
}

