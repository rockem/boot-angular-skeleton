package features;

import org.sample.Application;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RunWith(Cucumber.class)
public class RunFeatures {

    static ConfigurableApplicationContext backend;

    private static final int mongoPort = 27018;

    @BeforeClass
    public static void startBackend() throws IOException {
        try {
            runProcessFor("mongod",
                    "--config", "mongodb.yml",
                    "--port", String.valueOf(mongoPort));
            backend = SpringApplication.run(Application.class);
        } catch(ProcessInvocationException e) {
            System.exit(1);
        }
    }

    private static void runProcessFor(String... args) throws IOException {
        Process process = new ProcessBuilder(args).start();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(process.getErrorStream()));
        String line;
        Boolean error = false;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            error = true;
        }
        if(error) {
            throw new ProcessInvocationException();
        }
    }

    @AfterClass
    public static void stopBackend() throws Exception {
        backend.close();
        runProcessFor("mongo",
                "--port", String.valueOf(mongoPort),
                "--eval", "db.getSiblingDB('admin').shutdownServer()");
    }

    private static class ProcessInvocationException extends RuntimeException {
    }
}
