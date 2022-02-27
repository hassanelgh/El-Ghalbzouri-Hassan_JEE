package InjectionDesDependences;

import Dao.DaoImpl;
import Dao.IDao;
import Metier.MetierImpl;

public class Statique {
    public static void main(String[] args) {
        IDao dao=new DaoImpl();
        MetierImpl metier=new MetierImpl();
        metier.setDao(dao);
        System.out.println("------->Statique");
        System.out.println(metier.calcule());
    }
}
