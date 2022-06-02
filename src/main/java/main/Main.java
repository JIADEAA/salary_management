package main;

import dao.EmployeeMapper;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.PropertyConfigurator;
import pojo.Employee;
import utils.MybatisUtils;
import utils.ReadKeyboard;

import java.io.*;
import java.util.*;

/**
 * @author JIADE
 * @description Main
 * @date 2021/6/5 16:43
 */
public class Main {


    public static void main(String[] args) {

        /**
         * @description 程序入口
         * @param args
         * @return void
         * @author JIADE
         * @date 2021/6/6 16:02
         */
        Main main = new Main();
        initLog();
        main.enterMainMenu();
    }

    public static void initLog() {
        FileInputStream fileInputStream = null;
        try {
            Properties properties = new Properties();
            fileInputStream = new FileInputStream("finalexam/src/main/resources/log4j.properties");
            properties.load(fileInputStream);
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void enterMainMenu() {
        /**
         * @description 菜单
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/5 18:57
         */

        boolean flag = true;
        char menu;

        while (flag) {

            System.out.println("*********************职工工资管理系统*********************");
            System.out.println("****                 1 - 新增数据                    ****");
            System.out.println("****                 2 - 显示数据                    ****");
            System.out.println("****                 3 - 修改数据                    ****");
            System.out.println("****                 4 - 查找数据                    ****");
            System.out.println("****                 5 - 删除数据                    ****");
            System.out.println("****                 6 - 统计数据                    ****");
            System.out.println("****                 7 - 关于                       ****");
            System.out.println("****                 8 - 保存文件                    ****");
            System.out.println("****                 0 - 退出系统                    ****");
            System.out.println("********************************************************");

            menu = ReadKeyboard.readMenuSelection();

            switch (menu) {

                case '1':
                    try {
                        addEmp();
                    } catch (Exception e) {
                        System.out.println("出现错误，失败原因：" + e.getMessage());

                    }
                    break;
                case '2':
                    try {
                        showEmp();
                    } catch (Exception e) {
                        System.out.println("出现错误，失败原因：" + e.getMessage());

                    }
                    break;
                case '3':
                    try {
                        updateEmp();
                    } catch (Exception e) {
                        System.out.println("出现错误，失败原因：" + e.getMessage());

                    }
                    break;
                case '4':
                    try {
                        searchEmp();
                    } catch (Exception e) {
                        System.out.println("出现错误，失败原因：" + e.getMessage());

                    }
                    break;
                case '5':
                    try {
                        delateEmp();
                    } catch (Exception e) {
                        System.out.println("出现错误，失败原因：" + e.getMessage());

                    }
                    break;
                case '6':
                    try {
                        stats();
                    } catch (Exception e) {
                        System.out.println("出现错误，失败原因：" + e.getMessage());

                    }
                    break;
                case '7':
                    about();
                    break;
                case '8':
                    save();
                    break;

                case '0':
                    System.out.print("确认是否退出(Y/N)：");
                    char isExit = ReadKeyboard.readConfirmSelection();
                    if (isExit == 'Y') {
                        flag = false;
                    }
                    break;
            }
        }

    }

    private void save() {
        /**
         * @description 保存文件
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/7 9:02
         */

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        int i = mapper.queryCount();

        if (i > 0) {

            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;
            try {
                File file = new File("data.txt");

                fileWriter = new FileWriter(file);
                bufferedWriter = new BufferedWriter(fileWriter);
                List<Employee> employeeList = mapper.getEmployeeList();

                for (Employee s : employeeList) {

                    bufferedWriter.write(String.valueOf(s));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileWriter != null) {
                    //
                    try {
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        } else {
            System.out.println("暂无数据，请先添加数据\n");
        }


        System.out.println("成功保存\n");
        sqlSession.close();
        ReadKeyboard.readReturn();
    }

    private void delateEmp() {/**
     * @description 删除数据
     * @param
     * @return void
     * @author JIADE
     * @date 2021/6/5 21:41
     */
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        int i = mapper.queryCount();

        if (i > 0) {

            System.out.println("请输入员工的ID");
            Scanner scanner = new Scanner(System.in);
            int id = Integer.parseInt(scanner.nextLine());
            Employee employee = mapper.getEmpbyId(id);

            if (employee != null) {
                System.out.println("该员工的原信息：");
                System.out.println("ID号\t\t姓名\t\t基本工资\t\t职务工资\t\t津贴\t\t医疗保险\t\t公积金");
                System.out.println(employee.getId() + "\t\t" + employee.getName()
                        + "\t\t" + employee.getBp() + "\t\t" + employee.getPp() + "\t\t" + employee.getAllowance() + "\t\t" +
                        employee.getIns() + "\t\t" + employee.getCpf());

                System.out.println("确实是否删除(Y/N)");
                char c = ReadKeyboard.readConfirmSelection();

                if (c == 'Y') {
                    mapper.deleteEmp(id);
                    System.out.println("删除成功\n");
                }
            }

        } else {
            System.out.println("暂无数据，请先添加数据\n");
        }

        sqlSession.commit();
        sqlSession.close();
        ReadKeyboard.readReturn();

    }

    private void stats() {
        /**
         * @description 统计
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/5 21:41
         */

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);


        List<Employee> employeeList = mapper.getEmployeeList();


        System.out.println("ID\t\t姓名\t\t基本工资\t\t职务工资\t\t津贴\t\t医疗保险\t\t公积金\t\t总工资");

        int sum = 0;
        for (Employee employee : employeeList) {
            int all = employee.getId() + employee.getBp() + employee.getPp() + employee.getAllowance() + employee.getIns() + employee.getCpf();
            System.out.println(employee.getId() + "\t\t" + employee.getName()
                    + "\t\t" + employee.getBp() + "\t\t" + employee.getPp() + "\t\t" + employee.getAllowance() + "\t\t" +
                    employee.getIns() + "\t\t" + employee.getCpf() + "\t\t" + all);

            sum += all;
        }

        OptionalDouble average = employeeList.stream().mapToInt(Employee::getBp).average();
        System.out.println("基本工资平均值： " + average);

        OptionalDouble average1 = employeeList.stream().mapToInt(Employee::getPp).average();
        System.out.println("职务工资平均值： " + average1);

        OptionalDouble average2 = employeeList.stream().mapToInt(Employee::getAllowance).average();
        System.out.println("津贴平均值：    " + average2);

        OptionalDouble average3 = employeeList.stream().mapToInt(Employee::getIns).average();
        System.out.println("医疗保险平均值： " + average3);

        OptionalDouble average4 = employeeList.stream().mapToInt(Employee::getCpf).average();
        System.out.println("公积金平均值：   " + average4);

        sqlSession.close();
        ReadKeyboard.readReturn();
    }

    private void about() {

        /**
         * @description 关于本项目
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/5 21:40
         */
        System.out.println("************************");
        System.out.println("软件名称：职工工资管理系统");
        System.out.println("学号：8008120413");
        System.out.println("姓名：董伟垣");
        System.out.println("班级：2014");
        System.out.println("完成时间：2021/6/6 15.35");
    }

    private void searchEmp() {
        /**
         * @description 查询数据
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/5 21:40
         */

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        int i = mapper.queryCount();

        if (i > 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("请选择查询方式：");
            System.out.println("1-按ID查询");
            System.out.println("2-按姓名查询");

            int n = scanner.nextInt();
            scanner.nextLine();
            boolean flag = true;
            while (flag) {
                switch (n) {
                    case 1:
                        System.out.println("请输入员工ID号");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Employee employee = mapper.getEmpbyId(id);
                        show(employee);
                        flag = false;
                        break;

                    case 2:
                        System.out.println("请输入员工姓名");
                        String name = scanner.nextLine();
                        Employee empty = mapper.getEmpbyName(name);
                        show(empty);
                        flag = false;
                        break;
                    default:
                        System.out.println("输入有误请重新输入");
                        break;
                }
            }


        } else {
            System.out.println("暂无数据，请先添加数据");
        }


        sqlSession.close();
        ReadKeyboard.readReturn();
    }

    private void show(Employee emp) {
        /**
         * @description 查询返回的数据
         * @param emp
         * @return void
         * @author JIADE
         * @date 2021/6/6 12:24
         */
        if (emp != null) {
            System.out.println("该员工的原信息：");
            System.out.println("ID号\t\t姓名\t\t基本工资\t\t职务工资\t\t津贴\t\t医疗保险\t\t公积金");
            System.out.println(emp.getId() + "\t\t" + emp.getName()
                    + "\t\t" + emp.getBp() + "\t\t" + emp.getPp() + "\t\t" + emp.getAllowance() + "\t\t" +
                    emp.getIns() + "\t\t" + emp.getCpf());

        } else {

            System.out.println("查无此人\n");
        }
    }

    private void updateEmp() {
        /**
         * @description 更新数据
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/5 21:40
         */
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        int i = mapper.queryCount();

        Scanner scanner = new Scanner(System.in);
        if (i > 0) {

            System.out.println("请输入要更改的员工ID");
            int id = scanner.nextInt();
            scanner.nextLine();

            Employee employee = mapper.getEmpbyId(id);

            if (employee != null) {
                System.out.println("该员工的原信息：");
                System.out.println("ID号\t\t姓名\t\t基本工资\t\t职务工资\t\t津贴\t\t医疗保险\t\t公积金");
                System.out.println(employee.getId() + "\t\t" + employee.getName()
                        + "\t\t" + employee.getBp() + "\t\t" + employee.getPp() + "\t\t" + employee.getAllowance() + "\t\t" +
                        employee.getIns() + "\t\t" + employee.getCpf());

                System.out.println("请输入新信息");

                int i1 = mapper.updateEmp(new Employee(Integer.parseInt(scanner.nextLine()), scanner.nextLine(),
                        scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));

                scanner.nextLine();
                System.out.println(i1);
                System.out.println("修改成功\n");
            }

        } else {
            System.out.println("暂无数据，请先添加数据\n");
        }

        sqlSession.commit();
        sqlSession.close();
        ReadKeyboard.readReturn();
    }

    private void showEmp() {

        /**
         * @description 显示数据
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/5 21:40
         */
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        int i = mapper.queryCount();

        if (i > 0) {

            System.out.println("ID号\t\t姓名\t\t基本工资\t\t职务工资\t\t津贴\t\t医疗保险\t\t公积金");

            List<Employee> employeeList = mapper.getEmployeeList();

            for (Employee employee : employeeList) {
                System.out.println(employee.getId() + "\t\t" + employee.getName()
                        + "\t\t" + employee.getBp() + "\t\t" + employee.getPp() + "\t\t" + employee.getAllowance() + "\t\t" +
                        employee.getIns() + "\t\t" + employee.getCpf());
            }

        } else {
            System.out.println("暂无数据，请先添加数据\n");
        }


        sqlSession.close();
        ReadKeyboard.readReturn();

    }

    private void addEmp() {

        /**
         * @description 增加数据
         * @param
         * @return void
         * @author JIADE
         * @date 2021/6/5 21:40
         */
        System.out.println("请输入职工的ID号、姓名以及基本工资、职务工资、岗位津贴、医疗保险、公积金");
        Scanner scanner = new Scanner(System.in);

        Employee employee = new Employee(Integer.parseInt(scanner.nextLine()), scanner.nextLine(),
                scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt());

        scanner.nextLine();
        System.out.println("请选择是否继续添加数据（Y/N）");
        char c = ReadKeyboard.readConfirmSelection();
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        while (c == 'Y') {
            System.out.println("请输入职工的ID号、姓名以及基本工资、职务工资、岗位津贴、医疗保险、公积金");
            employees.add(new Employee(Integer.parseInt(scanner.nextLine()), scanner.nextLine(),
                    scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));


            scanner.nextLine();
            System.out.println("请选择是否继续添加数据（Y/N）");
            c = ReadKeyboard.readConfirmSelection();

        }

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        mapper.insertEmpList(employees);

        sqlSession.commit();
        sqlSession.close();
        ReadKeyboard.readReturn();

    }
}

