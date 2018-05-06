#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${groupId}.app.bean.Response;
import ${groupId}.app.util.FileUpload;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("file")
@Log4j2
public class FileUploadController {

    /**
     * 文件跟路径
     */
    @Value("${symbol_dollar}{file.root.path}")
    private String fileRootPath;

    /**
     * 编辑器文件、图片上传
     *
     * @param file    文件
     * @param request 请求
     * @return 响应
     */
    @RequestMapping(value = "editor", method = RequestMethod.POST)
    @ResponseBody
    public Response upload(@RequestParam(value = "imgFile") MultipartFile file,
                           HttpServletRequest request) {
        Response response = Response.getResponse();
        ServletContext context = request.getServletContext();
        String ctx = context.getContextPath();

        //上传本地文件
        String fileName = null;
        int error = 0;
        try {
            fileName = FileUpload.upload(fileRootPath, "upload/", file, "EDITOR");
        } catch (Exception e) {
            log.error("编辑器上传失败", e);
            error = -1;
        }

        response.put("error", error);
        response.put("url", ctx + fileName);
        return response;
    }

    /**
     * ajax文件上传
     *
     * @param file 文件
     * @return 响应
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Response upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        String fileName = null;
        String status = "success";
        try {
            fileName = FileUpload.upload(fileRootPath, "upload/", file, "EDITOR");
        } catch (Exception e) {
            log.error("上传失败", e);
            status = "fail";
        }
        Response response = Response.getResponse();
        response.put("fileName", fileName);
        response.put("status", status);
        return response;
    }


}
