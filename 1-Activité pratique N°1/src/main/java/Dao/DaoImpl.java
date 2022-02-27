package Dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("daoImp V1");
        double tmp=Math.random()*40;
        return tmp;
    }
}
