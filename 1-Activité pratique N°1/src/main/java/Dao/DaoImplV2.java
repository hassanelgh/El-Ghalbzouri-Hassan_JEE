package Dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImplV2 implements IDao {
    @Override
    public double getData() {
        System.out.println("daoImp V2");
        double tmp=Math.random()*10;
        return tmp;
    }
}
