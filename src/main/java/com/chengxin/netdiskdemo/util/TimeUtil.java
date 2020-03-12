package com.chengxin.netdiskdemo.util;

/**
 * TimeUtil 的注释
 *
 * @author chengxin
 * @date 2020/3/12 9:54
 * @description
 */
public class TimeUtil {


    public static TimeBuilder getDays(int days) {
        return new TimeBuilder(TimeType.DAY, days);
    }

    public static TimeBuilder getHours(int hours) {
        return new TimeBuilder(TimeType.HOUR, hours);
    }

    public static TimeBuilder getSeconds(long seconds) {
        return new TimeBuilder(TimeType.SECOND, seconds);
    }

    public static TimeBuilder getMinutes(long minutes) {
        return new TimeBuilder(TimeType.MINUTES, minutes);
    }

    public static class TimeBuilder {

        private TimeType type;
        private long count;

        public TimeBuilder(TimeType type, long count) {
            this.type = type;
            this.count = count;
        }

        public long toSeconds() {
            if (type == TimeType.MINUTES) {
                return count * 60;
            } else if (type == TimeType.HOUR) {
                return count * 3600;
            } else if (type == TimeType.DAY) {
                return count * 216000;
            } else {
                return count;
            }
        }
    }

    enum TimeType {
        DAY, HOUR, MINUTES, SECOND
    }

}
