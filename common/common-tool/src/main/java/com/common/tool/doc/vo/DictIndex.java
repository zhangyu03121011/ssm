package com.common.tool.doc.vo;

public class DictIndex {
    private int start;

    private int end;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "DictIndex [start=" + start + ", end=" + end + "]";
    }
}
