package com.scm.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 郭老师 on 2017/5/4.
 */
public class ScmUtil {

    public void alert(HttpServletResponse resp,String str) throws IOException {

        PrintWriter pw = resp.getWriter();
        pw.print("<!DOCTYPE html><html><head></head>");
        pw.print("<body onload=alert('"+str+"')>");
        pw.print("</body></html>");
        pw.flush();
        pw.close();
    }
}
