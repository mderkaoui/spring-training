package fr.dawan.project1.tasks;

import fr.dawan.project1.services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MyTasks {

    private final static Logger log = LoggerFactory.getLogger(MyTasks.class);

    private EmailService emailService;

    @Autowired
    public MyTasks(EmailService emailService) {
        this.emailService = emailService;
    }


//    @Scheduled(fixedDelay = 3000) // ou fixedDelay ou fixedRate
//    @Async(value="asyncExecutor")
//    public void m1(){
//      log.info("my task m1");
//    }
//
//    @Scheduled(fixedDelay = 5000) // ou fixedDelay ou fixedRate
//    @Async(value="asyncExecutor")
//    public void m2(){
//        log.info("my task m2");
//    }



    /**
     * Send email notification for out stock objects
     */
    @Scheduled(cron="0 57 9 * * *")
    @Async(value="asyncExecutor")
    public void sendNotifications() throws Exception {
        emailService.sendStockNotification(5);
    }
}


