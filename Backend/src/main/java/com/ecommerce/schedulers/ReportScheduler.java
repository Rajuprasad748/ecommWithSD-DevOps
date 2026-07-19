package com.ecommerce.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportScheduler {

    @Scheduled(cron = "0 0 23 * * *")
    public void generateDailySalesReport() {

        log.info(
                "Entering method : ReportScheduler.generateDailySalesReport()"
        );

        log.info(
                "Generating Daily Sales Report..."
        );

        log.info(
                "Daily Sales Report Generated Successfully"
        );

        log.info(
                "Exiting method : ReportScheduler.generateDailySalesReport()"
        );
    }
}