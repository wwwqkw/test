package com.teamdev.jxbrowser.examples.util;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**传入txt路径读取txt文件
     * @param txtPath
     * @return 返回读取到的内容
     */
    public static String readTxt(String txtPath) {
        File file = new File(txtPath);
        if(file.isFile() && file.exists()){
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuffer sb = new StringBuffer();
                String text = null;
                while((text = bufferedReader.readLine()) != null){
                    sb.append(text);
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**使用FileOutputStream来写入txt文件
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath,String content) throws IOException {
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        if(file.exists()){
            //判断文件是否存在，如果不存在就新建一个txt
            file.createNewFile();
        }
        fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(content.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();

    }

    public static void writeByte(String txtPath,byte[] content) throws IOException {
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        if(file.exists()){
            //判断文件是否存在，如果不存在就新建一个txt
            file.createNewFile();
        }
        fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(content);
        fileOutputStream.flush();
        fileOutputStream.close();

    }

    public static byte[] getFileBytes(String file) {
        try {
            File f = new File(file);
            int length = (int) f.length();
            byte[] data = new byte[length];
            FileInputStream fileInputStream = new FileInputStream(f);
            fileInputStream.read(data);
            fileInputStream.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<File> traverseFolder(File folder) {
        List<File> fileList = new ArrayList<>();
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // 如果是文件夹则递归遍历
                        List<File> fileListTmp = traverseFolder(file);
                        if (fileListTmp!= null) {
                            fileList.addAll(fileListTmp);
                        }

                    } else {
                        // 如果是文件则进行相应的操作，如打印文件名
//                        System.out.println(file.getAbsolutePath());
                        fileList.add(file);
                    }
                }
            }
            return fileList;
        }
        return null;
    }
}

