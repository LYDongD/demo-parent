package com.liam.demo.io.byteBuffer.bigfile;

/**
 * 文件分区，由起止位置唯一确定
 * @author Liam
 * @date 2019/4/28 下午7:13
 */
public class FilePartion {

    private Long start;

    private Long end;

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
