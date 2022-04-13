package com.ymf.springboot.controller;

import com.ymf.springboot.dao.DepartmentDao;
import com.ymf.springboot.dao.EmployeeDao;
import com.ymf.springboot.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author yuanmengfan
 * @date 2021/11/6 3:44 下午
 * @description
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);



    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;


    @RequestMapping(value = "/emps",method = RequestMethod.GET)
    public String list(Map<String,Object> map){
        map.put("emps", employeeDao.getAll());
        return  "emp/list";
    }

    //来到新增员工页面
    @RequestMapping(value = "/addEmp",method = RequestMethod.GET)
    public String addAction(Map<String,Object> map){
        //传入部门列表
        map.put("depts",departmentDao.getDepartments());
        return  "emp/add";
    }

    //员工添加
    @RequestMapping(value = "/emps",method = RequestMethod.POST)
    public String addEmp(Employee employee){
        logger.info("add " +employee);
        employeeDao.save(employee);
        //来到员工列表页面
        // redirect :表示重定向到一个地址
        // forward :表示转发到这个地址
        return "redirect:/emp/emps";
    }

    //来到员工修改页面
    @RequestMapping(value = "/emps/{id}",method = RequestMethod.GET)
    public String updateAction(@PathVariable String id, Map<String,Object> map,Model model){

        Employee employee = employeeDao.get(Integer.parseInt(id));
        //传入部门列表
        model.addAttribute("method", "put");
        map.put("depts",departmentDao.getDepartments());
        map.put("emp",employee);
        return  "emp/add";
    }

    //执行修改
    @RequestMapping(value = "/emps",method = RequestMethod.PUT)
    public String update(Employee employee,Model model){
        logger.info("update " +employee);
        employeeDao.save(employee);

        model.addAttribute("emps",employeeDao.getAll());
        return "redirect:/emp/emps";
    }

    //删除员工
    @RequestMapping(value = "/emps/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id, Map<String,Object> map){
        logger.info("delete id :" + id);
        employeeDao.delete(id);
        map.put("emps",employeeDao.getAll());
        return "redirect:/emp/emps";
    }
}
