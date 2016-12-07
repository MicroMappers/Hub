package org.qcri.micromappers.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jlucas on 11/28/16.
 */
public class HttpDownloadUtility {
    private static final int BUFFER_SIZE = 4096;

    public static boolean downloadFile(String fileURL, String saveDir, String fileName){
        boolean downloadFileCompleted = false;
        HttpURLConnection httpConn = null;
        try{
            URL url = new URL(fileURL);
            httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {

                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();

                fileName = getFileName(fileURL, disposition, fileName);

                System.out.println("Content-Type = " + contentType);
                System.out.println("Content-Disposition = " + disposition);
                System.out.println("Content-Length = " + contentLength);
                System.out.println("fileName = " + fileName);

                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;
                String saveFilePathProcessed = saveDir + File.separator + fileName + Constants.GDELT_PROCESSED_EXTENTION;
                // check file exists
                if(!new File(saveFilePath).exists() && !new File(saveFilePathProcessed).exists()){
                    // opens an output stream to save into file
                    FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    outputStream.close();
                    inputStream.close();
                }

                downloadFileCompleted = true;
                System.out.println("File downloaded");
            } else {
                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }


        }
        catch(IOException e1){
            System.out.println("IOException : " + e1);
        }
        catch(Exception e2){
            System.out.println("Exception : " + e2);
        }
        finally{
            httpConn.disconnect();
        }

        return downloadFileCompleted;
    }

    public static boolean UserAgentBasedDownloadFile(String fileURL, String saveDir, String fileName){
        boolean downloadFileCompleted = false;
        HttpURLConnection httpConn = null;

        try{
            URL url = new URL(fileURL);

            httpConn = (HttpURLConnection) url.openConnection();
            //String cookie = httpConn.getHeaderField( "Set-Cookie").split(";")[0];
            //  List<String> cookies = httpConn.getHeaderFields().get("Set-Cookie");

            //for (String cookie : cookies) {
            //  httpConn.addRequestProperty("Cookie", cookie.split(";", 2)[0]);
            // }

            //httpConn.setDoInput(true);
            //httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept-Charset",  "UTF-8");
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
            //httpConn.setRequestProperty("Cookie", cookie );

            int responseCode = httpConn.getResponseCode();

            // always check HTTP response code first
            if (responseCode == HttpURLConnection.HTTP_OK) {

                String disposition = httpConn.getHeaderField("Content-Disposition");
                String contentType = httpConn.getContentType();
                int contentLength = httpConn.getContentLength();

                //fileName = getFileName(fileURL, disposition, fileName);

                System.out.println("Content-Type = " + contentType);
                System.out.println("Content-Disposition = " + disposition);
                System.out.println("Content-Length = " + contentLength);
                System.out.println("fileName = " + fileName);

                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = saveDir + File.separator + fileName;
                String saveFilePathProcessed = saveDir + File.separator + fileName + Constants.GDELT_PROCESSED_EXTENTION;
                // check file exists
                if(!new File(saveFilePath).exists() && !new File(saveFilePathProcessed).exists()){
                    // opens an output stream to save into file
                    FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    outputStream.close();
                    inputStream.close();
                }

                downloadFileCompleted = true;
                System.out.println("File downloaded");
            } else {
                System.out.println("No file to download. Server replied HTTP code: " + responseCode);
            }


        }
        catch(IOException e1){
            System.out.println("IOException : " + e1);
        }
        catch(Exception e2){
            System.out.println("Exception : " + e2);
        }
        finally{
            if(httpConn!=null) httpConn.disconnect();
        }

        return downloadFileCompleted;
    }

    private static String getFileName(String fileURL, String disposition, String fileName){

        if(fileName!=null && !fileName.isEmpty()){
            return fileName;
        }

        if (disposition != null) {
            // extracts file name from header field
            int index = disposition.indexOf("filename=");
            if (index > 0) {
                fileName = disposition.substring(index + 10,
                        disposition.length() - 1);
            }
        } else {
            // extracts file name from URL
            fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1,
                    fileURL.length());
        }

        return fileName;
    }
}