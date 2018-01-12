package top.ball.rice.hospital.server.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import top.ball.rice.hospital.repo.BaseRepo;
import top.ball.rice.hospital.repo.BaseRepoImpl;

import javax.sql.DataSource;
import java.util.Random;

/**
 * repositoryBaseClass = BaseRepoImpl.class,
 */
@Configuration
//
@EnableTransactionManagement
@EnableJpaRepositories(repositoryBaseClass = BaseRepoImpl.class, basePackageClasses = BaseRepo.class)
@EnableJpaAuditing
class JpaConf {

    @Bean
    MyAuditorAware myAuditorAware() {
        return new MyAuditorAware();
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        return new LocalContainerEntityManagerFactoryBean();
//    }

//    @Bean
//    public DataSource dataSource() {
//        return new MysqlDataSource();
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
        lef.setDataSource(dataSource);
        lef.setJpaVendorAdapter(jpaVendorAdapter);
        lef.setPackagesToScan("top.ball.rice.hospital");
        return lef;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }
//    @Bean
//    EntityManagerFactory entityManagerFactory() {
//        return javax.persistence.Persistence.createEntityManagerFactory("jpaUnit");
//    }
//
//    @Bean
//    EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
//        return entityManagerFactory.createEntityManager();
//    }

    // SpringDataMongodbQuery
    // QuerydslRepositorySupport


    class MyAuditorAware implements AuditorAware<String> {

        private final String[] auditors = {"xxx", "yyy", "zzz"};
        private final Random random = new Random(System.currentTimeMillis());

        @Override
        public String getCurrentAuditor() {
            return auditors[Math.abs(random.nextInt() % auditors.length)];
        }
    }

}
