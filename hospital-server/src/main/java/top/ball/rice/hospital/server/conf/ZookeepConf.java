//package top.ball.rice.hospital.server.conf;
//
//import com.github.shadowseventh.distributed.lock.prop.DisLockProperties;
//import org.apache.curator.RetryPolicy;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.zookeeper.lock.ZookeeperLockRegistry;
//import top.ball.rice.hospital.service.HospitalProperties;
//
//import javax.ws.rs.BeanParam;
//
///**
// *
// */
//@Configuration
//public class ZookeepConf {
//
//    @Bean
//    public RetryPolicy zkRetry() {
//        return new ExponentialBackoffRetry(1000, 3);
//    }
//
//    @Bean(initMethod = "start", destroyMethod = "close")
//    public CuratorFramework zkClient(
//            HospitalProperties properties,
//            RetryPolicy zkRetry
//    ) {
//        return CuratorFrameworkFactory.newClient(
//                properties.getAddresses(),
//                zkRetry
//        );
//    }
//
//    @Bean
//    public ZookeeperLockRegistry zkLockRegistry(
//            CuratorFramework zkClient
//    ) {
//        return new ZookeeperLockRegistry(zkClient);
//    }
//
//    @Bean
//    public DisLockProperties properties(
//            HospitalProperties hospitalProperties
//    ){
//        DisLockProperties properties=new DisLockProperties();
////        properties.setAddresses(hospitalProperties.getAddresses());
//        properties.setWaitLockTime(hospitalProperties.getWaitLockTime());
//        return new DisLockProperties();
//    }
//}
