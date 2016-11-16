package org.bpl.gear.te.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bpl.gear.base.gear.spur.SpurGear;
import org.bpl.gear.te.MainController;
import org.bpl.gear.te.data.bean.Te;
import org.bpl.gear.te.data.mapper.GearOperate;
import org.bpl.gear.te.data.mapper.TeOperate;
import org.bpl.gear.te.error.spur.SpurGearError;
import org.bpl.gear.te.errorcalculate.SpurGearErrorCalculate;
import org.bpl.gear.te.utility.FileOperate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

/**
 * The type Gt error calculate test.
 *
 * @description
 * @create 2016 -11-15 下午6:26
 * @email gxz04220427 @163.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainController.class)
public class GtErrorCalculateTest {
    /**
     * The Logger.
     */
    private Logger logger = LogManager.getLogger(GtErrorCalculateTest.class);
    /**
     * The Gear operate.
     */
    @Autowired
    private GearOperate gearOperate;
    /**
     * The Te operate.
     */
    @Autowired
    private TeOperate teOperate;

    /**
     * Calculate error.
     *
     * @exception IOException the io exception
     */
    @Test
    public void calculateError() throws IOException {
//        List<Gear> gears = this.gearOperate.selectAllGears();
        String fileNo = "";
        for (int i = 1; i <= 180; i++) {
            if (i < 10) {
                fileNo = "00" + i;
            } else if (i >= 10 && i < 100) {
                fileNo = "0" + i;
            } else {
                fileNo = String.valueOf(i);
            }
            String file_l = "/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验数据/整理后数据/滤波数据/TE_Test_" + fileNo + "_left_filtered.txt";
            String file_r = "/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验数据/整理后数据/滤波数据/TE_Test_" + fileNo + "_right_filtered.txt";
            FileOperate fileOperate = new FileOperate();
            List<Double> leftdataList = fileOperate.readFileLines(file_l);
            List<Double> rightdataList = fileOperate.readFileLines(file_r);
            double[] leftdatas = new double[leftdataList.size()];
            double[] rightdatas = new double[rightdataList.size()];
            for (int j = 0; j < leftdataList.size(); j++) {
                leftdatas[j] = leftdataList.get(j);
                rightdatas[j] = rightdataList.get(j);
            }
            SpurGear drivingGear = new SpurGear();
            drivingGear.setZ(38).setMn(2.25).setAlpha(20).setB(20).setX(0);
            SpurGear drivedGear = new SpurGear();
            drivedGear.setZ(56).setMn(2.25).setAlpha(20).setB(20).setX(0);
            SpurGearError leftError = new SpurGearError();
            SpurGearError rightError = new SpurGearError();
            SpurGearErrorCalculate calculate = new SpurGearErrorCalculate();
            leftError = calculate.calculate(leftdatas, drivedGear);
            rightError = calculate.calculate(rightdatas, drivedGear);
            Te te = new Te();
            te.setDrivedid(7).setDrivingid(1).setEccentric_l(leftError.getEccentric()).setEccentric_r(rightError.getEccentric()).setFi_l(leftError.getFi()).setFi_r(rightError.getFi()).setFia_l(leftError.getFia()).setFia_r(rightError.getFia()).setFile_l(file_l).setFile_r(file_r).setFp_l(leftError.getFp()).setFp_r(rightError.getFp()).setFpt_l(leftError.getFpt()).setFpt_r(rightError.getFpt()).setLoada(0).setNum("TE_Test_" + fileNo).setRpm(10);
            this.teOperate.insertTe(te);
        }
    }

    /**
     * 修改齿轮测量的信息
     *
     * @exception IOException the io exception
     */
    @Test
    public void modifyGearId() throws IOException {
        FileOperate operate = new FileOperate();
        List<String[]> recordes = operate.readCsv("/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验条件图片/整理数据文件.csv");
        int drivingId = 0, drivedId = 0;
        for (String[] recorde : recordes) {
            String drivingName = recorde[1];
            String drivedName = recorde[2];
            double rpm = Double.parseDouble(recorde[4]);
            String num = recorde[5];
            switch (drivingName) {
                case "g001":
                    drivingId = 1;
                    break;
                case "g002":
                    drivingId = 2;
                    break;
                case "g003":
                    drivingId = 3;
                    break;
                case "g004":
                    drivingId = 4;
                    break;
                case "g005":
                    drivingId = 5;
                    break;
                case "g006":
                    drivingId = 6;
                    break;
            }

            switch (drivedName) {
                case "G001":
                    drivedId = 7;
                    break;
                case "G002":
                    drivedId = 8;
                    break;
                case "G003":
                    drivedId = 9;
                    break;
                case "G004":
                    drivedId = 10;
                    break;
                case "G005":
                    drivedId = 11;
                    break;
                case "G006":
                    drivedId = 12;
                    break;
            }
            this.teOperate.updateTeInfo(num, drivingId, drivedId, rpm);
        }
    }

}
