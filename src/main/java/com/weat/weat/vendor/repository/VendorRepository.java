package com.weat.weat.vendor.repository;

import com.weat.weat.vendor.model.VendorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository  extends JpaRepository<VendorDetails, Integer> {

}
