package com.weat.weat.vendor.model;

import com.weat.weat.vendor.dto.AccountNumberDetailsDto;
import com.weat.weat.vendor.dto.InventoryDto;
import com.weat.weat.vendor.dto.MobileNumberDto;
import com.weat.weat.vendor.dto.VendorDetailsDto;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Table(name ="vendor_details")
public class VendorDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "vendor_name")
    private String vendorName;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "pan")
    private Integer pan;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private double latitude;

    @OneToMany// unidectional
    @JoinColumn(name = "mobile_id")// mobile_fk
    private List<MobileNumber> mobileNumberList;

    @OneToOne
    @JoinColumn(name = "store_id")
    private StoreAddress storeAddress;

    @OneToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToMany
    @JoinColumn(name = "account_id")
    private List<AccountNumberDetails> accountNumberDetails;

    /**
     *vdto;
     * VendorDetails vdt = new VendorDetails(vdto)
     *
     * @param vendorDetailsDto
     */

    public VendorDetails(VendorDetailsDto vendorDetailsDto){
        this.setVendorName(vendorDetailsDto.getVendorName());
        this.setStoreName(vendorDetailsDto.getStoreName());
        this.setPan(vendorDetailsDto.getPan());
        this.setLongitude(vendorDetailsDto.getLongitude());
        this.setLatitude(vendorDetailsDto.getLatitude());

        List<MobileNumber> mobileNumberObjectList = new ArrayList<MobileNumber>();

        for (MobileNumberDto mob : vendorDetailsDto.getMobileNumberListdto()){
            MobileNumber mobileNumberSingleObject = new MobileNumber(mob);
            mobileNumberObjectList.add(mobileNumberSingleObject);
        }
        this.setMobileNumberList(mobileNumberObjectList);

        List<AccountNumberDetails> accountNumberDetailsObjectsList = new ArrayList<>();

        for (AccountNumberDetailsDto acc: vendorDetailsDto.getAccountNumberDetailsDto()){
            AccountNumberDetails accountNumberDetails = new AccountNumberDetails(acc);
            accountNumberDetailsObjectsList.add(accountNumberDetails);
        }
        this.setAccountNumberDetails(accountNumberDetailsObjectsList);
        this.setStoreAddress(new StoreAddress(vendorDetailsDto.getStoreAddressdto()));
        this.setInventory(new Inventory(vendorDetailsDto.getInventoryDto()));

    }

    public VendorDetails() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getPan() {
        return pan;
    }

    public void setPan(Integer pan) {
        this.pan = pan;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<MobileNumber> getMobileNumberList() {
        return mobileNumberList;
    }

    public void setMobileNumberList(List<MobileNumber> mobileNumberList) {
        this.mobileNumberList = mobileNumberList;
    }

    public StoreAddress getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(StoreAddress storeAddress) {
        this.storeAddress = storeAddress;

    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<AccountNumberDetails> getAccountNumberDetails() {
        return accountNumberDetails;
    }

    public void setAccountNumberDetails(List<AccountNumberDetails> accountNumberDetails) {
        this.accountNumberDetails = accountNumberDetails;
    }
}
