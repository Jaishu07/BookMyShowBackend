package com.cfs.BookMyShow.service;

import com.cfs.BookMyShow.dto.*;
import com.cfs.BookMyShow.excepation.ResourceNotFoundException;
import com.cfs.BookMyShow.model.*;
import com.cfs.BookMyShow.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private SeatRepository seatRepository;

    public ShowDto creteShow(ShowDto showDto)
    {
        Show show=new Show();
        Movie movie=movieRepository.findById(showDto.getMovie().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Movie Not Found"));

        Screen screen=screenRepository.findById(showDto.getScreen().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Screen Not Found"));

        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(showDto.getStartTime());
        show.setEndTime(showDto.getEndTime());

        Show savedShow=showRepository.save(show);

        List<ShowSeat> availableSeats=
                showSeatRepository.findByShowIdAndStatus(savedShow.getId(),"AVAILABLE");
        return mapToDto(savedShow,availableSeats);
    }

    public ShowDto getShowById(Long id)
    {
        Show show=showRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Show not found  with id: "+id));
        List<ShowSeat> availableSeats=
                showSeatRepository.findByShowIdAndStatus(show.getId(),"AVAILABLE");
        return mapToDto(show,availableSeats);
    }

    public List<ShowDto> getAllShows()
    {
        List<Show> shows=showRepository.findAll();
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepository.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show,availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowsByMovie(Long movieId)
    {
        List<Show> shows=showRepository.findByMovieId(movieId);
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepository.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show,availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowsByMovieAndCity(Long movieId,String city)
    {
        List<Show> shows=showRepository.findByMovie_IdAndScreen_Theater_City(movieId,city);
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepository.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show,availableSeats);
                })
                .collect(Collectors.toList());
    }

    public List<ShowDto> getShowsByDateRange(LocalDateTime startDate, LocalDateTime endDate)
    {
        List<Show> shows=showRepository.findByStartTimeBetween(startDate,endDate);
        return shows.stream()
                .map(show -> {
                    List<ShowSeat> availableSeats = showSeatRepository.findByShowIdAndStatus(show.getId(), "AVAILABLE");
                    return mapToDto(show,availableSeats);
                })
                .collect(Collectors.toList());
    }

    private ShowDto mapToDto(Show show,List<ShowSeat> availableSeats)
    {
        ShowDto showDto= new ShowDto();
        showDto.setId(show.getId());
        showDto.setStartTime(show.getStartTime());
        showDto.setEndTime(show.getEndTime());

        showDto.setMovie(new MovieDto(
                show.getMovie().getId(),
                show.getMovie().getTitle(),
                show.getMovie().getDescription(),
                show.getMovie().getLanguage(),
                show.getMovie().getGenre(),
                show.getMovie().getDurationMins(),
                show.getMovie().getReleaseDate(),
                show.getMovie().getPosterUrl()
        ));

        TheaterDto theaterDto=new TheaterDto(
                show.getScreen().getTheater().getId(),
                show.getScreen().getTheater().getName(),
                show.getScreen().getTheater().getAddress(),
                show.getScreen().getTheater().getCity(),
                show.getScreen().getTheater().getTotalScreens()
        );

        showDto.setScreen(new ScreenDto(
                show.getScreen().getId(),
                show.getScreen().getName(),
                show.getScreen().getTotalSeats(),
                theaterDto
        ));

        List<ShowSeatDto> seatDtos= availableSeats.stream()
                .map(seat->{
                    ShowSeatDto seatDto=new ShowSeatDto();
                    seatDto.setId(seat.getId());
                    seatDto.setStatus(seat.getStatus());
                    seatDto.setPrice(seat.getPrice());

                    SeatDto baseSeatDto=new SeatDto();
                    baseSeatDto.setId(seat.getSeat().getId());
                    baseSeatDto.setSeatNumber(seat.getSeat().getSeatNumber());
                    baseSeatDto.setSeatType(seat.getSeat().getSeatType());
                    baseSeatDto.setBasePrice(seat.getSeat().getBasePrice());
                    seatDto.setSeat(baseSeatDto);
                    return seatDto;
                })
                .collect(Collectors.toList());

        showDto.setAvailableSeats(seatDtos);
        return showDto;
    }

    public ShowDto createShow(ShowDto dto) {

        // 🔹 1. Validate input
        if (dto.getMovie() == null || dto.getMovie().getId() == null) {
            throw new IllegalArgumentException("Movie ID is required");
        }

        if (dto.getScreen() == null || dto.getScreen().getId() == null) {
            throw new IllegalArgumentException("Screen ID is required");
        }

        // 🔹 2. Fetch Movie
        Movie movie = movieRepository.findById(dto.getMovie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));

        // 🔹 3. Fetch Screen
        Screen screen = screenRepository.findById(dto.getScreen().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Screen not found"));

        // 🔹 4. Create Show
        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(dto.getStartTime());
        show.setEndTime(dto.getEndTime());

        Show savedShow = showRepository.save(show);

        // 🔹 5. Create ShowSeats (IMPORTANT 🔥)
        List<Seat> seats = seatRepository.findByScreenId(screen.getId());

        List<ShowSeat> showSeats = seats.stream().map(seat -> {
            ShowSeat ss = new ShowSeat();
            ss.setShow(savedShow);
            ss.setSeat(seat);
            ss.setPrice(seat.getBasePrice());
            ss.setStatus("AVAILABLE");
            return ss;
        }).toList();

        showSeatRepository.saveAll(showSeats);

        // 🔹 6. Prepare Response DTO
        ShowDto response = new ShowDto();
        response.setId(savedShow.getId());
        response.setStartTime(savedShow.getStartTime());
        response.setEndTime(savedShow.getEndTime());

        // Movie DTO
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        response.setMovie(movieDto);

        // Screen DTO
        ScreenDto screenDto = new ScreenDto();
        screenDto.setId(screen.getId());
        screenDto.setName(screen.getName());
        response.setScreen(screenDto);

        // Seats DTO (optional)
        List<ShowSeatDto> seatDtos = showSeats.stream().map(ss -> {
            ShowSeatDto s = new ShowSeatDto();
            s.setId(ss.getId());
            s.setPrice(ss.getPrice());
            s.setStatus(ss.getStatus());
            return s;
        }).toList();

        response.setAvailableSeats(seatDtos);

        return response;
    }
}
