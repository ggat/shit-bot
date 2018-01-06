package ge.shitbot.persist.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by giga on 12/8/17.
 */
public class SessionUtil {

    //Logger logger = LoggerFactory.getLogger(SessionUtil.class);

    private static SessionUtil instance;
    private final SessionFactory factory;
    private static final String CONFIG_NAME = "/configuration.properties";
    private static Session session;
    private static Map<String, String> settings = new HashMap<>();

    private SessionUtil() {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .applySettings(settings)
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        //FIXME: Moved this initialization here because of this we could not set settings without instantiating SessionUtil.
        instance = new SessionUtil();
    }

    public static Session getSession() {
        return getInstance().factory.openSession();
    }

    public static Session getSingletonSession() {
        if (session == null) {
            session = getSession();
        }

        return session;
    }

    private static SessionUtil getInstance() {
        return instance;
    }

    public static Map<String, String> getSettings() {
        return settings;
    }

    public static void setSettings(Map<String, String> settings) {
        SessionUtil.settings = settings;
    }
}
