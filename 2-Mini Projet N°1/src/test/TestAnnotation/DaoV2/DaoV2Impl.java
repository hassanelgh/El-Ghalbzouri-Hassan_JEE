package test.TestAnnotation.DaoV2;

public class DaoV2Impl implements IDaoV2 {

    public DaoV2Impl(){
        System.out.println("::>> create  DaoV2Impl");
    }
    @Override
    public double getT() {
        return 2;
    }
}
