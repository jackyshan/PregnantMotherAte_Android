package com.jackyshan.www.pregnantmotherate.Utils;

import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created with IntelliJ IDEA.
 * User: League
 * Date: 14-10-15
 * Time: 下午1:34
 * To change this template use File | Settings | File Templates.
 */
public class FileUtil {

    //创建txt文件
    public static boolean createTXTFileAtPath (String path, String msg, String fileName){

        boolean result = false;

        try {
            File dir = new File(path);

            if (!dir.exists()){
                dir.mkdirs();
            }

            String fileUrl = path + fileName + ".txt";

            File writeFile = new File(fileUrl);

            if (!writeFile.exists()){
                writeFile.createNewFile();
            }

            FileWriter filerWriter = new FileWriter(writeFile, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(msg);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();

            result = true;


        } catch (Exception ex) {

//            LogUtil.LogErr((new FileUtil()).getClass(), ex);

        }

        return result;
    }

    /**
     * 删除文件，可以是单个文件或文件夹
     * @param   fileName    待删除的文件名
     * @return 文件删除成功返回true,否则返回false
     */
    public static boolean delete(String fileName){

        try {

            File file = new File(fileName);
            if(!file.exists()){
                // 路径不存在
                return false;
            }else{
                // 路径存在并且是文件
                if(file.isFile()){

                    return deleteFile(fileName);
                    // 路径存在并且是目录
                }else{
                    return deleteDirectory(fileName);
                }
            }

        } catch (Exception ex) {
//            new FileUtil().logError(ex);
            return false;
        }
    }

    /**
     * 删除单个文件
     * @param   fileName    被删除文件的文件名
     * @return 单个文件删除成功返回true,否则返回false
     */
    public static boolean deleteFile(String fileName){

        try {

            File file = new File(fileName);
            if(file.isFile() && file.exists()){
                file.delete();
                System.out.println("删除单个文件"+fileName+"成功！");
                return true;
            }else{
                System.out.println("删除单个文件"+fileName+"失败！");
                return false;
            }

        } catch (Exception ex) {
//            new FileUtil().logError(ex);
            return false;
        }

    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   dir 被删除目录的文件路径
     * @return  目录删除成功返回true,否则返回false
     */
    public static boolean deleteDirectory(String dir){

        try {

            //如果dir不以文件分隔符结尾，自动添加文件分隔符
            if(!dir.endsWith(File.separator)){
                dir = dir+ File.separator;
            }
            File dirFile = new File(dir);
            //如果dir对应的文件不存在，或者不是一个目录，则退出
            if(!dirFile.exists() || !dirFile.isDirectory()){
                System.out.println("删除目录失败"+dir+"目录不存在！");
                return false;
            }
            boolean flag = true;
            //删除文件夹下的所有文件(包括子目录)
            File[] files = dirFile.listFiles();
            for(int i=0;i<files.length;i++){
                //删除子文件
                if(files[i].isFile()){
                    flag = deleteFile(files[i].getAbsolutePath());
                    if(!flag){
                        break;
                    }
                }
                //删除子目录
                else{
                    flag = deleteDirectory(files[i].getAbsolutePath());
                    if(!flag){
                        break;
                    }
                }
            }

            if(!flag){
                System.out.println("删除目录失败");
                return false;
            }

            //删除当前目录
            if(dirFile.delete()){
                System.out.println("删除目录"+dir+"成功！");
                return true;
            }else{
                System.out.println("删除目录"+dir+"失败！");
                return false;
            }

        } catch (Exception ex) {
//            new FileUtil().logError(ex);
            return false;
        }

    }

    public static boolean renameFile(String original, String destination) {

        try {

            if (original == null || original.trim().equals("") ||
                    destination == null || destination.trim().equals("")) {
                return false;
            }

            File originalFile = new File(original);
            if (!originalFile.exists())
                return false;

            File destinationFile = new File(destination);
            if (destinationFile.isDirectory())
                return false;
            if (destinationFile.exists())
                return false;

            originalFile.renameTo(new File(destination));
            return true;

        } catch (Exception ex) {
            LogUtil.LogErr(FileUtil.class, ex);
        }

        return false;

    }
}
