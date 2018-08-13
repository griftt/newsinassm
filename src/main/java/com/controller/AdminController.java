package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.data.ResultMap;
import com.entity.Admin;
import com.entity.Query;
import com.serviceImpl.AdminService;
import com.util.CodeUtil;

@Controller
@RequestMapping("/adminLogin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	// 验证码功能
	@RequestMapping("/code.action")
	public void getCode(HttpServletResponse response, HttpSession session) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String code = CodeUtil.number();

		output = (ByteArrayOutputStream) CodeUtil.handCode(CodeUtil.codeImg(code));
		try {
			ServletOutputStream out = response.getOutputStream();
			System.out.println(code);
			session.setAttribute("code", code);
			output.writeTo(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 管理员登录
	@SuppressWarnings("unused")
	@RequestMapping("/login.action")
	public String login(Admin admin, String code, Model model, HttpSession session) {
		String mycode = (String) session.getAttribute("code");
		mycode = mycode.toUpperCase();
		if (code == null) {
			return "login";
		} else {
			code = code.toUpperCase();
			if (!mycode.equals(code)) {
				return "login";
			}
		}

		if (admin == null) {
			return "login";
		} else {
			if (admin.getAccount() == null || admin.getPwd() == null) {
				return "login";
			} else {
				Admin admin2 = adminService.findAdmin(admin);
				if (admin2 == null) {
					return "login";
				}
				session.setAttribute("admin", admin2);
				model.addAttribute("admin", admin2);
				return "main";
			}
		}

	}

	// 管理员退出登录
	@RequestMapping("/loginOut.action")
	public String loginOut(HttpSession session) {
		Session session2 = SecurityUtils.getSubject().getSession();
		session.invalidate();
		return "login";
	}

	// 查看管理员
	@RequestMapping("/adminPage.action")
	@ResponseBody
	public ResultMap find(Integer page, Integer limit) {
		if (page <= 0 || limit <= 0) {
			return new ResultMap(0, "参数有误", null, 0);
		}
		int count = adminService.countAll();
		List<Admin> list = adminService.selectPage(new Query((page - 1) * limit, limit));
		return new ResultMap(0, "", list, count);
	}

	@RequestMapping("/deleteAdmin.action")
	@ResponseBody
	public boolean deleteAdmin(Integer id) {
		if (id <= 0) {
			return false;
		}
		System.err.println(id);
		boolean b =adminService.deleteAdmin(id);

		return b;
	}

	// 添加普通管理员
	@RequestMapping("/addAdmin.action")
	@ResponseBody
	public boolean insertAdmin( Admin admin) {
		/*Subject s=SecurityUtils.getSubject();
		Admin p = (Admin) s.getPrincipal();
		boolean a=s.hasRole("superadmin");*/
		if(admin!=null){
			boolean b=adminService.addAdmin(admin);
			return b;
		}
		System.out.println("权限不够无法添加");
		return false;
	}
	//管理员修改头像
	@RequestMapping("/upload.action")
	@ResponseBody
	public ResultMap uplodPic(MultipartFile pic,HttpSession session){
		if(pic.getOriginalFilename()==null){
			return null;
		}
		String picName=pic.getOriginalFilename();
		String ext=picName.substring(picName.lastIndexOf("."));
		String fileName=UUID.randomUUID().toString();
		String dir=fileName.substring(0, 3);
		File f=new File( "D:/pic/"+dir+"/"+fileName+ext);
		if(!f.exists()){
			f.getAbsoluteFile().mkdirs();
		}
		try {
			pic.transferTo(f);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path=dir+"/"+fileName+ext;
		Query q=new Query();
		Admin admin=(Admin)session.getAttribute("admin");
		q.setId(admin.getId());
		q.setMessage(path);
		boolean b=adminService.uploadPic(q);
		if(b){
		 return new ResultMap<String>(0, "", path,0);
		}
		return null;
	}
	
	//统计在线人数
	@ModelAttribute("countOnline")
	public Integer countOnlineUser(HttpSession session) {
		System.err.println("統計在線人數");
		Map<Integer, HttpSession> users = (Map<Integer, HttpSession>) session.getServletContext()
				.getAttribute("userSessionMap");
		if (users == null) {
			return 0;
		}
		return users.size();

	}

	@RequestMapping("/login2.action")
	public String login2(Admin admin, String code, Model model) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(true);
		String mycode = (String) session.getAttribute("code");
		mycode = mycode.toUpperCase();
		if (code == null) {
			return "login";
		} else {
			code = code.toUpperCase();
			if (!mycode.equals(code)) {
				return "login";
			}
		}

		System.out.println(admin);
		UsernamePasswordToken token = new UsernamePasswordToken(admin.getAccount(), admin.getPwd());
		try {
			// 执行认证操作.
			System.out.println("开始验证");
			subject.login(token);
			Admin admin2 = (Admin) subject.getPrincipal();
			System.out.println("admin2="+admin2);
			session.setAttribute("admin", admin2);
			model.addAttribute("admin", admin2);
			// return "main";
			
		} catch (AuthenticationException ae) {
			ae.getCause();
			System.out.println("登陆失败: " + ae.getMessage());
			return "login";
		}
		subject.isPermitted("show");

		return "main";
	}
public static void main(String[] args) {
	/*Calendar c = Calendar.getInstance();
	c.setTime(new Date());
	System.out.println(c.get(Calendar.MONTH));
	*/
	//HashMap m = new HashMap();
	//Hashtable m = new Hashtable();
	Map m=new ConcurrentHashMap<>();
	m.put(1, "1");
	//m.put(null, null);
	System.out.println(m.get(1));
	HashSet set = new HashSet();
	set.add(null);
	
 }
}
