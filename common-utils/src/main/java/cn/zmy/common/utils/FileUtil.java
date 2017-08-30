package cn.zmy.common.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

public class FileUtil
{
    /**
     * 判断SD卡是否挂载
     *
     * @return SD卡是否挂载
     */
    public static boolean isSDCardMounted()
    {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public static boolean isFileExist(String filePath)
    {
        if (TextUtils.isEmpty(filePath))
        {
            return false;
        }

        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    public static void deleteFile(String file)
    {
        deleteFile(new File(file));
    }

    public static void deleteFile(File file)
    {
        if (file.isFile())
        {
            file.delete();
            return;
        }
        if (file.isDirectory())
        {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0)
            {
                file.delete();
                return;
            }
            for (File f : childFile)
            {
                deleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 获取不带后缀的文件名
     */
    public static String getFileNameNoSuffix(String fullPath)
    {
        int start = fullPath.lastIndexOf("/");
        int end = fullPath.lastIndexOf(".");
        if (start != -1 && end != -1)
        {
            return fullPath.substring(start + 1, end);
        }
        else
        {
            return null;
        }
    }

    public static boolean makeDirs(String filePath)
    {
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName))
        {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) || folder.mkdirs();
    }

    public static String getFolderName(String filePath)
    {

        if (TextUtils.isEmpty(filePath))
        {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
    }

    public static boolean writeFile(String filePath, InputStream stream)
    {
        return writeFile(filePath, stream, false);
    }

    public static boolean writeFile(String filePath, InputStream stream, boolean append)
    {
        return writeFile(filePath != null ? new File(filePath) : null, stream, append);
    }

    public static boolean writeFile(File file, InputStream stream, boolean append)
    {
        OutputStream o = null;
        try
        {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length = -1;
            while ((length = stream.read(data)) != -1)
            {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
        catch (IOException e)
        {
            throw new RuntimeException("IOException occurred. ", e);
        }
        finally
        {
            if (o != null)
            {
                try
                {
                    o.close();
                    stream.close();
                }
                catch (IOException ignored)
                {
                }
            }
        }
    }

    public static void writeString(String filePath, String s, boolean append)
    {
        writeFile(filePath, new ByteArrayInputStream(s.getBytes()), append);
    }

    public static void writeString(File file,String s) throws IOException
    {
        if (!file.exists())
        {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(s);
        writer.flush();
        writer.close();
    }

    public static String readFileAsString(File file)
    {
        if (!file.exists())
        {
            return null;
        }
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes,0,inputStream.available());
            inputStream.close();
            return new String(bytes);
        }
        catch (IOException ex)
        {
            if (inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch (IOException ignored){}
            }
            return null;
        }
    }

    public static String readFileAsString(String filePath)
    {
        return readFileAsString(new File(filePath));
    }
}
