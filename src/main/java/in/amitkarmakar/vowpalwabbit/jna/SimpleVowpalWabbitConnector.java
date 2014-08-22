package in.amitkarmakar.vowpalwabbit.jna;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleVowpalWabbitConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleVowpalWabbitConnector.class);

    private static AtomicBoolean isCreated = new AtomicBoolean(false);

    private static SimpleVowpalWabbitConnector instance = new SimpleVowpalWabbitConnector();

    /**
     * Private constructor to prevent instantiation
     */
    private SimpleVowpalWabbitConnector() {
    }

    public static synchronized SimpleVowpalWabbitConnector getInstance() throws IOException {
        if (!isCreated.getAndSet(true)) {
            LOGGER.info("Initializing SimpleVowpalWabbitConnector");
            try {
                NativeLibrary.getInstance(Constants.VW_LIBRARY_NAME);
                Native.register(Constants.CONNECTOR_LIBRARY_NAME);
            } catch (Throwable t) {
                LOGGER.error("Could not initialize SimpleVowpalWabbitConnector", t);
                throw new IOException("Could not initialize JNA connector", t);
            }
        }
        return instance;
    }

    /**
     * Initialize vw instance with <code>command</code>
     *
     * @param command initialize vs instance with command
     */
    public native void initialize(String command);

    /**
     * Runs prediction on <code>example</code> and returns the prediction output
     *
     * @param example a single vw example string
     * @return prediction output
     */
    public native double getPrediction(String example);

    /**
     * Properly shutdown vw instance
     */
    public native void closeInstance();
}

