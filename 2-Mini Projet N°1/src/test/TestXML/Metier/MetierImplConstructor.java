package test.TestXML.Metier;

import test.TestXML.Dao.DaoImpl;
import test.TestXML.Dao.IDao;
import test.TestXML.DaoV2.IDaoV2;

public class MetierImplConstructor implements IMetier {

    private IDao dao1;
    private IDao dao2;
    private IDaoV2 daoV2;
    private int v;

    public MetierImplConstructor(IDao dao1,IDao dao2 ,IDaoV2 daoV2){
        this.dao1=dao1;
        this.dao2=dao2;
        this.daoV2=daoV2;
        v=1;
        System.out.println("::>> create  MetierImpl  version inject by constructor V1");
    }

    public MetierImplConstructor(IDao dao1 ,IDaoV2 daoV2,IDao dao2){
        this.dao1=dao1;
        this.dao2=dao2;
        this.daoV2=daoV2;

        v=2;
        System.out.println("::>> create  MetierImpl  version inject by constructor V2");
    }


    @Override
    public double calcule() {
        System.out.println("::>> MetierImpl  version inject by constructor V"+v);
        System.out.println("dao instance "+dao1.getValueOfi() +" = "+dao1.getData());
        System.out.println("dao instance "+dao2.getValueOfi() +" = "+dao2.getData());
        System.out.println("daoV2 instance  = "+daoV2.getT());
        return 1;
    }
}
