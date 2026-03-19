package com.cfs.BookMyShow.service;


import com.cfs.BookMyShow.dto.TheaterDto;
import com.cfs.BookMyShow.excepation.ResourceNotFoundException;
import com.cfs.BookMyShow.model.Theater;
import com.cfs.BookMyShow.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    private TheaterRepository theaterRepository;


    public TheaterDto createTheater(TheaterDto theaterDto)
    {
        Theater theater=mapToEntity(theaterDto);
        Theater savedTheater = theaterRepository.save(theater);
        return mapToDto(savedTheater);
    }

    public TheaterDto getTheaterById(Long id)
    {
        Theater theater=theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater not found with id: "+id));
        return mapToDto(theater);
    }

    public List<TheaterDto> getAllTheaters()
    {
        List<Theater> theaters=theaterRepository.findAll();
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TheaterDto> getAllTheaterByCity(String city)
    {
        List<Theater> theaters=theaterRepository.findByCity(city);
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    //update theater
    //delete theater

    private TheaterDto mapToDto(Theater theater) {
        TheaterDto theaterDto=new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setName(theater.getName());
        theaterDto.setCity(theater.getCity());
        theaterDto.setAddress(theater.getAddress());
        theaterDto.setTotalScreens(theater.getTotalScreens());
        return theaterDto;
    }

    private Theater mapToEntity(TheaterDto theaterDto) {
        Theater theater=new Theater();
        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreens(theaterDto.getTotalScreens());
        return theater;
    }
}
