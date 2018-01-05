//package top.ball.rice.hospital.server.conf;
//
//import org.springframework.context.annotation.*;
//import org.springframework.data.domain.*;
//import top.ball.rice.hospital.repo.BaseRepo;
//
//import java.util.*;
//
//@Configuration
//@EnableMongoRepositories(repositoryBaseClass = BaseRepoImpl.class, basePackageClasses = BaseRepo.class)
//@EnableMongoAuditing
//public class MongoConf {
//
//    @Bean
//    MyAuditorAware myAuditorAware() {
//        return new MyAuditorAware();
//    }
//
//    private static final String[] auditors = {"xxx", "yyy", "zzz"};
//    private static final Random random = new Random(System.currentTimeMillis());
//
//    public class MyAuditorAware implements AuditorAware<String> {
//
//        @Override
//        public String getCurrentAuditor() {
//            return auditors[Math.abs(random.nextInt()) % auditors.length];
//        }
//    }
//}