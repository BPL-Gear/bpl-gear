package org.bpl.gear.base.gear;


/**
 * 齿轮基类
 */
public abstract class Gear {
    /**
     * The 齿数.
     */
    private int z;
    /**
     * The 模数.
     */
    private double mn;
    /**
     * The 压力角.
     */
    private double alpha;
    /**
     * The 变位系数.
     */
    private double x;
    /**
     * The 齿宽.
     */
    private double b;

    /**
     * Gets z.
     *
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * Sets z.
     *
     * @param z the z
     *
     * @return the z
     */
    public Gear setZ(int z) {
        this.z = z;
        return this;
    }

    /**
     * Gets mn.
     *
     * @return the mn
     */
    public double getMn() {
        return mn;
    }

    /**
     * Sets mn.
     *
     * @param mn the mn
     *
     * @return the mn
     */
    public Gear setMn(double mn) {
        this.mn = mn;
        return this;
    }

    /**
     * Gets alpha.
     *
     * @return the alpha
     */
    public double getAlpha() {
        return alpha;
    }

    /**
     * Sets alpha.
     *
     * @param alpha the alpha
     *
     * @return the alpha
     */
    public Gear setAlpha(double alpha) {
        this.alpha = alpha;
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
    public Gear setX(double x) {
        this.x = x;
        return this;
    }

    /**
     * Gets b.
     *
     * @return the b
     */
    public double getB() {
        return b;
    }

    /**
     * Sets b.
     *
     * @param b the b
     *
     * @return the b
     */
    public Gear setB(double b) {
        this.b = b;
        return this;
    }
}
