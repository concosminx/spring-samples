package com.nimsoc.api.v1.mapper;

import com.nimsoc.api.v1.model.VendorDTO;
import com.nimsoc.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VendorMapper {

  VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

  VendorDTO vendorToVendorDTO(Vendor vendor);

  Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
