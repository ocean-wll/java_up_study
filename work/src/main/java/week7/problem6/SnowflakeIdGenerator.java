package week7.problem6;

/**
 * 雪花算法demo，实现自增长id
 * <p>
 * 雪花算法 由64位组成
 * 1.第一位 占用1bit，其值始终是0，没有实际作用。
 * 2.时间戳 占用41bit，精确到毫秒，总共可以容纳约69年的时间。
 * 3.工作机器id 占用10bit，其中高位5bit是数据中心ID，低位5bit是工作节点ID，做多可以容纳1024个节点。
 * 4.序列号 占用12bit，每个节点每毫秒0开始不断累加，最多可以累加到4095，一共可以产生4096个ID。
 *
 * @author : ocean_wll
 * @since 2021/6/19
 */
public class SnowflakeIdGenerator {

    /**
     * 序列号占用12个字节
     */
    private final int sequenceBit = 12;

    /**
     * 工作节点占用5个字节
     */
    private final int wordNodeBit = 5;

    /**
     * 数据中心占用5个字节
     */
    private final int dataCenterBit = 5;

    /**
     * 时间戳占用41个字节
     */
    private final int timestampBit = 41;

    /**
     * 工作节点左移 12位
     */
    private final int wordNodeShift = sequenceBit;

    /**
     * 数据中心左移 （12+5）位
     */
    private final int dataCenterShift = wordNodeShift + wordNodeBit;

    /**
     * 时间戳左移 （12 + 5 + 5）位
     */
    private final int timestampShift = dataCenterShift + dataCenterBit;

    /**
     * 序列号掩码 0b111111111111
     */
    private final long sequenceMask = ~(-1L << sequenceBit);

    /**
     * 时间戳掩码 0b111111111111111111111111111111111111111110000000000000000000000
     */
    private final long timeStampMask = ~(-1L << timestampBit) << timestampShift;

    /**
     * 最大的数据节点id为 0b11111  最大为31
     */
    private final long maxWorkNodeId = ~(-1L << wordNodeBit);

    /**
     * 最大的数据中心id为 0b11111 最大为31
     */
    private final long maxDataCenterId = ~(-1L << dataCenterBit);

    /**
     * 上次时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 数据中心id
     */
    private final long dataCenterId;

    /**
     * 工作节点id
     */
    private final long wordNodeId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 雪花算法构造函数
     *
     * @param dataCenterId 数据中心id，最大为31
     * @param wordNodeId   工作节点id，最大为31
     * @throws IllegalArgumentException 数据参数异常
     */
    public SnowflakeIdGenerator(Long dataCenterId, Long wordNodeId) throws IllegalArgumentException {
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId不允许大于0b11111 或 小于0");
        }
        if (wordNodeId > maxWorkNodeId || wordNodeId < 0) {
            throw new IllegalArgumentException("workNodeId不允许大于0b11111 或 小于0");
        }
        this.dataCenterId = dataCenterId;
        this.wordNodeId = wordNodeId;
    }

    /**
     * 生成唯一的雪花id（线程安全）
     *
     * @return id
     * @throws RuntimeException 如果系统时间回退则会抛出此异常
     */
    public synchronized long generateId() throws RuntimeException {
        long currTimestamp = timeGen();
        if (currTimestamp < lastTimestamp) {
            throw new RuntimeException("系统时间异常，当前时间小于上次执行时间，currTime = " + currTimestamp + " ,lastTimestamp = " + lastTimestamp);
        }
        //如果时间戳相同
        if (currTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 说明序列号溢出了则需要获取新的时间戳
            if (sequence == 0L) {
                currTimestamp = tilNextMillis(currTimestamp);
            }
        } else {
            //重置序列号
            sequence = 0L;
        }
        lastTimestamp = currTimestamp;

        return ~(1L << (timestampShift + timestampBit))
                & (currTimestamp << timestampShift) & timeStampMask
                | dataCenterId << dataCenterShift
                | wordNodeId << wordNodeShift
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回当前时间戳
     *
     * @return 毫秒
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1L, 1L);
        for (int i = 0; i < 10; i++) {
            long id = snowflakeIdGenerator.generateId();
            System.out.println(Long.toBinaryString(id));
        }
    }

}
