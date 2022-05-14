package test.TestAnnotation.Dao;

public class DaoImpl2 implements IDao {
    private int value;

    public DaoImpl2(){
        value=33;
        System.out.println("::>> create  DaoImpl2");

    }

    public int getValueOfi() {
        return value;
    }

    @Override
    public double getData() {
        return value*10;
    }
}
