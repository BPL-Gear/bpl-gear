package org.bpl.gear.te.test.fft;

import org.bpl.gear.te.gt.GtMeasureOperate;
import org.bpl.gear.te.utility.FileOperate;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @description
 * @create 2016-11-07 下午2:08
 * @email gxz04220427@163.com
 */
public class CommonsMathFFTTest {
    @Test
    public void FFTTest() throws IOException {
        FileOperate f = new FileOperate();
        int ORIGIN_SIZE = 256;
        int fftSize;
        double[] originData = new double[ORIGIN_SIZE];
        for (int i = 0; i < ORIGIN_SIZE; i++) {
            originData[i] = Math.sin(2 * Math.PI * i * 0.05) + 2 * Math.sin(10 * Math.PI * i * 0.05);
        }
        f.writeArraytoFile("/Users/guoxiaozhong/Downloads/", "aaaaa.txt", originData);
        GtMeasureOperate gtMeasureOperate = new GtMeasureOperate();
        double[] fftResultData = gtMeasureOperate.fftFilter(originData, 200);
        f.writeArraytoFile("/Users/guoxiaozhong/Downloads/", "bbbbb.txt", fftResultData);
    }

    @Test
    public void GtFFTTest() throws IOException {
        String fileNo = "";
        for (int i = 1; i <= 180; i++) {
            if (i < 10) {
                fileNo = "00" + i;
            } else if (i >= 10 && i < 100) {
                fileNo = "0" + i;
            } else {
                fileNo = String.valueOf(i);
            }

            FileOperate fileOperate = new FileOperate();
            List<Double> datas = fileOperate.readFileLines("/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验数据/001/TE_Test_" + fileNo + "_left.txt");
            double[] dataAarry = new double[datas.size()];
            for (int j = 0; j < datas.size(); j++) {
                dataAarry[j] = datas.get(j);
            }
            fileOperate.writeArraytoFile("/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验数据/整理后数据/原始数据/", "TE_Test_" + fileNo + "_left.txt", dataAarry);
            GtMeasureOperate gtMeasureOperate = new GtMeasureOperate();
            double[] filtered = gtMeasureOperate.fftFilter(dataAarry, 1000);
            fileOperate.writeArraytoFile("/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验数据/整理后数据/滤波数据/", "TE_Test_" + fileNo + "_left_filtered.txt", filtered);
        }
    }
}
