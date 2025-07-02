package fr.dawan.project1.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.project1.dto.LocationDto;
import fr.dawan.project1.services.LocationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public LocationServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<LocationDto> findAll() throws Exception {
        String url ="https://dawan.org/public/location";
        //getForEntity ne permet pas d'envoyer des entêtes
        //ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);


        //Solution 2 : utiliser la méthode Exchange qui permet en plus de passer des entêtes dans la requête
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("X-AUTH-API","votre clé API"); //si clé d'API
        //ou
        // headers.add("AUTHORIZATION","bearer votre jeton de connexion");

        //Headers en utilisant une multivaluemap au lieu d'u HttpHeaders
        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        //headers.put("X-AUTH-API", Arrays.asList("votre clé API"));
        //ou
        // headers.put("AUTHORIZATION",Arrays.asList("bearer votre jeton de connexion"));

        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);



        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> result =restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);



        if(result.getStatusCode()== HttpStatus.OK){
            LocationDto[] r = objectMapper.readValue(result.getBody(),LocationDto[].class);
            return Arrays.asList(r);
        }
        throw new Exception("Error on retrieving value from Location Web Service");
    }
}
