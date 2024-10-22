package be.pxl.services.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * NotificationService
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(NotificationServiceApp.class,args);
    }
}
