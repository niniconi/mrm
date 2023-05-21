package com.niniconi.mrm.service.files;

import com.niniconi.mrm.mapper.FilesMapper;
import com.niniconi.mrm.mapper.config.Config;
import com.niniconi.mrm.mapper.config.GlobalConfig;
import com.niniconi.mrm.util.Random;
import lombok.extern.slf4j.Slf4j;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileService {

    @Resource
    public FilesMapper filesMapper;
    @Resource
    public GlobalConfig globalConfig;
    @Resource
    public Config config;

    private String addFile(byte[] bytes, String relativePath){
        String fid = Random.uuid();
        String absolutePath = config.getProperty(-1,"content-path")+'/'+ relativePath;
        boolean status = save(absolutePath,bytes);
        if(!status)return null;
        filesMapper.addFile(fid,relativePath);
        return fid;
    }

    /**
     * add a new file
     * @return file ID
     */
    public String addFile(String path, MultipartFile file){
        String relativePath = path+'/'+ file.getOriginalFilename();
        byte[] temp;
        try {
            temp = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return addFile(temp,relativePath);
    }

    public String addFile(String path,String filename, ByteArrayOutputStream out){
        String relativePath = path+'/'+filename;
        byte[] temp = out.toByteArray();
        return addFile(temp,relativePath);
    }

    /**
     * add files
     * @param path label
     * @param files files
     */
    public String addFiles(String path,MultipartFile[] files){
        String fid = Random.uuid();
        String relativePath = path + '/' + "content";
        String absolutePath =config.getProperty(-1,"content-path")+ '/' + relativePath;
        boolean status = true;
        filesMapper.addFile(fid,relativePath);
        for(MultipartFile file:files){
            try {
                status = save(absolutePath+"/"+file.getOriginalFilename(),file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        System.out.println(status);
        if(!status){
            deleteFile(fid);
        }
        return fid;
    }

    /**
     * save a file as path str
     * @param path file path
     * @param bytes byte array of the file
     * @return is success? ture/false
     */
    private boolean save(String path,byte[] bytes){
        if (bytes.length>0) {
            File resourceFile = new File(path);
            if(!resourceFile.getParentFile().exists()){
                resourceFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(Files.newOutputStream(resourceFile.toPath()));
                out.write(bytes);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("Failed save file["+path+"]"+e.getMessage());
                return false;
            }
        } else {
            log.error("file["+path+"] save failed,it is empty");
            return false;
        }
        log.info("file["+path+"] save success");
        return true;
    }

    public int listFilesCount(String fid){
        File file = new File(config.getProperty(-1,"content-path")+'/'+filesMapper.getPath(fid));
        return listFilesCount(file);
    }

    public int listFilesCount(File file){
        if(!file.exists()) return -1;
        if(!file.isDirectory()) return 1;
        return listFiles(file).size();
    }

    private List<File> listFiles(File file){
        List<File> ret = new LinkedList<>();
        File[] files = file.listFiles();
        List<File> tmp = null;
        if(files != null){
            for(File f: files){
                if(f.isDirectory()){
                    tmp = listFiles(f);
                }else{
                    ret.add(f);
                }
            }
        }
        if(tmp != null) {
            ret.addAll(tmp);
        }
        return ret;
    }

    /**
     * read txt file such as markdown and txt
     * @param fid file id
     * @param page page
     */
    public String readOfString(String fid, int page){
        String path = getPath(fid);
        if(path == null)return null;
        File file = new File(path);
        if(file.isDirectory()){
            List<File> files = listFiles(file);
            if(page < files.size()){
                file = files.get(page);
            }
        }
        List<String> list;
        try {
            InputStreamReader input =  new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(input);
            list = reader.lines().collect(Collectors.toList());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String line:list) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }

    public void readImage(String fid,int page,HttpServletResponse response){
        String contentType = "image";
        String path = getPath(fid);
        if(path == null || contentType == null){
            try {
                response.sendRedirect("/error/img/404.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        File file = new File(path);
        if(file.isDirectory()){
            List<File> files = listFiles(file);
            if(page < files.size()){
                file = files.get(page);
            }
        }
        if(file.exists()){
            try {
                FileInputStream input = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                input.read(data);
                input.close();
                response.setContentType(contentType);
                OutputStream output = response.getOutputStream();
                output.write(data);
                output.flush();
                output.close();
            } catch (IOException e) {
                log.error("Failed read file" + e.getMessage());
                try {
                    response.sendRedirect("/error/img/404.png");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else{
            log.error("file["+file+"] is not exists");
            try {
                response.sendRedirect("/error/img/404.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("file["+file+"] is "+contentType);
        log.info("file["+file+"] read success");
    }

    /**
     * read a file from disk for response
     * @param fid File ID
     * @param contentType response content type
     *                  such as: "image/png","application/pdf"
     * @param response response
     */
    public void readOfBinData(String fid, int page, String contentType, HttpServletResponse response){
        String path = getPath(fid);
        if(path == null || contentType == null){
            try {
                response.sendRedirect("/error/img/404.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        File file = new File(path);
        if(file.isDirectory()){
            List<File> files = listFiles(file);
            if(page < files.size()){
                file = files.get(page);
            }
        }
        if(file.exists()){
            try {
                FileInputStream input = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                input.read(data);
                input.close();
                response.setContentType(contentType);
                OutputStream output = response.getOutputStream();
                output.write(data);
                output.flush();
                output.close();
            } catch (IOException e) {
                log.error("Failed read file" + e.getMessage());
                try {
                    response.sendRedirect("/error/img/404.png");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }else{
            log.error("file["+file+"] is not exists");
            try {
                response.sendRedirect("/error/img/404.png");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("file["+file+"] is "+contentType);
        log.info("file["+file+"] read success");
    }

    public void readErrorImage(HttpServletResponse response,int status){
        File file = new File(config.getProperty(-1,"content-path")+ "/.data/fileError.png");
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            input.read(data);
            input.close();
            response.setContentType("image/png");
            OutputStream output = response.getOutputStream();
            output.write(data);
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Fail read fileErrorFile");
        }
    }

    public long getFileSize(MultipartFile[] files) {
        long size = 0;
        for(MultipartFile file:files){
            size += file.getSize();
        }
        return size;
    }
    public long getFileSize(MultipartFile file){
       return file.getSize() ;
    }

    @Data
    public static class HdInfo{
        long usableSpace;
        long freeSpace;
        long totalSpace;

        public HdInfo(long usable,long free,long total){
            this.usableSpace = usable;
            this.freeSpace = free;
            this.totalSpace = total;
        }
    }

    /**
     * get the info of HD
     * @return Map<String, FileService.HdInfo>: key:磁盘盘符, value:磁盘信息
     */
    public Map<String, FileService.HdInfo> getHdInfo() {
        Map<String, FileService.HdInfo> map = new TreeMap<>();
        File[] roots = File.listRoots();
        for (File root : roots) {
            String hd = root.getPath();
            long free = root.getFreeSpace();
            long usable = root.getUsableSpace();
            long total = root.getTotalSpace();
            map.put(hd, new HdInfo(usable, free, total));
        }
        return map;
    }

    /**
     * get the path for file as fid(File ID)
     * @param fid File ID
     * @return File path
     */
    public String getPath(String fid) {
        String tmp = filesMapper.getPath(fid);
        if(tmp == null) return null;
        return config.getProperty(-1,"content-path")+ '/' + filesMapper.getPath(fid);
    }

    /**
     * get file count (as database's data)
     * @return file count
     */
    public int getFileCount(){
        return filesMapper.count();
    }

    /**
     * delete a file (include database's record)
     * @param fid File ID
     * @return is success?
     */
    public boolean deleteFile(String fid){
        String path = getPath(fid);
        File file = new File(path);
        if(file.exists()){
            try {
                file.delete();
            } catch ( SecurityException e){
                e.printStackTrace();
                return false;
            }
        }else{
            log.error("Failed File["+fid+"] is not exists");
            return false;
        }
        filesMapper.deleteFile(fid);
        log.info("Success delete File["+fid+"]");
        return true;
    }

    /**
     * 仅删除数据库中的文件描述信息
     */
    public void deleteFileOnlyDatabase(String fid){
        filesMapper.deleteFile(fid);
    }
}
