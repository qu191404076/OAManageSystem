package com.oamanage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.oamanage.dao.StaffDao;
import com.oamanage.exception.AddStaffInfoException;
import com.oamanage.exception.NoUserException;
import com.oamanage.po.Staff;
import com.oamanage.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	private StaffDao staffDao;

	
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}


	//查询全部员工
	@Override
	public List<Staff> queryAllStaff(int page,int row) {
		PageHelper.startPage(page,row);
		return staffDao.queryAllStaff();
	}

	//添加人员
	@Override
	public void addStaff(Staff staff) {
		// TODO Auto-generated method stub
		staffDao.addStaff(staff);
		
	}
	
	//添加人员部门关系
	public void addStaffDept(Staff staff){
		staffDao.addStaffDept(staff);
	}
	
	//添加人员角色关系
	public void addStaffRole(Staff staff){
		staffDao.addStaffRole(staff);
	}

	//修改密码
	@Override
	public void modifyPwd(String userName, String loginPassword) {
		// TODO Auto-generated method stub
		staffDao.modifyPwd(userName, loginPassword);
	}

	//查询人员总数
	@Override
	public int queryStaffCount() {
		int sum = staffDao.queryStaffCount();
		return sum;
	}
	
	//删除人员
	public void deleteStaff(String userId){
		staffDao.deleteStaff(userId);
		staffDao.deleteStaffDept(userId);
		staffDao.deleteStaffRole(userId);
	}
	
}
