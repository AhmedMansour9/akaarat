package com.akaarat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Units {



    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("unittypeid")
    @Expose
    private String unittypeid;
    @SerializedName("floorNo")
    @Expose
    private Integer floorNo;
    @SerializedName("noOfroom")
    @Expose
    private Integer noOfroom;
    @SerializedName("annualrent")
    @Expose
    private Integer annualrent;
    @SerializedName("monthlyRent")
    @Expose
    private Object monthlyRent;
    @SerializedName("electricityCounterNo")
    @Expose
    private Object electricityCounterNo;
    @SerializedName("electricityAnnualrent")
    @Expose
    private Object electricityAnnualrent;
    @SerializedName("electricitymonthlyrent")
    @Expose
    private Object electricitymonthlyrent;
    @SerializedName("requiredInsuranceValue")
    @Expose
    private Integer requiredInsuranceValue;
    @SerializedName("areasize")
    @Expose
    private String areasize;
    @SerializedName("bordereast")
    @Expose
    private Object bordereast;
    @SerializedName("bordewest")
    @Expose
    private Object bordewest;
    @SerializedName("bordersouth")
    @Expose
    private Object bordersouth;
    @SerializedName("bordernorth")
    @Expose
    private Object bordernorth;
    @SerializedName("purchaseingPrice")
    @Expose
    private Object purchaseingPrice;
    @SerializedName("purchasingdate")
    @Expose
    private Object purchasingdate;
    @SerializedName("costPrice")
    @Expose
    private Object costPrice;
    @SerializedName("marketprice")
    @Expose
    private Object marketprice;
    @SerializedName("sellingprice")
    @Expose
    private String sellingprice;
    @SerializedName("numberOfshare")
    @Expose
    private String numberOfshare;
    @SerializedName("shareAria")
    @Expose
    private String shareAria;
    @SerializedName("countOfSoldShare")
    @Expose
    private String countOfSoldShare;
    @SerializedName("purchasingSharePrice")
    @Expose
    private Object purchasingSharePrice;
    @SerializedName("sharePriceCost")
    @Expose
    private Object sharePriceCost;
    @SerializedName("sellingSharePrice")
    @Expose
    private String sellingSharePrice;
    @SerializedName("minSellingSharePrice")
    @Expose
    private Object minSellingSharePrice;
    @SerializedName("plannedId")
    @Expose
    private Object plannedId;
    @SerializedName("waterAnnualrent")
    @Expose
    private Object waterAnnualrent;
    @SerializedName("waterMonthlylrent")
    @Expose
    private Object waterMonthlylrent;
    @SerializedName("propertyAge")
    @Expose
    private Object propertyAge;
    @SerializedName("ownercardnumber")
    @Expose
    private Object ownercardnumber;
    @SerializedName("locationLang")
    @Expose
    private String locationLang;
    @SerializedName("locationLatit")
    @Expose
    private String locationLatit;
    @SerializedName("ownerPhone")
    @Expose
    private Object ownerPhone;
    @SerializedName("ownerName")
    @Expose
    private Object ownerName;
    @SerializedName("viewTypeId")
    @Expose
    private Object viewTypeId;
    @SerializedName("unitName")
    @Expose
    private String unitName;
    @SerializedName("officeaccountid")
    @Expose
    private Integer officeaccountid;
    @SerializedName("owneraccountid")
    @Expose
    private Integer owneraccountid;
    @SerializedName("waterAccoountId")
    @Expose
    private Object waterAccoountId;
    @SerializedName("ownerId")
    @Expose
    private Integer ownerId;
    @SerializedName("locationdescription")
    @Expose
    private String locationdescription;
    @SerializedName("washrooms")
    @Expose
    private Object washrooms;
    @SerializedName("electricitymeter")
    @Expose
    private Object electricitymeter;
    @SerializedName("customgaragecount")
    @Expose
    private Object customgaragecount;
    @SerializedName("subgaargecount")
    @Expose
    private Object subgaargecount;
    @SerializedName("insuranceRatio")
    @Expose
    private Object insuranceRatio;
    @SerializedName("tenantId")
    @Expose
    private Object tenantId;
    @SerializedName("officeid")
    @Expose
    private String officeid;
    @SerializedName("purposetype")
    @Expose
    private Integer purposetype;
    @SerializedName("paymentcash")
    @Expose
    private Object paymentcash;
    @SerializedName("paymentterm")
    @Expose
    private Object paymentterm;
    @SerializedName("paymentinstallment")
    @Expose
    private Object paymentinstallment;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("parentid")
    @Expose
    private Object parentid;
    @SerializedName("unitdescription")
    @Expose
    private String unitdescription;
    @SerializedName("pathroomcount")
    @Expose
    private Integer pathroomcount;
    @SerializedName("countoffloors")
    @Expose
    private Object countoffloors;
    @SerializedName("height")
    @Expose
    private Object height;
    @SerializedName("enablebid")
    @Expose
    private String enablebid;
    @SerializedName("enabledivision")
    @Expose
    private String enabledivision;
    @SerializedName("lastbidprice")
    @Expose
    private String lastbidprice;
    @SerializedName("biddate")
    @Expose
    private Object biddate;
    @SerializedName("buildingno")
    @Expose
    private Integer buildingno;
    @SerializedName("propertycount")
    @Expose
    private Object propertycount;
    @SerializedName("securitymanid")
    @Expose
    private Object securitymanid;
    @SerializedName("garage")
    @Expose
    private Boolean garage;
    @SerializedName("vehiclescount")
    @Expose
    private Object vehiclescount;
    @SerializedName("garagearea")
    @Expose
    private Object garagearea;
    @SerializedName("defaultimg")
    @Expose
    private String defaultimg;
    @SerializedName("isgrouped")
    @Expose
    private Object isgrouped;
    @SerializedName("unitid")
    @Expose
    private Object unitid;
    @SerializedName("ratingcount")
    @Expose
    private Object ratingcount;
    @SerializedName("countOfRatingUsers")
    @Expose
    private Object countOfRatingUsers;
    @SerializedName("unittype")
    @Expose
    private Units_Details unittype;
    @SerializedName("accedent")
    @Expose
    private List<Object> accedent = null;
    @SerializedName("accountingTransactions")
    @Expose
    private List<Object> accountingTransactions = null;
    @SerializedName("contract")
    @Expose
    private List<Object> contract = null;
    @SerializedName("definedtaxe")
    @Expose
    private List<Object> definedtaxe = null;
    @SerializedName("maintainenceRequest")
    @Expose
    private List<Object> maintainenceRequest = null;
    @SerializedName("rating")
    @Expose
    private List<Object> rating = null;
    @SerializedName("realEstateCatchReceipt")
    @Expose
    private List<Object> realEstateCatchReceipt = null;
    @SerializedName("realEstateSanadSarf")
    @Expose
    private List<Object> realEstateSanadSarf = null;
    @SerializedName("unitBooking")
    @Expose
    private List<Object> unitBooking = null;
    @SerializedName("unitPriceOffer")
    @Expose
    private List<Object> unitPriceOffer = null;
    @SerializedName("unitprice")
    @Expose
    private List<Object> unitprice = null;
    @SerializedName("unitreceving")
    @Expose
    private List<Object> unitreceving = null;
    @SerializedName("unitservice")
    @Expose
    private List<Object> unitservice = null;
    @SerializedName("unitshareowner")
    @Expose
    private List<Object> unitshareowner = null;

    @SerializedName("createdat")
    @Expose
    private String createdat;

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnittypeid() {
        return unittypeid;
    }

    public void setUnittypeid(String unittypeid) {
        this.unittypeid = unittypeid;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Integer getNoOfroom() {
        return noOfroom;
    }

    public void setNoOfroom(Integer noOfroom) {
        this.noOfroom = noOfroom;
    }

    public Integer getAnnualrent() {
        return annualrent;
    }

    public void setAnnualrent(Integer annualrent) {
        this.annualrent = annualrent;
    }

    public Object getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Object monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public Object getElectricityCounterNo() {
        return electricityCounterNo;
    }

    public void setElectricityCounterNo(Object electricityCounterNo) {
        this.electricityCounterNo = electricityCounterNo;
    }

    public Object getElectricityAnnualrent() {
        return electricityAnnualrent;
    }

    public void setElectricityAnnualrent(Object electricityAnnualrent) {
        this.electricityAnnualrent = electricityAnnualrent;
    }

    public Object getElectricitymonthlyrent() {
        return electricitymonthlyrent;
    }

    public void setElectricitymonthlyrent(Object electricitymonthlyrent) {
        this.electricitymonthlyrent = electricitymonthlyrent;
    }

    public Integer getRequiredInsuranceValue() {
        return requiredInsuranceValue;
    }

    public void setRequiredInsuranceValue(Integer requiredInsuranceValue) {
        this.requiredInsuranceValue = requiredInsuranceValue;
    }

    public String getAreasize() {
        return areasize;
    }

    public void setAreasize(String areasize) {
        this.areasize = areasize;
    }

    public Object getBordereast() {
        return bordereast;
    }

    public void setBordereast(Object bordereast) {
        this.bordereast = bordereast;
    }

    public Object getBordewest() {
        return bordewest;
    }

    public void setBordewest(Object bordewest) {
        this.bordewest = bordewest;
    }

    public Object getBordersouth() {
        return bordersouth;
    }

    public void setBordersouth(Object bordersouth) {
        this.bordersouth = bordersouth;
    }

    public Object getBordernorth() {
        return bordernorth;
    }

    public void setBordernorth(Object bordernorth) {
        this.bordernorth = bordernorth;
    }

    public Object getPurchaseingPrice() {
        return purchaseingPrice;
    }

    public void setPurchaseingPrice(Object purchaseingPrice) {
        this.purchaseingPrice = purchaseingPrice;
    }

    public Object getPurchasingdate() {
        return purchasingdate;
    }

    public void setPurchasingdate(Object purchasingdate) {
        this.purchasingdate = purchasingdate;
    }

    public Object getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Object costPrice) {
        this.costPrice = costPrice;
    }

    public Object getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(Object marketprice) {
        this.marketprice = marketprice;
    }

    public String getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(String sellingprice) {
        this.sellingprice = sellingprice;
    }

    public String getNumberOfshare() {
        return numberOfshare;
    }

    public void setNumberOfshare(String numberOfshare) {
        this.numberOfshare = numberOfshare;
    }

    public String getShareAria() {
        return shareAria;
    }

    public void setShareAria(String shareAria) {
        this.shareAria = shareAria;
    }

    public String getCountOfSoldShare() {
        return countOfSoldShare;
    }

    public void setCountOfSoldShare(String countOfSoldShare) {
        this.countOfSoldShare = countOfSoldShare;
    }

    public Object getPurchasingSharePrice() {
        return purchasingSharePrice;
    }

    public void setPurchasingSharePrice(Object purchasingSharePrice) {
        this.purchasingSharePrice = purchasingSharePrice;
    }

    public Object getSharePriceCost() {
        return sharePriceCost;
    }

    public void setSharePriceCost(Object sharePriceCost) {
        this.sharePriceCost = sharePriceCost;
    }

    public String getSellingSharePrice() {
        return sellingSharePrice;
    }

    public void setSellingSharePrice(String sellingSharePrice) {
        this.sellingSharePrice = sellingSharePrice;
    }

    public Object getMinSellingSharePrice() {
        return minSellingSharePrice;
    }

    public void setMinSellingSharePrice(Object minSellingSharePrice) {
        this.minSellingSharePrice = minSellingSharePrice;
    }

    public Object getPlannedId() {
        return plannedId;
    }

    public void setPlannedId(Object plannedId) {
        this.plannedId = plannedId;
    }

    public Object getWaterAnnualrent() {
        return waterAnnualrent;
    }

    public void setWaterAnnualrent(Object waterAnnualrent) {
        this.waterAnnualrent = waterAnnualrent;
    }

    public Object getWaterMonthlylrent() {
        return waterMonthlylrent;
    }

    public void setWaterMonthlylrent(Object waterMonthlylrent) {
        this.waterMonthlylrent = waterMonthlylrent;
    }

    public Object getPropertyAge() {
        return propertyAge;
    }

    public void setPropertyAge(Object propertyAge) {
        this.propertyAge = propertyAge;
    }

    public Object getOwnercardnumber() {
        return ownercardnumber;
    }

    public void setOwnercardnumber(Object ownercardnumber) {
        this.ownercardnumber = ownercardnumber;
    }

    public String getLocationLang() {
        return locationLang;
    }

    public void setLocationLang(String locationLang) {
        this.locationLang = locationLang;
    }

    public String getLocationLatit() {
        return locationLatit;
    }

    public void setLocationLatit(String locationLatit) {
        this.locationLatit = locationLatit;
    }

    public Object getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(Object ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public Object getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(Object ownerName) {
        this.ownerName = ownerName;
    }

    public Object getViewTypeId() {
        return viewTypeId;
    }

    public void setViewTypeId(Object viewTypeId) {
        this.viewTypeId = viewTypeId;
    }

    public String getUnitNamear() {
        return unitName;
    }

    public void setUnitNamear(String unitNamear) {
        this.unitName = unitNamear;
    }

    public Integer getOfficeaccountid() {
        return officeaccountid;
    }

    public void setOfficeaccountid(Integer officeaccountid) {
        this.officeaccountid = officeaccountid;
    }

    public Integer getOwneraccountid() {
        return owneraccountid;
    }

    public void setOwneraccountid(Integer owneraccountid) {
        this.owneraccountid = owneraccountid;
    }

    public Object getWaterAccoountId() {
        return waterAccoountId;
    }

    public void setWaterAccoountId(Object waterAccoountId) {
        this.waterAccoountId = waterAccoountId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getLocationdescription() {
        return locationdescription;
    }

    public void setLocationdescription(String locationdescription) {
        this.locationdescription = locationdescription;
    }

    public Object getWashrooms() {
        return washrooms;
    }

    public void setWashrooms(Object washrooms) {
        this.washrooms = washrooms;
    }

    public Object getElectricitymeter() {
        return electricitymeter;
    }

    public void setElectricitymeter(Object electricitymeter) {
        this.electricitymeter = electricitymeter;
    }

    public Object getCustomgaragecount() {
        return customgaragecount;
    }

    public void setCustomgaragecount(Object customgaragecount) {
        this.customgaragecount = customgaragecount;
    }

    public Object getSubgaargecount() {
        return subgaargecount;
    }

    public void setSubgaargecount(Object subgaargecount) {
        this.subgaargecount = subgaargecount;
    }

    public Object getInsuranceRatio() {
        return insuranceRatio;
    }

    public void setInsuranceRatio(Object insuranceRatio) {
        this.insuranceRatio = insuranceRatio;
    }

    public Object getTenantId() {
        return tenantId;
    }

    public void setTenantId(Object tenantId) {
        this.tenantId = tenantId;
    }

    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid;
    }

    public Integer getPurposetype() {
        return purposetype;
    }

    public void setPurposetype(Integer purposetype) {
        this.purposetype = purposetype;
    }

    public Object getPaymentcash() {
        return paymentcash;
    }

    public void setPaymentcash(Object paymentcash) {
        this.paymentcash = paymentcash;
    }

    public Object getPaymentterm() {
        return paymentterm;
    }

    public void setPaymentterm(Object paymentterm) {
        this.paymentterm = paymentterm;
    }

    public Object getPaymentinstallment() {
        return paymentinstallment;
    }

    public void setPaymentinstallment(Object paymentinstallment) {
        this.paymentinstallment = paymentinstallment;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getParentid() {
        return parentid;
    }

    public void setParentid(Object parentid) {
        this.parentid = parentid;
    }

    public String getUnitdescription() {
        return unitdescription;
    }

    public void setUnitdescription(String unitdescription) {
        this.unitdescription = unitdescription;
    }

    public Integer getPathroomcount() {
        return pathroomcount;
    }

    public void setPathroomcount(Integer pathroomcount) {
        this.pathroomcount = pathroomcount;
    }

    public Object getCountoffloors() {
        return countoffloors;
    }

    public void setCountoffloors(Object countoffloors) {
        this.countoffloors = countoffloors;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public String getEnablebid() {
        return enablebid;
    }

    public void setEnablebid(String enablebid) {
        this.enablebid = enablebid;
    }

    public String getEnabledivision() {
        return enabledivision;
    }

    public void setEnabledivision(String enabledivision) {
        this.enabledivision = enabledivision;
    }

    public String getLastbidprice() {
        return lastbidprice;
    }

    public void setLastbidprice(String lastbidprice) {
        this.lastbidprice = lastbidprice;
    }

    public Object getBiddate() {
        return biddate;
    }

    public void setBiddate(Object biddate) {
        this.biddate = biddate;
    }

    public Integer getBuildingno() {
        return buildingno;
    }

    public void setBuildingno(Integer buildingno) {
        this.buildingno = buildingno;
    }

    public Object getPropertycount() {
        return propertycount;
    }

    public void setPropertycount(Object propertycount) {
        this.propertycount = propertycount;
    }

    public Object getSecuritymanid() {
        return securitymanid;
    }

    public void setSecuritymanid(Object securitymanid) {
        this.securitymanid = securitymanid;
    }

    public Boolean getGarage() {
        return garage;
    }

    public void setGarage(Boolean garage) {
        this.garage = garage;
    }

    public Object getVehiclescount() {
        return vehiclescount;
    }

    public void setVehiclescount(Object vehiclescount) {
        this.vehiclescount = vehiclescount;
    }

    public Object getGaragearea() {
        return garagearea;
    }

    public void setGaragearea(Object garagearea) {
        this.garagearea = garagearea;
    }

    public String getDefaultimg() {
        return defaultimg;
    }

    public void setDefaultimg(String defaultimg) {
        this.defaultimg = defaultimg;
    }

    public Object getIsgrouped() {
        return isgrouped;
    }

    public void setIsgrouped(Object isgrouped) {
        this.isgrouped = isgrouped;
    }

    public Object getUnitid() {
        return unitid;
    }

    public void setUnitid(Object unitid) {
        this.unitid = unitid;
    }

    public Object getRatingcount() {
        return ratingcount;
    }

    public void setRatingcount(Object ratingcount) {
        this.ratingcount = ratingcount;
    }

    public Object getCountOfRatingUsers() {
        return countOfRatingUsers;
    }

    public void setCountOfRatingUsers(Object countOfRatingUsers) {
        this.countOfRatingUsers = countOfRatingUsers;
    }

    public Units_Details getUnittype() {
        return unittype;
    }

    public void setUnittype(Units_Details unittype) {
        this.unittype = unittype;
    }

    public List<Object> getAccedent() {
        return accedent;
    }

    public void setAccedent(List<Object> accedent) {
        this.accedent = accedent;
    }

    public List<Object> getAccountingTransactions() {
        return accountingTransactions;
    }

    public void setAccountingTransactions(List<Object> accountingTransactions) {
        this.accountingTransactions = accountingTransactions;
    }

    public List<Object> getContract() {
        return contract;
    }

    public void setContract(List<Object> contract) {
        this.contract = contract;
    }

    public List<Object> getDefinedtaxe() {
        return definedtaxe;
    }

    public void setDefinedtaxe(List<Object> definedtaxe) {
        this.definedtaxe = definedtaxe;
    }

    public List<Object> getMaintainenceRequest() {
        return maintainenceRequest;
    }

    public void setMaintainenceRequest(List<Object> maintainenceRequest) {
        this.maintainenceRequest = maintainenceRequest;
    }

    public List<Object> getRating() {
        return rating;
    }

    public void setRating(List<Object> rating) {
        this.rating = rating;
    }

    public List<Object> getRealEstateCatchReceipt() {
        return realEstateCatchReceipt;
    }

    public void setRealEstateCatchReceipt(List<Object> realEstateCatchReceipt) {
        this.realEstateCatchReceipt = realEstateCatchReceipt;
    }

    public List<Object> getRealEstateSanadSarf() {
        return realEstateSanadSarf;
    }

    public void setRealEstateSanadSarf(List<Object> realEstateSanadSarf) {
        this.realEstateSanadSarf = realEstateSanadSarf;
    }

    public List<Object> getUnitBooking() {
        return unitBooking;
    }

    public void setUnitBooking(List<Object> unitBooking) {
        this.unitBooking = unitBooking;
    }

    public List<Object> getUnitPriceOffer() {
        return unitPriceOffer;
    }

    public void setUnitPriceOffer(List<Object> unitPriceOffer) {
        this.unitPriceOffer = unitPriceOffer;
    }

    public List<Object> getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(List<Object> unitprice) {
        this.unitprice = unitprice;
    }

    public List<Object> getUnitreceving() {
        return unitreceving;
    }

    public void setUnitreceving(List<Object> unitreceving) {
        this.unitreceving = unitreceving;
    }

    public List<Object> getUnitservice() {
        return unitservice;
    }

    public void setUnitservice(List<Object> unitservice) {
        this.unitservice = unitservice;
    }

    public List<Object> getUnitshareowner() {
        return unitshareowner;
    }

    public void setUnitshareowner(List<Object> unitshareowner) {
        this.unitshareowner = unitshareowner;
    }


}
