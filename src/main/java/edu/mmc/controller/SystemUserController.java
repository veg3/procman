package edu.mmc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import edu.mmc.entity.SystemUser;
import edu.mmc.entity.vo.RoleMenuVo;
import edu.mmc.entity.vo.SystemUserVo;
import edu.mmc.entity.vo.TableDataVo;
import edu.mmc.service.ISystemUserService;
import edu.mmc.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@RestController
@RequestMapping("/user")
public class SystemUserController {
    @Autowired
    ISystemUserService sus;

    @PostMapping("/login")
    public TableDataVo login(String id, String pwd){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(id,pwd);
            try {
                currentUser.login(token);
                SystemUserVo user = sus.getUserVoByUid(id);
                currentUser.getSession().setAttribute("user", user);
            }
            catch (Exception e) {
                return TableDataVo.failMsg("账号或密码错误!!");
            }
        }
        return TableDataVo.okMsg("登录成功");
    }

    @RequestMapping("/logout")
    public TableDataVo logout(){
        SecurityUtils.getSubject().logout();
        return TableDataVo.okMsg("退出成功!!");
    }

    @RequestMapping("/login-info")
    public TableDataVo loginInfo(HttpSession session){
        SystemUserVo user = (SystemUserVo) session.getAttribute("user");
        if (Objects.nonNull(user)) {
            return TableDataVo.okData(user);
        }
        return TableDataVo.failMsg("获取失败!");
    }

    @PostMapping("/updatePwd")
    public TableDataVo updatePwd(SystemUserVo userVo,HttpSession session){
        String logPwd = userVo.getPwd();
        SystemUser su = sus.getUser(UserUtils.getUid(session).toString());
        String pass = su.getPassword();
        if (!logPwd.equals(pass) ){
            return TableDataVo.failMsg("当前密码输入错误！");
        }else {
            String newpwd = userVo.getNewPwd();
            su.setPassword(newpwd);
            sus.updatepwd(su.getUserId().toString(),newpwd);
            return TableDataVo.okMsg("密码修改成功！请重新登录！");
        }
    }
    @RequestMapping(value = "/getMenus", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public List<RoleMenuVo> getMenus(HttpSession session) {
        SystemUserVo user = (SystemUserVo) session.getAttribute("user");
        List<RoleMenuVo> menus = null;
        if (user != null) {
            // 得到用户菜单
            menus = sus.getMenus(user.getRoleId());
        }
        return menus;
    }
    @RequestMapping("/rid")
    public TableDataVo rid(HttpSession session){
        return TableDataVo.okData(UserUtils.getRid(session));
    }
    @RequestMapping("retpwd")
    public TableDataVo retpwd(String id,String idcard){
        SystemUser user = sus.getUser(id);
        if(Objects.nonNull(user)){
             String idc = user.getIdCard();
             if(user.getRoleId()!=1){
                 return TableDataVo.failMsg("抱歉,该功能暂时只对学生开放");
             }
             if(idc !=null && idc.equals(idcard)){
                 Random random = new Random();
                 String str = "";
                 for(int i=0;i<6;i++){
                     str+=random.nextInt(10);
                 }
                 sus.updatepwd(id,str);
                 return TableDataVo.okMsg("密码重置成功,新密码为"+str);
             }
        }
        return TableDataVo.failMsg("账号或身份证输入有误");
    }
}
