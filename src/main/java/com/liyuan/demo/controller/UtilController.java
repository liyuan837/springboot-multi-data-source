package com.liyuan.demo.controller;

import com.liyuan.demo.util.ZipUtil;
import com.liyuan.demo.domain.response.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
@RestController
@RequestMapping("/util")
public class UtilController extends BaseController {

    /*
   * 上传多个文件:produces属性防止乱码
   */
    @RequestMapping(value = "/upload", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    ResponseEntity uploadFiles(@RequestParam("file_upload") MultipartFile[] files) throws Exception {
        String realPath;
        for(int i=0;i<files.length;i++){
            if (!files[i].isEmpty()) {
                String uniqueName=files[i].getOriginalFilename();//得到文件名

                realPath="E:"+ File.separator+uniqueName;//文件上传的路径这里为E盘
                files[i].transferTo(new File(realPath));  // 转存文件
            }
        }
        return getSuccessResult();
    }

    /*
   * 下载多个文件
   */
    @RequestMapping(value = "/download")
    public ResponseEntity downloadFiles(HttpServletResponse response) {
        //定义目标文件的完整路径
        String[] paths = new String[]{"E:/pic/1.png", "E:/img/2.png"};

        //定义压缩后文件的临时路径
        String uri = "d:" + File.separator + "download.zip";//临时文件存储路径
        ZipUtil.compress(uri, paths);

        File zipFile = new File(uri);
        try {
            // 以流的形式下载文件
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(zipFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-Disposition", "attachment;filename=" + zipFile.getName());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            zipFile.delete();    //将生成的服务器端文件删除
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return getSuccessResult();
    }
}