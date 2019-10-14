package dao;

import cn.itcast.jdbc.TxQueryRunner;
import domain.Customer;
import domain.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomerDao {
    private QueryRunner qr = new TxQueryRunner();

    public Customer find(String id) {
        try {
            String sql = "select * from t_customer where id=?";
            return qr.query(sql, new BeanHandler<Customer>(Customer.class), id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PageBean<Customer> query(Customer customer, int pc, int pr) {
        try {
            PageBean<Customer> pb = new PageBean<>();
            pb.setPc(pc);
            pb.getPr(pr);

            StringBuilder cntSql = new StringBuilder("select count(*) from t_customer ");
            StringBuilder whereSql = new StringBuilder(" where 1=1");
            List<Object> params = new ArrayList<>();

            String name = customer.getName();
            if(name != null && !name.trim().isEmpty()) {
                whereSql.append(" and name like ?");
                params.add("%" + name + "%");
            }

            String gender = customer.getGender();
            if(gender != null && !gender.trim().isEmpty()) {
                whereSql.append(" and gender like ?");
                params.add("%" + gender + "%");
            }

            String phone = customer.getPhone();
            if(phone != null && !phone.trim().isEmpty()) {
                whereSql.append(" and phone like ?");
                params.add("%" + phone + "%");
            }

            String email = customer.getEmail();
            if(email != null && !email.trim().isEmpty()) {
                whereSql.append(" and email like ?");
                params.add("%" + email + "%");
            }

            Number num = qr.query(cntSql.append(whereSql).toString(), new ScalarHandler<>(), params.toArray());
            int val = num.intValue();
            pb.setTr(val);

            StringBuilder sql = new StringBuilder("select * from t_customer ");
            StringBuilder lmtSql = new StringBuilder(" limit ?,?");

            params.add((pc - 1) * pr);
            params.add(pr);

            List<Customer> beanList = qr.query(sql.append(whereSql).append(lmtSql).toString(), new BeanListHandler<Customer>(Customer.class), params.toArray());
            pb.setBeanList(beanList);

            return pb;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PageBean<Customer> findAll(int pc, int pr) {
        try {
            PageBean<Customer> pb = new PageBean<>();
            pb.setPc(pc);
            pb.setPr(pr);

            String sql = "select count(*) from t_customer";
            Number num = qr.query(sql, new ScalarHandler<>());
            int val = num.intValue();
            pb.setTr(val);

            sql = "select * from t_customer order by name limit ?,?";
            Object[] params = {(pc - 1) * pr, pr};
            List<Customer> beanList = qr.query(sql, new BeanListHandler<Customer>(Customer.class), params);

            pb.setBeanList(beanList);
            return pb;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Customer c) {
        try {
            String sql = "insert into t_customer values(?, ?, ?, ?, ?, ?)";
            Object[] params = {c.getId(), c.getName(), c.getGender(), c.getPhone(), c.getEmail(), c.getDescription()};
            qr.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void edit(Customer customer) {
        try {
            String sql = "update t_customer set name=?, gender=?,phone=?,email=?,description=?";
            Object[] params = {customer.getName(), customer.getGender(), customer.getPhone(), customer.getEmail(), customer.getDescription()};
            qr.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        try {
            String sql = "delete from t_customer where id=?";
            qr.update(sql, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
