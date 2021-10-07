package com.example.shopapp.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * The {@code CopyrightTag} class, represent creating copyright tag.
 *
 * @author Andrii Vasianovych
 * @version 1.0
 */
public class CopyrightTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(CopyrightTag.class);
    private static final String COPYRIGHT_SYMBOL = "© ";
    private static final String DASH = " - ";
    private String year;
    private String author;
    private String info;

    public void setYear(String year) {
        this.year = year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int doStartTag() {
        String copyrightNotice = makeCopyrightNotice();
        try {
            JspWriter out = pageContext.getOut();
            out.write(copyrightNotice);
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Unable to write to output stream." , e);
        }
        return SKIP_BODY;
    }

    private String makeCopyrightNotice() {
        StringBuilder notice = new StringBuilder(COPYRIGHT_SYMBOL);
        notice.append(year).append(" ").append(info).append(DASH).append(author);
        return notice.toString();
    }
}
