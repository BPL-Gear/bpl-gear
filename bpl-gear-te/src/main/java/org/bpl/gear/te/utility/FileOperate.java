package org.bpl.gear.te.utility;

import com.google.gson.Gson;
import org.bpl.gear.te.gt.GtMeasure;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type 文件操作.
 *
 * @description
 * @create 2016 -11-03 下午9:12
 * @email gxz04220427 @163.com
 */
public class FileOperate {

    /**
     * 读取数据Double.
     *
     * @param path the 文件路径
     *
     * @return the list
     *
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     */
    public List<Double> readFileLines(String path) throws IOException {
        List<Double> stringList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        String s;
        double d;
        while ((s = br.readLine()) != null) {
            try {
                d = new Double(s.trim());
                stringList.add(new Double(s.trim()));
            } catch (Exception e) {

            }
        }
        stringList.remove(0);
        stringList.remove(0);
        return stringList;
    }

    /**
     * Write arrayto file.
     *
     * @param path     the path
     * @param fileName the file name
     * @param datas    the datas
     *
     * @exception IOException the io exception
     * @exception IOException the io exception
     */
    public void writeArraytoFile(String path, String fileName, List<Double> datas) throws IOException {
        File file = new File(path + fileName);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (Double data : datas) {
            fileOutputStream.write((data.toString() + "\n").getBytes());
        }
        fileOutputStream.close();
        fileOutputStream.flush();
    }

    /**
     * Write arrayto file.
     *
     * @param path     the path
     * @param fileName the file name
     * @param datas    the datas
     *
     * @exception IOException the io exception
     */
    public void writeArraytoFile(String path, String fileName, double[] datas) throws IOException {
        File file = new File(path + fileName);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (double data : datas) {
            fileOutputStream.write((data + "\n").getBytes());
        }
        fileOutputStream.close();
        fileOutputStream.flush();
    }

    /**
     * 写入测量文件.
     *
     * @param gtMeasure the gt measure
     * @param path      the path
     *
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     */
    public void writeFile(GtMeasure gtMeasure, String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        String jsonString = new Gson().toJson(gtMeasure);
        bufferedWriter.write(jsonString);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    /**
     * 读取CSV文件.
     *
     * @param path the path
     *
     * @return the list
     *
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     * @exception IOException the io exception
     */
    public List<String[]> readCsv(String path) throws IOException {
        List<String[]> strings = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
        String s;
        while ((s = br.readLine()) != null) {
            String[] paras = s.split(",");
            strings.add(paras);
        }
        return strings;
    }
}
