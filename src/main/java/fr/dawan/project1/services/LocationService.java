package fr.dawan.project1.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.dawan.project1.dto.LocationDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocationService {

    List<LocationDto> findAll() throws Exception;
}
