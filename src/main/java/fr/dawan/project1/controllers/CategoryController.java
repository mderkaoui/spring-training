package fr.dawan.project1.controllers;

import fr.dawan.project1.dto.CategoryDto;

import fr.dawan.project1.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends GenericController<CategoryDto> {

    private CategoryService categoryService;
    private TaskScheduler taskScheduler;

 //   private CacheManager cacheManager;



    @Autowired
    public CategoryController(CategoryService categoryService, TaskScheduler taskScheduler){// CacheManager cacheManager) {
        super(categoryService);
        this.categoryService=categoryService;
        this.taskScheduler = taskScheduler;
   //     this.cacheManager = cacheManager;

    }

    @GetMapping(value={"/{page}/{size}/{search}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> findAllBySearch(
                                            @PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable(value="search", required = false) Optional<String> search) throws Exception {

     //Cache myCache1 =  cacheManager.getCache("myCache1");
     //myCache1.put("res1",34);
     //myCache1.get("res1");

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


