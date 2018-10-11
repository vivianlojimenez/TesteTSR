package tsr.com.br.tsr.model.ws;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author vivianlo
 * Mapping of products that are inside data
 */
public class ItemWS implements Serializable {

    @SerializedName("preservationFreezer")
    private Integer preservationFreezer;

    @SerializedName("energeticValue")
    private String energeticValue;

    @SerializedName("totalFat")
    private String totalFat;

    @SerializedName("quantity")
    private Integer quantity;

    @SerializedName("productId")
    private Long productId;

    @SerializedName("description")
    private String description;

    @SerializedName("weight")
    private Float weight;

    @SerializedName("priceMinimum")
    private Float priceMinimum;

    @SerializedName("enabled")
    private Boolean enabled;

    @SerializedName("url")
    private String url;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("size")
    private String size;

    @SerializedName("productCod")
    private String productCod;

    @SerializedName("preservationRefrigerator")
    private Integer preservationRefrigerator;

    @SerializedName("priceSuggested")
    private Float priceSuggested;

    @SerializedName("protein")
    private String protein;

    @SerializedName("name")
    private String name;

    @SerializedName("pricePromotional")
    private Float pricePromotional;

    public Integer getPreservationFreezer() {
        return preservationFreezer;
    }

    public void setPreservationFreezer(Integer preservationFreezer) {
        this.preservationFreezer = preservationFreezer;
    }

    public String getEnergeticValue() {
        return energeticValue;
    }

    public void setEnergeticValue(String energeticValue) {
        this.energeticValue = energeticValue;
    }

    public String getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(String totalFat) {
        this.totalFat = totalFat;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getPriceMinimum() {
        return priceMinimum;
    }

    public void setPriceMinimum(Float priceMinimum) {
        this.priceMinimum = priceMinimum;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductCod() {
        return productCod;
    }

    public void setProductCod(String productCod) {
        this.productCod = productCod;
    }

    public Integer getPreservationRefrigerator() {
        return preservationRefrigerator;
    }

    public void setPreservationRefrigerator(Integer preservationRefrigerator) {
        this.preservationRefrigerator = preservationRefrigerator;
    }

    public Float getPriceSuggested() {
        return priceSuggested;
    }

    public void setPriceSuggested(Float priceSuggested) {
        this.priceSuggested = priceSuggested;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPricePromotional() {
        return pricePromotional;
    }

    public void setPricePromotional(Float pricePromotional) {
        this.pricePromotional = pricePromotional;
    }
}
