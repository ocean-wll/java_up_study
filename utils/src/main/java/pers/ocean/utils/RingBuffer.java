package pers.ocean.utils;

import java.util.Arrays;

/**
 * @Description ringBuffer实现
 * @Author ocean_wll
 * @Date 2021/9/22 2:13 下午
 */
public class RingBuffer {

    /**
     * 默认大小
     */
    private final static int DEFAULT_SIZE = 1024;

    /**
     * 缓存数组
     */
    private final Object[] buffer;

    /**
     * 缓存头
     */
    private int head = 0;

    /**
     * 缓存尾
     */
    private int tail = 0;

    /**
     * 缓存大小
     */
    private final int bufferSize;

    public RingBuffer() {
        this.bufferSize = DEFAULT_SIZE;
        this.buffer = new Object[bufferSize];
    }

    public RingBuffer(final int initSize) {
        this.bufferSize = initSize;
        this.buffer = new Object[initSize];
    }

    /**
     * 判断是否为空
     *
     * @return true：空，false：非空
     */
    private Boolean empty() {
        return head == tail;
    }

    /**
     * 判断是否满了
     *
     * @return true：已满，false：非满
     */
    private Boolean full() {
        return (tail + 1) % bufferSize == head;
    }

    /**
     * 清空缓存
     */
    public void clear() {
        Arrays.fill(buffer, null);
        this.head = 0;
        this.tail = 0;
    }

    /**
     * 插入值
     *
     * @param obj 需要插入的值
     * @return true：插入成功，false：插入失败
     */
    public Boolean put(Object obj) {
        if (full()) {
            return false;
        }
        buffer[tail] = obj;
        tail = (tail + 1) % bufferSize;
        return true;
    }

    /**
     * 获取值
     *
     * @return 头元素，并将指针位置加1
     */
    public Object get() {
        if (empty()) {
            return null;
        }
        Object result = buffer[head];
        head = (head + 1) % bufferSize;
        return result;
    }

    /**
     * 获取所有缓存数据
     *
     * @return 缓存数组
     */
    public Object[] getAll() {
        if (empty()) {
            return new Object[0];
        }
        int cnt = head < tail ? tail - head : bufferSize - head + tail;
        Object[] result = new Object[cnt];
        if (head < tail) {
            if (tail - head >= 0) {System.arraycopy(buffer, head, result, 0, tail - head);}
        } else {
            if (bufferSize - head >= 0) {System.arraycopy(buffer, head, result, 0, bufferSize - head);}
            if (tail >= 0) {System.arraycopy(buffer, 0, result, bufferSize - head, tail);}
        }
        head = tail;
        return result;
    }
}
