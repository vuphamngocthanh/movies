package com.admin.service.region;

import com.model.dto.RegionDto;
import com.model.entity.Region;
import com.repo.RegionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionService implements IRegionsService {
    private final RegionRepository regionRepository;
    private final ModelMapper modelMapper;

    public RegionService(RegionRepository regionRepository, ModelMapper modelMapper) {
        this.regionRepository = regionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<RegionDto> findAll() {
        List<Region> regionDtoList = regionRepository.findAll();
        return regionDtoList.parallelStream()
                .map(region -> modelMapper.map(region,RegionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RegionDto findById(Integer id) {
        return null;
    }

    @Override
    public void save(RegionDto regionDto) {

    }

    @Override
    public void remove(Integer id) {

    }
}
