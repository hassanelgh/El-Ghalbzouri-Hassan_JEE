package test.TestXML.Metier;

import test.TestXML.Dao.IDao;
import test.TestXML.DaoV2.IDaoV2;

public class MetierImplField implements IMetier {

    private IDao dao1;
    private IDao dao2;
    private IDaoV2 daoV2;

    public MetierImplField(){
        System.out.println("::>> create MetierImpl  version inject by Field");
    }



    @Override
    public double calcule() {
        System.out.println("::>> MetierImpl  version inject by Field");
        System.out.println("dao instance "+dao1.getValueOfi() +" = "+dao1.getData());
        System.out.println("dao instance "+dao2.getValueOfi() +" = "+dao2.getData());
        System.out.println("daoV2 instance  = "+daoV2.getT());
        return 2;
    }
}
