package com.moon.project.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.moon.framework.config.MoonConfig;
import com.moon.framework.web.controller.BaseController;
import com.moon.project.system.menu.domain.Menu;
import com.moon.project.system.menu.service.IMenuService;
import com.moon.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author yihu
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private MoonConfig moonConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", moonConfig.getCopyrightYear());
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", moonConfig.getVersion());
        return "main";
    }

}
