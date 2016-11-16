package org.bpl.gear.te.errorcalculate.circlefitting;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小二乘拟合圆的问题
 *
 * @description
 * @create 2016 -11-14 下午9:00
 * @email gxz04220427 @163.com
 */
public class CircleProblem {
    /**
     * The 点集合
     */
    private List<double[]> points;
    /**
     * The 圆周上有多少点（用于选择哪个模型更接近于观察到的）.
     */
    private int resolution;

    /**
     * Instantiates a new Circle problem.
     *
     * @param resolution the resolution
     */
    public CircleProblem(int resolution) {
        this.points = new ArrayList<>();
        this.resolution = resolution;
    }

    /**
     * Instantiates a new Circle problem.
     */
    public CircleProblem() {
        this(500);
    }

    /**
     * Add points.
     *
     * @param px the px
     * @param py the py
     */
    public void addPoints(double px, double py) {
        this.points.add(new double[]{px, py});
    }

    /**
     * 目标集合
     *
     * @return the double [ ]
     */
    public double[] target() {
        final double[] t = new double[points.size() * 2];
        for (int i = 0; i < points.size(); i++) {
            final double[] p = points.get(i);
            final int index = i * 2;
            t[index] = p[0];
            t[index + 1] = p[1];
        }

        return t;
    }

    /**
     * 模型方程
     *
     * @return the model function
     */
    public MultivariateVectorFunction getModelFunction() {
        return new MultivariateVectorFunction() {
            @Override
            public double[] value(double[] point) throws IllegalArgumentException {
                final double cx = point[0];
                final double cy = point[1];
                final double r = point[2];

                final double[] model = new double[points.size() * 2];

                final double deltaTheta = MathUtils.TWO_PI / resolution;
                for (int i = 0; i < points.size(); i++) {
                    final double[] p = points.get(i);
                    final double px = p[0];
                    final double py = p[1];

                    double bestX = 0;
                    double bestY = 0;
                    double dMin = Double.POSITIVE_INFINITY;

                    // Find the angle for which the circle passes closest to the
                    // current point (using a resolution of 100 points along the
                    // circumference).
                    for (double theta = 0; theta <= MathUtils.TWO_PI; theta += deltaTheta) {
                        final double currentX = cx + r * FastMath.cos(theta);
                        final double currentY = cy + r * FastMath.sin(theta);
                        final double dX = currentX - px;
                        final double dY = currentY - py;
                        final double d = dX * dX + dY * dY;
                        if (d < dMin) {
                            dMin = d;
                            bestX = currentX;
                            bestY = currentY;
                        }
                    }

                    final int index = i * 2;
                    model[index] = bestX;
                    model[index + 1] = bestY;
                }

                return model;
            }
        };
    }

    /**
     * 雅克比矩阵
     *
     * @return the model function jacobian
     */
    public MultivariateMatrixFunction getModelFunctionJacobian() {
        return new MultivariateMatrixFunction() {
            @Override
            public double[][] value(double[] point) {
                return jacobian(point);
            }
        };
    }

    /**
     * 雅克比矩阵
     *
     * @param params the params
     *
     * @return the double [ ] [ ]
     */
    private double[][] jacobian(double[] params) {
        final double[][] jacobian = new double[points.size() * 2][3];

        for (int i = 0; i < points.size(); i++) {
            final int index = i * 2;
            // Partial derivative wrt x-coordinate of center.
            jacobian[index][0] = 1;
            jacobian[index + 1][0] = 0;
            // Partial derivative wrt y-coordinate of center.
            jacobian[index][1] = 0;
            jacobian[index + 1][1] = 1;
            // Partial derivative wrt radius.
            final double[] p = points.get(i);
            jacobian[index][2] = (p[0] - params[0]) / params[2];
            jacobian[index + 1][2] = (p[1] - params[1]) / params[2];
        }

        return jacobian;
    }
}
