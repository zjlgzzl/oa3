/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.common.auth.action.UserInfo
 *  com.common.auth.dao.RoleDAO
 *  com.common.auth.service.RoleService
 *  com.common.auth.service.RoleServiceImpl
 *  com.common.entity.TRole
 *  com.common.entity.VUserFun
 *  net.sf.json.JSONArray
 */
package com.common.auth.service;

import com.common.auth.action.UserInfo;
import com.common.auth.dao.RoleDAO;
import com.common.auth.service.RoleService;
import com.common.entity.TRole;
import com.common.entity.VUserFun;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;

public class RoleServiceImpl
implements RoleService {
    private RoleDAO roleDAO;

    public List<Integer> getList(int userId) {
        return this.roleDAO.getList(userId);
    }

    public JSONArray getUserFunArray(int userId) {
        ArrayList<String> arrayList = new ArrayList<String>(20);
        List list = this.roleDAO.getUserFunList(userId);
        if (list.size() == 0) {
            return null;
        }
        for (int i = 0; i < list.size(); ++i) {
            if ("admin".equals(UserInfo.getUserName()) && ((VUserFun)list.get(i)).getFunid() != 1 && (((VUserFun)list.get(i)).getFunid() <= 100 || ((VUserFun)list.get(i)).getFunid() >= 200) || ((VUserFun)list.get(i)).getFunid() == 202 || ((VUserFun)list.get(i)).getFunid() == 203 || ((VUserFun)list.get(i)).getFunid() == 204 || ((VUserFun)list.get(i)).getFunid() == 505 || ((VUserFun)list.get(i)).getFunid() == 303 || ((VUserFun)list.get(i)).getFunid() == 205 || ((VUserFun)list.get(i)).getFunid() == 507 || ((VUserFun)list.get(i)).getFunid() == 508 || ((VUserFun)list.get(i)).getFunid() == 509 || ((VUserFun)list.get(i)).getFunid() == 510 || ((VUserFun)list.get(i)).getFunid() == 511 || ((VUserFun)list.get(i)).getFunid() == 207 || ((VUserFun)list.get(i)).getFunid() == 208 || ((VUserFun)list.get(i)).getFunid() == 209 || ((VUserFun)list.get(i)).getFunid() == 210 || ((VUserFun)list.get(i)).getFunid() == 211 || ((VUserFun)list.get(i)).getFunid() == 212 || ((VUserFun)list.get(i)).getFunid() == 213 || ((VUserFun)list.get(i)).getFunid() == 214 || ((VUserFun)list.get(i)).getFunid() == 305 || ((VUserFun)list.get(i)).getFunid() == 411 || ((VUserFun)list.get(i)).getFunid() == 514 || ((VUserFun)list.get(i)).getFunid() == 515 || ((VUserFun)list.get(i)).getFunid() == 711 || ((VUserFun)list.get(i)).getFunid() == 306 || ((VUserFun)list.get(i)).getFunid() == 713 || ((VUserFun)list.get(i)).getFunid() == 216 || ((VUserFun)list.get(i)).getFunid() == 217 || ((VUserFun)list.get(i)).getFunid() == 618 || ((VUserFun)list.get(i)).getFunid() == 219 || ((VUserFun)list.get(i)).getFunid() == 607 || ((VUserFun)list.get(i)).getFunid() == 608 || ((VUserFun)list.get(i)).getFunid() == 307 || ((VUserFun)list.get(i)).getFunid() == 220 || ((VUserFun)list.get(i)).getFunid() == 309 || ((VUserFun)list.get(i)).getFunid() == 310 || ((VUserFun)list.get(i)).getFunid() == 221 || ((VUserFun)list.get(i)).getFunid() == 619 || ((VUserFun)list.get(i)).getFunid() == 620 || ((VUserFun)list.get(i)).getFunid() == 516 || ((VUserFun)list.get(i)).getFunid() == 311 || ((VUserFun)list.get(i)).getFunid() == 224 || ((VUserFun)list.get(i)).getFunid() == 225 || ((VUserFun)list.get(i)).getFunid() == 623 || ((VUserFun)list.get(i)).getFunid() == 226 || ((VUserFun)list.get(i)).getFunid() == 624 || ((VUserFun)list.get(i)).getFunid() == 228 || ((VUserFun)list.get(i)).getFunid() == 229 || ((VUserFun)list.get(i)).getFunid() == 231 || ((VUserFun)list.get(i)).getFunid() == 315 || ((VUserFun)list.get(i)).getFunid() == 625 || ((VUserFun)list.get(i)).getFunid() == 627 || ((VUserFun)list.get(i)).getFunid() == 628 || ((VUserFun)list.get(i)).getFunid() == 232 || ((VUserFun)list.get(i)).getFunid() == 317 || ((VUserFun)list.get(i)).getFunid() == 319 || ((VUserFun)list.get(i)).getFunid() == 206 || ((VUserFun)list.get(i)).getFunid() == 308) continue;
            StringBuffer data = new StringBuffer();
            data.append("{id:");
            data.append(((VUserFun)list.get(i)).getFunid().toString());
            data.append(",pId:");
            data.append(((VUserFun)list.get(i)).getUppid().toString());
            data.append(",name:");
            data.append("\"");
            data.append(((VUserFun)list.get(i)).getFunname().toString());
            data.append("\"");
            data.append(",page:");
            if (((VUserFun)list.get(i)).getUppid() > 0) {
                data.append("\"");
                data.append(((VUserFun)list.get(i)).getFunaction().toString());
                data.append("\"");
            } else {
                data.append("\"none\"");
            }
            data.append(",open:true}");
            arrayList.add(data.toString());
        }
        return JSONArray.fromObject(arrayList);
    }

    public List<VUserFun> getUserFunList(int userId) {
        return this.roleDAO.getUserFunList(userId);
    }

    public void saveRole(TRole role) throws Exception {
        this.roleDAO.saveRole(role);
    }

    public void deleteRole(int userId) throws Exception {
        this.roleDAO.deleteRole(userId);
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
}

