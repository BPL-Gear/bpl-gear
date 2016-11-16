package org.bpl.gear.te.errorcalculate;

import org.bpl.gear.base.gear.spur.SpurGear;
import org.bpl.gear.te.errorcalculate.circlefitting.CircleOptimizer;
import org.bpl.gear.te.errorcalculate.circlefitting.CircleProblem;

/**
 * The type Spur eccentric calculate.
 *
 * @description
 * @create 2016 -11-15 上午10:33
 * @email gxz04220427 @163.com
 */
public class SpurEccentricCalculate {
    /**
     * The Gear.
     */
    private SpurGear gear;
    /**
     * The Te data.
     */
    private double[] teData;
    /**
     * The Problem.
     */
    private CircleProblem problem;
    /**
     * The Optimizer.
     */
    private CircleOptimizer optimizer;
    /**
     * The X.
     */
    private double x;
    /**
     * The Y.
     */
    private double y;
    /**
     * The R.
     */
    private double r;
    /**
     * The 极坐标X序列
     */
    private double[] xi;
    /**
     * The 极坐标Y序列
     */
    private double[] yi;
    /**
     * 偏心
     */
    private double eccentric;

    /**
     * Instantiates a new Spur eccentric calculate.
     */
    public SpurEccentricCalculate() {
        this.problem = new CircleProblem();
        this.optimizer = new CircleOptimizer();
    }

    /**
     * Instantiates a new Spur eccentric calculate.
     *
     * @param gear   the gear
     * @param teData the te data
     */
    public SpurEccentricCalculate(SpurGear gear, double[] teData) {
        this();
        this.gear = gear;
        this.teData = teData;
    }

    /**
     * 计算偏心误差.
     */
    public void calculate() {
        double rec = this.gear.getZ() * this.gear.getMn() / 2;
        int n = this.teData.length;
        this.xi = new double[n];
        this.yi = new double[n];
        for (int i = 0; i < n; i++) {
            xi[i] = (teData[i] + rec) * Math.sin(2 * Math.PI * i / n);
            yi[i] = (teData[i] + rec) * Math.cos(2 * Math.PI * i / n);
            this.problem.addPoints(xi[i], yi[i]);
        }
        optimizer.setInitX(0).setInitY(0).setInitR(rec).setMaxIterations(50).setCircle(problem);
        optimizer.Optimize();
        this.x = optimizer.getX();
        this.y = optimizer.getY();
        this.r = optimizer.getR();
        this.eccentric = Math.sqrt(x * x + y * y);
    }

    /**
     * Gets gear.
     *
     * @return the gear
     */
    public SpurGear getGear() {
        return gear;
    }

    /**
     * Sets gear.
     *
     * @param gear the gear
     *
     * @return the gear
     */
    public SpurEccentricCalculate setGear(SpurGear gear) {
        this.gear = gear;
        return this;
    }

    /**
     * Get te data double [ ].
     *
     * @return the double [ ]
     */
    public double[] getTeData() {
        return teData;
    }

    /**
     * Sets te data.
     *
     * @param teData the te data
     *
     * @return the te data
     */
    public SpurEccentricCalculate setTeData(double[] teData) {
        this.teData = teData;
        return this;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     *
     * @return the x
     */
    public SpurEccentricCalculate setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     *
     * @return the y
     */
    public SpurEccentricCalculate setY(double y) {
        this.y = y;
        return this;
    }

    /**
     * Gets r.
     *
     * @return the r
     */
    public double getR() {
        return r;
    }

    /**
     * Sets r.
     *
     * @param r the r
     *
     * @return the r
     */
    public SpurEccentricCalculate setR(double r) {
        this.r = r;
        return this;
    }

    /**
     * Gets eccentric.
     *
     * @return the eccentric
     */
    public double getEccentric() {
        return eccentric;
    }

    /**
     * Sets eccentric.
     *
     * @param eccentric the eccentric
     *
     * @return the eccentric
     */
    public SpurEccentricCalculate setEccentric(double eccentric) {
        this.eccentric = eccentric;
        return this;
    }
}
