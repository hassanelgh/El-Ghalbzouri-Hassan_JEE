package Metier;

import Dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImplV2 implements IMetier {
    // couplage faible
    @Autowired
    @Qualifier("dao")
    IDao dao;

    public MetierImplV2(){

    }
    public MetierImplV2(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcule() {
        double data=dao.getData();
        double res=data*10;
        return res;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
