package test.TestAnnotation.Metier;

import FWID.byAnnotation.Inject;
import FWID.byAnnotation.InjectType;
import test.TestAnnotation.Dao.IDao;
import test.TestAnnotation.DaoV2.IDaoV2;

public class MetierImplField implements IMetier {

    @Inject(name = "dao1")
    private IDao dao1;

    @Inject()
    private IDao dao2;

    @Inject()
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
