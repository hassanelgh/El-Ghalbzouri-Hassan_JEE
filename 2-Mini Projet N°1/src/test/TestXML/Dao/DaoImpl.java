package test.TestXML.Dao;

public class DaoImpl implements IDao {
    static public int i=0;
    private int valueOfi;

    public DaoImpl(){
        i++;
        valueOfi=i;
        System.out.println("::>> create  DaoImpl "+valueOfi);

    }

    public int getValueOfi() {
        return valueOfi;
    }

    @Override
    public double getData() {
        return valueOfi*10;
    }
}
