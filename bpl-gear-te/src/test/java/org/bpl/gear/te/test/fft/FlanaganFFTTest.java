package org.bpl.gear.te.test.fft;

import flanagan.complex.Complex;
import flanagan.math.FourierTransform;
import flanagan.plot.PlotGraph;

import java.io.IOException;

/**
 * @description
 * @create 2016-11-06 下午8:55
 * @email gxz04220427@163.com
 */
public class FlanaganFFTTest {

    public static void main(String[] args) throws IOException {
        int nPoints = 256;  // number of points
        double[] tdata = new double[nPoints];
        double[] ydata = new double[nPoints];

        double amplitude1 = 1.0D;
        double amplitude2 = 2.0D;
        double pointsPerCycle = 200;
        double deltaT = 1.0D/pointsPerCycle;

        // Create wave form
        for(int i=0; i<nPoints; i++){
            ydata[i]= amplitude1*Math.sin(2.0D*Math.PI*i/pointsPerCycle)+ amplitude2*Math.sin(10.0D*Math.PI*i/pointsPerCycle);
            tdata[i]=i*deltaT;
        }

        // Plot original data
        PlotGraph pg0 = new PlotGraph(tdata, ydata);
        pg0.setGraphTitle("y = sin(2.pi.t) + 2sin(10.pi.t)");
        pg0.setXaxisLegend("time");
        pg0.setXaxisUnitsName("s");
        pg0.setXaxisLegend("y");
        pg0.plot();

        // Obtain Power spectrum
        FourierTransform ft0 = new FourierTransform(ydata);
        ft0.setDeltaT(deltaT);
        ft0.transform();
//        ft0.get
        double[][] powerSpectrum = ft0.powerSpectrum();
        Complex[] aaaa = ft0.getTransformedDataAsComplex();



        // Plot power spectrum
        ft0.plotPowerSpectrum();


        // Obtain the transformed data
        double[] transformedData = ft0.getTransformedDataAsAlternate();

        // Inverse transform the transformed data
        FourierTransform ft1 = new FourierTransform();
        ft1.setFftData(transformedData);
        ft1.inverse();

        // Obtain the inverse transformed data
        double[] inverseTransform = ft1.getTransformedDataAsAlternate();

        // Arrange real parts for plotting
        double[] newYdata = new double[nPoints];
        int k=0;
        for(int i=0; i<nPoints; i++){
            newYdata[i] = inverseTransform[k];
            k += 2;
        }

        // Plot inverse transformed data
        PlotGraph pg1 = new PlotGraph(tdata, newYdata);
        pg1.setGraphTitle("y = sin(2.pi.t) + 2sin(10.pi.t) fft transformed and then inverse transformed");
        pg1.setXaxisLegend("time");
        pg1.setXaxisUnitsName("s");
        pg1.setXaxisLegend("y");
        pg1.plot();
        System.in.read();
    }
}
