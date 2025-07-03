package fr.dawan.project1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.project1.dto.CategoryDto;
import fr.dawan.project1.dto.ProductDto;
import fr.dawan.project1.services.CategoryService;
import fr.dawan.project1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends GenericController<CategoryDto> {

    private CategoryService categoryService;
    private TaskScheduler taskScheduler;


    @Autowired
    public CategoryController(CategoryService categoryService, TaskScheduler taskScheduler) {
        super(categoryService);
        this.categoryService=categoryService;
        this.taskScheduler = taskScheduler;
    }

    @GetMapping(value={"/{page}/{size}/{search}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> findAllBySearch(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable(value="search", required = false) Optional<String> search) throws Exception {
        if(search.isPresent())
            return categoryService.findAll(page, size, search.get());
        else
            return categoryService.findAll(page, size, null);
    }

    @PostMapping(value="/save-with-image", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> saveWithImage(@RequestParam("category") String categoryStr,
                                                     @RequestPart("file") MultipartFile file) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED) //201
                .body(categoryService.saveOrUpdateWithImage(categoryStr,file));

    }

    @PutMapping(value="/update-with-image", consumes=MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> updateWithImage(@RequestParam("category") String categoryStr,
                                                     @RequestPart("file") MultipartFile file) throws Exception {

        return ResponseEntity.status(HttpStatus.OK) //201
                .body(categoryService.saveOrUpdateWithImage(categoryStr,file));


    }


    @GetMapping(value="/task", produces = MediaType.TEXT_PLAIN_VALUE)
    public String launchTask() throws Exception {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    categoryService.count(); // TO REPLACE WITH YOUR TASK
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        //taskScheduler.schedule(runnable);//déclenche la tache une fois
        taskScheduler.scheduleAtFixedRate(runnable, Instant.now(), Duration.of(10, ChronoUnit.SECONDS));
        return "task launched...";
    }


}


