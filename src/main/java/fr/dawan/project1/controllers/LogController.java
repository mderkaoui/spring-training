package fr.dawan.project1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.project1.dto.ErrorLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Manage server logs
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/logs")
public class LogController {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${app.storagefolder}")
    private String storageFolder;

    /**
     * write logs into root logger
     * @param logDto    dto received by client
     * @return  HttpStatusCode
     * @throws JsonProcessingException  if json to string conversion error
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> writeLog(ErrorLogDto logDto) throws JsonProcessingException {
        //String msg = logDto.getPath() + " " + logDto.getErrorCode() + " : " + logDto.getMessage();
        String msg= objectMapper.writeValueAsString(logDto); //convert dto to string json value

        switch(logDto.getLevel()){
            case INFO -> log.info(msg);
            case WARN -> log.warn(msg);
            case ERROR -> log.error(msg);
            case DEBUG -> log.debug(msg);

            default -> throw new IllegalStateException("Unexpected value: " + logDto.getLevel());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value="/{filename}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getFile(@PathVariable("filename") String filename) throws Exception{
        //read file from path
        File f = new File(storageFolder+"/"+filename);
        Path path = Paths.get(f.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        //http response header to inform the client that an attachment is joined to the response
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+filename+"\"");
        headers.add("Cache-Control","no-cache, no-store, must-revalidate");
        headers.add("Pragma","no-cache");
        headers.add("Expires","0");

        //return result
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(f.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
