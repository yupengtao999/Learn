package com.krry.controller;

import com.krry.dao.IUserDao;
import com.krry.entity.AlarmDetail;
import com.krry.entity.User;
import com.krry.util.TmStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller层，作为请求转发
 * 页面所有路径的访问方法:控制层的命名空间+@RequestMapping的value
 */
@Controller
@RequestMapping("/index")
public class KrryController {

    @Autowired
    private IUserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(KrryController.class);

    /**
     * 进入首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {

        return "index/index";   //默认是转发，不会显示转发路径
    }

    /**
     * 进入登录界面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {

        return "index/login";   //默认是转发，不会显示转发路径
    }


    /**
     * 点击登录
     * com.krry.controller.login
     * 方法名：login
     *
     * @param request
     * @return String
     * @throws
     * @author krry
     * @since 1.0.0
     */
    @RequestMapping(method = RequestMethod.POST, value = "/logined")
    public String logined(HttpServletRequest request) {
        //获取用户和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //如果邮箱和密码为null,那么就返回已null标识
        if (TmStringUtils.isEmpty(username)) return "index/allError";
        if (TmStringUtils.isEmpty(password)) return "index/allError";

        //根据邮箱或昵称查询，用户是否存在
        User user = userDao.findByUsername(username);

        //如果存在
        if (user != null) {

            if (password.equals(user.getPassword())) {
                //如果密码正确
                //将用户信息放入到会话中...
                request.getSession().setAttribute("user", user);

                //这里使用重定向 ,重定向到当前命名空间下@RequestMapping是index的方法
                return "redirect:index";
            } else {
                //如果密码错误
                LOGGER.error("密码错误");
                return "index/error";
            }
        } else {
            //如果不存在，代码邮箱和密码输入有误
            LOGGER.error("用户不存在");
            return "index/error";
        }
    }

    /**
     * 退出登录控制层
     * com.krry.controller.login
     * 方法名：logout
     *
     * @param request
     * @return String
     * @throws
     * @author krry
     * @since 1.0.0
     */
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate(); //清空session值
        return "index/index";
    }

    /**
     * 打开注册界面层
     *
     * @return
     */
    @RequestMapping("/rege")
    public String rege() {

        return "index/resgi";
    }

    /**
     * 注册控制层
     * com.krry.controller.login
     * 方法名：resig
     *
     * @param request
     * @return String
     * @throws
     * @author krry
     * @since 1.0.0
     */
    @RequestMapping(method = RequestMethod.POST, value = "/resig")
    public String resig(HttpServletRequest request) {
        //获取用户和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //如果邮箱和密码为null,那么就返回已null标识
        if (TmStringUtils.isEmpty(username)) return "index/allError";
        if (TmStringUtils.isEmail(password)) return "index/allError";

        //根据昵称查询，用户是否存在
        User user = userDao.findByUsername(username);

        //若存在
        if (user != null) { //昵称重复
            return "index/allError";
        }

        //格式化时间类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(new Date());

        //执行到这里，说明可以注册
        User newUser = new User(username, password, nowTime);
        //调用注册方法
        userDao.saveOrUpdateUser(newUser);

        //将信息设置session作用域
        request.getSession().setAttribute("user", newUser);

        return "redirect:index";
    }

    /**
     * 删除用户
     */
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(HttpServletRequest request) {
        String username = request.getParameter("user");
        userDao.delete(username);
        return "index/index";
    }

    /**
     * 查询所有
     */
//    @RequestMapping(method = RequestMethod.POST, value = "/query")
//    public String query(HttpServletRequest request, HttpSession session) {
//        List<User> result = userDao.findAll();
////        String[] tags = result.get(6).getTags();
////        for (int i=0;i<tags.length;i++){
////            System.out.println(tags[i]);
////        }
//        System.out.println(result);
////        //获取客户端向服务器端传送数据的协议名称
////        System.out.println("rotocol: " + request.getProtocol());
////
////        //返回的协议名称.默认是http
////        System.out.println("Scheme: " + request.getScheme());
////        //可以返回当前页面所在的服务器的名字;如果你的应用部署在本机那么其就返回localhost或者127.0.0.1 ，这两个是等价的
////        System.out.println("Server Name: " + request.getServerName());
////
////        //可以返回当前页面所在的服务器使用的端口,默认就是8080
////        System.out.println("Server Port: " + request.getServerPort());
////        //request.getRemoteAddr()是获得客户端的ip地址
////        System.out.println("Remote Addr: " + request.getRemoteAddr());
////
////        //request.getRemoteHost()是获得客户端的主机名。
////        System.out.println("Remote Host: " + request.getRemoteHost());
////
////        //返回字符编码
////        System.out.println("Character Encoding: " + request.getCharacterEncoding());
////
////        System.out.println("Content Length: " + request.getContentLength());
////
////        //定义网络文件的类型和网页的编码，决定浏览器将以什么形式、什么编码读取这个文件，
////        System.out.println("Content Type: " + request.getContentType());
////
////        //如果servlet由一个鉴定方案所保护，如HTTP基本鉴定，则返回方案名称
////        System.out.println("Auth Type: " + request.getAuthType());
////
////        //返回HTTP请求方法（例如GET、POST等等）
////        System.out.println("HTTP Method: " + request.getMethod());
////
////        //返回在URL中指定的任意附加路径信息。
////        System.out.println("path Info: " + request.getPathInfo());
////
////        //返回在URL中指定的任意附加路径信息，被子转换成一个实际路径
////        System.out.println("path Trans: " + request.getPathTranslated());
////
////        //返回查询字符串，即URL中?后面的部份。
////        System.out.println("Query String: " + request.getQueryString());
////
////        //如果用户通过鉴定，返回远程用户名，否则为null。
////        System.out.println("Remote User: " + request.getRemoteUser());
////
////        //返回客户端的会话ID
////        System.out.println("Session Id: " + request.getRequestedSessionId());
////
////        //返回URL中一部分，从“/”开始，包括上下文，但不包括任意查询字符串。
////        System.out.println("Request URI: " + request.getRequestURI());
////
////        //返回请求URI上下文后的子串
////        System.out.println("Servlet Path: " + request.getServletPath());
////
////        //返回指定的HTTP头标指。如果其由请求给出，则名字应为大小写不敏感。
////        System.out.println("Accept: " + request.getHeader("Accept"));
////        System.out.println("Host: " + request.getHeader("Host"));
////        System.out.println("Referer : " + request.getHeader("Referer"));
////        System.out.println("Accept-Language : " + request.getHeader("Accept-Language"));
////        System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding"));
////        System.out.println("User-Agent : " + request.getHeader("User-Agent"));
////        System.out.println("Connection : " + request.getHeader("Connection"));
////        System.out.println("Cookie : " + request.getHeader("Cookie"));
////        System.out.println("X-Requested-With : " + request.getHeader("X-Requested-With"));
////        System.out.println("Created : " + session.getCreationTime());
////        System.out.println("LastAccessed : " + session.getLastAccessedTime());
//        return "index/index";
//    }

    /**
     * @author: yupt
     * @date: 2019/12/16 16:00
     * 修改用戶
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String update(HttpServletRequest request) {
        String username = request.getParameter("user");
        userDao.update(username);
        return "index/index";
    }

    //    /**
//     * @author: yupt
//     * @date: 2020/1/3 8:58
//     * Json返回
//     */
//    @RequestMapping(method = RequestMethod.POST,value = "/query")
//    public @ResponseBody JsonResult query(){
//        List<User> result = userDao.findAll();
//        return new JsonResult(result);
//    }

    /**
     * 聚合查询
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public String query() {
        List<AlarmDetail> result = userDao.select(1,15);
        System.out.println(result);
        return "index/index";
    }
}



