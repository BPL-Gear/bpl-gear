package org.bpl.gear.te.errorcalculate.circlefitting;


import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

/**
 * The type Circle optimizer.
 *
 * @description
 * @create 2016 -11-15 上午9:19
 * @email gxz04220427 @163.com
 */
public class CircleOptimizer {
    /**
     * The Optimizer.
     */
    private LeastSquaresOptimizer optimizer;
    /**
     * The Max iterations.
     */
    private int maxIterations;
    /**
     * The Circle.
     */
    private CircleProblem circle;
    /**
     * The 初始X
     */
    private double initX;
    /**
     * The 初始Y
     */
    private double initY;
    /**
     * The 初始R
     */
    private double initR;

    /**
     * The 拟合后X
     */
    private double x;
    /**
     * The 拟合后Y
     */
    private double y;
    /**
     * The 拟合后R
     */
    private double r;


    /**
     * Instantiates a new Circle optimizer.
     */
    public CircleOptimizer() {
        this.optimizer = new LevenbergMarquardtOptimizer();
    }

    /**
     * Optimi.
     */
    public void Optimize() {
        double[] init = new double[3];
        init[0] = initX;
        init[1] = initY;
        init[2] = initR;
        LeastSquaresOptimizer.Optimum optimum = optimizer.optimize(
                builder(circle).maxIterations(100).start(init).build());
        final double[] paramFound = optimum.getPoint().toArray();
        this.x = paramFound[0];
        this.y = paramFound[1];
        this.r = paramFound[2];
    }

    /**
     * Builder least squares builder.
     *
     * @param problem the problem
     *
     * @return the least squares builder
     */
    public LeastSquaresBuilder builder(CircleProblem problem) {
        return new LeastSquaresBuilder()
//                .checkerPair(new SimpleVectorValueChecker(1e-6, 1e-6))
                .maxEvaluations(100)
                .maxIterations(getMaxIterations())
                .model(problem.getModelFunction(), problem.getModelFunctionJacobian())
                .target(problem.target());
//                .weight(new DiagonalMatrix(problem.weight()));  不涉及权重的问题
    }

    /**
     * Gets max iterations.
     *
     * @return the max iterations
     */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * Sets max iterations.
     *
     * @param maxIterations the max iterations
     *
     * @return the max iterations
     */
    public CircleOptimizer setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Gets circle.
     *
     * @return the circle
     */
    public CircleProblem getCircle() {
        return circle;
    }

    /**
     * Sets circle.
     *
     * @param circle the circle
     *
     * @return the circle
     */
    public CircleOptimizer setCircle(CircleProblem circle) {
        this.circle = circle;
        return this;
    }

    /**
     * Gets init x.
     *
     * @return the init x
     */
    public double getInitX() {
        return initX;
    }

    /**
     * Sets init x.
     *
     * @param initX the init x
     *
     * @return the init x
     */
    public CircleOptimizer setInitX(double initX) {
        this.initX = initX;
        return this;
    }

    /**
     * Gets init y.
     *
     * @return the init y
     */
    public double getInitY() {
        return initY;
    }

    /**
     * Sets init y.
     *
     * @param initY the init y
     *
     * @return the init y
     */
    public CircleOptimizer setInitY(double initY) {
        this.initY = initY;
        return this;
    }

    /**
     * Gets init r.
     *
     * @return the init r
     */
    public double getInitR() {
        return initR;
    }

    /**
     * Sets init r.
     *
     * @param initR the init r
     *
     * @return the init r
     */
    public CircleOptimizer setInitR(double initR) {
        this.initR = initR;
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
    public CircleOptimizer setX(double x) {
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
    public CircleOptimizer setY(double y) {
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
    public CircleOptimizer setR(double r) {
        this.r = r;
        return this;
    }
}
