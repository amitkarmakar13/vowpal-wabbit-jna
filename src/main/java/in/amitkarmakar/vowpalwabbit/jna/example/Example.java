package in.amitkarmakar.vowpalwabbit.jna.example;

import in.amitkarmakar.vowpalwabbit.jna.SimpleVowpalWabbitConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

class Example {

    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);

    // Private constructor to prevent instantiation
    private Example() {
    }

    public static void main(String[] args) {
        // Set JNA library path. This path should contain libvw.so and libvw_simple_jna.so
        System.setProperty("jna.library.path", "/usr/local/lib");

        try {
            SimpleVowpalWabbitConnector connector = SimpleVowpalWabbitConnector.getInstance();

            // Load model for prediction
            connector.initialize("-t -i model");

            double prediction = connector.getPrediction("1 1 | a b c d e f");
            LOGGER.info("Prediction : " + prediction);

            // Close previously opened model
            connector.closeInstance();
        } catch (IOException ioe) {
            LOGGER.error("Error running prediction", ioe);
        }
    }
}
