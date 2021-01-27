package com.aymer.sirketimceptebackend.common.servlet;

import com.aymer.sirketimceptebackend.belge.model.Documentable;
import com.aymer.sirketimceptebackend.common.constants.IConstants;
import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class FileDownloadServlet extends HttpServlet {

    @PersistenceContext
    private EntityManager entityManager;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String contentType = IConstants.CONTENT_TYPE;
        String documentId = request.getParameter(IConstants.FILE_DOWNLOAD_MANAGER_SERVLET_ID_PARAM);
        String documentClassName = request.getParameter(IConstants.FILE_DOWNLOAD_MANAGER_SERVLET_CLASS_PARAM);

        ServletOutputStream out = null;
        try {
            Documentable document = getDocumentById(documentClassName, documentId);
            byte[] contentOfFile = document.getDocumentContent();
            String documentName = StringUtils.withoutDiacritics(document.getFileName());
            contentType = document.getMinetype();
            if (!documentName.contains(".")) {
                String fileExtension = getFileExtension(contentType);
                if (!fileExtension.isEmpty()) {
                    documentName = documentName.concat(".").concat(fileExtension);
                }
            }

            response.setContentType(contentType);
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + documentName + "\"");
            out = response.getOutputStream();
            out.write(contentOfFile);
            out.flush();

            out.close();
        } catch (Exception e) {
            throw new ServiceException("error.belge.bulunamadi");
        }
    }

    private Documentable getDocumentById(String documentClassName, String id) throws ClassNotFoundException {
        Class documentClass = Class.forName(documentClassName);
        Documentable document = (Documentable) entityManager.find(documentClass, Long.parseLong(id));
        if (document == null) {
            throw new ServiceException("error.belge.bulunamadi");
        }
        return document;
    }


    private String getFileExtension(String contentType) {
        if (contentType.equals("text/udf") || contentType.equals("image/jpeg") || contentType.equals("application/pdf") ||
                contentType.equals("text/html") || contentType.equals("image/tiff")) {
            return contentType.split("/")[1];

        } else if (contentType.equals("vnd.ms-powerpoint")) {
            return "ppt";

        } else if (contentType.equals("application/msword")) {
            return "doc";

        } else if (contentType.equals("application/vnd.ms-excel")) {
            return "xls";

        } else if (contentType.equals("application/vnd.oasis.opendocument.text")) {
            return "odt";

        } else if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return "xlsx";
        }
        return "";
    }


}
