package com.admin.service.episode;

import com.admin.service.region.IRegionsService;
import com.model.dto.EpisodeDto;
import com.model.entity.Episode;
import com.repo.EpisodeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeService implements IEpisodeService{
    private final ModelMapper modelMapper;
    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository, ModelMapper modelMapper) {
        this.episodeRepository = episodeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Iterable<EpisodeDto> findAll() {
        List<Episode> episodeList = episodeRepository.findAll();
        return episodeList.parallelStream()
                .map(episode -> modelMapper.map(episodeList,EpisodeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EpisodeDto findById(Integer id) {
        return null;
    }

    @Override
    public void save(EpisodeDto episodeDto) {

    }

    @Override
    public void remove(Integer id) {

    }
}
