package top.ball.rice.hospital.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 保留该接口，方法统一追加自定义方法
 */
@NoRepositoryBean
public interface BaseRepo<T, ID extends Serializable>
        extends CrudRepository<T, ID> {
}
