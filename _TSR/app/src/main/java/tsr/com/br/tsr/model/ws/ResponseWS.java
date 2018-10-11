package tsr.com.br.tsr.model.ws;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vivianlo
 * Mapping the request return data
 */
public class ResponseWS {

    @SerializedName("data")
    private DataWS data;

    @SerializedName("errors")
    private String errors;

    public DataWS getData() {
        return data;
    }

    public void setData(DataWS data) {
        this.data = data;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public class DataWS {

        @SerializedName("errors")
        private String errors;

        @SerializedName("products")
        private List<ItemWS> products = new ArrayList<>();

        public String getErrors() {
            return errors;
        }

        public void setErrors(String errors) {
            this.errors = errors;
        }

        public List<ItemWS> getProducts() {
            return products;
        }

        public void setProducts(List<ItemWS> products) {
            this.products = products;
        }
    }

}
