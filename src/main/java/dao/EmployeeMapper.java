package dao;

import org.apache.ibatis.annotations.*;
import pojo.Employee;

import java.util.List;

/**
 * @author JIADE
 * @description EmployeeMapper
 * @date 2021/6/5 14:28
 */
public interface EmployeeMapper {

    @Select("select * from employee")
    List<Employee> getEmployeeList();
    /**
     * @description 获取所有数据
     * @param
     * @return java.util.List<pojo.Employee>
     * @author JIADE
     * @date 2021/6/5 14:55
     */

    @Select("select * from employee where id = #{id}")
    Employee getEmpbyId(int id);
    /**
     * @description 通过id查询
     * @param id
     * @return pojo.Employee
     * @author JIADE
     * @date 2021/6/5 14:58
     */

    @Select("select * from employee where name = #{name}")
    Employee getEmpbyName(String name);
    /**
     * @description 姓名查询
     * @param name
     * @return pojo.Employee
     * @author JIADE
     * @date 2021/6/5 14:47
     */

    @Insert("insert into employee (id,name,bp,pp,allowance,ins,cpf) values (#{id},#{name},#{bp},#{pp},#{allowance},#{ins},#{cpf})")
    int insertEmp(Employee employee);/**
     * @description 插入数据
     * @param employee 
     * @return int
     * @author JIADE
     * @date 2021/6/5 15:00
     */

    @Select("select count(*) from employee")
    int queryCount();/**
     * @description 查询数据总数
     * @param
     * @return int
     * @author JIADE
     * @date 2021/6/5 21:06
     */

    int insertEmpList(List<Employee> list);/**
     * @description 多条插入
     * @param list
     * @return int
     * @author JIADE
     * @date 2021/6/5 20:12
     */

    @Delete("delete from employee where id = #{id}")
    int deleteEmp(int id);/**
     * @description 根据id删除
     * @param id
     * @return int
     * @author JIADE
     * @date 2021/6/5 18:21
     */

    @Update("update employee set id = #{id},name = #{name},bp = #{bp},pp = #{pp},allowance = #{allowance},ins = #{ins},cpf = #{cpf} where id = #{id}")
    int updateEmp(Employee employee);/**
     * @description 修改数据
     * @param employee
     * @return int
     * @author JIADE
     * @date 2021/6/5 21:54
     */


}
