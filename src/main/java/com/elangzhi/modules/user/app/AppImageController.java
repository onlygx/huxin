package com.elangzhi.modules.user.app;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 图片获取
 * Created by GaoXiang on 2016/5/3 0003.
 */
@Controller
@RequestMapping("/app/image")
@Api(value = "图片", description = "当刚上传的图片获取不到的时候，可以使用此工具获取", position = 2)
public class AppImageController {


    /**
     * 输出图片文件流，选择项目路径内部文件
     * @param response 返回
     * @param request 请求
     * @param src 项目根路径，例如：/images/headimg/head.jpg
     * @throws IOException
     */
    @RequestMapping(value = "/showImg", method = RequestMethod.GET)
    @ApiOperation(value = "获取图片",  notes = "根据图片相对地址获取。", position = 1)
    public void showImg(HttpServletResponse response, HttpServletRequest request,@RequestParam String src) throws IOException {

        if(!"".equals(src)){
            String realPath = request.getServletContext().getRealPath(src);
            File file  = new File(realPath);
            outputStream(response,file);
        }
    }

    /**
     * 输出文件流
     * @param response 返回信息
     * @param file 文件
     * @throws IOException 输出文件流异常
     */
    private void outputStream(HttpServletResponse response,File file) throws IOException {
        if(file.exists()){
            InputStream in = new FileInputStream(file);
            response.setContentType("image/jpeg");
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            while( in.read(b)!= -1){
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        }
    }



}
