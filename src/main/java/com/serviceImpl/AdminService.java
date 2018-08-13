package com.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Admin;
import com.entity.Query;
import com.mapper.AdminMapper;
import com.mapper.WeiBoMapper;
import com.serviceInterface.AdminServiceInterface;

@Transactional
@Service
public class AdminService implements  AdminServiceInterface{
	
	@Resource(name="adminMapper")
	private AdminMapper adminMapper;
	@Resource
	private WeiBoServiceImpl wbs;

	@Override
	public List<Admin> SelectAll() {
		// TODO Auto-generated method stub
		List<Admin> admins = adminMapper.selectAll();
		return admins;
	}

	public Admin findAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(admin!=null){
			return adminMapper.findAdmin(admin);
		}
		
		return null;
	}

	@Override
	public List<Admin> selectPage(Query aq) {
		// TODO Auto-generated method stub
		List<Admin> admins =null;
		if(aq!=null){
			admins =adminMapper.selectPage(aq);
		}
		return admins;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return adminMapper.countAll();
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRES_NEW)
	public boolean deleteAdmin(int id) {
		// TODO Auto-generated method stub
		if(id<=0){
			return false; 
		}
		adminMapper.deleteAdmin(id);
		return true;
	}

	@Override
	public boolean insertAdmin(Admin admin) {
		return false;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

	public Admin findByAccount(String principal) {
		if(principal==null||"".equals(principal)){
			System.out.println("账号不能为空");
			return null;
		}
		Admin admin = null ;
		try{
		admin= adminMapper.findByAccount(principal);
		}catch(Exception e){
			System.err.println("有异常啦");
			e.printStackTrace();
		}
		System.err.println(admin);
		return admin;
		
	}

	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public boolean addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(admin==null){
			return false;
		}
		adminMapper.insertAdmin(admin);
		if(admin.getId()!=0){
			wbs.deleteWeiBoById(22);
		   int a=9/0;
			return true;
		}
		return false;
	}

	public boolean uploadPic(Query q){
		
		try{
		   adminMapper.uploadPic(q);
		}catch(Exception e){
			
			return false;
		}
		return true;
	}
	

}
