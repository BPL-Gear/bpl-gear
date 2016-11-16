package org.bpl.gear.te.gt;

import org.bpl.gear.base.gear.spur.SpurGear;
import org.bpl.gear.te.error.spur.SpurGearError;

import java.util.List;

/**
 * The type GT测量结果.
 *
 * @description
 * @create 2016 -11-03 下午8:40
 * @email gxz04220427 @163.com
 */
public class GtMeasure {
    /**
     * Instantiates a new Gt measure.
     */
    public GtMeasure() {
    }

    /**
     * Instantiates a new Gt measure.
     *
     * @param drivingGearNo the driving gear no
     * @param drivedGearNo  the drived gear no
     * @param drivinGear    the drivin gear
     * @param drivedGear    the drived gear
     * @param leftError     the left error
     * @param rightError    the right error
     * @param leftDatas     the left datas
     * @param rightDatas    the right datas
     * @param load          the load
     * @param rpm           the rpm
     */
    public GtMeasure(String drivingGearNo, String drivedGearNo, SpurGear drivinGear, SpurGear drivedGear, SpurGearError leftError, SpurGearError rightError, List<Double> leftDatas, List<Double> rightDatas, double load, double rpm) {
        this.drivingGearNo = drivingGearNo;
        this.drivedGearNo = drivedGearNo;
        this.drivinGear = drivinGear;
        this.drivedGear = drivedGear;
        this.leftError = leftError;
        this.rightError = rightError;
        this.leftDatas = leftDatas;
        this.rightDatas = rightDatas;
        this.load = load;
        this.rpm = rpm;
    }

    /**
     * The 主动轮编号.
     */
    private String drivingGearNo;
    /**
     * The 被动轮编号.
     */
    private String drivedGearNo;
    /**
     * The 主动轮.
     */
    private SpurGear drivinGear;
    /**
     * The 被动轮.
     */
    private SpurGear drivedGear;
    /**
     * The 左齿面误差.
     */
    private SpurGearError leftError;
    /**
     * The 右齿面误差.
     */
    private SpurGearError rightError;
    /**
     * The 左齿面数据.
     */
    private List<Double> leftDatas;
    /**
     * The 右齿面数据.
     */
    private List<Double> rightDatas;
    /**
     * The 载荷.
     */
    private double load;
    /**
     * The 转速.
     */
    private double rpm;




    /**
     * Gets driving gear no.
     *
     * @return the driving gear no
     */
    public String getDrivingGearNo() {
        return drivingGearNo;
    }

    /**
     * Sets driving gear no.
     *
     * @param drivingGearNo the driving gear no
     *
     * @return the driving gear no
     */
    public GtMeasure setDrivingGearNo(String drivingGearNo) {
        this.drivingGearNo = drivingGearNo;
        return this;
    }

    /**
     * Gets drived gear no.
     *
     * @return the drived gear no
     */
    public String getDrivedGearNo() {
        return drivedGearNo;
    }

    /**
     * Sets drived gear no.
     *
     * @param drivedGearNo the drived gear no
     *
     * @return the drived gear no
     */
    public GtMeasure setDrivedGearNo(String drivedGearNo) {
        this.drivedGearNo = drivedGearNo;
        return this;
    }

    /**
     * Gets drivin gear.
     *
     * @return the drivin gear
     */
    public SpurGear getDrivinGear() {
        return drivinGear;
    }

    /**
     * Sets drivin gear.
     *
     * @param drivinGear the drivin gear
     *
     * @return the drivin gear
     */
    public GtMeasure setDrivinGear(SpurGear drivinGear) {
        this.drivinGear = drivinGear;
        return this;
    }

    /**
     * Gets drived gear.
     *
     * @return the drived gear
     */
    public SpurGear getDrivedGear() {
        return drivedGear;
    }

    /**
     * Sets drived gear.
     *
     * @param drivedGear the drived gear
     *
     * @return the drived gear
     */
    public GtMeasure setDrivedGear(SpurGear drivedGear) {
        this.drivedGear = drivedGear;
        return this;
    }

    /**
     * Gets left error.
     *
     * @return the left error
     */
    public SpurGearError getLeftError() {
        return leftError;
    }

    /**
     * Sets left error.
     *
     * @param leftError the left error
     *
     * @return the left error
     */
    public GtMeasure setLeftError(SpurGearError leftError) {
        this.leftError = leftError;
        return this;
    }

    /**
     * Gets right error.
     *
     * @return the right error
     */
    public SpurGearError getRightError() {
        return rightError;
    }

    /**
     * Sets right error.
     *
     * @param rightError the right error
     *
     * @return the right error
     */
    public GtMeasure setRightError(SpurGearError rightError) {
        this.rightError = rightError;
        return this;
    }

    /**
     * Gets left datas.
     *
     * @return the left datas
     */
    public List<Double> getLeftDatas() {
        return leftDatas;
    }

    /**
     * Sets left datas.
     *
     * @param leftDatas the left datas
     *
     * @return the left datas
     */
    public GtMeasure setLeftDatas(List<Double> leftDatas) {
        this.leftDatas = leftDatas;
        return this;
    }

    /**
     * Gets right datas.
     *
     * @return the right datas
     */
    public List<Double> getRightDatas() {
        return rightDatas;
    }

    /**
     * Sets right datas.
     *
     * @param rightDatas the right datas
     *
     * @return the right datas
     */
    public GtMeasure setRightDatas(List<Double> rightDatas) {
        this.rightDatas = rightDatas;
        return this;
    }

    /**
     * Gets load.
     *
     * @return the load
     */
    public double getLoad() {
        return load;
    }

    /**
     * Sets load.
     *
     * @param load the load
     *
     * @return the load
     */
    public GtMeasure setLoad(double load) {
        this.load = load;
        return this;
    }

    /**
     * Gets rpm.
     *
     * @return the rpm
     */
    public double getRpm() {
        return rpm;
    }

    /**
     * Sets rpm.
     *
     * @param rpm the rpm
     *
     * @return the rpm
     */
    public GtMeasure setRpm(double rpm) {
        this.rpm = rpm;
        return this;
    }
}
