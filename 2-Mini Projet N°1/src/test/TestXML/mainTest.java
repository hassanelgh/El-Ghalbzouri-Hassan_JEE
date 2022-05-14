package test.TestXML;

import FWID.byXML.FWIDContext;
import test.TestXML.Dao.DaoImpl;
import test.TestXML.Dao.IDao;
import test.TestXML.DaoV2.DaoV2Impl;
import test.TestXML.DaoV2.IDaoV2;
import test.TestXML.Metier.IMetier;
import test.TestXML.Metier.MetierImplConstructor;
import test.TestXML.Metier.MetierImplField;
import test.TestXML.Metier.MetierImplSetter;


public class mainTest {
    public static void main(String[] args) {

        FWIDContext fwidContext=new FWIDContext("tstXMLV2.xml");
        IDao dao= (DaoImpl) fwidContext.getInstance("a");
        IDao dao2= (DaoImpl) fwidContext.getInstance("b");
        IDaoV2 daoV2 = (DaoV2Impl) fwidContext.getInstance("c");

        IMetier metierImplConstructorV2=(MetierImplConstructor) fwidContext.getInstance("d");
        IMetier metierImplConstructorV1=(MetierImplConstructor) fwidContext.getInstance("k");
        IMetier metierImplField=(MetierImplField) fwidContext.getInstance("f");
        IMetier metierImplSetter=(MetierImplSetter) fwidContext.getInstance("e");

        System.out.println("-----------------------------------------------------");
        System.out.println(metierImplConstructorV1.calcule());
        System.out.println("-----------------------------------------------------");
        System.out.println(metierImplConstructorV2.calcule());
        System.out.println("-----------------------------------------------------");

        System.out.println(metierImplField.calcule());
        System.out.println("-----------------------------------------------------");

        System.out.println(metierImplSetter.calcule());
        System.out.println("-----------------------------------------------------");









    }
}
