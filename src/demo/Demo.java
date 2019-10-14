package demo;

import cn.itcast.commons.CommonUtils;
import dao.CustomerDao;
import domain.Customer;

public class Demo {
    public static void main(String[] args) {
        CustomerDao customerDao = new CustomerDao();
        for(int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setId(CommonUtils.uuid());
            customer.setName("customer" + i);
            customer.setGender(i % 2 == 0 ? "male" : "female");
            customer.setPhone("12345" + i);
            customer.setEmail("customer" + i + "@163.com");
            customer.setDescription("hello world");
            customerDao.add(customer);
        }
        System.out.print("success");
    }
}
