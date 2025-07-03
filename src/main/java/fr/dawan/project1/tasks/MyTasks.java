package fr.dawan.project1.tasks;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MyTasks {

    private final static Logger log = LoggerFactory.getLogger(MyTasks.class);

    //Tache planifiée qui sera exécuté en fonction de la config indiqué
    @Scheduled(fixedDelay = 3000) // ou fixedDelay ou fixedRate
    @Async(value="asyncExecutor")
    public void m1(){
      log.info("my task m1");
    }

    @Scheduled(fixedDelay = 5000) // ou fixedDelay ou fixedRate
    @Async(value="asyncExecutor")
    public void m2(){
        log.info("my task m2");
    }

    @Scheduled(fixedDelay = 1000) // ou fixedDelay ou fixedRate
    @Async(value="asyncExecutor")
    public void sendNotifications(){
        //log.info("my task m2");
        //find all client qui ont une dateTime notif = Now
        //loop and send an email
    }
}


