package test.TestAnnotation.Metier;

import FWID.byAnnotation.Inject;
import FWID.byAnnotation.InjectType;
import test.TestAnnotation.Dao.IDao;
import test.TestAnnotation.DaoV2.IDaoV2;

public class MetierImplSetter implements IMetier {

    private IDao dao1;

    private IDao dao2;
    private IDaoV2 daoV2;

    public MetierImplSetter(){
        System.out.println("::>> create MetierImpl  version inject by Setter");
    }

    @Override
    public double calcule() {
        System.out.println("::>> MetierImpl  version inject by Setter");
        System.out.println("dao instance "+dao1.getValueOfi() +" = "+dao1.getData());
        System.out.println("dao instance "+dao2.getValueOfi() +" = "+dao2.getData());
        System.out.println("daoV2 instance  = "+daoV2.getT());
        return 3;
    }

    public IDao getDao1() {
        return dao1;
    }

    @Inject(name = "dao1",value = InjectType.SINGLE_ONE,id = "id1")
    public void setDao1(IDao dao1) {
        this.dao1 = dao1;
    }

    public IDao getDao2() {
        return dao2;
    }

    @Inject(name = "dao1")
    public void setDao2(IDao dao2) {
        this.dao2 = dao2;
    }

    public IDaoV2 getDaoV2() {
        return daoV2;
    }

    @Inject()
    public void setDaoV2(IDaoV2 daoV2) {
        this.daoV2 = daoV2;
    }
}
