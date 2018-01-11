package top.ball.rice.hospital.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 保留该接口，方法统一追加自定义方法
 *
 *  QueryDslPredicateExecutor<T>,
 */
@NoRepositoryBean
public interface BaseRepo<T, ID extends Serializable>
        extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
