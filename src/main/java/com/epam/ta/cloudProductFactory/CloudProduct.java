package com.epam.ta.cloudProductFactory;

public abstract class CloudProduct {
    private String productType;
//    private int numberOfInstances;
//    private String operatingSystem;
//    private String provisioningModel;
//    private String series;
//    private String machineType;
//    private String gpuType;
//    private int numberOfGPU;
//    private String localSSD;
//    private String dataCenterLocation;
//    private String committedUsage;

//    public CloudProduct(String productType) {
//        this.productType = productType;
//        this.operatingSystem = operatingSystem;
//    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

  public CloudProduct setProductParameters() {
    return this;
  }

  public String calculateProductPrice() {
    return "";
  }
}
