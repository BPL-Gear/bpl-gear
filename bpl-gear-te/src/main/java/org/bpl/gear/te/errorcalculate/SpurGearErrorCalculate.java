package org.bpl.gear.te.errorcalculate;

import org.bpl.gear.base.gear.spur.SpurGear;
import org.bpl.gear.te.error.spur.SpurGearError;
import org.bpl.gear.te.errorcalculate.interfaces.ISpurGearErrorCalculate;

import java.util.ArrayList;
import java.util.List;

/**
 * 直齿轮误差计算类.
 *
 * @description
 * @create 2016 -11-08 上午10:12
 * @email gxz04220427 @163.com
 */
public class SpurGearErrorCalculate implements ISpurGearErrorCalculate {
    /**
     * 直齿轮误差计算.
     *
     * @param tedata the tedata
     * @param gear   the gear
     *
     * @return the spur gear error
     */
    @Override
    public SpurGearError calculate(double[] tedata, SpurGear gear) {
        SpurGearError error = new SpurGearError();
        int totalNum = tedata.length;
//        计算每个齿大概有多少个测点
        int pointsNum_OneTooth = tedata.length / gear.getZ();
//        整个TE的开始位置，也就是第一个齿开始的位置，找到最小的那个点
        int teBeginPosition = 0;
        double temp = tedata[0];
        for (int i = 1; i < pointsNum_OneTooth; i++) {
            if (temp > tedata[i]) {
                temp = tedata[i];
                teBeginPosition = i;
            }
        }
//        找到了第一个齿的开始位置，然后把它前面的数据补到后面,得到完整的传动误差曲线
        double[] realTeData = new double[totalNum];
        for (int i = 0; i < totalNum - teBeginPosition; i++) {
            realTeData[i] = tedata[i + teBeginPosition];
        }
        for (int i = totalNum - teBeginPosition; i < totalNum; i++) {
            realTeData[i] = tedata[i + teBeginPosition - totalNum];
        }
//        计算切向综合总偏差
        error.setFi(this.calculateFi(realTeData));
//        把误差曲线分解到每个齿上面
        Tooth[] tooths = new Tooth[gear.getZ()];
        double angerPerTooth = ((double) 360) / gear.getZ();
        double angerPerPoint = ((double) 360) / tedata.length;
        for (int i = 0; i < gear.getZ(); i++) {
            tooths[i] = new Tooth(i);
            for (int j = 0; j < totalNum; j++) {
//                if ((0.1 * j < (((double) 360) / 56) * i) && (0.1 * j > (((double) 360) / 56) * (i + 1))) {
//                    tooths[i].addPoint(realTeData[j]);
//                }
                if (j * angerPerPoint >= i * angerPerTooth && j * angerPerPoint < (i + 1) * angerPerTooth) {
                    tooths[i].addPoint(realTeData[j]);
                }
            }
            tooths[i].calculateFia();
        }
//        计算一齿切向综合偏差
        double tempfia = tooths[0].getFia();
        for (int i = 0; i < gear.getZ(); i++) {
            if (tempfia < tooths[i].getFia()) {
                tempfia = tooths[i].getFia();
            }
        }
        error.setFia(tempfia);
//        计算单个齿距偏差
        error.setFpt(this.calculateFpt(tooths));
        error.setFp(this.calculateFp(tooths));

//        计算偏心误差
        SpurEccentricCalculate eccentricCalculate = new SpurEccentricCalculate(gear, tedata);
        eccentricCalculate.calculate();
        error.setEccentric(eccentricCalculate.getEccentric());
        return error;
    }

    /**
     * 计算切向综合总偏差
     *
     * @param tedata the tedata
     *
     * @return the double
     */
    private double calculateFi(double[] tedata) {
        double min = tedata[0];
        double max = tedata[0];
        for (double d : tedata) {
            if (min > d) {
                min = d;
            }
            if (max < d) {
                max = d;
            }
        }
        return max - min;
    }

    /**
     * 计算齿距累计总偏差
     *
     * @param tooths the tooths
     *
     * @return the double
     */
    private double calculateFp(Tooth[] tooths) {
        double[] fiErrors = new double[tooths.length];
        for (int i = 0; i < tooths.length; i++) {
            fiErrors[i] = tooths[i].getData().get(tooths[i].getData().size() - 1) - tooths[0].getData().get(0);
        }
        double fiMin = fiErrors[0];
        double fiMax = fiErrors[0];
        for (int i = 0; i < fiErrors.length; i++) {
            if (fiMin > fiErrors[i]) {
                fiMin = fiErrors[i];
            }
            if (fiMax < fiErrors[i]) {
                fiMax = fiErrors[i];
            }
        }
        return fiMax - fiMin;
    }

    /**
     * 计算单个齿距偏差
     *
     * @param tooths the tooths
     *
     * @return the double
     */
    private double calculateFpt(Tooth[] tooths) {
        double[] fiaErrors = new double[tooths.length];
        for (int i = 0; i < tooths.length; i++) {
            fiaErrors[i] = tooths[i].getData().get(tooths[i].getData().size() - 1) - tooths[i].getData().get(0);
        }
        double tempfis = Math.abs(fiaErrors[0]);
        for (int i = 1; i < fiaErrors.length; i++) {
            if (tempfis < Math.abs(fiaErrors[0])) {
                tempfis = Math.abs(fiaErrors[0]);
            }
        }
        return tempfis;
    }


    /**
     * 单个齿对象.
     */
    class Tooth {
        /**
         * Instantiates a new Tooth.
         *
         * @param no the no
         */
        public Tooth(int no) {
            this.no = no;
            this.data = new ArrayList<>();
        }

        /**
         * The 序号.
         */
        private int no;
        /**
         * The 传动误差数据.
         */
        private List<Double> data;
        /**
         * The 一齿切向综合偏差.
         */
        private double fia;

        /**
         * 计算一齿切向综合偏差.
         *
         * @return the double
         */
        public double calculateFia() {
            if (this.data.size() == 0) {
                throw new NullPointerException("传动误差数据为空，不能计算");
            }
            double min = data.get(0);
            double max = data.get(0);
            for (double d : data) {
                if (min > d) {
                    min = d;
                }
                if (max < d) {
                    max = d;
                }
            }
            fia = max - min;
            return fia;
        }

        /**
         * Add point.
         *
         * @param d the d
         */
        public void addPoint(double d) {
            this.data.add(d);
        }

        /**
         * Gets no.
         *
         * @return the no
         */
        public int getNo() {
            return no;
        }

        /**
         * Sets no.
         *
         * @param no the no
         *
         * @return the no
         */
        public Tooth setNo(int no) {
            this.no = no;
            return this;
        }

        /**
         * Gets data.
         *
         * @return the data
         */
        public List<Double> getData() {
            return data;
        }

        /**
         * Sets data.
         *
         * @param data the data
         *
         * @return the data
         */
        public Tooth setData(List<Double> data) {
            this.data = data;
            return this;
        }

        /**
         * Gets fia.
         *
         * @return the fia
         */
        public double getFia() {
            return fia;
        }

        /**
         * Sets fia.
         *
         * @param fia the fia
         *
         * @return the fia
         */
        public Tooth setFia(double fia) {
            this.fia = fia;
            return this;
        }
    }


}
