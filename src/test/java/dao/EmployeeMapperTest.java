package dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import pojo.Employee;
import utils.MybatisUtils;

import static org.junit.Assert.*;

public class EmployeeMapperTest {

    @Test
    public void updateEmp() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int i = mapper.updateEmp(new Employee(3, "1", 1, 1, 1, 1, 1));
        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();

    }
}