package com.admin.service.region;

import com.admin.service.IGeneralService;
import com.model.dto.RegionDto;
import com.model.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegionsService extends IGeneralService<RegionDto> {
}
