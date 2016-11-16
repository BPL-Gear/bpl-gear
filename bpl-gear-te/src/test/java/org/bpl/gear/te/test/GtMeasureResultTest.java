package org.bpl.gear.te.test;

import org.bpl.gear.te.utility.FileOperate;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @description
 * @create 2016-11-05 下午9:55
 * @email gxz04220427@163.com
 */
public class GtMeasureResultTest {
    @Test
    public void gtDataTest() throws IOException {
        FileOperate fileOperate = new FileOperate();
        List<Double> datas = fileOperate.readFileLines("/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验数据/001/TE_Test_001_left.txt");
        fileOperate.writeArraytoFile("/Users/guoxiaozhong/项目/汽车齿轮高效配对/E04.参考资料/实验数据/试验台实验结果/特型齿轮/实验数据/001/", "11111.txt", datas);

    }

    public static void main(String[] args) {
        System.out.print("输入\n");
        Scanner scan = new Scanner(System.in);
        String read = scan.nextLine();
        System.out.println("输入数据："+read);
    }
}
