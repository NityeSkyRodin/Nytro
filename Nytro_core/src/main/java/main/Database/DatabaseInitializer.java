package main.Database;

import lombok.extern.log4j.Log4j2;
import main.Status.model.Status;
import main.Status.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DatabaseInitializer implements CommandLineRunner {

    private final StatusRepository statusRepository;

    public DatabaseInitializer(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            if (statusRepository.findAll().size() == 0) {
                statusRepository.save(new Status("To Do", "Task that needs to be done", 1, 0L));
                statusRepository.save(new Status("In Progress", "Task that is in progress", 2, 1L));
                statusRepository.save(new Status("Done", "Task that is done", 3, 2L));
                statusRepository.save(new Status("Archived", "Task that is archived", 4, 3L));
                statusRepository.save(new Status("Late", "Task that is not finished on time", 5, 4L));
                log.info("All statuses are created successfully.");
            }
            else {
                log.info("Statuses are already created.");
            }

            long endTime = System.currentTimeMillis();
            float duration = endTime - startTime;
            log.info("Application started successfully in " + duration/100 + " seconds.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
