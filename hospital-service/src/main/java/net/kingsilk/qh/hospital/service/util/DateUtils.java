package net.kingsilk.qh.hospital.service.util;

import net.kingsilk.qh.bargain.api.bargainApp.bargain.wap.bargain.dto.BargainInfoResp;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateUtils {

//    public static Date timeTyoe2Date(BargainActivity.TimeType timeType) {
//
//        StringBuffer sb = new StringBuffer(timeType.getYear());
//        String s = sb.append(timeType.getMonth()).append(timeType.getDay()).toString();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date date;
//        try {
//            date = simpleDateFormat.parse(s);
//        } catch (Exception e) {
//            throw new ErrStatusException(ErrStatus.FOUNDNULL, "时间不对");
//        }
//        return date;
//    }

    public static BargainInfoResp.TimeType date2TimeType(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String s = simpleDateFormat.format(date);
        BargainInfoResp.TimeType timeType = new BargainInfoResp.TimeType();
        timeType.setYear(s.substring(0, 4));
        timeType.setMonth(s.substring(4, 6));
        timeType.setDay(s.substring(6));
        return timeType;
    }

//    public static BargainReq.TimeType timeType2TimeType(BargainActivity.TimeType olderTimeType) {
//
//        BargainReq.TimeType timeType = new BargainReq.TimeType();
//        timeType.setDay(olderTimeType.getDay());
//        timeType.setMonth(olderTimeType.getMonth());
//        timeType.setYear(olderTimeType.getYear());
//
//        return timeType;
//    }
}
