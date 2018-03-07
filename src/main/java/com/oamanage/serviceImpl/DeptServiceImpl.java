package com.oamanage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.oamanage.dao.DeptDao;
import com.oamanage.po.Dept;
import com.oamanage.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	
	@Autowired
	private DeptDao deptDao;
	
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}


	//添加部门
	@Override
	public void addDept(String deptName) {
		// TODO Auto-generated method stub
		deptDao.addDept(deptName);
	}

	//分页查询全部部门
	@Override
	public List<Dept> queryAllDept(int page,int row) {
		PageHelper.startPage(page, row);
		List<Dept> deptList = deptDao.queryAllDept();
		// TODO Auto-generated method stub
		return deptList;
	}

	//查询部门总数
	@Override
	public int queryDeptCount() {
		int count = deptDao.queryDeptCount();
		// TODO Auto-generated method stub
		return count;
	}

	//查询全部部门
	@Override
	public List<Dept> allDept() {
		List<Dept> deptList = deptDao.queryAllDept();
		// TODO Auto-generated method stub
		return deptList;
	}

}
