package fr.dawan.project1.services;

public interface EmailService {

    /**
     * send an email with a list of product out of stock (<5)
     */
    void sendStockNotification(int min) throws Exception;
}
