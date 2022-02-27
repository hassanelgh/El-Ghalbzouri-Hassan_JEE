package Metier;

import Dao.IDao;

public class MetierImpl implements IMetier {
    // couplage faible
    IDao dao;

    public MetierImpl(){

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
