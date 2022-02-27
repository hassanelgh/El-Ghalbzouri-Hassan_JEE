package InjectionDesDependences;

import Dao.IDao;
import Metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Dynamique {
    public static void main(String[] args) throws Exception {

        Scanner scanner=new Scanner(new File("config.txt"));
        String daoClassName=scanner.nextLine();
        Class cDao=Class.forName(daoClassName);
        IDao dao=(IDao) cDao.newInstance();


        String metierClassName=scanner.nextLine();
        Class cMetier=Class.forName(metierClassName);
        IMetier metier=(IMetier) cMetier.newInstance();

        Method methodSetDao=cMetier.getMethod("setDao",IDao.class);
        methodSetDao.invoke(metier,dao);

        System.out.println("------->Dynamique");
        System.out.println(metier.calcule());
    }
}
