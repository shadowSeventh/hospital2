package net.kingsilk.qh.bargain.repo;


import net.kingsilk.qh.bargain.domain.Adc;

import java.util.List;

public interface AdcRepo extends BaseRepo<Adc, String> {

    public abstract List<Adc> findAllByParent(Adc adc);

    public abstract Adc findOneByNo(String no);
}
