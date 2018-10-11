package tsr.com.br.tsr.controller;

/**
 * @author vivianlo
 * BO to deal with the business rule, specifically the badge in kart
 */
public class AppBO {

    private static AppBO instance;

    private int qtdProductsKart;

    private AppBO() {
        qtdProductsKart = 0;
    }

    public synchronized static AppBO getInstance() {

        if (instance == null) {
            instance = new AppBO();
        }

        return instance;
    }

    public int getQtdProductsKart() {
        return qtdProductsKart;
    }

    public void addProductsKart () {
        qtdProductsKart ++;
    }

    public void clearProductKart () {
        qtdProductsKart = 0;
    }

}
