package com.nebula.object.generator;

import com.nebula.object.ObjectGenerator;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.Date;

public class ObjectGeneratorBuilder {

    private int defaultInt = 0;
    private long defaultLong = 0l;
    private float defaultFloat = 0f;
    private double defaultDouble = 0d;
    private boolean defaultBoolean = false;
    private byte defaultByte = (byte) 0;
    private short defaultShort = (short) 0;
    private char defaultChar = 'c';
    private String defaultString = "string";
    private BigDecimal defaultBigDecimal = BigDecimal.ZERO;
    private int defaultCollectionSize = 10;
    private Date defaultDate = DateTime.now().toDate();
    private int defaultMaxDepth = 32;

    public ObjectGenerator build() {
        return new SimpleObjectGenerator(this);
    }

    public ObjectGeneratorBuilder setDefaultInt(int defaultInt) {
        this.defaultInt = defaultInt;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultLong(long defaultLong) {
        this.defaultLong = defaultLong;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultFloat(float defaultFloat) {
        this.defaultFloat = defaultFloat;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultDouble(double defaultDouble) {
        this.defaultDouble = defaultDouble;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultBoolean(boolean defaultBoolean) {
        this.defaultBoolean = defaultBoolean;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultByte(byte defaultByte) {
        this.defaultByte = defaultByte;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultShort(short defaultShort) {
        this.defaultShort = defaultShort;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultChar(char defaultChar) {
        this.defaultChar = defaultChar;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultString(String defaultString) {
        this.defaultString = defaultString;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultBigDecimal(BigDecimal defaultBigDecimal) {
        this.defaultBigDecimal = defaultBigDecimal;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultCollectionSize(int defaultCollectionSize) {
        this.defaultCollectionSize = defaultCollectionSize;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultDate(Date defaultDate) {
        this.defaultDate = defaultDate;
        return this;
    }

    public ObjectGeneratorBuilder setDefaultMaxDepth(int defaultMaxDepth) {
        this.defaultMaxDepth = defaultMaxDepth;
        return this;
    }

    public int getDefaultInt() {
        return defaultInt;
    }

    public long getDefaultLong() {
        return defaultLong;
    }

    public float getDefaultFloat() {
        return defaultFloat;
    }

    public double getDefaultDouble() {
        return defaultDouble;
    }

    public boolean isDefaultBoolean() {
        return defaultBoolean;
    }

    public byte getDefaultByte() {
        return defaultByte;
    }

    public short getDefaultShort() {
        return defaultShort;
    }

    public char getDefaultChar() {
        return defaultChar;
    }

    public String getDefaultString() {
        return defaultString;
    }

    public BigDecimal getDefaultBigDecimal() {
        return defaultBigDecimal;
    }

    public int getDefaultCollectionSize() {
        return defaultCollectionSize;
    }

    public Date getDefaultDate() {
        return defaultDate;
    }

    public int getDefaultMaxDepth() {
        return defaultMaxDepth;
    }
}
