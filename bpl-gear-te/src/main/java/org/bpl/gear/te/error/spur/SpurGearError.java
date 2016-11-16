package org.bpl.gear.te.error.spur;

import org.bpl.gear.te.error.GearError;

/**
 * The type Spur gear error.
 *
 * @description
 * @create 2016 -11-03 下午8:23
 * @email gxz04220427 @163.com
 */
public class SpurGearError extends GearError {
    /**
     * The 切向综合偏差.
     */
    private double fi;
    /**
     * The 一齿切向综合偏差.
     */
    private double fia;
    /**
     * The 齿距累计偏差.
     */
    private double fp;
    /**
     * The 单个齿距偏差+.
     */
    private double fpt;
    /**
     * The 偏心.
     */
    private double eccentric;

    /**
     * Gets fi.
     *
     * @return the fi
     */
    public double getFi() {
        return fi;
    }

    /**
     * Sets fi.
     *
     * @param fi the fi
     *
     * @return the fi
     */
    public GearError setFi(double fi) {
        this.fi = fi;
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
    public GearError setFia(double fia) {
        this.fia = fia;
        return this;
    }

    /**
     * Gets fp.
     *
     * @return the fp
     */
    public double getFp() {
        return fp;
    }

    /**
     * Sets fp.
     *
     * @param fp the fp
     *
     * @return the fp
     */
    public GearError setFp(double fp) {
        this.fp = fp;
        return this;
    }

    /**
     * Gets fpt.
     *
     * @return the fpt
     */
    public double getFpt() {
        return fpt;
    }

    /**
     * Sets fpt.
     *
     * @param fpt the fpt
     *
     * @return the fpt
     */
    public SpurGearError setFpt(double fpt) {
        this.fpt = fpt;
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
    public SpurGearError setEccentric(double eccentric) {
        this.eccentric = eccentric;
        return this;
    }
}
