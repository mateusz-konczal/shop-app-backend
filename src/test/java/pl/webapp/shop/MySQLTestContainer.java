package pl.webapp.shop;

import org.testcontainers.containers.MySQLContainer;

public class MySQLTestContainer extends MySQLContainer<MySQLTestContainer> {

    private static final String MYSQL_IMAGE = "mysql:8.0.32";
    private static MySQLTestContainer mySQLTestContainer;

    private MySQLTestContainer() {
        super(MYSQL_IMAGE);
    }

    public static MySQLTestContainer getInstance() {
        if (mySQLTestContainer == null) {
            mySQLTestContainer = new MySQLTestContainer();
        }

        return mySQLTestContainer;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("MYSQL_URL", mySQLTestContainer.getJdbcUrl());
        System.setProperty("MYSQL_USERNAME", mySQLTestContainer.getUsername());
        System.setProperty("MYSQL_PASSWORD", mySQLTestContainer.getPassword());
        System.setProperty("MYSQL_DRIVER", mySQLTestContainer.getDriverClassName());
    }
}
