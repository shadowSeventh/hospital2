package top.ball.rice.hospital.repo;


import top.ball.rice.hospital.domain.Adc;

import java.util.List;

public interface AdcRepo extends BaseRepo<Adc, String> {

    public abstract List<Adc> findAllByParent(Adc adc);

    public abstract Adc findOneByNo(String no);
}
