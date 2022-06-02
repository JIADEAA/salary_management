package pojo;

/**
 * @author JIADE
 * @description Employee
 * @date 2021/6/5 14:22
 */
public class Employee {

    //职工的ID号
    int id;
    //    职工的姓名
    String name;
    //    基本工资
    int bp;
    //    职务工资
    int pp;
    //    岗位津贴
    int allowance;
    //    医疗保险
    int ins;
    //    公积金
    int cpf;
    //    总工资
    int all;


    public Employee(int id, String name, int bp, int pp, int allowance, int ins, int cpf, int all) {
        this.id = id;
        this.name = name;
        this.bp = bp;
        this.pp = pp;
        this.allowance = allowance;
        this.ins = ins;
        this.cpf = cpf;
        this.all = all;
    }

    public Employee(int id, String name, int bp, int pp, int allowance, int ins, int cpf) {
        this.id = id;
        this.name = name;
        this.bp = bp;
        this.pp = pp;
        this.allowance = allowance;
        this.ins = ins;
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bp='" + bp + '\'' +
                ", pp='" + pp + '\'' +
                ", allowance='" + allowance + '\'' +
                ", ins='" + ins + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getIns() {
        return ins;
    }

    public void setIns(int ins) {
        this.ins = ins;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public Employee() {
    }
}
